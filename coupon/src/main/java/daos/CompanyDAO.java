package daos;

import java.util.Collection;
import java.util.List;

import exceptions.CouponSystemException;
import models.Company;
import models.Coupon;

public interface CompanyDAO {

	int createCompany(Company company) throws CouponSystemException;
	void removeCompany(Company company) throws CouponSystemException;
	void updateCompany(Company company) throws CouponSystemException;
	Company getCompany(int id) throws CouponSystemException;
	List<Company> getAllCompanies() throws CouponSystemException;
	Collection<Coupon> getCoupons(int id) throws CouponSystemException;
	int login(String companyName, String password) throws CouponSystemException;
	boolean companyNameExists(Company company) throws CouponSystemException;
	
}
