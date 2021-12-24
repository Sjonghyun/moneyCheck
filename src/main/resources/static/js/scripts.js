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

function wrapCreateBoardByMask() {
// 화면의 높이와 너비를 구한다.
    var maskHeight = $(document).height();
    var maskWidth = $(window).width();

// 마스크의 높이와 너비를 화면 것으로 만들어 전체 화면을 채운다.
    $('#mask_board_move').css({
        'width' : maskWidth,
        'height' : maskHeight
    });

    $('#mask_board_move').fadeTo("slow", 0.7);

    $('.normal_move_board_modal').show();
}

$(function() {
//검은 막 띄우기
//기본 모달창
    $('.openMask_board_move').click(function(e) {
//클릭 시 이벤트
        console.log("모달 켭니다.");
        e.preventDefault();
        wrapCreateBoardByMask();
    });

//닫기 버튼을 눌렀을 때
    $('.normal_move_board_modal .top .close').click(function(e) {
//링크 기본동작은 작동하지 않도록 한다.
        e.preventDefault();
        $('#mask_board_move, .normal_move_board_modal').hide();
    });

//검은 막을 눌렀을 때
    $('#mask_board_move').click(function() {
        $(this).hide();
        $('.normal_move_board_modal').hide();
    });

});
