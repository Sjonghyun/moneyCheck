<%@ page language="java" contentType="text/html; charset=EUC-KR"
         pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="EUC-KR">
    <title>Insert title here</title>
</head>
<body>
    <input type="hidden" id="u_no" name="u_no" value="${param.u_no}">
<script>
    var u_no = document.getElementById('u_no').value;
        if(confirm("Ż���Ͻðڽ��ϱ�?")) {
            alert('�����Ǿ����ϴ�.');
            window.location.href='/deleteUser.do?u_no='+u_no;
        }else{
            alert('������ҵǾ����ϴ�.');
            window.history.go(-1);
        }
</script>
</body>
</html>