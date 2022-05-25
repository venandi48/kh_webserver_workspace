package common;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.Base64.Encoder;

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
}
