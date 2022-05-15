package member.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import member.model.dto.Member;
import member.model.service.MemberService;

/**
 * Servlet implementation class MemberLoginServlet
 */
@WebServlet("/member/login")
public class MemberLoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private MemberService memberService = new MemberService();

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 1. 인코딩처리
		request.setCharacterEncoding("utf-8");
		
		// 2. 사용자입력값 처리
		String memberId = request.getParameter("memberId");
		String password = request.getParameter("password");
		System.out.println("memberId@MemberLoginServlet = " + memberId);
		System.out.println("password@MemberLoginServlet = " + password);
		
		// 3. 업무로직
		Member member = memberService.findByMemberId(memberId);
		System.out.println("member@MemberLoginServlet = " + member);
		
		// session 가져오기
		// getSession(create: boolean)
		//  - true(기본값) 존재하면 해당 세션객체, 없으면 새로 생성하여 리턴
		//  - false 존재하면 해당 세션객체를, 존재하지 않으면 null 리턴
		HttpSession session = request.getSession();
		
		if(member != null && password.equals(member.getPassword())) {
			// 로그인 성공!
			
			System.out.println(session.getId()); // JSESSIONID 동일
			session.setAttribute("loginMember", member);
		} else {
			// 로그인 실패!
			session.setAttribute("msg", "아이디 또는 비밀번호가 일치하지 않습니다.");			
		}
		
		// 4. 응답처리
		response.sendRedirect(request.getContextPath() + "/");
	}

}
