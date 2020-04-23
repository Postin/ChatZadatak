function register() {
	let username = $('#username').val();
	let password = $('#password').val();
	
	$.ajax({
		url:"rest/users/register",
		type:"POST",
		data:JSON.stringify({username,password}),
		contentType:"application/json",
		success: function(data) {
			alert(JSON.stringify(data));
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
			alert(JSON.stringify(user));
			if(user == undefined){
				alert('error');
			} else {
				localStorage.setItem('user', JSON.stringify(user));
				window.location.href = "index.html";
			}
		}
	});
}