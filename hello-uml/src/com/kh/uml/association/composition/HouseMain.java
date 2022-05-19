package com.kh.uml.association.composition;

import java.util.ArrayList;
import java.util.List;

public class HouseMain {

	public static void main(String[] args) {
		List<House> houses = new ArrayList<>();
		houses.add(new House(3));
		houses.add(new House(4));
		houses.add(new House(1));

		System.out.println(houses);

		houses.remove(1); // 4개의 Room객체도 함께 제거(참조 끊김)
	}

}
