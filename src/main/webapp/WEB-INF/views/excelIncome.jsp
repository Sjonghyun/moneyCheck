<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>

</head>
<body>
<form id="excelUploadForm" name="excelUploadForm" enctype="multipart/form-data" method="post" action= "/income_excelUpload.do">
    <div class="contents">
        <div>첨부파일은 한개만 등록 가능합니다.</div>

        <dl class="vm_name">
            <dt class="down w90">첨부 파일</dt>
            <input type="hidden" name="book_no" value="${param.book_no}">
            <dd><input id="excelFile" type="file" name="excelFile" /></dd>
        </dl>
    </div>

    <div class="bottom">
        <button type="button" id="addExcelImpoartBtn" class="btn" onclick="check()" ><span>추가</span></button>
        <button type="button" id="" class="btn" onclick="downloadForm()">양식 다운받기</button>
    </div>
</form>
<script src="/resources/js/jquery.min.js"></script>
<script src="https://malsup.github.io/jquery.form.js"></script>
<script>
    function checkFileType(filePath) {
        var fileFormat = filePath.split(".");
        if (fileFormat.indexOf("xlsx") > -1) {
            return true;
        } else {
            return false;
        }

    }

    function check() {
        var file = $("#excelFile").val();
        if (file == "" || file == null) {
            alert("파일을 선택해주세요.");
            return false;
        } else if (!checkFileType(file)) {
            alert("엑셀 파일만 업로드 가능합니다.");
            return false;
        }

        if (confirm("업로드 하시겠습니까?")) {
            var options = {
                success : function(data) {
                    console.log("data: "+data);
                    alert("모든 데이터가 업로드 되었습니다.");

                },
                type : "POST"
            };
            $("#excelUploadForm").ajaxSubmit(options);

        }
    }
function downloadForm(){
    document.excelUploadForm.method = "POST"
    document.excelUploadForm.action = "/download.do"
    document.excelUploadForm.submit();
}
</script>
</body>
</html>