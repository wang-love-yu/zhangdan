package com.beidou.ybz.accountbook.mvp.entity;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

/**
 * Author: xu.yang on 2017/12/10
 * QQ:754444814
 * E-mail:754444814@qq.com
 * module:徽章大类model
 */
public class HuizhangBigClassModel implements Parcelable {

    /**
     * body : {"badgeTypeList":[{"badgeSum":8,"badgeType":3,"badgeTypeName":"天天向上","getBadgeNum":0,"growthValue":0},{"badgeSum":18,"badgeType":2,"badgeTypeName":"金钥匙","getBadgeNum":0,"growthValue":0},{"badgeSum":1,"badgeType":4,"badgeTypeName":"精彩纷呈","getBadgeNum":0,"growthValue":0},{"badgeSum":6,"badgeType":1,"badgeTypeName":"特别行动","getBadgeNum":0,"growthValue":0}]}
     * header : {"code":"0000","desc":"成功","responseTime":"2018-01-10 15:33:41"}
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

    public static class BodyBean implements Parcelable {
        private List<BadgeTypeListBean> badgeTypeList;

        public List<BadgeTypeListBean> getBadgeTypeList() {
            return badgeTypeList;
        }

        public void setBadgeTypeList(List<BadgeTypeListBean> badgeTypeList) {
            this.badgeTypeList = badgeTypeList;
        }

        public static class BadgeTypeListBean implements Parcelable {
            /**
             * badgeSum : 8
             * badgeType : 3
             * badgeTypeName : 天天向上
             * getBadgeNum : 0
             * growthValue : 0
             */

            private String badgeSum;
            private String badgeType;
            private String badgeTypeName;
            private String getBadgeNum;
            private String growthValue;

            public String getBadgeSum() {
                return badgeSum;
            }

            public void setBadgeSum(String badgeSum) {
                this.badgeSum = badgeSum;
            }

            public String getBadgeType() {
                return badgeType;
            }

            public void setBadgeType(String badgeType) {
                this.badgeType = badgeType;
            }

            public String getBadgeTypeName() {
                return badgeTypeName;
            }

            public void setBadgeTypeName(String badgeTypeName) {
                this.badgeTypeName = badgeTypeName;
            }

            public String getGetBadgeNum() {
                return getBadgeNum;
            }

            public void setGetBadgeNum(String getBadgeNum) {
                this.getBadgeNum = getBadgeNum;
            }

            public String getGrowthValue() {
                return growthValue;
            }

            public void setGrowthValue(String growthValue) {
                this.growthValue = growthValue;
            }

            @Override
            public int describeContents() {
                return 0;
            }

            @Override
            public void writeToParcel(Parcel dest, int flags) {
                dest.writeString(this.badgeSum);
                dest.writeString(this.badgeType);
                dest.writeString(this.badgeTypeName);
                dest.writeString(this.getBadgeNum);
                dest.writeString(this.growthValue);
            }

            public BadgeTypeListBean() {
            }

            protected BadgeTypeListBean(Parcel in) {
                this.badgeSum = in.readString();
                this.badgeType = in.readString();
                this.badgeTypeName = in.readString();
                this.getBadgeNum = in.readString();
                this.growthValue = in.readString();
            }

            public static final Creator<BadgeTypeListBean> CREATOR = new Creator<BadgeTypeListBean>() {
                @Override
                public BadgeTypeListBean createFromParcel(Parcel source) {
                    return new BadgeTypeListBean(source);
                }

                @Override
                public BadgeTypeListBean[] newArray(int size) {
                    return new BadgeTypeListBean[size];
                }
            };
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeList(this.badgeTypeList);
        }

        public BodyBean() {
        }

        protected BodyBean(Parcel in) {
            this.badgeTypeList = new ArrayList<BadgeTypeListBean>();
            in.readList(this.badgeTypeList, BadgeTypeListBean.class.getClassLoader());
        }

        public static final Creator<BodyBean> CREATOR = new Creator<BodyBean>() {
            @Override
            public BodyBean createFromParcel(Parcel source) {
                return new BodyBean(source);
            }

            @Override
            public BodyBean[] newArray(int size) {
                return new BodyBean[size];
            }
        };
    }

    public static class HeaderBean implements Parcelable {
        /**
         * code : 0000
         * desc : 成功
         * responseTime : 2018-01-10 15:33:41
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

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(this.code);
            dest.writeString(this.desc);
            dest.writeString(this.responseTime);
        }

        public HeaderBean() {
        }

        protected HeaderBean(Parcel in) {
            this.code = in.readString();
            this.desc = in.readString();
            this.responseTime = in.readString();
        }

        public static final Creator<HeaderBean> CREATOR = new Creator<HeaderBean>() {
            @Override
            public HeaderBean createFromParcel(Parcel source) {
                return new HeaderBean(source);
            }

            @Override
            public HeaderBean[] newArray(int size) {
                return new HeaderBean[size];
            }
        };
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeParcelable(this.body, flags);
        dest.writeParcelable(this.header, flags);
    }

    public HuizhangBigClassModel() {
    }

    protected HuizhangBigClassModel(Parcel in) {
        this.body = in.readParcelable(BodyBean.class.getClassLoader());
        this.header = in.readParcelable(HeaderBean.class.getClassLoader());
    }

    public static final Parcelable.Creator<HuizhangBigClassModel> CREATOR = new Parcelable.Creator<HuizhangBigClassModel>() {
        @Override
        public HuizhangBigClassModel createFromParcel(Parcel source) {
            return new HuizhangBigClassModel(source);
        }

        @Override
        public HuizhangBigClassModel[] newArray(int size) {
            return new HuizhangBigClassModel[size];
        }
    };
}
