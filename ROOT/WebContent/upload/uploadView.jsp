<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>文件上传</title>
</head>
<body>
	<div style="width:98%;height:auto;padding:1%;text-align: center;">
		<iframe src="upload/upload.jsp" id="iframeForUpload"></iframe>
		<input type="range" id="progress" value="0" style="display: none;" />
	</div>
	<script type="text/javascript">
		window.progress = function(options){
			$('#iframeForUpload').css('display','none');
			$("#progress").css('display','inline-block');
			setTimeout(function(){flushProgress(options);},1000);
		}
		function flushProgress(options){
			$.post('upload.do?type=progress',{'timestamp':options},function(data){
				$("#progress").val((data.pBytesRead/data.pContentLength)*100);
				if(!data.end){
					setTimeout(function(){flushProgress(options);}, 1000);
				}
			},'json');
		}
	</script>
</body>
</html>