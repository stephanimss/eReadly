package com.ereadly.dao;

import com.config.DatabaseConfig;
import com.ereadly.model.User;
import com.ereadly.model.Admin;
import com.ereadly.model.Member;
import com.ereadly.util.PasswordUtil;
import com.ereadly.exception.AuthenticationException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDAO {

    public User authenticate(String email, String password) throws AuthenticationException {
        String sql = "SELECT * FROM users WHERE email = ?";

        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, email.trim().toLowerCase());

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    String storedHash = rs.getString("password");

                    if (PasswordUtil.checkPassword(password, storedHash)) {
                        String role = rs.getString("role");
                        
                        User user = "ADMIN".equalsIgnoreCase(role) ? new Admin() : new Member();
                        
                        user.setId(rs.getInt("id"));
                        user.setNama(rs.getString("nama"));
                        user.setEmail(rs.getString("email"));
                        user.setRole(role);
                        return user;
                    } else {
                        throw new AuthenticationException("Password yang Anda masukkan salah.");
                    }
                } else {
                    throw new AuthenticationException("Akun dengan email tersebut tidak ditemukan.");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new AuthenticationException("Terjadi masalah pada koneksi database.");
        }
    }

    public boolean emailExists(String email) {
        String sql = "SELECT 1 FROM users WHERE email = ?";
        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            
            ps.setString(1, email.trim().toLowerCase());
            try (ResultSet rs = ps.executeQuery()) {
                return rs.next(); 
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public void registerUser(String nama, String email, String plainPassword, String role) throws SQLException {
        String sql = "INSERT INTO users (nama, email, password, role) VALUES (?, ?, ?, ?)";

        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            
            String hashedPassword = PasswordUtil.hashPassword(plainPassword);
            
            ps.setString(1, nama);
            ps.setString(2, email.trim().toLowerCase());
            ps.setString(3, hashedPassword);
            ps.setString(4, role.toUpperCase()); 
            
            ps.executeUpdate();
        }
    }
}