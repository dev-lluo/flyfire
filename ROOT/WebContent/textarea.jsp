<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<link rel="stylesheet" type="text/css" href="src.common/css/reset.css"/>
<link rel="stylesheet" type="text/css" href="src.common/css/icon.css"/>

<script type="text/javascript" src="src.common/js/jquery-1.11.3.min.js"></script>

<script type="text/javascript" src="src.common/js/jquery.ft.base.js"></script>
<script type="text/javascript" src="src.common/js/jquery.ft.textarea.js"></script>

<title>Insert title here</title>
</head>
<body style="width:100%;height:600px;">

<div id="editor1"></div>
<div id="editor2"></div>

<script type="text/javascript">
$(function(){
	$('#editor1').ftTextarea();
	$('#editor2').ftTextarea();
});
</script>
</body>
</html>