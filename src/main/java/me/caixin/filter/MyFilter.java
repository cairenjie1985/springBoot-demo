package me.caixin.filter;

import java.io.IOException;

import javax.servlet.*;

/**
 * Project Name: SpringBootTest
 * Package Name: me.caixin.configuration.filter
 * Function:
 * User: roy Date: 2017-09-01
 */
public class MyFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
            throws IOException, ServletException {
        System.out.println("MyFilter");
        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {

    }

}
