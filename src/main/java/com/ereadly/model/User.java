package com.ereadly.model;

public abstract class User {
    protected int idUser;
    protected String nama;
    protected String email;
    protected String password;
    protected String role;

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
}