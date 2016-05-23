<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.io.*,java.util.*"%>

<%
	//获取账号信息
	File f = new File("/alidata1/default/ROOT/appleids/appleids.txt");
	Object allcount = application.getAttribute("allcount");
	if (null == allcount) {
		application.setAttribute("allcount", 4555);
		allcount = 4555;
	}

	Object count = application.getAttribute("count");
	if (null == count) {
		application.setAttribute("count", 1);
		count = 1;
	}

	if (Integer.parseInt(count.toString()) > Integer.parseInt(allcount.toString())) {
		count = Integer.parseInt(count.toString()) - Integer.parseInt(allcount.toString());
	}

	application.setAttribute("count",
			Integer.parseInt(count.toString()) + 1);
	String str = "";
	try {
		FileInputStream file = new FileInputStream(f);
		InputStreamReader in = new InputStreamReader(file, "utf-8");
		BufferedReader br = new BufferedReader(in);

		String s = "";
		int sum = Integer.parseInt(count.toString());
		while (true) {
			sum--;
			s = br.readLine();
			if (null == s || s.equals("")) {
				break;
			} else {
				if (sum == 0) {
					str = "{\"appleids\":{\"passwd\":\""
							+ s.split("><")[1] + "\",\"appleid\":\""
							+ s.split("><")[0]
							+ "\",\"product_model\":\""
							+ s.split("><")[3]
							+ "\",\"product_type\":\""
							+ s.split("><")[2] + "\",\"uuid\":\""
							+ s.split("><")[5] + "\",\"os_version\":\""
							+ s.split("><")[4] + "\",\"count\":"
									+count + "}}";
				}
			}
		}

	} catch (FileNotFoundException e) {
	} catch (UnsupportedEncodingException e) {
	} catch (IOException e) {
	}
	out.print(str);
%>