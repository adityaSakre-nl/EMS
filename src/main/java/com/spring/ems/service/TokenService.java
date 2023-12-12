package com.spring.ems.service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;
import java.util.Date;

@Service
public class TokenService {

    //Random string to seed the token creation
    public static final String token_secret = "asdasdasdaweweqedff";

    //Create a Token on Email and data
    public String createToken(String userEmail){

        try{
            //Algorithm used for creating Token
            Algorithm algo = Algorithm.HMAC256(token_secret);

            //Token payload contains email ID and date of creation
            String token = JWT.create().withClaim("emailID", userEmail).
                    withClaim("createAt", new Date()).
                    sign(algo);

            return token;
        }
        catch(UnsupportedEncodingException | JWTCreationException e){
            e.printStackTrace();
        }

        return null;
    }

    //Decoding token to get Email id
    public String getUserToken(String token)
    {
        try{
            //Algorithm used for creating Token
            Algorithm algo = Algorithm.HMAC256(token_secret);
            JWTVerifier jwtVerifier = JWT.require(algo).build();

            DecodedJWT decodedJWT = jwtVerifier.verify(token);

            //Retrieve email id from the input token
            return decodedJWT.getClaim("emailID").asString();
        }
        catch (JWTCreationException | UnsupportedEncodingException e)
        {
            e.printStackTrace();
        }
        return null;
    }

}

