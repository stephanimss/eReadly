package com.ereadly.dao;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
import com.ereadly.model.Admin;
import com.ereadly.model.Member;
import com.ereadly.model.User;
import com.ereadly.config.DatabaseConfig;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 *
 * @author Luluil Maknun
 */

public class UserDAO {

    public User authenticate(String email, String password) {
        User user = null;
        String sql = "SELECT * FROM users WHERE email=? AND password=?";

        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, email);
            ps.setString(2, password);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                int idUser = rs.getInt("id_user");
                String nama = rs.getString("nama");
                String role = rs.getString("role");

                if (role.equalsIgnoreCase("ADMIN")) {
                    user = new Admin(
                        idUser,
                        rs.getString("admin_id"),
                        nama,
                        email,
                        password
                    );
                } else {
                    user = new Member(
                        idUser,
                        rs.getString("member_id"),
                        nama,
                        email,
                        password
                    );
                }
            }

        } catch (Exception e) {
          e.printStackTrace();
        }
        return user;
    }
}
