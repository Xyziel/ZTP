package ztp.shelter.security.jwt.helpers;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;

import static ztp.shelter.security.jwt.helpers.JWTConstants.*;

//UNUSED FOR NOW
public class JWTExtractor
{
    public static String extractRoleFromJWT(String token)
    {

        if (!token.startsWith(JWTConstants.TOKEN_PREFIX))
        {
            return null;
        }

        String role = null;
        try
        {
            role = JWT.require(Algorithm.HMAC512(SECRET.getBytes()))
                    .build()
                    .verify(token.replace(TOKEN_PREFIX, ""))
                    .getClaim("role").asString();
        }
        catch(JWTVerificationException e)
        {

        }


        if(role != null)
        {
            return role;
        }

        return null;
    }

    public static String extractUsernameFromJWT(String token)
    {
        if (!token.startsWith(JWTConstants.TOKEN_PREFIX))
        {
            return null;
        }

        String username = null;

        try
        {
            username = JWT.require(Algorithm.HMAC512(SECRET.getBytes()))
                    .build()
                    .verify(token.replace(TOKEN_PREFIX, ""))
                    .getSubject();
        }
        catch(JWTVerificationException e)
        {

        }


        if(username != null)
        {
            return username;
        }

        return null;
    }
}