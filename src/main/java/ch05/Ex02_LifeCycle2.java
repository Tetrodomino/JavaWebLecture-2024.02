package ch05;

import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/ch05/lifecycle")
public class Ex02_LifeCycle2 extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	// 실행될 때 먼저 실행
	public void init(ServletConfig config) throws ServletException {
		System.out.println("init() method");
	}

	// 종료될 때 실행
	public void destroy() {
		System.out.println("destroy() method");
	}

	// 서비스 도중 실행
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("service() method");
		doGet(request, response);
	}

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("doGet() method start");
		response.getWriter().append("Served at: ").append(request.getContextPath());
		System.out.println("doGet() method stop");
	}

}
