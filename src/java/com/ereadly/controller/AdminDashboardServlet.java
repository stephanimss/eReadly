package com.ereadly.controller;

import com.ereadly.dao.BookDAO;
import com.ereadly.dao.LoanDAO;
import com.ereadly.model.User;
import com.ereadly.exception.AuthorizationException;
import com.ereadly.util.SessionUtil;
import java.io.IOException;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

@WebServlet("/admin/dashboard")
public class AdminDashboardServlet extends HttpServlet {
    
    private BookDAO bookDAO;
    private LoanDAO loanDAO;
    
    // Inisialisasi DAO untuk akses data buku dan peminjaman
    @Override
    public void init() {
        bookDAO = new BookDAO();
        loanDAO = new LoanDAO();
    }
    
    // Menampilkan ringkasan statistik (buku & pinjaman) di dashboard admin
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        try {
            HttpSession session = request.getSession(false);
            User user = SessionUtil.getUser(session);

            if (user == null || !user.isAdmin()) {
                throw new AuthorizationException("Akses ditolak: Anda bukan Administrator.");
            }

            int totalBooks = bookDAO.getAllBooks().size();
            int totalActiveLoans = loanDAO.getAllLoans().size();

            request.setAttribute("totalBooks", totalBooks);
            request.setAttribute("totalLoans", totalActiveLoans);
            
            request.getRequestDispatcher("/WEB-INF/views/admin/dashboard.jsp").forward(request, response);

        } catch (AuthorizationException e) {
            request.getSession().setAttribute("error", e.getMessage());
            response.sendRedirect(request.getContextPath() + "/login");
        } catch (Exception e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
    }
}