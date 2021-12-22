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
<%--    <link href="https://code.jquery.com/ui/1.11.4/themes/smoothness/jquery-ui.css" rel="stylesheet" type="text/css">--%>
    <link rel="stylesheet" href="/resources/css/datepicker.min.css">
</head>
<body onload="build();">
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
    <div class="row calendar">
        <div class="col-md-2 sidebar">
            <div class="black">
            </div>
            <ul class="nav flex-column">
                <li class="nav-item">
                    <a class="nav-link active" href="/excel">거래내역 엑셀 업로드</a>
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
                <div class="col-md-10">
                    <div class="text-center black">
                        <button id="before" onclick="prevMonth()">이전 달</button>
                        <input id="yearmonth" type="text">
                        <button id="next" onclick="nextMonth()">다음 달</button>
                    </div>
                    <div class="cal_top">
                        <table class="table cal_table table-bordered" id="calendar">
                            <tr>
                                <th>일</th>
                                <th>월</th>
                                <th>화</th>
                                <th>수</th>
                                <th>목</th>
                                <th>금</th>
                                <th>토</th>
                            </tr>
                        </table>
                    </div>
                </div>
            </c:when>
            <c:otherwise>
                <div class="col-md-12 calendar">
                    <div class="text-center black">
                        <button id="before" onclick="prevMonth()">이전 달</button>
                        <input id="yearmonth" type="text" value="">
                        <button id="next" onclick="nextMonth()">다음 달</button>
                    </div>
                    <div class="cal_top">
                        <table class="table cal_table table-bordered" id="calendar">
                            <tr>
                                <th>일</th>
                                <th>월</th>
                                <th>화</th>
                                <th>수</th>
                                <th>목</th>
                                <th>금</th>
                                <th>토</th>
                            </tr>
                        </table>
                    </div>
                </div>
            </c:otherwise>
        </c:choose>
    </div>
</div>
<div class="row">
    <nav class="navbar navbar-expand-lg fixed-bottom">
        <div class="container-fluid">
            <a class="navbar-brand" href="/main">MONEYCHECK</a>
            <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarColor03" aria-controls="navbarColor03" aria-expanded="false" aria-label="Toggle navigation">
                <span class="navbar-toggler-icon"></span>
            </button>

            <div class="collapse navbar-collapse" id="navbarColor03">
                <ul class="navbar-nav me-auto">
                    <li class="nav-item">
                        <a class="nav-link active" href="#">기능1
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
                            <li>
                                <a class="nav-link" href="/login">login</a>
                            </li>
                        </ul>
                    </c:when>
                    <c:otherwise>
                        <ul class="navbar-nav ml-md-auto">
                            <li class="nav-item dropup">
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

