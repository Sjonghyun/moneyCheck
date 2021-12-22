$("#datepicker").datepicker({
    language: 'ko'
});
// var result = 0 ;
// function dispList(select) {
//     if( select == "0" ) {
//         result = 0;
//         return result;
//     } else if(select == "1") {
//         result = 1;
//         return result;
//     }
// }

function addRow() {

    mytable = document.getElementById('spend_table');

    row = mytable.insertRow(mytable.rows.length);  //추가할 행

    cell1 = row.insertCell(0);  //실제 행 추가 여기서의 숫자는 컬럼 수
    cell2 = row.insertCell(1);
    cell3 = row.insertCell(2);
    cell4 = row.insertCell(3);
    cell5 = row.insertCell(4);
    cell6 = row.insertCell(5);
    cell7 = row.insertCell(6);

    cell1.innerHTML = "<td><input type='checkbox' name='checked' onclick='checkSelectAll(this)'></td>";  //추가한 행에 원하는  요소추가
    cell2.innerHTML = "<td><input type='text' name='sp_date' id='datepicker'</td>";
    cell3.innerHTML = "<td><input type='text' name='sp_used'></td>";
    cell4.innerHTML = "<td><input type='text' name='sp_money'></td>";
    cell5.innerHTML = "<td><input type='text' name='sp_category'></td>";
    cell6.innerHTML = "<td><input type='text' name='sp_account'></td>";
    cell7.innerHTML = "<td><input type='text' name='sp_memo'></td>";
}

function deleteRow(){ //삭제
    if($("input:checkbox[name='checked']:checked").length == 0){
        alert("삭제할 항목을 선택해 주세요");
        return;
    }
    $("input:checkbox[name='checked']:checked").each(function (k,kVal){
        var a = kVal.parentElement.parentElement;
        var checked = $("input:checkbox[name='checked']:checked").val();
        $(a).remove();

        if($(a).remove()){
            $("input:checkbox[name='selectall']:checked").prop("checked",false);
            if($("input:checkbox[name='checked']:checked").val() != null) {
                document.form.method = "POST"
                document.form.action = '/delete_spending?sp_no=' + checked;
                document.form.submit();
            }
        }
    });
}
function income_addRow(){

    mytable = document.getElementById('income_table');

    row = mytable.insertRow(mytable.rows.length);  //추가할 행

    cell1 = row.insertCell(0);  //실제 행 추가 여기서의 숫자는 컬럼 수
    cell2 = row.insertCell(1);
    cell3 = row.insertCell(2);
    cell4 = row.insertCell(3);
    cell5 = row.insertCell(4);
    cell6 = row.insertCell(5);
    cell7 = row.insertCell(6);

    cell1.innerHTML = "<td><input type='checkbox' name='checked' onclick='checkSelectAll(this)'></td>";  //추가한 행에 원하는  요소추가
    cell2.innerHTML = "<td><input type='text' name='in_date' id='datepicker' ></td>";
    cell3.innerHTML = "<td><input type='text' name='in_used'></td>";
    cell4.innerHTML = "<td><input type='text' name='in_money'></td>";
    cell5.innerHTML = "<td><input type='text' name='in_category'></td>";
    cell6.innerHTML = "<td><input type='text' name='in_account'></td>";
    cell7.innerHTML = "<td><input type='text' name='in_memo'></td>";

}
function income_deleteRow(){ //삭제
    if($("input:checkbox[name='checked']:checked").length == 0){
        alert("삭제할 항목을 선택해 주세요");
        return;
    }
    $("input:checkbox[name='checked']:checked").each(function (k,kVal){
        var a = kVal.parentElement.parentElement;
        var checked = $("input:checkbox[name='checked']:checked").val();
        $(a).remove();

        if($(a).remove()){
            $("input:checkbox[name='selectall']:checked").prop("checked",false);
            if($("input:checkbox[name='checked']:checked").val() != null){
                document.form.method="POST"
                document.form.action = '/delete_income?sp_no='+checked;
                document.form.submit();
            }
        }
    });
}
function selectAll(selectAll){ //전체체크, 전체 해제
    var checkboxes = document.getElementsByName('checked');

    checkboxes.forEach((checkbox) => {
        checkbox.checked = selectAll.checked;
    })
}
function checkSelectAll(checkbox){ //하나체크 해제 -> 전체체크 해제
        var selectall = document.querySelector('input[name="selectall"]') ;
        if(checkbox.checked === false){
            selectall.checked = false;
        }
}
