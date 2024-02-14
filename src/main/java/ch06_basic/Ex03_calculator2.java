package ch06_basic;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/ch06/calc2")
public class Ex03_calculator2 extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	// 시작할 때의 주소는 localhost:8080/jw/ch06/calc2
	// localhost:8080/jw/ch06/calc2?num1=3&op=+&num2=6&result=9
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int num1 = 0, num2 = 0, result = 0;
		
		/////////////// 방법 2에서 보내준 주소의 파라메터값을 받아서 값 변경 /////////////////
		String num1_ = request.getParameter("num1");
		String num2_ = request.getParameter("num2");
		String result_ = request.getParameter("result");
		
		num1 = (num1_ == null) ? 0 : Integer.parseInt(num1_);
		num2 = (num2_ == null) ? 0 : Integer.parseInt(num2_);
		result = (result_ == null) ? 0 : Integer.parseInt(result_);
		//////////////////////////////////////////////////////////////////////////////////////
		
		RequestDispatcher rd = request.getRequestDispatcher("/ch06/calc.jsp");
		request.setAttribute("num1", num1);
		request.setAttribute("num2", num2);
		request.setAttribute("result", result);
		rd.forward(request, response);
	}

	// html 코드 등으로 실행될 때 실행됨
	// <form action="/jw/ch06/calc2" method="post">
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String num1_ = request.getParameter("num1"); // num1이라는 파라메터를 String num1_ 로 받기
		String op = request.getParameter("op");
		String num2_ = request.getParameter("num2");
		
		int num1 = (num1_.equals("") || num1_ == null) ? 0 : Integer.parseInt(num1_);
		int num2 = (num2_.equals("") || num2_ == null) ? 0 : Integer.parseInt(num2_);
		
		int result = 0;
		switch(op)
		{
		case "+":
			result = num1 + num2;
			break;
		case "-":
			result = num1 - num2;
			break;
		case "*":
		case "×":
			result = num1 * num2;
			break;
		case "/":
		case "÷":
			result = num1 / num2;
			break;
		default:
			result = -999999;
		}
		
		// 방법 1
		// calc.jsp를 직접 호출하여
		// num1, num2, result 속성을 가진 태그의 값을 변경시키기 (ex: calc.jsp에 있는 ${num1}의 값을 num1로 넣음)
		RequestDispatcher rd = request.getRequestDispatcher("/ch06/calc.jsp");
		request.setAttribute("num1", num1);
		request.setAttribute("num2", num2);
		request.setAttribute("result", result);
		rd.forward(request, response);
		
		
		// 방법 2
		// redirection(리디렉션) 이용
		// 주소를 설정하여 주소창에 보내주기
//		String url = "/jw/ch06/calc2?num1="+ num1 + "&num2=" + num2 + "&result=" + result;
//		response.sendRedirect(url);
	}

}
