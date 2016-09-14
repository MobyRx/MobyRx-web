
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
@Path("/MobyRx/User")
@Transactional
public class UserActivityService extends BaseService{

    private Logger logger = LoggerFactory.getLogger(UserActivityService.class);

    private CommonBL commonBL;

    @Autowired(required = true)
    public void setCommonBL(CommonBL commonBL) {
        this.commonBL = commonBL;
    }

   

    @POST
    @Path("/Register")
    public Response patient(@Context UriInfo uriInfo) {
        return sendResponse(new UserWSO());
    }
    
    @POST
    @Path("/VerifyMobileOTP")
    public Response verifyMobileOTP(@Context UriInfo uriInfo) {
        return sendResponse(new UserWSO());
    }
    
    @POST
    @Path("/Authenticate")
    public Response authenticate(@Context UriInfo uriInfo) {
        return sendResponse(new UserWSO());
    }
    @POST
    @Path("/ForgetPasswordOTP")
    public Response forgetPasswordOTP(@Context UriInfo uriInfo) {
        return sendResponse(new UserWSO());
    }
    
    @POST
    @Path("/ValidateForgetPassOTP")
    public Response validateForgetPassOTP(@Context UriInfo uriInfo) {
        return sendResponse(new UserWSO());
    }
    
    @POST
    @Path("/UpdatePassword")
    public Response updatePassword(@Context UriInfo uriInfo) {
        return sendResponse(new UserWSO());
    }
}
