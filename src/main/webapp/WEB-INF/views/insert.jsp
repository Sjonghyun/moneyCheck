<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>

    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
    <link href="/resources/css/style.css" rel="stylesheet">
</head>
<body>
<div class="container-fluid popup">
        <input type="radio" name="sort" id="spending">
        <input type="radio" name="sort" id="income">
    <div class="row h-100">
        <div class="col-md-8">
                <ul class="nav nav-tabs">
                    <li class="nav-item">
                        <label  class="nav-link active show" for="spending"  data-toggle="tab" onclick="dispList('0');">지출</label>
                    </li>
                    <li class="nav-item">
                        <label  class="nav-link " for="income" data-toggle="tab" onclick="dispList('1');">수입</label>
                    </li>
                    <li class="nav-item insert_last">
                        <a class="nav-link" onclick="addRow()">행추가</a>
                    </li>
                    <li class="nav-item insert_last">
                        <a class="nav-link" onclick="deleteRow()">행삭제</a>
                    </li>
                </ul>

                <div class="tab-content">
                    <div id="spendingbox" >
                        <form name="form" action="#" method="post">
                            <table class="table table-hover h-auto " id="spend_table" >
                                <tr>
                                    <th><input type="checkbox" name="selectall" value="selectall" onclick="selectAll(this)"></th>
                                    <th>사용일</th>
                                    <th>내역</th>
                                    <th>금액</th>
                                    <th>분류</th>
                                    <th>출금처</th>
                                </tr>
                            </table>
                        </form>
                    </div>
                    <div id="incomebox" style="display: none;">
                        <form name="form" action="#" method="post">
                            <table class="table table-hover" id="income_table">
                                <tr>
                                    <th><input type="checkbox" name="selectAll" value="selectAll" onclick="selectAll(this)"></th>
                                    <th>사용일</th>
                                    <th>내역</th>
                                    <th>금액</th>
                                    <th>입금통장</th>
                                    <th>분류</th>
                                </tr>
                            </table>
                        </form>
                    </div>
                </div>
        </div>
        <div class="col-md-4 h-auto">
            <div class="col-md-12 h-auto">
                <ul class="nav nav-tabs">
                    <li>
                        <a class="nav-link disabled ">메모</a>
                    </li>
                </ul>
                <textarea></textarea>
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
            <button type="button" class="btn insert_btn" onclick="">추가하기</button>
            <button type="button" class="btn insert_btn" onclick="">취소</button>
        </div>
                <div class="collapse navbar-collapse" id="navbarColor03">

                    <ul class="navbar-nav ml-md-auto">
                        <li>
                            <a class="nav-link" href="/login">login</a>
                        </li>
                    </ul>
                </div>
                </div>
            </nav>
        </div>
    </div>
</div>
<script src="/resources/js/jquery.min.js"></script>
<script src="/resources/js/bootstrap.min.js"></script>
<script src="/resources/js/scripts.js"></script>
<%--<script>--%>
<%--    var result = 0 ;--%>
<%--    function dispList(select) {--%>
<%--        var obj1 = document.getElementById("spendingbox");--%>
<%--        var obj2 = document.getElementById("incomebox");--%>
<%--        if( select == "0" ) {--%>
<%--            obj1.style.display = "block";--%>
<%--            obj2.style.display = "none";--%>
<%--            result = 0;--%>
<%--            return result;--%>
<%--        } else if(select == "1") {--%>
<%--            obj1.style.display = "none";--%>
<%--            obj2.style.display = "block";--%>
<%--            result = 1;--%>
<%--            return result;--%>
<%--        }--%>
<%--    }--%>
<%--    function addRow(){ // radio타입에 따라 다르게 적용되야함. 테이블 크기가 다르게 때문에--%>
<%--        if(result == 0){--%>
<%--            mytable = document.getElementById('spend_table');--%>

<%--            row = mytable.insertRow(mytable.rows.length);  //추가할 행--%>

<%--            cell1 = row.insertCell(0);  //실제 행 추가 여기서의 숫자는 컬럼 수--%>
<%--            cell2 = row.insertCell(1);--%>
<%--            cell3 = row.insertCell(2);--%>
<%--            cell4 = row.insertCell(3);--%>
<%--            cell5 = row.insertCell(4);--%>
<%--            cell6 = row.insertCell(5);--%>

<%--            cell1.innerHTML = "<td><input type='checkbox' name='checked' onclick='checkSelectAll(this)'></td>";  //추가한 행에 원하는  요소추가--%>
<%--            cell2.innerHTML = "<td><input type='text' /></td>";--%>
<%--            cell3.innerHTML = "<td><input type='text' /></td>";--%>
<%--            cell4.innerHTML = "<td><input type='text' /></td>";--%>
<%--            cell5.innerHTML = "<td><input type='text' /></td>";--%>
<%--            cell6.innerHTML = "<td><input type='text' /></td>";--%>
<%--        }--%>
<%--        else if(result == 1){--%>
<%--            mytable = document.getElementById('income_table');--%>

<%--            row = mytable.insertRow(mytable.rows.length);  //추가할 행--%>

<%--            cell1 = row.insertCell(0);  //실제 행 추가 여기서의 숫자는 컬럼 수--%>
<%--            cell2 = row.insertCell(1);--%>
<%--            cell3 = row.insertCell(2);--%>
<%--            cell4 = row.insertCell(3);--%>
<%--            cell5 = row.insertCell(4);--%>
<%--            cell6 = row.insertCell(5);--%>

<%--            cell1.innerHTML = "<td><input type='checkbox' name='checked' onclick='checkSelectAll(this)'></td>";  //추가한 행에 원하는  요소추가--%>
<%--            cell2.innerHTML = "<td><input type='text' ></td>";--%>
<%--            cell3.innerHTML = "<td><input type='text' ></td>";--%>
<%--            cell4.innerHTML = "<td><input type='text' ></td>";--%>
<%--            cell5.innerHTML = "<td><input type='text' ></td>";--%>
<%--            cell6.innerHTML = "<td><input type='text' ></td>";--%>
<%--        }--%>


<%--    }--%>
<%--    function deleteRow(){ //삭제--%>
<%--        if($("input:checkbox[name='checked']:checked").length == 0){--%>
<%--            alert("삭제할 항목을 선택해 주세요");--%>
<%--            return;--%>
<%--        }--%>
<%--        $("input:checkbox[name='checked']:checked").each(function (k,kVal){--%>
<%--           var a = kVal.parentElement.parentElement;--%>
<%--           $(a).remove();--%>
<%--        });--%>
<%--        // $("input:checkbox[name='selectall']").attr("checked",false);--%>
<%--    }--%>
<%--    function selectAll(selectAll){ //전체체크, 전체 해제--%>
<%--        var checkboxes = document.getElementsByName('checked');--%>

<%--        checkboxes.forEach((checkbox) => {--%>
<%--            checkbox.checked = selectAll.checked;--%>
<%--        })--%>
<%--    }--%>
<%--    function checkSelectAll(checkbox){ //하나체크 해제 -> 전체체크 해제--%>
<%--        if(result == 0){//spend_table--%>
<%--           var selectall = document.querySelector('input[name="selectall"]') ;--%>
<%--           if(checkbox.checked === false){--%>
<%--               selectall.checked = false;--%>
<%--           }--%>
<%--        }else if(result == 1){// income_table--%>
<%--            var selectAll = document.querySelector('input[name="selectAll"]') ;--%>
<%--            if(checkbox.checked === false){--%>
<%--                selectAll.checked = false;--%>
<%--            }--%>

<%--        }--%>
<%--    }--%>

<%--</script>--%>
</body>
</html>