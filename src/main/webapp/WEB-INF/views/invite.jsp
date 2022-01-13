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
    <title></title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
    <link href="/resources/css/style.css" rel="stylesheet">
    <link href="https://code.jquery.com/ui/1.11.4/themes/smoothness/jquery-ui.css" rel="stylesheet" type="text/css">
    <link rel="preconnect" href="https://fonts.googleapis.com">
</head>
<body>
    <c:choose>
        <c:when test="${!empty user}">
        <div class="col-md-10 ">
            <div class="mz-0">
                <div class="black"></div>
                <div>
                    <p>초대를 하시면 가계부를 공유하게 됩니다.</p>
                    <p>회원만 초대 가능하며 초대상대가 비회원인 경우,</p>
                    <p>회원가입 후 진행해주세요</p>
                    <br>
                    <div>
                        아이디:  <input type="text" id="emailText">
                    </div>
                    <input type="hidden" value="${user.u_name}" id="u_name">
                    <input type="hidden" value="${user.book_no}" id="book_no">
                    <div class="check_font" id="mail_check"></div>
                    <div class="text-center">
                        <button class="btn btn-secondary btn-sm" onclick="emailSend()">초대하기</button>
                    </div>
                </div>
            </div>
        </div>
        </c:when>
    </c:choose>
    <script src="http://ajax.googleapis.com/ajax/libs/jquery/1.7.1/jquery.min.js"></script>
        <script src="http://code.jquery.com/ui/1.8.18/jquery-ui.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>
    <script src="/resources/js/scripts.js"></script>
     </body>
</html>