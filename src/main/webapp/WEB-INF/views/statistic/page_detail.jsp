<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
<%
String path = request.getContextPath();
String basePath = "https://"+request.getServerName()+":"+request.getServerPort()+path+"/";
//String basePath = "https://"+request.getServerName()+path+"/";
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
          
          
          <div class="row">
            <div class="col-md-12">
            	<div class="box box-info">
            		<div class="box-header with-border">
	                  <h3 class="box-title">页面分析</h3>
	                </div>
	                <div class="box-body">
	                	<div class="row">
	                		<div class="col-md-4">
	                			
			                   <div class="input-group">
				                    <span class="input-group-addon">统计日期:</span>
				                    <input type="text" class=" stat-daterange form-control"   name="timeRange" id="timeRange" placeholder="输入日期范围">
			                   </div>
	                		</div>
	                		<div class="col-md-6">
	                			<div class="input-group">
				                    <span class="input-group-addon" >url</span>
				                    <input type="text" class="form-control"  value="<%=request.getAttribute("pageUrl") %>"/>
				                    <input type="hidden"  id="pageId" name="pageId" value="<%=request.getAttribute("pageId") %>"/>
			                   </div>
	                		</div>
	                		<div class="col-md-2">
	                			<button type="button" class="btn btn-primary" onclick="loadAll()">确定</button>
	                		</div>
	                		
	                	</div>
	                </div>
            	</div>
			</div>
          </div>
          
          <div class="row">
          	 <div class="col-md-12">
          	 	<div class="box box-info">
          	 		<div class="overlay"  id="mainPointOverlay"  style="display: none;">
	                  <i class="fa fa-refresh fa-spin"></i>
	                </div>
          	 		<div class="box-header with-border">
	                  <h3 class="box-title">主要指标</h3>
	                </div>
	                <div class="box-body">
	          	 		<div class="row">
	          	 			<div class="col-md-2 col-sm-6 col-xs-12">
	          	 				<div class="info-box">
					                <span class="info-box-icon bg-aqua"><i class="ion ion-ios-people-outline"></i></span>
					                <div class="info-box-content">
					                  <span class="info-box-text">访问数(PV)</span>
					                  <span class="info-box-number" id="pv">0</span>
					                </div>
					            </div>
	          	 			</div>
	          	 			<div class="col-md-2 col-sm-6 col-xs-12">
	          	 				<div class="info-box">
					                <span class="info-box-icon bg-red"><i class="ion ion-ios-people-outline"></i></span>
					                <div class="info-box-content">
					                  <span class="info-box-text">访客数(UV)</span>
					                  <span class="info-box-number"  id="uv">0</span>
					                </div>
					            </div>
	          	 			</div>
	          	 			<div class="col-md-2 col-sm-6 col-xs-12">
	          	 				<div class="info-box">
					                <span class="info-box-icon bg-blue"><i class="ion ion-ios-people-outline"></i></span>
					                <div class="info-box-content">
					                  <span class="info-box-text">IP数</span>
					                  <span class="info-box-number" id="ip">0</span>
					                </div>
					            </div>
	          	 			</div>
	          	 			
	          	 			
	          	 			<div class="col-md-2 col-sm-6 col-xs-12">
	          	 				<div class="info-box">
					                <span class="info-box-icon bg-purple"><i class="ion ion-ios-people-outline"></i></span>
					                <div class="info-box-content">
					                  <span class="info-box-text" >入口次数</span>
					                  <span class="info-box-number" id="page_enter">0</span>
					                </div>
					            </div>
	          	 			</div>
	          	 			
	          	 			<div class="col-md-2 col-sm-6 col-xs-12">
	          	 				<div class="info-box">
					                <span class="info-box-icon bg-maroon  "><i class="ion ion-ios-people-outline"></i></span>
					                <div class="info-box-content">
					                  <span class="info-box-text">入口率(%)</span>
					                  <span class="info-box-number" id="page_enter_pct">0</span>
					                </div>
					            </div>
	          	 			</div>
	          	 			
	          	 		</div>
	          	 		
	          	 		<div class="row">
	          	 			
	          	 			
	          	 			<div class="col-md-2 col-sm-6 col-xs-12">
	          	 				<div class="info-box">
					                <span class="info-box-icon bg-orange "><i class="ion ion-ios-people-outline"></i></span>
					                <div class="info-box-content">
					                  <span class="info-box-text">跳出次数</span>
					                  <span class="info-box-number" id="page_exit">0</span>
					                </div>
					            </div>
	          	 			</div>
	          	 			<div class="col-md-2 col-sm-6 col-xs-12">
	          	 				<div class="info-box">
					                <span class="info-box-icon bg-yellow "><i class="ion ion-ios-people-outline"></i></span>
					                <div class="info-box-content">
					                  <span class="info-box-text">跳出率(%)</span>
					                  <span class="info-box-number" id="page_exit_pct">0</span>
					                </div>
					            </div>
	          	 			</div>
	          	 			
	          	 			<div class="col-md-2 col-sm-6 col-xs-12">
	          	 				<div class="info-box">
					                <span class="info-box-icon bg-green"><i class="ion ion-ios-people-outline"></i></span>
					                <div class="info-box-content">
					                  <span class="info-box-text">平均访问时长(秒)</span>
					                  <span class="info-box-number"  id="page_stayTime">0<small>秒</small></span>
					                </div>
					            </div>
	          	 			</div>
	          	 			
	          	 			<div class="col-md-2 col-sm-6 col-xs-12">
	          	 				<div class="info-box">
					                <span class="info-box-icon bg-green"><i class="ion ion-ios-people-outline"></i></span>
					                <div class="info-box-content">
					                  <span class="info-box-text">平均加载时间(秒)</span>
					                  <span class="info-box-number"  id="page_loadTime">0<small>秒</small></span>
					                </div>
					            </div>
	          	 			</div>
	          	 			
	          	 		</div>
	          	 	</div>
          	 	</div>
          	 	
          	 </div>
          </div>
          
          <div class="row">
            <div class="col-md-6">
            	<div class="box box-info">
	            	<div class="overlay" >
		                  <i class="fa fa-refresh fa-spin"></i>
		             </div>
	                <div class="box-header with-border">
	                  <h3 class="box-title">上游页面</h3>
	                </div><!-- /.box-header -->
	                <div class="box-body">
	                  <table class="table table-bordered">
	                    	<tr>
		                      <td width="10%">1.</td>
		                      <td>http://localhost:8080</td>
		                    </tr>
		                    <tr>
		                      <td width="10%">1.</td>
		                      <td>http://localhost:8080</td>
		                    </tr>
		                    <tr>
		                      <td width="10%">1.</td>
		                      <td>http://localhost:8080</td>
		                    </tr>
		                    <tr>
		                      <td width="10%">1.</td>
		                      <td>http://localhost:8080</td>
		                    </tr>
		                    <tr>
		                      <td width="10%">1.</td>
		                      <td>http://localhost:8080</td>
		                    </tr>
		                    <tr>
		                      <td width="10%">1.</td>
		                      <td>http://localhost:8080</td>
		                    </tr>
		                    <tr>
		                      <td width="10%">1.</td>
		                      <td>http://localhost:8080</td>
		                    </tr>
		                    <tr>
		                      <td width="10%">1.</td>
		                      <td>http://localhost:8080</td>
		                    </tr>
		                    <tr>
		                      <td width="10%">1.</td>
		                      <td>http://localhost:8080</td>
		                    </tr>
		                    <tr>
		                      <td width="10%">1.</td>
		                      <td>http://localhost:8080</td>
		                    </tr>
	                  </table>
	                </div><!-- /.box-body -->
              </div>
            </div>
            <div class="col-md-6">
            	<div class="box box-info">
	            	<div class="overlay" >
		                  <i class="fa fa-refresh fa-spin"></i>
		             </div>
	                <div class="box-header with-border">
	                  <h3 class="box-title">下游页面</h3>
	                </div><!-- /.box-header -->
	                <div class="box-body">
	                  <table class="table table-bordered">
	                    	<tr>
		                      <td width="10%">1.</td>
		                      <td>http://localhost:8080</td>
		                    </tr>
		                    <tr>
		                      <td width="10%">1.</td>
		                      <td>http://localhost:8080</td>
		                    </tr>
		                    <tr>
		                      <td width="10%">1.</td>
		                      <td>http://localhost:8080</td>
		                    </tr>
		                    <tr>
		                      <td width="10%">1.</td>
		                      <td>http://localhost:8080</td>
		                    </tr>
		                    <tr>
		                      <td width="10%">1.</td>
		                      <td>http://localhost:8080</td>
		                    </tr>
		                    <tr>
		                      <td width="10%">1.</td>
		                      <td>http://localhost:8080</td>
		                    </tr>
		                    <tr>
		                      <td width="10%">1.</td>
		                      <td>http://localhost:8080</td>
		                    </tr>
		                    <tr>
		                      <td width="10%">1.</td>
		                      <td>http://localhost:8080</td>
		                    </tr>
		                    <tr>
		                      <td width="10%">1.</td>
		                      <td>http://localhost:8080</td>
		                    </tr>
		                    <tr>
		                      <td width="10%">1.</td>
		                      <td>http://localhost:8080</td>
		                    </tr>
	                  </table>
	                </div><!-- /.box-body -->
              </div>
            </div>
          </div>

        </section><!-- /.content -->
      </div><!-- /.content-wrapper -->
      

    

  </body>
