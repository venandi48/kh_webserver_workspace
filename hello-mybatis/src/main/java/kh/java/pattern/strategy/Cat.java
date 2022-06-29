package kh.java.pattern.strategy;

public class Cat extends Pet {

	// 생성자는 상속되지않으므로 별도작성
	public Cat(String name) {
		super(name);
		// TODO Auto-generated constructor stub
	}

	@Override
	String getReply() {
		return "야옹";
	}

}