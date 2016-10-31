package com.MobyRx.java.service;

import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import com.MobyRx.java.entity.DoctorProfileEntity;
import com.MobyRx.java.service.wso.DataMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.MobyRx.java.bl.CommonBL;
import com.MobyRx.java.bl.DoctorBL;
import com.MobyRx.java.service.wso.DoctorProfileWSO;
import com.MobyRx.java.service.wso.StatusWSO;

import java.util.List;
import java.util.Map;

@Component
@Path("/doctor")
@Transactional
public class DoctorService extends BaseService {

    private Logger logger = LoggerFactory.getLogger(DoctorService.class);

    private CommonBL commonBL;

    private DoctorBL doctorBL;

    @Autowired(required = true)
    public void setCommonBL(CommonBL commonBL) {
        this.commonBL = commonBL;
    }

    @Autowired(required = true)
    public void setCommonBL(DoctorBL doctorBL) {
        this.doctorBL = doctorBL;
    }

    @POST
    public Response addDoctor(DoctorProfileWSO doctorProfileWSO, @Context UriInfo uriInfo) throws Exception {
        logger.info("within add doctor");
        StatusWSO statusWSO = new StatusWSO();
        doctorBL.save(doctorProfileWSO, statusWSO);
        return sendResponse(statusWSO);
    }

    @GET
    public Response doctors(@Context UriInfo uriInfo) throws Exception{
        Map<String, String> queryParam = getQueryParamAsStringMap(uriInfo);
        List<DoctorProfileEntity> doctors= doctorBL.searchDoctor(queryParam);
        return sendResponse(DataMapper.transformDoctors(doctors));
    }

    @GET
    @Path("/{doctorId}")
    public Response doctor(@PathParam("doctorId")Long id, @Context UriInfo uriInfo) throws Exception{
        DoctorProfileEntity doctorProfile = doctorBL.get(id);
        return sendResponse(DataMapper.transform(doctorProfile));
    }

}
