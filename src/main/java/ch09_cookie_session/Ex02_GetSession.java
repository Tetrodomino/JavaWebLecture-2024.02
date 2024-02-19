package ch09_cookie_session;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;

@WebServlet("/ch09/getSession")
public class Ex02_GetSession extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		
		int price = (Integer) session.getAttribute("price"); // price 항목에 저장된 데이터 유형은 object라서 형변환 필요
		String uid = (String) session.getAttribute("uid");
		String[] fruits = (String[]) session.getAttribute("fruits");
		System.out.println(Arrays.toString(fruits));
		
//		String sessionId = session.getId();
//		int interval = session.getMaxInactiveInterval();
		
//		PrintWriter out = response.getWriter();
//		response.setContentType("text/html; charset=utf-8");
//		out.print("<h1> price: " + price + ", uid: " + uid + "</h1>");
//		out.print("<h1>" + Arrays.toString(fruits) + "</h1>");
//		out.print("<h1>id: " + sessionId);
//		out.print("<h1>MaxInactiveInterval: " + interval); // 최대 세션 지속시간
		
		RequestDispatcher rd = null;
		rd = request.getRequestDispatcher("/ch09/session.jsp");
		request.setAttribute("price", price);
		request.setAttribute("uid", uid);
		request.setAttribute("fruits", fruits);
		rd.forward(request, response);			
	}
}
