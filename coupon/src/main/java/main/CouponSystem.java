package main;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import connectionPool.ConnectionPool;
import daos.CompanyDAO;
import daos.CompanyDAOdb;
import daos.CustomerDAO;
import daos.CustomerDAOdb;
import exceptions.CouponSystemException;
import facades.AdminFacade;
import facades.ClientFacade;
import facades.ClientType;
import facades.CompanyFacade;
import facades.CustomerFacade;
import main.CouponSystem;
import thread.DailyCleanupThread;

public class CouponSystem {
	static Logger logger = LoggerFactory.getLogger(CouponSystem.class);
	private static CouponSystem instance;
	//private DailyCleanupThread dailyThread = new DailyCleanupThread();

	private CompanyDAO companyDAOdb = new CompanyDAOdb();
	private CustomerDAO customerDAOdb = new CustomerDAOdb();
	private AdminFacade admin = new AdminFacade();
	
	public CouponSystem() {
		
		/*Thread dailyTask = new Thread(dailyThread);
		dailyTask.start();
		logger.trace("Daily thread run.");*/
	}

	public static synchronized CouponSystem getInstance() {

		if (instance == null) {
			instance = new CouponSystem();
		}

		return instance;
	}

	public ClientFacade login(String user, String pass, ClientType client) throws CouponSystemException {

		switch (client) {
		case ADMIN:

			AdminFacade adminFacade = AdminFacade.loginAdmin(user, pass);

			return adminFacade;

		case COMPANY:

			int companyID = companyDAOdb.login(user, pass);

			CompanyFacade company = new CompanyFacade(companyID);

			return company;
		case CUSTOMER:

			int customerID = customerDAOdb.login(user, pass);

			CustomerFacade customer = new CustomerFacade(customerID);

			return customer;

		default:

			return null;
		}
	}

	public void shutDown() {
		try {
			System.out.println("Coupon system will go down in 10 seconds.");
			Thread.sleep(100);
			System.out.println("System is shutting down.");
		} catch (InterruptedException e1) {
			System.out.println("Sleep of current shutdown process has been failed.");
		}
		//dailyThread.interrupt();
		try {
			ConnectionPool.getInstance().CloseAllConnections();
			System.out.println("All connection are closed");
		} catch (CouponSystemException e) {
			System.out.printf("Closing all connection action received an exception", e);
		}
	}

}
