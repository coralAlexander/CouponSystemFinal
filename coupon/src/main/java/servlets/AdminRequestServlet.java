package servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class AdminRequestServlet extends HttpServlet{
	
	private static final long serialVersionUID = 1L;
	static Logger logger = LoggerFactory.getLogger(CompanyRequestsServlet.class);
	
    public void doGet(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {

    	/*String companyIdString = request.getParameter("companyId");
    	int companyId = Integer.parseInt(companyIdString);
    	CouponSystem cs1 = CouponSystem.getInstance();
		AdminFacade af = null;
			try {
				af = (AdminFacade) cs1.login("admin", "1234", ClientType.ADMIN);
				response.getWriter().println(af.getCompany(companyId));
			} catch (CouponSystemException e) {
				e.printStackTrace();
			}	
			response.setContentType("application/json");
			response.setStatus(HttpServletResponse.SC_OK);
            logger.trace("Before switch");*/
    }

}
