package com.spring.ems.configuration;

import com.spring.ems.service.AdminService;
import com.spring.ems.service.TokenService;
import org.bson.types.ObjectId;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Configuration
public class AuthFilter extends GenericFilterBean {
    private final TokenService tokenService;
    private final AdminService adminService;

    AuthFilter(TokenService tokenService ,AdminService adminService)
    {
        this.tokenService= tokenService;
        this.adminService = adminService;
    }


    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        //Request from Client
        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;

        //Response from Server
        HttpServletResponse httpServletResponse = (HttpServletResponse) servletResponse;



        //Read Authorization token from request header
        String token = httpServletRequest.getHeader("Authorization");


        //Check is the request received is Option return err
        if ("OPTIONS".equalsIgnoreCase(httpServletRequest.getMethod()))
        {
            httpServletResponse.sendError(HttpServletResponse.SC_OK, "Success");
            return;
        }
        if (allowReqWithoutToken(httpServletRequest))
        {
            //Allow access to Employee and Admin signUp with out tokens
            httpServletResponse.setStatus(HttpServletResponse.SC_OK, "Success");
            filterChain.doFilter(httpServletRequest,httpServletResponse);
        }
        else
        {
            //Read the email id from the token received from client
            String emailId = tokenService.getUserToken(token);

            //Check is the email id belongs to Admin
            var admins = adminService.getAdminByEmail(emailId);

            //If email doesn't belong to admin send error
            if (admins.isEmpty())
            {
                httpServletResponse.sendError(HttpServletResponse.SC_OK, "Access Denied");
                return;
            }

            //For setting logged-in users email
            httpServletRequest.setAttribute("emailId", emailId);

            /*Removed
            //For setting admin mode
            //httpServletRequest.setAttribute("mode",2);
             */


            //passing modified serverRequest forward
            filterChain.doFilter(servletRequest,servletResponse);
        }
    }

    public boolean allowReqWithoutToken(HttpServletRequest httpServletRequest){
        //All Uri to Employee and Admin  signUp  pages returns true
        if (httpServletRequest.getRequestURI().equals("/admin/signUp")
                || httpServletRequest.getRequestURI().equals("/emp/signUp")
                || httpServletRequest.getRequestURI().equals("/admin/login" ))
        {
            return  true;
        }
        return  false;
    }
}
