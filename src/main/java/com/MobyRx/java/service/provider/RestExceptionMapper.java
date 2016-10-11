package com.MobyRx.java.service.provider;

import com.MobyRx.java.exception.MobyRxException;
import com.MobyRx.java.service.wso.StatusWSO;
import org.glassfish.jersey.server.ParamException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;

import javax.security.auth.login.CredentialNotFoundException;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;
import java.nio.file.AccessDeniedException;

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
        StatusWSO status = new StatusWSO();
        if(re instanceof NotFoundException || re instanceof ClassNotFoundException ){
            status.setCode(HttpStatus.NOT_FOUND.value());
            status.setMessage("No Record Found for your request");
        }else if(re instanceof ParamException){
            status.setCode(HttpStatus.BAD_REQUEST.value());
            status.setMessage("Request parameter type mismatch");
        } else if(re instanceof WebApplicationException){
            status.setCode(HttpStatus.METHOD_NOT_ALLOWED.value());
            status.setMessage(HttpStatus.METHOD_NOT_ALLOWED.getReasonPhrase());
        }else if(re instanceof MobyRxException){
            MobyRxException mobyRxException = (MobyRxException)re;
            status.setCode(mobyRxException.getCode());
            status.setMessage(re.getMessage());
        }else if (re instanceof AccessDeniedException) {
            status.setCode(HttpStatus.FORBIDDEN.value());
            status.setMessage("");
        }else if (re instanceof CredentialNotFoundException) {
            status.setCode(HttpStatus.NON_AUTHORITATIVE_INFORMATION.value());
            status.setMessage("token must be priovided");
        } else {
            status.setCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
            status.setMessage("Something unexpected happened. Wait for some time or check admin");
            logger.error("Unhandled exception:- "+re.getMessage());
            re.printStackTrace();
        }
        return Response.status(status.getCode()).entity(status).build();
    }
    
    
}
