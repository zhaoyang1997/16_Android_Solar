<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>后台页面头部</title>
<link href="css/css.css" type="text/css" rel="stylesheet" />
</head>
<body onselectstart="return false" oncontextmenu=return(false) style="overflow-x:hidden;">
<!--禁止网页另存为-->
<noscript><iframe scr="*.htm"></iframe></noscript>
<!--禁止网页另存为-->
<table width="100%" border="0" cellspacing="0" cellpadding="0" id="header">
  <tr>
    <td rowspan="2" align="left" valign="top" id="logo"><img src="images/main/logo.png" width="85" height="90"></td>
    <td align="left" valign="bottom">
    <table width="100%" border="0" cellspacing="0" cellpadding="0">
      <tr>
        <td align="left" valign="bottom" id="header-name">后台管理系统</td>
        <td align="right" valign="top" id="header-right">
        	<a href="index.jsp" target="topFrame" onFocus="this.blur()" class="admin-out">注销</a>
            <a href="index.jsp" target="top" onFocus="this.blur()" class="admin-home">管理首页</a>
        	<a href="index.jsp" target="_blank" onFocus="this.blur()" class="admin-index">网站首页</a>       	
            <span>
<!-- 日历 -->
<SCRIPT type=text/javascript src="js/clock.js"></SCRIPT>
<SCRIPT type=text/javascript>showcal();</SCRIPT>
            </span>
        </td>
      </tr>
    </table></td>
  </tr>
  <tr>
    <td align="left" valign="bottom">
	<table width="100%" border="0" cellspacing="0" cellpadding="0">
      <tr>
        <td align="left" valign="bottom" id="header-menu">
        <a href="index.jsp" target="left" onFocus="this.blur()" id="menuon">后台首页</a>
        <a href="SeeAllUserServlet" target="left" onFocus="this.blur()">用户管理</a>
        <a href="SeeAllTomatoServlet" target="left" onFocus="this.blur()">番茄管理</a>
        <a href="SeeAllTaskServlet" target="left" onFocus="this.blur()">任务管理</a>
        <a href="SeeAllImageServlet" target="left" onFocus="this.blur()">壁纸管理</a>
        <a href="SeeAllSingServlet" target="left" onFocus="this.blur()">铃声管理</a>
      </tr>
    </table></td>
  </tr>
</table>
</body>
</html>