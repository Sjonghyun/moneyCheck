<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="c"   uri="http://java.sun.com/jsp/jstl/core" %>
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

    <meta name="description" content="Source code generated using layoutit.com">
    <meta name="author" content="LayoutIt!">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
    <link href="/resources/css/style.css" rel="stylesheet">
    <link href="/resources/css/calendar.css" rel="stylesheet">
<%--    <link href="https://code.jquery.com/ui/1.11.4/themes/smoothness/jquery-ui.css" rel="stylesheet" type="text/css">--%>
    <link rel="stylesheet" href="/resources/css/datepicker.min.css">
</head>
<body onload="build();">
    <div class="row">
        <nav class="navbar navbar-expand-lg fixed-top">
            <div class="container-fluid">
                <a class="navbar-brand" href="/main">MONEYCHECK</a>
                <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarColor03" aria-controls="navbarColor03" aria-expanded="false" aria-label="Toggle navigation">
                    <span class="navbar-toggler-icon"></span>
                </button>
                <div class="collapse navbar-collapse" id="navbarColor03">
                    <ul class="navbar-nav me-auto">
                        <li class="nav-item">
                            <a class="nav-link active" href="#">기능1
                                <%--                                <span class="visually-hidden">(current)</span>--%>
                            </a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="#">기능2</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="#">기능3</a>
                        </li>
                    </ul>
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
                                        <a class="dropdown-item" href="#">연동하기</a>
                                        <a class="dropdown-item" href="#">다른기능?</a>
                                        <div class="dropdown-divider"></div>
                                        <a class="dropdown-item" href="/logout">로그아웃</a>
                                    </div>
                                <li class="nav-item last">
                                </li>
                                </li>
                            </ul>
                        </c:otherwise>
                    </c:choose>

                </div>
            </div>
        </nav>
        <div class="black">
        </div>

    </div>
    <c:if test="${!empty user}">
    <div class="row h-100">
        <div class="col-md-1 sidebar">
            <div class="black">
            </div>
            <ul class="nav flex-column">
                <li class="nav-item">
                    <a class="nav-link active" href="/excel?u_no=${user.u_no}">거래내역 엑셀 업로드</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link " href="#">보고서(그래프)</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="/insert_spend?u_no=${user.u_no}">가계부 쓰기</a>
                </li>
                <li class="nav-item dropdown">
                    <a class="nav-link dropdown-toggle" data-bs-toggle="dropdown" href="#" role="button" aria-haspopup="true" aria-expanded="false">Dropdown</a>
                    <div class="dropdown-menu">
                        <a class="dropdown-item" href="#">Action</a>
                        <a class="dropdown-item" href="#">Another action</a>
                        <a class="dropdown-item" href="#">Something else here</a>
                        <div class="dropdown-divider"></div>
                        <a class="dropdown-item" href="#">Separated link</a>
                    </div>
                </li>
            </ul>
            <h2>
                자산총액 나타날 곳
            </h2>

            <div>
                수입 = ********.
            </div>지출 = ********<span>.</span>

            <div>
                총액 = *******원
            </div>

            <p>
                <a class="btn" href="#"></a>
            </p>
        </div>

        </c:if>

        <c:choose>
            <c:when test="${!empty user}">
<%--            <script>--%>
<%--                /* 스케줄 등록시 등록완료 팝업창 */--%>
<%--                var message = "${message}";--%>
<%--                console.log(message);--%>
<%--                if (message != "") {--%>
<%--                    alert(message);--%>
<%--                }--%>
<%--            </script>--%>
                <div class="col-md-11 calendar">
                    <div class="text-center">
        <form name="calendarFrm" id="calendarFrm" action="" method="GET">
            <input type="hidden" name="year" value="${today_info.search_year}" />
            <input type="hidden" name="month" value="${today_info.search_month-1}" />
                        <div class="navigation">
                            <a class="before_after_year"
                               href="./main?year=${today_info.search_year-1}&month=${today_info.search_month-1}">
                                &lt;&lt; <!-- 이전해 -->
                            </a> <a class="before_after_month"
                                    href="./main?year=${today_info.before_year}&month=${today_info.before_month}">
                            &lt; <!-- 이전달 -->
                        </a> <span class="this_month"> &nbsp;${today_info.search_year}. <c:if
                                test="${today_info.search_month<10}">0</c:if>${today_info.search_month}
			            	</span> <a class="before_after_month"
                                    href="/main?year=${today_info.after_year}&month=${today_info.after_month}">
                            <!-- 다음달 --> &gt;
                        </a> <a class="before_after_year"
                                href="/main?year=${today_info.search_year+1}&month=${today_info.search_month-1}">
                            <!-- 다음해 --> &gt;&gt;
                        </a>
                        </div>

                    </div>
                    <div class="today_button_div">
                        <button type="button" class="buttonstyle"
                                onclick="javascript:location.href='/main'"
                                style="height: 30px; width: 80px;">Today</button>
<%--                        <button type="button"--%>
<%--                                class="buttonstyle board_move openMask_board_move pointer"--%>
<%--                                style="height: 30px; width: 130px;">Add Schedule</button>--%>
                    </div>
                    <table class="calendar_body">

                        <thead>
                        <tr bgcolor="#A0D9E2">
                            <td class="day sun">일</td>
                            <td class="day">월</td>
                            <td class="day">화</td>
                            <td class="day">수</td>
                            <td class="day">목</td>
                            <td class="day">금</td>
                            <td class="day sat">토</td>
                        </tr>
                        </thead>
                        <tbody>
                        <tr>
                            <c:forEach var="dateList" items="${dateList}"
                                       varStatus="date_status">
                            <c:choose>
                            <c:when test="${dateList.value=='today'}">
                            <c:if test="${date_status.index%7==0}">
                        <tr>
                            </c:if>
                            <td class="today">
                                <div class="date">
                                    </c:when>
                                    <c:when test="${date_status.index%7==6}">
                            <td class="sat_day">
                                <div class="sat">
                                    </c:when>
                                    <c:when test="${date_status.index%7==0}">
                        </tr>
                        <tr>
                            <td class="sun_day">
                                <div class="sun">
                                    </c:when>
                                    <c:otherwise>
                            <td class="normal_day">
                                <div class="date">
                                    </c:otherwise>
                                    </c:choose>
                                        ${dateList.date}
                                </div>
                                <div>
                                    <!-- 달력에 일정 띄우고 클릭 시 수정/삭제 창 띄우는 코드 -->
                                    <c:forEach var="scheduleList"
                                               items="${dateList.schedule_data_arr}"
                                               varStatus="schedule_data_arr_status">
1

                                        <a
                                                href="/schedule_show?schedule_date=${scheduleList.schedule_date}"
                                                onclick="window.open(this.href, '_blank', 'width=550,height=600,left=680%, top=200%, toolbars=no,scrollbars=no'); return false;"
                                                class="date_subject "
                                                style="color: ${scheduleList.schedule_mycolor}">${scheduleList.schedule_subject}</a>
                                        <br>

                                    </c:forEach>
                                </div>
                            </td>
                            </c:forEach>
                        </tbody>

                    </table>
             </form>
            </c:when>
        </c:choose>
    </div>
    <script src="/resources/js/jquery.min.js"></script>
    <script src="/resources/js/datepicker.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>
    <script src="/resources/js/scripts.js"></script>
</body>
</html>