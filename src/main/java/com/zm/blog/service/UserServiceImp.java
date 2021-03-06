package com.zm.blog.service;

import com.zm.blog.Inner.User;
import com.zm.blog.dao.UserRespository;
import com.zm.blog.util.MD5Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImp implements UserService {

    @Autowired
    private UserRespository userRespository;

    @Override
    public User checkUser(String username, String password) {
        User user=userRespository.findByUsernameAndPassword(username, MD5Utils.code(password));
        return user;
    }
}
