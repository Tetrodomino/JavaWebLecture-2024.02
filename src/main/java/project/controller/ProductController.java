package project.controller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.Part;
import project.entity.Product;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.time.LocalDateTime;

@WebServlet({"/bbs/product/insert", "/bbs/product/view"})
@MultipartConfig(
	fileSizeThreshold = 1 * 1024 * 1024, 	// 1 MB
	maxFileSize = 10 * 1024 * 1024, 		// 10 MB
	maxRequestSize = 10 * 1024 * 1024
)
public class ProductController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	public static final String UPLOAD_PATH = "c:/Temp/upload/bbs";
       
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String requestUri = request.getRequestURI(); // 현재 주소 리턴
		String[] uri = requestUri.split("/"); // 슬래시로 스플릿하여 구성요소(jw, ch07, kcity 등)가 나눠짐
		String action = uri[uri.length - 1]; // kcity 까지는 공통요소이므로 uri의 마지막 요소를 사용
		
		String method = request.getMethod(); // insert 등은 post와 get 모두 있으므로 구분하기 위해 사용
		
		HttpSession session = request.getSession(); // 로그인 상태를 판별하기 위한 객체
		
		//// 각종 변수나 객체 선언부 ////
		RequestDispatcher rd = null; 
		String fname = null;
		/////////////////////////////////
		
		switch(action) {
		case "insert":
			if (method.equals("GET")) {
				rd = request.getRequestDispatcher("/WEB-INF/view/product/insert.jsp");
				rd.forward(request, response);
			}
			else
			{
				String category = request.getParameter("category");
				String pname = request.getParameter("pname");
				String price_ = request.getParameter("price");
				int price = Integer.parseInt(price_);
				String description = request.getParameter("description");
				
				// 파일 이름 받는 방법
				Part filePart = request.getPart("imgFile");
				String filename = filePart.getSubmittedFileName();
				System.out.println("filename=" + filename);
				
				// 파일 확장자 받기
				String[] ext = filename.split("\\.");
				String extension = ext[ext.length - 1];
				
				// 하나의 경로를 만들고 filePart에 작성
				fname = category + System.currentTimeMillis() + "." + extension;
				String path = UPLOAD_PATH + "/" + fname;
				System.out.println("path=" + path);
				filePart.write(path);
				
				Product product = new Product(category, pname, price, description, fname);
				rd = request.getRequestDispatcher("/WEB-INF/view/product/detail.jsp");
				request.setAttribute("product", product);
				rd.forward(request, response);
			}
			break;
		case "view":
			byte[] buffer = new byte[8 * 1024];
			fname = request.getParameter("filename");
			String path = UPLOAD_PATH + "/" + fname;
			OutputStream os = response.getOutputStream();
			response.setContentType("text/html; charset=utf-8");
			response.setHeader("Cache-Control", "no-cache");
			response.setHeader("Content-desposition", "attachment; fileName=" + URLEncoder.encode(fname, "utf-8"));
			
			FileInputStream fis = new FileInputStream(path);
			while (true)
			{
				int count = fis.read();
				if (count == -1)
					break;
				
				os.write(buffer, 0, count);
			}
			fis.close();
			os.close();
			break;
		}
	}


}
