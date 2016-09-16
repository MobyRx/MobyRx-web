package com.MobyRx.java.service;
import com.MobyRx.java.bl.ClinicBL;
import com.MobyRx.java.bl.CommonBL;
import com.MobyRx.java.bl.impl.ClinicBLImpl;
import com.MobyRx.java.dao.impl.ClinicDaoImpl;
import com.MobyRx.java.entity.ClinicEntity;
import com.MobyRx.java.service.wso.UserWSO;
import com.MobyRx.java.service.wso.ClinicWSO;
import com.MobyRx.java.service.wso.StatusWSO;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
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
	    @Consumes({MediaType.APPLICATION_JSON})
	    @Produces({MediaType.APPLICATION_JSON})
	    @Path("/add")
	    public Response addClinic(ClinicWSO clinicWSO, @Context UriInfo uriInfo) {
	    	StatusWSO statusWSO = new StatusWSO();
	    	try
	    	{
	    		clinicBL.save(clinicWSO);
	    	}
	    	catch(Exception Ex)
	    	{
	    		statusWSO.setCode(400);
	    		statusWSO.setMessage(Ex.getMessage());
	    		return sendResponse(statusWSO);
	    	}
	    	statusWSO.setCode(200);
    		statusWSO.setMessage("Sucessful");
    		return sendResponse(statusWSO);
	    }
	    @POST
	    @Consumes({MediaType.APPLICATION_JSON})
	    @Produces({MediaType.APPLICATION_JSON})
	    @Path("/modify")
	    public Response modifyClinic(ClinicWSO clinicWSO, @Context UriInfo uriInfo) {
	    	StatusWSO statusWSO = new StatusWSO();
	    	try
	    	{
	    		clinicBL.modify(clinicWSO);
	    	}
	    	catch(Exception Ex)
	    	{
	    		statusWSO.setCode(400);
	    		statusWSO.setMessage(Ex.getMessage());
	    		return sendResponse(statusWSO);
	    	}
	    	statusWSO.setCode(200);
    		statusWSO.setMessage("Sucessful");
    		return sendResponse(statusWSO);
	    }
	    
	    @GET
	    @Consumes({MediaType.APPLICATION_JSON})
	    @Produces({MediaType.APPLICATION_JSON})
	    @Path("/delete")
	    public Response deleteClinic(@QueryParam("id")String id,@Context UriInfo uriInfo) {
	    	StatusWSO statusWSO = new StatusWSO();
	    	try
	    	{
	    		clinicBL.delete(Long.parseLong(id));
	    	}
	    	catch(Exception Ex)
	    	{
	    		statusWSO.setCode(400);
	    		statusWSO.setMessage(Ex.getMessage());
	    		return sendResponse(statusWSO);
	    	}
	    	statusWSO.setCode(200);
    		statusWSO.setMessage("Sucessful");
    		return sendResponse(statusWSO);
	    }
	    
	    @GET
	    @Consumes({MediaType.APPLICATION_JSON})
	    @Produces({MediaType.APPLICATION_JSON})
	    @Path("/get")
	    public Response getClinic(@QueryParam("id")String id,@Context UriInfo uriInfo) {
	    	StatusWSO statusWSO = new StatusWSO();
	    	ClinicWSO clinicWSO=null;
	    	try
	    	{
	    		clinicWSO = clinicBL.get(Long.parseLong(id));
	    		logger.info("clinicWSO  ="+clinicWSO.toString());
	    	}
	    	catch(Exception Ex)
	    	{
	    		logger.info("expection="+Ex.getMessage());
	    		statusWSO.setCode(400);
	    		statusWSO.setMessage(Ex.getMessage());
	    		return sendResponse(statusWSO);
	    	}
	        return  sendResponse(clinicWSO);
	    }
	    
	   
	    
}
