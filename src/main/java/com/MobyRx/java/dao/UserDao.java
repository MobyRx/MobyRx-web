package com.MobyRx.java.dao;

import com.MobyRx.java.entity.UserEntity;

/**
 * Created by IntelliJ IDEA.
 * User: ashqures
 * Date: 9/8/16
 * Time: 8:02 PM
 * To change this template use File | Settings | File Templates.
 */
public interface UserDao {


    UserEntity getUserByUsername(String username);
}
