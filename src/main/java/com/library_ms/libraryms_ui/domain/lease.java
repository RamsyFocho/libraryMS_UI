package com.library_ms.libraryms_ui.domain;

import java.util.Date;

public class lease {
    private int Id;
    private com.library_ms.libraryms_ui.domain.Book Book;
    private com.library_ms.libraryms_ui.domain.User User;
    private Date leaseDate;
    private Date DueDate;
    private String Status;

    public lease(int id, com.library_ms.libraryms_ui.domain.Book book, com.library_ms.libraryms_ui.domain.User user, Date leaseDate, Date dueDate, String status) {
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

    public com.library_ms.libraryms_ui.domain.Book getBook() {
        return Book;
    }

    public void setBook(com.library_ms.libraryms_ui.domain.Book book) {
        Book = book;
    }

    public com.library_ms.libraryms_ui.domain.User getUser() {
        return User;
    }

    public void setUser(com.library_ms.libraryms_ui.domain.User user) {
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
