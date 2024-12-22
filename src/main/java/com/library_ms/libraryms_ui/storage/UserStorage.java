package com.library_ms.libraryms_ui.storage;

import com.library_ms.libraryms_ui.domain.User;

public class UserStorage {
    private static User registeredUser;

    public static void setRegisteredUser(User user) {
        registeredUser = user;
        if(registeredUser!=null){
            System.out.println("Registered succesfully "+ user.getUsername());
        }
    }
    public static User getRegisteredUser(){
        System.out.println("wanting to get "+ registeredUser.getUsername());

        return registeredUser;
    }

}
