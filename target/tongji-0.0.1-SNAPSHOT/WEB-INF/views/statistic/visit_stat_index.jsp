<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
<%
String path = request.getContextPath();
//String basePath = "https://"+request.getServerName()+":"+request.getServerPort()+path+"/";
String basePath = "https://"+request.getServerName()+path+"/";
%>
<!DOCTYPE html>
<html>
  <head>
    <meta charset="UTF-8">
    <title>统计平台</title>
    <!-- Tell the browser to be responsive to screen width -->
    <meta content='width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no' name='viewport'>
   <jsp:include page="common.jsp" />

   
  </head>
  <body class="skin-blue sidebar-mini">
    <div class="wrapper">
      
      <header class="main-header">
        <!-- Logo -->
        <a href="#" class="logo">
          <!-- mini logo for sidebar mini 50x50 pixels -->
          <span class="logo-mini"><b>A</b>LT</span>
          <!-- logo for regular state and mobile devices -->
          <span class="logo-lg"><b>JF</b>统计平台</span>
        </a>
        <!-- Header Navbar: style can be found in header.less -->
        <nav class="navbar navbar-static-top" role="navigation">
          <!-- Sidebar toggle button-->
          <a href="#" class="sidebar-toggle" data-toggle="offcanvas" role="button">
            <span class="sr-only">Toggle navigation</span>
          </a>
          <div class="navbar-custom-menu">
            <ul class="nav navbar-nav">
              <!-- Messages: style can be found in dropdown.less-->
              <li class="dropdown messages-menu">
                <a href="#" class="dropdown-toggle" data-toggle="dropdown">
                  <i class="fa fa-envelope-o"></i>
                  <span class="label label-success"></span>
                </a>
                
              </li>
              <!-- Notifications: style can be found in dropdown.less -->
              <li class="dropdown notifications-menu">
                <a href="#" class="dropdown-toggle" data-toggle="dropdown">
                  <i class="fa fa-bell-o"></i>
                  <span class="label label-warning"></span>
                </a>
                
              </li>
              <!-- Tasks: style can be found in dropdown.less -->
              <li class="dropdown tasks-menu">
                <a href="#" class="dropdown-toggle" data-toggle="dropdown">
                  <i class="fa fa-flag-o"></i>
                  <span class="label label-danger"></span>
                </a>
                
              </li>
              <!-- User Account: style can be found in dropdown.less -->
              
              <!-- Control Sidebar Toggle Button -->
              <li>
                <a href="#" data-toggle="control-sidebar"><i class="fa fa-gears"></i></a>
              </li>
            </ul>
          </div>
        </nav>
      </header>
      <!-- Left side column. contains the logo and sidebar -->
      <aside class="main-sidebar">
        <!-- sidebar: style can be found in sidebar.less -->
        <section class="sidebar">
          
          
          <!-- sidebar menu: : style can be found in sidebar.less -->
          <jsp:include page="menu.jsp" />
        </section>
        <!-- /.sidebar -->
      </aside>

      <!-- Content Wrapper. Contains page content -->
      <div class="content-wrapper">
        <!-- Content Header (Page header) -->
        <section class="content-header">
          <h1>
            Dashboard
            <small>Control panel</small>
          </h1>
          <ol class="breadcrumb">
            <li><a href="#"><i class="fa fa-dashboard"></i> Home</a></li>
            <li class="active">Dashboard</li>
          </ol>
        </section>

        <!-- Main content -->
        <section class="content">
          <!-- Small boxes (Stat box) -->
          <div class="row">
            <div class="col-lg-3 col-xs-6">
              <!-- small box -->
              <div class="small-box bg-aqua">
                <div class="inner">
                  <h3><%=((Map)request.getAttribute("currStat")).get("pv") %></h3>
                  <p>浏览量(PV)</p>
                </div>
                <div class="icon">
                  <i class="ion ion-bag"></i>
                </div>
              </div>
            </div><!-- ./col -->
            <div class="col-lg-3 col-xs-6">
              <!-- small box -->
              <div class="small-box bg-green">
                <div class="inner">
                  <h3><%=((Map)request.getAttribute("currStat")).get("uv") %></h3>
                  <p>访客数(UV)</p>
                </div>
                <div class="icon">
                  <i class="ion ion-stats-bars"></i>
                </div>
              </div>
            </div><!-- ./col -->
            <div class="col-lg-3 col-xs-6">
              <!-- small box -->
              <div class="small-box bg-yellow">
                <div class="inner">
                  <h3><%=((Map)request.getAttribute("currStat")).get("ip") %></h3>
                  <p>IP数</p>
                </div>
                <div class="icon">
                  <i class="ion ion-person-add"></i>
                </div>
              </div>
            </div><!-- ./col -->
            <div class="col-lg-3 col-xs-6">
              <!-- small box -->
              <div class="small-box bg-red">
                <div class="inner">
                  <h3><%=((Map)request.getAttribute("currStat")).get("new_uv") %></h3>
                  <p>新访客数</p>
                </div>
                <div class="icon">
                  <i class="ion ion-pie-graph"></i>
                </div>
              </div>
            </div><!-- ./col -->
          </div><!-- /.row -->
          <!-- Main row -->
          <div class="row">
            <!-- Left col -->
            <section class="col-lg-7 connectedSortable" style="width: 100%">
            <div class="nav-tabs-custom">
              <div class="box-body">
              <button class="btn bg-purple margin">指标（最多可选2个）：</button>
              	<input type="checkbox"  value="pv" name="cb_point"  checked="checked"/>&nbsp;&nbsp;浏览量(PV)&nbsp;&nbsp;&nbsp;&nbsp;
                 <input type="checkbox" value="uv" name="cb_point" checked="checked"/>&nbsp;&nbsp;访客数(UV)&nbsp;&nbsp;&nbsp;&nbsp;
                 <input type="checkbox" value="ip" name="cb_point"/>&nbsp;&nbsp;IP数&nbsp;&nbsp;&nbsp;&nbsp;
                 <input type="checkbox" value="new_uv" name="cb_point"/>&nbsp;&nbsp;新访客数&nbsp;&nbsp;&nbsp;&nbsp;
                 
             <button class="btn bg-purple margin">统计日期：</button>
               <input input="text" class="stat-daterange"  name="timeRange" id="timeRange" size="30"/>&nbsp;&nbsp;
               <input type="button" onclick="ok()" name="" value="确定" class="btn btn-info btn-flat"/>
				
              </div>
            </div>
              <!-- Custom tabs (Charts with tabs)-->
              <div class="nav-tabs-custom">
	                <ul class="nav nav-tabs">
	                  <li class="active"><a href="#line-chart" data-toggle="tab">曲线图</a></li>
	                  <li><a href="#table-tab" data-toggle="tab">列表视图</a></li>
	                </ul>
                <div class="tab-content no-padding">
                  <!-- Morris chart - Sales -->
                  <div class="chart tab-pane active" id="line-chart" style="position: relative; height: 300px;"></div>
                  <div class="chart tab-pane" id="table-tab" style="position: relative;">
                  	<table id="table1" class="table table-bordered table-hover">
                  	</table>
                  </div>
                </div>
              </div><!-- /.nav-tabs-custom -->

              
		
              

            </section><!-- /.Left col -->
            
          </div><!-- /.row (main row) -->

        </section><!-- /.content -->
      </div><!-- /.content-wrapper -->
      

    
 <script type="text/javascript">
   var js = document.createElement( "script" )
	js.src = "<%=basePath%>resources/hm.js?random=" + Math.random();
	document.body.appendChild(js);
   </script>
  </body>
