package com.MobyRx.java.service;
import com.MobyRx.java.bl.ClinicBL;
import com.MobyRx.java.bl.CommonBL;
import com.MobyRx.java.entity.ClinicEntity;
import com.MobyRx.java.service.wso.ClinicWSO;
import com.MobyRx.java.service.wso.DataMapper;
import com.MobyRx.java.service.wso.StatusWSO;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

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


/**
 * Created by IntelliJ IDEA.
 * User: uday chandu G N
 * Date: 9/5/16
 * Time: 6:57 PM
 * To change this template use File | Settings | File Templates.
 */
@Component
@Path("/clinic")
@Transactional
public class ClinicService extends BaseService{
	

	private Logger logger = LoggerFactory.getLogger(ClinicService.class);

	    private CommonBL commonBL;
	    
	    private ClinicBL clinicBL;

	    @Autowired(required = true)
	    public void setCommonBL(CommonBL commonBL) {
	        this.commonBL = commonBL;
	    }
	    @Autowired(required = true)
	    public void setCommonBL(ClinicBL clinicBL) {
	        this.clinicBL = clinicBL;
	    }
	    
	    @POST
	    public Response addClinic(ClinicWSO clinicWSO, @Context UriInfo uriInfo) throws Exception{
	    	StatusWSO statusWSO = new StatusWSO();
	    	clinicBL.save(clinicWSO,statusWSO);
	    	return  sendResponse(statusWSO);
	    	}
	    
	    @PUT
	    public Response modifyClinic(ClinicWSO clinicWSO, @Context UriInfo uriInfo) throws Exception{
	    	StatusWSO statusWSO = new StatusWSO();
	    		clinicBL.update(clinicWSO,statusWSO);
	    		return  sendResponse(statusWSO);
	    	
	    }
	    
	    @DELETE
	    public Response deleteClinic(@QueryParam("clinicId")String clinicId,@Context UriInfo uriInfo) throws Exception{
	    	StatusWSO statusWSO = new StatusWSO();
	    		clinicBL.delete(Long.parseLong(clinicId),statusWSO);
	    		return  sendResponse(statusWSO);
	    	
	    }
	    
	    @GET
	    public Response getClinic(@QueryParam("clinicId")String clinicId,@Context UriInfo uriInfo) throws Exception{
	    	StatusWSO statusWSO = new StatusWSO();
	    	ClinicEntity clinicEntity=null;
	    	clinicEntity = clinicBL.get(Long.parseLong(clinicId),statusWSO);
	    	ClinicWSO  clinicWSO = DataMapper.clinicEntityToClinicWSo(clinicEntity);
	    	logger.info("clinicWSO  ="+clinicWSO.toString());
	    	return  sendResponse(clinicWSO);
	    }
	    
	    
	    
}
