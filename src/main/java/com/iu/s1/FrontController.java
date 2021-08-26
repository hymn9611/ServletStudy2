/** 맨 처음 web.xml을 확인하고서 모든 주소를 받는 FrontController로 넘어온다.
	FrontController에서는 각 주소에 맞는 다른 Controller로 뿌리는 역할만 하고 별 다른 코드는 없다.
*/

package com.iu.s1;

import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.iu.s1.bankbook.BankBookController;
import com.iu.s1.member.MemberController;

/**
 * Servlet implementation class FrontController
 */
@WebServlet("/FrontController")
public class FrontController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private MemberController memberController;
	private BankBookController bankBookController;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FrontController() {
        super();
        // TODO Auto-generated constructor stub
        memberController = new MemberController();
        bankBookController = new BankBookController();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		ServletConfig sc = getServletConfig();
		HttpSession session = request.getSession();
		ServletContext context = session.getServletContext();
		context = getServletContext(); //세션에서 안꺼내도 바로 사용할 수 있다.
		
		
		System.out.println("Front Controller 실행");
		
		/** 주소 빼내기 */
		String uri = request.getRequestURI();
		System.out.println("uri : " + uri);
		//url에는 쓸데없는 경로가 많아서 uri으로 주소를 subString 하는 것이 더 편하다.
//		String url = request.getRequestURL().toString();
		String path = "";
		//subString
		int startIndex = request.getContextPath().length();
		int lastIndex = uri.lastIndexOf("/");
		path = uri.substring(startIndex, lastIndex);
		
		/** 빼낸 주소를 확인하여 각 Controller 실행시키기 */
		
		//member 요청시
		if(path.equals("/member")) {
			try {
				memberController.start(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		//bankbook 요청시
		} else if(path.equals("/bankbook")) {
			bankBookController.start(request, response);
			
		//그외 주소 요청시
		} else {
			System.out.println("존재하지 않는 URL");
		}
		
		System.out.println("path : " + path);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
