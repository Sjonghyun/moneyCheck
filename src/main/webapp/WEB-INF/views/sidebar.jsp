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

    <title>메인화면</title>

    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
    <link href="/resources/css/style.css" rel="stylesheet">
    <link href="/resources/css/calendar.css" rel="stylesheet">
    <link href="https://code.jquery.com/ui/1.11.4/themes/smoothness/jquery-ui.css" rel="stylesheet" type="text/css">
    <link rel="stylesheet" href="/resources/css/datepicker.min.css">
</head>
<body>
<c:if test="${!empty user}">

<div class="row h-100 w-100">
    <div class="col-md-2 sidebar">
        <div class="black">
        </div>
        <div class="sideDiv"></div>
        <div class="sideDiv"></div>
        <div class="sideDiv"></div>
        <ul class="nav flex-column">
            <li class="nav-item dropdown">
                <a class="nav-link dropdown-toggle" data-bs-toggle="dropdown" href="#" role="button" aria-haspopup="true" aria-expanded="false">엑셀 업로드</a>
                <div class="dropdown-menu">
                    <a class="dropdown-item" href="#" onclick="spendPopup()">지출내역 업로드</a>
                    <a class="dropdown-item" href="#" onclick="incomePopup()">수입내역 업로드</a>
                </div>
            </li>
            <div class="sideDiv"></div>
            <li class="nav-item dropdown">
                <a class="nav-link dropdown-toggle" data-bs-toggle="dropdown" href="#" role="button" aria-haspopup="true" aria-expanded="false">보고서</a>
                <div class="dropdown-menu">
                    <a class="dropdown-item" href="/totalChart?year=${today_info.search_year}&month=${today_info.search_month-1}&book_no=${param.book_no}">전체 그래프</a>
                    <a class="dropdown-item" href="/spChart?year=${today_info.search_year}&month=${today_info.search_month-1}&book_no=${param.book_no}">지출 그래프</a>
                    <a class="dropdown-item" href="/inChart?year=${today_info.search_year}&month=${today_info.search_month-1}&book_no=${param.book_no}">수입 그래프</a>
                </div>
            </li>
            <div class="sideDiv"></div>
            <li class="nav-item dropdown">
                <a class="nav-link dropdown-toggle" data-bs-toggle="dropdown" href="#" role="button" aria-haspopup="true" aria-expanded="false">가계부 작성</a>
                <div class="dropdown-menu">
                    <a class="dropdown-item" href="/insert_spend?book_no=${user.book_no}&year=${today_info.search_year}&month=${today_info.search_month-1}">지출 내역</a>
                    <a class="dropdown-item" href="/insert_income?book_no=${user.book_no}&year=${today_info.search_year}&month=${today_info.search_month-1}">수입 내역</a>
                </div>
            </li>
            <div class="sideDiv"></div>
            <li class="nav-item dropdown">
                <a class="nav-link dropdown-toggle" data-bs-toggle="dropdown" href="#" role="button" aria-haspopup="true" aria-expanded="false">가계부 회원목록</a>
                <div class="dropdown-menu">
                    <c:forEach var="usersList" items="${usersList}">
                        <c:choose>
                            <c:when test="${usersList.u_no eq user.u_no}">
                                <a class="dropdown-item" href="#">나:${usersList.u_name}(${usersList.u_mail})</a>
                            </c:when>
                            <c:otherwise>
                                <a class="dropdown-item" href="#">${usersList.u_name}(${usersList.u_mail})</a>
                            </c:otherwise>
                        </c:choose>
                    </c:forEach>
                    <div class="dropdown-divider"></div>
                    <a class="dropdown-item" href="#" onclick="invitePopup()">회원 초대하기</a>
                    <a class="dropdown-item" href="/moveChating?roomNumber=${param.book_no}&roomName=${user.u_name}&u_no=${user.u_no}&book_no=${param.book_no}">채팅하기</a>
                </div>
            </li>
        </ul>
</div>
</c:if>
</body>
</html>