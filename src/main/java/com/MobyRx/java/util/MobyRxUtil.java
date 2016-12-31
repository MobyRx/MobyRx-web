package com.MobyRx.java.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Date;
import java.util.Properties;

import org.apache.commons.codec.binary.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class MobyRxUtil {
	private static Logger logger = LoggerFactory.getLogger(MobyRxUtil.class);


	public static String convertStringToImageByteArray(String 
			imageString,String patientId){

		OutputStream outputStream = null;

		String basePath=CommonUtil.getProperty("prescription.file.upload.path");
		String file="";
		if(new File(basePath).exists())
		{
			file= basePath+"/"+new Date().getTime()/1000L+"_"+patientId+".jpg";
		}
		else
		{
			file=System.getProperty("catalina.base")+"/"+new Date().getTime()/1000L+"_"+patientId+".jpg";
		}
		byte [] imageInByteArray = Base64.decodeBase64(
				imageString);
		try {
			outputStream = new FileOutputStream(file);
			outputStream.write(imageInByteArray);
			return file;
		} catch (FileNotFoundException e) {
			logger.error("Error in convertStringToImageByteArray ", e.getMessage());
			return null;
		} catch (IOException e) {
			logger.error("Error in convertStringToImageByteArray ", e.getMessage());
			return null;
		}finally{
			try {
				if(outputStream!=null)
				{
					outputStream.close();
				}
				else
					return null;
			} catch (IOException e) {
				logger.error("Error in convertStringToImageByteArray ", e.getMessage());
				return null;
			}
		}

	}
}


