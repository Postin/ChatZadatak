function register() {
	let username = $('#username').val();
	let password = $('#password').val();
	
	$.ajax({
		url:"rest/users/register",
		type:"POST",
		data:JSON.stringify({username,password}),
		contentType:"application/json",
		success: function(data) {
			alert(data);
			if(data == undefined){
				alert('error');
			} else {
				alert('Registration successful!')
				window.location.href = "login.html"
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
			alert(user);
			if(user == undefined){
				alert('error');
			} else {
				localStorage.setItem('user', JSON.stringify(user));
				window.location.href = "index.html";
			}
		}
	});
}

function logout() {
	let user = JSON.parse(localStorage.getItem('user'));
	alert(user);
	
	$.ajax({
		url:"rest/users/login",
		type:"DELETE",
		data:{user:user},
		contentType:"application/json",
		success: function(data) {
			alert(data);
		},
		error: function() {
			alert('error');
		}
	});
	
}