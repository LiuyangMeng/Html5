package com.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class MySecServlet extends HttpServlet {
	@Override
	public void init() throws ServletException {
		System.out.println("mysecservlet init");
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {

		/*
		 * System.out.println(req.getPathInfo());// /aa.html
		 * System.out.println(req.getRequestURI());//
		 * /TestServer/mysecservlet/aa.html
		 * System.out.println(req.getContextPath());// /TestServer
		 * System.out.println(req.getPathTranslated());//
		 * D:\workspaces\.metadata\.plugins\org.eclipse.wst.server.core\tmp1\
		 * wtpwebapps\TestServer\aa.html
		 * System.out.println(req.getServletPath());// /mysecservlet
		 * System.out.println(req.getRequestURL());//
		 * http://localhost/TestServer/mysecservlet/aa.html
		 */
		System.out.println("secservlet doget come in");
		// req.getRequestDispatcher("/about.html").forward(req, resp);

		String pathinfo = req.getPathInfo();
		// 首页
		if (null == pathinfo || pathinfo.trim().equals("/")) {
			homePage(req, resp);
			return;
			// 非法请求
		} else if (pathinfo.indexOf(".") == -1) {
			System.out.print("no . ");
			noAuth(req, resp);
			return;
		}

		pathinfo = pathinfo.substring(1, pathinfo.lastIndexOf(".")).trim();

		switch (pathinfo) {
		case "about":
			aboutPage(req, resp);
		case "maxconn":
			maxConn(req, resp);
		case "getlogs":
			getLogs(req, resp);
		default:
			noAuth(req, resp);
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}

	private void getLogs(HttpServletRequest request, HttpServletResponse response) throws IOException {
		PrintWriter pw = response.getWriter();
		while (true) {
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e1) {
				e1.printStackTrace();
			}
			int rand = (int) Math.round(Math.random() * 100);
			// 有新信息
			if (rand > 30 && rand < 60) {
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				StringBuffer sb = new StringBuffer();
				sb.append("This is new logs," + rand + ":");
				sb.append(sdf.format(new Date()) + "<br>");
				response.setCharacterEncoding("UTF-8");
				System.out.println(sb.toString());
				pw.write(sb.toString());
				pw.flush();
				pw.close();
				break;
				// 无新信息
			} else {
				System.out.println("no new logs:" + rand);
				try {
					Thread.sleep(3000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}

	private void homePage(HttpServletRequest request, HttpServletResponse response) {
		System.out.println("请求主页");
		response.setCharacterEncoding("UTF-8");
		// response.setCharacterEncoding("GBK");
		PrintWriter pw;
		try {
			pw = response.getWriter();
			pw.write("主页数据展示");
			pw.flush();
			pw.close();
		} catch (IOException e) {
			e.printStackTrace();
			// 错误页面，暂时用非法权限 页面替代
			noAuth(request, response);
		}
	}

	private void maxConn(HttpServletRequest request, HttpServletResponse response) {
		long start = System.currentTimeMillis();
		System.out.println("start:" + start);
		while (true) {
		}
	}

	private void aboutPage(HttpServletRequest request, HttpServletResponse response) throws IOException {
		response.sendRedirect("/TestServer/about.html");
	}

	private void noAuth(HttpServletRequest request, HttpServletResponse response) {
		System.out.println("无效链接:" + request.getPathInfo());
	}
	
	private void errorPage(HttpServletRequest request,HttpServletResponse response){
		System.out.println("服务器内部错误："+request.getPathInfo());
		//后期可在此处封装错误信息
	}

}
