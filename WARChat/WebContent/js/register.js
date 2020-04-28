function register() {
	let username = $('#username').val();
	let password = $('#password').val();
	
	$.ajax({
		url:"rest/users/register",
		type:"POST",
		data:JSON.stringify({username,password}),
		contentType:"application/json",
		success: function(data) {
			if(data == undefined){
				alert('error');
			} else {
				alert('Registration successful!' +' '+ data);
				window.location.href = "login.html";
			}
		}
	});
}

function login() {
	let username = $('#username').val();
	let password = $('#password').val();
	
	$.ajax({
		url:"rest/users/login",
		type:"POST",
		data:JSON.stringify({username,password}),
		contentType:"application/json",
		success: function(user) {
			if(user == undefined){
				alert('error');
			} else {
				sessionStorage.setItem('user', JSON.stringify(user));
				window.location.href = "index.html";
			}
		}
	});
}

function logout() {
	let user = JSON.parse(sessionStorage.getItem('user'));
	let username = user.username;

	alert(username);
	$.ajax({
		url:"rest/users/loggedIn/" + username,
		type:"DELETE",
		success: function() {
			sessionStorage.removeItem('user');
			window.location.href = 'login.html';	
		},
		error: function() {
			alert('error');
		}
	});
}



