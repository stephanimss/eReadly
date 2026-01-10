package com.ereadly.controller;

import com.ereadly.dao.BookDAO;
import com.ereadly.model.Book;
import com.ereadly.exception.BookNotFoundException; 
import com.ereadly.exception.InvalidInputException; 
import com.ereadly.util.CredentialUtil;           
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

@WebServlet("/admin/edit-book")
public class EditBookServlet extends HttpServlet {
    private BookDAO bookDAO;
    
    // Inisialisasi BookDAO untuk memproses operasi basis data terkait buku.
    public void init() {
        bookDAO = new BookDAO();
    }
    
    // Menangani permintaan penghapusan buku atau menampilkan form (tambah/edit) berdasarkan parameter ID
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        String action = request.getParameter("action");
        String idStr = request.getParameter("id");

        try {
            if ("delete".equals(action) && !CredentialUtil.isEmpty(idStr)) {
                bookDAO.deleteBook(Integer.parseInt(idStr));
                request.getSession().setAttribute("message", "Buku berhasil dihapus!");
                response.sendRedirect("manage-books");
                return;
            } 
            
            if (!CredentialUtil.isEmpty(idStr)) {
                int id = Integer.parseInt(idStr);
                Book existingBook = bookDAO.getBookById(id);
                
                if (existingBook == null) {
                    throw new BookNotFoundException("Buku dengan ID " + id + " tidak ditemukan.");
                }
                
                request.setAttribute("book", existingBook);
                request.setAttribute("formTitle", "Edit Buku");
            } else {
                request.setAttribute("formTitle", "Tambah Buku Baru");
            }
            
            request.getRequestDispatcher("/WEB-INF/views/admin/edit-book.jsp").forward(request, response);

        } catch (BookNotFoundException e) {
            request.getSession().setAttribute("error", e.getMessage());
            response.sendRedirect("manage-books");
        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("manage-books?error=1");
        }
    }
    
    // Memproses pengiriman data form untuk menyimpan buku baru atau memperbarui data buku yang sudah ada
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        String idStr = request.getParameter("id");
        String title = request.getParameter("title");
        String author = request.getParameter("author");
        String category = request.getParameter("category");
        String stockStr = request.getParameter("stock");

        try {
            if (CredentialUtil.isEmpty(title) || CredentialUtil.isEmpty(author)) {
                throw new InvalidInputException("Judul dan Penulis tidak boleh kosong!");
            }

            int stock = Integer.parseInt(stockStr);

            if (CredentialUtil.isEmpty(idStr)) {
                bookDAO.insertBook(new Book(0, title, author, category, stock));
                request.getSession().setAttribute("message", "Buku baru berhasil ditambahkan!");
            } else {
                int id = Integer.parseInt(idStr);
                bookDAO.updateBook(new Book(id, title, author, category, stock));
                request.getSession().setAttribute("message", "Data buku berhasil diperbarui!");
            }
        } catch (InvalidInputException e) {
            request.getSession().setAttribute("error", e.getMessage());
        } catch (Exception e) {
            request.getSession().setAttribute("error", "Gagal menyimpan data: " + e.getMessage());
        }
        response.sendRedirect("manage-books");
    }
}