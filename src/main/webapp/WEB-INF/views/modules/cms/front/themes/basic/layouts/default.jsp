<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/modules/cms/front/include/taglib.jsp"%>
<%@ taglib prefix="sitemesh" uri="http://www.opensymphony.com/sitemesh/decorator" %>
<!DOCTYPE html>
<html>
<head>
	<title><sitemesh:title default="欢迎光临"/> - ${site.title} - Powered By openEAP</title>
	<%@include file="/WEB-INF/views/modules/cms/front/include/head.jsp" %>
	
	<sitemesh:head/>
</head>
<body>
	<div class="navbar navbar-fixed-top">
      <div class="navbar-inner">
        <div class="container">
          <a class="brand" href="${ctx}">openEAP Web</a>
          <div class="nav-collapse">
            <ul id="main_nav" class="nav">
             	<li class="${empty category.id?'active':''}"><a href="${ctx}/index-${site.id}${fns:getUrlSuffix()}"><span>首  页</span></a></li>
				<c:forEach items="${fnc:getMainNavList(site.id)}" var="category">
					<c:choose>
		    			<c:when test="${not empty category.href}">
			    			<c:choose>
				    			<c:when test="${fn:indexOf(category.href, '://') eq -1}"><c:set var="url" value="${ctx}${category.href}"/></c:when>
				    			<c:otherwise><c:set var="url" value="${category.href}"/></c:otherwise>
				    		</c:choose>
		    			</c:when>
		    			<c:otherwise><c:set var="url" value="${ctx}/list-${category.id}${fns:getUrlSuffix()}"/></c:otherwise>
		    		</c:choose>
		    		<li class="${requestScope.category.id eq category.id||fn:indexOf(requestScope.category.parentIds,category.id) ge 1?'active':''}"><a href="${url}" target="${category.target}"><span>${category.name}</span></a></li>
		    	</c:forEach>
            </ul>
            <form class="navbar-search pull-right" action="${ctx}/search" method="get">
              	<input type="text" name="q" maxlength="20" class="search-query span2" placeholder="全站搜索..." value="${q}">
            </form>
          </div><!--/.nav-collapse -->
        </div>
      </div>
    </div>
	<div class="container">
		<div class="content">
			<sitemesh:body/>
		</div>
		<hr style="margin:20px 0 10px;">
		<footer>
			<div class="footer_nav"><a href="${ctx}/guestbook" target="_blank">公共留言</a> | <a href="${ctx}/search" target="_blank">全站搜索</a> | <a href="${ctx}/map-${site.id}${fns:getUrlSuffix()}" target="_blank">站点地图</a> | <a href="mailto:luochengwei@gmail.com">技术支持</a> | <a href="${pageContext.request.contextPath}${fns:getAdminPath()}" target="_blank">后台管理</a></div>
			<div class="pull-right">${fns:getDate('yyyy年MM月dd日 E')}</div><div class="copyright">${site.copyright}</div>
      	</footer>
    </div> <!-- /container -->
</body>
</html>