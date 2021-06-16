package ztp.shelter.security.jwt.filters;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import ztp.shelter.model.entity.User;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;

import org.springframework.security.core.*;
import ztp.shelter.security.jwt.ShelterUserDetails;

import static ztp.shelter.security.jwt.helpers.JWTConstants.*;

public class JWTAuthenticationFilter extends UsernamePasswordAuthenticationFilter
{
    private AuthenticationManager authenticationManager;

    public JWTAuthenticationFilter(AuthenticationManager authenticationManager)
    {
        this.authenticationManager = authenticationManager;
    }

    @Override
    public Authentication attemptAuthentication(
            HttpServletRequest request,
            HttpServletResponse response
    ) throws AuthenticationException
    {
        try
        {
            User userCreds = new ObjectMapper().readValue(request.getInputStream(), User.class);
            return authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            userCreds.getEmail(),
                            userCreds.getPassword()
                    )
            );
        } catch (Exception exception)
        {
            throw new RuntimeException(exception);
        }


    }

    @Override
    protected void successfulAuthentication(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain chain,
            Authentication authResult
    )
    throws IOException, ServletException
    {
//        System.out.println(authResult.getAuthorities().toArray()[0]);
//        String token = JWT.create()
//                .withSubject(
//                        ((org.springframework.security.core.userdetails.User)
//                                authResult.getPrincipal()).getUsername()
//                )
//                .withExpiresAt(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
//                .withClaim("role", authResult.getAuthorities().toArray()[0].toString())
//                .sign(Algorithm.HMAC512(SECRET.getBytes()));
//        System.out.println("witam");

        String token = JWT.create()
                .withSubject(
                        ((ShelterUserDetails)
                                authResult.getPrincipal()).getUsername()
                )
                .withExpiresAt(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .withClaim("role", authResult.getAuthorities().toArray()[0].toString())
                .sign(Algorithm.HMAC512(SECRET.getBytes()));
//        response.addHeader(HEADER_STRING, TOKEN_PREFIX + token);
        Cookie cookie = new Cookie(HEADER_STRING, TOKEN_PREFIX + token);
        cookie.setHttpOnly(true);
        response.addCookie(cookie);
    }
}
