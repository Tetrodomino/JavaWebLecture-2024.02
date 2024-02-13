package ch06_basic;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

// localhost:8080/jw/ch06/params?id=101&title=title  ← 매개변수로 id랑 title이 존재하는 url
@WebServlet("/ch06/params")
public class Ex02_parameter extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		String id_ = req.getParameter("id");
		
		//// id값 받기 1 ////
//		int id = Integer.parseInt(id_); 		// 그냥 받으면 id 값을 넣지 않으면 에러(500) 발생
		
		//// id값 받기 2 ////
		int id = 0;								// 입력이 되지 않으면 사용하는 기본값
		if (id_ != null && !id_.equals(""))		// id값이 있을 때만 id값에 대입
			id = Integer.parseInt(id_);
		
		String title = req.getParameter("title"); // title값은 넣지 않아도 에러 발생하지 않음
		System.out.println("GET id: " + id + ", title: " + title);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		String id_ = req.getParameter("id");
		int id = 0;	
		if (id_ != null && !id_.equals(""))
			id = Integer.parseInt(id_);
		
		String title = req.getParameter("title"); // title값은 넣지 않아도 에러 발생하지 않음
		System.out.println("POST id: " + id + ", title: " + title);
	}

}

//localhost:8080/jw/ch06/params?id=&title=   ← 입력값은 ""
//localhost:8080/jw/ch06/params				 ← 입력값은 null