package com.iu.s1.bankbook;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class BankBookController {
	
	private BankBookDAO bankBookDAO;
	
	public BankBookController() {
		bankBookDAO = new BankBookDAO();
	}
	
	public void start(HttpServletRequest request, HttpServletResponse response) {
		System.out.println("BankBook Controller 실행");
		
		String uri = request.getRequestURI();
		int index = uri.lastIndexOf("/");
		String path = uri.substring(index+1);
		
		if(path.equals("bankbookList.do")) {
			System.out.println("상품목록");
			ArrayList<BankBookDTO> ar = bankBookDAO.getList();
			//for(초기식; 조건식; 증감식)
			//for(모은타입명 변수명:컬렉션참조변수명)
			for(BankBookDTO bankBookDTO: ar) {
				System.out.println(bankBookDTO.getBookName());
			}
			
			request.setAttribute("list", ar);
			
			RequestDispatcher view = request.getRequestDispatcher("../WEB-INF/view/bankbook/bankbookSelect.jsp");
			try {
				view.forward(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			} 
			
		} else if(path.equals("bankbookInsert.do")) {
			System.out.println("상품등록");
		} else if(path.equals("bankbookSelect.do")) {
			System.out.println("상품상세조회");
			String num = request.getParameter("bookNumber");
			long num2 = Long.parseLong(num);
			BankBookDTO bankBookDTO = new BankBookDTO();
			bankBookDTO.setBookNumber(num2);
			bankBookDTO = bankBookDAO.getSelect(bankBookDTO);
			System.out.println(bankBookDTO.getBookName());
			
			//bankBookDTO는 DB까지 살아서 못간다 (지역변수 이기 때문에_
			//DB까지 살아서 가는 이는 request밖에 없다.
			//이 말은 bankbookSelect까지 살아서 보내려면 request 이용
			request.setAttribute("dto", bankBookDTO); //bankBookDTO를 "dto"에 넣는다.
			
			RequestDispatcher view = request.getRequestDispatcher("../WEB-INF/view/bankbook/bankbookSelect.jsp");
			try {
				view.forward(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			} 
			
		} else {
			System.out.println("없는 URL 입니다.");
		}
	}
	
}
