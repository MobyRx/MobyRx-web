package com.MobyRx.java.service;
import com.MobyRx.java.bl.CommonBL;
import com.MobyRx.java.service.wso.UserWSO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
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
@Path("/MobyRx/Doctor")
@Transactional
public class DoctorService extends BaseService{
	 private Logger logger = LoggerFactory.getLogger(DoctorService.class);

	    private CommonBL commonBL;

	    @Autowired(required = true)
	    public void setCommonBL(CommonBL commonBL) {
	        this.commonBL = commonBL;
	    }
	    
	    @GET
	    @Path("/GetDoctorInfo")
	    public Response getDoctorInfo( @Context UriInfo uriInfo) {
	        return sendResponse(new UserWSO());
	    }
	    
	    @POST
	    @Path("/AddUnRegisteredPatient")
	    public Response addUnRegisteredPatient(@Context UriInfo uriInfo) {
	        return sendResponse(new UserWSO());
	    }
	    
	    @GET
	    @Path("/GetPatientFamilyInfo")
	    public Response getPatientFamilyInfo(@Context UriInfo uriInfo) {
	        return sendResponse(new UserWSO());
	    }
	    @POST
	    @Path("/CheckMedicalRegNO")
	    public Response checkMedicalRegNO(@Context UriInfo uriInfo) {
	        return sendResponse(new UserWSO());
	    }
	    @POST
	    @Path("/insertUpdateDoctor")
	    public Response insertUpdateDoctor(@Context UriInfo uriInfo) {
	        return sendResponse(new UserWSO());
	    }
	    @POST
	    @Path("/InsertDoctorGCM")
	    public Response insertDoctorGCM(@Context UriInfo uriInfo) {
	        return sendResponse(new UserWSO());
	    }
}
