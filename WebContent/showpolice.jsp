<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.io.*,java.util.*"%>

<%
	//显示报警数据
	File f = new File("/alidata1/default/ROOT/police/police.txt");
	try {
		FileInputStream file = new FileInputStream(f);
		InputStreamReader in = new InputStreamReader(file, "utf-8");
		BufferedReader br = new BufferedReader(in);
		StringBuffer sb=new StringBuffer();
		String s = "";
		while (true) {
			s = br.readLine();
			if (null == s || s.equals("")) {
				break;
			} else {
				sb.insert(0, s+"</br>");
			}
		}
		out.print(sb.toString());
	} catch (FileNotFoundException e) {
	} catch (UnsupportedEncodingException e) {
	} catch (IOException e) {
	}
%>