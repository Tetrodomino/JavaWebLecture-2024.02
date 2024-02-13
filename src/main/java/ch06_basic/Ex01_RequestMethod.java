package ch06_basic;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/ch06/requestMethod")
public class Ex01_RequestMethod extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		
		/*
		 * request 정보를 통해 다양한 데이터를 얻을 수 있음
		 * 
		 * contextPath=/jw
		   method=GET
		   requestUri=/jw/ch06/requestMethod
		   serverName=localhost
		   servletPath=/ch06/requestMethod
		   pathInfo=null
		 * */
		String contextPath = req.getContextPath();
		String method = req.getMethod();
		String requestUri = req.getRequestURI();
		String serverName = req.getServerName();
		String servletPath = req.getServletPath();
		String pathInfo = req.getPathInfo();
		
		res.setContentType("text/html; charset=utf-8");
		PrintWriter out = res.getWriter();
		
		String html = "<!DOCTYPE html>"
				+ "<html>"
				+ "<head>"
				+ "<meta charset=\"UTF-8\">"
				+ "<title>Request Method</title>"
				+ "</head>"
				+ "<body>"
				+ "	<h1>HttpServletRequest의 다양한 메소드</h1>"
				+ " <hr>"
				+ " <ul>"
				+ "  <li>contextPath=" + contextPath + "</li>" // 각 항목을 출력하는 과정
				+ "  <li>method=" + method + "</li>"
				+ "  <li>requestUri=" + requestUri + "</li>"
				+ "  <li>serverName=" + serverName + "</li>"
				+ "  <li>servletPath=" + servletPath + "</li>"
				+ "  <li>pathInfo=" + pathInfo + "</li>"
				+ " </ul>"
				+ "</body>"
				+ "</html>";
		out.print(html);
	}


}
