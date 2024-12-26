package com.library_ms.libraryms_ui.storage;

import com.library_ms.libraryms_ui.domain.Category;
import com.library_ms.libraryms_ui.domain.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class UserStorage {
    private static List<User> registeredUser = new ArrayList<>();

    public void addRegisteredUser(User newUser) {
        boolean found= false;
        for(User user : registeredUser){
            if(user.getUsername().equals(newUser.getUsername())){
                found=true;
            }else{
                found=false;
            }
        }
        if (!found) {
            registeredUser.add(newUser);
        }
        if(newUser!=null){
            System.out.println("User added successfully "+ newUser.getUsername());
        }
    }
    public static boolean getRegisteredUser(String un, String pw){
        boolean found = false;
        for(User user : registeredUser){
            if (Objects.equals(user.getUsername(), un) && Objects.equals(user.getPassword(), pw)){
                found= true;
            }else{
                found= false;
            }
        }
        return found;
    }

}
