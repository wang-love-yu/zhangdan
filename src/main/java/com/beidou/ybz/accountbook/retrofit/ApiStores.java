package com.beidou.ybz.accountbook.retrofit;


import com.beidou.ybz.accountbook.mvp.entity.EncryptedResponseModel;
import com.beidou.ybz.accountbook.mvp.entity.InviteModel;
import com.beidou.ybz.accountbook.mvp.entity.StockSearchModel;
import com.beidou.ybz.accountbook.mvp.entity.UnencryptedResponseModel;

import okhttp3.ResponseBody;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by bob on 2017/3/24.
 * 集合时下最热门的rxjava+retrofit+okhttp+mvp
 */
public interface ApiStores {
    /**
     * 测试环境
     */

    String API_SERVER_URL = "http://www.360caifu.test/ybz/api/";
    String supportbankPrefix = "http://www.360caifu.test/";
    String fundDetailUrl = API_SERVER_URL + "account/fundpro.htm";//基金详情url前缀
    String API_SEARCH_URL = "http://member.360caifu.test/";//搜索
    String CAISHANG_URL = "http://ybz.360weilicai.com/";//二次分享域名
    String CMS_IMG_URL = "http://www.360caifu.test/";
    String CMS_IMG_URL2 = "http://www.360caifu.test";
    String YZM_URL = "http://static.360caifu.test/ybz/";//图形验证码的前缀
    String OTHER_IMG_URL = "http://static.ziyoulicaishi.test/upload/";//自由理财师头像前缀
    String XIEYI_URL = "http://static.360caifu.test/html/ybz/user/protocol.html";
    String ABOUT_URL = "http://static.360caifu.test/html/ybz/user/about.html";
    String APP_LOGO = "http://m.360caifu.test/ybz/logo.png";
    String CaiFuHui = "http://static.360caifu.test/html/ybz/user/cfhintroduce.html";//财富汇介绍页
    String Dianping = "http://m.haitoujia.test/account/mycomment.htm?userNo=";//我的-我的点评/留言
    String Focus = "http://m.haitoujia.test/account/mycollection.htm?userNo=";//我的-关注
    String Hlhs = "http://m.haitoujia.test/tools/hl.html";//增值，汇率换算
    String Dkjs = "http://m.haitoujia.test/tools/dk.html";//增值，贷款计算
    String CaifubaoIndex = "http://trade.360caifu.test/wap/account/index.htm";///
    String  houseorderDetail = API_SERVER_URL+"houseorder/detail.htm";
    String ZCFX = API_SERVER_URL + "zcfx.htm";//资产分析
    String incrementIndex = API_SERVER_URL + "va/index.htm";//增值首页
    String CaifubaoOpenAccount = "http://trade.360caifu.test/wap/account/openAccount.htm";//财富宝-开户
    String SimuDetail = API_SERVER_URL + "account/smfundpro.htm";//私募详情
    String StockPro = API_SERVER_URL + "account/stockpro.htm";

    //自由理财师测试版加密用的key iv
    String key = "1234567890abc12345678901234567890";
    String iv = "01234567";

    /**
     * UAT
     */

    /*String API_SERVER_URL = "http://www.360caifu.uat/ybz/api/";
    String supportbankPrefix = "http://www.360caifu.uat/";
    String fundDetailUrl = API_SERVER_URL+"account/fundpro.htm";//基金详情url前缀
    String API_SERVER_URL = "http://139.224.43.93:13006/ybz-api/";//api外网访问地址
    String API_SEARCH_URL = "http://member.360caifu.uat/";//搜索
    String CAISHANG_URL = "http://www.360caifu.uat/ybz/api/";//二次分享域名   http://www.360caifu.uat/ybz/api/fq
    String CMS_IMG_URL = "http://www.360caifu.uat/";
    String CMS_IMG_URL2 = "http://www.360caifu.uat";
    String YZM_URL = "http://static.360caifu.uat/ybz/";//图形验证码的前缀
    String OTHER_IMG_URL = "http://static.ziyoulicaishi.uat/upload/";//自由理财师头像前缀
    String XIEYI_URL = "http://static.360caifu.uat/html/ybz/user/protocol.html";
    String ABOUT_URL = "http://static.360caifu.uat/html/ybz/user/about.html";
    String APP_LOGO = "http://m.360caifu.test/ybz/logo.png";
    String CaiFuHui = "http://static.360caifu.uat/html/ybz/user/cfhintroduce.html";//财富汇介绍页
    String Dianping = "http://m.haitoujia.uat/account/mycomment.htm?userNo=";//我的-我的点评/留言
    String Focus = "http://m.haitoujia.uat/account/mycollection.htm?userNo=";//我的-关注
    String Hlhs = "http://m.haitoujia.uat/tools/hl.html";//增值，汇率换算
    String Dkjs = "http://m.haitoujia.uat/tools/dk.html";//增值，贷款计算
    String CaifubaoIndex = "http://trade.360caifu.uat/wap/account/index.htm";
    String ZCFX = API_SERVER_URL+"zcfx.htm";//资产分析
    String incrementIndex = API_SERVER_URL+"va/index.htm";//增值首页
    String CaifubaoOpenAccount = "http://trade.360caifu.uat/wap/account/openAccount.htm";//财富宝-开户
    String SimuDetail = API_SERVER_URL+"account/smfundpro.htm";//私募详情
    String StockPro = API_SERVER_URL+"account/stockpro.htm";

    String  key="1234567890abc12345678901234567891";
    String  iv="01234568";*/


    /**
     * 线上
     */
    /*String API_SERVER_URL = "http://www.360caifu.com/ybz/api/";
    String supportbankPrefix = "http://www.360caifu.com/";
    String fundDetailUrl = API_SERVER_URL + "account/fundpro.htm";//基金详情url前缀
    String API_SEARCH_URL = "http://member.360caifu.com/";//搜索
    String CAISHANG_URL = "http://www.360caifu.com/ybz/api/";//二次分享域名   http://www.360caifu.uat/ybz/api/fq
    String CMS_IMG_URL = "http://www.360caifu.com/";
    String CMS_IMG_URL2 = "http://www.360caifu.com";
    String YZM_URL = "http://static.360caifu.com/ybz/";//图形验证码的前缀
    String OTHER_IMG_URL = "http://static.ziyoulicaishi.com/upload/";//自由理财师头像前缀
    String XIEYI_URL = "http://static.360caifu.com/html/ybz/user/protocol.html";
    String ABOUT_URL = "http://static.360caifu.com/html/ybz/user/about.html";
    String APP_LOGO = "http://m.360caifu.test/ybz/logo.png";
    String  houseorderDetail = API_SERVER_URL+"houseorder/detail.htm";
    String CaiFuHui = "http://static.360caifu.com/html/ybz/user/cfhintroduce.html";//财富汇介绍页
    String Dianping = "http://m.haitoujia.com/account/mycomment.htm?userNo=";//我的-我的点评/留言
    String Focus = "http://m.haitoujia.com/account/mycollection.htm?userNo=";//我的-关注
    String Hlhs = "http://m.haitoujia.com/tools/hl.html";//增值，汇率换算
    String Dkjs = "http://m.haitoujia.com/tools/dk.html";//增值，贷款计算
    String CaifubaoIndex = "http://trade.360caifu.com/wap/account/index.htm";
    String ZCFX = API_SERVER_URL + "zcfx.htm";//资产分析
    String incrementIndex = API_SERVER_URL + "va/index.htm";//增值首页
    String CaifubaoOpenAccount = "http://trade.360caifu.com/wap/account/openAccount.htm";//财富宝-开户
    String SimuDetail = API_SERVER_URL + "account/smfundpro.htm";//私募详情
    String StockPro = API_SERVER_URL + "account/stockpro.htm";

    String key = "1234567890abc12345678901234567891";
    String iv = "01234568";*/

