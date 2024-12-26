package com.library_ms.libraryms_ui.storage;

import com.library_ms.libraryms_ui.domain.Book;
import com.library_ms.libraryms_ui.domain.Category;

import java.util.ArrayList;
import java.util.List;

public class BookStorage {
    private static List<Book> books = new ArrayList<>();
    public List<Book> getBooks() {
        return books;
    }

    public static void addBook(Book book){
        books.add(book);
        if(book!=null){
            System.out.println("Book recorded");
        }
    }
    public Book findBookbyId(int id){
        for(Book bk : books){
            if(bk.getId() == id){
                return bk;
            }
        }
        return null;
    }
    //    update
    public boolean updateBook(int id,String bookName,Category cat, int quantity){
        Book book = findBookbyId(id);
        System.out.println(id);
        if(book != null){
            book.setBookName(bookName);
            book.setCategory(cat);
            book.setQuantity(quantity);
            System.out.println("book updated successfully: " + cat);
            return true;
        }
        else{
            System.out.println("category with id not found");
            return false;
        }
    }
}
