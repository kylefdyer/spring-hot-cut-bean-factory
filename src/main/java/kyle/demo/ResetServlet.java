package kyle.demo;

import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServlet;

import kyle.demo.dependency.factory.UrlConfiguredBeanLookupDependencyFactory;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

public class ResetServlet extends HttpServlet {

	private UrlConfiguredBeanLookupDependencyFactory dependencyFactory;
	
	@Override
	public void service(ServletRequest request, ServletResponse response)
			throws ServletException, IOException {
			
			dependencyFactory.invalidateCache();
			response.getWriter().write("Cache invalidated.");
	}
	
	
	@Override
	public void init(ServletConfig arg0) throws ServletException {
		super.init(arg0);
		WebApplicationContext ctx = WebApplicationContextUtils.getWebApplicationContext(this.getServletContext());
		
		// lookup beans
		dependencyFactory = (UrlConfiguredBeanLookupDependencyFactory) ctx.getBean("dependencyFactory");
	}
	
	private static final long serialVersionUID = 1L;

}
