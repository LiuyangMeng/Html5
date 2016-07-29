<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>  
<%@ page import="ztree.*" %>  
<%   
    String id = request.getParameter("id") ;  
    System.out.println("得到的节点id: " + id) ;  
    ztreeTest demo = new ztreeTest() ;  
    String json = demo.getJsonData() ;  
    out.print(json) ;  
%> 