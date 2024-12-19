package com.library_ms.libraryms_ui.domain;

public class category {
    private int Id;
    private String Category;

    public category(int id, String category) {
        Id = id;
        Category = category;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getCategory() {
        return Category;
    }

    public void setCategory(String category) {
        Category = category;
    }
}
