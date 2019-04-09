package com.zm.blog.dao;

import com.zm.blog.Inner.type;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TypeRespository extends JpaRepository<type,Long> {
    type findByName(String name);
}

