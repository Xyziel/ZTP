package ztp.shelter.security.jwt.handlers;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import ztp.shelter.security.jwt.helpers.JWTConstants;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class JWTOnSuccessfulLogoutHandler implements LogoutSuccessHandler
{
    @Override
    public void onLogoutSuccess(
            HttpServletRequest httpServletRequest,
            HttpServletResponse httpServletResponse,
            Authentication authentication
    ) throws IOException, ServletException
    {
        Cookie cookie = new Cookie(JWTConstants.HEADER_STRING, "");
        cookie.setMaxAge(0);
        cookie.setHttpOnly(true);
        httpServletResponse.addCookie(cookie);
    }
}