    /**
     * 微信和QQ的ID和秘钥
     */
    String QQ_APPID = "101448247";
    String QQ_APPKEY = "cfa09450df93b2e91963bcacd192d6a4";
    String WEIXIN_APP_ID = "wx47aab94a860b83ed";
    String WEIXIN_APP_SECRET = "3a84ef9f7ff71d23ff7abc8b51d82191";

    @FormUrlEncoded
    @POST("common/start.htm")
    Observable<EncryptedResponseModel> getStart(@Field("encMsg") String encMsg, @Field("signMsg") String signMsg,
                                                @Field("msgType") String msgType, @Field("secretKeyId") String secretKeyId);

    @GET("common/getsecretkey.htm")
    Observable<UnencryptedResponseModel> getSecretkey(@Query("msgType") String msgType, @Query("msg") String msg);//响应未加密

    @FormUrlEncoded
    @POST("common/sendvalidcode.htm")
        //发送手机验证码-响应加密
    Observable<EncryptedResponseModel> sendValidcode(@Field("encMsg") String encMsg, @Field("signMsg") String signMsg, @Field("msgType") String msgType, @Field("secretKeyId") String secretKeyId);

    @FormUrlEncoded
    @POST("account/index.htm")
        //记账首页-响应加密
    Observable<ResponseBody> getAccountIndex(@Field("encMsg") String encMsg, @Field("signMsg") String signMsg, @Field("msgType") String msgType, @Field("secretKeyId") String secretKeyId);

    @FormUrlEncoded
    @POST("user/login.htm")
    Observable<EncryptedResponseModel> login(@Field("encMsg") String encMsg, @Field("signMsg") String signMsg,
                                             @Field("msgType") String msgType, @Field("secretKeyId") String secretKeyId);

    @FormUrlEncoded
    @POST("account/addoverseas.htm")
        //添加海外房产
    Observable<EncryptedResponseModel> addoverseas(@Field("encMsg") String encMsg, @Field("signMsg") String signMsg,
                                                   @Field("msgType") String msgType, @Field("secretKeyId") String secretKeyId);

    @FormUrlEncoded
    @POST("account/updateoverseas.htm")
        //编辑海外房产
    Observable<EncryptedResponseModel> updateoverseas(@Field("encMsg") String encMsg, @Field("signMsg") String signMsg,
                                                      @Field("msgType") String msgType, @Field("secretKeyId") String secretKeyId);

    @FormUrlEncoded
    @POST("account/updatedomain.htm")
        //编辑域名估值
    Observable<EncryptedResponseModel> updatedomain(@Field("encMsg") String encMsg, @Field("signMsg") String signMsg,
                                                    @Field("msgType") String msgType, @Field("secretKeyId") String secretKeyId);

    @FormUrlEncoded
    @POST("account/updatesmfund.htm")
        //编辑私募基金
    Observable<EncryptedResponseModel> updatesmfund(@Field("encMsg") String encMsg, @Field("signMsg") String signMsg,
                                                    @Field("msgType") String msgType, @Field("secretKeyId") String secretKeyId);

    @FormUrlEncoded
    @POST("account/updatefund.htm")
        //编辑基金
    Observable<EncryptedResponseModel> updatefund(@Field("encMsg") String encMsg, @Field("signMsg") String signMsg,
                                                  @Field("msgType") String msgType, @Field("secretKeyId") String secretKeyId);

    @FormUrlEncoded
    @POST("account/updateexpenseaccount.htm")
        //编辑报销
    Observable<EncryptedResponseModel> updateexpenseaccount(@Field("encMsg") String encMsg, @Field("signMsg") String signMsg,
                                                            @Field("msgType") String msgType, @Field("secretKeyId") String secretKeyId);

    @FormUrlEncoded
    @POST("account/updatecreditasset.htm")
        //编辑海外房产
    Observable<EncryptedResponseModel> updatecreditasset(@Field("encMsg") String encMsg, @Field("signMsg") String signMsg,
                                                         @Field("msgType") String msgType, @Field("secretKeyId") String secretKeyId);

    @FormUrlEncoded
    @POST("account/updatearrearsasset.htm")
        //编辑欠款
    Observable<EncryptedResponseModel> updatearrearsasset(@Field("encMsg") String encMsg, @Field("signMsg") String signMsg,
                                                          @Field("msgType") String msgType, @Field("secretKeyId") String secretKeyId);

    @FormUrlEncoded
    @POST("account/getoverseaslist.htm")
        //海外房产列表页
    Observable<EncryptedResponseModel> getoverseaslist(@Field("encMsg") String encMsg, @Field("signMsg") String signMsg,
                                                       @Field("msgType") String msgType, @Field("secretKeyId") String secretKeyId);


    @FormUrlEncoded
    @POST("account/addstock.htm")
        //添加股票页
    Observable<EncryptedResponseModel> addstock(@Field("encMsg") String encMsg, @Field("signMsg") String signMsg, @Field("msgType") String msgType, @Field("secretKeyId") String secretKeyId);

    @FormUrlEncoded
    @POST("account/getstocklist.htm")
        //股票列表页
    Observable<EncryptedResponseModel> getstocklist(@Field("encMsg") String encMsg, @Field("signMsg") String signMsg,
                                                    @Field("msgType") String msgType, @Field("secretKeyId") String secretKeyId);

    @FormUrlEncoded
    @POST("account/getfundlist.htm")
        //资金列表页
    Observable<EncryptedResponseModel> getfundlist(@Field("encMsg") String encMsg, @Field("signMsg") String signMsg,
                                                   @Field("msgType") String msgType, @Field("secretKeyId") String secretKeyId);

    @FormUrlEncoded
    @POST("account/addinsurance.htm")
        //添加保险
    Observable<EncryptedResponseModel> addinsurance(@Field("encMsg") String encMsg, @Field("signMsg") String signMsg,
                                                    @Field("msgType") String msgType, @Field("secretKeyId") String secretKeyId);

    @FormUrlEncoded
    @POST("account/addprepaidcard.htm")
        //添加预付卡
    Observable<EncryptedResponseModel> addprepaidcard(@Field("encMsg") String encMsg, @Field("signMsg") String signMsg,
                                                      @Field("msgType") String msgType, @Field("secretKeyId") String secretKeyId);


    @FormUrlEncoded
    @POST("account/addloanasset.htm")
        //添加借款
    Observable<EncryptedResponseModel> addloanasset(@Field("encMsg") String encMsg, @Field("signMsg") String signMsg,
                                                    @Field("msgType") String msgType, @Field("secretKeyId") String secretKeyId);

    @FormUrlEncoded
    @POST("account/addcustomasset.htm")
        //添加自定义分类
    Observable<EncryptedResponseModel> addcustomasset(@Field("encMsg") String encMsg, @Field("signMsg") String signMsg,
                                                      @Field("msgType") String msgType, @Field("secretKeyId") String secretKeyId);

    @FormUrlEncoded
    @POST("account/addgame.htm")
        //添加游戏
    Observable<EncryptedResponseModel> addgame(@Field("encMsg") String encMsg, @Field("signMsg") String signMsg,
                                               @Field("msgType") String msgType, @Field("secretKeyId") String secretKeyId);

    @FormUrlEncoded
    @POST("account/updateinsurance.htm")
        //更新保险
    Observable<EncryptedResponseModel> updateinsurance(@Field("encMsg") String encMsg, @Field("signMsg") String signMsg,
                                                       @Field("msgType") String msgType, @Field("secretKeyId") String secretKeyId);

    @FormUrlEncoded
    @POST("sys/sendopinion.htm")
        //更新保险
    Observable<EncryptedResponseModel> feedback(@Field("encMsg") String encMsg, @Field("signMsg") String signMsg,
                                                @Field("msgType") String msgType, @Field("secretKeyId") String secretKeyId);

