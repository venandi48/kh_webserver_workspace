package board.model.dao;

import static common.JdbcTemplate.close;

import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import board.model.dto.Attachment;
import board.model.dto.Board;
import board.model.dto.BoardExt;
import board.model.exception.BoardException;

public class BoardDao {
	
	private Properties prop = new Properties();

	public BoardDao() {
		String fileName = BoardDao.class.getResource("/sql/board-query.properties").getPath();

		try {
			prop.load(new FileReader(fileName));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public List<BoardExt> findAll(Connection conn, Map<String, Object> param) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		List<BoardExt> list = new ArrayList<BoardExt>();
		String sql = prop.getProperty("findAll");

		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, (int) param.get("start"));
			pstmt.setInt(2, (int) param.get("end"));
			rset = pstmt.executeQuery();
			while (rset.next()) {
				BoardExt board = handleBoardResultSet(rset);
				list.add(board);
			}
		} catch (Exception e) {
			throw new BoardException("게시판 전체게시글 조회 오류", e);
		} finally {
			close(rset);
			close(pstmt);
		}

		return list;
	}

	private BoardExt handleBoardResultSet(ResultSet rset) throws SQLException {
		BoardExt board = new BoardExt();

		board.setNo(rset.getInt("no"));
		board.setTitle(rset.getString("title"));
		board.setMemberId(rset.getString("member_id"));
		board.setContent(rset.getString("content"));
		board.setReadCount(rset.getInt("read_count"));
		board.setRegDate(rset.getDate("reg_date"));
		board.setAttachCount(rset.getInt("attach_cnt"));

		return board;
	}

	public int getTotalContents(Connection conn) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		int totalContents = 0;
		String sql = prop.getProperty("getTotalContents");

		try {
			pstmt = conn.prepareStatement(sql);
			rset = pstmt.executeQuery();
			while (rset.next()) {
				totalContents = rset.getInt(1); // 컬럼 인덱스 사용 (db는 1-based)
			}
		} catch (Exception e) {
			throw new BoardException("전체게시글수 조회 오류!", e);
		} finally {
			close(rset);
			close(pstmt);
		}

		return totalContents;
	}

	public int insertBoard(Connection conn, Board board) {
		int result = 0;
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("insertBoard");

		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, board.getTitle());
			pstmt.setString(2, board.getMemberId());
			pstmt.setString(3, board.getContent());

			result = pstmt.executeUpdate();
		} catch (Exception e) {
			throw new BoardException("게시글 등록 오류!", e);
		} finally {
			close(pstmt);
		}

		return result;
	}

	public int findCurrentBoardNo(Connection conn) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		int no = 0;
		String sql = prop.getProperty("findCurrentBoardNo");

		try {
			pstmt = conn.prepareStatement(sql);
			rset = pstmt.executeQuery();

			while (rset.next())
				no = rset.getInt(1);

		} catch (Exception e) {
			throw new BoardException("게시글 번호 조회 오류!", e);
		} finally {
			close(rset);
			close(pstmt);
		}
		return no;
	}

	public int insertAttachment(Connection conn, Attachment attach) {
		int result = 0;
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("insertAttachment");

		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, attach.getBoardNo());
			pstmt.setString(2, attach.getOriginalFileName());
			pstmt.setString(3, attach.getRenamedFileName());

			result = pstmt.executeUpdate();
		} catch (Exception e) {
			throw new BoardException("첨부파일 등록 오류!", e);
		} finally {
			close(pstmt);
		}
		return result;
	}

}
