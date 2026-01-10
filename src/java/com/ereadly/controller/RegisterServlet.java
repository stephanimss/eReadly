package com.ereadly.controller;

import com.ereadly.dao.UserDAO;
import com.ereadly.util.ValidationUtil;
import com.ereadly.util.CredentialUtil; 
import com.ereadly.exception.InvalidInputException; 
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

@WebServlet("/register")
public class RegisterServlet extends HttpServlet {

    private UserDAO userDAO;
    
    // Inisialisasi UserDAO untuk memproses penyimpanan data pengguna baru ke database
    @Override
    public void init() {
        userDAO = new UserDAO();
    }
    
    // Menampilkan halaman formulir registrasi kepada calon pengguna
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        request.getRequestDispatcher("/WEB-INF/views/auth/register.jsp").forward(request, response);
    }
    
    // Memproses data pendaftaran melalui validasi input, pengecekan duplikasi email, deteksi role otomatis, dan penyimpanan akun baru
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        String nama = request.getParameter("nama");
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        try {
            if (CredentialUtil.isEmpty(nama) || CredentialUtil.isEmpty(email) || CredentialUtil.isEmpty(password)) {
                throw new InvalidInputException("Semua field wajib diisi!");
            }
            
            if (!ValidationUtil.isValidEmail(email)) {
                throw new InvalidInputException("Format email tidak valid!");
            }

            if (userDAO.emailExists(email)) {
                throw new InvalidInputException("Email ini sudah digunakan!");
            }

            String role = ValidationUtil.detectRoleByEmail(email);

            userDAO.registerUser(nama, email, password, role);
            
            response.sendRedirect(request.getContextPath() + "/login?message=Registrasi Berhasil! Silakan Login.");

        } catch (InvalidInputException e) {
            request.setAttribute("error", e.getMessage());
            request.getRequestDispatcher("/WEB-INF/views/auth/register.jsp").forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("error", "Terjadi kesalahan sistem internal.");
            request.getRequestDispatcher("/WEB-INF/views/auth/register.jsp").forward(request, response);
        }
    }
}