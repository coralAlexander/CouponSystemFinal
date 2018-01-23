package main;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import exceptions.CouponSystemException;
import facades.AdminFacade;
import facades.ClientType;
import facades.CompanyFacade;
import facades.CustomerFacade;
import facades.MethodsRun;
import models.Company;
import models.Coupon;
import models.Customer;


public class MainTest {
	static Logger logger = LoggerFactory.getLogger(MainTest.class);
	public static void main(String[] args) throws CouponSystemException, ParseException {
		
        logger.trace("Coupon System process started.");

		
		runAsAdmin(MethodsRun.CreatingCompany);
		//runAsCompany(MethodsRun.UpdatingCompany);
		//runAsCustomer(MethodsRun.GetAllCustomerCopuponByType);
    
	}
	public static void runAsAdmin(MethodsRun method) throws CouponSystemException {
		CouponSystem cs1 = CouponSystem.getInstance();
		AdminFacade af = (AdminFacade) cs1.login("admin", "1234", ClientType.ADMIN);
        logger.trace("Before switch");
		switch (method) {
        
        case GetCompany:
    		System.out.println("======ADMIN FACADE TEST======");
    		System.out.println();
    		System.out.println("Company with ID 2 - " + af.getCompany(2));
    		
    		 //Shut down Admin System process
   		 System.out.println("Admin system is shutting down");
   		 cs1.shutDown();

		case GetAllCompanies:
			// 1. FETCHING LIST OF ALL COMPANIES
			System.out.println("1. List of companies : " + af.getAllCompanies());
		
		case CreatingCompany:
			System.out.println("2. Creating company - ");
			Company companyToCreate = new Company();
			// Negative testing for existing name
			// companyToCreate.setName("companyA");
			companyToCreate.setName("new4Company");
			companyToCreate.setEmail("Test4@email.com");
			companyToCreate.setPassword("testPass");
			af.createCompany(companyToCreate);
		
		case UpdatingCompany:
			/*System.out.println("Updating company with ID 3 - ");
			Company companyToUpdate = af.getCompany(3);
			companyToUpdate.setEmail("NewCompEmail245");
			companyToUpdate.setPassword("NewPass12");
			af.updateCompany(companyToUpdate);*/	
		
		case RemovingCompany:
			/*System.out.println("Removing company with ID 3 - ");
			Company companyToDelete = af.getCompany(3);
			af.removeCompany(companyToDelete);*/
		
		case GetAllCustomers :
			System.out.println("List of customers : " + af.getAllCustomers());
		
		case CreatingCustomer :	
			// 7. CREATING CUSTOMER -
			System.out.println("Creating customer : ");
			Customer customerToCreate = new Customer();
			// Negative testing for existing name
			// customerToCreate.setName("customerA");
			customerToCreate.setName("new1Customer");
			customerToCreate.setPassword("TestPass");
			af.createCustomer(customerToCreate);
        
		case FindSpecificCustomer :
			System.out.println("Customer with ID 1 - " + af.getCustomer(1));
		
		case UpdatingCustomer:
			// 9. UPDATING CUSTOMER, EXCEPT NAME -
			System.out.println("Updating customer with ID 1 - ");
			Customer customerToUpdate = af.getCustomer(1);
			customerToUpdate.setPassword("UpdPass");
			af.updateCustomer(customerToUpdate);
        	
		case RemovingCustomer:
			// 10. REMOVING CUSTOMER -
			System.out.println("Removing customer with ID 4 - ");
			Customer customerToDelete = af.getCustomer(4);
			af.removeCustomer(customerToDelete);
			
		default: 
			System.out.println("Method " + method +" not found" );
		}
		 //Shut down Admin System process
		 System.out.println("Admin system is shutting down");
		 cs1.shutDown();
	}