</html>
<script type="text/javascript">
<!--

var point = "<%=request.getParameter("point")==null?"":request.getParameter("point")%>";

var lineChart;

function drawLineChart(data) {
	var xkey = "timeText" ;
	var ykeys = point.split(",");
	var labels =  new Array();
	$.each(ykeys,function(i,val) {
		if(val == "pv") {
			labels.push("浏览量(PV)");
		} else if(val == "uv") {
			labels.push("访客数(UV)");
		} else if(val == "ip") {
			labels.push("IP数(IP)");
		} else if(val == "new_uv") {
			labels.push("新访客");
		}
	})
	
	$("#line-chart").children().remove();
	
	lineChart = Morris.Line({
	  element: 'line-chart',
	  data: data,
	  xkey: xkey,
	  ykeys: ykeys,
	  labels:labels,
	  lineColors: ['#00a65a', '#3c8dbc'],
      hideHover: 'auto'
	});
	
	
}

function drawTable(data) {
	
	 $('#table1').bootstrapTable('load', {
		 data: data
     });
}


function loadStatData() {
	if(point == "") {
		point = "pv,uv";
	}
	var timeRange = $("#timeRange").val();
	//alert(timeRange)
	$.ajax({
		url:'<%=basePath%>statistic/visitStat?point=' + point + '&timeRange=' + timeRange,
		dataType:'json',
		success:function(data) {
			if(data.state == true) {
				drawLineChart(data.data)
				drawTable(data.data);
			} else {
				alert(data.state);
			}
		},
		error : function() {    
          alert("异常！");    
     }  
	});
}

