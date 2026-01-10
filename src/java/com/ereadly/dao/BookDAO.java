package com.ereadly.dao;

import com.config.DatabaseConfig;
import com.ereadly.model.Book;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BookDAO {

    public List<Book> getAllBooks() {
        List<Book> books = new ArrayList<>();
        String sql = "SELECT b.*, COALESCE(AVG(r.score), 0) as avg_rating " +
                     "FROM books b " +
                     "LEFT JOIN ratings r ON b.id = r.book_id " +
                     "GROUP BY b.id";

        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Book b = new Book();
                b.setId(rs.getInt("id"));
                b.setTitle(rs.getString("title"));
                b.setAuthor(rs.getString("author"));
                b.setCategory(rs.getString("category"));
                b.setStock(rs.getInt("stock"));
                b.setAverageRating(rs.getDouble("avg_rating")); 
                books.add(b);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return books;
    }

    public void reduceStock(Connection conn, int bookId) throws SQLException {
        String sql = "UPDATE books SET stock = stock - 1 WHERE id = ? AND stock > 0";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, bookId);
            int rowsAffected = ps.executeUpdate();
            
            if (rowsAffected == 0) {
                throw new SQLException("Gagal kurangi stok: Stok habis atau buku tidak ditemukan.");
            }
        }
    }

    public void addStock(Connection conn, int bookId) throws SQLException {
        String sql = "UPDATE books SET stock = stock + 1 WHERE id = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, bookId);
            ps.executeUpdate();
        }
    }

    public Book getBookById(int id) {
        String sql = "SELECT * FROM books WHERE id = ?";
        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return new Book(
                        rs.getInt("id"),
                        rs.getString("title"),
                        rs.getString("author"),
                        rs.getString("category"),
                        rs.getInt("stock")
                    );
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void insertBook(Book book) throws SQLException {
        String sql = "INSERT INTO books (title, author, category, stock) VALUES (?, ?, ?, ?)";
        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, book.getTitle());
            ps.setString(2, book.getAuthor());
            ps.setString(3, book.getCategory());
            ps.setInt(4, book.getStock());
            ps.executeUpdate();
        }
    }

    public void updateBook(Book book) throws SQLException {
        String sql = "UPDATE books SET title = ?, author = ?, category = ?, stock = ? WHERE id = ?";
        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, book.getTitle());
            ps.setString(2, book.getAuthor());
            ps.setString(3, book.getCategory());
            ps.setInt(4, book.getStock());
            ps.setInt(5, book.getId());
            ps.executeUpdate();
        }
    }

    public void deleteBook(int id) throws SQLException {
        String sql = "DELETE FROM books WHERE id = ?";
        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        }
    }
}