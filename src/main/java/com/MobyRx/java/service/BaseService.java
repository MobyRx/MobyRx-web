package com.MobyRx.java.service;

import javax.ws.rs.core.Response;

/**
 * Created by IntelliJ IDEA.
 * User: ashqures
 * Date: 9/5/16
 * Time: 9:16 PM
 * To change this template use File | Settings | File Templates.
 */
public class BaseService {



    protected Response sendResponse(Object object) {
        return Response.ok(object).build();
    }
}