function ok() {
	//页面逻辑校验
	var arr = new Array();
	$("input[name='cb_point']").each(function() {
		//alert($(this).prop("checked"));
		if($(this).prop("checked")) {
			//alert($(this).val());
			arr.push($(this).val());
		}
	});
	if(arr.length > 2) {
		alert("最多同时支持2个指标统计");
		return;
	}
	point = arr.join(",");
	loadStatData();
}




$('.stat-daterange').daterangepicker(
          {
            ranges: {
              '今日': [moment(), moment()],
              '昨天': [moment().subtract(1, 'days'), moment().subtract(1, 'days')],
              '最近7天': [moment().subtract(6, 'days'), moment()],
              '最近30天': [moment().subtract(29, 'days'), moment()]
            },
            startDate: moment().subtract(60, 'days'),
            endDate: moment(),
            minDate:moment().subtract(60, 'days'),
            maxDate:moment(),
             locale : {
			     applyLabel: '确定',
			     cancelLabel: '取消',
			     fromLabel: 'From',
			     toLabel: 'To',
			     weekLabel: 'W',
			     format: 'YYYY-MM-DD',
			     customRangeLabel: '自定义(可选60天范围)',
			     daysOfWeek: moment.weekdaysMin(),
			     monthNames: moment.monthsShort(),
			     firstDay: moment.localeData()._week.dow,
			        monthNames: [
			            "01/",
			            "02/",
			            "03/",
			            "04/",
			            "05/",
			            "06/ ",
			            "07/",
			            "08/",
			            "09/",
			            "10/",
			            "11/",
			            "12/"
			        ],
			        firstDay: 1
			 }
          },
          function (start, end) {
    		//alert("You chose: " + start.format('MMMM D, YYYY') + ' - ' + end.format('MMMM D, YYYY'));
    	 }
 );
 var statTimeType = <%=request.getParameter("statTimeType")==null?"1" : request.getParameter("statTimeType")%>
 if(statTimeType == 1) {
	 $("#timeRange").val(moment().format('MM/DD/YYYY') + " - " + moment().format('MM/DD/YYYY'))
	 //$('.stat-daterange').data('daterangepicker').setStartDate(moment());
	 //$('.stat-daterange').data('daterangepicker').setEndDate(moment());
 } else if(statTimeType == 2) {
	 $("#timeRange").val(moment().subtract(1, 'days').format('MM/DD/YYYY') + " - " + moment().subtract(1, 'days').format('MM/DD/YYYY'))
	 //$('.stat-daterange').data('daterangepicker').setStartDate(moment().subtract(1, 'days'));
	 //$('.stat-daterange').data('daterangepicker').setEndDate(moment());
 } else if(statTimeType == 3) {	 
	 $("#timeRange").val(moment().subtract(6, 'days').format('MM/DD/YYYY') + " - " + moment().format('MM/DD/YYYY'))
	 //$('.stat-daterange').data('daterangepicker').setStartDate(moment().subtract(6, 'days'));
	 //$('.stat-daterange').data('daterangepicker').setEndDate(moment());
 } else if(statTimeType == 4) {
	 $("#timeRange").val(moment().subtract(29, 'days').format('MM/DD/YYYY') + " - " + moment().format('MM/DD/YYYY'))
	 //$('.stat-daterange').data('daterangepicker').setStartDate(moment().subtract(29, 'days'));
	 //$('.stat-daterange').data('daterangepicker').setEndDate(moment());
 }
 
 
 $('#table1').bootstrapTable({
	    columns: [
	    {
	        field: 'timeText',
	        title: '日期',
	        align: 'center',
	        valign: 'middle'
	    }, {
	        field: 'pv',
	        title: '浏览量(PV)',
	        align: 'center',
	        valign: 'middle'
	    }, {
	        field: 'uv',
	        title: '访客数(UV)',
	        align: 'left',
	        valign: 'top'
	    }, {
	        field: 'new_uv',
	        title: '新访客数',
	        align: 'left',
	        valign: 'top'
	    }, {
	        field: 'ip',
	        title: 'IP数',
	        align: 'center',
	        valign: 'middle'
	    }]
	});
 

 
 //加载数据
 loadStatData();
//-->
</script>

