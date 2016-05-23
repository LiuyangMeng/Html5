<%@page import="org.apache.jasper.tagplugins.jstl.core.ForEach"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.io.*,java.util.*"%>

<%
Object obj=application.getAttribute("initmap");
if(obj!=null&&((List<String>)obj).size()>0){
	List<String> map =(List<String>)obj;
	out.print(map.get(0));
	map.remove(0);
	application.setAttribute("initmap", map);
//	out.print(map.size());
}else{
	out.print("-1");
}
%>