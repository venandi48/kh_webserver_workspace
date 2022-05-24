package board.controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.FileRenamePolicy;

import board.model.dto.Attachment;
import board.model.dto.BoardExt;
import board.model.service.BoardService;
import common.HelloMvcFileRenamePolicy;

/**
 * Servlet implementation class BoardEnrollServlet
 */
@WebServlet("/board/boardEnroll")
public class BoardEnrollServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private BoardService boardService = new BoardService();

	/**
	 * 게시글쓰기 폼 요청
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/WEB-INF/views/board/boardEnroll.jsp").forward(request, response);
	}

	/**
	 * 게시글 등록 요청
	 *  - 게시글 등록시 /board/boardList 리다이렉트
	 * 
	 * 파일업로드 절차
	 *  - 1. 제출폼 enctype="multipart/form-data"
	 *  - 2. MultipartRequest객체 생성 => 파일저장 완료
	 *  	a. HttpServletRequest
	 *  	b. saveDirectory
	 *  	c. maxPostSize 최대업로드크키
	 *  	d. encoding
	 *  	e. FileRenamePolicy 객체
	 *  - 3. 사용자입력값 처리 - HttpServletRequest가 아닌 MultipartRequest에서 값 가져오기
	 *  - 4. 업무로직 - db board, attachment 레코드 등록
	 *  - 5. redirect
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 2. MultipartRequest객체 생성
		
		// b. 파일저장경로
		ServletContext application = getServletContext();
		String webRoot = application.getRealPath("/"); // F:\Workspaces\webserver_workspace\hello-mvc\src\main\webapp
		String saveDirectory = webRoot + "/upload/board";
		System.out.println("saveDirectory = " + saveDirectory);
		// 예전 사용법 : File.separator 운영체제별 경로 구분자(window = \, mac/linux: /)
		// String saveDirectory = webRoot + File.separator +"upload" + File.separator + "board";
		
		// c. 최대파일크기 10MB
		int maxPostSize = 1024 * 1024 * 10;
		
		// d. encoding
		String encoding = "utf-8";
		
		// e. FileRenamePolicy 객체 (파일명 재설정 정책 객체)
		// DefaultFileRenamePolicy : 파일명 중복시 numbering 처리함
		FileRenamePolicy policy = new HelloMvcFileRenamePolicy();
		
		MultipartRequest multiReq = 
				new MultipartRequest(request, saveDirectory, maxPostSize, encoding, policy);
		
		// 3. 사용자 입력값 처리 + dto 객체 생성
		String title = multiReq.getParameter("title");
		String memberId = multiReq.getParameter("writer");
		String content = multiReq.getParameter("content");
		
		BoardExt board = new BoardExt();
		board.setTitle(title);
		board.setMemberId(memberId);
		board.setContent(content);

		File upFile1 = multiReq.getFile("upFile1");
		File upFile2 = multiReq.getFile("upFile2");

		// 첨부한 파일이 하나라도 있는 경우
		if (upFile1 != null || upFile2 != null) {
			List<Attachment> attachments = new ArrayList<>();
			if (upFile1 != null)
				attachments.add(getAttachment(multiReq, "upFile1"));
			if (upFile2 != null)
				attachments.add(getAttachment(multiReq, "upFile2"));
			
			board.setAttachments(attachments);
		}
		 System.out.println("board = " + board);

		// 4. 업무로직
		int result = boardService.insertBoard(board);

		// 5. 리다이렉트
		response.sendRedirect(request.getContextPath() + "/board/boardList");
	}

	private Attachment getAttachment(MultipartRequest multiReq, String name) {
		Attachment attach = new Attachment();
		String originalFilename = multiReq.getOriginalFileName(name); // 업로드한 파일명
		String renamedFilename = multiReq.getFilesystemName(name); // 저장된 파일명
		
		attach.setOriginalFileName(originalFilename);
		attach.setRenamedFileName(renamedFilename);
		return attach;
	}

}
