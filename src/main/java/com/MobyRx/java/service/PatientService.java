package com.MobyRx.java.service;

import com.MobyRx.java.bl.CommonBL;
import com.MobyRx.java.bl.PatientBL;
import com.MobyRx.java.entity.common.AccountEntity;
import com.MobyRx.java.entity.patient.PatientProfileEntity;
import com.MobyRx.java.entity.type.AccountType;
import com.MobyRx.java.service.wso.DataMapper;
import com.MobyRx.java.service.wso.PatientProfileWSO;
import com.MobyRx.java.service.wso.StatusWSO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;


/**
 * Created by IntelliJ IDEA.
 * User: uday chandu G N
 * Date: 9/5/16
 * Time: 6:57 PM
 * To change this template use File | Settings | File Templates.
 */
@Component
@Path("/patient")
@Transactional
public class PatientService extends BaseService {
    private Logger logger = LoggerFactory.getLogger(PatientService.class);

    private PatientBL patientBL;

    @Autowired
    private CommonBL commonBL;


    @Autowired(required = true)
    public void setCommonBL(PatientBL patientBL) {
        this.patientBL = patientBL;
    }

    @GET
    @Path("/{patientId}")
    public Response get(@PathParam("patientId")Long id) throws Exception {	
        return sendResponse(DataMapper.transform(patientBL.getPatient(id)));
    }

    @GET
    @Path("/dependent")
    public Response getDependent(@QueryParam("id")Long id) throws Exception {
        return sendResponse(DataMapper.transformPatients(patientBL.getDependentPatient(id)));
    }

    @POST
    public Response addPatient(PatientProfileWSO patientProfileWSO, @Context UriInfo uriInfo) throws Exception {
        StatusWSO statusWSO = new StatusWSO();
        patientBL.save(patientProfileWSO, statusWSO);
        return sendResponse(statusWSO);
    }
    @PUT
    public Response updatePatient(PatientProfileWSO patientProfileWSO, @Context UriInfo uriInfo) throws Exception {
        StatusWSO statusWSO = new StatusWSO();
        patientBL.update(patientProfileWSO, statusWSO);
        return sendResponse(statusWSO);
    }
    @GET
    public Response getPatient(@Context UriInfo uriInfo) throws Exception{
    	 Map<String, String> queryParam = getQueryParamAsStringMap(uriInfo);
    	List<PatientProfileEntity> patientProfile = patientBL.searchPatient(queryParam);
        return sendResponse(DataMapper.transformPatients(patientProfile));
    }



}


