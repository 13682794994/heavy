$(document).ready(function(){
	
	//Sidebar Accordion Menu:
		
		//$("#main-nav li a.current").parent().find("ul").slideToggle("slow"); // Slide down the current menu item's sub menu
		
		/*$("#main-nav li a.nav-top-item").click( // When a top menu item is clicked...
			function () {
				//$(this).parent().siblings().find("ul").slideUp("normal"); // Slide up all sub menus except the one clicked
				//$(this).next().slideToggle("normal"); // Slide down the clicked sub menu
				return false;
			}
		);*/
		
		/*$("#main-nav li a.no-submenu").click( // When a menu item with no sub menu is clicked...
			function () {
				window.location.href=(this.href); // Just open the link instead of a sub menu
				return false;
			}
			
		);*/
	
    $("#1 tr td a").click(function(){
    	
		var s=$(this).parent().siblings().eq(0).html();
		var x=$(this).parent().parent();
		var k=$("#profile-links a").html();
		
		
		$.ajax({
            type: "POST",
            url: "/addBucket",//url
            dataType: "json",
            contentType:"application/json;charset=UTF-8",
            data:JSON.stringify({Aid:k,Gid:s}),
            success: function (data) { 
            	if(data!=null){
            		x.remove();
            		alert('添加成功');
            		$("table tbody").eq(1).append('<tr><td><input type="checkbox"></td><td>'+data["gid"]+'</td><td>'+data["gname"]+'</td><td>'+data["price"]+'</td><td><a title="Delete"><img src="resources/images/icons/cross.png" alt="Delete"></a></td></tr>');
            	}
            },
            error: function () {
                alert('哎呀！出错了！');
            }
            });
	});
    $("table tbody").eq(1).children().children().find("a").click(function(){
    	
	
		var s=$(this).parent().siblings().eq(1).html();//gid
		var x=$(this).parent().parent();
		//var k=$("#profile-links a").html();//Aid
		
		$.ajax({
            type: "POST",
            url: "/deleteBucket",//url
            dataType: "json",
            contentType:"application/json;charset=UTF-8",
            data:JSON.stringify({Gid:s}),
            success: function (data) { 	
            	if(data!=null){       
            		x.remove();
            	}
            },
            error: function () {
                alert('哎呀！出错了！');
            }
            });
	});
    $(".bulk-actions a").click(function(){
    	
    	
    	var s=$('input[type="checkbox"]');
    	var len=s.length;
    	if(len==1){alert('暂时没有物品在您的购物车内');}
    	var count=0;
		for(var i=0;i<len;i++){
			
			if(i==0)continue;
			//console.log(s.eq(i).attr("checked") == "checked");
			//console.log(s.eq(i).parent().siblings().eq(0).html());
			//console.log($("tfoot tr td div select").val());
			if(s.eq(i).attr("checked") == true){
				var g= s.eq(i).parent().siblings().eq(0).html();//gid
				var x=s.eq(i).parent().parent();
				var sel=$("tfoot tr td div select").val();
				if(sel=="option3"){
					
					$.ajax({
			            type: "POST",
			            url: "/deleteBucket",//url
			            dataType: "json",
			            contentType:"application/json;charset=UTF-8",
			            data:JSON.stringify({Gid:g}),
			            success: function (data) { 	
			            	
			            },
			            error: function () {
			                alert('哎呀！出错了1！');
			            }
			            });
					x.remove();
					if(i==len-1)
					s.eq(0).attr("checked",false);
				}
				else{
					if(sel=="option2"){
						
						var gname= s.eq(i).parent().siblings().eq(1).html();//gname
						count+=parseInt(s.eq(i).parent().siblings().eq(2).html());
						var myDate = new Date;
					    var year = myDate.getFullYear(); //获取当前年
					    var mon = myDate.getMonth() + 1; //获取当前月
					    var date = myDate.getDate(); //获取当前日
					    var h = myDate.getHours();//获取当前小时数(0-23)
					    var m = myDate.getMinutes();//获取当前分钟数(0-59)
					    var second = myDate.getSeconds();//获取当前秒
					    var week = myDate.getDay();
						var time=year+"-"+mon+"-"+date+"-"+h+":"+m+":"+second;
						var k=$("#profile-links a").html();
						var up = 1000000000000;
					    var down= 100000000000;
					    var rand = parseInt(Math.random() * (up - down+ 1) + down);
						var rid=rand;
						console.log(JSON.stringify({Rid:rid,Buytime:time,Aid:k,Gname:gname}));
						$.ajax({
				            type: "POST",
				            url: "/deleteBucket",//url
				            dataType: "json",
				            contentType:"application/json;charset=UTF-8",
				            data:JSON.stringify({Gid:g}),
				            success: function (data) { 
				            	
				            },
				            error: function () {
				                alert('哎呀！出错了2！');
				            }
				            });
						$.ajax({
				            type: "POST",
				            url: "/buyBucket",//url
				            dataType: "json",
				            contentType:"application/json;charset=UTF-8",
				            data:JSON.stringify({Rid:rid,Buytime:time,Aid:k,Gname:gname}),
				            success: function (data) { 	
				            	
				            },
				            error: function () {
				                alert('哎呀！出错3了！');
				            }
				            });
						$("table tbody").eq(2).append('<tr><td>'+rid+'</td><td>'+time+'</td><td>'+gname+'</td><td>在路上</td></tr>');
						x.remove();
						if(i==len-1)
							{
							s.eq(0).attr("checked",false);
							alert("购买成功！！已发货了哦！！共花费"+count+"元！");
							}					
					}
				}
				
			}
		}
		
	});
	
	$("#main-content div.content-box").eq(0).show().siblings().hide();
	
    $("#main-nav li a.nav-top-item").click(function(){
    	
        var ind = $("#main-nav li a.nav-top-item").index(this);
        
        $("#main-nav li a.nav-top-item.current").removeClass('current');
        $(this).addClass('current');
        $("#main-content div.content-box").eq(ind).show().siblings().hide();
    })

    // Sidebar Accordion Menu Hover Effect:
		
		$("#main-nav li .nav-top-item").hover(
			function () {
				$(this).stop().animate({ paddingRight: "25px" }, 200);
			}, 
			function () {
				$(this).stop().animate({ paddingRight: "15px" });
			}
		);

    //Minimize Content Box
		
		$(".content-box-header h3").css({ "cursor":"s-resize" }); // Give the h3 in Content Box Header a different cursor
		$(".closed-box .content-box-content").hide(); // Hide the content of the header if it has the class "closed"
		$(".closed-box .content-box-tabs").hide(); // Hide the tabs in the header if it has the class "closed"
		
		$(".content-box-header h3").click( // When the h3 is clicked...
			function () {
			  $(this).parent().next().toggle(); // Toggle the Content Box
			  $(this).parent().parent().toggleClass("closed-box"); // Toggle the class "closed-box" on the content box
			  $(this).parent().find(".content-box-tabs").toggle(); // Toggle the tabs
			}
		);

    // Content box tabs:
		
		$('.content-box .content-box-content div.tab-content').hide(); // Hide the content divs
		$('ul.content-box-tabs li a.default-tab').addClass('current'); // Add the class "current" to the default tab
		$('.content-box-content div.default-tab').show(); // Show the div with class "default-tab"
		$("#tab1").show();
		$("#tab2").hide();
		$("#1").show();
		$("#2").hide();
		$('.content-box ul.content-box-tabs li a').click( // When a tab is clicked...
			function() { 
				$(this).parent().siblings().find("a").removeClass('current'); // Remove "current" class from all tabs
				$(this).addClass('current'); // Add class "current" to clicked tab
				var currentTab = $(this).attr('href'); // Set variable "currentTab" to the value of href of clicked tab
				$(currentTab).siblings().hide(); // Hide all content divs
				$(currentTab).show(); // Show the content div with the id equal to the id of clicked tab
				return false; 
			}
		);

    //Close button:
		
		$(".close").click(
			function () {
				$(this).parent().fadeTo(400, 0, function () { // Links with the class "close" will close parent
					$(this).slideUp(400);
				});
				return false;
			}
		);

    // Alternating table rows:
		
		$('tbody tr:even').addClass("alt-row"); // Add class "alt-row" to even table rows

    // Check all checkboxes when the one in a table head is checked:
		
		$('.check-all').click(
			function(){
				$(this).parent().parent().parent().parent().find("input[type='checkbox']").attr('checked', $(this).is(':checked'));   
			}
		);

    // Initialise Facebox Modal window:
		
		$('a[rel*=modal]').facebox(); // Applies modal window to any link with attribute rel="modal"

    // Initialise jQuery WYSIWYG:
		
		$(".wysiwyg").wysiwyg(); // Applies WYSIWYG editor to any textarea with the class "wysiwyg"

});
  
  
  