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
    <%--    <link rel="stylesheet" href="//code.jquery.com/ui/1.13.0/themes/base/jquery-ui.css">--%>
    <link rel="stylesheet" href="/resources/css/datepicker.min.css">
</head>
<body>
<sec:authentication property="principal" var="session"/>
<c:if test="${session ne 'anonymousUser'}">
    <sec:authentication property="principal.authInfo" var="user"/>
</c:if>
<div class="container-fluid">
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
                            <a class="nav-link active" href="#">기능1</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="#">기능2</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="#">기능3</a>
                        </li>
                    </ul>
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
                </div>
            </div>
        </nav>
        <div class="black">
        </div>

    </div>
    <div class="row calendar">
        <div class="col-md-2 sidebar">
            <div class="black">
            </div>
            <ul class="nav flex-column">
                <li class="nav-item">
                    <a class="nav-link active" href="#">엑셀넣기</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link " href="#">보고서(그래프)</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="/insert_spend?u_no=${param.u_no}">가계부 쓰기</a>
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

        <div class="col-md-8">
            <div class="black"></div>
            <ul class="nav nav-tabs">
                <li class="nav-item">
                    <label  class="nav-link"  onclick="location.href='/insert_spend?u_no=${param.u_no}'">지출</label>
                </li>
                <li class="nav-item">
                    <label  class="nav-link active show"  onclick="location.href='/insert_income?u_no=${param.u_no}'">수입</label>
                </li>
                <li class="nav-item insert_last">
                    <a class="nav-link" onclick="income_addRow()">행추가</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" onclick="income_deleteRow()">행삭제</a>
                </li>
            </ul>
            <div class="tab-content">
                <div id="incomebox" >
                    <form name="form" action="/insert_income.do" method="post">
                        <input type="hidden" name="u_no" value="${param.u_no}">
                        <table class="table table-hover h-auto insert-table" id="income_table" >
                            <tr>
                                <th><input type="checkbox" name="selectall" value="selectall" onclick="selectAll(this)"></th>
                                <th>사용일</th>
                                <th>내역</th>
                                <th>금액</th>
                                <th>분류</th>
                                <th>입금처</th>
                                <th>메모</th>
                            </tr>
                            <c:forEach var="i" items="${incomeList}" begin="0" end="${fn:length(incomeList)}">
                                <tr>
                                    <td><input type='checkbox' name='checked' onclick='checkSelectAll(this)' value="${i.in_no}"></td>
                                    <td>${i.in_date}</td>
                                    <td>${i.in_used}</td>
                                    <td>${i.in_money}</td>
                                    <td>${i.in_category}</td>
                                    <td>${i.in_account}</td>
                                    <td>${i.in_memo}</td>
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
                <div class="col-md-7">

                </div>
                <div class="col-md-3">
                    <button type="button" class="btn insert_btn" onclick="document.form.submit()">추가</button>
                    <button type="button" class="btn insert_btn" onclick="window.close()">취소</button>
                </div>
                <%--                <div class="collapse navbar-collapse" id="navbarColor03">--%>

                <%--                    <ul class="navbar-nav ml-md-auto">--%>
                <%--                        <li>--%>
                <%--                            <a class="nav-link" href="/login">login</a>--%>
                <%--                        </li>--%>
                <%--                    </ul>--%>
                <%--                </div>--%>
            </div>
        </nav>

    </div>
</div>
</div>
<%--<script src="https://code.jquery.com/jquery-3.6.0.js"></script>--%>
<%--<script src="https://code.jquery.com/ui/1.13.0/jquery-ui.js"></script>--%>
<script src="/resources/js/jquery.min.js"></script>
<script src="/resources/js/datepicker.min.js"></script>
<script src="/resources/js/i18n/datepicker.ko.js"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>
<script src="/resources/js/scripts.js"></script>

</body>
</html>