package com.MobyRx.java.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;

/**
 * Created by IntelliJ IDEA.
 * User: ashqures
 * Date: 10/11/16
 * Time: 7:05 PM
 * To change this template use File | Settings | File Templates.
 */
public class MobyRxException extends RuntimeException {

    private Logger logger = LoggerFactory.getLogger(MobyRxException.class);

    private int code;
    private String message;

    public MobyRxException() {
        super();
        logger.error("MobyRxException occurred");
    }

    public MobyRxException(Exception e) {
        super(e);
        logger.error("MobyRxException occurred"+ e.getMessage());
    }


    public MobyRxException(int code) {
        super();
        this.code = code;
        this.message = HttpStatus.valueOf(code).toString();
        logger.error("MobyRxException occurred code:- "+ code);
    }

    public MobyRxException(String message) {
        super(message);
        this.code=HttpStatus.INTERNAL_SERVER_ERROR.value();
        logger.error("MobyRxException occured "+message);
    }

    public MobyRxException(int code, String message) {
        super(message);
        this.code = code;
        logger.error("MobyRxException occurred code "+code+" | Message "+message);
    }

    public MobyRxException(int code, String message, Throwable cause) {
        super(message, cause);
        this.code= code;
        logger.error("MobyRxException occurred "+message);
        cause.printStackTrace();
    }

    public int getCode() {
        return code;
    }
}