    @FormUrlEncoded
    @POST("account/updateprepaidcard.htm")
        //更新预付卡
    Observable<EncryptedResponseModel> updateprepaidcard(@Field("encMsg") String encMsg, @Field("signMsg") String signMsg,
                                                         @Field("msgType") String msgType, @Field("secretKeyId") String secretKeyId);

    @FormUrlEncoded
    @POST("account/updateloanasset.htm")
        //更新借款
    Observable<EncryptedResponseModel> updateloanasset(@Field("encMsg") String encMsg, @Field("signMsg") String signMsg,
                                                       @Field("msgType") String msgType, @Field("secretKeyId") String secretKeyId);

    @FormUrlEncoded
    @POST("account/updatecustomasset.htm")
        //更新自定义分类
    Observable<EncryptedResponseModel> updatecustomasset(@Field("encMsg") String encMsg, @Field("signMsg") String signMsg,
                                                         @Field("msgType") String msgType, @Field("secretKeyId") String secretKeyId);

    @FormUrlEncoded
    @POST("account/updategame.htm")
        //更新游戏
    Observable<EncryptedResponseModel> updategame(@Field("encMsg") String encMsg, @Field("signMsg") String signMsg,
                                                  @Field("msgType") String msgType, @Field("secretKeyId") String secretKeyId);

    @FormUrlEncoded
    @POST("account/updateprepaidcardsum.htm")
        //更新预付卡操作记录
    Observable<EncryptedResponseModel> updateprepaidcardsum(@Field("encMsg") String encMsg, @Field("signMsg") String signMsg,
                                                            @Field("msgType") String msgType, @Field("secretKeyId") String secretKeyId);

    @FormUrlEncoded
    @POST("account/getinsurancelist.htm")
        //保险列表页
    Observable<EncryptedResponseModel> getinsurancelist(@Field("encMsg") String encMsg, @Field("signMsg") String signMsg,
                                                        @Field("msgType") String msgType, @Field("secretKeyId") String secretKeyId);

    @FormUrlEncoded
    @POST("account/getarrearsasset.htm")
        //欠款详情页
    Observable<EncryptedResponseModel> getarrearsasset(@Field("encMsg") String encMsg, @Field("signMsg") String signMsg,
                                                       @Field("msgType") String msgType, @Field("secretKeyId") String secretKeyId);

    @FormUrlEncoded
    @POST("account/getloanasset.htm")
        //借款详情页
    Observable<EncryptedResponseModel> getloanasset(@Field("encMsg") String encMsg, @Field("signMsg") String signMsg,
                                                    @Field("msgType") String msgType, @Field("secretKeyId") String secretKeyId);

    @FormUrlEncoded
    @POST("account/getfund.htm")
        //基金详情页
    Observable<EncryptedResponseModel> getfund(@Field("encMsg") String encMsg, @Field("signMsg") String signMsg,
                                               @Field("msgType") String msgType, @Field("secretKeyId") String secretKeyId);

    @FormUrlEncoded
    @POST("account/getsmfund.htm")
        //私募详情页
    Observable<EncryptedResponseModel> getsmfund(@Field("encMsg") String encMsg, @Field("signMsg") String signMsg,
                                                 @Field("msgType") String msgType, @Field("secretKeyId") String secretKeyId);

    @FormUrlEncoded
    @POST("account/getstock.htm")
        //股票详情页
    Observable<EncryptedResponseModel> getstock(@Field("encMsg") String encMsg, @Field("signMsg") String signMsg,
                                                @Field("msgType") String msgType, @Field("secretKeyId") String secretKeyId);

    @FormUrlEncoded
    @POST("account/gettrust.htm")
        //固定收益详情页
    Observable<EncryptedResponseModel> gettrust(@Field("encMsg") String encMsg, @Field("signMsg") String signMsg,
                                                @Field("msgType") String msgType, @Field("secretKeyId") String secretKeyId);

    @FormUrlEncoded
    @POST("account/getdomain.htm")
        //domin详情页
    Observable<EncryptedResponseModel> getdomain(@Field("encMsg") String encMsg, @Field("signMsg") String signMsg,
                                                 @Field("msgType") String msgType, @Field("secretKeyId") String secretKeyId);

    @FormUrlEncoded
    @POST("account/addcurfin.htm")
        //添加活期理财页
    Observable<EncryptedResponseModel> addcurfin(@Field("encMsg") String encMsg, @Field("signMsg") String signMsg,
                                                 @Field("msgType") String msgType, @Field("secretKeyId") String secretKeyId);


    @FormUrlEncoded
    @POST("account/addwebaccount.htm")
        //添加互联网账号
    Observable<EncryptedResponseModel> addwebaccount(@Field("encMsg") String encMsg, @Field("signMsg") String signMsg,
                                                     @Field("msgType") String msgType, @Field("secretKeyId") String secretKeyId);

    @FormUrlEncoded
    @POST("account/addtrust.htm")
        //添加固定收益
    Observable<EncryptedResponseModel> addtrust(@Field("encMsg") String encMsg, @Field("signMsg") String signMsg,
                                                @Field("msgType") String msgType, @Field("secretKeyId") String secretKeyId);

    @FormUrlEncoded
    @POST("user/modifymobile.htm")
        //更改手机号-响应加密
    Observable<EncryptedResponseModel> modifymobile(@Field("encMsg") String encMsg, @Field("signMsg") String signMsg, @Field("msgType") String msgType, @Field("secretKeyId") String secretKeyId);


    @FormUrlEncoded
    @POST("account/getcurfinlist.htm")
        //活期理财列表
    Observable<EncryptedResponseModel> getcurfinlist(@Field("encMsg") String encMsg, @Field("signMsg") String signMsg,
                                                     @Field("msgType") String msgType, @Field("secretKeyId") String secretKeyId);

    @FormUrlEncoded
    @POST("account/getcurfin.htm")
        //活期理财详情
    Observable<EncryptedResponseModel> getcurfin(@Field("encMsg") String encMsg, @Field("signMsg") String signMsg,
                                                 @Field("msgType") String msgType, @Field("secretKeyId") String secretKeyId);

    @FormUrlEncoded
    @POST("account/getprepaidcard.htm")
        //活期理财详情
    Observable<EncryptedResponseModel> getprepaidcard(@Field("encMsg") String encMsg, @Field("signMsg") String signMsg,
                                                      @Field("msgType") String msgType, @Field("secretKeyId") String secretKeyId);

    @FormUrlEncoded
    @POST("account/addbankcard.htm")
        //添加银行卡
    Observable<EncryptedResponseModel> addbankcard(@Field("encMsg") String encMsg, @Field("signMsg") String signMsg,
                                                   @Field("msgType") String msgType, @Field("secretKeyId") String secretKeyId);

    @FormUrlEncoded
    @POST("account/updatebankcard.htm")
        //更新银行卡
    Observable<EncryptedResponseModel> updatebankcard(@Field("encMsg") String encMsg, @Field("signMsg") String signMsg,
                                                      @Field("msgType") String msgType, @Field("secretKeyId") String secretKeyId);


    @FormUrlEncoded
    @POST("account/getbankcardlist.htm")
        //获取银行卡列表
    Observable<EncryptedResponseModel> getbankcardlist(@Field("encMsg") String encMsg, @Field("signMsg") String signMsg, @Field("msgType") String msgType, @Field("secretKeyId") String secretKeyId);


    @FormUrlEncoded
    @POST("user/index.htm")
        //我的首页接口
    Observable<EncryptedResponseModel> index(@Field("encMsg") String encMsg, @Field("signMsg") String signMsg,
                                             @Field("msgType") String msgType, @Field("secretKeyId") String secretKeyId);

