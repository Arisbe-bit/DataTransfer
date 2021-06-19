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
		// var $checkbox = $(`#rollupForm\\:dt-rollup_${rollup.companyid}_checkbox`);
		var $checkbox = $(PrimeFaces.escapeClientId(`rollupForm:dt-rollup_${rollup.companyid}_checkbox`));
		var isProcessing = ( rollup.attribute1 === 'Processing' || 
						 rollup.attribute4 === 'Processing' ||
					     rollup.attribute5 === 'Processing' ||
						 rollup.attribute6 === 'Processing' ||
						 rollup.validations === 'Processing');

		// Update status for each process...
		updateProcessStatus(rollup.balanceValidationID, rollup.trialBalanceIcon, rollup.attribute1);
		updateProcessStatus(rollup.tradingPartnerValidationID, rollup.balanceValidationIcon, rollup.attribute4);
		updateProcessStatus(rollup.costCenterValidationID, rollup.costCenterValidationIcon, rollup.attribute5);
		updateProcessStatus(rollup.accountBalanceValidationID, rollup.validationsIcon, rollup.validations);
		updateProcessStatus(rollup.finishProcessID, rollup.finishedProcessIcon, rollup.attribute6);

		// Update the process dates..
		updateDate(rollup.iniDateId, rollup.initdate);
		updateDate(rollup.finDateId, rollup.findate);
		
		// Hide or show checkbox for prevent re-process.
		if(isProcessing) {
			$checkbox.removeClass('ui-chkbox-box');
		} else {
			$checkbox.addClass('ui-chkbox-box');
		}
	}
	if(message.processFinished) {
		playMP3();
	}
    console.log(message);
}

function updateProcessStatus(id, iclass, status) {
	console.log("updating values: id=" + id + ", iclass = "+ iclass + ", status = " + status );
	var $id = $('#' + id);
	var $span = $id.find("span");
	var spanclass = (iclass.trim() === '' ? '': 'sr-only');
	$id.removeClass().addClass(iclass);	
	$span.removeClass().addClass(spanclass);
	$span.get(0).innerText = status;
}
/**
 */
function updateDate(id, date) {
	console.log('About to update date for ' + id + 'with value = ' + date);
	var $date = $('#' + id);	
	if($date.get(0)) {
		$date.get(0).innerText = date;
	}
}

function playMP3() {
	var $audioMP3 =$(PrimeFaces.escapeClientId('rollupForm:alertMP3_audio'));
	if($audioMP3.get(0)) {
		$audioMP3.get(0).play();		
	}	
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
