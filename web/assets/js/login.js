/** 登陆 */

var emialRegexp = new RegExp("^[a-z0-9]+([._\\-]*[a-z0-9])*@([a-z0-9]+[-a-z0-9]*[a-z0-9]+.){1,63}[a-z0-9]+$");

var Login = {
		
		init : function(){
			var that= this;
			that.bindEvent();
		},
		bindEvent : function(){
			var that= this;
			
			$("#login-email").bind("keydown", function(event){
				if(event.keyCode==13){
					var email = $(this).val();
					console.log(email)
					if(!email ){
						commonUtil.msg("邮箱和验证码不能为空");
						return false;
					}
					if(!emialRegexp.test(email)){
						commonUtil.msg("邮箱格式不正确");
						return false;
					}
					commonUtil.http(Path.vcode, {email: email}, function (data){
						commonUtil.msg("验证码已发送");
						setTimeout(() => {
							$("#login-vcode").focus();
						}, 500);
					});
				}
			});
			$("#login-vcode").bind("keydown", function(event){
				if(event.keyCode!=13){
					return;
				}
				var email = $("#login-email").val();
				var vcode = $("#login-vcode").val();
				if(!email || !vcode){
					commonUtil.msg("邮箱和验证码不能为空");
					return false;
				}
				if(!emialRegexp.test(email)){
					commonUtil.msg("邮箱格式不正确");
					return false;
				}
				commonUtil.http(Path.doLogin, {email: email, vcode: vcode}, function (data){
					if(data && data.access_token){
						 localStorage.setItem('token', data.access_token);
						 localStorage.setItem('email', email);
						 commonUtil.msg("登陆成功");
						 $("#loginModal").modal("hide");
					}else{
						commonUtil.msg("登陆失败");
					}
				})
			});
		},
		
}