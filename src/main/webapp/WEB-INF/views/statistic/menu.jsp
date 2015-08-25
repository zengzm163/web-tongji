<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
<%
String path = request.getContextPath();
//String basePath = "https://"+request.getServerName()+":"+request.getServerPort()+path+"/";
String basePath = "https://"+request.getServerName()+ path+"/";
%>
<ul class="sidebar-menu">
            <li class="header">网站概况</li>
            <li class="active treeview">
              <a href="#">
                <i class="fa fa-dashboard"></i> <span>趋势分析</span> <i class="fa fa-angle-left pull-right"></i>
              </a>
              <ul class="treeview-menu">
                <li><a href="<%=basePath%>statistic/index?point=pv,uv&statTimeType=1"><i class="fa fa-circle-o"></i> 今日统计</a></li>
                <li><a href="<%=basePath%>statistic/index?point=pv,uv&statTimeType=2"><i class="fa fa-circle-o"></i> 昨日统计</a></li>
                <li><a href="<%=basePath%>statistic/index?point=pv,uv&statTimeType=3"><i class="fa fa-circle-o"></i> 最近7天</a></li>
                <li><a href="<%=basePath%>statistic/index?point=pv,uv&statTimeType=4"><i class="fa fa-circle-o"></i> 最近30天</a></li>
              </ul>
            </li>
            
            <li class="active treeview">
              <a href="#">
                <i class="fa fa-dashboard"></i> <span>页面分析</span> <i class="fa fa-angle-left pull-right"></i>
              </a>
              <ul class="treeview-menu">
                <li ><a href="<%=basePath%>statistic/pageStatIndex"><i class="fa fa-circle-o"></i> 受访页面</a></li>
              </ul>
            </li>
            
            <li class="active treeview">
              <a href="#">
                <i class="fa fa-dashboard"></i> <span>访客分析</span> <i class="fa fa-angle-left pull-right"></i>
              </a>
              <ul class="treeview-menu">
                <li ><a href="<%=basePath%>statistic/sysEnvStatIndex"><i class="fa fa-circle-o"></i>系统环境</a></li>
              </ul>
            </li>
            
          </ul>