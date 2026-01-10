package com.ereadly.controller;

import com.ereadly.dao.BookDAO;
import com.ereadly.model.Book;
import com.ereadly.model.User;
import com.ereadly.util.SessionUtil; 
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

@WebServlet("/catalog")
public class CatalogServlet extends HttpServlet {
    
    private BookDAO bookDAO;
    
    // Menginisialisasi objek BookDAO untuk menangani pengambilan data koleksi buku dari database
    @Override
    public void init() throws ServletException {
        bookDAO = new BookDAO();
    }
    
    // Memvalidasi sesi pengguna dan menyediakan daftar seluruh buku untuk ditampilkan pada halaman katalog member.
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        HttpSession session = request.getSession(false);
        User user = SessionUtil.getUser(session);

        if (user == null) {
            response.sendRedirect(request.getContextPath() + "/login");
            return;
        }

        try {
            List<Book> books = bookDAO.getAllBooks();
            
            request.setAttribute("books", books);
            request.getRequestDispatcher("/WEB-INF/views/member/catalog.jsp").forward(request, response);
            
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("error", "Gagal memuat katalog buku. Silakan coba lagi nanti.");
            request.getRequestDispatcher("/WEB-INF/views/member/dashboard.jsp").forward(request, response);
        }
    }
}