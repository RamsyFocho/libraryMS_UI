package com.library_ms.libraryms_ui.storage;

import com.library_ms.libraryms_ui.domain.Category;

import java.util.ArrayList;
import java.util.List;

public class CategoryStorage {
    private List<Category> category = new ArrayList<>();

    public List <Category> getCategory() {
        return category;
    }

    public void addCategory(Category category) {
        this.category.add(category);
        if(category!=null){
            System.out.println("Category added successfully "+ category.getCategory());
        }
    }
    public Category findCategorybyId(int id){
        for(Category cat : category){
            if(cat.getId() == id){
                return cat;
            }
        }
        return null;
    }
//    update
    public boolean updateCategory(int id,String cat){
        Category category = findCategorybyId(id);
        System.out.println(id);
        if(category != null){
            category.setCategory(cat);
            System.out.println("Category updated successfully: " + cat);
            return true;
        }
        else{
            System.out.println("category with id not found");
            return false;
        }
    }
}
