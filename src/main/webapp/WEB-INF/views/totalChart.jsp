<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<sec:authentication property="principal" var="session"/>
<c:if test="${session ne 'anonymousUser'}">
    <sec:authentication property="principal.authInfo" var="user"/>
</c:if>
<html>
<head>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
    <link href="/resources/css/style.css" rel="stylesheet">
    <link href="/resources/css/calendar.css" rel="stylesheet">
    <link href="https://code.jquery.com/ui/1.11.4/themes/smoothness/jquery-ui.css" rel="stylesheet" type="text/css">
    <link rel="stylesheet" href="/resources/css/datepicker.min.css">

    <script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.8.0/Chart.bundle.min.js"></script>
    <script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.8.0/Chart.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/3.7.0/chart.min.js"></script>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
    <script src="http://code.jquery.com/ui/1.8.18/jquery-ui.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>
    <script src="/resources/js/scripts.js"></script>
    <title>Insert title here</title>

</head>
<body>
<input id="book_no"  type="hidden" name="book_no" value="${param.book_no}">
    <c:set var="yearSp" value="${yearSp}"/>
    <c:set var="monthSp" value="${monthSp}"/>
    <c:set var="daySp" value="${daySp}"/>
    <c:set var="yearIn" value="${yearIn}"/>
    <c:set var="monthIn" value="${monthIn}"/>
    <c:set var="dayIn" value="${dayIn}"/>

    <jsp:include page="header.jsp"></jsp:include>
    <jsp:include page="sidebar.jsp"></jsp:include>
<div class="col-md-10">
<div class="black"></div>
    <div class="navigation">
        <a class="before_after_year"
           href="/totalChart?year=${today_info.search_year-1}&month=${today_info.search_month-1}&book_no=${param.book_no}">
            &lt;&lt; <!-- 이전해 -->
        </a> <a class="before_after_month"
                href="/totalChart?year=${today_info.before_year}&month=${today_info.before_month}&book_no=${param.book_no}">
        &lt; <!-- 이전달 -->
        </a> <span class="this_month"> &nbsp;${today_info.search_year}년
                                    <c:if test="${today_info.search_month<10}">0</c:if>${today_info.search_month}월
                                    </span> <a class="before_after_month"
                                               href="/totalChart?year=${today_info.after_year}&month=${today_info.after_month}&book_no=${param.book_no}">
            <!-- 다음달 --> &gt;
        </a> <a class="before_after_year"
                href="/spChart?year=${today_info.search_year+1}&month=${today_info.search_month-1}&book_no=${param.book_no}">
            <!-- 다음해 --> &gt;&gt;
        </a>
    </div>
    <div class="chart-container chart">
        <select id="chartType" name="selectBox">
            <option value="">선택</option>
            <option value="bar">막대형</option>
            <option value="radar">레이더형</option>
            <option value="line">꺽은선형</option>

        </select>
        <select id="selectBox" name="selectBox">
            <option value="">선택</option>
            <option value="1">년도별</option>
            <option value="2">월별</option>
            <option value="3">일별</option>
        </select>
        <button class="btn btn-dark btn-sm"  onclick="createChart()">그리기</button>
    </div>
    <div class="chart-container chart div1" style="display: none;">
        <canvas id="year"></canvas>
    </div>
    <div class="chart-container chart div2" style="display: none;">
        <c:set var="monthTotal" value="${monthInSum - monthSum}"/>
        <p>이번년도 남은금액은 <fmt:formatNumber value="${monthTotal}" /> 원 입니다.</p>
        <canvas id="month"></canvas>
    </div>
    <div class="chart-container chart div3" style="display: none;">
        <set></set>
        <c:set var="dayTotal" value="${dayInSum - monthSum}"/>
        <p>이번달 남은금액은 <fmt:formatNumber value="${dayTotal}" /> 원 입니다.</p>
        <canvas id="day"></canvas>
    </div>
