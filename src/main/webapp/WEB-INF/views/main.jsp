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

    <meta name="description" content="Source code generated using layoutit.com">
    <meta name="author" content="LayoutIt!">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
    <link href="/resources/css/style.css" rel="stylesheet">
    <link href="/resources/css/calendar.css" rel="stylesheet">
    <link href="https://code.jquery.com/ui/1.11.4/themes/smoothness/jquery-ui.css" rel="stylesheet" type="text/css">
    <link rel="stylesheet" href="/resources/css/datepicker.min.css">
    <link rel="preconnect" href="https://fonts.googleapis.com">
</head>
<body>
<jsp:include page="header.jsp"></jsp:include>
<jsp:include page="sidebar.jsp"></jsp:include>

        <c:choose>
            <c:when test="${!empty user}">
    <div class="col-md-11 calendar">
        <div class="text-center">
        <form name="calendarFrm" id="calendarFrm" action="" method="GET">
            <input type="hidden" name="year" value="${today_info.search_year}" />
            <input type="hidden" name="month" value="${today_info.search_month-1}" />
                    <div class="navigation">
                        <a class="before_after_year" href="/main?year=${today_info.search_year-1}&month=${today_info.search_month-1}&book_no=${param.book_no}">
                            &lt;&lt;
                        </a>
                        <a class="before_after_month" href="/main?year=${today_info.before_year}&month=${today_info.before_month}&book_no=${param.book_no}">
                            &lt;
                        </a>
                        <span class="this_month"> &nbsp;${today_info.search_year}년
                        <c:if test="${today_info.search_month<10}">
                            0
                        </c:if>
                                ${today_info.search_month}월
                        </span>
                        <a class="before_after_month" href="/main?year=${today_info.after_year}&month=${today_info.after_month}&book_no=${param.book_no}">
                            &gt;
                        </a>
                        <a class="before_after_year" href="/main?year=${today_info.search_year+1}&month=${today_info.search_month-1}&book_no=${param.book_no}">
                            &gt;&gt;
                        </a>
                    </div>
                    <div class="today_button_div">
                        <button type="button" class="buttonstyle" onclick="javascript:location.href='/main?book_no=${param.book_no}'" style="height: 30px; width: 80px;">Today</button>
                        <button type="button" class="buttonstyle board_move openMask_board_move pointer" style="height: 30px; width: 130px;">Add Schedule</button>
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
                            <c:forEach var="dateList" items="${dateList}" varStatus="date_status">
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
                                    <c:set var="spResult" value="0"/>
                                    <c:set var="inResult" value="0"/>
                                    <c:forEach var="spendList" items="${dateList.spending_data_arr}" varStatus="status">
                                        <c:if test="${!empty spendList}">
                                            <c:set var="count" value="${count +1}"/>
                                            <c:set var="spResult" value="${spResult + count}"/>
                                            <c:set var="count" value="0"/>
                                        </c:if>
                                    </c:forEach>
                                    <c:if test="${spResult ne 0}">
                                        <div>
                                            <span class="date_subject">지출 ${spResult}건</span>
                                        </div>
                                    </c:if>
                                    <c:forEach var="incomeList" items="${dateList.income_data_arr}" varStatus="status">
                                        <c:if test="${!empty incomeList}">
                                            <c:set var="count" value="${count +1}"/>
                                            <c:set var="inResult" value="${inResult + count}"/>
                                            <c:set var="count" value="0"/>
                                        </c:if>
                                    </c:forEach>
                                    <c:if test="${inResult ne 0}">
                                       <div>
                                            <span class="date_subject">수입 ${inResult}건</span>
                                       </div>
                                    </c:if>
                                    <c:forEach var="scheduleList" items="${dateList.schedule_data_arr}" varStatus="schedule_data_arr_status">
                                        <a href="/schedule_show?schedule_idx=${scheduleList.schedule_idx}&book_no=${user.book_no}"
                                                onclick="window.open(this.href, '_blank', 'width=550,height=600,left=680%, top=200%, toolbars=no,scrollbars=no'); return false;"
                                           class="date_subject">
                                                ${scheduleList.schedule_subject}
                                        </a>
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
    <script src="http://ajax.googleapis.com/ajax/libs/jquery/1.7.1/jquery.min.js"></script>
        <script src="http://code.jquery.com/ui/1.8.18/jquery-ui.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>
    <script src="/resources/js/scripts.js"></script>
            <div id="mask_board_move"></div>
            <div class="normal_move_board_modal">
            <div class="top" style="">
                <div class="close">x</div>
                <div class="subject">일정 추가</div>
            </div>

            <div class="bottom">
                <form name="schedule_add" action="schedule_add.do">
                    <input type="hidden" name="year" value="${today_info.search_year}" />
                    <input type="hidden" name="month"
                           value="${today_info.search_month-1}" />
                    <div class="contents">
                        <input type="hidden" name="book_no" id="book_no" value="${user.book_no}">
                        <ul>
                            <li>
                                <div class="text_subject">날짜 :</div>
                                <div class="text_desc">
                                    <input type="text" name="schedule_date" class="text_type1"
                                           id="testDatepicker" readonly="readonly" />
                                </div>
                            </li>
                            <li>
                                <div class="text_subject">제목 :</div>
                                <div class="text_desc">
                                    <input type="text" name="schedule_subject" class="text_type1" />
                                </div>
                            </li>
                            <li style="margin-bottom: 70px;">
                                <div class="text_subject">내용 :</div>
                                <div class="text_area_desc">
                                    <textarea name="schedule_desc" class="textarea_type1" rows="5"></textarea>
                                </div>
                            </li>
                            <li></li>
                            <li></li>
                            <li></li>
                            <li></li>
                            <li></li>
                            <li class="button_li">
                                <button type="button" class="buttonstyle board_move_go pointer" onclick="scheduleAdd();">일정추가</button>
                            </li>
                        </ul>
                    </div>
                </form>
            </div>
            </div>
        </div>
<script>
    var message = "${message}";
    console.log(message);
    if (message != "") {
        alert(message);
    }
</script>
</body>
</html>