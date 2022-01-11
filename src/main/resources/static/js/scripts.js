// $("#datepicker").datepicker({
//     language: 'ko'
// });
function addrow(){
    if(document.form.sp_date.value =="") {
        alert("날짜를 선택해주세요");
        return;
    }
    if(document.form.sp_used.value==""){
        form.sp_used.focus();
        return;
    }
    if(document.form.sp_money.value==""){
        form.sp_money.focus();
        return;
    }
    if(document.form.sp_category.value==""){
        form.sp_category.focus();
        return;
    }
    if(document.form.sp_account.value==""){
        form.sp_acoount.focus();
        return;
    }
    document.form.submit();
}
function deleteRow(){ //삭제
        var checked = $("input:checkbox[name='checked']:checked").val();
        var book_no = document.getElementById('book_no').value;
        var year = document.getElementById('year').value;
        var month = document.getElementById('month').value;
    if($("input:checkbox[name='checked']:checked").length == 0){
        alert("삭제할 항목을 선택해 주세요");
        return;
    }
    $("input:checkbox[name='checked']:checked").each(function (k,kVal){
        var a = kVal.parentElement.parentElement;
        var data = kVal.value;
        $(a).remove();

        if($(a).remove()){
            $("input:checkbox[name='selectall']:checked").prop("checked",false);
                $.ajax({
                    type:"post",
                    url:"/delete_spending",
                    data:{
                        sp_no: data,
                        book_no:book_no,
                        year:year,
                        month:month
                    },
                    success:function(){
                    },
                    error:function(e){
                        console.log(e);
                    }
                });
            }
    });
}
function income_addrow(){
    if(document.form.in_date.value =="") {
        alert("날짜를 선택해주세요");
        return;
    }
    if(document.form.in_used.value==""){
        form.in_used.focus();
        return;
    }
    if(document.form.in_money.value==""){
        form.in_money.focus();
        return;
    }
    if(document.form.in_category.value==""){
        form.in_category.focus();
        return;
    }
    if(document.form.in_account.value==""){
        form.in_acoount.focus();
        return;
    }
    document.form.submit();
}
function income_deleteRow(){ //삭제
    var book_no = document.getElementById('book_no').value;
    var year = document.getElementById('year').value;
    var month = document.getElementById('month').value;

    if($("input:checkbox[name='checked']:checked").length == 0){
        alert("삭제할 항목을 선택해 주세요");
        return;
    }
    $("input:checkbox[name='checked']:checked").each(function (k,kVal){
        var a = kVal.parentElement.parentElement;
        var checked = $("input:checkbox[name='checked']:checked").val();
        var data = kVal.value;
        $(a).remove();

        if($(a).remove()){
            $("input:checkbox[name='selectall']:checked").prop("checked",false);
            $.ajax({
                type:"post",
                url:"/delete_income",
                data:{
                    in_no: data,
                    book_no:book_no,
                    year:year,
                    month:month
                },
                success:function(){
                },
                error:function(e){
                    console.log(e);
                }
            });
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

    $('.normal_move_board_modal .top .close').click(function(e) {
        e.preventDefault();
        $('#mask_board_move, .normal_move_board_modal').hide();
    });
    $('#mask_board_move').click(function() {
        $(this).hide();
        $('.normal_move_board_modal').hide();
    });

});
var idx;
var num;
var subject;
var desc;
var date;
var book_no;

$(function() {
    $("#testDatepicker")
        .datepicker(
            {

                dateFormat : "yy-mm-dd",
                changeMonth : true,
                changeYear : true,
                dayNames : [ '월요일', '화요일', '수요일', '목요일',
                    '금요일', '토요일', '일요일' ],
                dayNamesMin : [ '월', '화', '수', '목', '금',
                    '토', '일' ],
                monthNamesShort : [ '1', '2', '3', '4',
                    '5', '6', '7', '8', '9', '10',
                    '11', '12' ]
            });
});
function scheduleAdd() {
    var schedule_add_form = document.schedule_add;
    if (schedule_add_form.schedule_date.value == ""
        || schedule_add_form.schedule_date.value == null) {
        alert("날짜를 입력해주세요.");
        schedule_add_form.schedule_date.focus();
        return false;
    } else if (schedule_add_form.schedule_subject.value == ""
        || schedule_add_form.schedule_subject.value == null) {
        alert("제목을 입력해주세요.");
        schedule_add_form.schedule_date.focus();
        return false;
    }
    schedule_add_form.submit();
}

function spendPopup() {
    var book_no = document.getElementById("book_no").value;
    window.open("/excel?book_no="+book_no, "a", "width=500, height=300, left=100, top=50"); }
function incomePopup() {
    var book_no = document.getElementById("book_no").value;
    window.open("/excelIncome?book_no="+book_no, "a", "width=500, height=300, left=100, top=50"); }
function invitePopup() {
    var book_no = document.getElementById("book_no").value;
    window.open("/invite?book_no="+book_no, "a", "width=500, height=300, left=500, top=100"); }

function emailSend(){
    let clientEmail = document.getElementById('emailText').value;
    let name = document.getElementById('u_name').value;
    let book_no = document.getElementById('book_no').value;

    $.ajax({
        type:"POST",
        url:"/invite.do",
        data:{ u_mail:clientEmail,
            name:name,
            book_no:book_no},
        success: function(data){
            if(data == 1) {
                alert("초대가 발송되었습니다.");
                window.close();
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