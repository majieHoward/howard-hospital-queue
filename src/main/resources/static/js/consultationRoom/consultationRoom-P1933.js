/**网页可见区域宽:document.body.clientWidth**/
/**网页可见区域高:document.body.clientHeight**/
/**网页可见区域宽:document.body.offsetWidth (包括边线的宽)**/
/**网页可见区域高:document.body.offsetHeight (包括边线的高)**/
/**网页正文全文宽:document.body.scrollWidth**/
/**网页正文全文高:document.body.scrollHeight**/
/**网页被卷去的高:document.body.scrollTop**/
/**网页被卷去的左:document.body.scrollLeft**/
/**网页正文部分上:window.screenTop**/
/**网页正文部分左:window.screenLeft**/
/**屏幕分辨率的高:window.screen.height**/
/**屏幕分辨率的宽:window.screen.width**/
/**屏幕可用工作区高度:window.screen.availHeight**/
/**屏幕可用工作区宽度:window.screen.availWidth**/
 var stompClient = null;
    function setConnected(connected) {
       
    }
$(document).ready( function (){
disconnect();
    /**屏幕的高度 660**/
	var screenHeight=document.documentElement?document.documentElement.clientHeight:document.body.clientHeight;
	/**屏幕的宽度 732**/
	var screenWidth=document.documentElement?document.documentElement.clientWidth:document.body.clientWidth;
	var headerContentHeight=$("#headerContent").outerHeight();
	var headerContentWidth=$("#headerContent").outerWidth();
    /**screenHeight==headerContentHeight**/
	var introduceHeight=$("#introduce").outerHeight();
	var introduceWidth=$("#introduce").outerWidth();
	console.log("screenHeight:"+screenHeight);
	console.log("screenWidth:"+screenWidth);
	console.log("headerContentHeight:"+headerContentHeight);
	console.log("headerContentWidth:"+headerContentWidth);
	console.log("introduceHeight:"+introduceHeight);
	console.log("introduceWidth:"+introduceWidth);
	var waitingAreaWidth=15*screenWidth/600;
	$("#waitingArea").css("padding",waitingAreaWidth+"px");
	var waitingAreaFontSize=2*screenWidth/600;
	$("#waitingArea").css("font-size",waitingAreaFontSize+"rem");	
	var waitingAreaFooterHeight=$("#waitingAreaFooter").outerHeight();
	/****/
	var doctorIntroductionHeight=screenHeight-headerContentHeight-introduceHeight-waitingAreaFooterHeight;
	console.log(doctorIntroductionHeight);
	if(doctorIntroductionHeight>0){
		$('#doctorSkills').css("padding-top",20+"px");
		$('#doctorSkills').css("padding-left",10+"px");
		$('#doctorSkills').css("padding-right",10+"px");
		//text() - 设置或返回所选元素的文本内容
		//html() - 设置或返回所选元素的内容（包括 HTML 标记）
		//val() - 设置或返回表单字段的值
		var doctorIntroductionValue=$('#doctorIntroduction').text();
		console.log(doctorIntroductionValue);
		var doctorIntroductionLength=stringValueLength(doctorIntroductionValue)+2;
		console.log("doctorIntroductionLength"+":"+doctorIntroductionLength);
		var row=doctorIntroductionLength/5;
		console.log("每行的字数:"+row);
		var fontSize=(screenWidth-20)/row;
		console.log("每个字的大小:"+fontSize);
		$('#doctorSkills').css("font-size",fontSize+"px");
		//设置首行缩进
		$('#doctorSkills').css("text-indent",fontSize*2+"px");
		//$('#doctorSkills').css("line-height",fontSize+"rem");
	}
	/**screenHeight:660**/
    /**screenWidth:412**/
	/**headerContentHeight:189**/
	/**headerContentWidth:412**/
	/**introduceHeight:31**/
	/**introduceWidth:412**/
});

function stringValueLength(parameter){
    return parameter.length;
}

function evaluateDoctorName(){
	$("#doctorName").html("");
}

function evaluateDoctorIntroduction(){
    $("#doctorIntroduction").html("");
}
$(window).load(function() {
		 
		connect();
	});
	function disconnect() {
        if (stompClient != null) {
            stompClient.disconnect();
        }
        setConnected(false);
        console.log('Disconnected');
    }
	function connect() {
    	//var sockjs = new SockJS(url, _reserved, options);
    	//sockjs.min.js:27 Uncaught Error: Only basic urls are supported in SockJS
        //建立一个基于Web Socket的连接到STOMP代理
    	var socket = new SockJS('/hospitalQueue');
        stompClient = Stomp.over(socket);
        //client.connect(login, passcode, connectCallback);
        //client.connect(login, passcode, connectCallback, errorCallback);
        //client.connect(login, passcode, connectCallback, errorCallback, host);
        //传递参数
        var connectionParameter={
        	"screenDevice": "SD988JH"
        }
        
        stompClient.connect(connectionParameter, function (frame) {
            setConnected(true);
            console.log('Connected:' + frame);
            stompClient.subscribe('/callTheName/public', function (response) {
            	// and acknowledge it
                response.ack();
            	console.log(response.body);
            	 var success = function() {  };
                 var error = function(message) {
                 console.log(message);
                 alert("Oopsie! " + message);
                 };
                 TTSClientPlugin.speak(response.body,success,error);
            });
            stompClient.subscribe('/screenDevice/171.19.231.4/subscribe', function (response) {
            	// and acknowledge it
                response.ack();
            	console.log(response.body);
            	 var success = function() {  };
                 var error = function(message) {
                 console.log(message);
                 alert("Oopsie! " + message);
                 };
                 TTSClientPlugin.speak(response.body,success,error);
            });
        });
        //当无法连接到服务器(测试时模拟了服务器DOWN机)
        socket.onclose = function() {
    	    console.log('execute socket.onclose connection closed');
    	};
    }