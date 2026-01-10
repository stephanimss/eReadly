package com.ereadly.controller;

import com.ereadly.util.SessionUtil; 
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/logout")
public class LogoutServlet extends HttpServlet {
    
    // Mengakhiri sesi pengguna secara aman dengan menghapus data atribut dari memori server dan mengalihkan kembali ke halaman login
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        HttpSession session = request.getSession(false);
        
        if (session != null) {
            SessionUtil.invalidate(session);
            System.out.println("DEBUG: Logout berhasil melalui SessionUtil.");
        }

        response.sendRedirect(request.getContextPath() + "/login?message=Anda telah berhasil keluar.");
    }
}