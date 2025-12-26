/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ereadly.model;

/**
 *
 * @author Luluil Maknun
 */

public class Member extends User {

    private String memberId;

    public Member(int idUser, String memberId, String nama, String email, String password) {
        super(idUser, nama, email, password, "MEMBER");
        this.memberId = memberId;
    }

    public String getMemberId() {
        return memberId;
    }

    @Override
    public boolean login(String email, String password) {
        return this.email.equals(email) && this.password.equals(password);
    }
}

