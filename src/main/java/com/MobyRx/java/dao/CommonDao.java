package com.MobyRx.java.dao;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: ashqures
 * Date: 9/5/16
 * Time: 7:02 PM
 * To change this template use File | Settings | File Templates.
 */
public interface CommonDao extends BaseDao{
    
    
    List getMasterData(String className);

}
