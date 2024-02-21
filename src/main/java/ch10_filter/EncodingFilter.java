package ch10_filter;

import jakarta.servlet.Filter;
import jakarta.servlet.http.HttpFilter;
import java.io.IOException;
import jakarta.servlet.FilterChain;
//import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;

/*
 * 필터 : 많은 양의 데이터를 처리할 때 공통적인 작업을 한꺼번에 처리하는 거름망 
 */
@WebFilter("/ch10/*")
public class EncodingFilter extends HttpFilter implements Filter {
	private static final long serialVersionUID = 1L;

//	public EncodingFilter() {
//        super();
//        System.out.println("생성자");
//    }

//	public void destroy() {
//		System.out.println("destroy()");
//	}

	
	// 필요한 필터 작업을 하는 위치
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		
		//request.setCharacterEncoding("utf-8");
		System.out.println("doFilter()");
		
		chain.doFilter(request, response);
	}

//	
//	public void init(FilterConfig fConfig) throws ServletException {
//		System.out.println("init()");
//	}

}