	public static void runAsCompany(MethodsRun method) throws CouponSystemException{
		System.out.println("=====COMPANY FACADE TEST=====");
		CouponSystem cs2 = CouponSystem.getInstance();
		CompanyFacade compFacade = (CompanyFacade) cs2.login("companyA", "123", ClientType.COMPANY);
		
		switch(method) {
		
		case GetAllCompaniesCoupons :
			System.out.println("Fetching all company's coupons list - " + compFacade.getAllCompanyCoupons());
			
		case CreateCompanyCoupon :
			System.out.println("Create company's coupon - ");

			Date startDate = new GregorianCalendar(2017, 5, 21).getTime();
			Date endDate1 = new GregorianCalendar(2018, 5, 21).getTime();
			Date endDate2 = new GregorianCalendar(2018, 7, 21).getTime();
			Coupon couponToCreate = new Coupon();
			// Negative testing for existing title
			// couponToCreate.setTitle("couponA");
			couponToCreate.setTitle("newCoupon");
			couponToCreate.setStart_date(startDate);
			couponToCreate.setEnd_date(endDate1);
			couponToCreate.setType("Video");
			couponToCreate.setMessage("NewMessage");
			couponToCreate.setPrice(100);
			couponToCreate.setExpired("no");
			couponToCreate.setImage("NewImage");

			compFacade.createCoupon(couponToCreate, 10);
		
		case UpdateCompanyCoupon:
			// UPDATE COMPANY'S COUPON
			Date endDate3 = new GregorianCalendar(2018, 7, 21).getTime();
			System.out.println("Updating company first coupon - ");
			ArrayList<Coupon> couponsUpdate = (ArrayList<Coupon>) compFacade.getAllCompanyCoupons();
			Coupon couponToUpdate = couponsUpdate.get(0);
			int newPrice = 200;
			compFacade.updateCoupon(couponToUpdate, endDate3, newPrice);
		
		case RemoveCompanyCoupon:
			// REMOVE COMPANY'S COUPON
			System.out.println("Removing company second coupon - ");
			ArrayList<Coupon> couponsRemove = (ArrayList<Coupon>) compFacade.getAllCompanyCoupons();
			Coupon couponToRemove = couponsRemove.get(1);
			compFacade.removeCoupon(couponToRemove);
			
		case GetCompanyCouponByType:
			// GET COMPANY'S COUPONS BY TYPE
			System.out.println("Getting all company's coupons by type Food - " + compFacade.getAllCouponsByType("Food"));
		
		case GetCompanyCouponByPrice:
		// GET COMPANY'S COUPONS BY PRICE
				System.out.println("Getting all company's coupons by price 130 in descending order - "
						+ compFacade.getAllCouponsByPrice(130));
				
		case GetCompanyCouponByDate:
		// GET COMPANY'S COUPONS BY DATE
			Date endDate4 = new GregorianCalendar(2018, 7, 21).getTime();	
			System.out.println(
						"Getting all company's coupons until date 2018, 7, 21 - " + compFacade.getAllCouponsByDate(endDate4)); 
		default:
			System.out.println("Method " + method +" not found" );
		}
		// Shut down Company System process
				System.out.println("Company system is shutting down");
				cs2.shutDown();
	}
	public static void runAsCustomer(MethodsRun method) throws CouponSystemException{
		// Making third CS instance and logging in as a Customer
				CouponSystem cs3 = CouponSystem.getInstance();
				CustomerFacade custf = (CustomerFacade) cs3.login("customerB", "23456", ClientType.CUSTOMER);
		switch(method) {
		
		case PurchaseCoupon:
		// PURCHASING COUPON -
				System.out.println("Purchasing coupon with ID 3 - ");
				Coupon coupon = custf.getCouponByID(3);
				custf.purchaseCoupon(coupon);
		
		case GetAllCustomerCoupon:
		// FETCHING ALL CUSTOMER'S COUPONS LIST
				System.out.println("Getting all customer's coupons list - " + custf.getAllCustomerCoupons());
				
		case GetAllCustomerCopuponByType:
		// FETCHING ALL CUSTOMER'S COUPONS BY TYPE
				System.out.println(
						"Getting all customer's coupons by type Alcohol - " + custf.getAllCustomerCouponsByType("Alcohol"));
		
		case GetAllCustomerCouponByPrice:
		// FETCHING ALL CUSTOMER'S COUPONS BY PRICE
				System.out.println(
						"Getting all customer's coupons up to price - 250 - " + custf.getAllCustomerCouponsByPrice(250));

		default:
			System.out.println("Method " + method +" not found" );
		}
		// Shut down Customer System process
				System.out.println("Customer system is shutting down");
				cs3.shutDown();
	}
	
}
