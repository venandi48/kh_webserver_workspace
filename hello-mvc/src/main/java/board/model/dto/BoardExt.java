package board.model.dto;

import java.sql.Date;

public class BoardExt extends Board {
	private int attachCount;

	public BoardExt() {
		super();
		// TODO Auto-generated constructor stub
	}

	public BoardExt(int no, String title, String memberId, String content, int readCount, Date regDate) {
		super(no, title, memberId, content, readCount, regDate);
		// TODO Auto-generated constructor stub
	}

	public BoardExt(int attachCount) {
		super();
		this.attachCount = attachCount;
	}

	public int getAttachCount() {
		return attachCount;
	}

	public void setAttachCount(int attachCount) {
		this.attachCount = attachCount;
	}

	@Override
	public String toString() {
		return "BoardExt [no=" + getNo() + ", title=" + getTitle() + ", memberId=" + getMemberId() + ", attachCount=" + attachCount + "]";
	}

}
