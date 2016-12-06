package com.MobyRx.java.bl;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: ashqures
 * Date: 9/5/16
 * Time: 7:03 PM
 * To change this template use File | Settings | File Templates.
 */
public interface CommonBL {

    void executeSQLQueryUpdate(String sqlQuery);
    
    List getMasterData(String className);


}
