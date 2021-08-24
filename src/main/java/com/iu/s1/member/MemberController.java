package com.iu.s1.member;

import javax.servlet.http.HttpServletRequest;

public class MemberController {
	public void start(HttpServletRequest request) {
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
		} else if(path.equals("memberPage.do")) {
			System.out.println("마이페이지");
		} else {
			System.out.println("존재하지 않는 URL");
		}

		System.out.println(request.getContextPath());
	}
}
