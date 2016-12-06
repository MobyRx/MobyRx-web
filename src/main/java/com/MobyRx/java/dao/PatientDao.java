package com.MobyRx.java.dao;

import com.MobyRx.java.entity.patient.PatientProfileEntity;

import java.util.List;
import java.util.Map;

/**
 * Created by IntelliJ IDEA.
 * User: ashqures
 * Date: 9/7/16
 * Time: 9:38 PM
 * To change this template use File | Settings | File Templates.
 */
public interface PatientDao extends BaseDao{

	List<PatientProfileEntity> searchPatient(Map<String, String> queryParam);

 
}
