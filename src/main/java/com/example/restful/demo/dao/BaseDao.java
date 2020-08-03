package com.example.restful.demo.dao;

import java.util.List;

public interface BaseDao<T> {

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
