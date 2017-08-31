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
public class CurrentDoctorSchedulingEntity implements Serializable{
	/**   
	 * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)   
	 */  
	private static final long serialVersionUID = 1L;
	// 医生工号
	private String doctorJobNumber="";
	// eg.300
    private String roomCode="";
    // eg.上午 下午 夜晚
    private String visitTimeDesc="";
    // eg.0 1 2
    private String visitTimeDescCode="";
    //
    private String visitDate="";
   
    public CurrentDoctorSchedulingEntity(JSONObject doctorSchedulingItem){
    	try {
			structureCurrentDoctorSchedulingEntityFromJSON(doctorSchedulingItem);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

    public void structureCurrentDoctorSchedulingEntityFromJSON(JSONObject doctorSchedulingObject)throws Exception{
    	if(doctorSchedulingObject==null){
			throw new RuntimeException("structureCurrentDoctorSchedulingEntityFromJSON方法传入的JSONObject对象为空");
		}
    	/**
    	 * SELECT
			* ***t.doctor AS D,
			* ***t.room_code AS RC,
			* ***t.visit_time_desc AS VTD,
			* ***t.visit_date AS VD
			* FROM
			* ***v_jiaohao t
			* GROUP BY
			* ***t.doctor,
			* ***t.room_code,
			* ***t.visit_time_desc,
			* ***t.visit_date
			* ORDER BY
			* ***t.room_code
    	 */
    	setDoctorJobNumber(FrameworkStringUtils.asString(doctorSchedulingObject.get("D")));
    	setRoomCode(FrameworkStringUtils.asString(doctorSchedulingObject.get("RC")));
    	setVisitTimeDesc(FrameworkStringUtils.asString(doctorSchedulingObject.get("VTD")));
    	setVisitDate(FrameworkStringUtils.asString(doctorSchedulingObject.get("VD")));
    	//上午 下午 夜晚
    	if("上午".equals(this.visitTimeDesc)){
    		setVisitTimeDescCode("0");
    	}else if("下午".equals(this.visitTimeDesc)){
    		setVisitTimeDescCode("1");
    	}else if("夜晚".equals(this.visitTimeDesc)){
    		setVisitTimeDescCode("2");
    	}else if ("".equals(this.visitTimeDesc)) {
    		setVisitTimeDescCode("3");
		}
    }
}
