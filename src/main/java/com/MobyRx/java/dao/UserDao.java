package com.MobyRx.java.dao;

import java.util.Map;

import com.MobyRx.java.entity.UserEntity;;

/**
 * Created by IntelliJ IDEA.
 * User: ashqures
 * Date: 9/8/16
 * Time: 8:02 PM
 * To change this template use File | Settings | File Templates.
 */
public interface UserDao extends BaseDao{


    UserEntity getUserByUsername(String username) ;
    
    UserEntity searchUser(Map<String, Object> param, String query);
}
