package board.model.dto;

import java.sql.Date;

public class Attachment {
	private int no;
	private int boardNo;
	private String originalFileName;
	private String renamedFileName;
	private Date regDate;

	public Attachment() {
		super();
	}

	public Attachment(int no, int boardNo, String originalFileName, String renamedFileName, Date regDate) {
		super();
		this.no = no;
		this.boardNo = boardNo;
		this.originalFileName = originalFileName;
		this.renamedFileName = renamedFileName;
		this.regDate = regDate;
	}

	public int getNo() {
		return no;
	}

	public void setNo(int no) {
		this.no = no;
	}

	public int getBoardNo() {
		return boardNo;
	}

	public void setBoardNo(int boardNo) {
		this.boardNo = boardNo;
	}

	public String getOriginalFileName() {
		return originalFileName;
	}

	public void setOriginalFileName(String originalFileName) {
		this.originalFileName = originalFileName;
	}

	public String getRenamedFileName() {
		return renamedFileName;
	}

	public void setRenamedFileName(String renamedFileName) {
		this.renamedFileName = renamedFileName;
	}

	public Date getRegDate() {
		return regDate;
	}

	public void setRegDate(Date regDate) {
		this.regDate = regDate;
	}

	@Override
	public String toString() {
		return "Attachment [no=" + no + ", boardNo=" + boardNo + ", originalFileName=" + originalFileName
				+ ", renamedFileName=" + renamedFileName + ", regDate=" + regDate + "]";
	}

}
