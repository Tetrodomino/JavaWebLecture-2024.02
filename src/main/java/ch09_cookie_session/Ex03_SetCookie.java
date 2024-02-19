package ch09_cookie_session;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLEncoder;
import java.util.Date;

@WebServlet("/ch09/set_Cookie")
public class Ex03_SetCookie extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		response.setContentType("text/html; charset=utf-8");
		
		Cookie c1 = new Cookie("cookie-name", "cookie-value"); // 인자의 순서대로 쿠키의 이름, 쿠기의 값
		c1.setMaxAge(24 * 60 * 60); // 유효기간 1일(86400초)
		response.addCookie(c1);
		
		String kmsg = URLEncoder.encode("한글 데이터", "utf-8");
		Cookie c2 = new Cookie("hangul-name", kmsg); //그냥 한글을 넣으면 한글이 깨짐
		c2.setMaxAge(-1); // 브라우저가 닫히면 삭제되는 지속시간
		response.addCookie(c2);
		
		out.write("<h1>현재시간: " + new Date() + "</h1>");
		
		
	}

}
