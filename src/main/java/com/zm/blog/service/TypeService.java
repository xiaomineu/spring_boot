package com.zm.blog.service;

import com.zm.blog.Inner.type;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface TypeService {

    //新增博客类
    type saveType(type _type);

    //根据id查询博客
    type getType(Long id);

    type getTypeByName(String name);

    //博客页
    Page<type> listType(Pageable pageable);

    //新增博客(先查询再实现)
    type updateType(Long id,type _type);

    //删除博客
    void deleteType(Long id);
}
