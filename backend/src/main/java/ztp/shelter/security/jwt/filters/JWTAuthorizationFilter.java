package ztp.shelter.security.jwt.filters;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import ztp.shelter.exceptions.ExceptionResponse;
import ztp.shelter.exceptions.JWTTokenViolatedException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.Console;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

import static ztp.shelter.security.jwt.helpers.JWTConstants.*;

public class JWTAuthorizationFilter extends BasicAuthenticationFilter
{
    public JWTAuthorizationFilter(AuthenticationManager authenticationManager)
    {
        super(authenticationManager);
    }

    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain chain
    ) throws IOException, ServletException
    {
        if(request.getRequestURI().equals("/login") || request.getRequestURI().equals("/logout"))
        {
            chain.doFilter(request, response);
            return;
        }


        Cookie[] cookies = request.getCookies();
        if (cookies == null)
        {
            chain.doFilter(request, response);
            return;
        }

        String token = null;

        for (Cookie cookie : cookies)
        {
            if (cookie.getName().equals(HEADER_STRING))
            {
                token = cookie.getValue();
            }
        }


//        if (token == null || !token.startsWith(TOKEN_PREFIX))
//        {
//            chain.doFilter(request, response);
//            return;
//        }

        if (token == null)
        {
            chain.doFilter(request, response);
            return;
        }


        Object tokenOrResponse = getAuthentication(token, response);

        if (tokenOrResponse == null || tokenOrResponse instanceof UsernamePasswordAuthenticationToken)
        {
            SecurityContextHolder.getContext().setAuthentication((UsernamePasswordAuthenticationToken) tokenOrResponse);
        }
        else
        {
            response = (HttpServletResponse) tokenOrResponse;
            return;
        }

        chain.doFilter(request, response);

    }

    private Object getAuthentication(String token,
                                     HttpServletResponse response) throws IOException
    {
        if (token != null)
        {
            String user = null;
            String role = null;
            try
            {
                user = JWT.require(Algorithm.HMAC512(SECRET.getBytes()))
                        .build()
                        .verify(token.replace(TOKEN_PREFIX, ""))
                        .getSubject();

                role = JWT.require(Algorithm.HMAC512(SECRET.getBytes()))
                        .build()
                        .verify(token.replace(TOKEN_PREFIX, ""))
                        .getClaim("role").asString();
            }
            catch (JWTVerificationException e)
            {
                ExceptionResponse exceptionResponse = new ExceptionResponse("Don't even try to be an impostor", 403);
                response.setContentType("application/json");
                response.setStatus(403);
                ObjectMapper objectMapper = new ObjectMapper();
                response.getWriter().write(objectMapper.writeValueAsString(exceptionResponse));
                return response;
            }


            if (user != null)
            {
                return new UsernamePasswordAuthenticationToken(user, null,
                                                               new ArrayList<GrantedAuthority>(
                                                                       Arrays.asList(
                                                                               new SimpleGrantedAuthority(
                                                                                       role)))
                );
            }
            return null;
        }

        return null;

    }
}
