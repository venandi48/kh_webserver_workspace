package kh.java.pattern.strategy;

public class Main {

	public static void main(String[] args) {
		Main main = new Main();
		main.test1();
	}

	
	private void test1() {
		Person chulsu = new Person("철수", new Dog("몽실이")); // 반려견
		Person younghee = new Person("영희", new Cat("치즈")); // 반려묘
		
		chulsu.playWithPet();
		younghee.playWithPet();
		
		Person yj = new Person("유진", new Snake("벨라레")); // 반려뱀
		yj.playWithPet();
	}

}
