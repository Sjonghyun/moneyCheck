<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<sec:authentication property="principal" var="session"/>
<c:if test="${session ne 'anonymousUser'}">
    <sec:authentication property="principal.authInfo" var="user"/>
</c:if>
<!DOCTYPE html>
<html>
<head>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
    <link rel="stylesheet" href="/resources/css/datepicker.min.css">
    <link href="/resources/css/chat.css" rel="stylesheet">
    <link href="/resources/css/style.css" rel="stylesheet">
    <link rel="preconnect" href="https://fonts.googleapis.com">

    <meta charset="UTF-8">
    <title>Chating</title>
</head>
<body onload="chatName()">
<jsp:include page="header.jsp"></jsp:include>
<jsp:include page="sidebar.jsp"></jsp:include>
<div class="col m-10 mz-0">
<div class="black"></div>
    <div id="container" class="container">
        <div class="mz-0 text-center">가계부를 같이 쓰고 있는 모든 사람과 대화할 수 있습니다.</div>
        <div class="black"></div>
        <input type="hidden" name="book_no" id="book_no" value="${param.book_no}">
        <input type="hidden" id="sessionId" value="">
        <input type="hidden" id="u_no" value="${param.u_no}">
        <input type="hidden" id="roomNumber" value="${roomNumber}">
        <div id="chating" class="chating">
            <c:forEach var="messageList" items="${messageList}">
                <c:choose>
                    <c:when test="${param.u_no eq messageList.u_no}">
                        <li class='me'><div class="userName">${messageList.name}</div></li>
                        <li class='me'><div class="message">${messageList.message}</div></li>
                    </c:when>
                    <c:otherwise>
                        <li class='others'><div class="userName">${messageList.name}</div> </li>
                        <li class='others'><div class="message">${messageList.message}</div> </li>
                    </c:otherwise>
                </c:choose>
            </c:forEach>
        </div>

        <div id="yourName">
            <table class="inputTable">
                <tr>
                    <th>사용자명</th>
                    <th><input type="hidden" name="userName" id="userName" value="${user.u_name}"></th>
                    <th><button onclick="chatName()" id="startBtn">이름 등록</button></th>
                </tr>
            </table>
        </div>
        <div id="yourMsg">
                <tr>
                    <th><input id="chatting" placeholder="보내실 메시지를 입력하세요."></th>
                    <th><button onclick="send()" id="sendBtn" class="btn btn-secondary btn-sm">보내기</button></th>
                </tr>
        </div>
    </div>
</div>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<script src="/resources/js/chat.js"></script>
<script src="/resources/js/scripts.js"></script>
<script src="/resources/js/jquery.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>

</body>
</html>