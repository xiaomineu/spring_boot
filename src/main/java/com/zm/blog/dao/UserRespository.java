package com.zm.blog.dao;

import com.zm.blog.Inner.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRespository extends JpaRepository<User,Long> {

    User findByUsernameAndPassword(String username,String password);
}
