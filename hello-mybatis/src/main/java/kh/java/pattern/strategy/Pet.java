package kh.java.pattern.strategy;

public abstract class Pet {

	private String name;

	public Pet() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	abstract String getReply();

	public Pet(String name) {
		super();
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "Pet [name=" + name + "]";
	}

}
