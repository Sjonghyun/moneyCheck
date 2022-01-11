var ws;

function wsOpen(){
    //웹소켓 전송시 현재 방의 번호를 넘겨서 보낸다.
    ws = new WebSocket("ws://" + location.host + "/chating/"+$("#roomNumber").val());
    wsEvt();
}

function wsEvt() {
    ws.onopen = function(data){
        //소켓이 열리면 동작
    }

    ws.onmessage = function(data) {
        //메시지를 받으면 동작
        var msg = data.data;
        if(msg != null && msg.trim() != ''){
            var d = JSON.parse(msg);
            if(d.type == "getId"){
                var si = d.sessionId != null ? d.sessionId : "";
                if(si != ''){
                    $("#sessionId").val(si);
                }
            }else if(d.type == "message"){
                console.log(d.msg);
                if(d.msg == ''){
                    return;
                }
                if(d.sessionId == $("#sessionId").val()){
                    $("#chating").append("<li class='me'><div class='userName'>" + d.userName + "</div></li>");
                    $("#chating").append("<li class='me'><div class='message'>" + d.msg + "</div></li>");
                }else{
                    $("#chating").append("<li class='others'><div class='userName'>" + d.userName + "</div></li>");
                    $("#chating").append("<li class='others'><div class='message'>" + d.msg + "</div></li>");
                }

            }else{
                console.warn("unknown type!")
            }
        }
        $('div.chating').scrollTop($('div.chating').prop('scrollHeight'));
    }

    document.addEventListener("keypress", function(e){
        if(e.keyCode == 13){ //enter press
            send();
        }
    });
}

function chatName(){
    var userName = $("#userName").val();
    if(userName == null || userName.trim() == ""){
        alert("사용자 이름을 입력해주세요.");
        $("#userName").focus();
    }else{
        wsOpen();
        $("#yourName").hide();
        $("#yourMsg").show();
    }
}

function send() {
    var today = new Date();

    var year = today.getFullYear();
    var month = ('0' + (today.getMonth() + 1)).slice(-2);
    var day = ('0' + today.getDate()).slice(-2);

    var date = year + '-' + month  + '-' + day;

    var hours = ('0' + today.getHours()).slice(-2);
    var minutes = ('0' + today.getMinutes()).slice(-2);
    var seconds = ('0' + today.getSeconds()).slice(-2);

    var time = hours + ':' + minutes  + ':' + seconds;

    var str = date +' '+ time;
    var option ={
            type: "message",
            roomNumber: $("#roomNumber").val(),
            sessionId : $("#sessionId").val(),
            userName : $("#userName").val(),
            msg : $("#chatting").val(),
            time: str,
            u_no: $('#u_no').val()
        }
    ws.send(JSON.stringify(option));
    $('#chatting').val("");
    $.ajax({
        url: '/insertDB',
        data : option,
        type: 'POST',
        success: function(data){
            console.log('성공' + data);
        },
        error: function(err){

            console.log('error' + err);
        }

    });
}