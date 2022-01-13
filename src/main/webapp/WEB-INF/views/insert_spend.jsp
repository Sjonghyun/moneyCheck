<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
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
<jsp:include page="header.jsp"></jsp:include>
<jsp:include page="sidebar.jsp"></jsp:include>

        <div class="col-md-10 mz-0">
            <div class="black"></div>
            <div class="navigation">
                <a class="before_after_year"
                   href="/insert_spend?year=${today_info.search_year-1}&month=${today_info.search_month-1}&book_no=${param.book_no}">
                    &lt;&lt; <!-- 이전해 -->
                </a> <a class="before_after_month"
                        href="/insert_spend?year=${today_info.before_year}&month=${today_info.before_month}&book_no=${param.book_no}">
                &lt; <!-- 이전달 -->
            </a> <span class="this_month"> &nbsp;${today_info.search_year}년
                                <c:if test="${today_info.search_month<10}">0</c:if>${today_info.search_month}월
                                </span> <a class="before_after_month"
                                           href="/insert_spend?year=${today_info.after_year}&month=${today_info.after_month}&book_no=${param.book_no}">
                <!-- 다음달 --> &gt;
            </a> <a class="before_after_year"
                    href="/insert_spend?year=${today_info.search_year+1}&month=${today_info.search_month-1}&book_no=${param.book_no}">
                <!-- 다음해 --> &gt;&gt;
            </a>
            </div>
            <div class="tab-content">
            <ul class="nav nav-tabs">
                <li class="nav-item">
                    <label  class="nav-link active show"  onclick="location.href='/insert_spend?book_no=${param.book_no}&year=${today_info.search_year}&month=${today_info.search_month-1}'">지출</label>
                </li>
                <li class="nav-item">
                    <label  class="nav-link"  onclick="location.href='/insert_income?book_no=${param.book_no}&year=${today_info.search_year}&month=${today_info.search_month-1}'">수입</label>
                </li>
                <li class="nav-item insert_last">
                    <button class="nav-link " onclick="addrow()">추가</button>
                </li>
                <li class="nav-item">
                    <button class="nav-link" onclick="deleteRow()">행삭제</button>
                </li>
            </ul>
                <div id="spendingbox" class="scrollbar" >
                <form name="form" action="/insert_spending.do" method="post">
                        <input type="hidden" name="book_no" id="book_no" value="${param.book_no}">
                        <input type="hidden" name="year" id="year" value="${today_info.search_year}">
                        <input type="hidden" name="month" id="month" value="${today_info.search_month-1}">
                    <table class="table table-hover h-auto insert-table overflow-auto" id="spend_table" >
                        <tr>
                            <th><input type="checkbox" name="selectall" value="selectall" onclick="selectAll(this)"></th>
                            <th>사용일</th>
                            <th>내역</th>
                            <th>금액</th>
                            <th>분류</th>
                            <th>출금처</th>
                            <th>메모</th>
                        </tr>
                        <tr>
                          <td>
<%--                              <input type='checkbox' name='checked' onclick='checkSelectAll(this)'>--%>
                          </td>
                          <td><input type='text' name='sp_date'  id='testDatepicker' readonly='readonly'></td>
                          <td><input type='text' name='sp_used'></td>
                          <td><input type='text' name='sp_money'></td>
                          <td><input type='text' name='sp_category'></td>
                          <td><input type='text' name='sp_account'></td>
                          <td><input type='text' name='sp_memo'></td>
                        </tr>
                        <c:forEach var="i" items="${spendingList}" begin="0" end="${fn:length(spendingList)}">
                            <tr>
                                <td><input type='checkbox' name='checked' onclick='checkSelectAll(this)' value="${i.sp_no}"></td>
                                <td>${i.sp_date}</td>
                                <td>${i.sp_used}</td>
                                <td>${i.sp_money}</td>
                                <td>${i.sp_category}</td>
                                <td>${i.sp_account}</td>
                                <td>${i.sp_memo}</td>
                            </tr>
                        </c:forEach>
                    </table>
                </form>
                </div>
            </div>
        </div>

    </div>
    <div class="row">


    </div>
</div>
<div class="row">
    <div class="col-md-12" >
        <nav class="navbar navbar-expand-lg fixed-bottom">
            <div class="container-fluid">
                <div class="col-md-2">
                    <a class="navbar-brand" href="">MONEYCHECK</a>
                </div>
                <div class="col-md-6">

                </div>
                <div class="col-md-3">
                    <c:choose>
                        <c:when test="${empty spMoney}">
                            이번 달 지출 : &nbsp;0원
                        </c:when>
                        <c:otherwise>
                            이번 달 지출 : &nbsp;${spMoney}원
                        </c:otherwise>
                    </c:choose>
                </div>
            </div>
        </nav>

    </div>
</div>
</div>
<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.7.1/jquery.min.js"></script>
<script src="http://code.jquery.com/ui/1.8.18/jquery-ui.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>
<script src="/resources/js/scripts.js"></script>
</body>
</html>