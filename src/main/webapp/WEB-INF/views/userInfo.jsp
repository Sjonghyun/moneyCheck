<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<html>
<head>
    <title>Insert title here</title>
</head>
<body>
<sec:authentication property="principal" var="session"/>
${session}
</body>
</html>