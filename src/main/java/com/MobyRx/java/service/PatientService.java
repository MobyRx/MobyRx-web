
package com.MobyRx.java.service;
import com.MobyRx.java.bl.CommonBL;
import com.MobyRx.java.bl.PatientBL;
import com.MobyRx.java.service.wso.PatientProfileWSO;
import com.MobyRx.java.service.wso.StatusWSO;
import com.MobyRx.java.service.wso.UserWSO;
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
@Path("/Patient")
@Transactional
public class PatientService extends BaseService{
	 private Logger logger = LoggerFactory.getLogger(PatientService.class);

	    private CommonBL commonBL;
	    
	    private PatientBL patientBL;

	    @Autowired(required = true)
	    public void setCommonBL(CommonBL commonBL) {
	        this.commonBL = commonBL;
	    }
	    

	    @Autowired(required = true)
	    public void setCommonBL(PatientBL patientBL) {
	        this.patientBL = patientBL;
	    }
	    
	    @POST
	    @Consumes({MediaType.APPLICATION_JSON})
	    @Produces({MediaType.APPLICATION_JSON})
	    @Path("/add")
	    public Response addPatient(PatientProfileWSO patientProfileWSO,@Context UriInfo uriInfo) throws Exception{
	    	StatusWSO statusWSO = new StatusWSO();
	    	patientBL.save(patientProfileWSO,statusWSO);
	    	
			return sendResponse(statusWSO);
	    }
}


