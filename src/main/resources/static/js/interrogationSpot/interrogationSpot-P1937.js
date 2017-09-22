$(function() {
	var interrogationSpotParameter={
		hospitalNameHtml:"成都中医药大学附属医院/四川省中医院",
		hospitalqueuePanelHeadingH2Html:"门诊西药房",
		applicationServer:"http://127.0.0.1:9999/",
		internetProtocol:"171.19.231.4",
		obtainDiagnosisAreaItemsController:"/treatment/hospital/queue/obtain.diagnosis.area.items.information.process"
	};
	var interrogationSpot=$('body').interrogationSpot(interrogationSpotParameter);
   var parameter={internetProtocol:"171.19.231.4","time":0};
	interrogationSpot.obtainDiagnosisAreaItems(parameter);
	//interrogationSpot.resizeTable();
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