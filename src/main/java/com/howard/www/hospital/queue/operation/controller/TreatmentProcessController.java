package com.howard.www.hospital.queue.operation.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.howard.www.core.base.util.FrameworkStringUtils;
import com.howard.www.core.data.transfer.dto.IDataTransferObject;
import com.howard.www.hospital.queue.operation.service.IOperationDoctorSchedulingService;
import com.howard.www.hospital.queue.operation.service.IOperationScreenDeviceService;
import com.howard.www.hospital.queue.operation.service.IOperationTreatmentProcessService;

@RestController
public class TreatmentProcessController {
	protected final Logger log = LoggerFactory.getLogger(ExternalProvidedHisController.class);
	@Autowired
	private ApplicationContext cApplicationContext;

	/**
	 * 
	 * @Title: obtainMedicalInformation   
	 * @Description: TODO(这里用一句话描述这个方法的作用)   
	 * @param: @param requiredParameter
	 * @param: @return
	 * @param: @throws Exception      
	 * @return: String      
	 * @throws
	 */
	public String obtainMedicalInformation(IDataTransferObject requiredParameter) throws Exception {
		return null;
	}
	
	@RequestMapping(value="/treatment/hospital/queue/obtain.home.screen.display.page.process")
	public String obtainHomeScreenDisplayPage(IDataTransferObject requiredParameter)throws Exception{
		/**
		 * {
		 * "requestAddress":"",
		 * "interactiveData":{
		 * "pageIdentity":"P1932",
		 * "serialNumber":"BAHBBDB652502811",
		 * "screenSerialEncoding":"A1524",
		 * "available":"",
		 * "screenSerialDescribe":"风湿免疫科门诊屏",
		 * "consultingRoomCodeItems":["302","304","300"],
		 * "internetProtocol":"171.19.231.2",
		 * "pageUrlAddress":"/hospital/queue/operation/interrogation/spot.P1932.exhibition",
		 * "scheduling":null,
		 * "screenDeviceIdentity":"351521004992889",
		 * "screenSpecification":"A1524",
		 * "androidId":null
		 * },
		 * "returnErrorEncoding":"",
		 * "interactiveMessage":"",
		 * "isSuccess":"success"}
		 */
		return FrameworkStringUtils.asString(obtainIOperationScreenDeviceService().obtainHomeScreenDisplayPage(requiredParameter));
	}
	/**
	 * 
	 * @Title: obtainDiagnosisAreaItemsInformation   
	 * @Description: TODO获取诊区的所有诊室的信息(包括诊室和所安排的医生)   
	 * @param: @param requiredParameter
	 * @param: @return
	 * @param: @throws Exception      
	 * @return: String      
	 * @throws
	 */
	@RequestMapping(value="/treatment/hospital/queue/obtain.diagnosis.area.items.information.process")
	public String obtainDiagnosisAreaItemsInformation(IDataTransferObject requiredParameter) throws Exception {
		/**
		 * {
		 * ***"requestAddress": "",
		 * ***"interactiveData": [
		 * ******{
		 * *********"inputCode": "FRMZZD",
		 * *********"internetProtocolItems": [
		 * ************"171.19.231.2"
		 * *********],
		 * *********"screenDeviceIdentityItems": [],
		 * *********"available": "",
		 * *********"roomCode": "302",
		 * *********"roomName": "发热门诊诊断室（1楼）",
		 * *********"internetProtocol": "",
		 * *********"simpleName": null,
		 * *********"floorNumber": "",
		 * *********"scheduling": null,
		 * *********"screenDeviceIdentity": "",
		 * *********"designation": "",
		 * *********"deptCode": "12901",
		 * *********"roomType": null
		 * ******},
		 * ******{
		 * *********"inputCode": "ZJKFK",
		 * *********"internetProtocolItems": [
		 * ************"171.19.231.4",
		 * ************"171.19.231.2"
		 * *********],
		 * *********"screenDeviceIdentityItems": [],
		 * *********"available": "",
		 * *********"roomCode": "304",
		 * *********"roomName": "针灸康复科8诊断室（5楼）",
		 * *********"internetProtocol": "",
		 * *********"simpleName": null,
		 * *********"floorNumber": "",
		 * *********"scheduling": null,
		 * *********"screenDeviceIdentity": "",
		 * *********"designation": "",
		 * *********"deptCode": "11101",
		 * *********"roomType": null
		 * ******},
		 * ******{
		 * *********"inputCode": "CDMZZS",
		 * *********"internetProtocolItems": [
		 * ************"171.19.231.2"
		 * *********],
		 * *********"screenDeviceIdentityItems": [],
		 * *********"available": "",
		 * *********"roomCode": "300",
		 * *********"roomName": "肠道门诊诊断室（1楼）",
		 * *********"internetProtocol": "",
		 * *********"simpleName": null,
		 * *********"floorNumber": "",
		 * *********"scheduling": null,
		 * *********"screenDeviceIdentity": "",
		 * *********"designation": "",
		 * *********"deptCode": "11301",
		 * *********"roomType": null
		 * ******}
		 * ***],
		 * ***"returnErrorEncoding": "",
		 * ***"interactiveMessage": "",
		 * ***"isSuccess": "success"
		 * }
		 */
		return FrameworkStringUtils.asString(
				obtainIOperationBusinessProcessService().obtainDiagnosisAreaItemsInformation(requiredParameter));
	}

	/**
	 * 
	 * @Title: obtainAtCertainTimesDoctor   
	 * @Description: TODO 获取某个诊室当前坐诊的医生详细信息  
	 * @param: @param requiredParameter
	 * @param: @return
	 * @param: @throws Exception      
	 * @return: String      
	 * @throws
	 */
	@RequestMapping("/treatment/hospital/queue/obtain.at.certain.times.doctor.process")
	public String obtainAtCertainTimesDoctor(IDataTransferObject requiredParameter) throws Exception {
		return FrameworkStringUtils
				.asString(obtainIOperationDoctorSchedulingService().obtainAtCertainTimesDoctor(requiredParameter));
	}

	private IOperationDoctorSchedulingService obtainIOperationDoctorSchedulingService() throws Exception {
		return (IOperationDoctorSchedulingService) cApplicationContext.getBean("operationDoctorSchedulingService");
	}

	private IOperationTreatmentProcessService obtainIOperationBusinessProcessService() throws Exception {
		return (IOperationTreatmentProcessService) cApplicationContext.getBean("operationTreatmentProcessService");
	}
	
	private IOperationScreenDeviceService obtainIOperationScreenDeviceService()throws Exception{
		return (IOperationScreenDeviceService) cApplicationContext.getBean("operationScreenDeviceService");
	}
}
