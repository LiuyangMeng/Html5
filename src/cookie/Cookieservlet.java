package cookie;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Cookieservlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public void init() throws ServletException {
		System.out.println("cookieservlet init");
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String pathinfo = req.getPathInfo();
		// 首页
		if (null == pathinfo || pathinfo.trim().equals("/")) {
			loginPage(req, resp);
			return;
			// 非法请求
		} else if (pathinfo.indexOf(".") == -1) {
			noAuth(req, resp);
			return;
		}

		pathinfo = pathinfo.substring(1, pathinfo.lastIndexOf(".")).trim();

		switch (pathinfo) {
		case "login":
			loginPage(req, resp);
			return;
		case "loginUser":
			loginUser(req, resp);
			return;
		default:
			noAuth(req, resp);
			return;
		}
	}

	private void loginPage(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		System.out.println("请求登陆主页");
		// 默认不记住密码
		request.setAttribute("rempwd", "0");
		// 获取该域名cookie
		Cookie[] cookies = request.getCookies();
		// 如果请求中存在cookie
		if (null != cookies) {
			// 遍历cookies，获取到自己的cookie
			for (Cookie cookie : cookies) {
				if (cookie.getName().equals("test-user")) {
					String value = cookie.getValue();
					System.out.println("cookie-value:" + value);
					// 存在cookie值
					if (null != value && !"".equals(value.trim())) {
						// 用户名
						request.setAttribute("username", value.split("-")[0]);
						if (value.split("-").length == 2 && null != value.split("-")[1]
								&& !value.split("-").equals("null")) {
							// 密码，此处未加密
							request.setAttribute("userpwd", value.split("-")[1]);
							// 记住密码，默认选中
							request.setAttribute("rempwd", "1");
						}
					}
				}
			}
		}
		// 跳转到登陆接界面
		request.getRequestDispatcher("/cookie/loginc.jsp").forward(request, response);
	}

	private void loginUser(HttpServletRequest request, HttpServletResponse response) {
		String rempwd = request.getParameter("rempwd");
		if (null == rempwd) {
			rempwd = "0";
		}

		String username = request.getParameter("username");
		String userpwd = request.getParameter("userpwd");
		System.out.println("loginUser：" + username + ":" + userpwd + ":" + rempwd);

		StringBuffer sbf = new StringBuffer();

		sbf.append(username);

		if (rempwd.equals("1")) {
			sbf.append("-").append(userpwd);
		}

		Cookie cookie = new Cookie("test-user", sbf.toString());
		cookie.setMaxAge(60 * 60 * 24 * 7);
		response.addCookie(cookie);
		response.setContentType("text/html;charset=UTF-8");

		PrintWriter pw;
		try {
			pw = response.getWriter();
			pw.write("登陆成功");
			pw.flush();
			pw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void noAuth(HttpServletRequest request, HttpServletResponse response) {
		System.out.println("无效链接:" + request.getPathInfo());

	}
}
