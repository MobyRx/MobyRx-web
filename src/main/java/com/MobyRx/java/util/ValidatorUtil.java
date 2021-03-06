package com.MobyRx.java.util;

import com.MobyRx.java.service.wso.*;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by IntelliJ IDEA.
 * User: ashif
 * Date: 9/27/16
 * Time: 12:12 AM
 * To change this template use File | Settings | File Templates.
 */
@Component
public class ValidatorUtil {

    private static Logger logger = LoggerFactory.getLogger(ValidatorUtil.class);

    private final static String PATTERN_PASSWORD = "^(?=.*\\d)(?=.*[A-Za-z])[A-Za-z0-9]{6,18}$";
    private final static String PATTERN_EMAIL = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
    private final static String PATTERN_MOBILE = "^((\\+[1-9]?[0-9])|0)?[7-9][0-9]{9}$";
    public static final String DATE_FORMAT = "dd/MM/yyyy";


    public static void validate(DrugWSO drugWSO, StatusWSO status){
         if(StringUtils.isEmpty(drugWSO.getName()))
             status.addError("Drug Name is required");
        if(StringUtils.isEmpty(drugWSO.getBrandName()))
            status.addError("Brand name is required");
        if(StringUtils.isEmpty(drugWSO.getDrugType()))
            status.addError("Drub type is required");
        if(StringUtils.isEmpty(drugWSO.getPackageUnit()))
            status.addError("Package Unit is required");
        if(StringUtils.isEmpty(drugWSO.getManufacturer()))
            status.addError("Manufacturer is required");
    }


    
    public static void validate(UserWSO userWSO, StatusWSO status) {
        if(StringUtils.isEmpty(userWSO.getEmail())){
        	status.setCode(400);
            status.addError("Email required");
        }else if(!isValidEmail(userWSO.getEmail())){
        	status.setCode(400);
            status.addError("Invalid Email Address");
        }
        else if(StringUtils.isEmpty(userWSO.getMobile()) ){
        	status.setCode(400);
            status.addError("PhoneNumber Required");
        }
        else if(!isValidMobile(userWSO.getMobile())){
        	status.setCode(400);
            status.addError("Invalid PhoneNumber");
        }
        else if(StringUtils.isEmpty(userWSO.getPassword()) ){
        	status.setCode(400);
            status.addError("Password Required");
        }
        else if(!isValidPassword(userWSO.getPassword())){
        	status.setCode(400);
            status.addError("Invalid Password");
        }
    }
    
    public void validate(ClinicWSO clinicWSO, StatusWSO status) {
        if(StringUtils.isEmpty(clinicWSO.getEmail())){
        	status.setCode(400);
            status.addError("Email required");
        }else if(!isValidEmail(clinicWSO.getEmail())){
        	status.setCode(400);
            status.addError("Invalid Email Address");
        }
        else if(StringUtils.isEmpty(clinicWSO.getPhoneNumber()) ){
        	status.setCode(400);
            status.addError("PhoneNumber Required");
        }
        else if(!isValidMobile(clinicWSO.getPhoneNumber())){
        	status.setCode(400);
            status.addError("Invalid PhoneNumber");
        }
    }

    public static void validate(AppointmentWSO appointmentWSO, StatusWSO status){
        if(null == appointmentWSO.getAppointmentOn()){
            status.addError("Appointment On Required");
        }
        if(StringUtils.isEmpty(appointmentWSO.getTime()))
            status.addError("Appointment time Required");
        if(null==appointmentWSO.getDoctor())
            status.addError("Doctor id Required");

        if(null==appointmentWSO.getClinic())
            status.addError("Clinic id Required");
    }
   
    public static boolean isValidEmail(String email) {
        return email.matches(PATTERN_EMAIL);
    }


    public static boolean isValidPassword(String password) {
        return password.matches(PATTERN_PASSWORD);
    }

    public static boolean isValidMobile(String mobile) {
        return mobile.matches(PATTERN_MOBILE);
    }


    public static boolean isNullOrEmpty(String text) {
        return null == text || "".equals(text);
    }

    public static boolean isValidDate(String dateToValidate) {
        if (dateToValidate == null) {
            return false;
        }
        SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT);
        sdf.setLenient(false);
        try {
            Date date = sdf.parse(dateToValidate);
            logger.info("Date:- "+ date);
        } catch (ParseException e) {
            return false;
        }
        return true;
    }
}
