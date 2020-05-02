function showUserRegistered(user) {

	let tr = $('<tr></tr>');
	let tdUsername = $('<td>'+user.username+'</td>');
	tr.append(tdUsername);
	$('#registeredTable tbody').append(tr);
}

function addToSelect(user) {
	let option = $('<option>'+user.username+'</option>');
	$('#selectUser').append(option);
}

function addUsersToSelect() {
	$.ajax({
		url:"rest/users/registered",
		type:"GET",
		success: function(users) {
			
			//za resetovanje select-a
			let length = document.getElementById('selectUser').options.length - 1;
		    for(i = length; i >= 0; i--) {
		    	document.getElementById('selectUser').remove(i);
		    }
		    
		    //postavljam opciju za All users nazad u select
		    let allusers = $('<option>All users</option>');
			$('#selectUser').append(allusers);
		    
			//dodajem sve usere
			for(i = 0; i < users.length; i++) {
				addToSelect(users[i]);
			}
		},
		error: function() {
			alert('error');
		}
	});
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
