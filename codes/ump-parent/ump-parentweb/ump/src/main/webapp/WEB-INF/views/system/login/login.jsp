<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<!--指定当前页面的字符编码-->
<meta charset="utf-8">
<!--如果是IE，会使用最新的渲染引擎进行渲染-->
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<!--标准的视口设置-->
<meta name="viewport" content="width=device-width, initial-scale=1,user-scalable=0">
<title><fmt:message key="projectTitle" /></title>
<!--引入bootstrap的JS，CSS文件-->
<link rel="icon" href="../resources/images/favicon.ico" type="image/x-icon" />
<link rel="shortcut icon" href="../resources/images/favicon.ico" />
<script type="text/javascript" data-main="../resources/libs/comm/require-config" src="../resources/libs/comm/require.js"></script>
<script type="text/javascript" src="../resources/libs/comm/test.js"></script>
</head>
<body>
	<div>
		<form action="<%=request.getContextPath()%>/login/login" method="post">
			username: <input type="text" name="userName" />
			 password: <input type="text" name="userPwd" />
			 <input id="loginBtn" type="button" value="submit"/>
		</form>

	</div>
</body>
</html>
