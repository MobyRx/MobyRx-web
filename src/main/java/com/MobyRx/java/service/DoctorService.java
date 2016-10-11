package com.MobyRx.java.service;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
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
import com.MobyRx.java.service.wso.DoctorProfileWSO;
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
	    @Path("/add")
	    @Consumes({MediaType.APPLICATION_JSON})
	    @Produces({MediaType.APPLICATION_JSON})
	    public Response addDoctor(DoctorProfileWSO doctorProfileWSO,@Context UriInfo uriInfo) throws Exception{
	    	logger.info("within add doctor");
	    	StatusWSO statusWSO = new StatusWSO();
	    	
	    	doctorBL.save(doctorProfileWSO,statusWSO);
	    	
			return sendResponse(statusWSO);
	    }
	    
	    
}
