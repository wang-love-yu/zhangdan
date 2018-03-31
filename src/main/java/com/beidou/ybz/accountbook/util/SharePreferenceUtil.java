package com.beidou.ybz.accountbook.util;

import android.content.Context;
import android.content.SharedPreferences;

public class SharePreferenceUtil {
    private SharedPreferences sp;
    private SharedPreferences.Editor editor;

    public SharePreferenceUtil(Context context, String file) {
        sp = context.getApplicationContext().getSharedPreferences(file, Context.MODE_PRIVATE);
        editor = sp.edit();
    }

    public void clear() {
        if (editor != null) {
            editor.clear();
            editor.commit();
        }
    }

    public void setShortUrl(String shortUrl) {
        editor.putString("shortUrl", shortUrl);
        editor.commit();
    }

    public String getShortUrl() {
        return sp.getString("shortUrl", "");
    }

    public void setSecretKey(String secretKey) {
        editor.putString("secretKey", secretKey);
        editor.commit();
    }

    public String getSecretKey() {
        return sp.getString("secretKey", "");
    }

    public void setSecretIv(String secretIv) {
        editor.putString("secretIv", secretIv);
        editor.commit();
    }

    public String getSecretIv() {
        return sp.getString("secretIv", "");
    }

    public void setSecretKeyId(String secretKeyId) {
        editor.putString("secretKeyId", secretKeyId);
        editor.commit();
    }

    public String getSecretKeyId() {
        return sp.getString("secretKeyId", "");
    }


    public void setName(String name) {
        editor.putString("name", name);
        editor.commit();
    }

    public String getName() {
        return sp.getString("name", "");
    }


    public void setPayPasswd(String paypasswd) {
        editor.putString("paypasswd", paypasswd);
        editor.commit();
    }

    public String getPayPasswd() {
        return sp.getString("paypasswd", "");
    }


    // version
    public void setVersion(String version) {
        editor.putString("version", version);
        editor.commit();
    }

    public String getVersion() {
        return sp.getString("version", "");
    }


    // user_id
    public void setPosition(int position) {
        editor.putInt("position", position);
        editor.commit();
    }

    public int getPosition() {
        return sp.getInt("position", -1);
    }

    public void setPhone(String phone) {
        editor.putString("phone", phone);
        editor.commit();
    }

    public String getPhone() {
        return sp.getString("phone", "");
    }

    //-----手机号带*
    public void setHidePhone(String phone) {
        editor.putString("allphone", phone);
        editor.commit();
    }

    public String getHidePhone() {
        return sp.getString("allphone", "");
    }


    //isNewUser
    public void setIsNewUser(boolean isNewUser) {
        editor.putBoolean("isNewUser", isNewUser);
        editor.commit();
    }

    public boolean getIsNewUser() {
        return sp.getBoolean("isNewUser", true);
    }

    //IsGesture
    public void setIsGesture(boolean isGesture) {
        editor.putBoolean("IsGesture", isGesture);
        editor.commit();
    }

    public boolean getIsGesture() {
        return sp.getBoolean("IsGesture", false);
    }


    //用户是否添加过资产
    public void setIsAddAssets(boolean isAddAssets) {
        editor.putBoolean("isAddAssets", isAddAssets);
        editor.commit();
    }

    public boolean getIsAddAssets() {
        return sp.getBoolean("isAddAssets", false);
    }

    //gesture
    public void setGesture(String gesture) {
        editor.putString("gesture", gesture);
        editor.commit();
    }

    public String getGesture() {
        return sp.getString("gesture", "");
    }


    //人脉邀请
    public void setRenmaiInvite(boolean renmaiinvite) {
        editor.putBoolean("renmaiinvite", renmaiinvite);
        editor.commit();
    }

    public boolean getRenmaiInvite() {
        return sp.getBoolean("renmaiinvite", false);
    }

    // avatar
    public void setAvatar(String avatar) {
        editor.putString("avatar", avatar);
        editor.commit();
    }

    public String getAvatar() {
        return sp.getString("avatar", "");
    }

    //===============================
    // avatar
    public void setPath(String path) {
        editor.putString("path", path);
        editor.commit();
    }

    public String getPath() {
        return sp.getString("path", "");
    }

    // user_id
    public void setUserId(String userId) {
        editor.putString("userId", userId);
        editor.commit();
    }

    public String getUserId() {
//        return "10051583";
        return sp.getString("userId", "");
    }


    // islogin
    public void setIsLogin(boolean islogin) {
        editor.putBoolean("islogin", islogin);
        editor.commit();
    }

    public boolean getIsLogin() {
        return sp.getBoolean("islogin", false);
    }

