package com.kh.uml.association.aggregaion;

public class BookInfo {
	private String title;
	private String author;
	private String publisher;

	public BookInfo() {
		super();
		// TODO Auto-generated constructor stub
	}

	public BookInfo(String title, String author, String publisher) {
		super();
		this.title = title;
		this.author = author;
		this.publisher = publisher;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getPublisher() {
		return publisher;
	}

	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}

	@Override
	public String toString() {
		return "BookInfo [title=" + title + ", author=" + author + ", publisher=" + publisher + "]";
	}

}