<%--    <script src="https://code.jquery.com/jquery-1.12.1.min.js"></script>--%>
<%--    <script src="https://code.jquery.com/ui/1.11.4/jquery-ui.min.js"></script>--%>
    <script src="/resources/js/jquery.min.js"></script>
    <script src="/resources/js/datepicker.min.js"></script>
    <script src="/resources/js/i18n/datepicker.ko.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>
    <script src="/resources/js/scripts.js"></script>

    <script type="text/javascript">

        var today = new Date();
        var date = new Date();

        function prevMonth(){ //이전 달을 today에 값을 저장
            today = new Date(today.getFullYear(), today.getMonth() -1 , today.getDate());
            build();

        }
        function nextMonth(){
            today = new Date(today.getFullYear(), today.getMonth() + 1, today.getDate());
            build();
        }

        function build(){
            var nMonth = new Date(today.getFullYear(), today.getMonth(),1); // 현재달의 첫째날
            var lastDate =  new Date(today.getFullYear(),today.getMonth() + 1,  0); //현재달의 마지막날
            var tbcal = document.getElementById("calendar"); // 테이블 달력 만들 테이블
            var yearmonth = document.getElementById("yearmonth");
            document.getElementById('yearmonth').value = today.getFullYear() + "년 " + (today.getMonth() + 1) + "월";
            console.log(document.getElementById('yearmonth').value = today.getFullYear() + "년 " + (today.getMonth() + 1) + "월");

            if(today.getMonth()+1 == 12) //  눌렀을 때 월이 넘어가는 곳
            {
                before.innerHTML=+(today.getMonth())+"월";
                next.innerHTML="01월";
            }
            else if(today.getMonth()+1==1) //  1월 일 때
            {
                before.innerHTML="12월";

                next.innerHTML= "0" + (today.getMonth()+2)+"월";
            }
            else //   12월 일 때
            {
                before.innerHTML=(today.getMonth())+"월";
                next.innerHTML=(today.getMonth()+2)+"월";
            }
            //남는 테이블 줄 삭제
            while(tbcal.rows.length > 1 ){
                tbcal.deleteRow(tbcal.rows.length -1);
            }
            var row = null;
            row = tbcal.insertRow();
            var cnt = 0;

            for(i = 0; i < nMonth.getDay(); i++){ // 1일 시작 칸 찾기
                cell = row.insertCell();
                cnt = cnt +1;
            }

            //달력 출력
            for(i=1; i<=lastDate.getDate(); i++){
                cell = row.insertCell();
                // cell.innerHTML = '<a onclick="showPopup();">' +'<div class="cal-day">'+i + '<div class="cal-schedule">';
                cell.innerHTML = '<div class="cal-day">'+i + '<div class="cal-schedule">';
                cell.setAttribute('id', + date.getFullYear()+'-'+(date.getMonth()+1)+'-' + i); // td에 하나하나 값 주기

                cnt = cnt + 1;

                if(cnt % 7 == 1){ //일요일
                    // cell.innerHTML = '<a onclick="showPopup();">' +"<font color=#FF9090>" + '<div class="cal-day">'+i + '<div class="cal-schedule">';
                    cell.innerHTML = "<font color=#FF9090>" + '<div class="cal-day">'+i + '<div class="cal-schedule">';
                }
                if(cnt % 7 == 0){ //토욜
                   // window.open("/insert_spend?u_no="+u_no+"&date="+id_date, "insert", "width=950px, height=500px, right=50, top=50");
                    //cell.innerHTML = '<a onclick="showPopup();">' + '<div class="cal-day">'+i + '<div class="cal-schedule">';
                    cell.innerHTML = '<div class="cal-day">'+i + '<div class="cal-schedule">';
                    row = calendar.insertRow();
                }

                var today_bg = document.getElementById( date.getFullYear()+'-'+(date.getMonth()+1)+'-' + i);
                if(today.getFullYear() == date.getFullYear() && today.getMonth() == date.getMonth() && i == date.getDate()){
                    $(today_bg).css("background","#FFE8DF");
                    //해당일에만 넘어와야함
                }
            }
        }



        // 	function setData(){ //shedule 정보 추가 제대로 안넘어감
        //     jsonData =
        //     {
        //         // "2021":{
        //         //     "12":{
        //         //         "25":"안녕"
        //         //     }
        //         //     ,"12":{
        //         //         "7":"칠석"
        //         //         ,"23":"처서"
        //         //     }
        //         //     ,"1":{
        //         //         "13":"추석"
        //         //         ,"23":"추분"
        //         //     }
        //         // }
        //     }
        // }

        // //스케줄 그리기
        // function drawSche(){
        //     setData();
        //     var dateMatch = null;
        //     for(var i=firstDay.getDay();i<firstDay.getDay()+lastDay.getDate();i++){
        //         var txt = "";
        //         txt =jsonData[year];
        //         if(txt){
        // 			txt = jsonData[year][month];
        //             if(txt){
        // 				txt = jsonData[year][month][i];
        //                 dateMatch = firstDay.getDay() + i -1;
        //                 schedule.eq(dateMatch).text(txt);
        //             }
        //         }
        //     }
        // }

        $(function spendCount(){
            var date = $(this).prop('id');
            console.log(date);
        });


    </script>
    <script>
        $("#yearmonth").datepicker({
            language: 'ko',
            view: 'months',
            minView: 'months',
            dateFormat: 'yyyy mm'
        });
        <%--function showPopup() {--%>
        <%--    var id_date = $(this).attr('id');--%>
        <%--    alert(id_date);--%>
        <%--        window.open("/insert_spend?date="+ id_date + "&u_no=${user.u_no}" , "insert", "width=950px, height=500px, right=50, top=50");--%>
        <%--}--%>
    </script>
</body>
</html>