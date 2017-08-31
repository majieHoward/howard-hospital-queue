package com.howard.www.hospital.queue.operation.domain;

import java.io.Serializable;

import com.howard.www.core.base.util.FrameworkStringUtils;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import net.sf.json.JSONObject;

@Data
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class BackInteractivenfoEntity implements Serializable{
    /**   
	 * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)   
	 */  
	private static final long serialVersionUID = 1L;
	private String isSuccess="";
    private String interactiveMessage="";
    private String returnErrorEncoding="";
    private String requestAddress="";
    private Object interactiveData;
    
	public String getIsSuccess() {
		if(FrameworkStringUtils.isEmpty(isSuccess)){
			this.isSuccess="success";
		}
		return isSuccess;
	}
	
	public Object getInteractiveData() {
		if(interactiveData==null){
			this.interactiveData=new JSONObject();
		}
		return interactiveData;
	}
	
}
