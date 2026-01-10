package com.ereadly.controller;

import com.ereadly.dao.LoanDAO;
import com.ereadly.dao.NotificationDAO;
import com.ereadly.model.Loan;
import com.ereadly.model.Notification;
import com.ereadly.model.User;
import com.ereadly.util.SessionUtil;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

@WebServlet("/member/dashboard")
public class MemberDashboardServlet extends HttpServlet {

    private LoanDAO loanDAO;
    private NotificationDAO notificationDAO;
    
    // Inisialisasi DAO untuk mengakses data peminjaman dan sistem notifikasi pengguna
    @Override
    public void init() {
        loanDAO = new LoanDAO();
        notificationDAO = new NotificationDAO();
    }
    
    // Menampilkan statistik ringkas (pinjaman aktif, denda, notifikasi) dan daftar pinjaman terbaru pada dashboard member
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
            int userId = user.getId();
            
            List<Loan> activeLoansList = loanDAO.getActiveLoans(userId);
            int activeLoansCount = loanDAO.countActiveLoans(userId);
            
            long totalFine = 0;
            int overdueLoansCount = 0;

            if (activeLoansList != null) {
                for (Loan l : activeLoansList) {
                    l.calculateFine(); 
                    if (l.getFine() > 0) {
                        overdueLoansCount++;
                        totalFine += l.getFine();
                    }
                }
            }

            List<Notification> notifications = notificationDAO.getUnreadByUserId(userId);

            request.setAttribute("activeLoans", activeLoansCount);
            request.setAttribute("overdueLoans", overdueLoansCount);
            request.setAttribute("totalFine", totalFine);
            request.setAttribute("recentLoans", activeLoansList); 
            request.setAttribute("notifications", notifications);

            request.getRequestDispatcher("/WEB-INF/views/member/dashboard.jsp").forward(request, response);

        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("error", "Gagal memuat dashboard.");
            request.getRequestDispatcher("/login").forward(request, response);
        }
    }
}