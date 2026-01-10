package com.ereadly.controller;

import com.ereadly.dao.RatingDAO;
import com.ereadly.model.User;
import com.ereadly.util.SessionUtil; 
import com.ereadly.util.CredentialUtil; 
import com.ereadly.exception.InvalidInputException; 
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

@WebServlet("/submit-rating")
public class SubmitRatingServlet extends HttpServlet {

    private RatingDAO ratingDAO;
    
    // Inisialisasi RatingDAO untuk menangani penyimpanan ulasan dan skor buku ke database
    @Override
    public void init() {
        ratingDAO = new RatingDAO();
    }

    // Memproses pengiriman ulasan, memvalidasi input, mencegah spam ulasan ganda, dan memberikan umpan balik kepada pengguna
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {

        HttpSession session = request.getSession(false);
        User user = SessionUtil.getUser(session);

        if (user == null || !user.isMember()) {
            response.sendRedirect(request.getContextPath() + "/login");
            return;
        }

        int bookId = -1;
        try {
            String bookIdParam = request.getParameter("bookId");
            String scoreParam = request.getParameter("score");
            String comment = request.getParameter("comment");

            if (CredentialUtil.isEmpty(bookIdParam) || CredentialUtil.isEmpty(scoreParam)) {
                throw new InvalidInputException("Data ulasan tidak lengkap.");
            }

            bookId = Integer.parseInt(bookIdParam);
            int score = Integer.parseInt(scoreParam);

            if (!ratingDAO.hasUserRated(user.getId(), bookId)) {
                ratingDAO.addRating(user.getId(), bookId, score, comment);
                session.setAttribute("message", "Terima kasih! Ulasan Anda telah dikirim.");
            } else {
                session.setAttribute("error", "Anda sudah memberikan ulasan untuk buku ini.");
            }
            
            response.sendRedirect(request.getContextPath() + "/book-reviews?id=" + bookId);

        } catch (InvalidInputException | NumberFormatException e) {
            session.setAttribute("error", e.getMessage());
            response.sendRedirect(request.getContextPath() + "/catalog");
        } catch (Exception e) {
            e.printStackTrace();
            session.setAttribute("error", "Terjadi kesalahan sistem saat menyimpan ulasan.");
            response.sendRedirect(request.getContextPath() + "/catalog");
        }
    }
}