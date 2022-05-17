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
 * Servlet implementation class MemberDeleteServlet
 */
@WebServlet("/member/memberDelete")
public class MemberDeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private MemberService memberService = new MemberService();

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			// 1. 인코딩처리 -> filter에서 처리
			// 2. 사용자입력값처리 생략
			
			// 3. 업무로직
			HttpSession session = request.getSession();
			Member member = (Member) session.getAttribute("loginMember");
			System.out.println("member@MemberDeleteServlet = " + member);
			
			int result = memberService.deleteMember(member);
			String msg = result > 0 ? "회원탈퇴가 완료되었습니다." : "회원탈퇴에 실패하였습니다.";
			session.setAttribute("msg", msg);
			
			response.sendRedirect(request.getContextPath() + "/");
			
		} catch(Exception e) {
			e.printStackTrace();
			throw e;
		}
		
	}

}
