package kyle.demo;

import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServlet;

import kyle.demo.service.Service;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

public class MyServlet extends HttpServlet {
	
	private Service service;

	@Override
	public void service(ServletRequest request, ServletResponse response)
			throws ServletException, IOException {
		
			String result = service.callService();
			response.getWriter().write("result is: " + result);

	}
	
	
	@Override
	public void init(ServletConfig arg0) throws ServletException {
		super.init(arg0);
		WebApplicationContext ctx = WebApplicationContextUtils.getWebApplicationContext(this.getServletContext());
		
		// lookup bean
		service = (Service) ctx.getBean("service");
	}
	
	private static final long serialVersionUID = 1L;

}
