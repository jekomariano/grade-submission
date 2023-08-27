package com.ltp.gradesubmission.security.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ltp.gradesubmission.entity.User;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

public class FilterTwo implements Filter {

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
            throws IOException, ServletException {
        User user = new ObjectMapper().readValue(servletRequest.getInputStream(), User.class);
        System.out.println(user.getUsername());
        System.out.println(user.getPassword());
        filterChain.doFilter(servletRequest, servletResponse);
    }
}
