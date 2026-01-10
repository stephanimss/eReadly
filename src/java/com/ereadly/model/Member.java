package com.ereadly.model;

public class Member extends User {

    public Member() {
        setRole("MEMBER");
    }

    public Member(int id, String nama, String email) {
        super(id, nama, email, "MEMBER");
    }
}
