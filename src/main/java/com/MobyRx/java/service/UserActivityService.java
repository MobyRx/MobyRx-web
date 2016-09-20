
package com.MobyRx.java.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.MobyRx.java.bl.ClinicBL;
import com.MobyRx.java.bl.CommonBL;
import com.MobyRx.java.bl.UserBL;
import com.MobyRx.java.service.*;
import com.MobyRx.java.service.wso.*;

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
@Path("/user")
@Transactional
public class UserActivityService extends BaseService{

    private Logger logger = LoggerFactory.getLogger(UserActivityService.class);

    private CommonBL commonBL;
    private UserBL userBL;

    @Autowired(required = true)
    public void setCommonBL(CommonBL commonBL) {
        this.commonBL = commonBL;
    }

    @Autowired(required = true)
    public void setUserBL(UserBL userBL) {
        this.userBL = userBL;
    }

    @POST
    @Path("/add")
    public Response addUser(UserWSO userWSO,@Context UriInfo uriInfo) {
    	logger.info("within add user");
    	StatusWSO statusWSO = new StatusWSO();
    	try
    	{
    		userBL.addUser(userWSO,statusWSO);
    	}
    	catch(Exception Ex)
    	{
    		logger.info(Ex.getMessage().toString());
    		statusWSO.setCode(400);
    		statusWSO.setMessage(Ex.getMessage()+ "statusWSO");
    		
    		return sendResponse(statusWSO);
    	}
    	
		return sendResponse(statusWSO);
    }
    
    @POST
    @Path("/modify")
    public Response modifyUser(UserWSO userWSO,@Context UriInfo uriInfo) {
    	logger.info("within add user");
    	StatusWSO statusWSO = new StatusWSO();
    	try
    	{
    		userBL.modifyUser(userWSO,statusWSO);
    	}
    	catch(Exception Ex)
    	{
    		logger.info(Ex.getMessage().toString());
    		statusWSO.setCode(400);
    		statusWSO.setMessage(Ex.getMessage()+ "statusWSO");
    		
    		return sendResponse(statusWSO);
    	}
    	
		return sendResponse(statusWSO);
    }
    
    @DELETE
    @Path("/delete")
    public Response modifyUser(@QueryParam("userId")String userId,@Context UriInfo uriInfo) {
    	logger.info("within add user");
    	StatusWSO statusWSO = new StatusWSO();
    	try
    	{
    		Long id=Long.parseLong(userId);
    		userBL.deleteUser(id,statusWSO);
    	}
    	catch(Exception Ex)
    	{
    		logger.info(Ex.getMessage().toString());
    		statusWSO.setCode(400);
    		statusWSO.setMessage(Ex.getMessage()+ "statusWSO");
    		
    		return sendResponse(statusWSO);
    	}
    	
		return sendResponse(statusWSO);
    }
    @GET
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
    @Path("/get/mobile/otp")
    public Response getMobileOTP(@QueryParam("userId")String userId,@Context UriInfo uriInfo) {
    	StatusWSO statusWSO = new StatusWSO();
    	try
    	{
    		userBL.generateMobileOTP(userId);
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
    @Path("/verify/mobile/otp")
    public Response verifyMobileOTP(@QueryParam("userId")String userId,@QueryParam("otp")String otp,@Context UriInfo uriInfo) {
    	StatusWSO statusWSO = new StatusWSO();
    	boolean result;
    	try
    	{
    		 result =userBL.verifyMobileOTP(userId,otp);
    	}
    	catch(Exception Ex)
    	{
    		statusWSO.setCode(400);
    		statusWSO.setMessage(Ex.getMessage());
    		return sendResponse(statusWSO);
    	}
    	if(result)
    	{
	    	statusWSO.setCode(200);
			statusWSO.setMessage("Sucessful");
			return sendResponse(statusWSO);
    	}
    	else
    	{
    		statusWSO.setCode(400);
    		statusWSO.setMessage("Failure");
    		return sendResponse(statusWSO);
    	}
    }
    
    @POST
    @Path("/Authenticate")
    public Response authenticate(@Context UriInfo uriInfo) {
        return sendResponse(new UserWSO());
    }
    
    
    @POST
    @Path("/UpdatePassword")
    public Response updatePassword(@Context UriInfo uriInfo) {
        return sendResponse(new UserWSO());
    }
}