    @FormUrlEncoded
    @POST("user/account.htm")
        //账户首页接口
    Observable<EncryptedResponseModel> account(@Field("encMsg") String encMsg, @Field("signMsg") String signMsg,
                                               @Field("msgType") String msgType, @Field("secretKeyId") String secretKeyId);

    @FormUrlEncoded
    @POST("user/bankcardlist.htm")
        //我的财富卡列表
    Observable<EncryptedResponseModel> getCaifucardlist(@Field("encMsg") String encMsg, @Field("signMsg") String signMsg,
                                                        @Field("msgType") String msgType, @Field("secretKeyId") String secretKeyId);


    @FormUrlEncoded
    @POST("fq/index.htm")
        //进入财商首页
    Observable<EncryptedResponseModel> fqindex(@Field("encMsg") String encMsg, @Field("signMsg") String signMsg,
                                               @Field("msgType") String msgType, @Field("secretKeyId") String secretKeyId);


    @FormUrlEncoded
    @POST("user/modifyemail.htm")
        //邮箱修改
    Observable<EncryptedResponseModel> modifyemail(@Field("encMsg") String encMsg, @Field("signMsg") String signMsg,
                                                   @Field("msgType") String msgType, @Field("secretKeyId") String secretKeyId);

    @FormUrlEncoded
    @POST("account/delinsurance.htm")
        //保险列表删除
    Observable<EncryptedResponseModel> delinsurance(@Field("encMsg") String encMsg, @Field("signMsg") String signMsg,
                                                    @Field("msgType") String msgType, @Field("secretKeyId") String secretKeyId);


    @FormUrlEncoded
    @POST("common/getpiccode.htm")
        //图形验证码
    Observable<EncryptedResponseModel> getpiccode(@Field("encMsg") String encMsg, @Field("signMsg") String signMsg,
                                                  @Field("msgType") String msgType, @Field("secretKeyId") String secretKeyId);

    @FormUrlEncoded
    @POST("account/delstock.htm")
        //股票列表删除
    Observable<EncryptedResponseModel> delstock(@Field("encMsg") String encMsg, @Field("signMsg") String signMsg,
                                                @Field("msgType") String msgType, @Field("secretKeyId") String secretKeyId);

    @FormUrlEncoded
    @POST("account/delfund.htm")
        //基金列表删除
    Observable<EncryptedResponseModel> delfund(@Field("encMsg") String encMsg, @Field("signMsg") String signMsg,
                                               @Field("msgType") String msgType, @Field("secretKeyId") String secretKeyId);

    @FormUrlEncoded
    @POST("account/delarrearsasset.htm")
        //欠款删除
    Observable<EncryptedResponseModel> delarrearsasset(@Field("encMsg") String encMsg, @Field("signMsg") String signMsg,
                                                       @Field("msgType") String msgType, @Field("secretKeyId") String secretKeyId);

    @FormUrlEncoded
    @POST("common/checkpiccode.htm")
        //校验图形验证码
    Observable<EncryptedResponseModel> checkpiccode(@Field("encMsg") String encMsg, @Field("signMsg") String signMsg,
                                                    @Field("msgType") String msgType, @Field("secretKeyId") String secretKeyId);

    @FormUrlEncoded
    @POST("account/deloverseas.htm")
        //海外房产列表删除
    Observable<EncryptedResponseModel> deloverseas(@Field("encMsg") String encMsg, @Field("signMsg") String signMsg,
                                                   @Field("msgType") String msgType, @Field("secretKeyId") String secretKeyId);

    @FormUrlEncoded
    @POST("common/checkvalidcode.htm")
        //短信校验接口
    Observable<EncryptedResponseModel> checkvalidcode(@Field("encMsg") String encMsg, @Field("signMsg") String signMsg,
                                                      @Field("msgType") String msgType, @Field("secretKeyId") String secretKeyId);

    @FormUrlEncoded
    @POST("account/delcurfin.htm")
        //理财列表删除
    Observable<EncryptedResponseModel> delcurfin(@Field("encMsg") String encMsg, @Field("signMsg") String signMsg,
                                                 @Field("msgType") String msgType, @Field("secretKeyId") String secretKeyId);

    @FormUrlEncoded
    @POST("account/delcreditasset.htm")
        //贷款列表删除
    Observable<EncryptedResponseModel> delcreditasset(@Field("encMsg") String encMsg, @Field("signMsg") String signMsg,
                                                      @Field("msgType") String msgType, @Field("secretKeyId") String secretKeyId);

    @FormUrlEncoded
    @POST("account/delequityassets.htm")
        //理财列表删除
    Observable<EncryptedResponseModel> delequityassets(@Field("encMsg") String encMsg, @Field("signMsg") String signMsg,
                                                       @Field("msgType") String msgType, @Field("secretKeyId") String secretKeyId);

    @FormUrlEncoded
    @POST("account/delprepaidcard.htm")
        //预付卡删除
    Observable<EncryptedResponseModel> delprepaidcard(@Field("encMsg") String encMsg, @Field("signMsg") String signMsg,
                                                      @Field("msgType") String msgType, @Field("secretKeyId") String secretKeyId);

    @FormUrlEncoded
    @POST("account/delgame.htm")
        //游戏删除
    Observable<EncryptedResponseModel> delgame(@Field("encMsg") String encMsg, @Field("signMsg") String signMsg,
                                               @Field("msgType") String msgType, @Field("secretKeyId") String secretKeyId);

    @FormUrlEncoded
    @POST("account/deldomain.htm")
        //域名删除
    Observable<EncryptedResponseModel> deldomain(@Field("encMsg") String encMsg, @Field("signMsg") String signMsg,
                                                 @Field("msgType") String msgType, @Field("secretKeyId") String secretKeyId);

    @FormUrlEncoded
    @POST("account/delcustomasset.htm")
        //自定义分类删除
    Observable<EncryptedResponseModel> delcustomasset(@Field("encMsg") String encMsg, @Field("signMsg") String signMsg,
                                                      @Field("msgType") String msgType, @Field("secretKeyId") String secretKeyId);

    @FormUrlEncoded
    @POST("account/delsmfund.htm")
        //私募基金删除
    Observable<EncryptedResponseModel> delsmfund(@Field("encMsg") String encMsg, @Field("signMsg") String signMsg,
                                                 @Field("msgType") String msgType, @Field("secretKeyId") String secretKeyId);

    @FormUrlEncoded
    @POST("account/delloanasset.htm")
        //借款删除
    Observable<EncryptedResponseModel> delloanasset(@Field("encMsg") String encMsg, @Field("signMsg") String signMsg,
                                                    @Field("msgType") String msgType, @Field("secretKeyId") String secretKeyId);

    @FormUrlEncoded
    @POST("account/delexpenseaccount.htm")
        //删除报销单
    Observable<EncryptedResponseModel> delexpenseaccount(@Field("encMsg") String encMsg, @Field("signMsg") String signMsg,
                                                         @Field("msgType") String msgType, @Field("secretKeyId") String secretKeyId);

    @FormUrlEncoded
    @POST("account/deltrust.htm")
        //固定收益列表删除
    Observable<EncryptedResponseModel> deltrust(@Field("encMsg") String encMsg, @Field("signMsg") String signMsg,
                                                @Field("msgType") String msgType, @Field("secretKeyId") String secretKeyId);

    @FormUrlEncoded
    @POST("account/delwebaccount.htm")
        //理财列表删除
    Observable<EncryptedResponseModel> delwebaccount(@Field("encMsg") String encMsg, @Field("signMsg") String signMsg,
                                                     @Field("msgType") String msgType, @Field("secretKeyId") String secretKeyId);


