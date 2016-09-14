package com.MobyRx.java.service.provider;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

/**
 * Created with IntelliJ IDEA.
 * UserEntity: Ashif.Qureshi
 * Date: 9/4/16
 * Time: 7:29 PM
 * To change this template use File | Settings | File Templates.
 */
@Provider
public class RestExceptionMapper implements ExceptionMapper<Throwable> {
    private Logger logger = LoggerFactory.getLogger(RestExceptionMapper.class);

    public Response toResponse(Throwable re) {

        return Response.status(HttpStatus.OK.value()).build();
    }
    
    
}
