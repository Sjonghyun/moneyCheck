<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <title>메인화면</title>

    <meta name="description" content="Source code generated using layoutit.com">
    <meta name="author" content="LayoutIt!">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
    <link href="/resources/css/style.css" rel="stylesheet">

</head>
<body onload="build();">
<div class="container-fluid">
    <div class="row">
        <nav class="navbar navbar-expand-lg navbar-dark bg-dark fixed-bottom">
            <div class="container-fluid">
                <a class="navbar-brand" href="#">Navbar</a>
                <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarColor03" aria-controls="navbarColor03" aria-expanded="false" aria-label="Toggle navigation">
                    <span class="navbar-toggler-icon"></span>
                </button>

                <div class="collapse navbar-collapse" id="navbarColor03">
                    <ul class="navbar-nav me-auto">
                        <li class="nav-item">
                            <a class="nav-link active" href="#">Home
                                <span class="visually-hidden">(current)</span>
                            </a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="#">Features</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="#">Pricing</a>
                        </li>
                    </ul>
                    <ul class="navbar-nav ml-md-auto">
                        <li class="nav-item dropup">
                            <a class="nav-link dropdown-toggle" data-bs-toggle="dropdown" href="#" role="button" aria-haspopup="true" aria-expanded="false">Dropdown</a>
                            <div class="dropdown-menu">
                                <a class="dropdown-item" href="#">Action</a>
                                <a class="dropdown-item" href="#">Another action</a>
                                <a class="dropdown-item" href="#">Something else here</a>
                                <div class="dropdown-divider"></div>
                                <a class="dropdown-item" href="#">Separated link</a>
                            </div>
                        <li class="nav-item">
                            <a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</a>
                        </li>
                        </li>
                    </ul>
                </div>
            </div>
        </nav>
        <div class="col-md-12">
            <h3 class="text-center">
                달력 놓는곳
            </h3>
        </div>
    </div>
    <div class="row">
        <div class="col-md-2">
            <ul class="nav flex-column">
                <li class="nav-item">
                    <a class="nav-link active" href="#">엑셀넣기</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link " href="#">보고서(그래프)</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="#">Messages</a>
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
        <div class="col-md-10">
            <div class="cal_top">
                <table class="table cal_table" id="calendar">
                    <button id="before" onclick="prevMonth()">이전 달</button>
                    <span id="yearmonth"></span>
                    <button id="next" onclick="nextMonth()">다음 달</button>
                    <div id="cal_tab" class="cal">
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
    </div>
</div>
</div>

<script src="/resources/js/jquery.min.js"></script>
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
        yearmonth.innerHTML = today.getFullYear() + "년 " + (today.getMonth() + 1) + "월";

        if(today.getMonth()+1 == 12) //  눌렀을 때 월이 넘어가는 곳
        {
            before.innerHTML=(today.getMonth())+"월";
            next.innerHTML="1월";
        }
        else if(today.getMonth()+1==1) //  1월 일 때
        {
            before.innerHTML="12월";
            next.innerHTML=(today.getMonth()+2)+"월";
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
            cell.setAttribute('id', + date.getFullYear()+'-'+(date.getMonth()+1)+'-' + i); // td에 하나하나 값 주기
            cell.innerHTML = '<div class="cal-day">'+i;

            cnt = cnt + 1;

            if(cnt % 7 == 1){ //일요일
                cell.innerHTML = "<font color=#FF9090>" + '<div class="cal-day">'+i;
            }
            if(cnt % 7 == 0){ //토욜
                cell.innerHTML = "<font color=#7ED5E4>" + '<div class="cal-day">'+i;
                row = calendar.insertRow();
            }

            var today_bg = document.getElementById( date.getFullYear()+'-'+(date.getMonth()+1)+'-' + i);
            if(today.getFullYear() == date.getFullYear() && today.getMonth() == date.getMonth() && i == date.getDate()){
                $(today_bg).css("background","#BCF1B1");
                //해당일에만 넘어와야함 https://euny0356.tistory.com/9

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
</script>
</body>
</html>