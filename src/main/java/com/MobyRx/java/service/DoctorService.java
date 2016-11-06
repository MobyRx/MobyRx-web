package com.MobyRx.java.service;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
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

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.MobyRx.java.bl.CommonBL;
import com.MobyRx.java.bl.DoctorBL;
import com.MobyRx.java.entity.DoctorProfileEntity;
import com.MobyRx.java.entity.UserEntity;
import com.MobyRx.java.service.wso.DataMapper;
import com.MobyRx.java.service.wso.DoctorProfileWSO;
import com.MobyRx.java.service.wso.PrescriptionWSO;
import com.MobyRx.java.service.wso.StatusWSO;

@Component
@Path("/doctor")
@Transactional
public class DoctorService extends BaseService{
	

	private Logger logger = LoggerFactory.getLogger(DoctorService.class);

	    private CommonBL commonBL;
	    
	    private DoctorBL doctorBL;

	    @Autowired(required = true)
	    public void setCommonBL(CommonBL commonBL) {
	        this.commonBL = commonBL;
	    }
	    @Autowired(required = true)
	    public void setCommonBL(DoctorBL doctorBL) {
	        this.doctorBL = doctorBL;
	    }
	    
	    @POST
	    public Response addDoctor(DoctorProfileWSO doctorProfileWSO,@Context UriInfo uriInfo) throws Exception{
	    	logger.info("within add doctor");
	    	StatusWSO statusWSO = new StatusWSO();
	    	doctorBL.save(doctorProfileWSO,statusWSO);
			return sendResponse(statusWSO);
	    }
	    
	    @PUT
	    public Response updateDoctor(DoctorProfileWSO doctorProfileWSO,@Context UriInfo uriInfo) throws Exception{
	    	StatusWSO statusWSO = new StatusWSO();
	    	doctorBL.update(doctorProfileWSO,statusWSO);
			return sendResponse(statusWSO);
	    }
	    @DELETE
	    public Response deleteDoctor(@QueryParam("doctorId")String doctorId,@Context UriInfo uriInfo) throws Exception{

	    	StatusWSO statusWSO = new StatusWSO();
	    	Long id=Long.parseLong(doctorId);
	    	doctorBL.delete(id,statusWSO);	
			return sendResponse(statusWSO);
	    }
	    
	    @GET
	    public Response getDoctor(@QueryParam("query")String query, @QueryParam("filter")String filterParams) throws Exception{
	    	List<DoctorProfileEntity> doctorProfiles = doctorBL.searchDoctor(query);
	    	List<DoctorProfileWSO> doctorProfileWSO=DataMapper.transformDoctors(doctorProfiles);
	        return sendResponse(doctorProfileWSO);
	    }
	    
	    
}
