package com.MobyRx.java.service;

import com.MobyRx.java.service.wso.StatusWSO;

import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * Created by IntelliJ IDEA.
 * User: ashqures
 * Date: 9/5/16
 * Time: 9:16 PM
 * To change this template use File | Settings | File Templates.
 */

@Produces({MediaType.APPLICATION_JSON})
public class BaseService {

    protected Response sendResponse(Object object) {
        return Response.ok(object).build();
    }


    protected Response sendResponse(StatusWSO statusWSO) {
        return Response.status(statusWSO.getCode()).entity(statusWSO).build();
    }
}
