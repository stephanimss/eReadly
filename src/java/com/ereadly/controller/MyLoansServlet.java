package com.ereadly.controller;

import com.ereadly.dao.LoanDAO;
import com.ereadly.model.Loan;
import com.ereadly.model.User;
import com.ereadly.util.SessionUtil; 
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

@WebServlet("/member/my-loans")
public class MyLoansServlet extends HttpServlet {

    private LoanDAO loanDAO;
    
    // Inisialisasi LoanDAO untuk menangani penarikan data riwayat peminjaman spesifik pengguna
    @Override
    public void init() throws ServletException {
        loanDAO = new LoanDAO();
    }
    
    // Memvalidasi identitas member dan menyajikan daftar seluruh riwayat pinjaman pribadi pengguna ke halaman JSP
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {

        HttpSession session = request.getSession(false);
        User user = SessionUtil.getUser(session);

        if (user == null || !user.isMember()) {
            response.sendRedirect(request.getContextPath() + "/login");
            return;
        }

        try {
            List<Loan> userLoans = loanDAO.getLoansByUser(user.getId());

            request.setAttribute("loans", userLoans);
            request.getRequestDispatcher("/WEB-INF/views/member/my-loans.jsp").forward(request, response);

        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("errorMessage", "Gagal memuat riwayat pinjaman Anda.");
            request.getRequestDispatcher("/member/dashboard").forward(request, response);
        }
    }
}

