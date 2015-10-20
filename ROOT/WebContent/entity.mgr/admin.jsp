<%@page import="java.util.Iterator"%>
<%@page import="flyfire.root.sql.picture.TblPic"%>
<%@page import="java.util.Map.Entry"%>
<%@page import="java.util.Set"%>
<%@page import="flyfire.root.sql.resolver.EntityResolver"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>

<table>
	<caption>实体类管理器
		<select id="entity_select">
		
		</select>
	</caption>
	<thead>
		<tr>
			
			<th>
				属性名
			</th>
			<th>
				列名
			</th>
			<th>
				ID
			</th>
			<th>
				唯一
			</th>
			<th>
				最大值
			</th>
			<th>
				最小值
			</th>
			<th>
				默认值
			</th>
		</tr>
	</thead>
	<tbody id="show_details">
		
	</tbody>
	<tfoot>
		<tr>
			<td>
				<input type="button" value="创建表" id="crt_table"/>
			</td>
			<td>
				<input type="button" value="插入" id="ins_table"/>
			</td>
			<td>
				<input type="button" value="删除" id="del_table"/>
			</td>
			<td>
				<input type="button" value="修改" id="upd_table"/>
			</td>
			<td>
				<input type="button" value="查询" id="sel_table"/>
			</td>
		</tr>
	</tfoot>
</table>
<textarea id="result" cols="40" rows="10"></textarea>
</body>
<script type="text/javascript" src="../src.common/js/jquery-2.1.4.min.js"></script>
<script type="text/javascript">
	$(function(){
		
		$("#crt_table").click(function(){
			$.get("../entity.mgr/table.create",{clzz:$('#entity_select').val()},function(data){
				$('#result').val(data);
			},'text');
		});
		
		$("#upd_table").click(function(){
			$.get("../entity.mgr/table.update",{clzz:$('#entity_select').val()},function(data){
				$('#result').val(data);
			},'text');
		});
		
		$.get("../entity.mgr/tables",{},function(data){
			var $s = $("#entity_select");
			for(var a in data){
				var $o = $("<option></option>");
				$o.val(data[a]);
				$o.text(a);
				$o.appendTo($s);
			}
			$("#entity_select").change();
		},'json');
		$("#entity_select").change(function(){
			$("#show_details").html('');
			var val = $(this).val();
			$.get("../entity.mgr/table.details",{clzz:val},function(data){
				if(data.clmns){
					for(var a in data.clmns){
						var field = data.clmns[a];
						var $r = $("<tr></tr>");
						var $fieldName = $("<td></td>");
						$fieldName.text(field.jName);
						$fieldName.appendTo($r);
						
						var $clmnName = $("<td></td>");
						$clmnName.text(field.name);
						$clmnName.appendTo($r);
						
						var $id = $("<td></td>");
						$id.text(field.isId);
						$id.appendTo($r);
						
						var $un = $("<td></td>");
						$un.text(field.isUn);
						$un.appendTo($r);
						
						var $max = $("<td></td>");
						$max.text(field.maxVal);
						$max.appendTo($r); 
						
						var $min = $("<td></td>");
						$min.text(field.minVal);
						$min.appendTo($r);
						
						var $def = $("<td></td>");
						$def.text(field.defVal);
						$def.appendTo($r);
						
						$r.appendTo($("#show_details"));
					}
				}
			},'json');
		});
		
	});
</script>
</html>