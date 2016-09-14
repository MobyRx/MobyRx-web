package com.MobyRx.java.service.wso;

import com.MobyRx.java.entity.DrugsEntity;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: ashqures
 * Date: 9/14/16
 * Time: 11:35 AM
 * To change this template use File | Settings | File Templates.
 */
@Component
public class DataMapper {


    public DrugWSO transform(DrugsEntity drugsEntity){
        DrugWSO drug = new DrugWSO();
        drug.setId(drugsEntity.getId());
        return drug;
    }

    public List<DrugWSO> transform(List<DrugsEntity> drugsEntities){
        List<DrugWSO> drugs = new ArrayList<DrugWSO>();
        for(DrugsEntity drugsEntity : drugsEntities)
            drugs.add(transform(drugsEntity));
        return drugs;
    }
}
