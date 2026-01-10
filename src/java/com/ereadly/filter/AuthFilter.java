package com.ereadly.filter;

import com.ereadly.util.SessionUtil;
import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.*;
import java.io.IOException;

@WebFilter(urlPatterns = {
    "/member/*", 
    "/admin/*", 
    "/borrow", 
    "/return", 
    "/submit-rating", 
    "/book-reviews",
    "/catalog"
})
public class AuthFilter implements Filter {

    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) res;
        String path = request.getRequestURI().substring(request.getContextPath().length());

        if (path.equals("/login") || path.equals("/register") || 
            path.startsWith("/css") || path.startsWith("/js") || path.startsWith("/assets")) {
            chain.doFilter(req, res);
            return;
        }

        HttpSession session = request.getSession(false);

        if (!SessionUtil.isLoggedIn(session)) {
            response.sendRedirect(request.getContextPath() + "/login?error=session_expired");
            return;
        }

        chain.doFilter(req, res);
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {}

    @Override
    public void destroy() {}
}