    //被保险人
    public void setBeibao(String IDnumber, String name, String id) {
        editor.putString("Beibao_IDnumber", IDnumber);
        editor.putString("Beibao_name", name);
        editor.putString("Beibao_id", id);
        editor.commit();
    }

    public String getBeibao(String key) {
        return sp.getString(key, "");
    }

    //投保人
    public void setToubao(String IDnumber, String name, String id) {
        editor.putString("Toubao_IDnumber", IDnumber);
        editor.putString("Toubao_name", name);
        editor.putString("Toubao_id", id);
        editor.commit();
    }

    public String getToubao(String key) {
        return sp.getString(key, "");
    }

    //收益人
    public void setShouyi(String IDnumber, String name, String id) {
        editor.putString("Shouyi_IDnumber", IDnumber);
        editor.putString("Shouyi_name", name);
        editor.putString("Shouyi_id", id);
        editor.commit();
    }

    public String getShouyi(String key) {
        return sp.getString(key, "");
    }

    /**
     * 保存邮箱
     */

    public void setEmail(String email) {
        editor.putString("email", email);
        editor.commit();
    }

    public String getEmail() {
        return sp.getString("email", "");
    }

    /**
     * 图形验证码弹出标志
     */
//    public void setCodeFlag(String codeFlag) {
//        editor.putString("codeFlag", codeFlag);
//        editor.commit();
//    }
//
//    public String getcodeFlag() {
//        return sp.getString("codeFlag","");
//    }

    /**
     * 用户昵称
     */
    public void setNickName(String nickName) {
        editor.putString("nickName", nickName);
        editor.commit();
    }

    public String getNickName() {
        return sp.getString("nickName", "");
    }

    /**
     * 用户头像
     */
    public void setPortraitUrl(String portraitUrl) {
        editor.putString("portraitUrl", portraitUrl);
        editor.commit();
    }

    public String getPortraitUrl() {
        return sp.getString("portraitUrl", "");
    }


    /**
     * 是否开启了黑匣子 false未开启 true开启
     */

    public void setOpenblackBox(boolean openblackBox) {
        editor.putBoolean("openblackBox", openblackBox);
        editor.commit();
    }

    public boolean getOpenblackBox() {
        return sp.getBoolean("openblackBox", false);
    }

    /**
     * 三方账号类型，三方登录完成设置上acctChannel
     */
    public void setAcctChannel(String acctChannel) {
        editor.putString("acctChannel", acctChannel);
        editor.commit();
    }

    public String getAcctChannel() {
        return sp.getString("acctChannel", "");
    }

    /**
     * 三方账号的OpenId
     */

    public void setThirdAcctNo(String thirdAcctNo) {
        editor.putString("thirdAcctNo", thirdAcctNo);
        editor.commit();
    }

    public String getThirdAcctNo() {
        return sp.getString("thirdAcctNo", "");
    }


    /**
     * 账号登录绑定微信
     *
     * @param accountbindWechat
     */
    public void setAccountbindWechat(String accountbindWechat) {
        editor.putString("accountbindWechat", accountbindWechat);
        editor.commit();
    }

    public String getAccountbindWechat() {
        return sp.getString("accountbindWechat", "");
    }

    /**
     * 最近一次点击我的页面消息中心的时间，每次点击更新
     *
     * @param messageTime
     */
    public void setMessageTime(String messageTime) {
        editor.putString("messageTime", messageTime);
        editor.commit();
    }

    public String getMessageTime() {
        return sp.getString("messageTime", "");
    }

    /**
     * 同步的通讯录联系人
     *
     * @param contactJson
     */
    public void setContactJson(String contactJson) {
        editor.putString("contactJson", contactJson);
        editor.commit();
    }

    public String getcontactJson() {
        return sp.getString("contactJson", "");
    }

    /**
     * 是否从手势密码验证页跳到登录
     *
     * @param isFromGes
     */
    public void setIsFromGes(boolean isFromGes) {
        editor.putBoolean("isFromGes", isFromGes);
        editor.commit();
    }

    public boolean getIsFromGes() {
        return sp.getBoolean("isFromGes", false);
    }


    /**
     * 是否保存了联系人数据
     *
     * @param isSave
     */
    public void setIsSave(boolean isSave) {
        editor.putBoolean("isSave", isSave);
        editor.commit();
    }

    public boolean getIsSave() {
        return sp.getBoolean("isSave", false);
    }

    /**
     * deviceToken
     * @param deviceToken
     */
    public void setDeviceToken(String deviceToken) {
        editor.putString("deviceToken", deviceToken);
        editor.commit();
    }

    public String getdeviceToken() {
        return sp.getString("deviceToken", "");
    }
}
