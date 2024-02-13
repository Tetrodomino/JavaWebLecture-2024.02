package ch05;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

// 웹 브라우저의 주소창에 localhost:8080/jw/hello 를 입력하면 처리됨
@WebServlet({"/hello", "/ch05/hello"}) // hello와 ch05/hello 모두 가능
public class Ex01_FirstSevlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	// Get 방식의 요청이 왔을 때 처리하는 코드
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String html = "<!DOCTYPE html>"
				+ "<html>"
				+ "<head>"
				+ "<meta charset=\"UTF-8\">"
				+ "<title>첫 번째 서블릿Sevlet</title>"
				+ "</head>"
				+ "<body>"
				+ "	<h1>자바 서블릿에서 작성한 코드</h1>"
				+ "</body>"
				+ "</html>";
		response.setContentType("text/html; charset=utf-8");
		PrintWriter out = response.getWriter();
		out.print(html);
	}

	/**
	 *	Post 요청이 왔을 때 처리하는 코드
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
