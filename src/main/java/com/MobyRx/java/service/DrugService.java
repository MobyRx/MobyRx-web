package com.MobyRx.java.service;

import com.MobyRx.java.bl.CommonBL;
import com.MobyRx.java.entity.DrugsEntity;
import com.MobyRx.java.service.wso.DataMapper;
import com.MobyRx.java.service.wso.DrugWSO;
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

    @Autowired(required = true)
    public void setCommonBL(CommonBL commonBL) {
        this.commonBL = commonBL;
    }

    @Autowired
    private DataMapper dataMapper;


    @GET
    public Response getDrugs(@QueryParam("query")String query, @QueryParam("filter")String filterParams){
        List<DrugsEntity> drugs = commonBL.searchDrugs(query);
        return sendResponse(dataMapper.transform(drugs));
    }


    @POST
    @Consumes(value = MediaType.APPLICATION_FORM_URLENCODED)
    public Response saveDrug(@FormParam("abcd")String abcd){

        return null;
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
