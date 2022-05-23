package admin.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.HelloMvcUtils;
import member.model.dto.Member;
import member.model.service.MemberService;

/**
 * 페이징
 *  1. content 영역
 *   - cPage 현재페이지
 *   - numPerPage 한 페이지당 표시할 컨텐츠 수
 *   - 페이징쿼리
 *  	- start
 *  	- end
 *  	
 *  2. pagebar 영역
 *   - totalContents 전체컨텐츠수
 *   - totalPages 전체페이지수 (totalContents, numPerPage)
 *   - pagebarSize 페이지바 길이 (페이지바에 출력될 페이지번호의 수)
 *   - pageNo 페이지증감변수
 *   - PageStart ~ pageEnd 페이지바 범위
 *   - url 다음 요청 url
 */
@WebServlet("/admin/memberList")
public class AdminMemberListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private MemberService memberService = new MemberService(); // 별도의 관리자서비스를 만들어도 됨
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			// 1. 사용자입력값 처리
			int numPerPage = MemberService.NUM_PER_PAGE; // 상수는 service단에 선언
			int cPage = 1;
			try {
				cPage = Integer.parseInt(request.getParameter("cPage"));
			} catch (NumberFormatException e) {
				// 예외발생시 현재페이지는 1로 처리
			}
			
			Map<String, Object> param = new HashMap<>();
			int start = (cPage - 1) * numPerPage + 1;
			int end = cPage * numPerPage;
			param.put("start", start);
			param.put("end", end);
			
			// 2. 업무로직
			// 2-a. content 영역
			List<Member> list = memberService.findAll(param);
			System.out.println("list = " + list);
			
			// 2-b. pagebar 영역
			int totalContents = memberService.getTotalContents();
			String url = request.getRequestURI();
			String pagebar = HelloMvcUtils.getPagebar(cPage, numPerPage, totalContents, url);
			System.out.println("pagebar = " + pagebar);
			
			// 3. view단 처리
			request.setAttribute("list", list);
			request.setAttribute("pagebar", pagebar);
			request.getRequestDispatcher("/WEB-INF/views/admin/memberList.jsp")
				.forward(request, response);;
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		
	}

}
