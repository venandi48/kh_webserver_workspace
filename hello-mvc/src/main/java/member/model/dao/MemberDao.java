package member.model.dao;

import static common.JdbcTemplate.close;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

import member.model.dto.Member;
import member.model.dto.MemberRole;
import member.model.exception.MemberException;

public class MemberDao {

	private Properties prop = new Properties();

	public MemberDao() {
		// buildpath의 sql/member-query.properties 파일의 내용 불러오기
		String fileName = MemberDao.class.getResource("/sql/member-query.properties").getPath();
//		System.out.println("fileName@MemberDao = " + fileName);

		try {
			prop.load(new FileReader(fileName));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public Member findByMemberId(Connection conn, String memberId) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String sql = prop.getProperty("findByMemberId");
		Member member = null;

		// 1. pstmt객체 & 미완성쿼리 값대입
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, memberId);

			// 2. 실행 및 rset처리
			rset = pstmt.executeQuery();
			while (rset.next()) {
				member = new Member();
				member.setMemberId(rset.getString("member_id"));
				member.setPassword(rset.getString("password"));
				member.setMemberName(rset.getString("member_name"));

				// "U"->MemberRole.U , "A"->MemberRole.A
				member.setMemberRole(MemberRole.valueOf(rset.getString("member_role")));

				member.setGender(rset.getString("gender"));
				member.setBirthday(rset.getDate("birthday"));
				member.setEmail(rset.getString("email"));
				member.setPhone(rset.getString("phone"));
				member.setAddress(rset.getString("address"));
				member.setHobby(rset.getString("hobby"));
				member.setEnrollDate(rset.getDate("enroll_date"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			// 3. 자원반납(rset, pstmt)
			close(rset);
			close(pstmt);
		}

		return member;
	}

	public int insertMember(Connection conn, Member member) {
		String sql = prop.getProperty("insertMember");
		PreparedStatement pstmt = null;
		int result = 0;
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, member.getMemberId());
			pstmt.setString(2, member.getPassword());
			pstmt.setString(3, member.getMemberName());
			pstmt.setString(4, member.getMemberRole().toString());
			pstmt.setString(5, member.getGender());
			pstmt.setDate(6, member.getBirthday());
			pstmt.setString(7, member.getEmail());
			pstmt.setString(8, member.getPhone());
			pstmt.setString(9, member.getAddress());
			pstmt.setString(10, member.getHobby());
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			throw new MemberException("회원가입오류", e);
		} finally {
			close(pstmt);
		}
		
		return result;
	}

}
