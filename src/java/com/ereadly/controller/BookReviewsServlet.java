package com.ereadly.controller;

import com.ereadly.dao.RatingDAO;
import com.ereadly.model.Rating;
import com.ereadly.util.SessionUtil;
import com.ereadly.util.CredentialUtil;
import com.ereadly.exception.InvalidInputException;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

@WebServlet("/book-reviews")
public class BookReviewsServlet extends HttpServlet {
    
    private RatingDAO ratingDAO;
    
    // Inisialisasi RatingDAO untuk mengakses data ulasan buku
    @Override
    public void init() {
        ratingDAO = new RatingDAO();
    }
    
    // Memvalidasi identitas pengguna, memproses ID buku, dan menampilkan daftar ulasan dari database ke JSP.
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        if (!SessionUtil.isLoggedIn(request.getSession(false))) {
            response.sendRedirect(request.getContextPath() + "/login");
            return;
        }

        String idParam = request.getParameter("id");

        try {
            if (CredentialUtil.isEmpty(idParam)) {
                throw new InvalidInputException("ID Buku diperlukan untuk melihat ulasan.");
            }

            int bookId = Integer.parseInt(idParam);
            
            List<Rating> reviews = ratingDAO.getReviewsByBookId(bookId);
            
            request.setAttribute("reviews", reviews);
            request.getRequestDispatcher("/WEB-INF/views/member/book-reviews.jsp").forward(request, response);

        } catch (InvalidInputException | NumberFormatException e) {
            response.sendRedirect(request.getContextPath() + "/catalog");
        } catch (Exception e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
    }
}