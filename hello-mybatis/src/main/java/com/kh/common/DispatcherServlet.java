package com.kh.common;

import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.student.model.dao.StudentDao;
import com.kh.student.model.dao.StudentDaoImpl;
import com.kh.student.model.service.StudentService;
import com.kh.student.model.service.StudentServiceImpl;

/**
 * Servlet implementation class DispatcherServlet
 */
@WebServlet("*.do")
public class DispatcherServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	// key = url-pattern, value = controller객체(AbstractController자식타입)
	private Map<String, AbstractController> urlCommandMap = new HashMap<>();
       
	public DispatcherServlet() throws ClassNotFoundException, InstantiationException, IllegalAccessException,
			IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException {

		// 1. urlCommand.properties load -> prop
    	Properties prop = new Properties();
    	String filename = DispatcherServlet.class.getResource("/urlCommand.properties").getPath();
		try {
			prop.load(new FileReader(filename));
		} catch (IOException e) {
			e.printStackTrace();
		}

		// 2. urlCommandMap 요소추가 (url - className)
		StudentDao studentDao = new StudentDaoImpl();
		StudentService studentService = new StudentServiceImpl(studentDao); // controller 주입용 공용 service객체
		Set<String> urls = prop.stringPropertyNames();
		for (String url : urls) {
			String className = prop.getProperty(url);

			// reflection api를 사용해 new 연산자 없이 객체 생성
			Class<?> clz = Class.forName(className);
			Class<?>[] param = {StudentService.class};
			AbstractController controller = (AbstractController) clz.getDeclaredConstructor(param).newInstance(studentService);
			urlCommandMap.put(url, controller);
		}
		System.out.println("urlCommandMap = " + urlCommandMap);
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		try {
			// 1. controller 연결
			String url = request.getRequestURI().replace(request.getContextPath(), ""); // /student/studentEnroll.do
			AbstractController controller = urlCommandMap.get(url);
			if (controller == null) {
				// response.setStatus(404); // NotFound처리
				response.sendError(HttpServletResponse.SC_NOT_FOUND, request.getRequestURI());
				return;
			}
			String method = request.getMethod();
			String viewName = null;
			switch (method) {
			case "GET":
				viewName = controller.doGet(request, response);
				break;
			case "POST":
				viewName = controller.doPost(request, response);
				break;
			}

			// 2. view단 처리 (forward/redirect/처리X)
			if (viewName != null) {
				// redirect
				if (viewName.startsWith("redirect:")) {
					String location = request.getContextPath() + viewName.replace("redirect:", "");
					response.sendRedirect(location);
				}
				// forward
				else {
					String prefix = "/WEB-INF/views/";
					String suffix = ".jsp";
					viewName = prefix + viewName + suffix;
					request.getRequestDispatcher(viewName).forward(request, response);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
