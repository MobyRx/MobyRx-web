package com.MobyRx.java.util;

import com.MobyRx.java.bl.CommonBL;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * Created by IntelliJ IDEA.
 * User: ashqures
 * Date: 9/30/16
 * Time: 1:33 AM
 * To change this template use File | Settings | File Templates.
 */
@Component
public class MasterDBUtil {

    private Logger logger = LoggerFactory.getLogger(MasterDBUtil.class);

    @Autowired
    private CommonBL commonBL;

    @Value("${master.data.file}")
    private String MASTER_QUERY_FILE;

    @Value("${hibernate.hbm2ddl.auto}")
    private String HBM2_DDL = "";

    private boolean isProcessing = true;


    public String getMASTER_QUERY_FILE() {
        return MASTER_QUERY_FILE;
    }

    public void setMASTER_QUERY_FILE(String MASTER_QUERY_FILE) {
        this.MASTER_QUERY_FILE = MASTER_QUERY_FILE;
    }

    public String getHBM2_DDL() {
        return HBM2_DDL;
    }

    public void setHBM2_DDL(String HBM2_DDL) {
        this.HBM2_DDL = HBM2_DDL;
    }

    public CommonBL getCommonBL() {
        return commonBL;
    }

    public void setCommonBL(CommonBL commonBL) {
        this.commonBL = commonBL;
    }

    @PostConstruct
    public void execute() throws IOException {
        if (isProcessing) {
            return;
        }
        isProcessing = true;
        logger.info("hbm command:- " + getHBM2_DDL());
        if (null == getHBM2_DDL() || !"create".equalsIgnoreCase(getHBM2_DDL().trim()))
            return;
        StringBuilder sb = new StringBuilder();
        InputStream in = null;
        try {
            in = getClass().getClassLoader().getResourceAsStream(getMASTER_QUERY_FILE());
            BufferedReader br = new BufferedReader(new InputStreamReader(in));
            String s = "";
            while ((s = br.readLine()) != null) {
                sb.append(s);
            }
            br.close();
            String[] queries = sb.toString().split(";");
            for (String query : queries) {
                if (!query.trim().equals("")) {
                    commonBL.executeSQLQueryUpdate(query.trim());
                }
            }
        } catch (IOException e) {
            logger.error("Failed to execute master data\n" + e);
        } finally {
            if (null != in)
                in.close();
        }
    }

}
