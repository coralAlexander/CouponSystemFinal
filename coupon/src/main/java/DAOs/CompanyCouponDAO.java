package DAOs;

import Exceptions.CouponSystemException;

public interface CompanyCouponDAO {
	
	public void linkCoupon(int compID, int coupID, int amount) throws CouponSystemException;
	public void deleteExpiredCoupons() throws CouponSystemException;
	public void deleteCouponsByCompanyID(int compID) throws CouponSystemException;
	public void deleteCouponsByCustomerID(int custID) throws CouponSystemException;
	public void deleteCouponsByID(int coupID) throws CouponSystemException;
	public int getCouponAmount(int coupID) throws CouponSystemException;
	public void updateCouponAmount(int coupID, int newAmount) throws CouponSystemException;
}
