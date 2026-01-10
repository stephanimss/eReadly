package com.ereadly.dao;

import com.config.DatabaseConfig;
import com.ereadly.model.Rating;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RatingDAO {

    public void addRating(int userId, int bookId, int score, String comment) throws SQLException {
        String sql = "INSERT INTO ratings (user_id, book_id, score, comment, created_at) VALUES (?, ?, ?, ?, NOW())";
        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, userId);
            ps.setInt(2, bookId);
            ps.setInt(3, score);
            ps.setString(4, comment);
            ps.executeUpdate();
        }
    }

    public boolean hasUserRated(int userId, int bookId) {
        String sql = "SELECT COUNT(*) FROM ratings WHERE user_id = ? AND book_id = ?";
        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, userId);
            ps.setInt(2, bookId);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) return rs.getInt(1) > 0;
            }
        } catch (SQLException e) { 
            e.printStackTrace(); 
        }
        return false;
    }
    
    public List<Rating> getReviewsByBookId(int bookId) {
        List<Rating> reviews = new ArrayList<>();
        String sql = "SELECT r.*, u.nama FROM ratings r " +
                     "JOIN users u ON r.user_id = u.id " +
                     "WHERE r.book_id = ? " +
                     "ORDER BY r.created_at DESC";

        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            
            ps.setInt(1, bookId);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Rating r = new Rating();
                    r.setScore(rs.getInt("score"));
                    r.setComment(rs.getString("comment"));
                    r.setUserName(rs.getString("nama")); 
                    
                    Timestamp ts = rs.getTimestamp("created_at");
                    if (ts != null) {
                        r.setCreatedAt(ts.toString());
                    }
                    
                    reviews.add(r);
                }
            }
        } catch (SQLException e) { 
            e.printStackTrace(); 
        }
        return reviews;
    }
}