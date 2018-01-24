package servlets;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.omg.CORBA.portable.ApplicationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import exceptions.CouponSystemException;
import facades.AdminFacade;
import facades.ClientType;
import main.CouponSystem;
import main.MainTest;
import models.Company;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author alexander coral
 *
 */
public class CompanyRequestsServlet extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	static Logger logger = LoggerFactory.getLogger(CompanyRequestsServlet.class);
	
    public void doGet(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
     String method=request.getParameter("method");
        switch (method) {
		case "getCompany":
			String companyIdString = request.getParameter("companyId");
	    	int companyId = Integer.parseInt(companyIdString);
				try {
				    AdminFacade	af = logIn ();
					response.getWriter().println(af.getCompany(companyId));
				} catch (CouponSystemException e) {
					e.printStackTrace();
				}	
				response.setContentType("application/json");
				response.setStatus(HttpServletResponse.SC_OK);
	            logger.trace("getCompany method");
			break;
		case "":
			
			break;
		default:
			break;
		}
    	
    }

    public void doPost(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
        /*Map<String, Object> pageVariables = createPageVariablesMap(request, null);

        String message = request.getParameter("message");

        response.setContentType("text/html;charset=utf-8");

        if (message == null || message.isEmpty()) {
            response.setStatus(HttpServletResponse.SC_FORBIDDEN);
        } else {
            response.setStatus(HttpServletResponse.SC_OK);
        }
        pageVariables.put("message", message == null ? "" : message);

        response.getWriter().println(PageGenerator.instance().getPage("page.html", pageVariables));
    */
    }

    private static Map<String, Object> createPageVariablesMap(HttpServletRequest request, Company company) {
        Map<String, Object> pageVariables = new HashMap<>(); 
        pageVariables.put("companyId", company.getId());
        pageVariables.put("method", request.getMethod());
        pageVariables.put("URL", request.getRequestURL().toString());
        pageVariables.put("pathInfo", request.getPathInfo());
        pageVariables.put("sessionId", request.getSession().getId());
        pageVariables.put("parameters", request.getParameterMap().toString());
        return pageVariables;
    }
    
    public AdminFacade logIn () throws CouponSystemException {
    	CouponSystem cs1 = CouponSystem.getInstance();
		AdminFacade af = null;
		af = (AdminFacade) cs1.login("admin", "1234", ClientType.ADMIN);
    	return af;
    	
    }
}
