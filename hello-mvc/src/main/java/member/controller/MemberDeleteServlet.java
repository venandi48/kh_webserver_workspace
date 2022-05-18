package member.controller;

import java.io.IOException;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import member.model.service.MemberService;

/**
 * Servlet implementation class MemberDeleteServlet
 */
@WebServlet("/member/memberDelete")
public class MemberDeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private MemberService memberService = new MemberService();

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			// 1. 인코딩처리 -> filter에서 처리
			// 2. 사용자입력값처리
			String memberId = request.getParameter("memberId");
			
			// 3. 업무로직
			int result = memberService.deleteMember(memberId);
			HttpSession session = request.getSession();


			// 탈퇴후처리 - 세션폐기, 쿠키폐기
			// 쿠키폐기
			Cookie cookie = new Cookie("saveId", memberId);
			cookie.setPath(request.getContextPath());
			cookie.setMaxAge(0);
			response.addCookie(cookie);
			// 모든 세션속성 제거(session.invalidate() 대신)
			Enumeration<String> names = session.getAttributeNames();
			while(names.hasMoreElements()) {
				String name = names.nextElement();
				session.removeAttribute(name);
			}
			
			String msg = result > 0 ? "회원탈퇴가 완료되었습니다." : "회원탈퇴에 실패하였습니다.";
			session.setAttribute("msg", msg);
			response.sendRedirect(request.getContextPath() + "/");

		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}

	}

}
