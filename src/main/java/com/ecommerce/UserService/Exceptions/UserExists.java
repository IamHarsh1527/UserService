package com.ecommerce.UserService.Exceptions;

public class UserExists extends Exception{
    public UserExists(String msg){
        super(msg);
    }
}
