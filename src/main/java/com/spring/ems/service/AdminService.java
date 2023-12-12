package com.spring.ems.service;

import com.spring.ems.model.*;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminService {

    private final AdminRepository adminRepository;
    private final TokenService tokenService;

    //Constructor dependency injection
    AdminService( AdminRepository adminRepository, TokenService tokenService)
    {
        this.adminRepository = adminRepository;
        this.tokenService = tokenService;
    }

    //login for Admin
    public String adminLogin(String email, String password)
    {
        String message = "Login is successful";

        //find input email from the admin table
        List<Admin> foundAdmin = adminRepository.getUserByEmail(email);

        //response structure class( message, data, token)
        var resp = new RespObject();

        if (foundAdmin.isEmpty())
        {
            message =  "Authentication failed";

        }
        else if(!foundAdmin.get(0).getPassword().equals(password))
        {
            message = "Incorrect Password";
        }
        else
        {
            String data = "{Name :"+ foundAdmin.get(0).getName() +"}";
            String token = tokenService.createToken(foundAdmin.get(0).getEmail());

            message = "Login Successful";
            resp.data = data;
            resp.token = token;
        }

        resp.message = message;
        return  resp.print();

    }


    ///Crud operation

    //Insert Admin
    public String insertAdmin(Admin admin)
    {
        adminRepository.save(admin);
        String message = "Sign-up successful";

        var resp  = new RespObject(message, admin.toString(), "");
        return  resp.print();
    }

    public List<Admin> getAdminByEmail(String email)
    {
        return  adminRepository.getUserByEmail(email);
    }
}
