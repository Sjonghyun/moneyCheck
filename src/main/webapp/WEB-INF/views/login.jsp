<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
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
           <a class="navbar-brand logo" href="/login">MONEYCHECK</a>
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
        </div>
    </nav>
</div>
<div class="col-md-11 calendar">
    <div class="black"></div>
    <div class="text-center">
        <form name="calendarFrm" id="calendarFrm" action="" method="GET">
            <input type="hidden" name="year" value="${today_info.search_year}" />
            <input type="hidden" name="month" value="${today_info.search_month-1}" />
            <div class="navigation">
                <a class="before_after_year"
                   href="/login?year=${today_info.search_year-1}&month=${today_info.search_month-1}">
                    &lt;&lt; <!-- 이전해 -->
                </a> <a class="before_after_month"
                        href="/login?year=${today_info.before_year}&month=${today_info.before_month}">
                &lt; <!-- 이전달 -->
            </a> <span class="this_month"> &nbsp;${today_info.search_year}년
                            <c:if test="${today_info.search_month<10}">0</c:if>${today_info.search_month}월
			            	</span> <a class="before_after_month"
                                       href="/login?year=${today_info.after_year}&month=${today_info.after_month}">
                <!-- 다음달 --> &gt;
            </a> <a class="before_after_year"
                    href="/login?year=${today_info.search_year+1}&month=${today_info.search_month-1}">
                <!-- 다음해 --> &gt;&gt;
            </a>
            </div>
            <div class="today_button_div">
                <button type="button" class="buttonstyle"
                        onclick="javascript:location.href='/login'"
                        style="height: 30px; width: 80px;">Today
                </button>
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
                    </td>
                </c:forEach>
                </tbody>
            </table>
        </form>
    </div>
    <script src="http://ajax.googleapis.com/ajax/libs/jquery/1.7.1/jquery.min.js"></script>
    <script src="http://code.jquery.com/ui/1.8.18/jquery-ui.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>
    <script src="/resources/js/scripts.js"></script>

</body>
</html>