    @FormUrlEncoded
    @POST("account/delbankcard.htm")
        //银行卡列表删除
    Observable<EncryptedResponseModel> delbankcard(@Field("encMsg") String encMsg, @Field("signMsg") String signMsg,
                                                   @Field("msgType") String msgType, @Field("secretKeyId") String secretKeyId);

    @FormUrlEncoded
    @POST("account/getinsurance.htm")
        //保险详情
    Observable<EncryptedResponseModel> getinsurance(@Field("encMsg") String encMsg, @Field("signMsg") String signMsg,
                                                    @Field("msgType") String msgType, @Field("secretKeyId") String secretKeyId);

    @FormUrlEncoded
    @POST("account/getwebaccount.htm")
        //保险详情
    Observable<EncryptedResponseModel> getwebaccount(@Field("encMsg") String encMsg, @Field("signMsg") String signMsg,
                                                     @Field("msgType") String msgType, @Field("secretKeyId") String secretKeyId);

    @FormUrlEncoded
    @POST("account/getcustomasset.htm")
        //自定义分类详情
    Observable<EncryptedResponseModel> getcustomasset(@Field("encMsg") String encMsg, @Field("signMsg") String signMsg,
                                                      @Field("msgType") String msgType, @Field("secretKeyId") String secretKeyId);

    @FormUrlEncoded
    @POST("account/getexpenseaccount.htm")
        //保险详情
    Observable<EncryptedResponseModel> getexpenseaccount(@Field("encMsg") String encMsg, @Field("signMsg") String signMsg,
                                                         @Field("msgType") String msgType, @Field("secretKeyId") String secretKeyId);

    @FormUrlEncoded
    @POST("account/getgame.htm")
        //游戏详情
    Observable<EncryptedResponseModel> getgame(@Field("encMsg") String encMsg, @Field("signMsg") String signMsg,
                                               @Field("msgType") String msgType, @Field("secretKeyId") String secretKeyId);

    @FormUrlEncoded
    @POST("account/getoverseas.htm")
        //海外房产详情
    Observable<EncryptedResponseModel> getoverseas(@Field("encMsg") String encMsg, @Field("signMsg") String signMsg,
                                                   @Field("msgType") String msgType, @Field("secretKeyId") String secretKeyId);

    @FormUrlEncoded
    @POST("account/updatecurfinsum.htm")
        //活期理财新增操作记录
    Observable<EncryptedResponseModel> updatecurfinsum(@Field("encMsg") String encMsg, @Field("signMsg") String signMsg,
                                                       @Field("msgType") String msgType, @Field("secretKeyId") String secretKeyId);


    @FormUrlEncoded
    @POST("account/updatecurfin.htm")
        //活期理财信息编辑
    Observable<EncryptedResponseModel> updatecurfin(@Field("encMsg") String encMsg, @Field("signMsg") String signMsg,
                                                    @Field("msgType") String msgType, @Field("secretKeyId") String secretKeyId);

    @FormUrlEncoded
    @POST("account/updatewebaccount.htm")
        //活期理财信息编辑
    Observable<EncryptedResponseModel> updatewebaccount(@Field("encMsg") String encMsg, @Field("signMsg") String signMsg,
                                                        @Field("msgType") String msgType, @Field("secretKeyId") String secretKeyId);

    @FormUrlEncoded
    @POST("account/updatetrust.htm")
        //固定收益编辑
    Observable<EncryptedResponseModel> updatetrust(@Field("encMsg") String encMsg, @Field("signMsg") String signMsg,
                                                   @Field("msgType") String msgType, @Field("secretKeyId") String secretKeyId);

    @FormUrlEncoded
    @POST("account/geturrentrecordList.htm")
        //活期理财操作记录
    Observable<EncryptedResponseModel> geturrentrecordList(@Field("encMsg") String encMsg, @Field("signMsg") String signMsg,
                                                           @Field("msgType") String msgType, @Field("secretKeyId") String secretKeyId);

    @FormUrlEncoded
    @POST("account/getprrecordList.htm")
        //预付卡操作记录
    Observable<EncryptedResponseModel> getprrecordList(@Field("encMsg") String encMsg, @Field("signMsg") String signMsg,
                                                       @Field("msgType") String msgType, @Field("secretKeyId") String secretKeyId);

    @FormUrlEncoded
    @POST("account/getstockrecordlist.htm")
        //操作记录
    Observable<EncryptedResponseModel> getstockrecordlist(@Field("encMsg") String encMsg, @Field("signMsg") String signMsg,
                                                          @Field("msgType") String msgType, @Field("secretKeyId") String secretKeyId);

    @FormUrlEncoded
    @POST("account/getsmfundrecordlist.htm")
        //私募操作记录
    Observable<EncryptedResponseModel> getsmfundrecordlist(@Field("encMsg") String encMsg, @Field("signMsg") String signMsg,
                                                           @Field("msgType") String msgType, @Field("secretKeyId") String secretKeyId);

    @FormUrlEncoded
    @POST("account/getfundrecordlist.htm")
        //基金操作记录
    Observable<EncryptedResponseModel> getfundrecordlist(@Field("encMsg") String encMsg, @Field("signMsg") String signMsg,
                                                         @Field("msgType") String msgType, @Field("secretKeyId") String secretKeyId);

    @FormUrlEncoded
    @POST("account/getbankcard.htm")
        //银行卡详情
    Observable<EncryptedResponseModel> getbankcard(@Field("encMsg") String encMsg, @Field("signMsg") String signMsg,
                                                   @Field("msgType") String msgType, @Field("secretKeyId") String secretKeyId);

    @FormUrlEncoded
    @POST("user/querytxpwd.htm")
        //查询交易密码是否设置接口
    Observable<EncryptedResponseModel> querytxpwd(@Field("encMsg") String encMsg, @Field("signMsg") String signMsg,
                                                  @Field("msgType") String msgType, @Field("secretKeyId") String secretKeyId);

    @FormUrlEncoded
    @POST("user/settxpwd.htm")
        //支付密码设置接口
    Observable<EncryptedResponseModel> settxpwd(@Field("encMsg") String encMsg, @Field("signMsg") String signMsg,
                                                @Field("msgType") String msgType, @Field("secretKeyId") String secretKeyId);

    @FormUrlEncoded
    @POST("user/checktxpwd.htm")
        //校验交易密码是否正确
    Observable<EncryptedResponseModel> checktxpwd(@Field("encMsg") String encMsg, @Field("signMsg") String signMsg,
                                                  @Field("msgType") String msgType, @Field("secretKeyId") String secretKeyId);

    @FormUrlEncoded
    @POST("bank/getdata.htm")
        //获取支持的银行卡列表
    Observable<EncryptedResponseModel> getSupportBank(@Field("encMsg") String encMsg, @Field("signMsg") String signMsg,
                                                      @Field("msgType") String msgType, @Field("secretKeyId") String secretKeyId);


    @FormUrlEncoded
    @POST("account/defzc.htm")
        //添加资产
    Observable<EncryptedResponseModel> defzc(@Field("encMsg") String encMsg, @Field("signMsg") String signMsg,
                                             @Field("msgType") String msgType, @Field("secretKeyId") String secretKeyId);

    @FormUrlEncoded
    @POST("common/currencylist.htm")
        //获取支持的货币列表
    Observable<EncryptedResponseModel> getCurrencylist(@Field("encMsg") String encMsg, @Field("signMsg") String signMsg,
                                                       @Field("msgType") String msgType, @Field("secretKeyId") String secretKeyId);


    @FormUrlEncoded
    @POST("account/delindexzc.htm")
        //删除首页添加的资产模块
    Observable<EncryptedResponseModel> delindexzc(@Field("encMsg") String encMsg, @Field("signMsg") String signMsg,
                                                  @Field("msgType") String msgType, @Field("secretKeyId") String secretKeyId);


