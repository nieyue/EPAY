<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="ISO-8859-1"%>
<%--<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>--%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>支付结果</title>
</head>
<body>


<%
 String SENDURL=(String)request.getAttribute("url");
System.out.println(SENDURL);
%> 
	<div align="center" style="font-weight: bold;">
			<form id="kqPay" name="kqPay" action="<%=SENDURL %>" method="post">
			</form>
	</div>

</body>
 <script type="text/javascript">
    var frm = document.getElementById("kqPay");
	frm.submit();
</script>
</html>