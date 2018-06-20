<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="../resources/libs/jquery/jquery-3.3.1.js"></script>
</head>
<body>
	<%=System.getProperty("webapp.root")%>
	<input id="btnk" value="10" />
	<input type="button" id="btn">
</body>
<script type="text/javascript">
	$(document).ready(function() {
		$("#btn").click(function() {
			$.ajax({
				type : 'POST',
				url : "../backstage/user/findByPage",
				data : {
					custCode : "1234"
				},
				success : function(result) {
					alert(JSON.stringify(result));
				},
				dataType : "json"
			});
			$("#btnk").val("hello")
		});
	});
</script>

</html>