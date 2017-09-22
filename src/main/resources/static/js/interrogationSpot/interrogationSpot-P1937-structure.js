/**add by mayijie at 2017.09.05**/
;(function($){
	// set the version of the interrogationSpot
	$.interrogationSpot = {version: "2017.09.04.P1937"};
	
	/**$.fn.extend() 函数为jQuery扩展一个或多个实例属性和方法(主要用于扩展方法)**/
	$.fn.interrogationSpot = function(options) {
		return new $.interrogationSpot(this, options);
	};

	$.interrogationSpot = function (interrogationSpot, options){
		/**jQuery.extend() 函数用于将一个或多个对象的内容合并到目标对象**/
		options = $.extend({}, $.interrogationSpot.defaults, options);
		var self = this;
		var $interrogationSpot = $(interrogationSpot);
		var screenWidth;
        var screenHeight;
		var pageLogoHeight;
		var hospitalqueuePanelHeadingHeight;
		var listOfVisitsBody;
		var listOfVisitsBodyStatus=400;
		/**add by mayijie at 2017.09.08**/
		var maxStringLength=0;
		var hospitalqueuePanelBodyPadding;
		var obtainDiagnosisAreaItemsController=options.applicationServer+options.obtainDiagnosisAreaItemsController;
		/**$.data() 函数用于在指定的元素上存取数据，返回设置值**/
		$.data($interrogationSpot, "interrogationSpot", self);
		
		this.obtainScreenHeight = function (){
			return screenHeight;
		}
		
		this.obtainScreenWidth = function (){
			return screenWidth;
		}
		
		this.resizeTable=function(){
			resizeTable();
		}
		/**<span id="hospitalName" name="hospitalName">成都中医药大学附属医院/四川省中医院</span>**/
		this.evaluateHospitalNameHtml=function(parameter){
			evaluateHospitalNameHtml(parameter);
		}
		
		/**<h2 id="hospitalqueuePanelHeadingH2" name="hospitalqueuePanelHeadingH2">门诊西药房</h2>**/
		this.evaluateHospitalqueuePanelHeadingH2Html=function(parameter){
			evaluateHospitalqueuePanelHeadingH2Html(parameter);
		}
		
		/**add by mayijie at 2017.09.07**/
		/**获取诊区的所有诊室的信息(包括诊室和所安排的医生)**/
		this.obtainDiagnosisAreaItems=function(parameterData,successCallback){
			if( $.isFunction(successCallback) ){
				obtainDiagnosisAreaItems(obtainDiagnosisAreaItemsController,parameterData,successCallback);
			}else{
				obtainDiagnosisAreaItems(obtainDiagnosisAreaItemsController,parameterData,diagnosisAreaItemsSuccess);
			}
		}
		/**获取诊区的所有诊室的信息(包括诊室和所安排的医生)成功回掉函数**/
		var diagnosisAreaItemsSuccess=function(resultJsonObject){
			console.log(resultJsonObject);
			if($.hosptialQueueJudgmentCrossValue.typeOfJsonObject(resultJsonObject)
				&&resultJsonObject.hasOwnProperty("isSuccess")
				&&resultJsonObject.hasOwnProperty("interactiveData")){
				/**"isSuccess": "success"**/
				if("success"==resultJsonObject.isSuccess){
					/**interactiveData**/
					listOfVisitsBodyStatus=200;
					listOfVisitsBody=resultJsonObject.interactiveData;
					
				}
			}
			resizeTable();
		}
		
		function obtainDiagnosisAreaItems(controller,parameterData, successCallback){			
			$.hosptialQueuePost(controller,parameterData, successCallback);
		}
		
		function layoutPage(){
			obtainScreenSize();
			resizehospitalLogoImg();
		}
		
		function obtainScreenSize(){
			obtainScreenWidth();
			obtainScreenHeight();
		}
		
		function resizehospitalLogoImg(){
			/**<img src="img/logo.png" alt="logo" id="hospitalLogoImg" name="hospitalLogoImg"></img>**/
			var logoImglength=4.8*screenWidth/1024;
			$("#hospitalLogoImg").css("height",logoImglength+"rem");
			$("#hospitalLogoImg").css("width",logoImglength+"rem");
			resizeHospitalName();
			obtainPageLogoHeight();
			resizeHospitalqueuePanelHeading();
			obtainHospitalqueuePanelHeadingHeight();
		}
		
		/**获取屏幕的宽度**/
		function obtainScreenWidth(){
			/**屏幕的宽度 1024**/
			screenWidth = document.documentElement?document.documentElement.clientWidth:document.body.clientWidth;
		}
		
		function obtainScreenHeight(){
			/**屏幕的高度 768**/
			screenHeight = document.documentElement?document.documentElement.clientHeight:document.body.clientHeight;
		}
		
		function resizeHospitalName(){
			/**设置<span id="hospitalName" name="hospitalName">成都中医药大学附属医院/四川省中医院</span>的font-size**/
			evaluateHospitalNameHtml(options.hospitalNameHtml);
		}
		
		function evaluateHospitalNameHtml(parameter){
			/** <a class="navbar-brand" href="#" id="hospitalBrand" name="hospitalBrand">**/
			var hospitalBrandPadding= 1.5*screenWidth/1024;
			$("#hospitalBrand").css("padding",hospitalBrandPadding+"rem")
			/**设置<span id="hospitalName" name="hospitalName">成都中医药大学附属医院/四川省中医院</span>的font-size**/
			var hospitalNameFontSize= 4.8*screenWidth/1024;
			console.log("parameters.extendsHength():"+parameter.extendsHength());
			/**add by mayijie at 2017.09.09 hospitalName中字体的大小**/
			var nameLength=(parameter.extendsHength()/2)+(parameter.extendsHength()%2);
			var hospitalNameWidth=screenWidth/10-(hospitalBrandPadding*2)-hospitalNameFontSize;
			$("#hospitalName").css("font-size",(hospitalNameWidth/nameLength)+"rem");
			$("#hospitalName").html(parameter);
		}
		
		function obtainPageLogoHeight(){
			/**<nav class="top1 navbar navbar-default navbar-static-top navbar-fixed-top" role="navigation" style="margin-bottom: 0" id="pageLogo" name="pageLogo">**/
			pageLogoHeight = $('#pageLogo').outerHeight();
			resizeMainHospitalqueueV();
		}
		
		function resizeHospitalqueuePanelHeading(){
			/**<div class="panel-heading" id="hospitalqueuePanelHeading" name="hospitalqueuePanelHeading">**/
			$("#hospitalqueuePanelHeading").css("padding",1*screenHeight/768+"rem");
			$("#hospitalqueuePanelHeading").css("height",5*screenHeight/768+"rem");
			resizeHospitalqueuePanelHeadingH2();
		}
		
		function resizeHospitalqueuePanelHeadingH2(){
			/**<h2 id="hospitalqueuePanelHeadingH2" name="hospitalqueuePanelHeadingH2">门诊西药房</h2>**/
			$("#hospitalqueuePanelHeadingH2").css("font-size",3*screenHeight/768+"rem");
			evaluateHospitalqueuePanelHeadingH2Html(options.hospitalqueuePanelHeadingH2Html);
		}
		
		function  evaluateHospitalqueuePanelHeadingH2Html(parameter){
			$("#hospitalqueuePanelHeadingH2").html(parameter);
		}
	
	    function  obtainHospitalqueuePanelHeadingHeight(){
			hospitalqueuePanelHeadingHeight=$('#hospitalqueuePanelHeading').outerHeight();
		}
	
		function resizeMainHospitalqueueV(){
			/**<section class="hospitalqueue" id="mainHospitalqueueV" name="mainHospitalqueueV">**/
			$('#mainHospitalqueueV').css("padding-top",pageLogoHeight+"px");
			$("#mainHospitalqueueV").css("vertical-align","middle");
			resizeHospitalqueuePanelBody();
		}
		
		function resizeHospitalqueuePanelBody(){
			/**<div class="bs-example4" id="hospitalqueuePanelBody" name="hospitalqueuePanelBody">**/
			hospitalqueuePanelBodyPadding=1*screenWidth/1024;
			$("#hospitalqueuePanelBody").css("padding-left",hospitalqueuePanelBodyPadding+"rem");
			$("#hospitalqueuePanelBody").css("padding-right",hospitalqueuePanelBodyPadding+"rem");
			
		}
		
		function resizeTable(){
			var windowHeight=screenHeight-pageLogoHeight-hospitalqueuePanelHeadingHeight-3;
			console.log("windowHeight:"+windowHeight);
			$("#hospitalqueuePanelBody").css("height",windowHeight+"px");
			/**add by mayijie at 2017.09.07**/
			var totalInteractiveData = 0;
			if(200==listOfVisitsBodyStatus){
				totalInteractiveData=listOfVisitsBody.length+1;
			}
			if(totalInteractiveData<7){
				totalInteractiveData=7;
			}
			console.log("totalInteractiveData:"+totalInteractiveData);
			/**构造table的中的tbody标签中的内容**/
			structureListOfVisitsBodyData(listOfVisitsBody);
			var trHeight=windowHeight/totalInteractiveData;
			/**line-height	设置数字,此数字会与当前的字体尺寸相乘来设置行间距**/
			console.log("(screenWidth-(hospitalqueuePanelBodyPadding*20)):"+(screenWidth-(hospitalqueuePanelBodyPadding*20)));
			console.log("this.maxStringLength:"+this.maxStringLength);
			var tbodyFontSize=(screenWidth-(hospitalqueuePanelBodyPadding*20))/(this.maxStringLength);
			console.log("tbodyFontSize:"+tbodyFontSize);
			
			var tbodyPadding=5*screenWidth/768;
			var dd=trHeight-(2*tbodyPadding);
			console.log("trHeight-(2*tbodyPadding):"+dd);
			if((trHeight-(2*tbodyPadding))<tbodyFontSize){
				tbodyFontSize=trHeight-(2*tbodyPadding);
			}
			console.log("trHeight:"+trHeight);
			$("#mainHospitalqueueV").find("tr").each(function(){
				$(this).css("line-height","1");
				$(this).css("height",trHeight+"px");
				
				
				/**设置font-size**/
			});
			$("#mainHospitalqueueV").find("td").each(function(){
				$(this).css("vertical-align","middle");
				/**设置font-size**/
				$(this).css("line-height","1");
				$(this).css("font-size",tbodyFontSize+"px");
				//$(this).css("padding-top",tbodyPadding+"px");
				//$(this).css("padding-bottom",tbodyPadding+"px");
			});
			$("#mainHospitalqueueV").find("th").each(function(){
				//    vertical-align: middle;
				$(this).css("vertical-align","middle");
				$(this).css("line-height","1");
				$(this).css("font-size",tbodyFontSize+"px");
				//text-align: center;
				//$(this).css("text-align","center");
				/**设置font-size**/
				//$(this).css("padding-top",tbodyPadding+"px");
				//$(this).css("padding-bottom",tbodyPadding+"px");
			});
		}
		
		function structureListOfVisitsBodyData(listOfVisitsBody){
			if(listOfVisitsBody.length>0){
				/**
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
				}
				**/
				var tr,td,roomCode,roomName,doctorJobNumber,doctorName;
				var totalStringLength=0;
				var totalStringLengthArray=[];
				$.each(listOfVisitsBody,function(identifier,diagnosisAreaItem){
					/**diagnosisAreaItem.hasOwnProperty("roomCode")**/
					if(diagnosisAreaItem&&diagnosisAreaItem.hasOwnProperty("roomCode")){
						roomCode=diagnosisAreaItem.roomCode;
					}
					
					/**diagnosisAreaItem.hasOwnProperty("roomName")**/
					if(diagnosisAreaItem&&diagnosisAreaItem.hasOwnProperty("roomName")){
						roomName=diagnosisAreaItem.roomName;
					}
					
					totalStringLength=totalStringLength+roomName.extendsHength();
					/**diagnosisAreaItem.scheduling.hasOwnProperty("doctorJobNumber")**/
					if(diagnosisAreaItem&&diagnosisAreaItem.scheduling
					&&diagnosisAreaItem.scheduling.hasOwnProperty("doctorJobNumber")){
						doctorJobNumber=diagnosisAreaItem.scheduling.doctorJobNumber;
					}
					
					/**diagnosisAreaItem.scheduling.hasOwnProperty("doctorName")**/
					if(diagnosisAreaItem&&diagnosisAreaItem.scheduling
					&&diagnosisAreaItem.scheduling.hasOwnProperty("doctorName")){
						doctorName=diagnosisAreaItem.scheduling.doctorName;
					}
					totalStringLength=totalStringLength+roomName.extendsHength();
					
					tr=listOfVisitsBodyTr("","","",{},$("#listOfVisitsBody"));
					/**edit by mayijie at 2017.09.22 修改元素的ID**/
					/**科室中文名称**/
					td=listOfVisitsBodyTd(roomCode+"Room","",roomName,{},tr);
					/**医生姓名**/
					td=listOfVisitsBodyTd(roomCode+"Doctor","",doctorName,{},tr);
					/**构造患者名称**/
					td=listOfVisitsBodyTd(roomCode+"Patient","","",{},tr);
					/**构造患者名称**/
					td=listOfVisitsBodyTd(roomCode+"Patient","","",{},tr);
					totalStringLength+=8;
					totalStringLengthArray.push(totalStringLength);
					totalStringLength=0;
				});
				console.log(totalStringLengthArray);
				this.maxStringLength=Math.max.apply(null, totalStringLengthArray);
			}
		}
		
		function listOfVisitsBodyTr(primaryKey,className,htmlValue,cssValue,parentObject){
			var tr=$("<tr>",{
				"class":className,
				"id":primaryKey,
				"name":primaryKey,
				css:cssValue,
				html:htmlValue
			});
			tr.appendTo(parentObject);
			return tr;
		}
		
		function listOfVisitsBodyTd(primaryKey,className,htmlValue,cssValue,parentObject){
			var tr=$("<td>",{
				"class":className,
				"id":primaryKey,
				"name":primaryKey,
				css:cssValue,
				html:htmlValue
			});
			tr.appendTo(parentObject);
			return tr;
		}
		
		/**$.isFunction()函数用于判断指定参数是否是一个函数**/
		if( $.isFunction(options.init) ) options.init.apply(self, [$interrogationSpot, options]);
		layoutPage();
	};

	$.interrogationSpot.defaults = {
		hospitalNameHtml:"",
		hospitalqueuePanelHeadingH2Html:"",
		/**后台服务调用使用的IP和PORT**/
		applicationServer:"",
		/**当前屏幕设备的IP address**/
		internetProtocol:"",
		/**页面logo后显示的文字信息**/
		/**获取诊区的所有诊室的信息(包括诊室和所安排的医生) controller RequestMapping value**/
		obtainDiagnosisAreaItemsController:""
	};

})(jQuery);