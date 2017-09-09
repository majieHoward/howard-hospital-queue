$(function() {
	var sreenDeviceIp="171.19.231.2";
	/****/
	var interrogationSpotParameter={
		applicationServer:"http://127.0.0.1:9999/",
		internetProtocol:sreenDeviceIp,
		hospitalNameHtml:"成都中医药大学附属医院/四川省中医院",
		obtainDiagnosisAreaItemsController:"/treatment/hospital/queue/obtain.diagnosis.area.items.information.process"
	};
	
	var interrogationSpot=$('body').interrogationSpot(interrogationSpotParameter);
	var parameter={internetProtocol:"171.19.231.2","time":0};
	interrogationSpot.obtainDiagnosisAreaItems(parameter);
	//interrogationSpot.resizeTable();
	var	stompConnectheaders={
		"screenDevice": sreenDeviceIp
	};
	/****/
	var hospitalQueueSocketOptions= {
		/**后台服务调用使用的IP和PORT**/
		applicationServer:"http://127.0.0.1:9999/",
		/**WebSocket要连接的服务器 var url = "ws://localhost:61614/stomp";**/
		webSocketServerHost:"",
		/****/
		serverEndpoint:"/hospitalQueue",
		/****/
		stompConnectheaders:stompConnectheaders,
		/****/
		subscribeMessageTopic:["/callTheName/demo","/screenDevice/"+sreenDeviceIp+"/subscribe"]
	};
	
	var hospitalQueueSocket=$('body').hospitalQueueSocket(hospitalQueueSocketOptions);
	
});

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
 /**
 {
    "requestAddress": "",
    "interactiveData": [
        {
            "inputCode": "ZJKFK",
            "internetProtocolItems": [
                "171.19.231.4",
                "171.19.231.2"
            ],
            "screenDeviceIdentityItems": [],
            "available": "",
            "roomCode": "304",
            "roomName": "针灸康复科8诊断室（5楼）",
            "internetProtocol": "",
            "simpleName": null,
            "floorNumber": "",
            "scheduling": null,
            "screenDeviceIdentity": "",
            "designation": "",
            "deptCode": "11101",
            "roomType": null
        },
        {
            "inputCode": "EKZDSDLD",
            "internetProtocolItems": [
                "171.19.231.4"
            ],
            "screenDeviceIdentityItems": [],
            "available": "",
            "roomCode": "104",
            "roomName": "儿科4诊断室（1楼）",
            "internetProtocol": "",
            "simpleName": null,
            "floorNumber": "",
            "scheduling": null,
            "screenDeviceIdentity": "",
            "designation": "",
            "deptCode": "11201",
            "roomType": null
        },
        {
            "inputCode": "EKZDSDLD",
            "internetProtocolItems": [
                "171.19.231.4"
            ],
            "screenDeviceIdentityItems": [],
            "available": "",
            "roomCode": "105",
            "roomName": "儿科5诊断室（1楼）",
            "internetProtocol": "",
            "simpleName": null,
            "floorNumber": "",
            "scheduling": null,
            "screenDeviceIdentity": "",
            "designation": "",
            "deptCode": "11201",
            "roomType": null
        },
        {
            "inputCode": "EKZDSDLD",
            "internetProtocolItems": [
                "171.19.231.4"
            ],
            "screenDeviceIdentityItems": [],
            "available": "",
            "roomCode": "106",
            "roomName": "儿科6诊断室（1楼）",
            "internetProtocol": "",
            "simpleName": null,
            "floorNumber": "",
            "scheduling": {
                "doctorIntroduction": null,
                "doctorName": "李建保",
                "groupName": "儿科病区",
                "doctorJobNumber": "3121",
                "available": "",
                "positionalTitle": "副主任医师",
                "deptCode": "1120201"
            },
            "screenDeviceIdentity": "",
            "designation": "",
            "deptCode": "11201",
            "roomType": null
        },
        {
            "inputCode": "EKZDSDLD",
            "internetProtocolItems": [
                "171.19.231.4"
            ],
            "screenDeviceIdentityItems": [],
            "available": "",
            "roomCode": "107",
            "roomName": "儿科7诊断室（1楼）",
            "internetProtocol": "",
            "simpleName": null,
            "floorNumber": "",
            "scheduling": null,
            "screenDeviceIdentity": "",
            "designation": "",
            "deptCode": "11201",
            "roomType": null
        },
        {
            "inputCode": "EKZDSDLD",
            "internetProtocolItems": [
                "171.19.231.4"
            ],
            "screenDeviceIdentityItems": [],
            "available": "",
            "roomCode": "108",
            "roomName": "儿科8诊断室（1楼）",
            "internetProtocol": "",
            "simpleName": null,
            "floorNumber": "",
            "scheduling": null,
            "screenDeviceIdentity": "",
            "designation": "",
            "deptCode": "11201",
            "roomType": null
        },
        {
            "inputCode": "EKZDSDLD",
            "internetProtocolItems": [
                "171.19.231.4"
            ],
            "screenDeviceIdentityItems": [],
            "available": "",
            "roomCode": "109",
            "roomName": "儿科9诊断室（1楼）",
            "internetProtocol": "",
            "simpleName": null,
            "floorNumber": "",
            "scheduling": null,
            "screenDeviceIdentity": "",
            "designation": "",
            "deptCode": "11201",
            "roomType": null
        },
        {
            "inputCode": "EBHKZDSD",
            "internetProtocolItems": [
                "171.19.231.4"
            ],
            "screenDeviceIdentityItems": [],
            "available": "",
            "roomCode": "111",
            "roomName": "耳鼻喉科1诊断室（4楼）",
            "internetProtocol": "",
            "simpleName": null,
            "floorNumber": "",
            "scheduling": {
                "doctorIntroduction": null,
                "doctorName": "彭顺林",
                "groupName": "科研部",
                "doctorJobNumber": "3058",
                "available": "",
                "positionalTitle": "主任医师",
                "deptCode": "408"
            },
            "screenDeviceIdentity": "",
            "designation": "",
            "deptCode": "12701",
            "roomType": null
        },
        {
            "inputCode": "EBHKZDSD",
            "internetProtocolItems": [
                "171.19.231.4"
            ],
            "screenDeviceIdentityItems": [],
            "available": "",
            "roomCode": "112",
            "roomName": "耳鼻喉科2诊断室（4楼）",
            "internetProtocol": "",
            "simpleName": null,
            "floorNumber": "",
            "scheduling": {
                "doctorIntroduction": null,
                "doctorName": "牟珊",
                "groupName": "耳鼻喉科病区",
                "doctorJobNumber": "0300",
                "available": "",
                "positionalTitle": "副主任医师",
                "deptCode": "1270201"
            },
            "screenDeviceIdentity": "",
            "designation": "",
            "deptCode": "12701",
            "roomType": null
        },
        {
            "inputCode": "EBHKZDSD",
            "internetProtocolItems": [
                "171.19.231.4"
            ],
            "screenDeviceIdentityItems": [],
            "available": "",
            "roomCode": "113",
            "roomName": "耳鼻喉科3诊断室（4楼）",
            "internetProtocol": "",
            "simpleName": null,
            "floorNumber": "",
            "scheduling": {
                "doctorIntroduction": null,
                "doctorName": "尹红",
                "groupName": "耳鼻喉科病区",
                "doctorJobNumber": "0292",
                "available": "",
                "positionalTitle": "副主任医师",
                "deptCode": "1270201"
            },
            "screenDeviceIdentity": "",
            "designation": "",
            "deptCode": "12701",
            "roomType": null
        }
    ],
    "returnErrorEncoding": "",
    "interactiveMessage": "",
    "isSuccess": "success"
}
*/