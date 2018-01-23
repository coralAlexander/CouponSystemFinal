package DAOs;

import java.util.Collection;

import Exceptions.CouponSystemException;
import Models.Coupon;

public interface CustomerCouponDAO {

	public void linkCoupon(int custID, int coupID, int amount) throws CouponSystemException;
	public void deleteExpiredCoupons() throws CouponSystemException;
	public void deleteCouponsByCompanyID(int compID) throws CouponSystemException;
	public void deleteCouponsByCustomerID(int custID) throws CouponSystemException;
	public void deleteCouponsByID(int coupID) throws CouponSystemException;
	public Collection<Coupon> getPurchasedCouponsByCustomer(int custID) throws CouponSystemException;
	public Collection<Coupon> getPurchasedCouponsByCustomerByType(int custID, String coupType)throws CouponSystemException;
	public Collection<Coupon> getPurchasedCouponsByCustomerByPrice(int custID, int price) throws CouponSystemException;
	public boolean couponAlreadyPurchased(int coupID, int custID) throws CouponSystemException;
	
}
