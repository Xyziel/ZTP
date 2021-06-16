package ztp.shelter.service;


import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import org.springframework.stereotype.Service;
import ztp.shelter.exceptions.JWTTokenViolatedException;
import ztp.shelter.security.jwt.helpers.JWTConstants;

import static ztp.shelter.security.jwt.helpers.JWTConstants.SECRET;
import static ztp.shelter.security.jwt.helpers.JWTConstants.TOKEN_PREFIX;

@Service
public class SessionService
{
    public String getUserRole(String token)
    {
        if (token == null)
        {
            return "UNLOGGED";
        }

        return extractRoleFromJWT(token);

    }

    public String getUserEmail(String token)
    {
        if (token == null)
        {
            return "UNLOGGED";
        }

        return extractEmailFromJWT(token);

    }

    private String extractRoleFromJWT(String token)
    {
        String role;
        if(!token.startsWith(JWTConstants.TOKEN_PREFIX))
        {
            role = "UNLOGGED";
//            throw new JWTTokenViolatedException();
        }


        try
        {
            role = JWT.require(Algorithm.HMAC512(SECRET.getBytes()))
                    .build()
                    .verify(token.replace(TOKEN_PREFIX, ""))
                    .getClaim("role").asString();
        }
        catch(JWTVerificationException e)
        {
            role = "UNLOGGED";
//            throw new JWTTokenViolatedException();
        }

        //IF something goes wrong i should check if role is null and react to that
        return role;


    }

    private String extractEmailFromJWT(String token)
    {
        String email;

        if(!token.startsWith(JWTConstants.TOKEN_PREFIX))
        {
            email = "UNLOGGED";
//            throw new JWTTokenViolatedException();
        }


        try
        {
            email = JWT.require(Algorithm.HMAC512(SECRET.getBytes()))
                    .build()
                    .verify(token.replace(TOKEN_PREFIX, ""))
                    .getSubject();
        }
        catch(JWTVerificationException e)
        {
            email = "UNLOGGED";
//            throw new JWTTokenViolatedException();
        }

        return email;
    }
}
