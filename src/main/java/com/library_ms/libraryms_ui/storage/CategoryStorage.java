package com.library_ms.libraryms_ui.storage;

import com.library_ms.libraryms_ui.domain.Category;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class CategoryStorage {
    private static List<Category> category = new ArrayList<>();

    public List <Category> getCategory() {
        return category;
    }

    public boolean addCategory(Category category) {
        boolean found = false;
        for(Category cat : this.category){
            if(cat.getCategory().equals(category.getCategory())){
                found=true;
            }else{
                found=false;
            }
        }
        if (!found) {
            this.category.add(category);
        }
        if(category!=null){
            System.out.println("Category added successfully "+ category.getCategory());
        }
        return found;
    }
    public Category findCategorybyId(int id){
        for(Category cat : category){
            if(cat.getId() == id){
                return cat;
            }
        }
        return null;
    }
    public static Category findCategoryByName(String categoryName){
        for(Category cat : category){
            if(Objects.equals(cat.getCategory(), categoryName)){
                return cat;
            }
        }
        return null;
    }
//    update
    public boolean updateCategory(int id,String cat){
        Category category = findCategorybyId(id);
        boolean catFound = false;
        boolean updated = false;
        System.out.println(id);
        if(category != null){
            for(Category Cat : this.category){
                catFound= Cat.getCategory().equals(cat);
            }
            if(!catFound){
                category.setCategory(cat);
                System.out.println("Category updated successfully: " + cat);
                updated = true;
            }
        }
        else{
            System.out.println("category with id not found");
        }
        return updated;
    }
}
