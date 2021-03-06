package com.howard.www.hospital.queue.operation.service.impl;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Repository;

import com.howard.www.core.base.util.FrameworkStringUtils;
import com.howard.www.core.data.transfer.dto.IDataTransferObject;
import com.howard.www.hospital.queue.operation.service.IExternalProvidedHisService;
import com.howard.www.hospital.queue.operation.service.IOperationConsultingRoomSerivce;
import com.howard.www.hospital.queue.operation.service.IOperationDoctorAttributeService;
import com.howard.www.hospital.queue.operation.service.IOperationSubscribeMessageService;
import com.howard.www.hospital.queue.operation.websocket.domain.ArrangeDoctorsMessageOfResponseEntity;
import com.howard.www.hospital.queue.operation.websocket.domain.CallAPatientMessageOfResponseEntity;

import net.sf.json.JSONObject;
/**
 * 
 * @ClassName:  ExternalProvidedHisServiceImpl   
 * @Description:TODO   
 * @author: mayijie
 * @date:   2017年9月22日 下午3:51:58   
 *     
 * @Copyright: 2017 https://github.com/majieHoward Inc. All rights reserved.
 */
@Repository("externalProvidedHisService")
public class ExternalProvidedHisServiceImpl implements IExternalProvidedHisService {
	
	private static final Logger logger = LoggerFactory.getLogger(ExternalProvidedHisServiceImpl.class);
	@Autowired
	private ApplicationContext cApplicationContext;

	public IOperationSubscribeMessageService obtainIOperationSubscribeMessageService()throws Exception{
		return (IOperationSubscribeMessageService) cApplicationContext.getBean("operationSubscribeMessageService");
	}
	
	/**
	 * 
	 * @Title: callAPatientParameterVerification   
	 * @Description: TODO 为callAPatientToSeeADoctor(IDataTransferObject paramDto)方法验证参数
	 * @param: @param paramDto
	 * @param: @throws Exception      
	 * @return: void      
	 * @throws
	 */
	private void callAPatientParameterVerification(IDataTransferObject paramDto) throws Exception{
		//呼叫内容
		String callContents=FrameworkStringUtils
				.asString(paramDto.obtainMapOfRequiredParameter().get("callContents"));
		if("".equals(callContents)){
			throw new RuntimeException("40028");
			//throw new RuntimeException("缺少呼叫内容(callContents)");
		}
		//通用验证
		universalVerification(paramDto);
		//判断患者
		String patientCode=FrameworkStringUtils
				.asString(paramDto.obtainMapOfRequiredParameter().get("patientCode"));
		if("".equals(patientCode)){
			throw new RuntimeException("40026");
			//throw new RuntimeException("缺少患者编号(patientCode)");
		}
		//判断患者名
		String patientName=FrameworkStringUtils
				.asString(paramDto.obtainMapOfRequiredParameter().get("patientName"));
		if("".equals(patientName)){
			throw new RuntimeException("40027");
			//throw new RuntimeException("缺少患者名(patientName)");
		}
		/**
		 * Verification relation 验证医生患者以及患者和患者编码之间对应关系正确
		 */
	}

	/**
	 * 
	 * <p>Title: callAPatientToSeeADoctor</p>   
	 * <p>Description: </p>   
	 * @param paramDto
	 * @return
	 * @throws Exception   
	 * @see com.howard.www.hospital.queue.operation.service.IExternalProvidedHisService#callAPatientToSeeADoctor(com.howard.www.core.data.transfer.dto.IDataTransferObject)
	 */
	@Override
	public JSONObject callAPatientToSeeADoctor(IDataTransferObject paramDto) throws Exception {
		// TODO Auto-generated method stub
		callAPatientParameterVerification(paramDto);
		CallAPatientMessageOfResponseEntity messageBody=new CallAPatientMessageOfResponseEntity(paramDto.obtainMapOfRequiredParameter());
		/**
		 * 
		 */
		messageBody.setContent(FrameworkStringUtils
				.asString(paramDto.obtainMapOfRequiredParameter().get("callContents")));
		messageBody.setFromUserName(FrameworkStringUtils.asString(paramDto.obtainMapOfRequiredParameter().get("roomCode")));
		logger.info(FrameworkStringUtils.asString(JSONObject.fromObject(messageBody)));
		/**
		 * {
		 *******"fromUserName": "HospitalQueueSystem",
		 *******"msgType": "text",
		 *******"createTime": "1504025065860",dd
		 *******"toUserName": "",
		 *******"content": "请彼得到发热门诊诊断室（1楼）就诊史蒂芬请做好准备"
		 * }
		 */
		obtainIOperationSubscribeMessageService().sendToCorrespondingScreenIpInConsulting("/subscribe", FrameworkStringUtils.asString(paramDto.obtainMapOfRequiredParameter().get("roomCode")), messageBody);
		return null;
	}

