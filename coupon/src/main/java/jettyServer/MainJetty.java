package jettyServer;

import java.text.ParseException;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHandler;
import org.eclipse.jetty.servlet.ServletHolder;

import exceptions.CouponSystemException;
import servlets.CompanyRequestsServlet;

public class MainJetty {

	public static void main(String[] args) throws Exception {
		
		CompanyRequestsServlet companyRequestsServlet = new CompanyRequestsServlet();
		

        ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);
        context.addServlet(new ServletHolder(companyRequestsServlet), "/company/*");

        Server server = new Server(8080);
        server.setHandler(context);

        server.start();
        server.join();
		
	}


}
