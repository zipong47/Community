function post() {
    var questionId = $("#question_id").val();
    var content = $("#comment_content").val();
    $.ajax({
        type:"POST",
        url:"/comment",
        data: JSON.stringify({
            "parentId":questionId,
            "content":content,
            "type":1
        }),
        success:function (response){
            if(response.code==200){
                $("#comment_section").hide();
            }else{
                if(response.code==2003){
                    var isAccepted = confirm(response.message);
                    if(isAccepted){
                        window.open("https://gitee.com/oauth/authorize?client_id=4e4b0ba0ae034c4ab51f92daf2f79f7f25a46ab8a61958cafaa22ef048f76593&redirect_uri=http://localhost:8887/callback&response_type=code");
                        window.localStorage.setItem("closable","true");
                    }
                }else{
                    alert(response.message);
                }
            }
        },
        dataType:"json",
        contentType:"application/json"
    });
}