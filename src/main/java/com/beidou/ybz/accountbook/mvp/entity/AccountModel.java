package com.beidou.ybz.accountbook.mvp.entity;

/**
 * Author: ${Supreme} on 2017/12/12
 * QQ:977594142
 * E-mail:977594142@qq.com
 * module:账户首页接口Model
 */

public class AccountModel {

    /**
     * body : {"email":"977594142@qq.com","hideMobile":"155****8936","mobile":"15555928936","nickName":"360财富","portraitUrl":"images/10049975/51512714109924.jpg","qqFlag":"1","qqName":"360财富","realName":"","wechatFlag":"1","wechatName":"Supreme"}
     * header : {"code":"0000","desc":"成功","responseTime":"2018-03-02 16:50:31"}
     */

    private BodyBean body;
    private HeaderBean header;

    public BodyBean getBody() {
        return body;
    }

    public void setBody(BodyBean body) {
        this.body = body;
    }

    public HeaderBean getHeader() {
        return header;
    }

    public void setHeader(HeaderBean header) {
        this.header = header;
    }

    public static class BodyBean {
        /**
         * email : 977594142@qq.com
         * hideMobile : 155****8936
         * mobile : 15555928936
         * nickName : 360财富
         * portraitUrl : images/10049975/51512714109924.jpg
         * qqFlag : 1
         * qqName : 360财富
         * realName :
         * wechatFlag : 1
         * wechatName : Supreme
         */

        private String email;
        private String hideMobile;
        private String mobile;
        private String nickName;
        private String portraitUrl;
        private String qqFlag;
        private String qqName;
        private String realName;
        private String idNo;

        public String getIdNo() {
            return idNo;
        }

        public void setIdNo(String idNo) {
            this.idNo = idNo;
        }

        private String wechatFlag;
        private String wechatName;

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getHideMobile() {
            return hideMobile;
        }

        public void setHideMobile(String hideMobile) {
            this.hideMobile = hideMobile;
        }

        public String getMobile() {
            return mobile;
        }

        public void setMobile(String mobile) {
            this.mobile = mobile;
        }

        public String getNickName() {
            return nickName;
        }

        public void setNickName(String nickName) {
            this.nickName = nickName;
        }

        public String getPortraitUrl() {
            return portraitUrl;
        }

        public void setPortraitUrl(String portraitUrl) {
            this.portraitUrl = portraitUrl;
        }

        public String getQqFlag() {
            return qqFlag;
        }

        public void setQqFlag(String qqFlag) {
            this.qqFlag = qqFlag;
        }

        public String getQqName() {
            return qqName;
        }

        public void setQqName(String qqName) {
            this.qqName = qqName;
        }

        public String getRealName() {
            return realName;
        }

        public void setRealName(String realName) {
            this.realName = realName;
        }

        public String getWechatFlag() {
            return wechatFlag;
        }

        public void setWechatFlag(String wechatFlag) {
            this.wechatFlag = wechatFlag;
        }

        public String getWechatName() {
            return wechatName;
        }

        public void setWechatName(String wechatName) {
            this.wechatName = wechatName;
        }
    }

    public static class HeaderBean {
        /**
         * code : 0000
         * desc : 成功
         * responseTime : 2018-03-02 16:50:31
         */

        private String code;
        private String desc;
        private String responseTime;

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }

        public String getDesc() {
            return desc;
        }

        public void setDesc(String desc) {
            this.desc = desc;
        }

        public String getResponseTime() {
            return responseTime;
        }

        public void setResponseTime(String responseTime) {
            this.responseTime = responseTime;
        }
    }
}
