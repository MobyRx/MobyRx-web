package com.MobyRx.java.bl;

import java.util.List;

import com.MobyRx.java.entity.DrugsEntity;
import com.MobyRx.java.service.wso.ClinicWSO;

/**
 * Created by IntelliJ IDEA.
 * User: ashqures
 * Date: 9/5/16
 * Time: 6:58 PM
 * To change this template use File | Settings | File Templates.
 */
public interface BaseBL {

	List<DrugsEntity> searchDrugs(String query);
}
