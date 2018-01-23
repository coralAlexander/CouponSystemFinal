package junit;

import static org.junit.Assert.assertEquals;
import java.text.ParseException;

import org.json.simple.JSONObject;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Rule;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import exceptions.CouponSystemException;
import facades.AdminFacade;
import facades.ClientType;
import httpClient.HttpClient;
import main.CouponSystem;
import models.Company;

/**
 * @author Alexander Coral
 *
 */
public class Junit  {
	static Logger logger = LoggerFactory.getLogger(Junit.class);
	

	// ###################################### ===== Tests ===== ######################################################
	@Test() 
	public void test1_GetCompany() throws Exception {
    HttpClient client =new HttpClient();
    int conferenceIdNumber=2;
		String expected = "{\"password\":\"234\",\"name\":\"companyB\",\"id\":2,\"email\":\"emailB@mail.com\"}";
        String actual = client.sendGet(conferenceIdNumber);
        //JSONObject actual = logIn().getCompany(2);
        assertEquals(expected.toString(),actual.toString());
	}
	@Ignore
	@Test()
	public void test2_GetAllConpanies() throws CouponSystemException {
		String expected = "{\"ArrayOfCompaniesObjects\":[{\"password\":\"123\",\"name\":\"companyA\",\"id\":1,\"email\":\"emailA@mail.com\"},{\"password\":\"234\",\"name\":\"companyB\",\"id\":2,\"email\":\"emailB@mail.com\"},{\"password\":\"testPass\",\"name\":\"new2Company\",\"id\":4,\"email\":\"Test@email.com\"},{\"password\":\"testPass\",\"name\":\"new3Company\",\"id\":5,\"email\":\"Test3@email.com\"},{\"password\":\"testPass\",\"name\":\"new4Company\",\"id\":6,\"email\":\"Test4@email.com\"}]}";
		JSONObject actual = logIn().getAllCompanies();
		assertEquals(expected, actual.toJSONString());
		//System.out.println(actual.toJSONString());
		writeToLogs(expected.equals(actual.toJSONString()));
		
	}
	//###############################################################################################################
	
	/**
	 * log in method 
	 * @return AdminFacade 
	 * @throws CouponSystemException
	 */
	public AdminFacade logIn() throws CouponSystemException {
		CouponSystem cs1 = CouponSystem.getInstance();
		AdminFacade af = (AdminFacade) cs1.login("admin", "1234", ClientType.ADMIN);
		return af;	
	}
	
	/**
	 * Method write to log file depends of result.
	 * @param write
	 */
	public void writeToLogs(boolean write) {
		if (write == true ) {
			System.out.println("Test success");
			logger.trace("Test get allCompanies test - pass.");
		}
		else {
			logger.trace("Test get allCompanies test - failed.");
			System.out.println("Test failed");
		}
	}
	public String createCompanyToString() {
		return null; 
	}
}
