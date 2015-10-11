<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>[扑火流萤]文件上传</title>
</head>
<body>

<form action="../upload" method="post" enctype="multipart/form-data" name="book">
<table>
	<tr>
		<th colspan="2" align="center">录入书籍信息</th>
	</tr>
	<tr>
		<td>书名：</td>
		<td><input type="text" name="bookname" /></td>
	</tr>
	<tr>
		<td>封面：</td>
		<td><input type="file" name="cover" /></td>
	</tr>
	<tr>
		<td>作者：</td>
		<td><input type="text" name="author" /></td>		
	</tr>
	<tr>
		<td colspan="2"><input type="submit" value="提交" /></td>
	</tr>
</table>
</form>


</body>
</html>