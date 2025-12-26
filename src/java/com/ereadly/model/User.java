/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ereadly.model;

/**
 *
 * @author Luluil Maknun
 */
public abstract class User {
    protected int idUser;
    protected String nama;
    protected String email;
    protected String password;
    protected String role;
    
    public User(int idUser, String nama, String email, String password, String role) {
        this.idUser = idUser;
        this.nama = nama;
        this.email = email;
        this.password = password;
        this.role = role;
    }

    public int getIdUser() { 
        return idUser; 
    }

    public void setIdUser(int idUser) { 
        this.idUser = idUser; 
    }
    
    public String getNama() { 
        return nama; 
    }

    public void setNama(String nama) { 
        this.nama = nama; 
    }

    public String getEmail() { 
        return email; 
    }

    public void setEmail(String email) { 
        this.email = email; 
    }

    public String getRole() { 
        return role; 
    }

    public void setRole(String role) { 
        this.role = role; 
    }
    
    public abstract boolean login(String email, String password);
    
     public void logout() {
        System.out.println(nama + " logout");
    }
}
