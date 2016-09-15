package com.MobyRx.java.service;
import com.MobyRx.java.bl.CommonBL;
import com.MobyRx.java.bl.impl.ClinicBLImpl;
import com.MobyRx.java.dao.impl.ClinicDaoImpl;
import com.MobyRx.java.service.wso.UserWSO;
import com.MobyRx.java.service.wso.ClinicWSO;
import com.MobyRx.java.service.wso.StatusWSO;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.ws.rs.Consumes;
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
@Path("/Clinic")
@Transactional
public class ClinicService extends BaseService{
	 private Logger logger = LoggerFactory.getLogger(ClinicService.class);

	    private CommonBL commonBL;

	    @Autowired(required = true)
	    public void setCommonBL(CommonBL commonBL) {
	        this.commonBL = commonBL;
	    }
	    /*
	     {
  "name": "Asha",
  "address": {
    "id": 1,
    "buildingNumber": "1",
    "street": "guthalu",
    "landmark": "harlimara",
    "city": "mandya",
    "state": "karnata",
    "country": null,
    "zipCode": "5714301",
    "latitude": 1098,
    "longitude": 3647
  },
  "phoneNumber": "12345",
  "email": "uday@gmail.com",
  "licenceNumber": "1234",
  "registrationDate": 689797800000,
  "url": "www.something.com",
  "category": "general",
  "services": [
    {
      "id": 2,
      "name": "yyy",
      "description": "xxx"
    },
    {
      "id": 3,
      "name": "yyy1",
      "description": "xxx1"
    }
  ],
  "verified": false
}
	     */
	    @POST
	    @Consumes({MediaType.APPLICATION_JSON})
	    @Produces({MediaType.APPLICATION_JSON})
	    @Path("/add")
	    public Response addClinicAddress(ClinicWSO clinicWSO, @Context UriInfo uriInfo) {
	        return sendResponse(commonBL.save(clinicWSO));
	    }
	    
	    @POST
	    @Path("/UpateClinicAddress")
	    public Response upateClinicAddress(@Context UriInfo uriInfo) {
	        return sendResponse(new UserWSO());
	    }
	    
	    @GET
	    @Path("/GetClinicAddress")
	    public Response getClinicAddress(@Context UriInfo uriInfo) {
	        return sendResponse(new UserWSO());
	    }
	    @POST
	    @Path("/RemoveClinicAddress")
	    public Response removeClinicAddress(@Context UriInfo uriInfo) {
	        return sendResponse(new UserWSO());
	    }
}
