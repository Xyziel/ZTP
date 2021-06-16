package ztp.shelter.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;


//@Component
@WebFilter
public class CorsWebFilter implements Filter
{
    //TODO czy ja to na pewno potrzebuje?

    //    private final List<String> allowedOrigins= Arrays.asList("http://localhost:3000/");
    @Override
    public void init(FilterConfig filterConfig) throws ServletException
    {

    }


    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException
    {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        HttpServletResponse httpServletResponse=(HttpServletResponse)response;

        httpServletResponse.setHeader("Access-Control-Allow-Origin", "http://localhost:3000");
        httpServletResponse.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE, PATCH, PUT");
        httpServletResponse.setHeader("Access-Control-Allow-Headers", "Content-Type, Authorization");
        httpServletResponse.setHeader("Access-Control-Allow-Credentials", "true");
        chain.doFilter(httpServletRequest,httpServletResponse);

    }



    @Override
    public void destroy()
    {

    }
}