    @FormUrlEncoded
    @POST("account/stockpro.htm")
        //股票详情
    Observable<EncryptedResponseModel> stockpro(@Field("encMsg") String encMsg, @Field("signMsg") String signMsg,
                                                @Field("msgType") String msgType, @Field("secretKeyId") String secretKeyId);


    @FormUrlEncoded
    @POST("account/updatestock.htm")
        //股票编辑
    Observable<EncryptedResponseModel> updatestock(@Field("encMsg") String encMsg, @Field("signMsg") String signMsg,
                                                   @Field("msgType") String msgType, @Field("secretKeyId") String secretKeyId);

//    @FormUrlEncoded
//    @POST("search/loadwordsstock.htm")
//        //股票搜索
//    Observable<EncryptedResponseModel> loadwordsstock(@Field("encMsg") String encMsg, @Field("signMsg") String signMsg,
//                                                   @Field("msgType") String msgType, @Field("secretKeyId") String secretKeyId);

    @GET("search/loadwordsstock.htm")
        //股票搜索
    Observable<StockSearchModel> loadwordsstock(@Query("callback") String callback, @Query("words") String words);

    @GET("search/loadwordssm.htm")
        //私募基金搜索
    Observable<StockSearchModel> loadwordssm(@Query("callback") String callback, @Query("words") String words);

    @GET("search/loadwordsgm.htm")
        //基金搜索
    Observable<StockSearchModel> loadwordsgm(@Query("callback") String callback, @Query("words") String words);


    @FormUrlEncoded
    @POST("fq/indexdata.htm")
        //财商首页数据
    Observable<EncryptedResponseModel> indexdata(@Field("encMsg") String encMsg, @Field("signMsg") String signMsg,
                                                 @Field("msgType") String msgType, @Field("secretKeyId") String secretKeyId);


    @FormUrlEncoded
    @POST("account/addarrearsasset.htm")
        //添加欠款
    Observable<EncryptedResponseModel> addarrearsasset(@Field("encMsg") String encMsg, @Field("signMsg") String signMsg,
                                                       @Field("msgType") String msgType, @Field("secretKeyId") String secretKeyId);


    @FormUrlEncoded
    @POST("account/getarrearsassetlist.htm")
        //欠款列表
    Observable<EncryptedResponseModel> getArrearsList(@Field("encMsg") String encMsg, @Field("signMsg") String signMsg,
                                                      @Field("msgType") String msgType, @Field("secretKeyId") String secretKeyId);

    @FormUrlEncoded
    @POST("account/addcreditasset.htm")
        //添加贷款
    Observable<EncryptedResponseModel> addCreditasset(@Field("encMsg") String encMsg, @Field("signMsg") String signMsg,
                                                      @Field("msgType") String msgType, @Field("secretKeyId") String secretKeyId);

    @FormUrlEncoded
    @POST("account/getcreditassetlist.htm")
        //贷款列表
    Observable<EncryptedResponseModel> getCreditList(@Field("encMsg") String encMsg, @Field("signMsg") String signMsg,
                                                     @Field("msgType") String msgType, @Field("secretKeyId") String secretKeyId);

    @FormUrlEncoded
    @POST("account/addequityassets.htm")
        //添加股权
    Observable<EncryptedResponseModel> addEquityassets(@Field("encMsg") String encMsg, @Field("signMsg") String signMsg,
                                                       @Field("msgType") String msgType, @Field("secretKeyId") String secretKeyId);

    @FormUrlEncoded
    @POST("account/adddomain.htm")
        //添加域名估值
    Observable<EncryptedResponseModel> adddomain(@Field("encMsg") String encMsg, @Field("signMsg") String signMsg,
                                                 @Field("msgType") String msgType, @Field("secretKeyId") String secretKeyId);

    @FormUrlEncoded
    @POST("account/addsmfund.htm")
        //添加域名估值
    Observable<EncryptedResponseModel> addsmfund(@Field("encMsg") String encMsg, @Field("signMsg") String signMsg,
                                                 @Field("msgType") String msgType, @Field("secretKeyId") String secretKeyId);

    @FormUrlEncoded
    @POST("account/addfund.htm")
        //添加公募基金
    Observable<EncryptedResponseModel> addfund(@Field("encMsg") String encMsg, @Field("signMsg") String signMsg,
                                               @Field("msgType") String msgType, @Field("secretKeyId") String secretKeyId);

    @FormUrlEncoded
    @POST("account/getequityassetslist.htm")
        //股权列表
    Observable<EncryptedResponseModel> getEquityList(@Field("encMsg") String encMsg, @Field("signMsg") String signMsg,
                                                     @Field("msgType") String msgType, @Field("secretKeyId") String secretKeyId);

    @FormUrlEncoded
    @POST("account/getprepaidcardlist.htm")
        //股权列表
    Observable<EncryptedResponseModel> getprepaidcardlist(@Field("encMsg") String encMsg, @Field("signMsg") String signMsg,
                                                          @Field("msgType") String msgType, @Field("secretKeyId") String secretKeyId);

    @FormUrlEncoded
    @POST("account/getdomainlist.htm")
        //域名估值列表
    Observable<EncryptedResponseModel> getdomainlist(@Field("encMsg") String encMsg, @Field("signMsg") String signMsg,
                                                     @Field("msgType") String msgType, @Field("secretKeyId") String secretKeyId);

    @FormUrlEncoded
    @POST("account/getcustomassetlist.htm")
        //自定义分类列表
    Observable<EncryptedResponseModel> getcustomassetlist(@Field("encMsg") String encMsg, @Field("signMsg") String signMsg,
                                                          @Field("msgType") String msgType, @Field("secretKeyId") String secretKeyId);

    @FormUrlEncoded
    @POST("account/getsmfundlist.htm")
        //私募基金列表
    Observable<EncryptedResponseModel> getsmfundlist(@Field("encMsg") String encMsg, @Field("signMsg") String signMsg,
                                                     @Field("msgType") String msgType, @Field("secretKeyId") String secretKeyId);

    @FormUrlEncoded
    @POST("account/getloanassetlist.htm")
        //借款列表
    Observable<EncryptedResponseModel> getloanassetlist(@Field("encMsg") String encMsg, @Field("signMsg") String signMsg,
                                                        @Field("msgType") String msgType, @Field("secretKeyId") String secretKeyId);

    @FormUrlEncoded
    @POST("account/getgamelist.htm")
        //游戏列表
    Observable<EncryptedResponseModel> getgamelist(@Field("encMsg") String encMsg, @Field("signMsg") String signMsg,
                                                   @Field("msgType") String msgType, @Field("secretKeyId") String secretKeyId);

    @FormUrlEncoded
    @POST("account/getexpenseaccountlist.htm")
        //报销单列表
    Observable<EncryptedResponseModel> getexpenseaccountlist(@Field("encMsg") String encMsg, @Field("signMsg") String signMsg,
                                                             @Field("msgType") String msgType, @Field("secretKeyId") String secretKeyId);

    @FormUrlEncoded
    @POST("account/gettrustlist.htm")
        //股权列表
    Observable<EncryptedResponseModel> gettrustlist(@Field("encMsg") String encMsg, @Field("signMsg") String signMsg,
                                                    @Field("msgType") String msgType, @Field("secretKeyId") String secretKeyId);

    @FormUrlEncoded
    @POST("account/getwebaccountlist.htm")
        //互联网账号列表
    Observable<EncryptedResponseModel> getwebaccountlist(@Field("encMsg") String encMsg, @Field("signMsg") String signMsg,
                                                         @Field("msgType") String msgType, @Field("secretKeyId") String secretKeyId);

