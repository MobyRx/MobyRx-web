package com.MobyRx.java.service;

import com.MobyRx.java.bl.ClinicBL;
import com.MobyRx.java.bl.CommonBL;
import com.MobyRx.java.entity.DrugsEntity;
import com.MobyRx.java.service.wso.WSOToEntityConversion;
import com.MobyRx.java.service.wso.DrugWSO;
import com.MobyRx.java.service.wso.StatusWSO;
import com.MobyRx.java.service.wso.UserWSO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.constraints.NotNull;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import javax.xml.bind.annotation.XmlElement;
import java.util.List;


/**
 * Created by IntelliJ IDEA.
 * User: uday chandu G N
 * Date: 9/5/16
 * Time: 6:57 PM
 * To change this template use File | Settings | File Templates.
 */
@Component
@Path("/drug")
@Transactional
public class DrugService extends BaseService {
    private Logger logger = LoggerFactory.getLogger(DrugService.class);

    private CommonBL commonBL;
    
    private ClinicBL clinicBL;

    
    @Autowired(required = true)
    public void setCommonBL(ClinicBL clinicBL) {
        this.clinicBL = clinicBL;
    }

    @Autowired(required = true)
    public void setCommonBL(CommonBL commonBL) {
        this.commonBL = commonBL;
    }

    @Autowired
    private WSOToEntityConversion wSOToEntityConversion;


    @GET
    public Response getDrugs(@QueryParam("query")String query, @QueryParam("filter")String filterParams){
        List<DrugsEntity> drugs = commonBL.searchDrugs(query);
        return sendResponse(wSOToEntityConversion.transform(drugs));
    }
    
    @POST
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
    @Path("/drug/add")
    public Response addDrug(DrugWSO drugWSO, @Context UriInfo uriInfo) throws Exception{
    	StatusWSO statusWSO = new StatusWSO();
    	
    	clinicBL.save(drugWSO,statusWSO);
    	return  sendResponse(statusWSO);
    	}
    
    @DELETE
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
    @Path("/drug/delete")
    public Response deleteDrug(@QueryParam("drugId")String drugId, @Context UriInfo uriInfo) throws Exception{
    	StatusWSO statusWSO = new StatusWSO();
    	
    	clinicBL.delete(Long.parseLong(drugId),statusWSO);
    	return  sendResponse(statusWSO);
    	}


    

    /*@POST
    @Path("/addDrug")
    public Response addDrug(@Context UriInfo uriInfo) {
        return sendResponse(new UserWSO());
    }

    @POST
    @Path("/AddUnRegisteredPatient")
    public Response addUnRegisteredPatient(@Context UriInfo uriInfo) {
        return sendResponse(new UserWSO());
    }*/


}
