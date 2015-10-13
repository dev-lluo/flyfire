<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>[扑火流萤]文件上传</title>
<link rel="stylesheet" type="text/css" href="../src.common/css/reset.css"/>
<link rel="stylesheet" type="text/css" href="../src.common/css/icon.css"/>
<style>
	*{
		font: 12px/1 "Hiragino Sans GB","Microsoft YaHei","WenQuanYi Micro Hei",sans-serif;
		text-align: center;
	}
	.upload-hide{
		display:none;
	}
	.upload-view{
		display:inline-block;
		width:90%;
		height:25px;
		border:1px solid gray;
		boxShadow:'1px 1px 3px #292929';
		background-color:#eee;
		border-radius:3px;
		text-align:center;
		line-height:25px;
	}
</style>
</head>
<body>

<form action="../upload.do" method="post" enctype="multipart/form-data" id="upload-form">
			<label for="upload" class="upload-view">选择文件</label>
			<input id="upload" class="upload-hide" type="file" name="upload" onchange="push()" />
			<input id="timestamp" type="hidden" name="timestamp" />
</form>
<script type="text/javascript">
	function push(){
		var form = document.getElementById('upload-form');
		form.style.display = 'none';
		if(typeof window.parent.progress === 'function'){
			var timestamp = new Date().getTime();
			document.getElementById('timestamp').value = timestamp;
			window.parent.progress(timestamp);
		}
		form.submit();
	}
</script>

</body>
</html>