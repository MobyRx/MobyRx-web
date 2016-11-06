package com.MobyRx.java.service;

import com.MobyRx.java.bl.CommonBL;
import com.MobyRx.java.bl.PatientBL;
import com.MobyRx.java.entity.DoctorProfileEntity;
import com.MobyRx.java.entity.PatientProfileEntity;
import com.MobyRx.java.service.wso.DataMapper;
import com.MobyRx.java.service.wso.PatientProfileWSO;
import com.MobyRx.java.service.wso.StatusWSO;
import com.MobyRx.java.service.wso.UserWSO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import javax.xml.bind.annotation.XmlElement;


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


    @Autowired(required = true)
    public void setCommonBL(PatientBL patientBL) {
        this.patientBL = patientBL;
    }

    @GET
    @Path("/byId")
    public Response get(@QueryParam("id")Long id) throws Exception {
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
    @DELETE
    public Response deletePatient(@QueryParam("patientId")String patientId,@Context UriInfo uriInfo) throws Exception{

    	StatusWSO statusWSO = new StatusWSO();
    	Long id=Long.parseLong(patientId);
    	patientBL.delete(id,statusWSO);	
		return sendResponse(statusWSO);
    }
    
    @GET
    public Response getPatient(@QueryParam("query")String query, @QueryParam("filter")String filterParams) throws Exception{
    	List<PatientProfileEntity> patientProfile = patientBL.searchPatient(query);
        return sendResponse(DataMapper.transformPatients(patientProfile));
    }
}


