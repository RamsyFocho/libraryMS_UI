package com.library_ms.libraryms_ui.domain;

import java.util.Date;

public class lease {
    private int Id;
    private book Book;
    private user User;
    private Date leaseDate;
    private Date DueDate;
    private String Status;

    public lease(int id, book book, user user, Date leaseDate, Date dueDate, String status) {
        Id = id;
        Book = book;
        User = user;
        this.leaseDate = leaseDate;
        DueDate = dueDate;
        Status = status;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public book getBook() {
        return Book;
    }

    public void setBook(book book) {
        Book = book;
    }

    public user getUser() {
        return User;
    }

    public void setUser(user user) {
        User = user;
    }

    public Date getLeaseDate() {
        return leaseDate;
    }

    public void setLeaseDate(Date leaseDate) {
        this.leaseDate = leaseDate;
    }

    public Date getDueDate() {
        return DueDate;
    }

    public void setDueDate(Date dueDate) {
        DueDate = dueDate;
    }

    public String getStatus() {
        return Status;
    }

    public void setStatus(String status) {
        Status = status;
    }
}
