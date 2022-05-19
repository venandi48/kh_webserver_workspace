package com.kh.uml.association;

/**
 * 연관관계 Association
 *  - 다른 클래스를 필드로 참조하는 경우
 * Person - Phone 단방향(Person만 Phone을 알고있음)
 * Person - Home 양방향(Person도 Home을 알고있고, Home도 Person을 알고있음)
 *
 */
public class Person {
	private String id;
	private String name;
	private Phone phone;
	private Home home;
}
