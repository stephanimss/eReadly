package com.ereadly.controller;

import com.ereadly.model.User;
import com.ereadly.util.SessionUtil; 
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

@WebServlet("/member/rating")
public class RatingViewServlet extends HttpServlet {
    // Memvalidasi sesi pengguna dan mengarahkan member ke halaman formulir ulasan buku tertentu.
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        HttpSession session = request.getSession(false);
        User user = SessionUtil.getUser(session);

        if (user == null || !user.isMember()) {
            response.sendRedirect(request.getContextPath() + "/login");
            return;
        }

        request.getRequestDispatcher("/WEB-INF/views/member/rating.jsp").forward(request, response);
    }
}