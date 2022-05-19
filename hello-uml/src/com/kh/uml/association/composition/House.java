package com.kh.uml.association.composition;

import java.util.ArrayList;
import java.util.List;

/**
 * Composition 합성관계
 * 
 * House - Room
 *  - House가 Room객체의 생명주기를 결정
 *  - House객체가 제거되면 Room객체도 제거
 * 
 */
public class House {
	private String address;
	private List<Room> rooms;
	
	public House(int roomCnt) {
		rooms = new ArrayList<>();
		for(int i = 0; i< roomCnt; i++) {
			rooms.add(new Room());
		}
	}
}
