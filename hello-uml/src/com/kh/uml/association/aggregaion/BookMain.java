package com.kh.uml.association.aggregaion;

import java.util.ArrayList;
import java.util.List;

public class BookMain {

	public static void main(String[] args) {
		BookInfo bookInfo = new BookInfo("마지막 몰입", "짐 퀵", "비지니스북스");
		
		List<Book> list = new ArrayList<>();
		list.add(new Book(1L, bookInfo));
		list.add(new Book(1L, bookInfo));
		list.add(new Book(1L, bookInfo));
		
		System.out.println(list);
		
		list.remove(2); // 세번째 Book객체 제거
		System.out.println(list.get(0).getBookInfo());
	}

}
