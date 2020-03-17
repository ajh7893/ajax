<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<script src="http://code.jquery.com/jquery-1.10.2.js"></script>
<script type="text/javascript">
	$(function() {
		$("#btn_search").click(function() {
			var searchID = $("#checkId").val();
			console.log(searchID);
			if (searchID == "") {
				alert("아이디를입력하세요");
			}
			$.ajax({
				type : "get",
				dataType : "text",
				async : false,
				url : "http://localhost:9090/pro17/mem/search",
				data : {
					id : searchID
				},
				success : function(data, textStatus) {
					if (data == 'no_result') {
						console.log(data);
						alert("존재하지 않는 아이디 입니다.");
						$("#custId").val('');
						$("#custPwd").val('');
						$("#custName").val('');
					} else {
						var jsonInfo = JSON.parse(data);
						console.log(data);
						$("#custId").val(jsonInfo.id);
						$("#custPwd").val(jsonInfo.pwd);
						$("#custName").val(jsonInfo.name);
					}
				},
				error : function(data, textStatus) {
					alert("일치하는 아이디가 없습니다.");
					$("#custId").attr("disabled", false);
				},
				complete : function(data, textStatus) {
				//	alert("complete");
				}
			});	//end else
		});//end search
		
		$("#btn_save").click(function() {
			var saveId = $("custId").val();
			var savePwd =$("custPwd").val();
			var saveName =$("custName").val();
			if(id=''){
				alert("아이디를 입력하세요.");
			}
			$.ajax({
				type: "get",
				dataType: "text",
				async: false,
				url: "http://localhost:9090/pro17/mem/add",
				data:{
					id: saveId,
					pwd: savePwd,
					name: saveName
				},
				success: function(data, textStatus){
					if(data == "failed"){
						alert('작업이 완료되지 않았습니다.');
					}else{
						alert('작업이 완료되었습니다.');
					}
				},
				error: function(data,textStatus){
					alert('에러가 발생했습니다');
					console.log(textStatus);
				},
				complete : function(data, textStatus){
				
				}
			})
		});
	})
	function fn_process(){
    var _id=$("#t_id").val();
    if(_id==''){
   	 alert("ID를 입력하세요");
   	 return;
    }
    $.ajax({
       type:"post",
       async:false,  
       url:"http://localhost:9090/AjaxTest/mem",
       dataType:"text",
       data: {id:_id},
       success:function (data,textStatus){
          if(data=='usable'){
       	   $('#message').text("사용할 수 있는 ID입니다.");
       	   $('#btn_Duplicate').prop("disabled", true);
          }else{
       	   $('#message').text("사용할 수 없는 ID입니다.");
          }
       },
       error:function(data,textStatus){
          alert("에러가 발생했습니다.");
       },
       complete:function(data,textStatus){
          //alert("작업을완료 했습니다");
       }
    });  //end ajax	 
 }		
</script>
<body>
	<form action="mem" id="check">
		<input type="text" id="checkId" /> <input type="button"
			id="btn_search" value="조회" /> <input type="button" id="btn_add"
			value="추가" /> <input type="button" id="btn_save" value="저장" /> <input
			type="button" id="btn_before" value="이전" /> <input type="button"
			id="btn_after" value="다음" />

		<p>고객ID: 
		<input type="text" id="custId"><br></p>
		<p>고객PW: 
		<input type="text" id="custPwd"><br></p>
		<p>고객이름: 
		<input type="text" id="custName"><br></p>

	</form>
</body>
</html>