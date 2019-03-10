package com.zm.blog.service;


import com.zm.blog.Inner.User;

public interface UserService {

    //用户名检查;
    User checkUser(String username,String password);
}
