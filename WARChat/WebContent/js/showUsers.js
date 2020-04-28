function showUserRegistered(user) {

	let tr = $('<tr></tr>');
	let tdUsername = $('<td>'+user.username+'</td>');
	tr.append(tdUsername);
	$('#registeredTable tbody').append(tr);
}

function showRegistered() {
	
	$.ajax({
		url:"rest/users/registered",
		type:"GET",
		success: function(users) {
			$('#registeredTable tbody').html('');
			for(i = 0; i < users.length; i++) {
				showUserRegistered(users[i]);
				
			}
			
			$('#registeredTable').attr('hidden', false);
		},
		error: function() {
			alert('error');
		}
	});
}

function showUserLoggedIn(user) {

	let tr = $('<tr></tr>');
	let tdUsername = $('<td>'+user.username+'</td>');
	tr.append(tdUsername);
	$('#loggedInTable tbody').append(tr);
}

function showLoggedIn() {
	
	$.ajax({
		url:"rest/users/loggedIn",
		type:"GET",
		success: function(users) {
			$('#loggedInTable tbody').html('');
			for(i = 0; i < users.length; i++) {
				showUserLoggedIn(users[i]);
				
			}
			
			$('#loggedInTable').attr('hidden', false);
		},
		error: function() {
			alert('error');
		}
	});
}
