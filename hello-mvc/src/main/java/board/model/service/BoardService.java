package board.model.service;

import static common.JdbcTemplate.close;
import static common.JdbcTemplate.commit;
import static common.JdbcTemplate.getConnection;
import static common.JdbcTemplate.rollback;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.List;
import java.util.Map;

import board.model.dao.BoardDao;
import board.model.dto.Attachment;
import board.model.dto.Board;
import board.model.dto.BoardExt;

public class BoardService {

	public static final int NUM_PER_PAGE = 10;
	private BoardDao boardDao = new BoardDao();

	public List<BoardExt> findAll(Map<String, Object> param) {
		Connection conn = getConnection();
		List<BoardExt> list = boardDao.findAll(conn, param);
		close(conn);
		return list;
	}

	public int getTotalContents() {
		Connection conn = getConnection();
		int totalContents = boardDao.getTotalContents(conn);
		close(conn);
		return totalContents;
	}

	/**
	 * Transaction
	 *  - all or none
	 */
	public int insertBoard(Board board) {
		int result = 0;
		Connection conn = getConnection();
		try {
			// 1. board에 등록
			result = boardDao.insertBoard(conn, board); // pk no값 결정됨 seq_board_no.nextVal

			// 2. board pk 가져오기
			int no = boardDao.findCurrentBoardNo(conn); // seq_board_no.currval
			System.out.println("등록된 board.no = " + no);

			// 3. attachment에 등록
			List<Attachment> attachments = ((BoardExt) board).getAttachments();
			if (attachments != null && !attachments.isEmpty()) {
				for (Attachment attach : attachments) {
					attach.setBoardNo(no);
					result = boardDao.insertAttachment(conn, attach);
				}
			}

			commit(conn);
		} catch (Exception e) {
			rollback(conn);
			throw e;
		} finally {
			close(conn);
		}
		return result;
	}

	public BoardExt findByNo(int no) {
		Connection conn = getConnection();
		BoardExt board = boardDao.findByNo(conn, no); // board테이블 조회
		List<Attachment> attachments = boardDao.findAttachmentByBoardNo(conn, no); // attachment테이블 조회
		board.setAttachments(attachments);
		close(conn);
		return board;
	}

	public int updateReadCount(int no) {
		int result = 0;
		Connection conn = getConnection();
		try {
			result = boardDao.updateReadCount(conn, no);
			commit(conn);
		} catch (Exception e) {
			rollback(conn);
			throw e;
		} finally {
			close(conn);
		}
		return result;
	}

	public Attachment findAttachmentByNo(int no) {
		Connection conn = getConnection();
		Attachment attach = boardDao.findAttachmentByNo(conn, no);
		close(conn);
		return attach;
	}

	public int deleteBoard(int no) {
		int result = 0;
		Connection conn = getConnection();

		try {
			result = boardDao.deleteBoard(conn, no);

			commit(conn);
		} catch (Exception e) {
			rollback(conn);
			throw e;
		} finally {
			close(conn);
		}

		return result;
	}

	public List<Attachment> findAttachmentByBoardNo(int no) {
		Connection conn = getConnection();
		List<Attachment> attachments = boardDao.findAttachmentByBoardNo(conn, no);
		close(conn);
		return attachments;
	}

}
