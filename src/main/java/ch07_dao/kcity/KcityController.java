package ch07_dao.kcity;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import ch07_dao.City;
import ch07_dao.CityDao;

/*
 * 사이트 구성
 * 
 * 메인 페이지 하나에 도시 리스트를 띄우고 거기에 추가나 삭제 등의 버튼을 추가하는 것
 * */
// 여러개의 주소를 라우팅할 때는 중괄호를 이용해서 집합처럼 사용
// @WebSerblet("/ch07/kcity/*")을 통해 모든 하위 주소를 사용하는 것도 가능
@WebServlet({"/ch07/kcity/list", "/ch07/kcity/insert", "/ch07/kcity/update",
	"/ch07/kcity/delete", "/ch07/kcity/wrong"}) // 4가지 주소를 해당 파일에서 다룸, wrong은 default를 실행하기 위해 넣은 더미
public class KcityController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private CityDao cDao = new CityDao();
	
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String requestUri = request.getRequestURI(); // 현재 주소 리턴
		String[] uri = requestUri.split("/"); // 슬래시로 스플릿하여 구성요소(jw, ch07, kcity 등)가 나눠짐
		String action = uri[uri.length - 1]; // kcity 까지는 공통요소이므로 uri의 마지막 요소를 사용
		
		String method = request.getMethod(); // insert 등은 post와 get 모두 있으므로 구분하기 위해 사용
		
		//// 각종 변수나 객체 선언부 ////
		RequestDispatcher rd = null; 
		String district = null;
		String name = null;
		String countryCode = null;
		String population_ = null;
		int population = 0;
		City city = null;
		int id = 0;
		String[] districts = "Cheju,Chollabuk,Chollanam,Chungchongbuk,Chungchongnam,Inchon,Kang-won,Kwangju,Kyonggi,Kyongsangbuk,Kyongsangnam,Pusan,Seoul,Taegu,Taejon".split(",");
		/////////////////////////////////
		
		// action의 값에 따라 페이지마다 다른 작업 수행
		// 이 방식을 통해 list, insert 등의 주소마다 다른 작업을 수행할 수 있음
		switch(action) {
		case "list":
			district = request.getParameter("district");
			district = (district == null || district.equals("")) ? "Cheju" : district; // 필터링 할 district 속성
			String num_ = request.getParameter("num");
			int num = (num_ == null || num_.equals("")) ? 30 : Integer.parseInt(num_); // 페이지에 띄울 최대 항목 개수
			String offset_ = request.getParameter("offset");
			int offset = (offset_ == null || offset_.equals("")) ? 0 : Integer.parseInt(offset_);
			
			List<City> list = cDao.getCityList(district, num, offset);
			rd = request.getRequestDispatcher("/ch07/kcity/list.jsp");
			request.setAttribute("list", list);
			rd.forward(request, response);
			break;
		case "insert":
			if (method.equals("GET"))
			{
				rd = request.getRequestDispatcher("/ch07/kcity/insert.jsp");
				request.setAttribute("districts", districts);
				rd.forward(request, response);				
			}
			else
			{
				name = request.getParameter("name");
				countryCode = request.getParameter("countryCode");
				district = request.getParameter("district");
				population_ = request.getParameter("population");
				population = (population_.equals("")) ? 0 : Integer.parseInt(population_);
				
				city = new City(name, countryCode, district, population);
				cDao.insertCity(city);
				
				response.sendRedirect("/jw/ch07/kcity/list?district=" + district + "&num=30&offset=0");
			}
			break;
		case "update":
			if (method.equals("GET"))
			{
				id = Integer.parseInt(request.getParameter("id"));
				
				city = cDao.getCity(id);
				
				rd = request.getRequestDispatcher("/ch07/kcity/update.jsp");
				request.setAttribute("districts", districts);
				request.setAttribute("city", city);
				rd.forward(request, response);
			}
			else
			{
				id = Integer.parseInt(request.getParameter("id"));
				name = request.getParameter("name");
				countryCode = request.getParameter("countryCode");
				district = request.getParameter("district");
				population_ = request.getParameter("population");
				population = (population_.equals("")) ? 0 : Integer.parseInt(population_);
				
				city = new City(id, name, countryCode, district, population);
				
				cDao.updateCity(city);
				
				response.sendRedirect("/jw/ch07/kcity/list?district=" + district + "&num=30&offset=0");
			}
			break;
		case "delete":
			id = Integer.parseInt(request.getParameter("id"));
			
			cDao.deleteCity(id);
			
			response.sendRedirect("/jw/ch07/kcity/list?district=Kyonggi&num=30&offset=0");
			break;
		default:
			rd = request.getRequestDispatcher("/ch07/kcity/alertMsg.jsp");
			request.setAttribute("msg", "잘못된 접근입니다");
			request.setAttribute("url", "/jw/ch07/kcity/list");
			rd.forward(request, response);
		}
		
		PrintWriter out = response.getWriter();
		out.print(requestUri);
	}

}
