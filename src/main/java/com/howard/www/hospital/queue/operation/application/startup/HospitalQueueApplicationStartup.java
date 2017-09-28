package com.howard.www.hospital.queue.operation.application.startup;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.web.socket.messaging.StompSubProtocolHandler;
import org.springframework.web.socket.messaging.SubProtocolHandler;
import org.springframework.web.socket.messaging.SubProtocolWebSocketHandler;

import com.howard.www.core.data.transfer.dto.impl.DataTransferObject;
import com.howard.www.hospital.queue.operation.service.IOperationConsultingRoomSerivce;
import com.howard.www.hospital.queue.operation.service.IOperationDoctorAttributeService;
import com.howard.www.hospital.queue.operation.service.IOperationDoctorSchedulingService;
import com.howard.www.hospital.queue.operation.service.IOperationScreenConsultingService;
import com.howard.www.hospital.queue.operation.service.IOperationScreenDeviceService;
import com.howard.www.hospital.queue.operation.websocket.socket.messaging.HospitalQueueStompSubProtocolHandler;

/**
 * 
 * @ClassName: HospitalQueueApplicationStartup
 * @Description:TODO 当容器初始化完成之后,需要处理一些操作,比如一些数据的加载初始化缓存特定任务的注册等等
 *                   在spring中InitializingBean接口也提供了类似的功能,只不过它进行操作的时机是在所有bean都被实例化之后才进行调用
 * @author: mayijie
 * @date: 2017年9月4日 上午12:53:01
 * 
 * @Copyright: 2017 https://github.com/majieHoward Inc. All rights reserved.
 */
public class HospitalQueueApplicationStartup implements ApplicationListener<ContextRefreshedEvent> {
	protected final Logger log = LoggerFactory.getLogger(HospitalQueueApplicationStartup.class);
	private ApplicationContext cApplicationContext;

	@Override
	public void onApplicationEvent(ContextRefreshedEvent event) {
		// TODO Auto-generated method stub
		log.info(
				"Interface to be implemented by application event listeners. Based on the standard java.util.EventListener interface for the Observer design pattern.");
		cApplicationContext = event.getApplicationContext();
		try {
			outputContainerInstance();
			/**
			 * 初始化诊室列表
			 */
			obtainIOperationConsultingRoomSerivce().initializingServiceBaseData(new DataTransferObject());
			/**
			 * 初始化医生列表(包括医生简介)
			 */
			obtainIOperationDoctorAttributeService().initializingServiceBaseData(new DataTransferObject());
			/**
			 * 初始化设备列表(包含设备类型对应的页面关系)
			 */
			obtainIOperationScreenDeviceService().initializingServiceBaseData(new DataTransferObject());
			/**
			 * 构造诊室和设备的对照关系 在ROOM_CODE对应的ConsultingRoomEntity上添加(Vector
			 * push)InternetProtocol为IP的ScreenDeviceEntity映射关系
			 * 在IP对应的SreenDevice上添加(Vector push)ROOMCODE对应的ConsultingRoom映射关系
			 */
			obtainIOperationScreenConsultingService().initializingServiceBaseData(new DataTransferObject());
			/**
			 * 构造当天诊室和医生的对照关系
			 * 将visitTimeDescCode为:visitTimeDescCode的ConcurrentHashMap<String,
			 * CurrentDoctorSchedulingEntity>对象放入到schedulingMap中
			 * 将roomCode为:ROOM_CODE的ConcurrentHashMap<String,
			 * ConcurrentHashMap<String,
			 * CurrentDoctorSchedulingEntity>>对象放入到doctorSchedulingMap中
			 */
			obtainIOperationDoctorSchedulingService().initializingServiceBaseData(new DataTransferObject());
			/**
			 * 其他的关系可以暂时不初始化
			 */
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			replacedStompSubProtocolHandler();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 
	 * @Title: outputContainerInstance   
	 * @Description: TODO 输出Spring容器中所有实例化出的Bean 
	 * @param: @throws Exception      
	 * @return: void      
	 * @throws
	 */
	private void outputContainerInstance()throws Exception{
		for(String instanceBeanName:this.cApplicationContext.getBeanDefinitionNames()){
			log.info("Spring 容器中的Bean:"+instanceBeanName);
		}
	}
	
	private void replacedStompSubProtocolHandler() throws Exception {
		SubProtocolWebSocketHandler subProtocolWebSocketHandler = obtainsubProtocolWebSocketHandler();
		Map<String, SubProtocolHandler> subProtocolHandlerMap = subProtocolWebSocketHandler.getProtocolHandlerMap();
		List<SubProtocolHandler> listOfSubProtocolHandler = new ArrayList<SubProtocolHandler>();
		for (String key : subProtocolHandlerMap.keySet()) {  
			if(subProtocolHandlerMap.get(key) instanceof StompSubProtocolHandler){
				listOfSubProtocolHandler.add(new HospitalQueueStompSubProtocolHandler(subProtocolHandlerMap.get(key),cApplicationContext));
			}
			
		}  
		
		if(listOfSubProtocolHandler.size()>0){
			subProtocolWebSocketHandler.setProtocolHandlers(listOfSubProtocolHandler);
		}
	}

	private SubProtocolWebSocketHandler obtainsubProtocolWebSocketHandler() throws Exception {
		return (SubProtocolWebSocketHandler) cApplicationContext.getBean("subProtocolWebSocketHandler");
	}

	private IOperationConsultingRoomSerivce obtainIOperationConsultingRoomSerivce() throws Exception {
		return (IOperationConsultingRoomSerivce) cApplicationContext.getBean("operationConsultingRoomSerivce");
	}

	private IOperationDoctorAttributeService obtainIOperationDoctorAttributeService() throws Exception {
		return (IOperationDoctorAttributeService) cApplicationContext.getBean("operationDoctorAttributeService");
	}

	private IOperationScreenDeviceService obtainIOperationScreenDeviceService() throws Exception {
		return (IOperationScreenDeviceService) cApplicationContext.getBean("operationScreenDeviceService");
	}

	private IOperationScreenConsultingService obtainIOperationScreenConsultingService() throws Exception {
		return (IOperationScreenConsultingService) cApplicationContext.getBean("operationScreenConsultingService");
	}

	private IOperationDoctorSchedulingService obtainIOperationDoctorSchedulingService() throws Exception {
		return (IOperationDoctorSchedulingService) cApplicationContext.getBean("operationDoctorSchedulingService");
	}
}
