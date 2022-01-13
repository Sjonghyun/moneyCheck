<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html lang="ko">
<head>
    <script src="http://ajax.googleapis.com/ajax/libs/jquery/1.7.1/jquery.min.js"></script>

    <link rel="stylesheet" href="http://code.jquery.com/ui/1.8.18/themes/base/jquery-ui.css" type="text/css" />
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="stylesheet" href="/resources/css/schedule_show.css">
    <title></title>
</head>
<body style="margin: 0 auto">
<div class="normal_manage_board_modal">
    <script>
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
    </script>

    <div class="top">
        <div class="subject">일정</div>
    </div>

    <div class="bottom">
        <div class="info"></div>

        <form role="form" name="schedule_modify">
            <input type="hidden" name="year" value="${today_info.search_year}" />
            <input type="hidden" name="month"
                   value="${today_info.search_month-1}" />
            <div class="contents">
                <ul>

                    <input type="hidden" name= "schedule_idx" class="text_type1" value=${schedule_show.schedule_idx} >
                    <input type="hidden" name= "book_no" class="text_type1" value=${schedule_show.book_no} >
                    <li>
                        <div class="text_subject">날짜 :</div>
                        <div class="text_desc">
                            <input type="text" name="schedule_date" class="text_type1"
                                   id="testDatepicker" readonly="readonly"
                                   value= ${schedule_show.schedule_date }>
                        </div>
                    </li>
                    <li>
                        <div class="text_subject">제목 :</div>
                        <div class="text_desc">
                            <textarea name="schedule_subject" class="textarea_type1" rows="1" style="resize: none;">${schedule_show.schedule_subject }</textarea>
                        </div>
                    </li>
                    <li style="margin-bottom: 75px;">
                        <div class="text_subject">내용 :</div>
                        <div class="text_area_desc">
                            <textarea name="schedule_desc" class="textarea_type1" rows="5">${schedule_show.schedule_desc}</textarea>
                        </div>
                    </li>
                    <li class="button_li">
                        <div class="managebutton">
                            <button type="submit" data-oper='modify'
                                    class="buttonstyle board_manage_go pointer">Modify</button>
                            <button type="submit" data-oper='delete'
                                    class="buttonstyle2 board_manage_go pointer">Delete</button>
                        </div>
                    </li>
                </ul>
            </div>
        </form>
    </div>
</div>
<script>
    $(document).ready(function(){
        var formObj = $("form");

        $('button').on("click",function(e){
            e.preventDefault();

            var operation = $(this).data("oper");

            console.log(operation);

            if(operation === 'delete'){
                formObj.attr("action", "/delete");
            }else if(operation === 'modify'){
                formObj.attr("action", "modify.do");
            }
            formObj.submit();
        });
    });
</script>
</body>
</html>