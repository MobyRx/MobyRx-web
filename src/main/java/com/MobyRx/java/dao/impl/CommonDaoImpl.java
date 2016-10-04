package com.MobyRx.java.dao.impl;

import com.MobyRx.java.dao.CommonDao;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: ashqures
 * Date: 9/5/16
 * Time: 7:00 PM
 * To change this template use File | Settings | File Templates.
 */
@Repository("commonDao")
public class CommonDaoImpl extends BaseDaoImpl implements CommonDao{

    @Override
    public List getMasterData(String className) {
        String hql = "From "+ className;
        return getCurrentSession().createQuery(hql).list();
    }
}
