package com.ereadly.controller;

import com.ereadly.dao.LoanDAO;
import com.ereadly.dao.BookDAO; 
import com.ereadly.dao.NotificationDAO;
import com.ereadly.interfaces.Borrowable;
import com.ereadly.model.*;
import com.ereadly.exception.*;
import com.ereadly.util.SessionUtil;
import com.ereadly.util.CredentialUtil;
import com.ereadly.util.DateUtil;
import java.io.IOException;
import java.text.SimpleDateFormat;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

@WebServlet("/borrow")
public class BorrowServlet extends HttpServlet {
    
    private LoanDAO loanDAO;
    private NotificationDAO notificationDAO;
    private BookDAO bookDAO; // Tambahkan field BookDAO
    
    // Inisialisasi seluruh DAO yang diperlukan untuk proses peminjaman, notifikasi, dan manajemen stok
    @Override
    public void init() {
        loanDAO = new LoanDAO();
        notificationDAO = new NotificationDAO();
        bookDAO = new BookDAO(); 
    }
    
    // Memproses permintaan peminjaman buku, menghitung jatuh tempo, memperbarui stok, dan mengirimkan notifikasi keberhasilan kepada member
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {

        HttpSession session = request.getSession(false);
        User user = SessionUtil.getUser(session);

        if (user == null || !user.isMember()) {
            response.sendRedirect(request.getContextPath() + "/login");
            return;
        }

        try {
            Member member = (Member) user;
            String bookIdParam = request.getParameter("bookId");
            int bookId = Integer.parseInt(bookIdParam);

            Book book = bookDAO.getBookById(bookId);
            String judul = (book != null) ? book.getTitle() : "Buku ID " + bookId;

            Loan loan = new Loan();
            loan.setBookId(bookId);
            loan.setUserId(member.getId());
            loan.borrow(member);

            java.util.Date dueDate = DateUtil.addDays(loan.getLoanDate(), 7);
            loan.setDueDate(dueDate);

            loanDAO.borrowBook(loan, member);

            java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("dd MMM yyyy");
            String tglKembali = sdf.format(loan.getDueDate());

            String msgPinjam = "Berhasil! Buku '" + judul + "' telah dipinjam. Kembalikan sebelum " + tglKembali + ".";
            notificationDAO.addNotification(user.getId(), msgPinjam);

            session.setAttribute("successMessage", "Peminjaman berhasil!");
            response.sendRedirect(request.getContextPath() + "/member/my-loans");

        } catch (Exception e) {
            e.printStackTrace();
            session.setAttribute("errorMessage", "Gagal memproses peminjaman.");
            response.sendRedirect(request.getContextPath() + "/catalog");
        }
    }
}