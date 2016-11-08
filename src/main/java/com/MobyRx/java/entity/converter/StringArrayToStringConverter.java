package com.MobyRx.java.entity.converter;

import org.apache.commons.lang.StringUtils;

import javax.persistence.AttributeConverter;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by IntelliJ IDEA.
 * User: ashqures
 * Date: 8/26/16
 * Time: 1:38 PM
 * To change this template use File | Settings | File Templates.
 */
public class StringArrayToStringConverter implements AttributeConverter<ArrayList,String> {

    public String convertToDatabaseColumn(ArrayList arrayList) {
        return arrayList == null ? "" : StringUtils.join(arrayList, ",");
    }

    public ArrayList<String> convertToEntityAttribute(String dbData) {
        ArrayList<String> data = null;
        if (!StringUtils.isBlank(dbData)){
            data =  new ArrayList<String>(Arrays.asList(dbData.split(",")));
        }
       return data;
    }
}
