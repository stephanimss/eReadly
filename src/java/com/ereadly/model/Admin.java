/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ereadly.model;

/**
 *
 * @author Luluil Maknun
 */

public class Admin extends User {

    private final String adminId;

    public Admin(int idUser, String adminId, String nama, String email, String password) {
        super(idUser, nama, email, password, "ADMIN");
        this.adminId = adminId;
    }

    public String getAdminId() {
        return adminId;
    }

    @Override
    public boolean login(String email, String password) {
        return this.email.equals(email) && this.password.equals(password);
    }
}
