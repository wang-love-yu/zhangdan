package com.beidou.ybz.accountbook.mvp.entity;

import java.util.List;

/**
 * Author: xu.yang on 2017/12/10
 * QQ:754444814
 * E-mail:754444814@qq.com
 * module:欠款详情model
 */
public class ArrearsDetailModel {

    /**
     * body : {"infoDto":{"amount":12000,"arrearsName":"杨旭","arrearsStatus":"2","assetId":"0006","createTime":{"date":25,"day":1,"hours":16,"minutes":2,"month":11,"seconds":27,"time":1514188947000,"timezoneOffset":-480,"year":117},"delFlag":"1","id":23,"interest":"12","loanTime":"2017年12月25日","memo":"还钱还钱","returnTime":"2017年12月29日","userNo":"10051564"},"proList":[],"zsz":0}
     * header : {"code":"0000","desc":"成功","responseTime":"2017-12-25 17:27:46"}
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
         * infoDto : {"amount":12000,"arrearsName":"杨旭","arrearsStatus":"2","assetId":"0006","createTime":{"date":25,"day":1,"hours":16,"minutes":2,"month":11,"seconds":27,"time":1514188947000,"timezoneOffset":-480,"year":117},"delFlag":"1","id":23,"interest":"12","loanTime":"2017年12月25日","memo":"还钱还钱","returnTime":"2017年12月29日","userNo":"10051564"}
         * proList : []
         * zsz : 0
         */

        private InfoDtoBean infoDto;
        private int zsz;
        private List<?> proList;

        public InfoDtoBean getInfoDto() {
            return infoDto;
        }

        public void setInfoDto(InfoDtoBean infoDto) {
            this.infoDto = infoDto;
        }

        public int getZsz() {
            return zsz;
        }

        public void setZsz(int zsz) {
            this.zsz = zsz;
        }

        public List<?> getProList() {
            return proList;
        }

        public void setProList(List<?> proList) {
            this.proList = proList;
        }

        public static class InfoDtoBean {
            /**
             * amount : 12000
             * arrearsName : 杨旭
             * arrearsStatus : 2
             * assetId : 0006
             * createTime : {"date":25,"day":1,"hours":16,"minutes":2,"month":11,"seconds":27,"time":1514188947000,"timezoneOffset":-480,"year":117}
             * delFlag : 1
             * id : 23
             * interest : 12
             * loanTime : 2017年12月25日
             * memo : 还钱还钱
             * returnTime : 2017年12月29日
             * userNo : 10051564
             */

            private String amount;
            private String arrearsName;
            private String arrearsStatus;
            private String assetId;
            private CreateTimeBean createTime;
            private String delFlag;
            private String id;
            private String interest;
            private String loanTime;
            private String memo;
            private String returnTime;
            private String userNo;

            public String getAmount() {
                return amount;
            }

            public void setAmount(String amount) {
                this.amount = amount;
            }

            public String getArrearsName() {
                return arrearsName;
            }

            public void setArrearsName(String arrearsName) {
                this.arrearsName = arrearsName;
            }

            public String getArrearsStatus() {
                return arrearsStatus;
            }

            public void setArrearsStatus(String arrearsStatus) {
                this.arrearsStatus = arrearsStatus;
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

            public String getDelFlag() {
                return delFlag;
            }

            public void setDelFlag(String delFlag) {
                this.delFlag = delFlag;
            }

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getInterest() {
                return interest;
            }

            public void setInterest(String interest) {
                this.interest = interest;
            }

            public String getLoanTime() {
                return loanTime;
            }

            public void setLoanTime(String loanTime) {
                this.loanTime = loanTime;
            }

            public String getMemo() {
                return memo;
            }

            public void setMemo(String memo) {
                this.memo = memo;
            }

            public String getReturnTime() {
                return returnTime;
            }

            public void setReturnTime(String returnTime) {
                this.returnTime = returnTime;
            }

            public String getUserNo() {
                return userNo;
            }

            public void setUserNo(String userNo) {
                this.userNo = userNo;
            }

            public static class CreateTimeBean {
                /**
                 * date : 25
                 * day : 1
                 * hours : 16
                 * minutes : 2
                 * month : 11
                 * seconds : 27
                 * time : 1514188947000
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
         * responseTime : 2017-12-25 17:27:46
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
