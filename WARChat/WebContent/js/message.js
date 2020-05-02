function sendMessage() {
	let text = document.getElementById("txtMessage").value;
	let receiver = document.getElementById("selectUser").value;
	let sender = JSON.parse(sessionStorage.getItem('user'));
	
	if(receiver == "All users") {
		$.ajax({
			url: "rest/messages/all",
			type:"POST",
			data: JSON.stringify({text,sender}),
			contentType:"application/json",
			dataType:"json",
			complete: function(data) {
				console.log('Sent message to the server.');
			}
		});
	} else {
		$.ajax({
			url: "rest/messages/user/"+ receiver,
			type:"POST",
			data: JSON.stringify({text,sender}),
			contentType:"application/json",
			dataType:"json",
			complete: function(data) {
				console.log('Sent message to the server.');
			}
		});
	}
}

function showMessage(message) {
	let tr = $('<tr></tr>');
	let tdSender = $('<td>'+message.sender.username+'</td>');
	let tdSubject = $('<td>'+message.subject+'</td>');
	let tdText = $('<td>'+message.text+'</td>');
	let tdDatetime = $('<td>'+message.datetime+'</td>');
	tr.append(tdSender).append(tdSubject).append(tdText).append(tdDatetime);
	$('#messagesTable tbody').append(tr);
}

function showAllMessages() {
	let user = JSON.parse(sessionStorage.getItem('user'));
	let username = user.username;
	$.ajax({
		url:"rest/messages/"+username,
		type:"GET",
		success: function(messages) {
			$('#messagesTable tbody').html('');
			for(i = 0; i < messages.length; i++) {
				showMessage(messages[i]);				
			}
			
			$('#messagesTable').attr('hidden', false);
		},
		error: function() {
			alert('error');
		}
	});
}

