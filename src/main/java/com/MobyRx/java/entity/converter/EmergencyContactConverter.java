package com.MobyRx.java.entity.converter;

import com.MobyRx.java.entity.EmergencyContact;
import org.apache.commons.lang.StringUtils;

import javax.persistence.AttributeConverter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: ashqures
 * Date: 9/8/16
 * Time: 1:20 PM
 * To change this template use File | Settings | File Templates.
 */
public class EmergencyContactConverter implements AttributeConverter<ArrayList,String> {

    public String convertToDatabaseColumn(ArrayList arrayList) {
        return arrayList == null ? null : StringUtils.join(arrayList, ",");
    }

    public ArrayList<EmergencyContact> convertToEntityAttribute(String dbData) {
        ArrayList<EmergencyContact> data = null;
        if (!StringUtils.isBlank(dbData)){
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
