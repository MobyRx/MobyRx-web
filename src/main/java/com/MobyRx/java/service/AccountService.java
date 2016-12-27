package com.MobyRx.java.service;

import com.MobyRx.java.bl.AccountBL;
import com.MobyRx.java.bl.CommonBL;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.util.Map;

/**
 * Created by IntelliJ IDEA.
 * User: ashqures
 * Date: 12/20/16
 * Time: 9:35 PM
 * To change this template use File | Settings | File Templates.
 */
@Component
@Path("/account")
@Transactional
public class AccountService extends BaseService {

    private Logger logger = LoggerFactory.getLogger(PatientService.class);

    @Autowired
    private CommonBL commonBL;

    @Autowired
    private AccountBL accountBL;

    @GET
    @Path("/list")
    public Response search(@Context UriInfo uriInfo) {
        Map<String, String> queryParam = getQueryParamAsStringMap(uriInfo);
        return sendResponse(this.commonBL.getAccounts(queryParam));
    }

    @GET
    @Path("/{accountId}")
    public Response search(@PathParam("accountId")Long accountId) {
        return sendResponse(this.commonBL.getAccount(accountId));
    }

    @GET
    @Path("/{accountId}/doctor")
    public Response doctor(@PathParam("accountId")Long accountId, @Context UriInfo uriInfo) {
        Map<String, String> queryParam = getQueryParamAsStringMap(uriInfo);
        return sendResponse(this.accountBL.getAccountDoctor(accountId, queryParam));
    }

}
