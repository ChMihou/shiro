package com.example.restful.demo.service;

import java.util.List;

public interface BaseService<T> {

    //插入
    public Integer insert(T t);

    //更新
    public Boolean update(T t);

    //删除
    public Boolean delete(Integer id);

    //查找
    public T select(Integer id);

    //查找全部
    public List<T> selectAll();

}
