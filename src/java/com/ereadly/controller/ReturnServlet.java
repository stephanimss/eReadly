package com.ereadly.controller;

import com.ereadly.dao.LoanDAO;
import com.ereadly.dao.NotificationDAO;
import com.ereadly.model.*;
import com.ereadly.util.SessionUtil;
import com.ereadly.util.CredentialUtil;
import com.ereadly.exception.InvalidInputException;
import java.io.IOException;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

@WebServlet("/admin/return-book")
public class ReturnServlet extends HttpServlet {
    
    private LoanDAO loanDAO;
    private NotificationDAO notificationDAO;
    
    // Inisialisasi DAO untuk memproses validasi pengembalian dan pengiriman notifikasi sistem
    @Override
    public void init() {
        loanDAO = new LoanDAO();
        notificationDAO = new NotificationDAO();
    }
    
    // Memproses pengembalian buku, menghitung denda keterlambatan, memulihkan stok buku, dan memberikan notifikasi sukses
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        HttpSession session = request.getSession(false);
        User user = SessionUtil.getUser(session);

        if (user == null) {
            response.sendRedirect(request.getContextPath() + "/login");
            return;
        }

        try {
            String loanIdParam = request.getParameter("loanId");
            if (CredentialUtil.isEmpty(loanIdParam)) {
                throw new InvalidInputException("ID Transaksi tidak ditemukan.");
            }

            int loanId = Integer.parseInt(loanIdParam);
            
            Loan existingLoan = loanDAO.getLoanById(loanId);
            
            if (existingLoan == null) {
                throw new InvalidInputException("Data peminjaman tidak ditemukan di sistem.");
            }

            existingLoan.returnBook(new Member()); 

            boolean isUpdated = loanDAO.returnBook(existingLoan, new Member());

            if (isUpdated) {
                String msg = "Terima kasih! Buku '" + existingLoan.getBookTitle() + "' telah berhasil dikembalikan.";
                notificationDAO.addNotification(existingLoan.getUserId(), msg);
                
                session.setAttribute("successMessage", "Buku berhasil dikembalikan!");
            } else {
                session.setAttribute("errorMessage", "Gagal memperbarui database.");
            }

        } catch (Exception e) {
            e.printStackTrace();
            session.setAttribute("errorMessage", "Kesalahan: " + e.getMessage());
        }
        
        if (user.isAdmin()) {
            response.sendRedirect(request.getContextPath() + "/admin/manage-loans");
        } else {
            response.sendRedirect(request.getContextPath() + "/member/dashboard");
        }
    }
}