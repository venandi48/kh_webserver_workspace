package com.kh.uml.dependency;

/**
 * Dependency 의존관계
 *  - User#buy메소드에서 지역변수(매개변수)로 Item을 참조
 */
public class User {
	private String username;
	
	public void buy(Item item) {
		System.out.println(username + "이 " + item.getName()+ "을 " + item.getPrice() +"원에 구매한다.");
	}
}
