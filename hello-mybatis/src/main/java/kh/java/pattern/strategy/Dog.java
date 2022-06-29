package kh.java.pattern.strategy;

public class Dog extends Pet {

	// 생성자는 상속되지않으므로 별도작성
	public Dog(String name) {
		super(name);
		// TODO Auto-generated constructor stub
	}

	@Override
	String getReply() {
		return "멍멍";
	}

}
