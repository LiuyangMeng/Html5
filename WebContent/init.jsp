<%@page import="org.apache.jasper.tagplugins.jstl.core.ForEach"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.io.*,java.util.*"%>

<%
List<String> list=new ArrayList<String>();
for (int i = 1; i <200; i++) {
	list.add("give: "+i);
}
application.setAttribute("initmap", list);
out.print(list.size());
%>