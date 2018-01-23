package JettyServerMain;

import java.text.ParseException;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHandler;
import org.eclipse.jetty.servlet.ServletHolder;

import Exceptions.CouponSystemException;

public class MainJetty {

	public static void main(String[] args) throws Exception {
		
		AllRequestsServlet allRequestsServlet = new AllRequestsServlet();

        ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);
        context.addServlet(new ServletHolder(allRequestsServlet), "/company/*");

        Server server = new Server(8080);
        server.setHandler(context);

        server.start();
        server.join();
		
	}


}
