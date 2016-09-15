package com.MobyRx.java.dao;

import java.util.List;
import java.util.Map;

import com.MobyRx.java.entity.ClinicEntity;
import com.MobyRx.java.entity.DrugsEntity;
import com.MobyRx.java.service.wso.ClinicWSO;
import com.MobyRx.java.service.wso.StatusWSO;

/**
 * Created by IntelliJ IDEA.
 * User: ashqures
 * Date: 9/8/16
 * Time: 7:51 PM
 * To change this template use File | Settings | File Templates.
 */
public interface ClinicDao {
	public StatusWSO saveClinic(ClinicEntity clinicEntity) ;
}
