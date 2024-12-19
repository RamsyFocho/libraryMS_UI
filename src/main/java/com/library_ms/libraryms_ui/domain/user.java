package com.library_ms.libraryms_ui.domain;

public class user {
    private int Id;
    private String username;
    private String address;
    private String email;

    public user(int id, String username, String address, String email) {
        Id = id;
        this.username = username;
        this.address = address;
        this.email = email;
    }

}
