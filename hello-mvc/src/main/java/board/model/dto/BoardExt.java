package board.model.dto;

import java.util.List;

public class BoardExt extends Board {
	private int attachCount;
	private int commentCount;
	private List<Attachment> attachments;
	private List<BoardComment> comments;

	public int getCommentCount() {
		return commentCount;
	}

	public void setCommentCount(int commentCount) {
		this.commentCount = commentCount;
	}

	public List<Attachment> getAttachments() {
		return attachments;
	}

	public void setAttachments(List<Attachment> attachments) {
		this.attachments = attachments;
	}

	public int getAttachCount() {
		return attachCount;
	}

	public void setAttachCount(int attachCount) {
		this.attachCount = attachCount;
	}

	public void setBoardComments(List<BoardComment> comments) {
		this.comments = comments;
	}

	public List<BoardComment> getBoardComments() {
		return comments;
	}

	@Override
	public String toString() {
		return "BoardExt [attachCount=" + attachCount + ", attachments=" + attachments + ", comments=" + comments
				+ ", commentCount=" + commentCount + ", toString()=" + super.toString() + "]";
	}

}
