package com.ereadly.controller;

import com.ereadly.dao.LoanDAO;
import com.ereadly.model.Loan;
import com.ereadly.util.SessionUtil;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

@WebServlet("/admin/manage-loans")
public class AdminManageLoansServlet extends HttpServlet {
    
    private LoanDAO loanDAO;
    
    // Inisialisasi DAO untuk memproses data transaksi peminjaman secara global
    @Override
    public void init() {
        loanDAO = new LoanDAO();
    }
    
    // Mengambil seluruh data pinjaman dan menghitung denda real-time untuk ditampilkan kepada Admin
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        HttpSession session = request.getSession(false);
        
        // Proteksi level Admin
        if (session == null || !SessionUtil.isAdmin(session)) {
            response.sendRedirect(request.getContextPath() + "/login");
            return;
        }

        try {
            List<Loan> allLoans = loanDAO.getAllLoans();

            // Kalkulasi denda jika list tidak kosong
            if (allLoans != null) {
                for (Loan l : allLoans) {
                    l.calculateFine(); 
                }
            }

            request.setAttribute("loans", allLoans);
            request.getRequestDispatcher("/WEB-INF/views/admin/manage-loans.jsp").forward(request, response);

        } catch (Exception e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Database Error");
        }
    }
}