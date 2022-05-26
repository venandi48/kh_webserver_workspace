package common;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.Base64.Encoder;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletResponse;

import board.model.dto.Attachment;

public class HelloMvcUtils {
	

	/**
	 * SHA256 | SHA512 (SHA1또는 MD5 사용하지 말것)
	 * @param password
	 * @return
	 */
	public static String encrypt(String password, String salt) {
		// 1. 암호화 Hashing
		MessageDigest md = null;
		byte[] encrypted = null;
		try {
			md = MessageDigest.getInstance("SHA-512");
			byte[] input = password.getBytes("utf-8");
			byte[] saltBytes = salt.getBytes("utf-8");
			
			md.update(saltBytes); // salt값으로 MessageDigest객체 갱신
			encrypted = md.digest(input); // MessageDigest객체에 raw password 전달 및 hashing
		} catch (NoSuchAlgorithmException | UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		
		// 2. 인코딩 (단순문자변환)
		Encoder encoder = Base64.getEncoder();
		return encoder.encodeToString(encrypted);
	}

	
	/**
	 * 
	 * @param cPage
	 * @param numPerPage 10
	 * @param totalContents 116
	 * @param url /mvc/admin/memberList
	 * @return
	 * 
	 * <a href='/mvc/admin/memberList?cPage=5'>prev</a>
	 * <a href='/mvc/admin/memberList?cPage=6'>6</a>
	 * <a href='/mvc/admin/memberList?cPage=7'>7</a>
	 * <a href='/mvc/admin/memberList?cPage=8'>8</a>
	 * <a href='/mvc/admin/memberList?cPage=9'>9</a>
	 * <span class='cPage='>10</span>
	 * <a href='/mvc/admin/memberList?cPage=11'>next</a>
	 * 
	 */
	public static String getPagebar(int cPage, int numPerPage, int totalContents, String url) {
		StringBuilder pagebar = new StringBuilder();
		int totalPages = (int) Math.ceil((double) totalContents / numPerPage); // 전체페이지수
		int pagebarSize = 5;
		int pagebarStart = (cPage - 1) / pagebarSize * pagebarSize + 1; // 1, 6, 11
		int pagebarEnd = pagebarStart + pagebarSize - 1; // 5, 10, 15
		int pageNo = pagebarStart;

		url += "?cPage=";

		// 이전 prev
		if (pageNo == 1) {
			// prev 버튼 비활성화
		} else {
			// prev 버튼 활성화
			pagebar.append("<a href='" + url + (pageNo - 1) + "'>prev</a>");
			pagebar.append("\n");
		}

		// 번호
		while (pageNo <= pagebarEnd && pageNo <= totalPages) {
			if (pageNo == cPage) {
				// 현재페이지인 경우
				pagebar.append("<span class='cPage'>" + pageNo + "</span>");
				pagebar.append("\n");
			} else {
				// 현재페이지가 아닌 경우(링크필요)
				pagebar.append("<a href='" + url + pageNo + "'>" + pageNo + "</a>");
				pagebar.append("\n");
			}
			pageNo++;
		}

		// 다음 next
		if (pageNo > totalPages) {
			// 이동할 페이지 없음
		} else {
			pagebar.append("<a href='" + url + pageNo + "'>next</a>");
			pagebar.append("\n");
		}

		return pagebar.toString();
	}
	
	public static void fileDownload(HttpServletResponse response, String saveDirectory, String originalFilename,
			String renamedFilename) throws UnsupportedEncodingException, IOException, FileNotFoundException {

		response.setContentType("application/octet-stream"); // 응답데이터 타입 - 이진데이터
		// Content-Disposition 첨부파일인 경우, 브라우저 다운로드(Save as)처리 명시
		String resFilename = new String(originalFilename.getBytes("utf-8"), "iso-8859-1"); // tomcat 기본인코딩
		response.setHeader("Content-Disposition", "attachment;filename=" + resFilename);
		System.out.println(resFilename);

		// 파일을 읽어서(input) 응답메세지에 쓰기(output)
		File file = new File(saveDirectory, renamedFilename);

		// 기본스트림 - 대상과 연결
		// 보조스트림 - 기본스트림과 연결, 보조스트림 제어
		try (BufferedInputStream bis = new BufferedInputStream(new FileInputStream(file));
				BufferedOutputStream bos = new BufferedOutputStream(response.getOutputStream());) {
			byte[] buffer = new byte[8192];
			int len = 0; // 읽어낸 byte 수
			while ((len = bis.read(buffer)) != -1) {
				bos.write(buffer, 0, len); // buffer의 0번지부터 len(읽은 개수)까지 출력
			}
		}
	}

}
