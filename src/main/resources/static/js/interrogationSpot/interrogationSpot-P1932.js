//ready事件：
//ready事件在DOM结构绘制完成之后就绘执行。
//这样能确保就算有大量的媒体文件没加载出来，JS代码一样可以执行。
//load事件：
//load事件必须等到网页中所有内容全部加载完毕之后才被执行。
//如果一个网页中有大量的图片的话，则就会出现这种情况：
//网页文档已经呈现出来，但由于网页数据还没有完全加载完毕，
//导致load事件不能够即时被触发。
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
var stompClient = null;
function setConnected(connected) {
   
}
//$(function(){});
$(document).ready( function (){
	disconnect();
    /**屏幕的宽度 1024**/
	var screenWidth=document.documentElement?document.documentElement.clientWidth:document.body.clientWidth;
	/**屏幕的高度 768**/
	var screenHeight=document.documentElement?document.documentElement.clientHeight:document.body.clientHeight;
	console.log("screenHeight:"+screenHeight);
    console.log("screenWidth:"+screenWidth);
	
	var logoImglength=48*screenWidth/1024;
	
	$("#hospitalLogoImg").css("height",logoImglength+"px");
	$("#hospitalLogoImg").css("width",logoImglength+"px");
	var hospitalNameFontSize= 4*screenWidth/1024;
	$("#hospitalName").css("font-size",hospitalNameFontSize+"rem");
	
	var waitingAreaWidth=15*screenWidth/1024;
	$("#waitingArea").css("padding",waitingAreaWidth+"px");
	var waitingAreaFontSize=2*screenWidth/1024;
	$("#waitingArea").css("font-size",waitingAreaFontSize+"rem");

    var waitingAreaFooterHeight=$("#waitingAreaFooter").outerHeight();
	/**padding: 10px;**/
	/**margin-bottom: 20px;**/
    /**font-size:2rem;**/
	$("#callMessage").css("padding",10*screenHeight/1024+"px");
	$("#callMessage").css("margin-bottom",20*screenHeight/1024+"px");
	$("#consultingRoomList").css("margin-top",-20*screenHeight/1024+"px");
	$("#callMessage").css("font-size",2*screenWidth/1024+"rem");
    var pageLogoHeight=$('#pageLogo').outerHeight();
	
	$('#mainHospitalqueueV').css("padding-top",pageLogoHeight+"px");
	$("#mainHospitalqueueV").css("vertical-align","middle");
	//设置bootstrapTable起始的高度
	var callContentsHeight=$("#callContents").outerHeight();
	var windowHeight=screenHeight-callContentsHeight-pageLogoHeight-waitingAreaFooterHeight;
	var trHeight=windowHeight/7;
	$("#mainHospitalqueueV").find("tr").each(function(){
		$(this).css("height",trHeight+"px");
	});
	$("#mainHospitalqueueV").find("th").each(function(){
	    //    vertical-align: middle;
		$(this).css("vertical-align","middle");
	});
	$('#consultingRoom').bootstrapTable({height:windowHeight}); 
});
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
    //STOMP JavaScript客户端将使用URL为 ws:// 与STOMP server建立通信
	var socket = new SockJS('/hospitalQueue');
    stompClient = Stomp.over(socket);
    //client.connect(login, passcode, connectCallback);
    //client.connect(login, passcode, connectCallback, errorCallback);
    //client.connect(login, passcode, connectCallback, errorCallback, host);
    // 心跳检测heart-beating
    //如果STOMP 代理接收的帧是STOMP 1.1协议时，默认心跳检测是开启的。
    //客户端client对象有一个字段 heartbeat ,通过改变incoming和outgoing数值，来配置心跳频率（默认频率值为：10000ms）
	//客户端每20000ms发送一次心跳检测
    //client.heartbeat.outgoing = 20000; 
	//client不接收serever端的心跳检测
	//client.heartbeat.incoming = 0; 
	//heart-beating是利用window.setInterval()去规律地发送heart-beats或者检查服务端的heart-beats
    //传递参数
    var connectionParameter={
    	"screenDevice": "SD987JH"
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
        stompClient.subscribe('/screenDevice/171.19.231.2/subscribe', function (response) {
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