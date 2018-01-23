package Facades;

import java.util.Date;
import java.util.ArrayList;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.google.gson.JsonObject;

import DAOs.CompanyCouponDAOdb;
import DAOs.CompanyDAO;
import DAOs.CompanyDAOdb;
import DAOs.CouponDAO;
import DAOs.CouponDAOdb;
import DAOs.CustomerCouponDAOdb;
import Exceptions.CouponSystemException;
import Models.Company;
import Models.Coupon;

public class CompanyFacade implements ClientFacade {

	private CompanyDAO companyDAO = new CompanyDAOdb();
	private CouponDAO couponDAO = new CouponDAOdb();
	private CompanyCouponDAOdb companyCouponDAOdb = new CompanyCouponDAOdb();
	private CustomerCouponDAOdb customerCouponDAOdb = new CustomerCouponDAOdb();

	private int company_ID;

	public CompanyFacade(int company_ID) {
		super();
		this.company_ID = company_ID;
	}

	/** Login for user of Company type */
	public CompanyFacade loginCompany(String user, String password) throws CouponSystemException {

		// Verifying correct user credentials (with no hint where user failed -
		// avoiding credentials guessing)
		if (companyDAO.login(user, password) < 0) {
			throw new CouponSystemException("Invalid username or password, try again.");
		}
		// Returning Company Facade with logged in Company ID
		return new CompanyFacade(companyDAO.login(user, password));
	}

	/** Writes provided Coupon into DB and links logged in Company to it */
	public JSONObject createCoupon(Coupon coupon, int amount) throws CouponSystemException {
		JSONObject jsonObject= null;
		// Creating Coupon in DB according to Coupon object provided and saving
		// its ID to variable
		int couponID;
		//boolean exists = couponDAO.couponTitleExists(coupon);
			couponID = couponDAO.createCoupon(coupon);
		if (couponID != -1) {
			companyCouponDAOdb.linkCoupon(this.company_ID, couponID, amount);
			jsonObject = couponToJson(coupon);
		} 
		else {
			jsonObject = errorMessageCouponNotFound(coupon);
	    }
		return jsonObject;
}

	/** Removes provided Coupon from DB - both from main and secondary tables */
	public void removeCoupon(Coupon coupon) throws CouponSystemException {

		// Fetching Coupon ID
		int couponID = coupon.getId();

		// Removing Coupon from secondary tables
		companyCouponDAOdb.deleteCouponsByID(couponID);
		customerCouponDAOdb.deleteCouponsByID(couponID);
		// Removing Coupon from main Coupon table
		couponDAO.removeCoupon(coupon);
	}

	/** Updates provided Coupon in DB with new End Date and Price received */
	public void updateCoupon(Coupon coupon, Date new_date, int new_price) throws CouponSystemException {

		// Setting new End Date and Price values
		coupon.setEnd_date(new_date);
		coupon.setPrice(new_price);
		// Updating Coupon in the DB
		couponDAO.updateCoupon(coupon);
	}

	/** Returns current Company object */
	public Company getCompany() throws CouponSystemException {

		int companyID = this.company_ID;

		// Fetching Company with current Company ID from DB
		Company company = companyDAO.getCompany(companyID);
		// Returning Company object
		return company;
	}

	/** Returns all Coupons of current Company */
	public List<Coupon> getAllCompanyCoupons() throws CouponSystemException {

		int companyID = this.company_ID;

		// Creating new list to contain Coupon objects
		List<Coupon> couponsList = new ArrayList<>();
		// Fetching Coupons by current Company ID and adding them to the list
		couponsList = (List<Coupon>) couponDAO.getCouponsByCompany(companyID);
		// Returning Coupons list
		return couponsList;
	}

	/** Receives Coupon type and returns all Coupons of this Coupon type */
	public List<Coupon> getAllCouponsByType(String coupType) throws CouponSystemException {
		// ToDo - convert string to enum.
		// Creating new list to contain Coupon objects
		List<Coupon> couponsList = new ArrayList<>();
		// Fetching Coupons by Coupon type and adding them to the list
		couponsList = (List<Coupon>) couponDAO.getCouponsByType(coupType, this.company_ID);
		// Returning Coupons list
		return couponsList;
	}

	/** Receives Price and returns all Coupons below this Price */
	public List<Coupon> getAllCouponsByPrice(int price) throws CouponSystemException {

		// Creating new list to contain Coupon objects
		List<Coupon> couponsList = new ArrayList<>();
		// Fetching Coupons by Price and adding them to the list
		couponsList = (List<Coupon>) couponDAO.getCouponsByPrice(price, this.company_ID);
		// Returning Coupons list
		return couponsList;
	}

	/** Receives Date and returns all Coupons with End Date before this Date */
	public List<Coupon> getAllCouponsByDate(Date date) throws CouponSystemException {

		// Creating new list to contain Coupon objects
		List<Coupon> couponsList = new ArrayList<>();
		// Fetching Coupons by End Date and adding them to the list
		couponsList = (List<Coupon>) couponDAO.getCouponsByDate(date, this.company_ID);
		// Returning Coupons list
		return couponsList;
	}

	/** Returns requested Coupon object */
	public Coupon getCoupon(int id) throws CouponSystemException {
		// Fetching Coupon with given ID from DB
		Coupon coupon = couponDAO.getCoupon(id);
		// Returning Coupon object
		return coupon;
	}
	/**
	 *  Convert company object to Json object .
	 * @param company
	 * @return  Json Object .
	 */
	@SuppressWarnings("unchecked")
	private JSONObject  companyToJson(Company company) {
		// Company to json object
				JSONObject jsonObject = new JSONObject();
				jsonObject.put("email", company.getEmail());
				jsonObject.put("id", company.getId());
				jsonObject.put("name", company.getName());
				jsonObject.put("password", company.getPassword());
				return jsonObject;
         }
	/**
	 * Convert the coupon object to Json object , in case of Success flow .
	 * @param coupon
	 * @return Json  object .
	 */
	@SuppressWarnings("unchecked")
	private JSONObject couponToJson (Coupon coupon ) {
		JSONObject jsonObjectCoupon = new JSONObject();
		jsonObjectCoupon.put("id", coupon.getId());
		jsonObjectCoupon.put("Title", coupon.getTitle());
		jsonObjectCoupon.put("Start_Date", coupon.getStart_date());
		jsonObjectCoupon.put("End_Date", coupon.getEnd_date());
		JSONObject jsonObjectCouponSuccess = new JSONObject();
		jsonObjectCouponSuccess.put("Success coupon created", jsonObjectCoupon);
		return jsonObjectCouponSuccess;	
	}
	/**
	 * Convert the coupon object to Json object , in case of Error flow .
	 * @param coupon
	 * @return Json object .
	 */
	@SuppressWarnings("unchecked")
	private JSONObject errorMessageCouponNotFound (Coupon coupon ) {
		JSONObject messageObject = new JSONObject();
		messageObject.put("id", coupon.getId());
		messageObject.put("Title", coupon.getTitle());
		JSONObject errorMessageObject = new JSONObject();
		errorMessageObject.put("coupon not foud ", messageObject);
		return errorMessageObject;	
	}
}