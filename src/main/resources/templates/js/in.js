$(document).ready(function(){
	
	$("form input").eq(0).trigger("input");
    $("form input").eq(0).bind("input propertychange",function(event){
    	var s=$(this).val();
    	$.ajax({
            type: "POST",
            url: "/existAdmin",//url
            contentType:"application/json;charset=UTF-8",
            data:JSON.stringify({Aid:s}),
            success: function (data) {
            	if(data=="注册"){
            		$("form input").eq(2).val("SIGN IN");
            		$("h1").html("服装商城");
            		$("h2").html("请注册");
            	}
            	else{
            		if(data=="admin"){
            			$("form input").eq(2).val("LOG IN");
            			$("h1").html("服装商城后台");
            			$("h2").html("卖家登录");
            		}else{
            			$("form input").eq(2).val("LOG IN");
            			$("h1").html("服装商城");
            			$("h2").html("用户登录");
            		}
            	}
            },
            error: function () {
                alert('哎呀！出错了！');
            }
            });
	});
    
});