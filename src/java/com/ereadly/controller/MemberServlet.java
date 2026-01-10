package com.ereadly.controller;

import com.ereadly.model.User;
import com.ereadly.util.SessionUtil;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

@WebServlet("/member")
public class MemberServlet extends HttpServlet {
    // Menangani akses awal jalur /member, melakukan validasi otoritas role, dan mengalihkan pengguna ke halaman katalog utama
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) 
            throws ServletException, IOException {
        
        User user = SessionUtil.getUser(req.getSession(false));

        if (user == null || !user.isMember()) {
            resp.sendRedirect(req.getContextPath() + "/login");
            return;
        }

        resp.sendRedirect(req.getContextPath() + "/catalog");
    }
}