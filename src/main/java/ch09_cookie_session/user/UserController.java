package ch09_cookie_session.user;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.List;

@WebServlet({"/ch09/user/list", "/ch09/user/register", "/ch09/user/update"
	, "/ch09/user/delete", "/ch09/user/login", "/ch09/user/logout"})
public class UserController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private UserService usvc = new UserServiceImpl();
    
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String requestUri = request.getRequestURI(); // 현재 주소 리턴
		String[] uri = requestUri.split("/"); // 슬래시로 스플릿하여 구성요소(jw, ch07, kcity 등)가 나눠짐
		String action = uri[uri.length - 1]; // kcity 까지는 공통요소이므로 uri의 마지막 요소를 사용
		
		String method = request.getMethod(); // insert 등은 post와 get 모두 있으므로 구분하기 위해 사용
		
		HttpSession session = request.getSession(); // 로그인 상태를 판별하기 위한 객체
		
		//// 각종 변수나 객체 선언부 ////
		RequestDispatcher rd = null; 
		String uid = null;
		String pwd = null;
		String pwd2 = null;
		String email = null;
		String uname = null;
		String msg = null;
		String url = null;
		User u = null;
		/////////////////////////////////
		
		switch (action) {
		case "list":
			String page_ = request.getParameter("page");
			int page = (page_ == null || page_.equals("")) ? 1 : Integer.parseInt(page_);
			
			List<User> list = usvc.getUserList(page);
			request.setAttribute("list", list);
			rd = request.getRequestDispatcher("/ch09/user/list.jsp");
			rd.forward(request, response);
			break;
		case "login":
			if (method.equals("GET"))
			{
				// 로그인 화면 띄우기
				rd = request.getRequestDispatcher("/ch09/user/login.jsp");
				rd.forward(request, response);
			}
			else 
			{
				// 화면의 form에서 입력받은 데이터를 가지고 로그인 성공 여부를 판단한 다음
				// 성공할 경우 세션의 값을 설정하고 리스트로
				// 실패할 경우 알맞은 메시지를 띄우고 로그인 화면으로
				uid = request.getParameter("uid");
				pwd = request.getParameter("pwd");
				int result = usvc.login(uid, pwd);
				
				if (result == usvc.CORRECT_LOGIN)
				{
					u = usvc.getUserByUid(uid);
					
					// 로그인 성공했으므로 세션에 값을 설정
					session.setAttribute("sessUid", uid);
					session.setAttribute("sessUname", u.getUname());
					
					msg = u.getUname() + "님 환영합니다.";
					url = "/jw/ch09/user/list?page=1"; // 초기 화면으로 가는 url
				}
				else if (result == usvc.WRONG_PASSWORD)
				{
					msg = "패스워드가 틀립니다";
					url = "/jw/ch09/user/login";
				}
				else
				{
					msg = "ID 입력이 잘못되었습니다";
					url = "/jw/ch09/user/login";
				}
				
				// 알림창 띄우기
				rd = request.getRequestDispatcher("/ch09/user/alertMsg.jsp");
				request.setAttribute("msg", msg);
				request.setAttribute("url", url);
				rd.forward(request, response);
			}
			break;
		case "logout":
			// 세션을 비활성화하고 리스트로 돌아가기
			session.invalidate(); 
			response.sendRedirect("/jw/ch09/user/list?page=1");
			break;
		case "register":
			if (method.equals("GET"))
			{
				rd = request.getRequestDispatcher("/ch09/user/register.jsp");
				rd.forward(request, response);
			}
			else
			{
				uid = request.getParameter("uid");
				pwd = request.getParameter("pwd");
				pwd2 = request.getParameter("pwd2");
				uname = request.getParameter("name_reg");
				email = request.getParameter("email");
				
				if (!pwd.equals(pwd2))
				{
					msg = "패스워드가 잘못 입력되었습니다";
					url = "/jw/ch09/user/register";
					
					rd = request.getRequestDispatcher("/ch09/user/alertMsg.jsp");
					request.setAttribute("msg", msg);
					request.setAttribute("url", url);
					rd.forward(request, response);
				}
				else
				{
					u = new User(uid, pwd, uname, email);
					usvc.registerUser(u);
					
					msg = "가입을 환영합니다";
					url = "/jw/ch09/user/login";
					
					rd = request.getRequestDispatcher("/ch09/user/alertMsg.jsp");
					request.setAttribute("msg", msg);
					request.setAttribute("url", url);
					rd.forward(request, response);
				}				
			}
			break;
		case "update":
			if (method.equals("GET"))
			{
				uid = request.getParameter("uid");
				u = usvc.getUserByUid(uid);
				
				rd = request.getRequestDispatcher("/ch09/user/update.jsp");
				request.setAttribute("user", u);
				rd.forward(request, response);
			}
			else
			{
				uid = request.getParameter("uid");
				pwd = request.getParameter("pwd");
				pwd2 = request.getParameter("pwd2");
				uname = request.getParameter("name");
				email = request.getParameter("email");
				
				if (!pwd.equals(pwd2))
				{
					msg = "패스워드가 잘못 입력되었습니다";
					url = "/jw/ch09/user/update";
					
					rd = request.getRequestDispatcher("/ch09/user/alertMsg.jsp");
					request.setAttribute("msg", msg);
					request.setAttribute("url", url);
					rd.forward(request, response);
				}
				else if (usvc.getUserByUid(uid) != null)
				{
					msg = "이미 존재하는 계정입니다";
					url = "/jw/ch09/user/update";
					
					rd = request.getRequestDispatcher("/ch09/user/alertMsg.jsp");
					request.setAttribute("msg", msg);
					request.setAttribute("url", url);
					rd.forward(request, response);
				}
				else
				{
					u = new User(uid, pwd, uname, email);
					usvc.updateUser(u);
					
					msg = "회원 정보가 수정되었습니다";
					url = "/jw/ch09/user/list?page=1";
					
					rd = request.getRequestDispatcher("/ch09/user/alertMsg.jsp");
					request.setAttribute("msg", msg);
					request.setAttribute("url", url);
					rd.forward(request, response);
				}			
			}
			break;
		case "delete":
			uid = request.getParameter("uid");
			usvc.deleteUser(uid);
			
			response.sendRedirect("/jw/ch09/user/list");
			break;
		default:
			rd = request.getRequestDispatcher("/ch09/user/alertMsg.jsp");
			request.setAttribute("msg", "잘못된 접근입니다");
			request.setAttribute("url", "/jw/ch09/user/list");
			rd.forward(request, response);
		}
	}

}
