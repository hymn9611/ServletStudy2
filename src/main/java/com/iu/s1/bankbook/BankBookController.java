/** 
 *  FrontController에서 BankBookController의 start() 메서드를 실행시켰다.
 */

package com.iu.s1.bankbook;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class BankBookController {
	
	//
	private BankBookDAO bankBookDAO;
	
	public BankBookController() {
		bankBookDAO = new BankBookDAO();
	}
	
	//요청에 bankbook이 있으면 FrontController에서 실행하는 메서드
	public void start(HttpServletRequest request, HttpServletResponse response) {
		System.out.println("BankBook Controller 실행");
		
		//FrontController와 마찬가지로 받은 요청을 조금 더 세분화
		String uri = request.getRequestURI();
		int index = uri.lastIndexOf("/");
		String path = uri.substring(index+1);
		
		//요청이 bankbookList.do라면..
		if(path.equals("bankbookList.do")) {
			System.out.println("상품목록(bankbookList)을 출력합니다.");
			
			//bankBookDAO에 getList()메서드는 bankbook의 모든 정보를 담아낸다.
			ArrayList<BankBookDTO> ar = bankBookDAO.getList();
			//for(초기식; 조건식; 증감식) == for(모은타입명 변수명:컬렉션참조변수명)
			for(BankBookDTO bankBookDTO: ar) {
				System.out.println(bankBookDTO.getBookName());
			}
			//start()메서드가 실행되고 있는 동안만 담아놓은 정보가 살아있다.
			//정보를 끝까지 무사히 전달하려면 request에 정보를 담아야 한다.
			request.setAttribute("list", ar); //request.setAttribute("keyname", Object);
			//RequestDispatcher는 클라이언트로부터 최초에 들어온 요청을 JSP/Servlet 내에서 원하는 자원으로 요청을 넘기는(보내는) 역할을 수행하거나, 
			//특정 자원에 처리를 요청하고 처리 결과를 얻어오는 기능을 수행하는 클래스
			RequestDispatcher view = request.getRequestDispatcher("../WEB-INF/view/bankbook/bankbookList.jsp");
			try {
				view.forward(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			} 
			
			
		//요청이 bankbookInsert.do라면..
		} else if(path.equals("bankbookInsert.do")) {
			System.out.println("상품등록(bankbookInsert)을 진행합니다.");
			
			String method = request.getMethod();
			System.out.println("Method : " + method);
			
			if(method.equals("POST")) {
				System.out.println("insert2");
				//파라미터값 출력하기
				String bookName = request.getParameter("bookName");
				String bookRate = request.getParameter("bookRate");
				String bookSale = request.getParameter("bookSale");
				BankBookDTO bankBookDTO = new BankBookDTO();
				bankBookDTO.setBookName(bookName);
				bankBookDTO.setBookRate(Double.parseDouble(bookRate));
				bankBookDTO.setBookSale(Integer.parseInt(bookSale));
				
				int result = bankBookDAO.setInsert(bankBookDTO);
				System.out.println(result);
				
				//값을 집어넣고 다시 게시판(리스트)으로 화면전환, 포워드방식 : 잘못된 방식! 주소가 안바뀐다..
//				ArrayList<BankBookDTO> ar = bankBookDAO.getList();
//				request.setAttribute("list", ar);
//				RequestDispatcher view = request.getRequestDispatcher("../index.jsp");
//				try {
//					view.forward(request, response);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
				
				try {
					response.sendRedirect("./bankbookList.do");
				} catch (IOException e) {
					e.printStackTrace();
				}
					
			} else {
				RequestDispatcher view = request.getRequestDispatcher("../WEB-INF/view/bankbook/bankbookInsert.jsp");
				try {
					view.forward(request, response);
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}//else
			
		
		//요청이 bankbookSelect라면..
		} else if(path.equals("bankbookSelect.do")) {
			System.out.println("상품상세조회(bankbookSelect)를 실행합니다.");
			
			String num = request.getParameter("bookNumber");
			long num2 = Long.parseLong(num);
			BankBookDTO bankBookDTO = new BankBookDTO();
			bankBookDTO.setBookNumber(num2);
			bankBookDTO = bankBookDAO.getSelect(bankBookDTO);
			System.out.println(bankBookDTO.getBookName());
			
//			bankBookDTO는 DB까지 살아서 못간다 (지역변수 이기 때문에 DB까지 살아서 가는 이는 request밖에 없다.
//			이 말은 bankbookSelect까지 살아서 보내려면 request 이용
			request.setAttribute("dto", bankBookDTO); //bankBookDTO를 "dto"에 넣는다.
			//bankbookSelect.jsp에서 실험하려고 만든 코드
			request.setAttribute("count", 123);
			request.setAttribute("name", "iu");
			//
			HttpSession session = request.getSession();
			session.setAttribute("se", "session");//같은키 다른영역
			request.setAttribute("se", "request");//같은키 다른영역 - 생명주기 짧은것 부터 찾게된다.
			
			
			RequestDispatcher view = request.getRequestDispatcher("../WEB-INF/view/bankbook/bankbookSelect.jsp");
			try {
				view.forward(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			} 
			
			
		//요청이 bankbookInsert, bankbookInsert, bankbookSelect가 아니라면..
		} else {
			System.out.println("없는 URL 입니다.");
		}
	
	
	}//start메서드 종료
	
}//클래스메서드
