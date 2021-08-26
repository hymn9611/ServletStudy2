package com.iu.s1.member;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class MemberController {
	
	private MemberService memberService;
	
	public MemberController() {
		memberService = new MemberService();
	}
	public void start(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("MemberController 실행");
		
		//memberLogin.do, memberJoin.do, memberPage.do 인덱스
		//맨 끝에 있는 마지막 주소가 필요한 것!
		String uri = request.getRequestURI();
		int Index = uri.lastIndexOf("/");
		String path = uri.substring(Index+1);

		if(path.equals("memberLogin.do")) {
			System.out.println("로그인 진행");
			String value = request.getParameter("id");
			System.out.println(value);
			String value2 = request.getParameter("pw");
			System.out.println(value2);
			
			
		} else if(path.equals("memberJoin.do")) {
			System.out.println("회원가입 진행");
			
			String method = request.getMethod();
			
			if(method.equals("POST")) {
				int result = memberService.memberJoin(request, response);
				if(result>0) {
					response.sendRedirect("../");
				} else {
					response.sendRedirect("./memberJoin.do");
				}
				
				
			} else {
				RequestDispatcher view = request.getRequestDispatcher("../WEB-INF/view/member/memberJoin.jsp");
				view.forward(request, response);
				
			}
			
			
		} else if(path.equals("memberPage.do")) {
			System.out.println("마이페이지");
			
			
		} else {
			System.out.println("존재하지 않는 URL");
		}

		System.out.println(request.getContextPath());
	}
}
