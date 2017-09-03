package com.howard.www.hospital.queue.operation.domain;

import java.io.Serializable;
import java.util.Vector;

import com.howard.www.core.base.util.FrameworkStringUtils;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import net.sf.json.JSONObject;

/**
 * 
 * @ClassName: ScreenDeviceEntity
 * @Description:TODO(屏幕设备信息放入到redis或cache中,屏幕的IP地址作为Key,ScreenDeviceEntity作为Value)
 * @author: mayijie
 * @date: 2017年8月21日 上午10:18:38
 * 
 * @Copyright: 2017 https://github.com/majieHoward Inc. All rights reserved.
 */
@Data
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class ScreenDeviceEntity implements Serializable {
	/**
	 * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)
	 */
	/**
	 * ANDROID_ID 是设备首次启动时由系统随机生成的一串64位的十六进制数字.
	 * 设备刷机wipe数据或恢复出厂设置时ANDROID_ID值会被重置. 现在网上已有修改设备ANDROID_ID值的APP应用.
	 * 某些厂商定制的系统可能会导致不同的设备产生相同的ANDROID_ID. 某些厂商定制的系统可能导致设备返回ANDROID_ID值为空.
	 * CDMA设备，ANDROID_ID和DeviceId返回的值相同.
	 **/
	/**
	 * MEI号（国际移动设备身份码）、IMSI号（国际移动设备识别码）这两个是有电话功能的移动设备才具有,
	 * 也就是说某些没有电话功能的平板是获取不到IMEI和IMSI号的. 且在某些设备上getDeviceId()会返回垃圾数据
	 */
	private static final long serialVersionUID = 1L;
	// eg.171.217.95.14
	private String internetProtocol="";
	// eg.351521004992889
	private String screenDeviceIdentity="";
	/**
	 * Serial Number在一些没有电话功能的设备会提供，某些手机上也可能提供(所以就是经常会返回Unknown)
	 */
	/**
	 * 序列号SerialNumber 从Android 2.3开始，通过android.os.Build.SERIAL方法可获取到一个序列号
	 * 没有电话功能的设备也都需要上给出此唯一的序列号 在某些设备上此方法会返回垃圾数据
	 */
	private String serialNumber="";
	/**
	 * ANDROID_ID 是设备首次启动时由系统随机生成的一串64位的十六进制数字.
	 * 设备刷机wipe数据或恢复出厂设置时ANDROID_ID值会被重置. 现在网上已有修改设备ANDROID_ID值的APP应用.
	 * 某些厂商定制的系统可能会导致不同的设备产生相同的ANDROID_ID. 某些厂商定制的系统可能导致设备返回ANDROID_ID值为空.
	 * CDMA设备，ANDROID_ID和DeviceId返回的值相同.
	 **/
	private String androidId="";
	// eg.A1524
	private String screenSpecification="";
	// 是否可用'10A' '10X'
	private String available="";
	
	private Vector<String> consultingRoomCodeItems=new Vector<String>();
	
	//eg.屏幕规格编号A1524
	private String screenSerialEncoding="";
	//eg.屏幕规格描述
	private String screenSerialDescribe="";
	//eg.P5632
	private String pageIdentity="";
	//eg.http://171.217.95.100:9999/index.howard
	private String pageUrlAddress="";
	
	private Object scheduling;

	public ScreenDeviceEntity(JSONObject object) throws Exception{
		structureScreenDeviceEntityFromJSON(object);
	} 
	
	public void pustRoomCodeToScreenDevice(String roomCode){
		if(!"".equals(FrameworkStringUtils.asString(roomCode))){
			consultingRoomCodeItems.add(roomCode);
		}
		
	}
	
	public void structureScreenDeviceEntityFromJSON(JSONObject object)throws Exception{
		if(object==null){
			throw new RuntimeException("structureScreenDeviceEntityFromJSON方法传入的JSONObject对象为空");
		}
		/**
		 * {
		 * ***"IP":"171.19.231.2",
		 * ***"SDI":"351521004992889",
		 * ***"SN":"BAHBBDB652502811",
		 * ***"DA":"10A",
		 * ***"SDS":"A1524",
		 * ***"AI":null,
		 * ***"SSE":"A1524",
		 * ***"PI":"P1932",
		 * ***"SSD":"风湿免疫科门诊屏",
		 * ***"SA":"10A",
		 * ***"PN":"interrogationSpot.P1932",
		 * ***"PS":"编号为P1932诊区外大屏展示页面模板",
		 * ***"PUA":"/hospital/queue/operation/interrogation/spot.P1932.exhibition",
		 * ***"PA":"10A"
		 * }
		 */
		/**
		 * SELECT
		 * ***device.internet_protocol AS IP,
		 * ***device.screen_device_identity AS SDI,
		 * ***device.serial_number AS SN,
		 * ***device.available AS DA,
		 * ***device.screen_device_specification AS SDS,
		 * ***device.android_id AS AI,
		 * ***specification.screen_serial_encoding AS SSE,
		 * ***specification.page_identity AS PI,
		 * ***specification.screen_serial_describe AS SSD,
		 * ***specification.available AS SA,
		 * ***page.page_identity AS PI,
		 * ***page.page_name AS PN,
		 * ***page.page_describe AS PS,
		 * ***page.page_url_address AS PUA,
		 * ***page.available AS PA
	     * FROM
	     * ***hhq_screen_device AS device
		 * ***INNER JOIN hhq_screen_specification AS specification ON specification.screen_serial_encoding = device.screen_device_specification
		 * ***INNER JOIN hhq_page_display AS page ON specification.page_identity = page.page_identity
		 * WHERE
		 * ***device.available = '10A'
		 * ***AND specification.available = '10A'
		 * ***AND page.available = '10A'
		 */
		this.setInternetProtocol(FrameworkStringUtils.asString(object.get("IP")));
		this.setScreenDeviceIdentity(FrameworkStringUtils.asString(object.get("SDI")));
		this.setSerialNumber(FrameworkStringUtils.asString(object.get("SN")));
		this.setScreenSpecification(FrameworkStringUtils.asString(object.get("SDS")));
		this.setAndroidId(FrameworkStringUtils.asString(object.get("AI")));
		this.setScreenSerialEncoding(FrameworkStringUtils.asString(object.get("SSE")));
		this.setScreenSerialDescribe(FrameworkStringUtils.asString(object.get("SSD")));
		this.setPageIdentity(FrameworkStringUtils.asString(object.get("PI")));
		this.setPageUrlAddress(FrameworkStringUtils.asString(object.get("PUA")));
	}
}