</html>
<script type="text/javascript">
<!--
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
$("#timeRange").val(moment().format('MM/DD/YYYY') + " - " + moment().format('MM/DD/YYYY'))

	//上游页面
	function upperPage() {
		
	}
	
	//下游页面
	function lowerPage() {
		
	}
	
	//主要指标
	function mainPoint() {
		$("#mainPointOverlay").show();
		var timeRange = $("#timeRange").val();
		var pageId = $("#pageId").val();
		//alert(timeRange)
		$.ajax({
			url:'<%=basePath%>statistic/pageDetailMainPoint?pageId=' + pageId + '&timeRange=' + timeRange,
			dataType:'json',
			success:function(data) {
				//alert(data.pv);
				$("#pv").html(data.pv);
				$("#uv").html(data.uv);
				$("#ip").html(data.ip);
				$("#page_stayTime").html(data.page_stayTime);
				$("#page_enter").html(data.page_enter);
				$("#page_enter_pct").html(data.page_enter_pct);
				$("#page_exit").html(data.page_exit);
				$("#page_exit_pct").html(data.page_exit_pct);
				$("#mainPointOverlay").hide();
			},
			error : function() {   
				$("#mainPointOverlay").hide();
	          	alert("异常！");    
	     	}  
		});
	}
	
	function loadAll() {
		mainPoint();
	}
	loadAll();
//-->
</script>

