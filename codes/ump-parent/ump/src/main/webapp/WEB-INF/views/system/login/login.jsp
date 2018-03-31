<%@ page contentType="text/html;charset=UTF-8"%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<title>UMP</title>
<meta charset="UTF-8" />
<meta http-equiv="content-type" content="text/html;charset=utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=Edge">
<meta content="always" name="referrer">
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<script type="text/javascript" src="<%=request.getContextPath()%>/resources/libs/jquery/jquery-3.3.1.js"></script>
</head>
<body class="login-layout login-bg">
	<div class="box">
		<div class="login-box">
			<div class="login-title text-center">
				<h1>
					<small>登录</small>
				</h1>
				<form action="/login/login" method="post">
					<input type="text" id="username" name="username" class="form-control" placeholder="用户名"> <input type="text"
						id="password" name="password" class="form-control" placeholder="密码"> 
						<input id="submitBtn" type="submit" value="submit">
				</form>
			</div>
		</div>
	</div>
	<script>
		$(function() {
			$("#submitBtn").click(function() {
				var params = {"username:":'aaa'};
				$.ajax({
					type : "POST",
					url : "login",
					data : {
						params : params
					},
					success : function(e) {

					}
				});
			});
		});
	</script>
</body>
</html>