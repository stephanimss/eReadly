package com.ereadly.controller;

import com.ereadly.dao.UserDAO;
import com.ereadly.model.User;
import com.ereadly.exception.AuthenticationException;
import com.ereadly.exception.InvalidInputException;
import com.ereadly.util.CredentialUtil;
import com.ereadly.util.SessionUtil; 

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet("/login")
public class AuthServlet extends HttpServlet {

    private UserDAO userDAO;

    // Inisialisasi UserDAO untuk keperluan validasi kredensial ke database
    @Override
    public void init() {
        userDAO = new UserDAO();
    }
    
    // Menangani permintaan tampilan halaman login atau pengalihan otomatis jika sesi aktif ditemukan
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        HttpSession session = request.getSession(false);
        
        if (SessionUtil.isLoggedIn(session)) {
            User user = SessionUtil.getUser(session);
            if (user != null) {
                redirectByUserRole(request, response, user);
                return;
            }
        }

        request.getRequestDispatcher("/WEB-INF/views/auth/login.jsp").forward(request, response);
    }
    
    // Memproses data login, memvalidasi input, dan menginisialisasi sesi pengguna yang berhasil login
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {

        String email = request.getParameter("email");
        String password = request.getParameter("password");

        try {
            if (!CredentialUtil.isLoginInputValid(email, password)) {
                throw new InvalidInputException("Email dan Password wajib diisi!");
            }

            User user = userDAO.authenticate(email, password);

            HttpSession session = request.getSession(true);
            session.setAttribute("user", user);
            
            redirectByUserRole(request, response, user);

        } catch (AuthenticationException | InvalidInputException e) {
            request.setAttribute("error", e.getMessage());
            request.getRequestDispatcher("/WEB-INF/views/auth/login.jsp").forward(request, response);
            
        } catch (Exception e) {
            e.printStackTrace(); 
            request.setAttribute("error", "Terjadi kesalahan sistem internal.");
            request.getRequestDispatcher("/WEB-INF/views/auth/login.jsp").forward(request, response);
        }
    }
    
    // Mengalihkan pengguna ke halaman dashboard yang sesuai berdasarkan peran (Admin/Member)
    private void redirectByUserRole(HttpServletRequest request, HttpServletResponse response, User user) 
            throws IOException {
        if (user.isAdmin()) {
            response.sendRedirect(request.getContextPath() + "/admin/manage-loans");
        } else {
            response.sendRedirect(request.getContextPath() + "/member/dashboard");
        }
    }
}