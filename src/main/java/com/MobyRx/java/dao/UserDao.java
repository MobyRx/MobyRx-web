package com.MobyRx.java.dao;

import java.util.List;
import java.util.Map;
import java.util.Set;

import com.MobyRx.java.entity.common.UserEntity;
import com.MobyRx.java.entity.master.RoleEntity;

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

    List<RoleEntity> getRoles(Set<String> roleNames);
    
    RoleEntity getRole(String name);
}
