package com.ereadly.controller;

import com.ereadly.dao.BookDAO;
import com.ereadly.model.Book;
import com.ereadly.model.User;
import com.ereadly.util.SessionUtil; 
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet("/admin/manage-books")
public class ManageBooksServlet extends HttpServlet {
    private BookDAO bookDAO;
    
    // Inisialisasi BookDAO untuk menyediakan akses ke data koleksi buku bagi administrator
    @Override
    public void init() { 
        bookDAO = new BookDAO(); 
    }
    
    // Memvalidasi otoritas admin dan mengambil daftar seluruh buku untuk ditampilkan pada halaman manajemen inventaris
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        HttpSession session = request.getSession(false);
        User user = SessionUtil.getUser(session);

        if (user == null || !user.isAdmin()) {
            response.sendRedirect(request.getContextPath() + "/login");
            return;
        }

        try {
            List<Book> books = bookDAO.getAllBooks();
            
            request.setAttribute("books", books);
            request.getRequestDispatcher("/WEB-INF/views/admin/manage-books.jsp").forward(request, response);
            
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("error", "Gagal memuat data buku.");
            request.getRequestDispatcher("/admin/dashboard").forward(request, response);
        }
    }
}