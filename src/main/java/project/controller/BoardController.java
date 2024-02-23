package project.controller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import project.entity.Board;
import project.entity.Reply;
import project.service.BoardService;

import project.service.BoardServiceImpl;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

@WebServlet({"/bbs/board/list", "/bbs/board/insert", "/bbs/board/update",
	"/bbs/board/delete", "/bbs/board/detail"})
public class BoardController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private BoardService bsvc = new BoardServiceImpl();

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String requestUri = request.getRequestURI(); // 현재 주소 리턴
		String[] uri = requestUri.split("/"); // 슬래시로 스플릿하여 구성요소(jw, ch07, kcity 등)가 나눠짐
		String action = uri[uri.length - 1]; // kcity 까지는 공통요소이므로 uri의 마지막 요소를 사용
		
		String method = request.getMethod(); // insert 등은 post와 get 모두 있으므로 구분하기 위해 사용
		
		HttpSession session = request.getSession(); // 로그인 상태를 판별하기 위한 객체
		
		//// 각종 변수나 객체 선언부 ////
		RequestDispatcher rd = null; 
		String title = null;
		Board board = null;
		String content = null;
		String sessUid = (String) session.getAttribute("sessUid");
		String field = null;
		String query = null;
		String uid = null;
		int bid = 0;
		int page = 0;
		/////////////////////////////////
		
		switch(action) {
		case "list": // /jw/bbs/board/list?p=1&f=title&q=검색  -- 이런 구조로 주소가 구성
			
			// 주소 파라메터를 받기
			String page_ = request.getParameter("p");
			field = request.getParameter("f"); // 검색 분야
			query = request.getParameter("q"); // 검색어
			
			// 받은 데이터 검사
			page = (page_ == null || page_.equals("")) ? 1 : Integer.parseInt(page_);
			field = (field == null || field.equals("")) ? "title" : field;
			query = (query == null || query.equals("")) ? "" : query;
			
			// 세션과 리퀘스트의 속성 설정
			session.setAttribute("currentBoardPage", page);
			session.setAttribute("field", field);
			session.setAttribute("query", query);
			
			// 리스트 만들고 요청에 설정
			List<Board> boardList = bsvc.getBoardList(page, field, query);
			request.setAttribute("boardList", boardList);
			
			// 페이지 만들 변수 구하기
			int totalItems = bsvc.getBoardCount(field, query);
			int totalPages = (totalItems - 1) / 10 + 1;
			List<String> pageList = new ArrayList<String>();
			for (int i = 1; i <= totalPages; i++)
			{
				pageList.add(i + "");
			}
			request.setAttribute("pageList", pageList);
			
			rd = request.getRequestDispatcher("/WEB-INF/view/board/list.jsp");
			rd.forward(request, response);
			break;
		case "insert":
			sessUid = (String) session.getAttribute("sessUid");
			if (sessUid == null || sessUid.equals(""))
			{
				response.sendRedirect("/jw/bbs/user/login");
				break;
			}
			if (method.equals("GET"))
			{
				rd = request.getRequestDispatcher("/WEB-INF/view/board/insert.jsp");
				rd.forward(request, response);
			}
			else
			{
				title = request.getParameter("title");
				content = request.getParameter("content");
				board = new Board(title, content, sessUid );
				bsvc.insertBoard(board);
				response.sendRedirect("/jw/bbs/board/list?p=1");
			}
			break;
		case "detail": // 게시물 조회
			bid = Integer.parseInt(request.getParameter("bid"));
			uid = request.getParameter("uid");
			
			if (!uid.equals(sessUid))
				bsvc.increaseViewCount(bid); // 작성자가 아니면 게시물 조회수 늘리기
			
			board = bsvc.getBoard(bid);
			request.setAttribute("board", board);
			
			List<Reply> replyList = null; // 댓글 목록 리스트
			request.setAttribute("replyList", replyList);
			
			rd = request.getRequestDispatcher("/WEB-INF/view/board/detail.jsp");
			rd.forward(request, response);
			break;
		case "delete":
			bid = Integer.parseInt(request.getParameter("bid"));
			bsvc.deleteBoard(bid);
			
			// 세션에서 값 받기
			page = (Integer) session.getAttribute("currentBoardPage");
			field = (String) session.getAttribute("field");
			query = (String) session.getAttribute("query");
			query = URLEncoder.encode(query, "utf-8");
			
			response.sendRedirect("/jw/bbs/board/list?p=" + page + "&f=" + field + "&q=" + query);
			break;
		case "update":
			sessUid = (String) session.getAttribute("sessUid");
			if (sessUid == null || sessUid.equals(""))
			{
				response.sendRedirect("/jw/bbs/user/login");
				break;
			}
			if (method.equals("GET"))
			{
				bid = Integer.parseInt(request.getParameter("bid"));
				board = bsvc.getBoard(bid);
				request.setAttribute("board", board);
				rd = request.getRequestDispatcher("/WEB-INF/view/board/update.jsp");
				rd.forward(request, response);
			}
			else
			{
				bid = Integer.parseInt(request.getParameter("bid"));
				uid = request.getParameter("uid");
				title = request.getParameter("title");
				content = request.getParameter("content");
				board = new Board(bid, title, content, sessUid );
				bsvc.updateBoard(board);
				response.sendRedirect("/jw/bbs/board/detail?bid=" + bid + "&uid=" + uid);
			}
			break;
		}
	}

}
