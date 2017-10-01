			 /***
			  * @author yzj
			  * @date 2016-08-24 09:06:39
			  * 初始化的时候 需要 知道 上次是否点击了 记住我功能 ，如果 使用记住我功能 则需要 cookie登陆
			  */
 				var loginWay = 0 ;
			$(function(){
				/**
				 **  查看 登录方式 默认为 0.正常登陆  ，如果上次点击了 记住我功能，1 则  cookie 登陆   
				 **/
				 loginWay = getCookie("loginWay");
				 console.log(getCookie("loginWay"));
				 if(loginWay == 1)
					 {
					 	//cookie登陆 ， 密码为加密模式 后台需要转换
					 	 var username = getCookie("username");
					 	 var password = getCookie("password");
					 	 $("#username").val(username);
					 	 $("#password").val(password);
					 	//默认选中 记住我功能 
					 	$("#remember").attr("checked", true);
					 	$("#remember").parent().addClass("checked");
					 }
				 
			 });




				/****
				 * 登陆前的验证函数
				 */
				function checkCodePaper()
			  {
				  //如果勾选 记住我 则 将用户 写入 cookies 
				  //setCookie = 1 记住 
			 	  if($("#remember").is(":checked"))
			 	  {
			 		  $("#setCookie").val(1);
			 	  }
			 	  //否则 修改 loginWay = 0 ，登陆方式变为 普通登陆 
			 	  else
			 	  {
			 		 SetCookie("loginWay",0);
			 	  }
				  
				 /*****
				 **    修改提示语 为未登录之前 
				 ***/
				 $("#loginError").html("请输入用户名和密码");
				 
				 
				 /**
				  * 如果是 cookies 登陆 则需要修改cookieLogin = 1
				  * 后台会根据这个参数 判断是否要解密密码 
				  */
				 if(loginWay == 1)
				  {
					 $("#cookieLogin").val(1);
				  }
				 
				 
				 $('#login-form').submit();
			  }	
		  
				
				/****
				 * @author yzj
				 * @date 2016-08-24 09:50:11
				 * 初始化登陆方式 ： 当用户修改了 用户名和密码的时候 登陆方式 修改为普通登陆 loginWay = 0;
				 */
				function initLoginWay()
				{
					loginWay = 0 ;
				}