package com.library_ms.libraryms_ui.domain;

public class book {
    private int Id;
    private String bookName;
    private category Category;
    private int quantity;

    public book(int id, String bookName, category category, int quantity) {
        Id = id;
        this.bookName = bookName;
        Category = category;
        this.quantity = quantity;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public category getCategory() {
        return Category;
    }

    public void setCategory(category category) {
        Category = category;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
