package com.MobyRx.java.service.provider;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
/**
 * Created with IntelliJ IDEA.
 * User: Ashif.Qureshi
 * Date: 9/4/16
 * Time: 5:27 PM
 * To change this template use File | Settings | File Templates.
 */
public class RestSecurityInterceptor implements ContainerRequestFilter {

    private Logger logger = LoggerFactory.getLogger(RestSecurityInterceptor.class);


    public void filter(ContainerRequestContext containerRequest) {
    }
}
