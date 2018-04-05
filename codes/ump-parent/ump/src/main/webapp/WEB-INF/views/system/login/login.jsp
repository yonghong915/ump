<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8" />
<title>首页案例</title>
<!--引入bootstrap的JS，CSS文件-->
<script charset="utf8" src="../resources/libs/jquery/jquery-3.3.1.min.js"></script>

</head>
<body>
	<div>
	   <form action="<%=request.getContextPath()%>/login/login" method="post">
	      username: <input type="text" name="userName"/>
	      password: <input type="text" name="userPwd"/>
	      <input type="submit" value="submit"/>
	   </form>
	
	</div>
</body>
</html>
