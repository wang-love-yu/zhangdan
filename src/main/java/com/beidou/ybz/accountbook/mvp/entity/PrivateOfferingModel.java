package com.beidou.ybz.accountbook.mvp.entity;

import java.util.List;

/**
 * Author: xu.yang on 2018/1/3
 * QQ:754444814
 * E-mail:754444814@qq.com
 * module:私募列表model
 */
public class PrivateOfferingModel {

    /**
     * body : {"positionList":[{"assetId":"0010","brokerName":"投资平台","createTime":{"date":3,"day":3,"hours":16,"minutes":11,"month":0,"seconds":57,"time":1514967117000,"timezoneOffset":-480,"year":118},"delFlag":"1","dqsz":0,"fundCode":"","fundName":"回老家了","id":4,"jjjz":0,"jzrq":"","memo":"啦啦啦","positionNumber":"22","userNo":"10049975","zdf":0},{"assetId":"0010","brokerName":"平台","createTime":{"date":3,"day":3,"hours":16,"minutes":22,"month":0,"seconds":56,"time":1514967776000,"timezoneOffset":-480,"year":118},"delFlag":"1","dqsz":0,"fundCode":"","fundName":"私募名称","id":5,"jjjz":0,"jzrq":"","memo":"备注","positionNumber":"12","userNo":"10049975","zdf":0}],"recordList":[],"stockPositionDto":null,"zsz":0}
     * header : {"code":"0000","desc":"成功","responseTime":"2018-01-03 16:22:55"}
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
         * positionList : [{"assetId":"0010","brokerName":"投资平台","createTime":{"date":3,"day":3,"hours":16,"minutes":11,"month":0,"seconds":57,"time":1514967117000,"timezoneOffset":-480,"year":118},"delFlag":"1","dqsz":0,"fundCode":"","fundName":"回老家了","id":4,"jjjz":0,"jzrq":"","memo":"啦啦啦","positionNumber":"22","userNo":"10049975","zdf":0},{"assetId":"0010","brokerName":"平台","createTime":{"date":3,"day":3,"hours":16,"minutes":22,"month":0,"seconds":56,"time":1514967776000,"timezoneOffset":-480,"year":118},"delFlag":"1","dqsz":0,"fundCode":"","fundName":"私募名称","id":5,"jjjz":0,"jzrq":"","memo":"备注","positionNumber":"12","userNo":"10049975","zdf":0}]
         * recordList : []
         * stockPositionDto : null
         * zsz : 0
         */

        private Object stockPositionDto;
        private String zsz;
        private List<PositionListBean> positionList;
        private List<?> recordList;

        public Object getStockPositionDto() {
            return stockPositionDto;
        }

        public void setStockPositionDto(Object stockPositionDto) {
            this.stockPositionDto = stockPositionDto;
        }

        public String getZsz() {
            return zsz;
        }

        public void setZsz(String zsz) {
            this.zsz = zsz;
        }

        public List<PositionListBean> getPositionList() {
            return positionList;
        }

        public void setPositionList(List<PositionListBean> positionList) {
            this.positionList = positionList;
        }

        public List<?> getRecordList() {
            return recordList;
        }

        public void setRecordList(List<?> recordList) {
            this.recordList = recordList;
        }

        public static class PositionListBean {
            /**
             * assetId : 0010
             * brokerName : 投资平台
             * createTime : {"date":3,"day":3,"hours":16,"minutes":11,"month":0,"seconds":57,"time":1514967117000,"timezoneOffset":-480,"year":118}
             * delFlag : 1
             * dqsz : 0
             * fundCode :
             * fundName : 回老家了
             * id : 4
             * jjjz : 0
             * jzrq :
             * memo : 啦啦啦
             * positionNumber : 22
             * userNo : 10049975
             * zdf : 0
             */

            private String assetId;
            private String brokerName;
            private CreateTimeBean createTime;
            private String delFlag;
            private String dqsz;
            private String fundCode;
            private String fundName;
            private String id;
            private String jjjz;
            private String jzrq;
            private String memo;
            private String positionNumber;
            private String userNo;
            private String zdf;

            public String getAssetId() {
                return assetId;
            }

            public void setAssetId(String assetId) {
                this.assetId = assetId;
            }

            public String getBrokerName() {
                return brokerName;
            }

            public void setBrokerName(String brokerName) {
                this.brokerName = brokerName;
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

            public String getDqsz() {
                return dqsz;
            }

            public void setDqsz(String dqsz) {
                this.dqsz = dqsz;
            }

            public String getFundCode() {
                return fundCode;
            }

            public void setFundCode(String fundCode) {
                this.fundCode = fundCode;
            }

            public String getFundName() {
                return fundName;
            }

            public void setFundName(String fundName) {
                this.fundName = fundName;
            }

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getJjjz() {
                return jjjz;
            }

            public void setJjjz(String jjjz) {
                this.jjjz = jjjz;
            }

            public String getJzrq() {
                return jzrq;
            }

            public void setJzrq(String jzrq) {
                this.jzrq = jzrq;
            }

            public String getMemo() {
                return memo;
            }

            public void setMemo(String memo) {
                this.memo = memo;
            }

            public String getPositionNumber() {
                return positionNumber;
            }

            public void setPositionNumber(String positionNumber) {
                this.positionNumber = positionNumber;
            }

            public String getUserNo() {
                return userNo;
            }

            public void setUserNo(String userNo) {
                this.userNo = userNo;
            }

            public String getZdf() {
                return zdf;
            }

            public void setZdf(String zdf) {
                this.zdf = zdf;
            }

            public static class CreateTimeBean {
                /**
                 * date : 3
                 * day : 3
                 * hours : 16
                 * minutes : 11
                 * month : 0
                 * seconds : 57
                 * time : 1514967117000
                 * timezoneOffset : -480
                 * year : 118
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
         * responseTime : 2018-01-03 16:22:55
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
