package com.library_ms.libraryms_ui.domain;

public class Staff extends User {
    private String firstName;
    private String lastName;

    public Staff(int id, String firstName, String lastName, String username, String email,String password) {
        super(id, username, email, password);
        this.firstName = firstName;
        this.lastName = lastName;
    }


    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }


}
