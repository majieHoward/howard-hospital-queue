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

/**
 * 
 * @ClassName: DoctorBaseInfoEntity
 * @Description:TODO(医生的基础信息放入到redis或cache中,医生的ID作为Key,DoctorBaseInfoEntity作为value)
 * @author: mayijie
 * @date: 2017年8月21日 上午9:17:21
 * 
 * @Copyright: 2017 https://github.com/majieHoward Inc. All rights reserved.
 */
@Data
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class DoctorAttributeEntity implements Serializable {
	/**
	 * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)
	 */
	private static final long serialVersionUID = 1L;
	// 医生工号
	private String doctorJobNumber;
	// 医生简介
	private String doctorIntroduction;
	// 医生姓名
	private String doctorName;
	// 医生头像照片以base64位存储

	private String groupName;
	// eg.主治医师
	private String positionalTitle;

	private String deptCode;
	// 是否可用'10A' '10X'
	private String available;
	
	public DoctorAttributeEntity(JSONObject doctorObject) throws Exception{
		structureDoctorAttributeEntityFromJSON(doctorObject);
	}

	public void structureDoctorAttributeEntityFromJSON(JSONObject doctorObject) throws Exception {
		if(doctorObject==null){
			throw new RuntimeException("structureDoctorAttributeEntityFromJSON方法传入的JSONObject对象为空");
		}
		/**
		 * SELECT
		 * ***doctor.ID AS I,
		 * ***doctor.NAME AS N,
		 * ***doctor.GROUP_NAME AS GN,
		 * ***doctor.TITLE AS T,
		 * ***doctor.DEPT_CODE AS DC 
		 * ***resume.doctor_resume AS DR
		 * "FROM "
		 * "v_outp_doctor doctor "
		 * "LEFT JOIN hhq_doctor_resume resume "
		 * "on doctor.ID = resume.doctor_id"
		 */
		this.setDoctorJobNumber(FrameworkStringUtils.asString(doctorObject.get("I")));
		this.setDoctorName(FrameworkStringUtils.asString(doctorObject.get("N")));
		this.setGroupName(FrameworkStringUtils.asString(doctorObject.get("GN")));
		this.setPositionalTitle(FrameworkStringUtils.asString(doctorObject.get("T")));
		this.setDeptCode(FrameworkStringUtils.asString(doctorObject.get("DC")));
	    this.setDoctorIntroduction(FrameworkStringUtils.asString(doctorObject.get("DR")));
	}
}
