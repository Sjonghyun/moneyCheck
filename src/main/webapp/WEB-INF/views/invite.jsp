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
    <link href="https://code.jquery.com/ui/1.11.4/themes/smoothness/jquery-ui.css" rel="stylesheet" type="text/css">
    <link rel="stylesheet" href="/resources/css/datepicker.min.css">
</head>
<body>
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
                                        <a class="dropdown-item" href="/invite">초대하기</a>
                                        <a class="dropdown-item" href="#">대화하기</a>
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
    </div>
    <c:if test="${!empty user}">
    <div class="row h-100 w-100">
        <div class="col-md-1 sidebar">
            <div class="black">
            </div>
            <ul class="nav flex-column">
                <li class="nav-item">
                    <a class="nav-link active" href="/excel?book_no=${user.book_no}">거래내역 엑셀 업로드</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link " href="#">보고서(그래프)</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="/insert_spend?book_no=${user.book_no}&year=${today_info.search_year}&month=${today_info.search_month}">가계부 쓰기</a>
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
                <div class="col-md-11 ">
                    <div class="text-center">
                        <div class="black"></div>
                        <div>
                            <p>초대를 하시면 가계부를 공유하게 됩니다.</p>
                            <p>회원만 초대 가능하며 초대상대가 비회원인 경우, 회원가입 후 진행해주세요</p>
                            <br>
                            <div>
                                이메일:  <input type="text" id="emailText">
                            </div>
                            <input type="hidden" value="${user.u_name}" id="u_name">
                            <input type="hidden" value="${user.book_no}" id="book_no">
                            <div class="check_font" id="mail_check"></div>
                            <div>
                                <button id="" onclick="emailSend()">초대하기</button>
                            </div>
                        </div>
                    </div>
                </div>
            </c:when>
        </c:choose>
    </div>
    <script src="http://ajax.googleapis.com/ajax/libs/jquery/1.7.1/jquery.min.js"></script>
        <script src="http://code.jquery.com/ui/1.8.18/jquery-ui.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>
    <script src="/resources/js/scripts.js"></script>
<script>
    function emailSend(){//이메일 보냄
        let clientEmail = document.getElementById('emailText').value;
        let name = document.getElementById('u_name').value;
        let book_no = document.getElementById('book_no').value;
        console.log('입력한 메일' + clientEmail);

        $.ajax({
            type:"POST",
            url:"/invite.do",
            data:{ u_mail:clientEmail,
                   name:name,
                book_no:book_no},
            success: function(data){
                if(data == 1) {
                    alert("초대가 발송되었습니다.");
                    console.log('성공', data);
                }else{
                    alert("아이디가 없습니다.");
                }
            },
            error: function (e){
                alert("초대 발송에 실패하였습니다.")
                console.log('실패',e);
            }
        });
    }

</script>
     </body>
</html>