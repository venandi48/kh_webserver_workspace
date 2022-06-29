package kh.java.reflection;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class Main {

	public static void main(String[] args) throws Exception {
		Main main = new Main();
//		main.test1();
		main.test2();
	}

	// ctr + f11 + 자바어플리케이션
	/**
	 * 메소드 호출
	 */
	private void test2() throws Exception {
		Class<?> clz = Class.forName("kh.java.reflection.Sample");
		Sample sample = (Sample) clz.getDeclaredConstructor(int.class, String.class).newInstance(123, "abc");
		
		Method[] methods = clz.getDeclaredMethods();
		for(Method method : methods) {
			System.out.println(method);
		}
		
		Method getStr = clz.getDeclaredMethod("getStr", null);
		String str = (String) getStr.invoke(sample);
		System.out.println(str); // abc

		Method setStr = clz.getDeclaredMethod("setStr", String.class);
		setStr.invoke(sample, "홍길동"); // 반환타입은 null
		
		String str2 = (String) getStr.invoke(sample);
		System.out.println(str2); // 홍길동
	}

	
	
	
	/**
	 * Reflection API
	 *  - 클래스객체의 정보를 가지고 객체를 제어하는 기술
	 *  - 생성자호출 | 메소드호출 | 필드값 제어
	 */
	private void test1() {
		Sample sample1 = new Sample();
		System.out.println(sample1);
		
		Sample sample3 = new Sample(123, "abc");
		System.out.println(sample3);
		
		// 클래스객체 가져오기
		try {
			Class<?> clz = Class.forName("kh.java.reflection.Sample");
			System.out.println(clz);
			
			// 기본생성자
			Class<?>[] param = null;
			Constructor<Sample> constructor1 = (Constructor<Sample>) clz.getDeclaredConstructor(param);
			Sample sample2 = constructor1.newInstance();
			System.out.println(sample2);
			
			// 파라미터생성자
			Constructor<Sample> constructor2 = (Constructor<Sample>) clz.getDeclaredConstructor(int.class, String.class);
			Sample sample4 = constructor2.newInstance(456, "def");
			System.out.println(sample4);
			
		} catch (ClassNotFoundException | NoSuchMethodException | SecurityException | InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
			e.printStackTrace();
		}
	}
}
