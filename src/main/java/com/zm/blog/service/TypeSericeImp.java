package com.zm.blog.service;

import com.zm.blog.Inner.type;
import com.zm.blog.NotFoundException;
import com.zm.blog.dao.TypeRespository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;


@Service
public class TypeSericeImp implements TypeService{

    @Autowired
    private TypeRespository typeRespository;

    @Transactional
    @Override
    public type saveType(type _type) {
        return typeRespository.save(_type);
    }

    @Transactional
    @Override
    public type getType(Long id) {
        Optional<type> name=typeRespository.findById(id);
        return name.get();
    }

    @Override
    public type getTypeByName(String name) {
        return typeRespository.findByName(name);
    }

    @Transactional
    @Override
    public Page<type> listType(Pageable pageable) {
        return typeRespository.findAll(pageable);
    }

    @Transactional
    @Override
    public type updateType(Long id, type _type) {
        Optional<type> name=typeRespository.findById(id);
        type t=name.get();
        if(t==null)
        {
            throw new NotFoundException("不存在该类型");
        }
        BeanUtils.copyProperties(_type,t);
        return typeRespository.save(t);
    }

    @Transactional
    @Override
    public void deleteType(Long id) {
        typeRespository.deleteById(id);
    }
}
