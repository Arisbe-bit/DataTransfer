/**
 * 
 */
function connect() {
    var endpoint = '/datatransfer/wsrollups';
    var subscribeTopic = '/topic/rollups';
    var socket = new SockJS(endpoint);
    stompClient = Stomp.over(socket);
    stompClient.connect({}, function (frame) {
        console.log('Connected! frame : ' + frame);
        stompClient.subscribe(subscribeTopic, function (rollUpMsg) {
			var msg = JSON.parse(rollUpMsg.body);
            showStatus(msg);
        });
    });
}
function pingStatus(message) {
    var endpoint = '/app/rollups';
	var jSonMsg = JSON.stringify({'id':'','iClass':'','spanClass':'','message':message});
    console.log("sending message:[" + message + "] to endpoint:" + endpoint);
    stompClient.send(endpoint, {}, jSonMsg);
}
function showStatus(message) {
	var $id = $(message.id);
	var $idspan = $id.find("span");
	$id.removeClass().addClass(message.iClass);	
	$idspan.removeClass();
	if(message.spanClass.trim().length > 0){
		$idspan.addClass(message.spanClass);
	}	
	if(message.message.trim().length > 0) {
		PF('wvMessage').renderMessage({"summary": "RollUp process", "detail": message.message, "severity":"info"});
	}	
    console.log(message);
}
function year_onblur(e) {
    var message = '';
    if (isNaN(e.value)) {
        message = 'The year must be a number';
    } else {
        if (!(parseInt(e.value) >= 1998)) {
            message = 'The year must be bigger than 1998 ';
        }
    }
    if (message !== '') {
        $("#dialogMsgId").get(0).innerText = message;
        PF('dlgMessage').show();
        e.select();
        e.focus();
    }
}
$(window).on('load', function () {
    connect();
});
