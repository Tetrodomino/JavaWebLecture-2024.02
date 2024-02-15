package ch06_basic;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;

// 연결된 파일은 registerForm.jsp
// 주소 주기
@WebServlet("/ch06/register")
public class Ex04_RegisterMember extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	// form을 제공해주는 역할을 하는 Get
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher rd = request.getRequestDispatcher("/ch06/registerFormBootstrap.jsp");
		rd.forward(request, response); // 화면이 띄워지게 하는 forward
	}

	// 입력받은 form을 처리해주는 역할
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// form 내부 input 태그의 값들을 String으로 저장
		String uid = request.getParameter("uid"); // registerForm.jsp의 name속성이 uid인 태그의 값 가져오기
		String pwd = request.getParameter("pwd");
		//String pwd2 = request.getParameter("pwd2");
		String name = request.getParameter("name_reg");
		String email = request.getParameter("email");
		
		// uid가 있는지 확인
		// pwd와 pwd2가 일치하는지 확인
		// 입력된 값으로 User 객체 만들기
		User user = new User(uid, pwd, name, email, LocalDate.now(), 0);
		
		// UserService의 registerMember를 호출(DB에 등록)
		
		// 환영 메세지와 함께 로그인 창으로 전송
		// register 창에서 submit 버튼을 누를 경우 registerResult.jsp가 실행됨
		RequestDispatcher rd = request.getRequestDispatcher("/ch06/registerResult.jsp");
		request.setAttribute("user", user); // user 객체 자체를 registerResult.jsp에 보냄, 이후의 처리는 해당 파일에 있음
		rd.forward(request, response);
		
	}

}
