<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="c"   uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<html lang="en">
<sec:authentication property="principal" var="session"/>
<c:if test="${session ne 'anonymousUser'}">
    <sec:authentication property="principal.authInfo" var="user"/>
</c:if>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <title></title>

    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
    <link href="/resources/css/style.css" rel="stylesheet">
    <link href="/resources/css/calendar.css" rel="stylesheet">
    <link href="https://code.jquery.com/ui/1.11.4/themes/smoothness/jquery-ui.css" rel="stylesheet" type="text/css">
    <link rel="stylesheet" href="/resources/css/datepicker.min.css">
    <link rel="preconnect" href="https://fonts.googleapis.com">
</head>
<body>
<div class="row">
    <nav class="navbar navbar-expand-lg fixed-top">
        <div class="container-fluid">
            <a class="navbar-brand logo" href="/main?book_no=${param.book_no}">MONEYCHECK</a>
                <c:choose>
                    <c:when test="${empty user}" >
                        <ul class="navbar-nav ml-md-auto">
                            <li class="nav-item dropdown">
                                <a class="nav-link dropdown-toggle" data-bs-toggle="dropdown" href="#" role="button" aria-haspopup="true" aria-expanded="false">login</a>
                                <div class="dropdown-menu">
                                    <a class="nav-link p-2 sns_button" href="/oauth2/authorization/naver" aria-label="naver"><img src="/resources/img/sns_naver.png"></a>
                                    <a class="nav-link p-2 sns_button" href="/oauth2/authorization/kakao" aria-label="kakao"><img src="/resources/img/sns_kakao.jpg"></a>
                                    <a class="nav-link p-2 sns_button" href="/oauth2/authorization/google" aria-label="google"><img src="/resources/img/sns_google.png"></a>
                                </div>
                            </li>
                            <li class="nav-item last">
                            </li>
                        </ul>
                    </c:when>
                    <c:otherwise>
                        <ul class="navbar-nav ml-md-auto">
                            <li class="nav-item dropdown">
                                <a class="nav-link dropdown-toggle" data-bs-toggle="dropdown" href="#" role="button" aria-haspopup="true" aria-expanded="false">${user.u_name}</a>
                                <div class="dropdown-menu">
                                    <a class="dropdown-item" href="#">${user.u_mail}</a>
                                    <a class="dropdown-item" href="#" onclick="invitePopup()">회원 초대하기</a>
                                    <a class="dropdown-item" href="/moveChating?roomNumber=${param.book_no}&roomName=${user.u_name}&u_no=${user.u_no}&book_no=${param.book_no}">채팅하기</a>
                                    <div class="dropdown-divider"></div>
                                    <a class="dropdown-item" href="/logout">로그아웃</a>
                                    <a class="dropdown-item" href="/deleteUser?u_no=${user.u_no}">탈퇴하기</a>
                                </div>
                            <li class="nav-item last">
                            </li>
                            </li>
                        </ul>
                    </c:otherwise>
                </c:choose>
            </div>
    </nav>
</div>
</body>
</html>