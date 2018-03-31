package com.beidou.ybz.accountbook.mvp.entity;

import java.util.List;

/**
 * Author: xu.yang on 2017/12/10
 * QQ:754444814
 * E-mail:754444814@qq.com
 * module:海外房产详情model
 */
public class OverseasDetailModel {

    /**
     * body : {"overseas":{"address":"马来西亚","amount":1500000,"amountRmb":0,"area":"150","assetId":"0012","createTime":{"date":11,"day":1,"hours":18,"minutes":55,"month":11,"seconds":32,"time":1512989732000,"timezoneOffset":-480,"year":117},"curType":"CNY","delFlag":"1","homeName":"海外4号","id":38,"memo":"啦啦啦","unit":"㎡","userNo":"10051550"},"overseasList":[],"zsz":0}
     * header : {"code":"0000","desc":"成功","responseTime":"2017-12-14 16:09:38"}
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
         * overseas : {"address":"马来西亚","amount":1500000,"amountRmb":0,"area":"150","assetId":"0012","createTime":{"date":11,"day":1,"hours":18,"minutes":55,"month":11,"seconds":32,"time":1512989732000,"timezoneOffset":-480,"year":117},"curType":"CNY","delFlag":"1","homeName":"海外4号","id":38,"memo":"啦啦啦","unit":"㎡","userNo":"10051550"}
         * overseasList : []
         * zsz : 0
         */

        private OverseasBean overseas;
        private String zsz;
        private List<?> overseasList;

        public OverseasBean getOverseas() {
            return overseas;
        }

        public void setOverseas(OverseasBean overseas) {
            this.overseas = overseas;
        }

        public String getZsz() {
            return zsz;
        }

        public void setZsz(String zsz) {
            this.zsz = zsz;
        }

        public List<?> getOverseasList() {
            return overseasList;
        }

        public void setOverseasList(List<?> overseasList) {
            this.overseasList = overseasList;
        }

        public static class OverseasBean {
            /**
             * address : 马来西亚
             * amount : 1500000
             * amountRmb : 0
             * area : 150
             * assetId : 0012
             * createTime : {"date":11,"day":1,"hours":18,"minutes":55,"month":11,"seconds":32,"time":1512989732000,"timezoneOffset":-480,"year":117}
             * curType : CNY
             * delFlag : 1
             * homeName : 海外4号
             * id : 38
             * memo : 啦啦啦
             * unit : ㎡
             * userNo : 10051550
             */

            private String address;
            private String amount;
            private String amountRmb;
            private String area;
            private String assetId;
            private CreateTimeBean createTime;
            private String curType;
            private String delFlag;
            private String homeName;
            private String id;
            private String memo;
            private String unit;
            private String userNo;

            public String getAddress() {
                return address;
            }

            public void setAddress(String address) {
                this.address = address;
            }

            public String getAmount() {
                return amount;
            }

            public void setAmount(String amount) {
                this.amount = amount;
            }

            public String getAmountRmb() {
                return amountRmb;
            }

            public void setAmountRmb(String amountRmb) {
                this.amountRmb = amountRmb;
            }

            public String getArea() {
                return area;
            }

            public void setArea(String area) {
                this.area = area;
            }

            public String getAssetId() {
                return assetId;
            }

            public void setAssetId(String assetId) {
                this.assetId = assetId;
            }

            public CreateTimeBean getCreateTime() {
                return createTime;
            }

            public void setCreateTime(CreateTimeBean createTime) {
                this.createTime = createTime;
            }

            public String getCurType() {
                return curType;
            }

            public void setCurType(String curType) {
                this.curType = curType;
            }

            public String getDelFlag() {
                return delFlag;
            }

            public void setDelFlag(String delFlag) {
                this.delFlag = delFlag;
            }

            public String getHomeName() {
                return homeName;
            }

            public void setHomeName(String homeName) {
                this.homeName = homeName;
            }

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getMemo() {
                return memo;
            }

            public void setMemo(String memo) {
                this.memo = memo;
            }

            public String getUnit() {
                return unit;
            }

            public void setUnit(String unit) {
                this.unit = unit;
            }

            public String getUserNo() {
                return userNo;
            }

            public void setUserNo(String userNo) {
                this.userNo = userNo;
            }

            public static class CreateTimeBean {
                /**
                 * date : 11
                 * day : 1
                 * hours : 18
                 * minutes : 55
                 * month : 11
                 * seconds : 32
                 * time : 1512989732000
                 * timezoneOffset : -480
                 * year : 117
                 */

                private String date;
                private String day;
                private String hours;
                private String minutes;
                private String month;
                private String seconds;
                private String time;
                private String timezoneOffset;
                private String year;

                public String getDate() {
                    return date;
                }

                public void setDate(String date) {
                    this.date = date;
                }

                public String getDay() {
                    return day;
                }

                public void setDay(String day) {
                    this.day = day;
                }

                public String getHours() {
                    return hours;
                }

                public void setHours(String hours) {
                    this.hours = hours;
                }

                public String getMinutes() {
                    return minutes;
                }

                public void setMinutes(String minutes) {
                    this.minutes = minutes;
                }

                public String getMonth() {
                    return month;
                }

                public void setMonth(String month) {
                    this.month = month;
                }

                public String getSeconds() {
                    return seconds;
                }

                public void setSeconds(String seconds) {
                    this.seconds = seconds;
                }

                public String getTime() {
                    return time;
                }

                public void setTime(String time) {
                    this.time = time;
                }

                public String getTimezoneOffset() {
                    return timezoneOffset;
                }

                public void setTimezoneOffset(String timezoneOffset) {
                    this.timezoneOffset = timezoneOffset;
                }

                public String getYear() {
                    return year;
                }

                public void setYear(String year) {
                    this.year = year;
                }
            }
        }
    }

    public static class HeaderBean {
        /**
         * code : 0000
         * desc : 成功
         * responseTime : 2017-12-14 16:09:38
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
