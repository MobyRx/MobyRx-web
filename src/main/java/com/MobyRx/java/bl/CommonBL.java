package com.MobyRx.java.bl;

import com.MobyRx.java.entity.DrugsEntity;
import com.MobyRx.java.service.wso.ClinicWSO;
import com.MobyRx.java.service.wso.StatusWSO;

import java.util.List;
import java.util.Map;

/**
 * Created by IntelliJ IDEA.
 * User: ashqures
 * Date: 9/5/16
 * Time: 7:03 PM
 * To change this template use File | Settings | File Templates.
 */
public interface CommonBL {


    List<DrugsEntity> searchDrugs(String query);

}