	/**
	 * 
	 * @Title: registerDoctorParameterVerification   
	 * @Description: TODO 为registerDoctorOnlineStatus(IDataTransferObject paramDto)方法验证参数
	 * @param: @param paramDto
	 * @param: @throws Exception      
	 * @return: void      
	 * @throws
	 */
	private void registerDoctorParameterVerification(IDataTransferObject paramDto) throws Exception{
		//通用验证
		universalVerification(paramDto);
		//判断诊断室编码是否存在
		String signInStatus = FrameworkStringUtils
				.asString(paramDto.obtainMapOfRequiredParameter().get("signInStatus"));
		if("".equals(signInStatus)){
			throw new RuntimeException("40006");
			//throw new RuntimeException("缺少签到标识(signInStatus)");
		}
		if (!"20A".equals(signInStatus) && !"20X".equals(signInStatus)) {
			throw new RuntimeException("40007");
			//throw new RuntimeException("无效的签到标识(signInStatus),检查signInStatus的有效性10A表示医生签到,10X表示医生签退");
		}
		//
		
		//判断诊断室对应的sreenDevice是否为在线状态
	}
	
	/**
	 * 
	 * <p>Title: registerDoctorOnlineStatus</p>   
	 * <p>Description: </p>   
	 * @param paramDto
	 * @return
	 * @throws Exception   
	 * @see com.howard.www.hospital.queue.operation.service.IExternalProvidedHisService#registerDoctorOnlineStatus(com.howard.www.core.data.transfer.dto.IDataTransferObject)
	 */
	@Override
	public JSONObject registerDoctorOnlineStatus(IDataTransferObject paramDto) throws Exception {
		// TODO Auto-generated method stub
		registerDoctorParameterVerification(paramDto);
		ArrangeDoctorsMessageOfResponseEntity messageBody=new ArrangeDoctorsMessageOfResponseEntity(
				paramDto.obtainMapOfRequiredParameter());
		//根据医生的编号获取医生的姓名
		messageBody.setDoctorName(obtainIOperationDoctorAttributeService().obtainDoctorNameByDoctorJobNumberFromMap(
				FrameworkStringUtils.asString(paramDto.obtainMapOfRequiredParameter().get("doctorJobNumber"))));
		obtainIOperationSubscribeMessageService().sendToCorrespondingScreenIpInConsulting("/subscribe", 
				FrameworkStringUtils.asString(paramDto.obtainMapOfRequiredParameter().get("roomCode")), 
				messageBody);		
		return null;
	}

	/**
	 * 
	 * @Title: universalVerification   
	 * @Description: TODO  判断doctorJobNumber和roomCode是否存在和有效
	 * @param: @param paramDto
	 * @param: @throws Exception      
	 * @return: void      
	 * @throws
	 */
	private void universalVerification(IDataTransferObject paramDto)throws Exception{
		/**
		 * 判断接口传参是否满足要求
		 */
		if (paramDto == null) {
			throw new RuntimeException("40001");
			//throw new RuntimeException("接口调用的传入的参数为空");
		}
		String doctorJobNumber = FrameworkStringUtils
				.asString(paramDto.obtainMapOfRequiredParameter().get("doctorJobNumber"));
		if ("".equals(doctorJobNumber)) {
			throw new RuntimeException("40002");
			//throw new RuntimeException("缺少医生工号(doctorJobNumber)");
		}
		judgmentDoctorJobNumberExistence(doctorJobNumber);
		//判断医生工号是否存在
		String roomCode = FrameworkStringUtils.asString(paramDto.obtainMapOfRequiredParameter().get("roomCode"));
		if ("".equals(roomCode)) {
			throw new RuntimeException("40004");
			//throw new RuntimeException("缺少诊断室编码(roomCode)");
		}
		judgmentRoomCodeExistence(roomCode);
		
	}
	
	/**
	 * 
	 * @Title: judgmentDoctorJobNumberExistence   
	 * @Description: TODO 验证是否存在doctorJobNumber为传入doctorJobNumber的DoctorAttributeEntity对象 
	 * @param: @param doctorJobNumber
	 * @param: @throws Exception      
	 * @return: void      
	 * @throws
	 */
	private void judgmentDoctorJobNumberExistence(String doctorJobNumber) throws Exception{
		if(false==obtainIOperationDoctorAttributeService().existDoctorAttributeEntityByDoctorJobNumber(doctorJobNumber)){
			throw new RuntimeException("40003");
			//throw new RuntimeException("无效的医生工号(doctorJobNumber),请开发者检查doctorJobNumber的正确性，避免异常字符，注意大小写");
		}
	}
	
	/**
	 * 
	 * @Title: judgmentRoomCodeExistence   
	 * @Description: TODO 验证是否存在roomCode为传入roomCode的ConsultingRoomEntity对象   
	 * @param: @param roomCode
	 * @param: @throws Exception      
	 * @return: void      
	 * @throws
	 */
	private void judgmentRoomCodeExistence(String roomCode) throws Exception{
		if(false==obtainIOperationConsultingRoomSerivce().existConsultingRoomEnityByRoomCode(roomCode)){
			throw new RuntimeException("40005");
			//throw new RuntimeException("无效的诊断室编码(roomCode),请开发者检查roomCode的正确性，避免异常字符，注意大小写");
		}
	}

	private void judgmentSreenDeviceOnline() throws Exception{
		throw new RuntimeException("40008");
	}
	
	private IOperationDoctorAttributeService obtainIOperationDoctorAttributeService()throws Exception{
		return (IOperationDoctorAttributeService) cApplicationContext.getBean("operationDoctorAttributeService");
	}
	
	private IOperationConsultingRoomSerivce obtainIOperationConsultingRoomSerivce()throws Exception{
		return (IOperationConsultingRoomSerivce) cApplicationContext.getBean("operationConsultingRoomSerivce");
	}
}
