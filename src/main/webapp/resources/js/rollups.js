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
	var jSonMsg = JSON.stringify({'title':'info', 'severity':'info', 'message':message});
    console.log("sending message:[" + message + "] to endpoint:" + endpoint);
    stompClient.send(endpoint, {}, jSonMsg);
}
function showStatus(message) {
	
	if(message.message.trim().length > 0) {
		PF('wvMessage').renderMessage({"summary": message.title, "detail": message.message, "severity": message.severity});
	}
	// refresh every status from rollUp
	if(message.rollup){
		var rollup = message.rollup;
		var $checkbox = $(`#rollupForm\\:dt-rollup_${rollup.companyid}_checkbox`);
		var isProcessing = ( rollup.attribute1 === 'Processing' || 
						 rollup.attribute4 === 'Processing' ||
					     rollup.attribute5 === 'Processing' ||
						 rollup.attribute6 === 'Processing' ||
						 rollup.validations === 'Processing');
		updateProcessStatus(rollup.balanceValidationID, rollup.trialBalanceIcon, rollup.attribute1);
		updateProcessStatus(rollup.tradingPartnerValidationID, rollup.balanceValidationIcon, rollup.attribute4);
		updateProcessStatus(rollup.costCenterValidationID, rollup.costCenterValidationIcon, rollup.attribute5);
		updateProcessStatus(rollup.accountBalanceValidationID, rollup.validationsIcon, rollup.validations);
		updateProcessStatus(rollup.finishProcessID, rollup.finishedProcessIcon, rollup.attribute6);
		// Hide or show checkbox for prevent re-process.
		if(isProcessing) {
			$checkbox.removeClass('ui-chkbox-box');
		} else {
			$checkbox.addClass('ui-chkbox-box');
		}
	}

    console.log(message);
}

function updateProcessStatus(id, iclass, status) {
	console.log("updating values: id=" + id + ", iclass = "+ iclass + ", status = " + status );
	var $id = $('#' + id);
	console.log("$id=" + $id );
	var $span = $id.find("span");
	var spanclass = (iclass.trim() === '' ? '': 'sr-only');
	$id.removeClass().addClass(iclass);	
	$span.removeClass().addClass(spanclass);
	$span.get(0).innerText = status;

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
