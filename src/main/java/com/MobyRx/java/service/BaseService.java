package com.MobyRx.java.service;

import com.MobyRx.java.service.wso.StatusWSO;

import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by IntelliJ IDEA.
 * User: ashqures
 * Date: 9/5/16
 * Time: 9:16 PM
 * To change this template use File | Settings | File Templates.
 */

@Consumes({MediaType.APPLICATION_JSON})
@Produces({MediaType.APPLICATION_JSON})
public class BaseService {

    protected Response sendResponse(Object object) {
        return Response.ok(object).build();
    }


    protected Response sendResponse(StatusWSO statusWSO) {
        return Response.status(statusWSO.getCode()).entity(statusWSO).build();
    }

    protected Map<String,String> getQueryParamAsStringMap(UriInfo uriInfo){
        MultivaluedMap<String, String> requestData =  uriInfo.getQueryParameters();
        Map<String,String> queryParamMap = new HashMap<String, String>();
        for(String key :  requestData.keySet()){
            queryParamMap.put(key,requestData.getFirst(key));
        }
        return queryParamMap;
    }
}