    @FormUrlEncoded
    @POST("account/addexpenseaccount.htm")
        //添加报销单
    Observable<EncryptedResponseModel> addExpenseaccount(@Field("encMsg") String encMsg, @Field("signMsg") String signMsg,
                                                         @Field("msgType") String msgType, @Field("secretKeyId") String secretKeyId);

    @FormUrlEncoded
    @POST("account/getequityassets.htm")
        //股权详情
    Observable<EncryptedResponseModel> getequityassets(@Field("encMsg") String encMsg, @Field("signMsg") String signMsg,
                                                       @Field("msgType") String msgType, @Field("secretKeyId") String secretKeyId);

    @FormUrlEncoded
    @POST("account/getcreditasset.htm")
        //贷款详情
    Observable<EncryptedResponseModel> getcreditasset(@Field("encMsg") String encMsg, @Field("signMsg") String signMsg,
                                                      @Field("msgType") String msgType, @Field("secretKeyId") String secretKeyId);

    @FormUrlEncoded
    @POST("account/updateequityassets.htm")
        //编辑股权资产
    Observable<EncryptedResponseModel> updateequityassets(@Field("encMsg") String encMsg, @Field("signMsg") String signMsg,
                                                          @Field("msgType") String msgType, @Field("secretKeyId") String secretKeyId);


    @FormUrlEncoded
    @POST("account/addhjz.htm")
        //新增黑匣子
    Observable<EncryptedResponseModel> addhjz(@Field("encMsg") String encMsg, @Field("signMsg") String signMsg,
                                              @Field("msgType") String msgType, @Field("secretKeyId") String secretKeyId);

    @FormUrlEncoded
    @POST("account/delhjz.htm")
        //删除黑匣子
    Observable<EncryptedResponseModel> delhjz(@Field("encMsg") String encMsg, @Field("signMsg") String signMsg,
                                              @Field("msgType") String msgType, @Field("secretKeyId") String secretKeyId);

    @FormUrlEncoded
    @POST("account/gethjz.htm")
        //查询黑匣子
    Observable<EncryptedResponseModel> gethjz(@Field("encMsg") String encMsg, @Field("signMsg") String signMsg,
                                              @Field("msgType") String msgType, @Field("secretKeyId") String secretKeyId);

    @FormUrlEncoded
    @POST("account/updatehjz.htm")
        //更新黑匣子
    Observable<EncryptedResponseModel> updatehjz(@Field("encMsg") String encMsg, @Field("signMsg") String signMsg,
                                                 @Field("msgType") String msgType, @Field("secretKeyId") String secretKeyId);

    @FormUrlEncoded
    @POST("sys/activitylist.htm")
        //活动列表数据接口
    Observable<EncryptedResponseModel> activitylist(@Field("encMsg") String encMsg, @Field("signMsg") String signMsg,
                                                    @Field("msgType") String msgType, @Field("secretKeyId") String secretKeyId);

    @FormUrlEncoded
    @POST("sys/messagelist.htm")
        //消息列表数据接口
    Observable<EncryptedResponseModel> messagelist(@Field("encMsg") String encMsg, @Field("signMsg") String signMsg,
                                                   @Field("msgType") String msgType, @Field("secretKeyId") String secretKeyId);


    @FormUrlEncoded
    @POST("badge/badgetypelist.htm")
        //徽章大类
    Observable<EncryptedResponseModel> badgetypelist(@Field("encMsg") String encMsg, @Field("signMsg") String signMsg,
                                                     @Field("msgType") String msgType, @Field("secretKeyId") String secretKeyId);

    @FormUrlEncoded
    @POST("badge/badgedetaillist.htm")
        //徽章二类
    Observable<EncryptedResponseModel> badgedetaillist(@Field("encMsg") String encMsg, @Field("signMsg") String signMsg,
                                                       @Field("msgType") String msgType, @Field("secretKeyId") String secretKeyId);

    @FormUrlEncoded
    @POST("fq/getavdata.htm")
        //财富试听获取分享数据
    Observable<EncryptedResponseModel> getavdata(@Field("encMsg") String encMsg, @Field("signMsg") String signMsg,
                                                 @Field("msgType") String msgType, @Field("secretKeyId") String secretKeyId);


    @FormUrlEncoded
    @POST("user/thirdlogin.htm")
        //三方登录接口
    Observable<EncryptedResponseModel> thirdlogin(@Field("encMsg") String encMsg, @Field("signMsg") String signMsg,
                                                  @Field("msgType") String msgType, @Field("secretKeyId") String secretKeyId);

    @FormUrlEncoded
    @POST("user/thirdbind.htm")
        //三方登录绑定账户接口
    Observable<EncryptedResponseModel> thirdbind(@Field("encMsg") String encMsg, @Field("signMsg") String signMsg,
                                                 @Field("msgType") String msgType, @Field("secretKeyId") String secretKeyId);

    @FormUrlEncoded
    @POST("user/releasebind.htm")
        //三方登录解除绑定接口
    Observable<EncryptedResponseModel> releasebind(@Field("encMsg") String encMsg, @Field("signMsg") String signMsg,
                                                   @Field("msgType") String msgType, @Field("secretKeyId") String secretKeyId);

    @FormUrlEncoded
    @POST("user/accountbind.htm")
        //账户首页绑定三方账号接口
    Observable<EncryptedResponseModel> accountbind(@Field("encMsg") String encMsg, @Field("signMsg") String signMsg,
                                                   @Field("msgType") String msgType, @Field("secretKeyId") String secretKeyId);

    @FormUrlEncoded
    @POST("sys/msgdetail.htm")
        //消息详情页面接口
    Observable<EncryptedResponseModel> msgdetail(@Field("encMsg") String encMsg, @Field("signMsg") String signMsg,
                                                 @Field("msgType") String msgType, @Field("secretKeyId") String secretKeyId);

//    @FormUrlEncoded
//    @POST("badge/invited.htm")
//        //人脉徽章埋点
//    Observable<EncryptedResponseModel> invited(@Field("encMsg") String encMsg, @Field("signMsg") String signMsg,
//                                                 @Field("msgType") String msgType, @Field("secretKeyId") String secretKeyId);


    @GET("badge/invited.htm")
        //人脉获取徽章埋点
    Observable<InviteModel> invited(@Query("userNo") String userNo);


