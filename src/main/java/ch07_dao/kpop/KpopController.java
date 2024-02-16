package ch07_dao.kpop;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;


@WebServlet({"/ch07/kpop/list", "/ch07/kpop/insertartist", "/ch07/kpop/insertsong", "/ch07/kpop/updateartist"
	, "/ch07/kpop/updatesong", "/ch07/kpop/deleteartist", "/ch07/kpop/deletesong"})
public class KpopController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	KpopDao kDao = new KpopDaoImpl();
       
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String requestUri = request.getRequestURI(); // 현재 주소 리턴
		String[] uri = requestUri.split("/"); // 슬래시로 스플릿하여 구성요소(jw, ch07, kcity 등)가 나눠짐
		String action = uri[uri.length - 1]; // kcity 까지는 공통요소이므로 uri의 마지막 요소를 사용
		
		String method = request.getMethod(); // insert 등은 post와 get 모두 있으므로 구분하기 위해 사용
		
		//// 각종 변수나 객체 선언부 ////
		RequestDispatcher rd = null; 
		int gid = 0;
		int sid = 0;
		String name = null;
		int hit_song_id = 0;
		LocalDate debut = null;
		String title = null;
		String lyrics = null;
		Artist artist = null;
		Song song = null;
		/////////////////////////////////s
		
		switch(action) {
		case "list":
			List<Kpop> list = kDao.getKpopList();
			rd = request.getRequestDispatcher("/ch07/kpop/list.jsp");
			request.setAttribute("list", list);
			rd.forward(request, response);
			break;
		case "insertartist":
			if (method.equals("GET"))
			{
				rd = request.getRequestDispatcher("/ch07/kpop/insertartist.jsp");
				rd.forward(request, response);				
			}
			else
			{
				name = request.getParameter("name");
				debut = LocalDate.parse(request.getParameter("debut"));
				hit_song_id = Integer.parseInt(request.getParameter("hit_song_id"));
				
				artist = new Artist(name, debut, hit_song_id);
				
				kDao.insertArtist(artist);
				response.sendRedirect("/jw/ch07/kpop/list");
			}
			break;
		case "insertsong":
			if (method.equals("GET"))
			{
				rd = request.getRequestDispatcher("/ch07/kpop/insertsong.jsp");
				rd.forward(request, response);			
			}
			else
			{
				title = request.getParameter("title");
				lyrics = request.getParameter("lyrics");
				
				song = new Song(title, lyrics);
				
				kDao.insertSong(song);
				response.sendRedirect("/jw/ch07/kpop/list");
			}
			break;
		case "updateartist":
			if (method.equals("GET"))
			{
				gid = Integer.parseInt(request.getParameter("id"));
				artist = kDao.getArtist(gid);
				
				rd = request.getRequestDispatcher("/ch07/kpop/updateartist.jsp");
				request.setAttribute("artist", artist);
				rd.forward(request, response);	
			}
			else
			{
				gid = Integer.parseInt(request.getParameter("id"));
				name = request.getParameter("name");
				debut = LocalDate.parse(request.getParameter("debut"));
				hit_song_id = Integer.parseInt(request.getParameter("hit_song_id"));
				
				artist = new Artist(gid, name, debut, hit_song_id);
				kDao.updateArtist(artist);
				
				response.sendRedirect("/jw/ch07/kpop/list");
			}
			break;
		case "updatesong":
			if (method.equals("GET"))
			{
				sid = Integer.parseInt(request.getParameter("id"));
				song = kDao.getSong(sid);
				
				if (song != null)
				{
					rd = request.getRequestDispatcher("/ch07/kpop/updatesong.jsp");
					request.setAttribute("song", song);
					rd.forward(request, response);
				}
				else
				{
					rd = request.getRequestDispatcher("/ch07/kpop/insertsong2.jsp");
					request.setAttribute("sid", sid);
					rd.forward(request, response);
				}
			}
			else
			{
				sid = Integer.parseInt(request.getParameter("id"));
				title = request.getParameter("title");
				lyrics = request.getParameter("lyrics");
				
				song = new Song(sid, title, lyrics);
				kDao.updateSong(song);
				
				response.sendRedirect("/jw/ch07/kpop/list");
			}
			break;
		case "deleteartist":
			gid = Integer.parseInt(request.getParameter("id"));
			kDao.deleteArtist(gid);
			
			response.sendRedirect("/jw/ch07/kpop/list");
			break;
		case "deletesong":
			sid = Integer.parseInt(request.getParameter("id"));
			kDao.deleteSong(sid);
			
			response.sendRedirect("/jw/ch07/kpop/list");
			break;
		default:
			rd = request.getRequestDispatcher("/ch07/kpop/alertMsg.jsp");
			request.setAttribute("msg", "잘못된 접근입니다");
			request.setAttribute("url", "/jw/ch07/kpop/list");
			rd.forward(request, response);
		}
	}

}
