<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="java.sql.ResultSetMetaData"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="com.neuedu.util.DBManager"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>

<script>

	var xhr = null;
	
	function getSQL(direction){
	
		xhr = new XMLHttpRequest();	
		
		xhr.open("get", "GetSQLServlet?direction=" + direction, true);
		
		xhr.send(null);
		
		//绑定回调函数
		xhr.onreadystatechange = getSQLResult;
	}
	
	//回调函数
	function getSQLResult(){
		
		if(xhr.readyState==4){
			
			if(xhr.status==200){
			
				var sql = document.getElementById("sql");
				
				sql.innerText = xhr.responseText;
			}
		}
	}
</script>
</head>
<body>

<%
	List<String> list = new ArrayList<String>();
	if(session.getAttribute("list")!=null){
		list = (List<String>)session.getAttribute("list");
	}
	
	int index = 0;
	if(session.getAttribute("index")!=null){
		index = (Integer)session.getAttribute("index");
	}
%>
<%
	String sql = request.getParameter("sql");

	if(sql==null){
		sql = "";
	}else{
		sql = sql.trim();
	}
%>

<form action="sql.jsp" method="post" >
	<textarea id="sql" rows="3" cols="50" name="sql"><%=sql%></textarea>
	<input type="submit" value="执行"/>
</form>

<button onclick="getSQL(-1)">上一条</button>
<button onclick="getSQL(1)">下一条</button>

<%
if(!"".equals(sql)){

	//将当前的SQL语句放入集合
	list.add(sql);
	session.setAttribute("list", list);

	index = list.size() - 1;
	session.setAttribute("index", index);

	DBManager dbManager = DBManager.getInstance();

	if(sql.startsWith("select")){
		
		ResultSet rs = dbManager.execQuery(sql);
		
		ResultSetMetaData rsmd = rs.getMetaData();
		
		int columnCount = rsmd.getColumnCount();
		
		out.print("<table border='1'>");
		
		out.print("<tr>");
		
		//打印表头
		for (int i = 1; i <= columnCount; i++) {
			out.print("<td>" + rsmd.getColumnName(i) + "</td>");
		}
		out.print("</tr>");
		
		while(rs.next()){
			
			out.print("<tr>");
			
			for (int i = 1; i <= columnCount; i++) {
				out.print("<td>" + rs.getObject(i) + "</td>");
			}
			
			out.print("</tr>");
		}
		
		out.print("</table>");
		
		dbManager.closeConnection();
		
	}else if(sql.startsWith("insert") || sql.startsWith("delete") || sql.startsWith("update")){
		
		if(dbManager.execUpdate(sql)){
			out.println("数据更新成功");
		}else{
			out.println("数据更新失败");
		}
	}else{
		out.println("SQL有误，请检查");
	}
}
%>

</body>
</html>