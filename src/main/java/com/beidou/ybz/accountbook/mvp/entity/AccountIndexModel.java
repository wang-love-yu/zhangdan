package com.beidou.ybz.accountbook.mvp.entity;

import java.util.List;

/**
 * Author: xu.yang on 2017/12/10
 * QQ:754444814
 * E-mail:754444814@qq.com
 * module:记账首页响应model
 */
public class AccountIndexModel {

    /**
     * body : {"fieldViewAmountList":[{"amount":0,"nameValue":"0012","nameView":"海外房产","syrs":"200"},{"amount":0,"nameValue":"","nameView":"","syrs":""},{"amount":0,"nameValue":"","nameView":"","syrs":""}],"fieldViewdelList":[{"amount":0,"nameValue":"0012","nameView":"海外房产","syrs":"200"},{"amount":0,"nameValue":"","nameView":"","syrs":""},{"amount":0,"nameValue":"","nameView":"","syrs":""}],"reProList":[{"productName":"upload/cms/product/image/7c14ef3ac1574009895610244015256a.png","productUrl":""}],"userDayAssetJzc":0,"userDayAssetZqk":0,"userDayAssetZzc":0}
     * header : {"code":"0000","desc":"成功","responseTime":"2017-12-12 21:13:52"}
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
         * fieldViewAmountList : [{"amount":0,"nameValue":"0012","nameView":"海外房产","syrs":"200"},{"amount":0,"nameValue":"","nameView":"","syrs":""},{"amount":0,"nameValue":"","nameView":"","syrs":""}]
         * fieldViewdelList : [{"amount":0,"nameValue":"0012","nameView":"海外房产","syrs":"200"},{"amount":0,"nameValue":"","nameView":"","syrs":""},{"amount":0,"nameValue":"","nameView":"","syrs":""}]
         * reProList : [{"productName":"upload/cms/product/image/7c14ef3ac1574009895610244015256a.png","productUrl":""}]
         * userDayAssetJzc : 0
         * userDayAssetZqk : 0
         * userDayAssetZzc : 0
         */

        private String userDayAssetJzc;
        private String userDayAssetZqk;
        private String userDayAssetZzc;
        private List<FieldViewAmountListBean> fieldViewAmountList;
        private List<FieldViewdelListBean> fieldViewdelList;
        private List<ReProListBean> reProList;
        private String nh7r;

        public String getNh7r() {
            return nh7r;
        }

        public void setNh7r(String nh7r) {
            this.nh7r = nh7r;
        }

        public String getUserDayAssetJzc() {
            return userDayAssetJzc;
        }

        public void setUserDayAssetJzc(String userDayAssetJzc) {
            this.userDayAssetJzc = userDayAssetJzc;
        }

        public String getUserDayAssetZqk() {
            return userDayAssetZqk;
        }

        public void setUserDayAssetZqk(String userDayAssetZqk) {
            this.userDayAssetZqk = userDayAssetZqk;
        }

        public String getUserDayAssetZzc() {
            return userDayAssetZzc;
        }

        public void setUserDayAssetZzc(String userDayAssetZzc) {
            this.userDayAssetZzc = userDayAssetZzc;
        }

        public List<FieldViewAmountListBean> getFieldViewAmountList() {
            return fieldViewAmountList;
        }

        public void setFieldViewAmountList(List<FieldViewAmountListBean> fieldViewAmountList) {
            this.fieldViewAmountList = fieldViewAmountList;
        }

        public List<FieldViewdelListBean> getFieldViewdelList() {
            return fieldViewdelList;
        }

        public void setFieldViewdelList(List<FieldViewdelListBean> fieldViewdelList) {
            this.fieldViewdelList = fieldViewdelList;
        }

        public List<ReProListBean> getReProList() {
            return reProList;
        }

        public void setReProList(List<ReProListBean> reProList) {
            this.reProList = reProList;
        }

        public static class FieldViewAmountListBean {
            /**
             * amount : 0
             * nameValue : 0012
             * nameView : 海外房产
             * syrs : 200
             */

            private String amount;
            private String nameValue;
            private String nameView;
            private String hwfcFlag;

            public String getHwfcFlag() {
                return hwfcFlag;
            }

            public void setHwfcFlag(String hwfcFlag) {
                this.hwfcFlag = hwfcFlag;
            }

            private String syrs;
            private String bblx;

            public String getBblx() {
                return bblx;
            }

            public void setBblx(String bblx) {
                this.bblx = bblx;
            }

            public String getAmount() {
                return amount;
            }

            public void setAmount(String amount) {
                this.amount = amount;
            }

            public String getNameValue() {
                return nameValue;
            }

            public void setNameValue(String nameValue) {
                this.nameValue = nameValue;
            }

            public String getNameView() {
                return nameView;
            }

            public void setNameView(String nameView) {
                this.nameView = nameView;
            }

            public String getSyrs() {
                return syrs;
            }

            public void setSyrs(String syrs) {
                this.syrs = syrs;
            }
        }

        public static class FieldViewdelListBean {
            /**
             * amount : 0
             * nameValue : 0012
             * nameView : 海外房产
             * syrs : 200
             */

            private String amount;
            private String nameValue;
            private String nameView;
            private String syrs;
            private String bblx;

            public String getBblx() {
                return bblx;
            }

            public void setBblx(String bblx) {
                this.bblx = bblx;
            }

            public String getAmount() {
                return amount;
            }

            public void setAmount(String amount) {
                this.amount = amount;
            }

            public String getNameValue() {
                return nameValue;
            }

            public void setNameValue(String nameValue) {
                this.nameValue = nameValue;
            }

            public String getNameView() {
                return nameView;
            }

            public void setNameView(String nameView) {
                this.nameView = nameView;
            }

            public String getSyrs() {
                return syrs;
            }

            public void setSyrs(String syrs) {
                this.syrs = syrs;
            }
        }

        public static class ReProListBean {
            /**
             * productName : upload/cms/product/image/7c14ef3ac1574009895610244015256a.png
             * productUrl :
             */

            private String productName;
            private String productUrl;
            private String imageUrl;


            public String getImageUrl() {
                return imageUrl;
            }

            public void setImageUrl(String imageUrl) {
                this.imageUrl = imageUrl;
            }


            public String getProductName() {
                return productName;
            }

            public void setProductName(String productName) {
                this.productName = productName;
            }

            public String getProductUrl() {
                return productUrl;
            }

            public void setProductUrl(String productUrl) {
                this.productUrl = productUrl;
            }
        }
    }

    public static class HeaderBean {
        /**
         * code : 0000
         * desc : 成功
         * responseTime : 2017-12-12 21:13:52
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
