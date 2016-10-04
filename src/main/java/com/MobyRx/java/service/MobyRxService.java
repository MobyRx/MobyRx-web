package com.MobyRx.java.service;

import com.MobyRx.java.bl.CommonBL;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: ashqures
 * Date: 10/4/16
 * Time: 11:07 AM
 * To change this template use File | Settings | File Templates.
 */
@Component
@Path("/api")
@Transactional
public class MobyRxService extends BaseService{


    private Logger logger = LoggerFactory.getLogger(MobyRxService.class);


    @Autowired
    private CommonBL commonBL;
    
    
    @GET
    @Path("/{className}")
    public Response prescription(@PathParam("className") String className){
        List masterDataList = commonBL.getMasterData(className);
        return sendResponse(masterDataList);
    }
}
