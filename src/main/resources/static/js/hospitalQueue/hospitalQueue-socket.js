/**
*STOMP是一种简单的面向文本的消息协议.
*它定义了可互操作的有线格式,
*以便任何可用的STOMP客户端可以与任何STOMP消息代理进行通信,
*以便在语言和平台之间提供简单而广泛的消息互操作性
*/
;(function($){
	// set the version of the hospitalQueueSocket
	$.hospitalQueueSocket = {version: "2017.09.09.WEBSOCKET"};
	
	$.fn.hospitalQueueSocket = function(options) {
		return new $.hospitalQueueSocket(this, options);
	};
	
	$.hospitalQueueSocket = function (hospitalQueueSocket, options){
        /**jQuery.extend() 函数用于将一个或多个对象的内容合并到目标对象**/
		options = $.extend({}, $.hospitalQueueSocket.defaults, options);
		var self = this;
		var $hospitalQueueSocket = $(hospitalQueueSocket);
		var socket;
		var stompClient=null;
		var webSocketUrl=options.webSocketServerHost+options.serverEndpoint;
		/**$.data() 函数用于在指定的元素上存取数据，返回设置值**/
		$.data($hospitalQueueSocket, "hospitalQueueSocket", self);
		
		/**STOMP JavaScript客户端将使用ws://URL 与STOMP服务器通信**/
		
		/**创建STOMP客户端**/
	    function creatingASTOMPClient(){
			  /**
				*STOMP JavaScript客户端将使用ws://URL 与STOMP服务器通信
				*要创建一个STOMP客户端JavaScript对象,
				*您需要调用Stomp.client(url) 与服务器的WebSocket端点对应的URL
				*var url = "ws://localhost:61614/stomp";
				*var client = Stomp.client(url);
				*/
			socket = new SockJS(webSocketUrl);
            stompClient = Stomp.over(socket);
			
		}
		
		var disconnectTheCustomerServiceConnection = function(){
			/**
			*要断开客户端与服务器的连接您可以调用其disconnect()方法.
			*断开是异步的:当断开连接有效时通知,该disconnect方法采用可选callback参数
			*当客户端断开连接时,它不能再发送或接收消息.
			*client.disconnect(function() {
			*alert("See you next time!");
			*};
			*/
			console.log("断开连接时,调用disconnect方法,这个方法也是异步的,当断开成功后会接收一个额外的回调函数的参数");
		}
		
		var disconnectFromTheServer = function(){
			 console.log('execute function socket.onclose connection closed Disconnected from WebSocket server. It will reconnect after 10 seconds...');
		}
		
		/**连接异步完成:无法保证在connect返回呼叫时有效连接.
		  *要通知连接,需要将一个connect_callback函数传递 给该connect()方法
		  */
		var connect_callback = function() {
			/**called back after the client is connected and authenticated to the STOMP server**/
			clientSubscriptionDestinations();
		};
	    /**该connect()方法接受可选error_callback参数,如果客户端无法连接到服务器,则该参数将被调用.
		  *回调将被调用一个参数，对应于STOMP ERROR框的错误对象
		  */
	    var error_callback = function(error) {
			/**display the error's message header**/
			
			/**XMLHttpRequest cannot load ws://127.0.0.1:9999/hospitalQueue/info. 
			*Cross origin requests are only supported for protocol schemes: 
			*http, data, chrome, chrome-extension, https.
			**/
			/**
			* Whoops! Lost connection to undefined
			*/
			console.log(error);
	    };
		
		/**
		  *一旦创建了STOMP客户端,它必须调用其connect()方法来有效地连接和认证到STOMP服务器.
		  *该方法采用两个强制性参数,login以及passcode对应于用户凭据.
		  *连接异步完成:无法保证在connect返回呼叫时有效连接.要通知连接,
		  *需要将一个connect_callback函数传递 给该connect()方法.
		  */
		/**STOMP客户端通过发送CONNECT帧来启动到服务器的流或TCP连接**/
		function connectToTheSTOMPServer(){
			 /**
			  *client.connect(login, passcode, connectCallback);
			  *client.connect(login, passcode, connectCallback, errorCallback);
			  *client.connect(login, passcode, connectCallback, errorCallback, host);
			  *其中login，passcode是字符串，connectCallback并且errorCallback是函数
			  */
			 
			 /**client.connect(headers, connectCallback);**/
             /**client.connect(headers, connectCallback, errorCallback);**/
			 /**header是Map，connectCallback并且errorCallback是function**/
			 stompClient.connect(options.stompConnectheaders, connect_callback,error_callback);
			 /**
			  *断开连接时,调用disconnect方法,这个方法也是异步的,当断开成功后会接收一个额外的回调函数的参数
			  */
			 //stompClient.disconnect(disconnectTheCustomerServiceConnection);
			 /**add by mayijie at 2017.09.09**/
			 socket.onclose = disconnectFromTheServer;
		}
		
		function clientSubscriptionDestinations(){
			/**
			  *var subscription = client.subscribe("/queue/test", callback);
			  *默认情况下,如果标题中没有提供,则库将生成唯一的ID.要使用您自己的ID,请使用headers参数传递它
			  *var mysubid = '...';
			  *var subscription = client.subscribe(destination, callback, { id: mysubid });
			  */
			$.each(options.subscribeMessageTopic,function(identifier,messageTopic){
				subscribeToMessageTopics(messageTopic,callback);
			});
		}
        
		function subscribeToMessageTopics(messageTopic,callback){
			/**  
			  *var mysubid = '...';
              *var subscription = client.subscribe(destination, callback, { id: mysubid });
			  */
			console.log("subscribe message topic is "+messageTopic);
			var subscription = stompClient.subscribe(messageTopic,callback);
			
			
			/**
			  *要停止接收消息,客户端可以使用该unsubscribe()方法返回的对象上的subscribe()方法
			  *var subscription = client.subscribe(...);
			  *...
			  *subscription.unsubscribe();
			  */
		}
		
		/**add by mayijie at 2017.09.22 Message handling Factory**/
		var CallAPatient = (function () {
			// Instance stores a reference to the Singleton
			var instanceSingleton;
			function init() {
				// 单例
				// 私有方法和变量
				return {
					// 共有方法和变量
					handleMessage: function(messageBody) {
						$("#"+messageBody.roomCode+"Patient").html(messageBody.patientName);
					}
				};
			};
			return {
				// 如果存在获取此单例实例，如果不存在创建一个单例实例
				obtainInstance: function () {
					if (!instanceSingleton) {
						instanceSingleton = init();
					}
					return instanceSingleton;
				}
			};
		})();
		
		var ArrangeDoctors = (function () {
			// Instance stores a reference to the Singleton
			var instanceSingleton;
			function init() {
				// 单例
				// 私有方法和变量
				return {
					// 共有方法和变量
					handleMessage: function(messageBody) {
						var signInStatus=messageBody.signInStatus;
						if("20A" == signInStatus){
							$("#"+messageBody.roomCode+"Doctor").html(messageBody.doctorName);
						}else if("20X" == signInStatus){
							$("#"+messageBody.roomCode+"Doctor").html("");
						}
					}
				};
			};
			return {
				// 如果存在获取此单例实例，如果不存在创建一个单例实例
				obtainInstance: function () {
					if (!instanceSingleton) {
						instanceSingleton = init();
					}
					return instanceSingleton;
				}
			};
		})();
		
		/**抽象工厂**/
		var AbstractMessageHandlingFactory = (function () {
		    // Storage for our MessageType types
		    var types = {};
		    return {
		        obtainMessageType: function (msgType,options) {
		            var MessageType = types[msgType];
		            return (MessageType ? MessageType.obtainInstance().handleMessage(options) : null);
		        },
		        evaluateMessageType: function (msgType, messageType) {
		            var options = messageType.prototype;
		            types[msgType] = messageType;
		            return AbstractMessageHandlingFactory;
		        }
		    };
		})();

		AbstractMessageHandlingFactory.evaluateMessageType("callAPatient",CallAPatient);
		AbstractMessageHandlingFactory.evaluateMessageType("arrangeDoctors",ArrangeDoctors);
		
		var callback = function(message) {
			// called when the client receives a STOMP message from the server
			if (message) {
				
				var quote = $.hosptialQueueJudgmentCrossValue.jsonformatConvertToJSONObject(message);
				/**edit by mayijie at 2017.09.22 通过抽象工厂来实现业务逻辑**/
				var messageBody=$.hosptialQueueJudgmentCrossValue.jsonformatConvertToJSONObject(quote.body);
				var messageHeaders=$.hosptialQueueJudgmentCrossValue.jsonformatConvertToJSONObject(quote.headers);
				if(quote&&messageBody&&messageBody.msgType){
					AbstractMessageHandlingFactory.obtainMessageType(messageBody.msgType,messageBody);
				}
				/**
				 * Socket ack (acknowledgement)
				 * 客户端必须使用该message.ack()方法通知服务器它已经确认了该消息
				 * 
				 * **/
				/**
				 * Client.prototype.ack = function(messageID, subscription, headers) {
			     *******if (headers == null) {
			     ***********headers = {};
			     *******}
			     *******console.log(headers);
			     *******headers["message-id"] = messageID;
			     *******headers.subscription = subscription;
			     *******return this._transmit("ACK", headers);
				 * };
				 */
				message.ack({uuidOfMessage:messageHeaders.id});
			} else {
			  
			}
		}
		
		
		
		function execute(){
			creatingASTOMPClient();
			connectToTheSTOMPServer();
		}
		
		/**$.isFunction()函数用于判断指定参数是否是一个函数**/
		if( $.isFunction(options.init) ) options.init.apply(self, [$interrogationSpot, options]);
		
		execute();
	};
	
	$.hospitalQueueSocket.defaults = {
		/**后台服务调用使用的IP和PORT**/
		applicationServer:"http://127.0.0.1:9999/",
		/**WebSocket要连接的服务器 var url = "ws://localhost:61614/stomp";**/
		webSocketServerHost:"http://127.0.0.1:9999",
		/****/
		serverEndpoint:"/hospitalQueue",
		/****/
		stompConnectheaders:{"screenDevice": "171.19.231.2"},
		/****/
		subscribeMessageTopic:[]
	};

})(jQuery);