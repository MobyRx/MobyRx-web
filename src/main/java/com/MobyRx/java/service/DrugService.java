package com.MobyRx.java.service;

import com.MobyRx.java.bl.ClinicBL;
import com.MobyRx.java.bl.CommonBL;
import com.MobyRx.java.bl.DrugBL;
import com.MobyRx.java.entity.DrugsEntity;
import com.MobyRx.java.service.wso.WSOToEntityConversion;
import com.MobyRx.java.service.wso.DataMapper;
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

    private DrugBL drugBL;


    @Autowired(required = true)
    public void setDrugBL(DrugBL drugBL) {
        this.drugBL = drugBL;
    }

    @Autowired
    private WSOToEntityConversion wSOToEntityConversion;


    @GET
    public Response getDrugs(@QueryParam("query") String query, @QueryParam("filter") String filterParams) throws Exception {
        List<DrugsEntity> drugs = drugBL.searchDrugs(query);
        return sendResponse(DataMapper.transformDrugs(drugs));
    }



    @POST
    public Response addDrug(DrugWSO drugWSO, @Context UriInfo uriInfo) throws Exception {
        StatusWSO statusWSO = new StatusWSO();
        drugBL.save(drugWSO, statusWSO);
        return sendResponse(statusWSO);
    }

    @DELETE
    public Response deleteDrug(@QueryParam("drugId") String drugId) throws Exception {
        StatusWSO statusWSO = new StatusWSO();
        drugBL.delete(Long.parseLong(drugId), statusWSO);
        return sendResponse(statusWSO);
    }

  /*  @GET
    @Path("/drug")
    public Response getDrug(@QueryParam("id") Long id) throws Exception {
        DrugsEntity drug = drugBL.get(id);
        return sendResponse(wSOToEntityConversion.transform(drug));
    }*/
}