</div>
    <script>
        // $(document).ready(function() {
            $('#selectBox').change(function () {
                var result = $('#selectBox option:selected').val();
                if (result == '1') {
                    $('.div1').show();
                    $('.div2').hide();
                    $('.div3').hide();
                }
                if (result == '2') {
                    $('.div2').show();
                    $('.div1').hide();
                    $('.div3').hide();
                }
                if (result == '3') {
                    $('.div1').hide();
                    $('.div2').hide();
                    $('.div3').show();
                }
            });
        // });

        // $(document).ready(function() {
            $('#chartType').change(function () {
                var type = $('#chartType option:selected').val();
                console.log(type);
                if (type == "pie") {
                    chartType = 'pie';
                } else if (type == "bar") {
                    chartType = 'bar';
                } else if (type == "line") {
                    chartType = 'line';
                } else if (type == "doughnut") {
                    chartType = 'doughnut';
                } else if (type == "radar") {
                    chartType = 'radar';
                } else {
                    chartType = '';
                }
            });
        // });
        var book_no = document.getElementById("book_no");
        function createChart(){
            $('#year').remove();
            $('#month').remove();
            $('#day').remove();

            var yearHTML = '<canvas id="year"></canvas>';
            var monthHTML = '<canvas id="month"></canvas>';
            var dayHTML = '<canvas id="day"></canvas>';

            $('.div1').append(yearHTML);
            $('.div2').append(monthHTML);
            $('.div3').append(dayHTML);

            var yearSp = ${yearSp}; // 년별
            var yearjson = JSON.stringify(yearSp);
            var Ydata = JSON.parse(yearjson);

            var monthSp = ${monthSp}; //월별
            var monthjson = JSON.stringify(monthSp);
            var Mdata = JSON.parse(monthjson);

            var daySp = ${daySp}; // 일별
            var dayjson = JSON.stringify(daySp);
            var Ddata = JSON.parse(dayjson);

            var yearIn = ${yearIn};
            var yearInjson = JSON.stringify(yearIn);
            var YIndata = JSON.parse(yearInjson);

            var monthIn = ${monthIn};
            var monthInjson = JSON.stringify(monthIn);
            var MIndata = JSON.parse(monthInjson);

            var dayIn = ${dayIn};
            var dayInjson = JSON.stringify(dayIn);
            var DIndata = JSON.parse(dayInjson);

            var labelList = new Array();
            var valueList = new Array();
            var colorList = new Array();

            var invalueList = new Array();

            for(var i = 0; i<Ydata.length;i++){
                var d = Ydata[i];
                labelList.push(d.dates);
                valueList.push(d.money);
                colorList.push(colorize());
            }
            for(var i = 0; i<YIndata.length;i++){
                var d = YIndata[i];
                invalueList.push(d.money);
            }
            var data1 ={
                labels: labelList,
                datasets: [{
                    label: '지출',
                    data: valueList,
                    backgroundColor:[
                        'rgba(255,99,132,0.2)'
                    ]
                },
                {
                    label: '수입',
                    data: invalueList,
                    backgroundColor:[
                        'rgba(54,162,235,0.2)']
                }],

            };
            var ctx1 =document.getElementById('year').getContext('2d');
            var chart1 = new Chart(ctx1,{
                type: document.getElementById('chartType').value,
                data: data1,
                options:{
                    title:{
                        display:true,
                        text:"년도별 지출"
                    },
                    scales:{
                        yAxes:[{
                            ticks:{
                                beginAtZero:true
                            }
                        }]
                    }
                }
            });

            labelList = new Array();
            valueList = new Array();
            colorList = new Array();

            invalueList = new Array();

            for(var i = 0; i<Mdata.length;i++){
                var d = Mdata[i];
                labelList.push(d.dates);
                valueList.push(d.money);
                colorList.push(colorize());
            }

            for(var i = 0; i<MIndata.length;i++){
                var d = MIndata[i];
                invalueList.push(d.money);
            }
            var data2 ={
                labels:labelList,
                datasets: [{
                    label:'지출',
                    data: valueList,
                    backgroundColor:[
                        'rgba(255,99,132,0.2)'
                    ]
                },
                    {
                    label: '수입',
                    data: invalueList,
                    backgroundColor:[
                        'rgba(54,162,235,0.2)']
                }],
                options:{
                    title:{
                        display:true,
                        text:"지출"
                    },
                    scales:{
                      yAxes:[{
                          ticks:{
                              beginAtZero:true
                          }
                      }]
                    }
                }
            };

            var ctx2 =document.getElementById('month').getContext('2d');
            var chart2 = new Chart(ctx2,{
                type: document.getElementById('chartType').value,
                data: data2
            });

            labelList = new Array();
            valueList = new Array();
            colorList = new Array();

            invalueList = new Array();

            for(var i = 0; i<Ddata.length;i++){
                var d = Ddata[i];
                labelList.push(d.dates);
                valueList.push(d.money);
                colorList.push(colorize());
            }
            for(var i = 0; i<DIndata.length;i++){
                var d = DIndata[i];
                invalueList.push(d.money);
            }
            var data3 ={
                labels: labelList,
                datasets: [{
                    label:'지출',
                    data: valueList,
                    backgroundColor:[
                        'rgba(255,99,132,0.2)']

                },
                {
                    label: '수입',
                    data: invalueList,
                    backgroundColor:[
                        'rgba(54,162,235,0.2)']

                }],

            };

            var ctx3 =document.getElementById('day').getContext('2d');
            var chart3 = new Chart(ctx3,{
                type: document.getElementById('chartType').value,
                data: data3,
                options:{
                    title:{
                        display:true,
                        text:"지출"
                    },
                    scales:{
                        yAxes:[{
                            ticks:{
                                beginAtZero:true
                            }
                        }]
                    }
                }
            });

           }
        function colorize(){
            var r = Math.floor(Math.random()*200);
            var g = Math.floor(Math.random()*200);
            var b = Math.floor(Math.random()*200);
            var color = 'rgba(' + r + ', ' + g + ', ' + b + ', 0.7)';

            return color;
        }

    </script>
</body>
</html>