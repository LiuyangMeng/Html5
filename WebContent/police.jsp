<%@page import="java.util.Date"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.net.URLDecoder"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.io.*"%>

<%
	//报警机制，添加数据
	File f = new File("/alidata1/default/ROOT/police/police.txt");
	if(!f.exists()){
		f.createNewFile();
	}

	String soid=request.getParameter("soid");
	if(null==soid||soid.trim().isEmpty()){
		soid="00000000";
	}
	String msg=request.getParameter("msg");
	if(null==msg){
		msg="empty";
	}
	msg=URLDecoder.decode(msg, "UTF-8");
	
	SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	String date=sdf.format(new Date());
	
	
	FileWriter fw = null;
	BufferedWriter bw = null;
	try {

		fw = new FileWriter(f, true);
		bw = new BufferedWriter(fw);

		bw.write(date+"\t|\t"+soid+"\t|\t"+msg+"\r\n");
		bw.flush();
		bw.close();
		fw.close();
		out.print("{\"code\":0}");
		
		Object count=application.getAttribute("count");
		if(null==count){
			application.setAttribute("count", 0);
			count="0";
		}
		application.setAttribute("count", Integer.parseInt(count.toString())+1);
		
		out.print(application.getAttribute("count"));
		
	} catch (FileNotFoundException e) {
	} catch (UnsupportedEncodingException e) {
	}
%>