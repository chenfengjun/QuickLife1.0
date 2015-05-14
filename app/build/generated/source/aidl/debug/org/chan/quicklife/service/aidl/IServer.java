/*
 * This file is auto-generated.  DO NOT MODIFY.
 * Original file: D:\\AndroidStudioProjects\\QuickLife1.01\\app\\src\\main\\aidl\\org\\chan\\quicklife\\service\\aidl\\IServer.aidl
 */
package org.chan.quicklife.service.aidl;
public interface IServer extends android.os.IInterface
{
/** Local-side IPC implementation stub class. */
public static abstract class Stub extends android.os.Binder implements org.chan.quicklife.service.aidl.IServer
{
private static final java.lang.String DESCRIPTOR = "org.chan.quicklife.service.aidl.IServer";
/** Construct the stub at attach it to the interface. */
public Stub()
{
this.attachInterface(this, DESCRIPTOR);
}
/**
 * Cast an IBinder object into an org.chan.quicklife.service.aidl.IServer interface,
 * generating a proxy if needed.
 */
public static org.chan.quicklife.service.aidl.IServer asInterface(android.os.IBinder obj)
{
if ((obj==null)) {
return null;
}
android.os.IInterface iin = obj.queryLocalInterface(DESCRIPTOR);
if (((iin!=null)&&(iin instanceof org.chan.quicklife.service.aidl.IServer))) {
return ((org.chan.quicklife.service.aidl.IServer)iin);
}
return new org.chan.quicklife.service.aidl.IServer.Stub.Proxy(obj);
}
@Override public android.os.IBinder asBinder()
{
return this;
}
@Override public boolean onTransact(int code, android.os.Parcel data, android.os.Parcel reply, int flags) throws android.os.RemoteException
{
switch (code)
{
case INTERFACE_TRANSACTION:
{
reply.writeString(DESCRIPTOR);
return true;
}
case TRANSACTION_getAllUser:
{
data.enforceInterface(DESCRIPTOR);
java.util.List<org.chan.quicklife.entity.User> _result = this.getAllUser();
reply.writeNoException();
reply.writeTypedList(_result);
return true;
}
case TRANSACTION_getNotFriendUser:
{
data.enforceInterface(DESCRIPTOR);
long _arg0;
_arg0 = data.readLong();
java.util.List<org.chan.quicklife.entity.User> _result = this.getNotFriendUser(_arg0);
reply.writeNoException();
reply.writeTypedList(_result);
return true;
}
case TRANSACTION_login:
{
data.enforceInterface(DESCRIPTOR);
org.chan.quicklife.entity.User _arg0;
if ((0!=data.readInt())) {
_arg0 = org.chan.quicklife.entity.User.CREATOR.createFromParcel(data);
}
else {
_arg0 = null;
}
org.chan.quicklife.entity.User _result = this.login(_arg0);
reply.writeNoException();
if ((_result!=null)) {
reply.writeInt(1);
_result.writeToParcel(reply, android.os.Parcelable.PARCELABLE_WRITE_RETURN_VALUE);
}
else {
reply.writeInt(0);
}
return true;
}
case TRANSACTION_register:
{
data.enforceInterface(DESCRIPTOR);
org.chan.quicklife.entity.User _arg0;
if ((0!=data.readInt())) {
_arg0 = org.chan.quicklife.entity.User.CREATOR.createFromParcel(data);
}
else {
_arg0 = null;
}
boolean _result = this.register(_arg0);
reply.writeNoException();
reply.writeInt(((_result)?(1):(0)));
return true;
}
case TRANSACTION_location:
{
data.enforceInterface(DESCRIPTOR);
org.chan.quicklife.entity.User _arg0;
if ((0!=data.readInt())) {
_arg0 = org.chan.quicklife.entity.User.CREATOR.createFromParcel(data);
}
else {
_arg0 = null;
}
boolean _result = this.location(_arg0);
reply.writeNoException();
reply.writeInt(((_result)?(1):(0)));
return true;
}
case TRANSACTION_modify:
{
data.enforceInterface(DESCRIPTOR);
org.chan.quicklife.entity.User _arg0;
if ((0!=data.readInt())) {
_arg0 = org.chan.quicklife.entity.User.CREATOR.createFromParcel(data);
}
else {
_arg0 = null;
}
boolean _result = this.modify(_arg0);
reply.writeNoException();
reply.writeInt(((_result)?(1):(0)));
return true;
}
case TRANSACTION_canModify:
{
data.enforceInterface(DESCRIPTOR);
long _arg0;
_arg0 = data.readLong();
java.lang.String _arg1;
_arg1 = data.readString();
boolean _result = this.canModify(_arg0, _arg1);
reply.writeNoException();
reply.writeInt(((_result)?(1):(0)));
return true;
}
case TRANSACTION_canRegister:
{
data.enforceInterface(DESCRIPTOR);
java.lang.String _arg0;
_arg0 = data.readString();
boolean _result = this.canRegister(_arg0);
reply.writeNoException();
reply.writeInt(((_result)?(1):(0)));
return true;
}
case TRANSACTION_getBusinessesByLoc:
{
data.enforceInterface(DESCRIPTOR);
double _arg0;
_arg0 = data.readDouble();
double _arg1;
_arg1 = data.readDouble();
java.util.List<org.chan.quicklife.entity.Business> _result = this.getBusinessesByLoc(_arg0, _arg1);
reply.writeNoException();
reply.writeTypedList(_result);
return true;
}
case TRANSACTION_getAllBusinesses:
{
data.enforceInterface(DESCRIPTOR);
java.util.List<org.chan.quicklife.entity.Business> _result = this.getAllBusinesses();
reply.writeNoException();
reply.writeTypedList(_result);
return true;
}
case TRANSACTION_getBusiness:
{
data.enforceInterface(DESCRIPTOR);
long _arg0;
_arg0 = data.readLong();
org.chan.quicklife.entity.Business _result = this.getBusiness(_arg0);
reply.writeNoException();
if ((_result!=null)) {
reply.writeInt(1);
_result.writeToParcel(reply, android.os.Parcelable.PARCELABLE_WRITE_RETURN_VALUE);
}
else {
reply.writeInt(0);
}
return true;
}
case TRANSACTION_getBussByKeyname:
{
data.enforceInterface(DESCRIPTOR);
java.lang.String _arg0;
_arg0 = data.readString();
java.util.List<org.chan.quicklife.entity.Business> _result = this.getBussByKeyname(_arg0);
reply.writeNoException();
reply.writeTypedList(_result);
return true;
}
case TRANSACTION_getAllCoupons:
{
data.enforceInterface(DESCRIPTOR);
java.util.List<org.chan.quicklife.entity.Coupon> _result = this.getAllCoupons();
reply.writeNoException();
reply.writeTypedList(_result);
return true;
}
case TRANSACTION_getHotCoupons:
{
data.enforceInterface(DESCRIPTOR);
java.util.List<org.chan.quicklife.entity.Coupon> _result = this.getHotCoupons();
reply.writeNoException();
reply.writeTypedList(_result);
return true;
}
case TRANSACTION_getCouponById:
{
data.enforceInterface(DESCRIPTOR);
long _arg0;
_arg0 = data.readLong();
org.chan.quicklife.entity.Coupon _result = this.getCouponById(_arg0);
reply.writeNoException();
if ((_result!=null)) {
reply.writeInt(1);
_result.writeToParcel(reply, android.os.Parcelable.PARCELABLE_WRITE_RETURN_VALUE);
}
else {
reply.writeInt(0);
}
return true;
}
case TRANSACTION_getCouponsByBuss:
{
data.enforceInterface(DESCRIPTOR);
long _arg0;
_arg0 = data.readLong();
java.util.List<org.chan.quicklife.entity.Coupon> _result = this.getCouponsByBuss(_arg0);
reply.writeNoException();
reply.writeTypedList(_result);
return true;
}
case TRANSACTION_checkin:
{
data.enforceInterface(DESCRIPTOR);
org.chan.quicklife.entity.ActionLog _arg0;
if ((0!=data.readInt())) {
_arg0 = org.chan.quicklife.entity.ActionLog.CREATOR.createFromParcel(data);
}
else {
_arg0 = null;
}
boolean _result = this.checkin(_arg0);
reply.writeNoException();
reply.writeInt(((_result)?(1):(0)));
return true;
}
case TRANSACTION_consume:
{
data.enforceInterface(DESCRIPTOR);
org.chan.quicklife.entity.ActionLog _arg0;
if ((0!=data.readInt())) {
_arg0 = org.chan.quicklife.entity.ActionLog.CREATOR.createFromParcel(data);
}
else {
_arg0 = null;
}
boolean _result = this.consume(_arg0);
reply.writeNoException();
reply.writeInt(((_result)?(1):(0)));
return true;
}
case TRANSACTION_comment:
{
data.enforceInterface(DESCRIPTOR);
org.chan.quicklife.entity.ActionLog _arg0;
if ((0!=data.readInt())) {
_arg0 = org.chan.quicklife.entity.ActionLog.CREATOR.createFromParcel(data);
}
else {
_arg0 = null;
}
boolean _result = this.comment(_arg0);
reply.writeNoException();
reply.writeInt(((_result)?(1):(0)));
return true;
}
case TRANSACTION_getAllAction:
{
data.enforceInterface(DESCRIPTOR);
java.util.List<org.chan.quicklife.entity.ActionLog> _result = this.getAllAction();
reply.writeNoException();
reply.writeTypedList(_result);
return true;
}
case TRANSACTION_getFriendAction:
{
data.enforceInterface(DESCRIPTOR);
long _arg0;
_arg0 = data.readLong();
java.util.List<org.chan.quicklife.entity.ActionLog> _result = this.getFriendAction(_arg0);
reply.writeNoException();
reply.writeTypedList(_result);
return true;
}
case TRANSACTION_getMyAction:
{
data.enforceInterface(DESCRIPTOR);
long _arg0;
_arg0 = data.readLong();
java.util.List<org.chan.quicklife.entity.ActionLog> _result = this.getMyAction(_arg0);
reply.writeNoException();
reply.writeTypedList(_result);
return true;
}
case TRANSACTION_getActionByBuss:
{
data.enforceInterface(DESCRIPTOR);
long _arg0;
_arg0 = data.readLong();
int _arg1;
_arg1 = data.readInt();
java.util.List<org.chan.quicklife.entity.ActionLog> _result = this.getActionByBuss(_arg0, _arg1);
reply.writeNoException();
reply.writeTypedList(_result);
return true;
}
case TRANSACTION_getActionIds:
{
data.enforceInterface(DESCRIPTOR);
long _arg0;
_arg0 = data.readLong();
java.util.List<org.chan.quicklife.entity.ActionLog> _result = this.getActionIds(_arg0);
reply.writeNoException();
reply.writeTypedList(_result);
return true;
}
case TRANSACTION_getActionById:
{
data.enforceInterface(DESCRIPTOR);
long _arg0;
_arg0 = data.readLong();
org.chan.quicklife.entity.ActionLog _result = this.getActionById(_arg0);
reply.writeNoException();
if ((_result!=null)) {
reply.writeInt(1);
_result.writeToParcel(reply, android.os.Parcelable.PARCELABLE_WRITE_RETURN_VALUE);
}
else {
reply.writeInt(0);
}
return true;
}
case TRANSACTION_getFriends:
{
data.enforceInterface(DESCRIPTOR);
long _arg0;
_arg0 = data.readLong();
java.util.List<org.chan.quicklife.entity.Friend> _result = this.getFriends(_arg0);
reply.writeNoException();
reply.writeTypedList(_result);
return true;
}
case TRANSACTION_addFriend:
{
data.enforceInterface(DESCRIPTOR);
long _arg0;
_arg0 = data.readLong();
long _arg1;
_arg1 = data.readLong();
int _arg2;
_arg2 = data.readInt();
boolean _result = this.addFriend(_arg0, _arg1, _arg2);
reply.writeNoException();
reply.writeInt(((_result)?(1):(0)));
return true;
}
case TRANSACTION_echoFriend:
{
data.enforceInterface(DESCRIPTOR);
long _arg0;
_arg0 = data.readLong();
int _arg1;
_arg1 = data.readInt();
boolean _result = this.echoFriend(_arg0, _arg1);
reply.writeNoException();
reply.writeInt(((_result)?(1):(0)));
return true;
}
case TRANSACTION_getFriendApply:
{
data.enforceInterface(DESCRIPTOR);
long _arg0;
_arg0 = data.readLong();
java.util.List<org.chan.quicklife.entity.Friend> _result = this.getFriendApply(_arg0);
reply.writeNoException();
reply.writeTypedList(_result);
return true;
}
case TRANSACTION_getRecommend:
{
data.enforceInterface(DESCRIPTOR);
long _arg0;
_arg0 = data.readLong();
java.util.List<org.chan.quicklife.entity.Recommend> _result = this.getRecommend(_arg0);
reply.writeNoException();
reply.writeTypedList(_result);
return true;
}
case TRANSACTION_addRecommend:
{
data.enforceInterface(DESCRIPTOR);
org.chan.quicklife.entity.Recommend _arg0;
if ((0!=data.readInt())) {
_arg0 = org.chan.quicklife.entity.Recommend.CREATOR.createFromParcel(data);
}
else {
_arg0 = null;
}
boolean _result = this.addRecommend(_arg0);
reply.writeNoException();
reply.writeInt(((_result)?(1):(0)));
return true;
}
case TRANSACTION_addFavorite:
{
data.enforceInterface(DESCRIPTOR);
org.chan.quicklife.entity.Favorite _arg0;
if ((0!=data.readInt())) {
_arg0 = org.chan.quicklife.entity.Favorite.CREATOR.createFromParcel(data);
}
else {
_arg0 = null;
}
boolean _result = this.addFavorite(_arg0);
reply.writeNoException();
reply.writeInt(((_result)?(1):(0)));
return true;
}
case TRANSACTION_getFavorites:
{
data.enforceInterface(DESCRIPTOR);
long _arg0;
_arg0 = data.readLong();
java.util.List<org.chan.quicklife.entity.Favorite> _result = this.getFavorites(_arg0);
reply.writeNoException();
reply.writeTypedList(_result);
return true;
}
case TRANSACTION_getCheckinCount:
{
data.enforceInterface(DESCRIPTOR);
long _arg0;
_arg0 = data.readLong();
int _result = this.getCheckinCount(_arg0);
reply.writeNoException();
reply.writeInt(_result);
return true;
}
case TRANSACTION_getCouponCount:
{
data.enforceInterface(DESCRIPTOR);
long _arg0;
_arg0 = data.readLong();
int _result = this.getCouponCount(_arg0);
reply.writeNoException();
reply.writeInt(_result);
return true;
}
case TRANSACTION_getFavoriteCount:
{
data.enforceInterface(DESCRIPTOR);
long _arg0;
_arg0 = data.readLong();
int _result = this.getFavoriteCount(_arg0);
reply.writeNoException();
reply.writeInt(_result);
return true;
}
case TRANSACTION_getShareCount:
{
data.enforceInterface(DESCRIPTOR);
long _arg0;
_arg0 = data.readLong();
int _result = this.getShareCount(_arg0);
reply.writeNoException();
reply.writeInt(_result);
return true;
}
case TRANSACTION_getFriendCount:
{
data.enforceInterface(DESCRIPTOR);
long _arg0;
_arg0 = data.readLong();
int _result = this.getFriendCount(_arg0);
reply.writeNoException();
reply.writeInt(_result);
return true;
}
case TRANSACTION_getCommentCount:
{
data.enforceInterface(DESCRIPTOR);
long _arg0;
_arg0 = data.readLong();
int _result = this.getCommentCount(_arg0);
reply.writeNoException();
reply.writeInt(_result);
return true;
}
}
return super.onTransact(code, data, reply, flags);
}
private static class Proxy implements org.chan.quicklife.service.aidl.IServer
{
private android.os.IBinder mRemote;
Proxy(android.os.IBinder remote)
{
mRemote = remote;
}
@Override public android.os.IBinder asBinder()
{
return mRemote;
}
public java.lang.String getInterfaceDescriptor()
{
return DESCRIPTOR;
}
@Override public java.util.List<org.chan.quicklife.entity.User> getAllUser() throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
java.util.List<org.chan.quicklife.entity.User> _result;
try {
_data.writeInterfaceToken(DESCRIPTOR);
mRemote.transact(Stub.TRANSACTION_getAllUser, _data, _reply, 0);
_reply.readException();
_result = _reply.createTypedArrayList(org.chan.quicklife.entity.User.CREATOR);
}
finally {
_reply.recycle();
_data.recycle();
}
return _result;
}
@Override public java.util.List<org.chan.quicklife.entity.User> getNotFriendUser(long id) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
java.util.List<org.chan.quicklife.entity.User> _result;
try {
_data.writeInterfaceToken(DESCRIPTOR);
_data.writeLong(id);
mRemote.transact(Stub.TRANSACTION_getNotFriendUser, _data, _reply, 0);
_reply.readException();
_result = _reply.createTypedArrayList(org.chan.quicklife.entity.User.CREATOR);
}
finally {
_reply.recycle();
_data.recycle();
}
return _result;
}
@Override public org.chan.quicklife.entity.User login(org.chan.quicklife.entity.User user) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
org.chan.quicklife.entity.User _result;
try {
_data.writeInterfaceToken(DESCRIPTOR);
if ((user!=null)) {
_data.writeInt(1);
user.writeToParcel(_data, 0);
}
else {
_data.writeInt(0);
}
mRemote.transact(Stub.TRANSACTION_login, _data, _reply, 0);
_reply.readException();
if ((0!=_reply.readInt())) {
_result = org.chan.quicklife.entity.User.CREATOR.createFromParcel(_reply);
}
else {
_result = null;
}
}
finally {
_reply.recycle();
_data.recycle();
}
return _result;
}
@Override public boolean register(org.chan.quicklife.entity.User user) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
boolean _result;
try {
_data.writeInterfaceToken(DESCRIPTOR);
if ((user!=null)) {
_data.writeInt(1);
user.writeToParcel(_data, 0);
}
else {
_data.writeInt(0);
}
mRemote.transact(Stub.TRANSACTION_register, _data, _reply, 0);
_reply.readException();
_result = (0!=_reply.readInt());
}
finally {
_reply.recycle();
_data.recycle();
}
return _result;
}
@Override public boolean location(org.chan.quicklife.entity.User user) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
boolean _result;
try {
_data.writeInterfaceToken(DESCRIPTOR);
if ((user!=null)) {
_data.writeInt(1);
user.writeToParcel(_data, 0);
}
else {
_data.writeInt(0);
}
mRemote.transact(Stub.TRANSACTION_location, _data, _reply, 0);
_reply.readException();
_result = (0!=_reply.readInt());
}
finally {
_reply.recycle();
_data.recycle();
}
return _result;
}
@Override public boolean modify(org.chan.quicklife.entity.User user) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
boolean _result;
try {
_data.writeInterfaceToken(DESCRIPTOR);
if ((user!=null)) {
_data.writeInt(1);
user.writeToParcel(_data, 0);
}
else {
_data.writeInt(0);
}
mRemote.transact(Stub.TRANSACTION_modify, _data, _reply, 0);
_reply.readException();
_result = (0!=_reply.readInt());
}
finally {
_reply.recycle();
_data.recycle();
}
return _result;
}
@Override public boolean canModify(long id, java.lang.String pwd) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
boolean _result;
try {
_data.writeInterfaceToken(DESCRIPTOR);
_data.writeLong(id);
_data.writeString(pwd);
mRemote.transact(Stub.TRANSACTION_canModify, _data, _reply, 0);
_reply.readException();
_result = (0!=_reply.readInt());
}
finally {
_reply.recycle();
_data.recycle();
}
return _result;
}
@Override public boolean canRegister(java.lang.String loginName) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
boolean _result;
try {
_data.writeInterfaceToken(DESCRIPTOR);
_data.writeString(loginName);
mRemote.transact(Stub.TRANSACTION_canRegister, _data, _reply, 0);
_reply.readException();
_result = (0!=_reply.readInt());
}
finally {
_reply.recycle();
_data.recycle();
}
return _result;
}
@Override public java.util.List<org.chan.quicklife.entity.Business> getBusinessesByLoc(double lat, double lon) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
java.util.List<org.chan.quicklife.entity.Business> _result;
try {
_data.writeInterfaceToken(DESCRIPTOR);
_data.writeDouble(lat);
_data.writeDouble(lon);
mRemote.transact(Stub.TRANSACTION_getBusinessesByLoc, _data, _reply, 0);
_reply.readException();
_result = _reply.createTypedArrayList(org.chan.quicklife.entity.Business.CREATOR);
}
finally {
_reply.recycle();
_data.recycle();
}
return _result;
}
@Override public java.util.List<org.chan.quicklife.entity.Business> getAllBusinesses() throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
java.util.List<org.chan.quicklife.entity.Business> _result;
try {
_data.writeInterfaceToken(DESCRIPTOR);
mRemote.transact(Stub.TRANSACTION_getAllBusinesses, _data, _reply, 0);
_reply.readException();
_result = _reply.createTypedArrayList(org.chan.quicklife.entity.Business.CREATOR);
}
finally {
_reply.recycle();
_data.recycle();
}
return _result;
}
@Override public org.chan.quicklife.entity.Business getBusiness(long id) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
org.chan.quicklife.entity.Business _result;
try {
_data.writeInterfaceToken(DESCRIPTOR);
_data.writeLong(id);
mRemote.transact(Stub.TRANSACTION_getBusiness, _data, _reply, 0);
_reply.readException();
if ((0!=_reply.readInt())) {
_result = org.chan.quicklife.entity.Business.CREATOR.createFromParcel(_reply);
}
else {
_result = null;
}
}
finally {
_reply.recycle();
_data.recycle();
}
return _result;
}
@Override public java.util.List<org.chan.quicklife.entity.Business> getBussByKeyname(java.lang.String key) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
java.util.List<org.chan.quicklife.entity.Business> _result;
try {
_data.writeInterfaceToken(DESCRIPTOR);
_data.writeString(key);
mRemote.transact(Stub.TRANSACTION_getBussByKeyname, _data, _reply, 0);
_reply.readException();
_result = _reply.createTypedArrayList(org.chan.quicklife.entity.Business.CREATOR);
}
finally {
_reply.recycle();
_data.recycle();
}
return _result;
}
@Override public java.util.List<org.chan.quicklife.entity.Coupon> getAllCoupons() throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
java.util.List<org.chan.quicklife.entity.Coupon> _result;
try {
_data.writeInterfaceToken(DESCRIPTOR);
mRemote.transact(Stub.TRANSACTION_getAllCoupons, _data, _reply, 0);
_reply.readException();
_result = _reply.createTypedArrayList(org.chan.quicklife.entity.Coupon.CREATOR);
}
finally {
_reply.recycle();
_data.recycle();
}
return _result;
}
@Override public java.util.List<org.chan.quicklife.entity.Coupon> getHotCoupons() throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
java.util.List<org.chan.quicklife.entity.Coupon> _result;
try {
_data.writeInterfaceToken(DESCRIPTOR);
mRemote.transact(Stub.TRANSACTION_getHotCoupons, _data, _reply, 0);
_reply.readException();
_result = _reply.createTypedArrayList(org.chan.quicklife.entity.Coupon.CREATOR);
}
finally {
_reply.recycle();
_data.recycle();
}
return _result;
}
@Override public org.chan.quicklife.entity.Coupon getCouponById(long couponId) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
org.chan.quicklife.entity.Coupon _result;
try {
_data.writeInterfaceToken(DESCRIPTOR);
_data.writeLong(couponId);
mRemote.transact(Stub.TRANSACTION_getCouponById, _data, _reply, 0);
_reply.readException();
if ((0!=_reply.readInt())) {
_result = org.chan.quicklife.entity.Coupon.CREATOR.createFromParcel(_reply);
}
else {
_result = null;
}
}
finally {
_reply.recycle();
_data.recycle();
}
return _result;
}
@Override public java.util.List<org.chan.quicklife.entity.Coupon> getCouponsByBuss(long bussId) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
java.util.List<org.chan.quicklife.entity.Coupon> _result;
try {
_data.writeInterfaceToken(DESCRIPTOR);
_data.writeLong(bussId);
mRemote.transact(Stub.TRANSACTION_getCouponsByBuss, _data, _reply, 0);
_reply.readException();
_result = _reply.createTypedArrayList(org.chan.quicklife.entity.Coupon.CREATOR);
}
finally {
_reply.recycle();
_data.recycle();
}
return _result;
}
@Override public boolean checkin(org.chan.quicklife.entity.ActionLog action) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
boolean _result;
try {
_data.writeInterfaceToken(DESCRIPTOR);
if ((action!=null)) {
_data.writeInt(1);
action.writeToParcel(_data, 0);
}
else {
_data.writeInt(0);
}
mRemote.transact(Stub.TRANSACTION_checkin, _data, _reply, 0);
_reply.readException();
_result = (0!=_reply.readInt());
}
finally {
_reply.recycle();
_data.recycle();
}
return _result;
}
@Override public boolean consume(org.chan.quicklife.entity.ActionLog action) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
boolean _result;
try {
_data.writeInterfaceToken(DESCRIPTOR);
if ((action!=null)) {
_data.writeInt(1);
action.writeToParcel(_data, 0);
}
else {
_data.writeInt(0);
}
mRemote.transact(Stub.TRANSACTION_consume, _data, _reply, 0);
_reply.readException();
_result = (0!=_reply.readInt());
}
finally {
_reply.recycle();
_data.recycle();
}
return _result;
}
@Override public boolean comment(org.chan.quicklife.entity.ActionLog action) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
boolean _result;
try {
_data.writeInterfaceToken(DESCRIPTOR);
if ((action!=null)) {
_data.writeInt(1);
action.writeToParcel(_data, 0);
}
else {
_data.writeInt(0);
}
mRemote.transact(Stub.TRANSACTION_comment, _data, _reply, 0);
_reply.readException();
_result = (0!=_reply.readInt());
}
finally {
_reply.recycle();
_data.recycle();
}
return _result;
}
@Override public java.util.List<org.chan.quicklife.entity.ActionLog> getAllAction() throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
java.util.List<org.chan.quicklife.entity.ActionLog> _result;
try {
_data.writeInterfaceToken(DESCRIPTOR);
mRemote.transact(Stub.TRANSACTION_getAllAction, _data, _reply, 0);
_reply.readException();
_result = _reply.createTypedArrayList(org.chan.quicklife.entity.ActionLog.CREATOR);
}
finally {
_reply.recycle();
_data.recycle();
}
return _result;
}
@Override public java.util.List<org.chan.quicklife.entity.ActionLog> getFriendAction(long userId) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
java.util.List<org.chan.quicklife.entity.ActionLog> _result;
try {
_data.writeInterfaceToken(DESCRIPTOR);
_data.writeLong(userId);
mRemote.transact(Stub.TRANSACTION_getFriendAction, _data, _reply, 0);
_reply.readException();
_result = _reply.createTypedArrayList(org.chan.quicklife.entity.ActionLog.CREATOR);
}
finally {
_reply.recycle();
_data.recycle();
}
return _result;
}
@Override public java.util.List<org.chan.quicklife.entity.ActionLog> getMyAction(long userId) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
java.util.List<org.chan.quicklife.entity.ActionLog> _result;
try {
_data.writeInterfaceToken(DESCRIPTOR);
_data.writeLong(userId);
mRemote.transact(Stub.TRANSACTION_getMyAction, _data, _reply, 0);
_reply.readException();
_result = _reply.createTypedArrayList(org.chan.quicklife.entity.ActionLog.CREATOR);
}
finally {
_reply.recycle();
_data.recycle();
}
return _result;
}
@Override public java.util.List<org.chan.quicklife.entity.ActionLog> getActionByBuss(long bussId, int acType) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
java.util.List<org.chan.quicklife.entity.ActionLog> _result;
try {
_data.writeInterfaceToken(DESCRIPTOR);
_data.writeLong(bussId);
_data.writeInt(acType);
mRemote.transact(Stub.TRANSACTION_getActionByBuss, _data, _reply, 0);
_reply.readException();
_result = _reply.createTypedArrayList(org.chan.quicklife.entity.ActionLog.CREATOR);
}
finally {
_reply.recycle();
_data.recycle();
}
return _result;
}
@Override public java.util.List<org.chan.quicklife.entity.ActionLog> getActionIds(long userId) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
java.util.List<org.chan.quicklife.entity.ActionLog> _result;
try {
_data.writeInterfaceToken(DESCRIPTOR);
_data.writeLong(userId);
mRemote.transact(Stub.TRANSACTION_getActionIds, _data, _reply, 0);
_reply.readException();
_result = _reply.createTypedArrayList(org.chan.quicklife.entity.ActionLog.CREATOR);
}
finally {
_reply.recycle();
_data.recycle();
}
return _result;
}
@Override public org.chan.quicklife.entity.ActionLog getActionById(long acId) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
org.chan.quicklife.entity.ActionLog _result;
try {
_data.writeInterfaceToken(DESCRIPTOR);
_data.writeLong(acId);
mRemote.transact(Stub.TRANSACTION_getActionById, _data, _reply, 0);
_reply.readException();
if ((0!=_reply.readInt())) {
_result = org.chan.quicklife.entity.ActionLog.CREATOR.createFromParcel(_reply);
}
else {
_result = null;
}
}
finally {
_reply.recycle();
_data.recycle();
}
return _result;
}
@Override public java.util.List<org.chan.quicklife.entity.Friend> getFriends(long myId) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
java.util.List<org.chan.quicklife.entity.Friend> _result;
try {
_data.writeInterfaceToken(DESCRIPTOR);
_data.writeLong(myId);
mRemote.transact(Stub.TRANSACTION_getFriends, _data, _reply, 0);
_reply.readException();
_result = _reply.createTypedArrayList(org.chan.quicklife.entity.Friend.CREATOR);
}
finally {
_reply.recycle();
_data.recycle();
}
return _result;
}
@Override public boolean addFriend(long myId, long friendId, int state) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
boolean _result;
try {
_data.writeInterfaceToken(DESCRIPTOR);
_data.writeLong(myId);
_data.writeLong(friendId);
_data.writeInt(state);
mRemote.transact(Stub.TRANSACTION_addFriend, _data, _reply, 0);
_reply.readException();
_result = (0!=_reply.readInt());
}
finally {
_reply.recycle();
_data.recycle();
}
return _result;
}
@Override public boolean echoFriend(long shipId, int isPass) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
boolean _result;
try {
_data.writeInterfaceToken(DESCRIPTOR);
_data.writeLong(shipId);
_data.writeInt(isPass);
mRemote.transact(Stub.TRANSACTION_echoFriend, _data, _reply, 0);
_reply.readException();
_result = (0!=_reply.readInt());
}
finally {
_reply.recycle();
_data.recycle();
}
return _result;
}
@Override public java.util.List<org.chan.quicklife.entity.Friend> getFriendApply(long id) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
java.util.List<org.chan.quicklife.entity.Friend> _result;
try {
_data.writeInterfaceToken(DESCRIPTOR);
_data.writeLong(id);
mRemote.transact(Stub.TRANSACTION_getFriendApply, _data, _reply, 0);
_reply.readException();
_result = _reply.createTypedArrayList(org.chan.quicklife.entity.Friend.CREATOR);
}
finally {
_reply.recycle();
_data.recycle();
}
return _result;
}
@Override public java.util.List<org.chan.quicklife.entity.Recommend> getRecommend(long userId) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
java.util.List<org.chan.quicklife.entity.Recommend> _result;
try {
_data.writeInterfaceToken(DESCRIPTOR);
_data.writeLong(userId);
mRemote.transact(Stub.TRANSACTION_getRecommend, _data, _reply, 0);
_reply.readException();
_result = _reply.createTypedArrayList(org.chan.quicklife.entity.Recommend.CREATOR);
}
finally {
_reply.recycle();
_data.recycle();
}
return _result;
}
@Override public boolean addRecommend(org.chan.quicklife.entity.Recommend rec) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
boolean _result;
try {
_data.writeInterfaceToken(DESCRIPTOR);
if ((rec!=null)) {
_data.writeInt(1);
rec.writeToParcel(_data, 0);
}
else {
_data.writeInt(0);
}
mRemote.transact(Stub.TRANSACTION_addRecommend, _data, _reply, 0);
_reply.readException();
_result = (0!=_reply.readInt());
}
finally {
_reply.recycle();
_data.recycle();
}
return _result;
}
@Override public boolean addFavorite(org.chan.quicklife.entity.Favorite favorite) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
boolean _result;
try {
_data.writeInterfaceToken(DESCRIPTOR);
if ((favorite!=null)) {
_data.writeInt(1);
favorite.writeToParcel(_data, 0);
}
else {
_data.writeInt(0);
}
mRemote.transact(Stub.TRANSACTION_addFavorite, _data, _reply, 0);
_reply.readException();
_result = (0!=_reply.readInt());
}
finally {
_reply.recycle();
_data.recycle();
}
return _result;
}
@Override public java.util.List<org.chan.quicklife.entity.Favorite> getFavorites(long userId) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
java.util.List<org.chan.quicklife.entity.Favorite> _result;
try {
_data.writeInterfaceToken(DESCRIPTOR);
_data.writeLong(userId);
mRemote.transact(Stub.TRANSACTION_getFavorites, _data, _reply, 0);
_reply.readException();
_result = _reply.createTypedArrayList(org.chan.quicklife.entity.Favorite.CREATOR);
}
finally {
_reply.recycle();
_data.recycle();
}
return _result;
}
@Override public int getCheckinCount(long userId) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
int _result;
try {
_data.writeInterfaceToken(DESCRIPTOR);
_data.writeLong(userId);
mRemote.transact(Stub.TRANSACTION_getCheckinCount, _data, _reply, 0);
_reply.readException();
_result = _reply.readInt();
}
finally {
_reply.recycle();
_data.recycle();
}
return _result;
}
@Override public int getCouponCount(long userId) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
int _result;
try {
_data.writeInterfaceToken(DESCRIPTOR);
_data.writeLong(userId);
mRemote.transact(Stub.TRANSACTION_getCouponCount, _data, _reply, 0);
_reply.readException();
_result = _reply.readInt();
}
finally {
_reply.recycle();
_data.recycle();
}
return _result;
}
@Override public int getFavoriteCount(long userId) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
int _result;
try {
_data.writeInterfaceToken(DESCRIPTOR);
_data.writeLong(userId);
mRemote.transact(Stub.TRANSACTION_getFavoriteCount, _data, _reply, 0);
_reply.readException();
_result = _reply.readInt();
}
finally {
_reply.recycle();
_data.recycle();
}
return _result;
}
@Override public int getShareCount(long userId) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
int _result;
try {
_data.writeInterfaceToken(DESCRIPTOR);
_data.writeLong(userId);
mRemote.transact(Stub.TRANSACTION_getShareCount, _data, _reply, 0);
_reply.readException();
_result = _reply.readInt();
}
finally {
_reply.recycle();
_data.recycle();
}
return _result;
}
@Override public int getFriendCount(long userId) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
int _result;
try {
_data.writeInterfaceToken(DESCRIPTOR);
_data.writeLong(userId);
mRemote.transact(Stub.TRANSACTION_getFriendCount, _data, _reply, 0);
_reply.readException();
_result = _reply.readInt();
}
finally {
_reply.recycle();
_data.recycle();
}
return _result;
}
@Override public int getCommentCount(long userId) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
int _result;
try {
_data.writeInterfaceToken(DESCRIPTOR);
_data.writeLong(userId);
mRemote.transact(Stub.TRANSACTION_getCommentCount, _data, _reply, 0);
_reply.readException();
_result = _reply.readInt();
}
finally {
_reply.recycle();
_data.recycle();
}
return _result;
}
}
static final int TRANSACTION_getAllUser = (android.os.IBinder.FIRST_CALL_TRANSACTION + 0);
static final int TRANSACTION_getNotFriendUser = (android.os.IBinder.FIRST_CALL_TRANSACTION + 1);
static final int TRANSACTION_login = (android.os.IBinder.FIRST_CALL_TRANSACTION + 2);
static final int TRANSACTION_register = (android.os.IBinder.FIRST_CALL_TRANSACTION + 3);
static final int TRANSACTION_location = (android.os.IBinder.FIRST_CALL_TRANSACTION + 4);
static final int TRANSACTION_modify = (android.os.IBinder.FIRST_CALL_TRANSACTION + 5);
static final int TRANSACTION_canModify = (android.os.IBinder.FIRST_CALL_TRANSACTION + 6);
static final int TRANSACTION_canRegister = (android.os.IBinder.FIRST_CALL_TRANSACTION + 7);
static final int TRANSACTION_getBusinessesByLoc = (android.os.IBinder.FIRST_CALL_TRANSACTION + 8);
static final int TRANSACTION_getAllBusinesses = (android.os.IBinder.FIRST_CALL_TRANSACTION + 9);
static final int TRANSACTION_getBusiness = (android.os.IBinder.FIRST_CALL_TRANSACTION + 10);
static final int TRANSACTION_getBussByKeyname = (android.os.IBinder.FIRST_CALL_TRANSACTION + 11);
static final int TRANSACTION_getAllCoupons = (android.os.IBinder.FIRST_CALL_TRANSACTION + 12);
static final int TRANSACTION_getHotCoupons = (android.os.IBinder.FIRST_CALL_TRANSACTION + 13);
static final int TRANSACTION_getCouponById = (android.os.IBinder.FIRST_CALL_TRANSACTION + 14);
static final int TRANSACTION_getCouponsByBuss = (android.os.IBinder.FIRST_CALL_TRANSACTION + 15);
static final int TRANSACTION_checkin = (android.os.IBinder.FIRST_CALL_TRANSACTION + 16);
static final int TRANSACTION_consume = (android.os.IBinder.FIRST_CALL_TRANSACTION + 17);
static final int TRANSACTION_comment = (android.os.IBinder.FIRST_CALL_TRANSACTION + 18);
static final int TRANSACTION_getAllAction = (android.os.IBinder.FIRST_CALL_TRANSACTION + 19);
static final int TRANSACTION_getFriendAction = (android.os.IBinder.FIRST_CALL_TRANSACTION + 20);
static final int TRANSACTION_getMyAction = (android.os.IBinder.FIRST_CALL_TRANSACTION + 21);
static final int TRANSACTION_getActionByBuss = (android.os.IBinder.FIRST_CALL_TRANSACTION + 22);
static final int TRANSACTION_getActionIds = (android.os.IBinder.FIRST_CALL_TRANSACTION + 23);
static final int TRANSACTION_getActionById = (android.os.IBinder.FIRST_CALL_TRANSACTION + 24);
static final int TRANSACTION_getFriends = (android.os.IBinder.FIRST_CALL_TRANSACTION + 25);
static final int TRANSACTION_addFriend = (android.os.IBinder.FIRST_CALL_TRANSACTION + 26);
static final int TRANSACTION_echoFriend = (android.os.IBinder.FIRST_CALL_TRANSACTION + 27);
static final int TRANSACTION_getFriendApply = (android.os.IBinder.FIRST_CALL_TRANSACTION + 28);
static final int TRANSACTION_getRecommend = (android.os.IBinder.FIRST_CALL_TRANSACTION + 29);
static final int TRANSACTION_addRecommend = (android.os.IBinder.FIRST_CALL_TRANSACTION + 30);
static final int TRANSACTION_addFavorite = (android.os.IBinder.FIRST_CALL_TRANSACTION + 31);
static final int TRANSACTION_getFavorites = (android.os.IBinder.FIRST_CALL_TRANSACTION + 32);
static final int TRANSACTION_getCheckinCount = (android.os.IBinder.FIRST_CALL_TRANSACTION + 33);
static final int TRANSACTION_getCouponCount = (android.os.IBinder.FIRST_CALL_TRANSACTION + 34);
static final int TRANSACTION_getFavoriteCount = (android.os.IBinder.FIRST_CALL_TRANSACTION + 35);
static final int TRANSACTION_getShareCount = (android.os.IBinder.FIRST_CALL_TRANSACTION + 36);
static final int TRANSACTION_getFriendCount = (android.os.IBinder.FIRST_CALL_TRANSACTION + 37);
static final int TRANSACTION_getCommentCount = (android.os.IBinder.FIRST_CALL_TRANSACTION + 38);
}
public java.util.List<org.chan.quicklife.entity.User> getAllUser() throws android.os.RemoteException;
public java.util.List<org.chan.quicklife.entity.User> getNotFriendUser(long id) throws android.os.RemoteException;
public org.chan.quicklife.entity.User login(org.chan.quicklife.entity.User user) throws android.os.RemoteException;
public boolean register(org.chan.quicklife.entity.User user) throws android.os.RemoteException;
public boolean location(org.chan.quicklife.entity.User user) throws android.os.RemoteException;
public boolean modify(org.chan.quicklife.entity.User user) throws android.os.RemoteException;
public boolean canModify(long id, java.lang.String pwd) throws android.os.RemoteException;
public boolean canRegister(java.lang.String loginName) throws android.os.RemoteException;
public java.util.List<org.chan.quicklife.entity.Business> getBusinessesByLoc(double lat, double lon) throws android.os.RemoteException;
public java.util.List<org.chan.quicklife.entity.Business> getAllBusinesses() throws android.os.RemoteException;
public org.chan.quicklife.entity.Business getBusiness(long id) throws android.os.RemoteException;
public java.util.List<org.chan.quicklife.entity.Business> getBussByKeyname(java.lang.String key) throws android.os.RemoteException;
public java.util.List<org.chan.quicklife.entity.Coupon> getAllCoupons() throws android.os.RemoteException;
public java.util.List<org.chan.quicklife.entity.Coupon> getHotCoupons() throws android.os.RemoteException;
public org.chan.quicklife.entity.Coupon getCouponById(long couponId) throws android.os.RemoteException;
public java.util.List<org.chan.quicklife.entity.Coupon> getCouponsByBuss(long bussId) throws android.os.RemoteException;
public boolean checkin(org.chan.quicklife.entity.ActionLog action) throws android.os.RemoteException;
public boolean consume(org.chan.quicklife.entity.ActionLog action) throws android.os.RemoteException;
public boolean comment(org.chan.quicklife.entity.ActionLog action) throws android.os.RemoteException;
public java.util.List<org.chan.quicklife.entity.ActionLog> getAllAction() throws android.os.RemoteException;
public java.util.List<org.chan.quicklife.entity.ActionLog> getFriendAction(long userId) throws android.os.RemoteException;
public java.util.List<org.chan.quicklife.entity.ActionLog> getMyAction(long userId) throws android.os.RemoteException;
public java.util.List<org.chan.quicklife.entity.ActionLog> getActionByBuss(long bussId, int acType) throws android.os.RemoteException;
public java.util.List<org.chan.quicklife.entity.ActionLog> getActionIds(long userId) throws android.os.RemoteException;
public org.chan.quicklife.entity.ActionLog getActionById(long acId) throws android.os.RemoteException;
public java.util.List<org.chan.quicklife.entity.Friend> getFriends(long myId) throws android.os.RemoteException;
public boolean addFriend(long myId, long friendId, int state) throws android.os.RemoteException;
public boolean echoFriend(long shipId, int isPass) throws android.os.RemoteException;
public java.util.List<org.chan.quicklife.entity.Friend> getFriendApply(long id) throws android.os.RemoteException;
public java.util.List<org.chan.quicklife.entity.Recommend> getRecommend(long userId) throws android.os.RemoteException;
public boolean addRecommend(org.chan.quicklife.entity.Recommend rec) throws android.os.RemoteException;
public boolean addFavorite(org.chan.quicklife.entity.Favorite favorite) throws android.os.RemoteException;
public java.util.List<org.chan.quicklife.entity.Favorite> getFavorites(long userId) throws android.os.RemoteException;
public int getCheckinCount(long userId) throws android.os.RemoteException;
public int getCouponCount(long userId) throws android.os.RemoteException;
public int getFavoriteCount(long userId) throws android.os.RemoteException;
public int getShareCount(long userId) throws android.os.RemoteException;
public int getFriendCount(long userId) throws android.os.RemoteException;
public int getCommentCount(long userId) throws android.os.RemoteException;
}
