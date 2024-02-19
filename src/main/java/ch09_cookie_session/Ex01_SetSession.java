package ch09_cookie_session;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
//import java.io.PrintWriter;

@WebServlet("/ch09/setSession")
public class Ex01_SetSession extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		
//		PrintWriter out = response.getWriter();
//		out.print("<h1>isNew: " + session.isNew());
		
		
		session.setAttribute("price", 12500);
		session.setAttribute("uid", "james");
		String[] fruits = {"apple", "banana", "cherry"};
		session.setAttribute("fruits", fruits);
		
		session.setMaxInactiveInterval(60 * 60); // 세션 대기 시간 60분으로

		session.invalidate();
		
		//response.getWriter().append("served at: ").append(request.getContextPath());
		RequestDispatcher rd = request.getRequestDispatcher("/ch09/session.jsp");
		rd.forward(request, response);
	}
}
