package com.ereadly.dao;

import com.config.DatabaseConfig; 
import com.ereadly.model.Notification;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class NotificationDAO {

    public void addNotification(int userId, String message) throws SQLException {
        String sql = "INSERT INTO notifications (user_id, message, is_read) VALUES (?, ?, FALSE)";
        
        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, userId);
            ps.setString(2, message);
            ps.executeUpdate();
        }
    }

    public List<Notification> getUnreadByUserId(int userId) throws SQLException {
        List<Notification> list = new ArrayList<>();
        
        String sql = "SELECT * FROM notifications WHERE user_id = ? " +
                     "ORDER BY created_at DESC LIMIT 3";
        
        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            
            ps.setInt(1, userId);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Notification n = new Notification();
                    n.setId(rs.getInt("id"));
                    n.setUserId(rs.getInt("user_id"));
                    n.setMessage(rs.getString("message"));
                    n.setCreatedAt(rs.getTimestamp("created_at"));
                    n.setRead(rs.getBoolean("is_read"));
                    list.add(n);
                }
            }
        }
        return list;
    }

    public void markAsRead(int userId) throws SQLException {
        String sql = "UPDATE notifications SET is_read = TRUE WHERE user_id = ?";
        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, userId);
            ps.executeUpdate();
        }
    }
}