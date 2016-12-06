package com.MobyRx.java.entity.converter;

import com.MobyRx.java.entity.common.EmergencyContact;
import org.apache.commons.lang.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: ashqures
 * Date: 9/8/16
 * Time: 1:20 PM
 * To change this template use File | Settings | File Templates.
 */
public class EmergencyContactConverter{

    public static String convertToDatabaseColumn(List<EmergencyContact> arrayList) {
        return arrayList == null ? "" : StringUtils.join(arrayList, ",");
    }

    public static List<EmergencyContact> convertToEntityAttribute(String dbData) {
        ArrayList<EmergencyContact> data = null;
        if (StringUtils.isNotEmpty(dbData)){
            data =  new ArrayList<EmergencyContact>();
            String [] contacts = dbData.split(",");
            for(String contact : contacts){
                EmergencyContact emergencyContact = new EmergencyContact();
                String[] cnt = contact.split("-");
                if(null!= cnt && cnt.length>0){
                    emergencyContact.setName(cnt[0]);
                    if(cnt.length>1)
                        emergencyContact.setMobile(cnt[1]);
                    data.add(emergencyContact);
                }
            }
        }
        return data;
    }
}
