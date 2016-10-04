package com.MobyRx.java.dao;

import java.util.List;
import java.util.Map;

import com.MobyRx.java.entity.DrugsEntity;

public interface DrugDao extends BaseDao{

	List<DrugsEntity> searchDrugs(Map<String, Object> param, String query);

}
