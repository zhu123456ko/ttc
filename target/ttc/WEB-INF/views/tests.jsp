<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
NIHAOHAO ZLHUZHOU  TEST test<br/>
<c:forEach var="item" items="${paramValues.orderBy}">
	<c:set var="ob" value="${ob }{${item}}"/>
</c:forEach>

${param.orderBy==null?"none":ob} and ${param.as==null?"none":param.as}<br/>
徐娅，我爱你
</body>
</html>