    @FormUrlEncoded
    @POST("common/shorturl.htm")
        //获取短链接接口
    Observable<EncryptedResponseModel> getShorturl(@Field("encMsg") String encMsg, @Field("signMsg") String signMsg,
                                                   @Field("msgType") String msgType, @Field("secretKeyId") String secretKeyId);

//    @GET("Index/mainpage")
//    Observable<MainPageModel> getMain();
//
//    @FormUrlEncoded
//    @POST("Person/userCenter")
//    Observable<UserCenterModel> getUserCenter(@Field("userId") String userId, @Field("userType") String userType);
//    @GET("System/home")
//    Observable<MainPageModel> getMain1();
//    @GET("System/home")
//    Observable<MainPageModel> getCollect();
//    @FormUrlEncoded
//    @POST("Product/product")
//    Observable<InsuranceModel> getProduct(@Field("page") String page, @Field("type") String type, @Field("priceOrderby") String priceOrderby, @Field("salesVolumeOrderby") String salesVolumeOrderby);
//    @FormUrlEncoded
//    @POST("New/search")
//    Observable<SearchModel> getSearch(@Field("keywords") String keywords);
//    @FormUrlEncoded
//    @POST("User/checkPhone")
//    Observable<ValidPhoneModel> validPhone(@Field("phonenum") String phonenum);
//    @FormUrlEncoded
//    @POST("User/authcode")
//    Observable<VercodeModel> getVercode(@Field("phonenum") String phonenum, @Field("type") String type);
//    @FormUrlEncoded
//    @POST("User/comparecode")
//    Observable<ValidVercodeModel> validVercode(@Field("phonenum") String phonenum, @Field("code") String code);
//    @FormUrlEncoded
//    @POST("User/register")
//    Observable<VercodeModel> register(@Field("phonenum") String phonenum, @Field("password") String password);
//    @FormUrlEncoded
//    @POST("User/login")
//    Observable<LoginModel> login(@Field("phonenum") String phonenum, @Field("password") String password);
//    @FormUrlEncoded
//    @POST("New/explain")
//    Observable<HelpCenterModel> getHelpCenter(@Field("page") String page, @Field("type") String type);
//    @FormUrlEncoded
//    @POST("New/index")
//    Observable<NewsModel> getNews(@Field("page") String page);
//    @FormUrlEncoded
//    @POST("Product/productdetails")
//    Observable<ProductDetailModel> getProductDetail(@Field("userId") String userId, @Field("productId") String productId);
//
//    @FormUrlEncoded
//    @POST("Collect/add")
//    Observable<CollectAddModel> addCollect(@Field("userId") String userId, @Field("productId") String productId);
//
//    @FormUrlEncoded
//    @POST("Collect/index")
//    Observable<CollectListModel> getCollectList(@Field("userId") String userId, @Field("page") String page);
//
//    @FormUrlEncoded
//    @POST("Collect/del")
//    Observable<VercodeModel> delCollect(@Field("userId") String userId, @Field("id") String id);
//
//    @Multipart
//    @POST("Person/headerImg")
//    Observable<PersonalCenterModel> upload(
//            @Part("userId") RequestBody userId,
//            @Part("avar\"; filename=\"avatar.png\"") RequestBody file);
////            @PartMap Map<String, RequestBody> params);
//
//    @FormUrlEncoded
//    @POST("Person/addinforrain")
//    Observable<VercodeModel> editUserInfo(@FieldMap Map<String, String> map);
//
//    @FormUrlEncoded
//    @POST("Person/inforrain")
//    Observable<UserInfoModel> getUserInfo(@Field("userId") String userId);
//
//    @FormUrlEncoded
//    @POST("Person/namecard")
//    Observable<BusinessCardModel> getBusinessCarList(@Field("userId") String userId, @Field("page") String page);
//
//    @FormUrlEncoded
//    @POST("Person/addnamecard")
//    Observable<VercodeModel> addBusinessCard(@FieldMap Map<String, String> map);
//    @FormUrlEncoded
//    @POST("Person/editnamecard")
//    Observable<VercodeModel> modifyBusinessCard(@FieldMap Map<String, String> map);
//    @FormUrlEncoded
//    @POST("Person/delectnamecard")
//    Observable<VercodeModel> delBusinessCard(@Field("userId") String userId, @Field("userinfo_id") String userinfo_id);
//    @FormUrlEncoded
//    @POST("System/feedback")
//    Observable<VercodeModel> feedback(@Field("userId") String userId, @Field("message") String message, @Field("phonenum") String phonenum);
//    @FormUrlEncoded
//    @POST("Person/accompany")
//    Observable<VercodeModel> doPayProduct(@FieldMap Map<String, String> map);
//    @FormUrlEncoded
//    @POST("Order/index")
//    Observable<OrderCenterModel> getOrderCenter(@Field("userId") String userId, @Field("page") String page, @Field("type") String type);


//    @GET("Index/mainpage")
//    Observable<MainPageModel> getMain();
//
//    @FormUrlEncoded
//    @POST("Person/userCenter")
//    Observable<UserCenterModel> getUserCenter(@Field("userId") String userId, @Field("userType") String userType);
//    @GET("System/home")
//    Observable<MainPageModel> getMain1();
//    @GET("System/home")
//    Observable<MainPageModel> getCollect();
//    @FormUrlEncoded
//    @POST("Product/product")
//    Observable<InsuranceModel> getProduct(@Field("page") String page, @Field("type") String type, @Field("priceOrderby") String priceOrderby, @Field("salesVolumeOrderby") String salesVolumeOrderby);
//    @FormUrlEncoded
//    @POST("New/search")
//    Observable<SearchModel> getSearch(@Field("keywords") String keywords);
//    @FormUrlEncoded
//    @POST("User/checkPhone")
//    Observable<ValidPhoneModel> validPhone(@Field("phonenum") String phonenum);
//    @FormUrlEncoded
//    @POST("User/authcode")
//    Observable<VercodeModel> getVercode(@Field("phonenum") String phonenum, @Field("type") String type);
//    @FormUrlEncoded
//    @POST("User/comparecode")
//    Observable<ValidVercodeModel> validVercode(@Field("phonenum") String phonenum, @Field("code") String code);
//    @FormUrlEncoded
//    @POST("User/register")
//    Observable<VercodeModel> register(@Field("phonenum") String phonenum, @Field("password") String password);
//    @FormUrlEncoded
//    @POST("User/login")
//    Observable<LoginModel> login(@Field("phonenum") String phonenum, @Field("password") String password);
//    @FormUrlEncoded
//    @POST("New/explain")
//    Observable<HelpCenterModel> getHelpCenter(@Field("page") String page, @Field("type") String type);
//    @FormUrlEncoded
//    @POST("New/index")
//    Observable<NewsModel> getNews(@Field("page") String page);
//    @FormUrlEncoded
//    @POST("Product/productdetails")
//    Observable<ProductDetailModel> getProductDetail(@Field("userId") String userId, @Field("productId") String productId);
//
//    @FormUrlEncoded
//    @POST("Collect/add")
//    Observable<CollectAddModel> addCollect(@Field("userId") String userId, @Field("productId") String productId);
//
//    @FormUrlEncoded
//    @POST("Collect/index")
//    Observable<CollectListModel> getCollectList(@Field("userId") String userId, @Field("page") String page);
//
//    @FormUrlEncoded
//    @POST("Collect/del")
//    Observable<VercodeModel> delCollect(@Field("userId") String userId, @Field("id") String id);
//
//    @Multipart
//    @POST("Person/headerImg")
//    Observable<PersonalCenterModel> upload(
//            @Part("userId") RequestBody userId,
//            @Part("avar\"; filename=\"avatar.png\"") RequestBody file);
////            @PartMap Map<String, RequestBody> params);
//
//    @FormUrlEncoded
//    @POST("Person/addinforrain")
//    Observable<VercodeModel> editUserInfo(@FieldMap Map<String, String> map);
//
//    @FormUrlEncoded
//    @POST("Person/inforrain")
//    Observable<UserInfoModel> getUserInfo(@Field("userId") String userId);
//
//    @FormUrlEncoded
//    @POST("Person/namecard")
//    Observable<BusinessCardModel> getBusinessCarList(@Field("userId") String userId, @Field("page") String page);
//
//    @FormUrlEncoded
//    @POST("Person/addnamecard")
//    Observable<VercodeModel> addBusinessCard(@FieldMap Map<String, String> map);
//    @FormUrlEncoded
//    @POST("Person/editnamecard")
//    Observable<VercodeModel> modifyBusinessCard(@FieldMap Map<String, String> map);
//    @FormUrlEncoded
//    @POST("Person/delectnamecard")
//    Observable<VercodeModel> delBusinessCard(@Field("userId") String userId, @Field("userinfo_id") String userinfo_id);
//    @FormUrlEncoded
//    @POST("System/feedback")
//    Observable<VercodeModel> feedback(@Field("userId") String userId, @Field("message") String message, @Field("phonenum") String phonenum);
//    @FormUrlEncoded
//    @POST("Person/accompany")
//    Observable<VercodeModel> doPayProduct(@FieldMap Map<String, String> map);
//    @FormUrlEncoded
//    @POST("Order/index")
//    Observable<OrderCenterModel> getOrderCenter(@Field("userId") String userId, @Field("page") String page, @Field("type") String type);

}
