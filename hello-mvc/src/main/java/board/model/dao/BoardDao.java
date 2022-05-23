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
				BoardExt board = handleBoardResultSet(conn, rset);
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

	private BoardExt handleBoardResultSet(Connection conn, ResultSet rset) throws SQLException {
		BoardExt board = new BoardExt();

		int boardNo = rset.getInt("no");
		board.setNo(boardNo);
		board.setTitle(rset.getString("title"));
		board.setMemberId(rset.getString("member_id"));
		board.setContent(rset.getString("content"));
		board.setReadCount(rset.getInt("read_count"));
		board.setRegDate(rset.getDate("reg_date"));

		List<Attachment> attachment = findAttachmentByBoardNo(conn, boardNo);
		board.setAttachCount(attachment.size());

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

	public List<Attachment> findAttachmentByBoardNo(Connection conn, int boardNo) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		List<Attachment> list = new ArrayList<Attachment>();
		String sql = prop.getProperty("findAttachmentByBoardNo");

		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, boardNo);
			rset = pstmt.executeQuery();

			while (rset.next()) {
				Attachment attach = handleAttachResultSet(rset);
				list.add(attach);
			}
		} catch (Exception e) {
			throw new BoardException("boardNo일치 첨부파일 조회 오류!", e);
		} finally {
			close(rset);
			close(pstmt);
		}

		return list;
	}

	private Attachment handleAttachResultSet(ResultSet rset) throws SQLException {
		Attachment attach = new Attachment();

		attach.setNo(rset.getInt("no"));
		attach.setBoardNo(rset.getInt("board_no"));
		attach.setOriginalFileName(rset.getString("original_filename"));
		attach.setRenamedFileName(rset.getString("renamed_filename"));
		attach.setRegDate(rset.getDate("reg_date"));

		return attach;
	}

}
