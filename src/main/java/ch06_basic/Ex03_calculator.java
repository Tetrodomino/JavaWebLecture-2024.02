package ch06_basic;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/ch06/calc")
public class Ex03_calculator extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	// 주소의 형태가 아래와 같이 주소의 파라메터로 입력할 때 실행됨
	// localhost:8080/jw/ch06/calc?num1=3&op=+&num2=6   -- 3 + 6
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}

	// html 코드 등으로 실행될 때 실행됨
	// <form action="/jw/ch06/calc" method="post">
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
		
		PrintWriter out = response.getWriter();
		out.print(num1 + " " + op + " " + num2 + " = " + result);
	}

}
