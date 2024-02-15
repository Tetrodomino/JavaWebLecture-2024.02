package ch07_dao;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/ch07/city/search")
public class Ex01_search extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// id 파라메터 값(String)이 비어있지 않으면 그 값을 Int로 전환
		int id = (request.getParameter("id") == null) ? 2331 : Integer.parseInt(request.getParameter("id"));
		
		// 데이터베이스랑 연결된 CityDao 클래스를 호출하고
		// 번호에 해당되는 city 객체를 찾음
		CityDao cDao = new CityDao();
		City city = cDao.getCity(id);
		System.out.println(city);
		
		response.setContentType("text/html; charset=utf-8"); // 한글을 깨지지 않게 하기
		
		// 브라우저 화면에 출력시키기
		PrintWriter out = response.getWriter();
		out.print(city);
	}

}
