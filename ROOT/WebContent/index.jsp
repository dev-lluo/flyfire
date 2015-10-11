<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="src.common/css/reset.css"/>
<link rel="stylesheet" type="text/css" href="src.common/css/icon.css"/>
<link rel="stylesheet" type="text/css" href="src/css/index.css"/>
<title>扑火流萤</title>
</head>
<body>
	<div id="page_top">
			
	</div>
	<div id="page_middle">
		<p id="page_title">
			<img align="absmiddle" width="156"  height="105" alt="扑火流萤" title="扑火流萤"  
			src="data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAJwAAABpCAYAAADP50rnAAAAGXRFWHRTb2Z0d2FyZQBBZG9iZSBJbWFnZVJlYWR5ccllPAAAAyJpVFh0WE1MOmNvbS5hZG9iZS54bXAAAAAAADw/eHBhY2tldCBiZWdpbj0i77u/IiBpZD0iVzVNME1wQ2VoaUh6cmVTek5UY3prYzlkIj8+IDx4OnhtcG1ldGEgeG1sbnM6eD0iYWRvYmU6bnM6bWV0YS8iIHg6eG1wdGs9IkFkb2JlIFhNUCBDb3JlIDUuMC1jMDYwIDYxLjEzNDc3NywgMjAxMC8wMi8xMi0xNzozMjowMCAgICAgICAgIj4gPHJkZjpSREYgeG1sbnM6cmRmPSJodHRwOi8vd3d3LnczLm9yZy8xOTk5LzAyLzIyLXJkZi1zeW50YXgtbnMjIj4gPHJkZjpEZXNjcmlwdGlvbiByZGY6YWJvdXQ9IiIgeG1sbnM6eG1wPSJodHRwOi8vbnMuYWRvYmUuY29tL3hhcC8xLjAvIiB4bWxuczp4bXBNTT0iaHR0cDovL25zLmFkb2JlLmNvbS94YXAvMS4wL21tLyIgeG1sbnM6c3RSZWY9Imh0dHA6Ly9ucy5hZG9iZS5jb20veGFwLzEuMC9zVHlwZS9SZXNvdXJjZVJlZiMiIHhtcDpDcmVhdG9yVG9vbD0iQWRvYmUgUGhvdG9zaG9wIENTNSBNYWNpbnRvc2giIHhtcE1NOkluc3RhbmNlSUQ9InhtcC5paWQ6RDJBRkM5MEJCRDYzMTFFMzg3QTI5QzAyREFEMDY4ODciIHhtcE1NOkRvY3VtZW50SUQ9InhtcC5kaWQ6RDJBRkM5MENCRDYzMTFFMzg3QTI5QzAyREFEMDY4ODciPiA8eG1wTU06RGVyaXZlZEZyb20gc3RSZWY6aW5zdGFuY2VJRD0ieG1wLmlpZDpEMkFGQzkwOUJENjMxMUUzODdBMjlDMDJEQUQwNjg4NyIgc3RSZWY6ZG9jdW1lbnRJRD0ieG1wLmRpZDpEMkFGQzkwQUJENjMxMUUzODdBMjlDMDJEQUQwNjg4NyIvPiA8L3JkZjpEZXNjcmlwdGlvbj4gPC9yZGY6UkRGPiA8L3g6eG1wbWV0YT4gPD94cGFja2V0IGVuZD0iciI/PqkSL9IAAAzuSURBVHja7F0PjBRn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">
			扑火流萤
		</p>
		<ul id="menu">
			<li class="iconfont"><label class="i">&#xf00de;</label><label class="t"><a target="_blank" href="iconfont/demo.html">图标库</a></label></li>
			<li class="iconfont"><label class="i">&#xf00fc;</label><label class="t">资源库</label></li>
			<li class="iconfont"><label class="i">&#xf0020;</label><label class="t">上传</label></li>
		</ul>
	</div>
	<div id="page_bottom">
	</div>
	
	<div id="weather">
		<div>城市&nbsp;:&nbsp;&nbsp;<select id="citys"></select></div>
		<div><%=request.getSession().getServletContext().getRealPath("/upload/store/") %></div>
	</div>
	<div id="uploadWin"></div>
	<div id="uploadWin2"></div>
	
</body>
<script type="text/javascript" src="src.common/js/jquery-2.1.4.min.js"></script>
<script type="text/javascript" src="src.common/js/jquery.ft.window.js"></script>
<script type="text/javascript">


	$(function(){
		/*
		 *天气数据查询
		 */
		$.post('src.common/json/code.city.weather.json',{},function(data){
			var $citys = $("#citys");
			$(data).each(function(i,obj){
				var $city = $("<option></option>");
				$city.val(obj.code);
				$city.text(obj.name);
				if(obj.name==="闵行"){
					$city.attr('selected','selected');
				}
				$city.appendTo($citys);
			});
			$citys.change(function(){
				var val = $(this).val();
				$.get('http://www.weather.com.cn/adat/sk/'+val+'.html',{},function(data){
					alert(data);
				},'json');
			});
		},'json');
		
		$("#uploadWin").ftWindow({title:'文件上传',css:{width:'450px',height:'300px'},dataMode:'server',url:'upload/upload.jsp'});

		
	});
</script>
</html>