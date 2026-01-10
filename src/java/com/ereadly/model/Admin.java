package com.ereadly.model;

public class Admin extends User {

    public Admin() {
        setRole("ADMIN");
    }

    public Admin(int id, String nama, String email) {
        super(id, nama, email, "ADMIN");
    }
}
