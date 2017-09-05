package com.howard.www.hospital.queue.http.converter.json;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.HttpOutputMessage;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.howard.www.core.base.util.FrameworkStringUtils;
import com.howard.www.hospital.queue.operation.domain.BackInteractivenfoEntity;

import net.sf.json.JSONObject;

public class HospitalQueueMappingJackson2HttpMessageConverter
		extends org.springframework.http.converter.json.MappingJackson2HttpMessageConverter {
	protected final Logger log = LoggerFactory.getLogger(HospitalQueueMappingJackson2HttpMessageConverter.class);

	@Override
	protected Object readInternal(Class<? extends Object> clazz, HttpInputMessage inputMessage)
			throws IOException, HttpMessageNotReadableException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected void writeInternal(Object t, HttpOutputMessage outputMessage)
			throws IOException, HttpMessageNotWritableException {
		// TODO Auto-generated method stub
		ObjectMapper mapper = new ObjectMapper();
		BackInteractivenfoEntity backInteractivenfoEntity = new BackInteractivenfoEntity();
		backInteractivenfoEntity.setInteractiveData(mapper.writeValueAsString(t));
		outputMessage.getBody()
				.write(FrameworkStringUtils.asString(JSONObject.fromObject(backInteractivenfoEntity)).getBytes());
	}

}
