var stompClient = null;

function connect() {	
	/**
     * 向服务器发送一个ping包
     */
	var pingToServer = function () {
        String.prototype.getBytes = function() {
            var bytes = [];
            for(var i=0;i < this.length;i++){
                var charCode=this.charCodeAt(i);
                var cLen=Math.ceil(Math.log(charCode)/Math.log(256));
                for(var j=0;j < cLen;j++){
					bytes.push((charCode << (j*8))&0xFF);
                }
            }
            return bytes;
        };

        var payload = 'i';
        var buffer = new ArrayBuffer(payload.length);
        var intView = new Int8Array(buffer);
        for(var i = 0; i < intView.length; i++) {
            intView[i] = payload.getBytes()[i];
        }
        stompClient.send("/app/messageForwarding.websocket", {}, JSON.stringify({'name': "hreatkeep","device":"127.0.0.1"}));
	    };
   
     /**
     * 心跳检测
     * 若30秒内没有接收到任何来自服务器的信息，则向服务器发起一个ping包
     * @type {{timeout: number, timeoutObj: null, serverTimeoutObj: null, reset: reset, start: start}}
     */
    var heartCheck = {
        timeout: 20000, //计时器设定为20s
        timeoutObj: null,
        serverTimeoutObj: null,
        reset: function() {
            clearTimeout(this.timeoutObj);
            clearTimeout(this.serverTimeoutObj);
            this.start();
        },
        start: function() {
            var self = this;
            this.timeoutObj = setTimeout(function() {
                //向服务器发送ping消息
                pingToServer();
                //计算答复的超时时间
                self.serverTimeoutObj = setTimeout(function() {
                    //如果调用onclose会执行reconnect，导致重连两次，因此直接调用close()关闭连接
                    //socket.close();
                }, self.timeout);
            }, this.timeout);
        }
    };
	//var sockjs = new SockJS(url, _reserved, options);
	//sockjs.min.js:27 Uncaught Error: Only basic urls are supported in SockJS
	var socket = new SockJS('/hospitalQueue');
	//建立一个基于Web Socket的连接到STOMP代理
	//Simple (or Streaming) Text Orientated Messaging Protocol
	//STOMP即Simple (or Streaming) Text Orientated Messaging Protocol,简单(流)文本定向消息协议
	//http://jmesnil.net/stomp-websocket/doc/
    stompClient = Stomp.over(socket);
    //client.connect(login, passcode, connectCallback);
    //client.connect(login, passcode, connectCallback, errorCallback);
    //client.connect(login, passcode, connectCallback, errorCallback, host);
    //传递参数
    var connectionParameter={
    	"screenDevice": "127.0.0.1"
    };
    
    stompClient.connect(connectionParameter, function (frame) {
        console.log('Connected:' + frame);
        console.log("execute function socket.onopen 心跳检测启动");
	    //heartCheck.start();
	    stompClient.subscribe('/callTheName/demo', function (response) {
	    	// and acknowledge it
	    	 //heartCheck.reset();
	    	console.log(response);
	        response.ack();
	    	
	    });
    },function(error){
    	console.log(error);
    });
    //当无法连接到服务器(测试时模拟了服务器DOWN机)
    socket.onclose = function() {
	    console.log('execute function socket.onclose connection closed Disconnected from WebSocket server. It will reconnect after 10 seconds...');
	    // 10秒后重新连接，实际效果：每10秒重连一次，直到连接成功
        setTimeout(function () {
        	connect();
        }, 10000);
	};
}

function disconnect() {
    if (stompClient != null) {
        stompClient.disconnect();
    }
    console.log('Disconnected');
}

function sendName() {
    var name = $('#name').val();
    var device = $('#device').val();
    stompClient.send("/app/messageForwarding.websocket", {}, JSON.stringify({'name': name,'device':device}));
}

function showResponse(message) {
    $("#response").html(message);
}