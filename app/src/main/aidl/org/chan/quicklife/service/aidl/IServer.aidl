
package org.chan.quicklife.service.aidl;

import org.chan.quicklife.entity.User;
import org.chan.quicklife.entity.Friend;
import org.chan.quicklife.entity.Business;
import org.chan.quicklife.entity.Coupon;
import org.chan.quicklife.entity.ActionLog;
import org.chan.quicklife.entity.Recommend;
import org.chan.quicklife.entity.Favorite;
interface IServer {
    
    List<User> getAllUser();
    List<User> getNotFriendUser(long id);
	User login(in User user);
	boolean register(in User user);
	boolean location(in User user);
	boolean modify(in User user);
	boolean canModify(long id,String pwd);
	boolean canRegister(String loginName);
	
	List<Business> getBusinessesByLoc(double lat,double lon);
    List<Business> getAllBusinesses();
    Business getBusiness(long id);
    List<Business> getBussByKeyname(String key);
    
    List<Coupon> getAllCoupons();
    List<Coupon> getHotCoupons();
    Coupon getCouponById(long couponId);
    List<Coupon> getCouponsByBuss(long bussId);
    
    boolean checkin(in ActionLog action);
    boolean consume(in ActionLog action);
    boolean comment(in ActionLog action);
    
    List<ActionLog> getAllAction();
    List<ActionLog> getFriendAction(long userId);
    List<ActionLog> getMyAction(long userId); 
     List<ActionLog> getActionByBuss(long bussId,int acType); 
     List<ActionLog> getActionIds(long userId);
     ActionLog getActionById(long acId);
    
    List<Friend> getFriends(long myId);
    boolean addFriend(long myId,long friendId,int state);
    boolean echoFriend(long shipId,int isPass); 
    List<Friend> getFriendApply(long id);
    
    List<Recommend> getRecommend(long userId);
    boolean addRecommend(in Recommend rec);
    
    boolean addFavorite(in Favorite favorite);
    List<Favorite> getFavorites(long userId);
    
    int getCheckinCount(long userId);
    int getCouponCount(long userId);
    int getFavoriteCount(long userId);
    int getShareCount(long userId);
    int getFriendCount(long userId);
    int getCommentCount(long userId);
}
