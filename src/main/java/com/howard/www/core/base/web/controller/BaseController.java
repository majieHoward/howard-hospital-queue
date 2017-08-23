package com.howard.www.core.base.web.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.ui.ModelMap;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.howard.www.core.base.web.IRequestController;
import com.howard.www.core.data.transfer.dto.IDataTransferObject;

/**
 * 
 * @ClassName: BaseController
 * @Description:TODO
 * @author: mayijie
 * @date: 2017年2月15日 下午9:16:14
 * 
 * @Copyright: 2017 https://github.com/majieHoward Inc. All rights reserved.
 */
public class BaseController implements IRequestController {
	private IDataTransferObject paramOfDto;

	private ConcurrentHashMap<String, Object> pageDisplayParameters = new ConcurrentHashMap<String, Object>();

	private List<String> keyOfUsingStringTemplates = new ArrayList<String>();

	

	

	public IDataTransferObject getParamOfDto() {
		return paramOfDto;
	}

	public void setParamOfDto(IDataTransferObject paramOfDto) {
		this.paramOfDto = paramOfDto;
	}

	public ConcurrentHashMap<String, Object> getPageDisplayParameters() {
		return pageDisplayParameters;
	}

	public void setPageDisplayParameters(ConcurrentHashMap<String, Object> pageDisplayParameters) {
		this.pageDisplayParameters = pageDisplayParameters;
	}

	public ConcurrentHashMap<String, Object> obtainPageDisplayParameters() {

		return this.getPageDisplayParameters();
	}

	public void evaluatePageDisplayParameter(String keyOfPageDisplayParameter, Object valueOfPageDisplayParameter) {
		pageDisplayParameters.put(keyOfPageDisplayParameter, valueOfPageDisplayParameter);

	}

	public void loadTemplateString(ModelMap model, boolean usingStringTemplates) {
		if (true == usingStringTemplates) {
			model.put("usingStringTemplates", "true");
		} else {
			model.put("usingStringTemplates", "false");
		}
	}

	public void structurePageTemplate(ModelMap model, String keyOfUsingStringTemplate,
			Object valueOfUsingStringTemplate) {
		keyOfUsingStringTemplates.add(keyOfUsingStringTemplate);
		model.put(keyOfUsingStringTemplate, valueOfUsingStringTemplate);
		if (model.get("keyOfUsingStringTemplates") == null) {
			model.put("keyOfUsingStringTemplates", this.keyOfUsingStringTemplates);
		}
		loadTemplateString(model, true);
	}

	@Override
	public void evaluateTemplateParameter(ModelMap model, String keyOfPageDisplayParameter,
			Object valueOfPageDisplayParameter) {
		model.put(keyOfPageDisplayParameter, valueOfPageDisplayParameter);
	}

}
