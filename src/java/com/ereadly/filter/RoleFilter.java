package com.ereadly.filter;

import com.ereadly.model.User;
import com.ereadly.util.SessionUtil; 
import com.ereadly.exception.AuthorizationException;

import java.io.IOException;
import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebFilter("/admin/*")
public class RoleFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;
        HttpSession session = req.getSession(false);

        try {
            User user = SessionUtil.getUser(session);

            if (user == null || !user.isAdmin()) {
                throw new AuthorizationException("Akses Terbatas: Anda tidak memiliki izin untuk mengakses halaman Administrator.");
            }
            
            chain.doFilter(request, response);

        } catch (AuthorizationException e) {
            req.getSession().setAttribute("error", e.getMessage());
            
            User currentUser = SessionUtil.getUser(session);
            if (currentUser != null && currentUser.isMember()) {
                resp.sendRedirect(req.getContextPath() + "/member/dashboard");
            } else {
                resp.sendRedirect(req.getContextPath() + "/login");
            }
        }
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {}

    @Override
    public void destroy() {}
}