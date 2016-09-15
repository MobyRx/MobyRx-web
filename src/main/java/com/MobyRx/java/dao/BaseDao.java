package com.MobyRx.java.dao;

import com.MobyRx.java.entity.BaseEntity;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: ashqures
 * Date: 9/5/16
 * Time: 6:56 PM
 * To change this template use File | Settings | File Templates.
 */
public interface BaseDao {


    void executeSQLQueryUpdate(String sqlQuery);

    void save(Object object) throws Exception;

    void saveAll(List objectList);

    void update(Object object);

    void delete(Object object);

    void delete(Class clazz, Long id);

    <T extends BaseEntity> T get( Class<T> clazz, Long id);

    <T extends BaseEntity> List get( Class<T> clazz,String associatedProperty, Long id);
}
