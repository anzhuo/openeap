<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="org.apache.shiro.web.filter.authc.FormAuthenticationFilter"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>${fns:getConfig('productName')} 登录</title>
	<meta name="decorator" content="default"/>
	<style type="text/css">
      html,body,table{width:100%;height:100%;text-align:center;}
      body{display: inline-block;background-color: #3ca7eb;background-image: linear-gradient(to bottom, #0084c5, #3ca7eb);background-repeat: repeat-x;}
      .login_con{ padding:0; margin:0; width:922px; height:519px; margin:7% auto 0; background:url(${ctxStatic}/images/login/loginbj.png); position:relative}
      .login_con .logo{ position:absolute; text-align:center; width:922px; left:0; top:12px}
      .tleft{text-align:left}
      .form-signin-heading{font-size:36px;margin-bottom:20px;color:#0663a2;}
      .form-signin{width:250px; height:280px; padding:100px 0 0 378px;position:relative;text-align:left;width:300px;margin:0 auto 20px;}
      .form-signin .checkbox{margin-bottom:10px;color:#0663a2;} .form-signin .input-label{font-size:16px;line-height:23px;}
      .form-signin .input-block-level{font-size:16px;height:auto;margin-bottom:15px;padding:7px;*width:283px;*padding-bottom:0;_padding:7px 7px 9px 7px;}
      .form-signin .btn.btn-large{font-size:16px;} .form-signin #themeSwitch{position:absolute;right:15px;bottom:10px;}
      .form-signin div.validateCode {padding-bottom:15px;} .mid{vertical-align:middle;}
      /*.header{height:60px;padding-top:30px;position: absolute; left: 0;top: 0} .alert{position:relative;width:300px;margin:0 auto;*padding-bottom:0px;}*/
      label.error{background:none;padding:2px;font-weight:normal;color:inherit;margin:0;}
      .login_con .login{ width:250px; height:280px; padding:100px 0 0 510px}
      .login_con .login table{ width:100%; height:100%}
      .abtn-primary {background-color: #309fcd;background-image: linear-gradient(to bottom, #61ceee, #117db6); background-repeat: repeat-x;border-color: rgba(0, 0, 0, 0.1) rgba(0, 0, 0, 0.1) rgba(0, 0, 0, 0.25); color: #FFFFFF;text-shadow: 0 -1px 0 rgba(0, 0, 0, 0.25);box-shadow: 0 1px 0 rgba(255, 255, 255, 0.2) inset, 0 1px 2px rgba(0, 0, 0, 0.05);padding:5px 20px; text-align:center; border: solid 1px #0a6ea3; cursor:pointer; border-radius:0 8px 24px rgba(255, 255, 255, 0.5) inset; font-size:16px; font-weight:bold;font-family:微软雅黑}
      .abtn-primary:hover{background-color: #0058a8; background-image: linear-gradient(to bottom, #039ae0, #0058a8); color: #FFFFFF;}
      .input-label { font-size: 16px;line-height: 23px;}
      .a_copyright{ font-family:Arial; position:absolute; width:922px; text-align:center; bottom:13px; letter-spacing:1px; color:#004686}
      #messageBox{margin-top: 10px}
    </style>
	<script type="text/javascript">
		$(document).ready(function() {
			$("#loginForm").validate({
				rules: {
					validateCode: {remote: "${pageContext.request.contextPath}/servlet/validateCodeServlet"}
				},
				messages: {
					username: {required: "请填写用户名."},password: {required: "请填写密码."},
					validateCode: {remote: "验证码不正确.", required: "请填写验证码."}
				},
				errorLabelContainer: "#messageBox",
				errorPlacement: function(error, element) {
					error.appendTo($("#loginError").parent());
				} 
			});
		});
		// 如果在框架中，则跳转刷新上级页面
		if(self.frameElement && self.frameElement.tagName=="IFRAME"){
			parent.location.reload();
		}
	</script>
</head>
<body>
<div class="login_con" >
	<!--[if lte IE 6]><br/><div class='alert alert-block' style="text-align:left;padding-bottom:10px;"><a class="close" data-dismiss="alert">x</a><h4>温馨提示：</h4><p>你使用的浏览器版本过低。为了获得更好的浏览体验，我们强烈建议您 <a href="http://browsehappy.com" target="_blank">升级</a> 到最新版本的IE浏览器，或者使用较新版本的 Chrome、Firefox、Safari 等。</p></div><![endif]-->

	<%--</div>--%>
    <div class="logo"><img src="${ctxStatic}/images/login/logo.png"></div>
	<%--<h1 class="form-signin-heading">${fns:getConfig('productName')}</h1>--%>
	<form id="loginForm" class="form-signin" action="${ctx}/login" method="post">
        <table width="100%" border="0" cellspacing="0" cellpadding="0" class="tleft">
            <tr>
                <td colspan="2" align="center" valign="bottom" height="85"><img src="${ctxStatic}/images/login/login.jpg" /></td>
            </tr>
            <tr>
                <td width="80" align="right"><label class="input-label" for="username">登录名：</label></td>
                <td><input type="text" id="username" name="username" class="input-medium required" value="${username}" width=""></td>
            </tr>
            <tr>
                <td align="right"><label class="input-label" for="password">密&nbsp;&nbsp;码：</label></td>
                <td><input type="password" id="password" name="password" class="input-medium required"></td>
            </tr>
            <c:if test="${isValidateCodeLogin}"><div class="validateCode">
            <tr>
                <td align="right"><label class="input-label mid" for="validateCode">验证码：</label></td>
                <td><tags:validateCode name="validateCode" inputCssStyle="margin-bottom:0;"/></td>
            </tr>
            </c:if>
            <tr>
                <td>&nbsp;</td>
                <td><label for="rememberMe" title="下次不需要再登录"><input type="checkbox" id="rememberMe" name="rememberMe"/> 记住我（公共场所慎用）</label></td>
            </tr>
            <tr>
                <td>&nbsp;</td>
                <td><input class="abtn-primary" type="submit" value="登 录"/>&nbsp;&nbsp;<input type="reset" value="重 置" class="abtn-primary"></td>
            </tr>
            <tr>
                <td colspan="2">
                    <div class="header"><%String error = (String) request.getAttribute(FormAuthenticationFilter.DEFAULT_ERROR_KEY_ATTRIBUTE_NAME);%>
                        <div id="messageBox" class="alert alert-error <%=error==null?"hide":""%>"><button data-dismiss="alert" class="close">×</button>
                            <label id="loginError" class="error"><%=error==null?"":"com.openeap.modules.sys.security.CaptchaException".equals(error)?"验证码错误, 请重试.":"用户或密码错误, 请重试." %></label>
                        </div>
                    </div>
                </td>
            </tr>
        </table>


		<%--<div id="themeSwitch" class="dropdown">--%>
			<%--<a class="dropdown-toggle" data-toggle="dropdown" href="#">${fns:getDictLabel(cookie.theme.value,'theme','默认主题')}<b class="caret"></b></a>--%>
			<%--<ul class="dropdown-menu">--%>
			  <%--<c:forEach items="${fns:getDictList('theme')}" var="dict"><li><a href="#" onclick="location='${pageContext.request.contextPath}/theme/${dict.value}?url='+location.href">${dict.label}</a></li></c:forEach>--%>
			<%--</ul>--%>
			<%--<!--[if lte IE 6]><script type="text/javascript">$('#themeSwitch').hide();</script><![endif]-->--%>
		<%--</div>--%>
	</form>
    <div class="a_copyright">Copyright &copy; 2012-${fns:getConfig('copyrightYear')} <a href="${pageContext.request.contextPath}${fns:getFrontPath()}">${fns:getConfig('productName')}</a> - Powered By <a href="https://www.baidu.com" target="_blank">openEAP</a> ${fns:getConfig('version')}</div>
</div>
</body>
</html>