package com.beidou.ybz.accountbook.mvp.presenter;

import android.content.Context;

import com.beidou.ybz.accountbook.mvp.entity.AccountIndexModel;
import com.beidou.ybz.accountbook.mvp.entity.AccountModel;
import com.beidou.ybz.accountbook.mvp.entity.ArrearsDetailModel;
import com.beidou.ybz.accountbook.mvp.entity.ArrearsModel;
import com.beidou.ybz.accountbook.mvp.entity.AssetClassModel;
import com.beidou.ybz.accountbook.mvp.entity.BankcardDetailModel;
import com.beidou.ybz.accountbook.mvp.entity.BankcardListModel;
import com.beidou.ybz.accountbook.mvp.entity.BaseResponse;
import com.beidou.ybz.accountbook.mvp.entity.BindModel;
import com.beidou.ybz.accountbook.mvp.entity.CaifuCardModel;
import com.beidou.ybz.accountbook.mvp.entity.CheckPiccodeModel;
import com.beidou.ybz.accountbook.mvp.entity.CreditDetailModel;
import com.beidou.ybz.accountbook.mvp.entity.CreditModel;
import com.beidou.ybz.accountbook.mvp.entity.CsIndexDataModel;
import com.beidou.ybz.accountbook.mvp.entity.CurrencyModel;
import com.beidou.ybz.accountbook.mvp.entity.CurrentFinancialDetailModel;
import com.beidou.ybz.accountbook.mvp.entity.CurrentFinancialListModel;
import com.beidou.ybz.accountbook.mvp.entity.CustomDetailModel;
import com.beidou.ybz.accountbook.mvp.entity.CustomListModel;
import com.beidou.ybz.accountbook.mvp.entity.DominDetailModel;
import com.beidou.ybz.accountbook.mvp.entity.DominListModel;
import com.beidou.ybz.accountbook.mvp.entity.EncryptedResponseModel;
import com.beidou.ybz.accountbook.mvp.entity.EquityDetailModel;
import com.beidou.ybz.accountbook.mvp.entity.EquityModel;
import com.beidou.ybz.accountbook.mvp.entity.FixedIncomeDetailModel;
import com.beidou.ybz.accountbook.mvp.entity.FundDetailModel;
import com.beidou.ybz.accountbook.mvp.entity.FundListModel;
import com.beidou.ybz.accountbook.mvp.entity.GameListModel;
import com.beidou.ybz.accountbook.mvp.entity.GetHjzModel;
import com.beidou.ybz.accountbook.mvp.entity.GetPiccodeModel;
import com.beidou.ybz.accountbook.mvp.entity.GetavdataModel;
import com.beidou.ybz.accountbook.mvp.entity.HuizhangBigClassModel;
import com.beidou.ybz.accountbook.mvp.entity.HuizhangSecondClassModel;
import com.beidou.ybz.accountbook.mvp.entity.HuodongListModel;
import com.beidou.ybz.accountbook.mvp.entity.InsuranceDetailModel;
import com.beidou.ybz.accountbook.mvp.entity.InsuranceListModel;
import com.beidou.ybz.accountbook.mvp.entity.InternetDetailModel;
import com.beidou.ybz.accountbook.mvp.entity.InternetListModel;
import com.beidou.ybz.accountbook.mvp.entity.InviteModel;
import com.beidou.ybz.accountbook.mvp.entity.LoandetailModel;
import com.beidou.ybz.accountbook.mvp.entity.LoanlistModel;
import com.beidou.ybz.accountbook.mvp.entity.LoginModel;
import com.beidou.ybz.accountbook.mvp.entity.MessageListModel;
import com.beidou.ybz.accountbook.mvp.entity.MyModel;
import com.beidou.ybz.accountbook.mvp.entity.OperationRecordModel;
import com.beidou.ybz.accountbook.mvp.entity.OverseasDetailModel;
import com.beidou.ybz.accountbook.mvp.entity.OverseasListModel;
import com.beidou.ybz.accountbook.mvp.entity.PayPasswdQueryModel;
import com.beidou.ybz.accountbook.mvp.entity.PrepaidListModel;
import com.beidou.ybz.accountbook.mvp.entity.PrepaidRecordListModel;
import com.beidou.ybz.accountbook.mvp.entity.PrepaiddetailModel;
import com.beidou.ybz.accountbook.mvp.entity.PrivateOfferingModel;
import com.beidou.ybz.accountbook.mvp.entity.ReimDetailModel;
import com.beidou.ybz.accountbook.mvp.entity.ReimburseListModel;
import com.beidou.ybz.accountbook.mvp.entity.SecretKeyResponseModel;
import com.beidou.ybz.accountbook.mvp.entity.SercetKeyOverdueModel;
import com.beidou.ybz.accountbook.mvp.entity.ShortUrlModel;
import com.beidou.ybz.accountbook.mvp.entity.StartModel;
import com.beidou.ybz.accountbook.mvp.entity.StockEditModel;
import com.beidou.ybz.accountbook.mvp.entity.StockListModel;
import com.beidou.ybz.accountbook.mvp.entity.StockRecordModel;
import com.beidou.ybz.accountbook.mvp.entity.StockSearchModel;
import com.beidou.ybz.accountbook.mvp.entity.SupportBankModel;
import com.beidou.ybz.accountbook.mvp.entity.ThirdLoginModel;
import com.beidou.ybz.accountbook.mvp.entity.UnencryptedResponseModel;
import com.beidou.ybz.accountbook.mvp.entity.YZMResponseModel;
import com.beidou.ybz.accountbook.mvp.main.BaseView;
import com.beidou.ybz.accountbook.mvp.other.BasePresenter;
import com.beidou.ybz.accountbook.mvp.view.ChangePhoneView;
import com.beidou.ybz.accountbook.mvp.view.CommonView;
import com.beidou.ybz.accountbook.mvp.view.GetPiccodeView;
import com.beidou.ybz.accountbook.mvp.view.OtherView;
import com.beidou.ybz.accountbook.mvp.view.ThirdLoginView;
import com.beidou.ybz.accountbook.mvp.view.ThirdView;
import com.beidou.ybz.accountbook.mvp.view.ValidVercodeView;
import com.beidou.ybz.accountbook.retrofit.ApiCallback;
import com.beidou.ybz.accountbook.util.DESedeUtil;
import com.beidou.ybz.accountbook.util.GsonTools;
import com.beidou.ybz.accountbook.util.LogUtils;
import com.beidou.ybz.accountbook.util.SharePreferenceUtil;

import okhttp3.ResponseBody;

/**
 * Created by bob on 2017/3/24.
 * 集合时下最热门的rxjava+retrofit+okhttp+mvp
 */
public class CommonPresenter extends BasePresenter<BaseView> {
    public SharePreferenceUtil spUtils;
    private Context mContext;

    public CommonPresenter(Context Context, BaseView view) {
        this.mContext = Context;
        attachView(Context, view);
//        if(mContext != null){
        spUtils = new SharePreferenceUtil(mContext, "xinliangbao");
//        }

    }

    /**
     * 引导页
     */
//    public void getIndex(String userNo) {
//        mvpView.showLoading();
//        addSubscription(apiStores.getIndex(userNo), new ApiCallback<IndexModel>(mContext) {
//            @Override
//            public void onSuccess(IndexModel model) {
//                if (model.getRet() == 0) {
//                    ((CommonView) mvpView).getDataSuccess(model);
//                } else {
//                    ((CommonView) mvpView).getDataFail(model.getMessage());
//                }
//            }
//
//            /**
//             * 引导页
//             */
//            public void getIndex(String userNo) {
//                mvpView.showLoading();
//                addSubscription(apiStores.getIndex(userNo), new ApiCallback<IndexModel>(mContext) {
//                    @Override
//                    public void onSuccess(IndexModel model) {
//                        if (model.getRet() == 0) {
//                            ((CommonView) mvpView).getDataSuccess(model);
//                        } else {
//                            ((CommonView) mvpView).getDataFail(model.getMessage());
//                        }
//                    }
//
//                    @Override
//                    public void onFailure(String msg) {
//                        ((CommonView) mvpView).getDataFail(msg);
//                    }
//
//                    @Override
//                    public void onFinish() {
//                        mvpView.hideLoading();
//                    }
//                });
//            }
//
//            @Override
//            public void onFailure(String msg) {
//                ((CommonView) mvpView).getDataFail(msg);
//            }
//
//            @Override
//            public void onFinish() {
//                mvpView.hideLoading();
//            }
//        });
//    }

    /**
     * 获取秘钥id
     */
    public void getSecretkey(String msg) {
//        mvpView.showLoading();
        addSubscription(apiStores.getSecretkey("1", msg), new ApiCallback<UnencryptedResponseModel>(mContext) {
            @Override
            public void onSuccess(UnencryptedResponseModel model) {
                SecretKeyResponseModel secretKeyResponseModel = GsonTools.getObject(model.getMsg(), SecretKeyResponseModel.class);
                LogUtils.loge(secretKeyResponseModel.getHeader().getCode());
                LogUtils.loge(secretKeyResponseModel.getBody().getSecretKeyId());

                String secretKey = secretKeyResponseModel.getBody().getSecretKey();
                String secretIv = secretKeyResponseModel.getBody().getSecretIv();
                String secretKeyId = secretKeyResponseModel.getBody().getSecretKeyId();

                spUtils.setSecretKey(secretKey);
                spUtils.setSecretIv(secretIv);
                spUtils.setSecretKeyId(secretKeyId);
                ((ThirdView) mvpView).ThirdSuccess(secretKeyResponseModel);

            }

            @Override
            public void onFailure(String msg) {
                ((ThirdView) mvpView).ThirdFail(msg);
            }

            @Override
            public void onFinish() {
                mvpView.hideLoading();
            }
        });
    }

    /**
     * 发送手机验证码
     */
    public void sendValidcode(String encMsg, String signMsg, String msgType, String secretKeyId) {
//        mvpView.showLoading();
        addSubscription(apiStores.sendValidcode(encMsg, signMsg, msgType, secretKeyId), new ApiCallback<EncryptedResponseModel>(mContext) {
            @Override
            public void onSuccess(EncryptedResponseModel model) {
                LogUtils.loge("getMsgType" + model.getMsgType());

                try {
                    String platext = DESedeUtil.decrypt(model.getEncMsg(), spUtils.getSecretKey(), spUtils.getSecretIv());
                    LogUtils.loge("解密后：--------发送手机验证码---------" + platext);

                    YZMResponseModel yzmResponseModel = GsonTools.getObject(platext, YZMResponseModel.class);

                    if (yzmResponseModel != null && yzmResponseModel.getHeader().getCode().equals("0000")) {
                        ((CommonView) mvpView).getDataSuccess(yzmResponseModel);
                    } else {
                        ((CommonView) mvpView).getDataFail(yzmResponseModel.getHeader().getDesc());
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }


//                if(model.getRet() == 0){
//                    ((CommonView)mvpView).getDataSuccess(model);
//                }else{
//                    ((CommonView)mvpView).getDataFail(model.getMessage());
//                }
            }

            @Override
            public void onFailure(String msg) {
                ((CommonView) mvpView).getDataFail(msg);
            }

            @Override
            public void onFinish() {
                mvpView.hideLoading();
            }
        });
    }

    /**
     * 更改手机号
     */
    public void modifyMobile(String encMsg, String signMsg, String msgType, String secretKeyId) {
//        mvpView.showLoading();
        addSubscription(apiStores.modifymobile(encMsg, signMsg, msgType, secretKeyId), new ApiCallback<EncryptedResponseModel>(mContext) {
            @Override
            public void onSuccess(EncryptedResponseModel model) {
                LogUtils.loge("getMsgType" + model.getMsgType());
                String encMsg = model.getEncMsg();
                LogUtils.loge(model.getEncMsg());
                try {
                    String platext = DESedeUtil.decrypt(encMsg, spUtils.getSecretKey(), spUtils.getSecretIv());
                    LogUtils.loge("解密后：-------更改手机号-----" + platext);
                    SercetKeyOverdueModel sercetKeyOverdueModel = GsonTools.getObject(platext, SercetKeyOverdueModel.class);
                    if (sercetKeyOverdueModel != null && sercetKeyOverdueModel.getHeader().getCode().equals("0000")) {
                        ((ChangePhoneView) mvpView).changePhoneSuccess(sercetKeyOverdueModel);
                    } else {
                        ((ChangePhoneView) mvpView).changePhoneFail(sercetKeyOverdueModel.getHeader().getDesc());
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(String msg) {
                ((ChangePhoneView) mvpView).changePhoneFail(msg);
            }

            @Override
            public void onFinish() {
                mvpView.hideLoading();
            }
        });
    }

    /**
     * 获取记账首页数据
     */
    public void getAccountIndex(String encMsg, String signMsg, final String msgType, String secretKeyId) {
//        mvpView.showLoading();
        addSubscription(apiStores.getAccountIndex(encMsg, signMsg, msgType, secretKeyId), new ApiCallback<ResponseBody>(mContext) {
            @Override
            public void onSuccess(ResponseBody model) {
                try {
                    String response = model.string();
                    LogUtils.loge("response:" + response);
                    BaseResponse baseResponse = GsonTools.getObject(response, BaseResponse.class);
                    LogUtils.loge("MsgType:" + baseResponse.getMsgType());

                    if (baseResponse.getMsgType().equals("2")) {//加密正常，继续判断Code
                        EncryptedResponseModel encryptedResponseModel = GsonTools.getObject(response, EncryptedResponseModel.class);
                        String encMsg = encryptedResponseModel.getEncMsg();
                        String platext = DESedeUtil.decrypt(encMsg, spUtils.getSecretKey(), spUtils.getSecretIv());
                        LogUtils.loge("解密后：" + platext);
                        AccountIndexModel accountIndexModel = GsonTools.getObject(platext, AccountIndexModel.class);
                        if (accountIndexModel.getHeader().getCode().equals("0000")) {
                            ((CommonView) mvpView).getDataSuccess(accountIndexModel);
                        } else {
                            LogUtils.loge(accountIndexModel.getHeader().getDesc());
                            ((CommonView) mvpView).getDataFail(accountIndexModel.getHeader().getDesc());
                        }
//                    LogUtils.loge(model.getEncMsg());
                    } else {//加密异常，肯定有错
                        UnencryptedResponseModel unencryptedResponseModel = GsonTools.getObject(response, UnencryptedResponseModel.class);
                        SercetKeyOverdueModel sercetKeyOverdueModel = GsonTools.getObject(unencryptedResponseModel.getMsg(), SercetKeyOverdueModel.class);

                        LogUtils.loge(sercetKeyOverdueModel.getHeader().getCode() + "::" + sercetKeyOverdueModel.getHeader().getDesc());
                        ((CommonView) mvpView).getDataFail(sercetKeyOverdueModel.getHeader().getCode() + "::" + sercetKeyOverdueModel.getHeader().getDesc());
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }

//                LogUtils.loge("MsgType:"+model.getMsgType());
//                String msgType = model.getMsgType();
//                if(msgType != null && msgType.equals("2")){//加密
//                    String encMsg = model.getEncMsg();
//                    LogUtils.loge(model.getEncMsg());
//                    try {
//                        String platext = DESedeUtil.decrypt(encMsg, spUtils.getSecretKey(), spUtils.getSecretIv());
//                        LogUtils.loge("解密后："+platext);
//                        AccountIndexModel accountIndexModel = GsonTools.getObject(platext, AccountIndexModel.class);
//                        if(accountIndexModel.getHeader().getCode().equals("0000")){
//                            ((CommonView)mvpView).getDataSuccess(accountIndexModel);
//                        }else{
//
//                        }
//                    } catch (Exception e) {
//                        e.printStackTrace();
//                    }
//                }else{
//                    LogUtils.loge("首页：未加密:"+model.getMsgType());
//                }
            }

            @Override
            public void onFailure(String msg) {
                ((CommonView) mvpView).getDataFail(msg);
            }

            @Override
            public void onFinish() {
                mvpView.hideLoading();
            }
        });
    }

    /**
     * 登录
     */
    public void login(String encMsg, String signMsg, final String msgType, String secretKeyId) {
        mvpView.showLoading();
        addSubscription(apiStores.login(encMsg, signMsg, msgType, secretKeyId), new ApiCallback<EncryptedResponseModel>(mContext) {
            @Override
            public void onSuccess(EncryptedResponseModel model) {
                LogUtils.loge("MsgType:" + model.getMsgType());
                String msgType = model.getMsgType();
                if (msgType != null && msgType.equals("2")) {//加密
                    String encMsg = model.getEncMsg();
                    LogUtils.loge(model.getEncMsg());
                    try {
                        String platext = DESedeUtil.decrypt(encMsg, spUtils.getSecretKey(), spUtils.getSecretIv());
                        LogUtils.loge("解密后：" + platext);
                        LoginModel loginModel = GsonTools.getObject(platext, LoginModel.class);
                        if (loginModel.getHeader().getCode().equals("0000")) {
                            ((ThirdView) mvpView).ThirdSuccess(loginModel);
                        } else {
                            ((ThirdView) mvpView).ThirdFail(loginModel.getHeader().getDesc());
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                } else {
                    LogUtils.loge("首页：未加密:" + model.getMsgType());
                }
            }

            @Override
            public void onFailure(String msg) {
                ((ThirdView) mvpView).ThirdFail(msg);
            }

            @Override
            public void onFinish() {
                mvpView.hideLoading();
            }
        });
    }

    /**
     * 添加海外房产列表
     */
    public void addoverseas(String encMsg, String signMsg, final String msgType, String secretKeyId) {
        mvpView.showLoading();
        addSubscription(apiStores.addoverseas(encMsg, signMsg, msgType, secretKeyId), new ApiCallback<EncryptedResponseModel>(mContext) {
            @Override
            public void onSuccess(EncryptedResponseModel model) {
                LogUtils.loge("MsgType:" + model.getMsgType());
                String msgType = model.getMsgType();
                if (msgType != null && msgType.equals("2")) {//加密
                    String encMsg = model.getEncMsg();
                    LogUtils.loge(model.getEncMsg());
                    try {
                        String platext = DESedeUtil.decrypt(encMsg, spUtils.getSecretKey(), spUtils.getSecretIv());
                        LogUtils.loge("解密后：" + platext);
                        SercetKeyOverdueModel loginModel = GsonTools.getObject(platext, SercetKeyOverdueModel.class);
                        if (loginModel.getHeader().getCode().equals("0000")) {
                            ((CommonView) mvpView).getDataSuccess(loginModel);
                        } else {
                            ((CommonView) mvpView).getDataFail(loginModel.getHeader().getDesc());
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                } else {
                    LogUtils.loge("首页：未加密:" + model.getMsgType());

                }
            }

            @Override
            public void onFailure(String msg) {
                ((CommonView) mvpView).getDataFail(msg);
            }

            @Override
            public void onFinish() {
                mvpView.hideLoading();
            }
        });
    }

    /**
     * 编辑海外房产列表
     */
    public void updateoverseas(String encMsg, String signMsg, final String msgType, String secretKeyId) {
        mvpView.showLoading();
        addSubscription(apiStores.updateoverseas(encMsg, signMsg, msgType, secretKeyId), new ApiCallback<EncryptedResponseModel>(mContext) {
            @Override
            public void onSuccess(EncryptedResponseModel model) {
                LogUtils.loge("MsgType:" + model.getMsgType());
                String msgType = model.getMsgType();
                if (msgType != null && msgType.equals("2")) {//加密
                    String encMsg = model.getEncMsg();
                    LogUtils.loge(model.getEncMsg());
                    try {
                        String platext = DESedeUtil.decrypt(encMsg, spUtils.getSecretKey(), spUtils.getSecretIv());
                        LogUtils.loge("解密后：" + platext);
                        SercetKeyOverdueModel loginModel = GsonTools.getObject(platext, SercetKeyOverdueModel.class);
                        if (loginModel.getHeader().getCode().equals("0000")) {
                            ((CommonView) mvpView).getDataSuccess(loginModel);
                        } else {
                            ((CommonView) mvpView).getDataFail(loginModel.getHeader().getDesc());
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                } else {
                    LogUtils.loge("首页：未加密:" + model.getMsgType());

                }
            }

            @Override
            public void onFailure(String msg) {
                ((CommonView) mvpView).getDataFail(msg);
            }

            @Override
            public void onFinish() {
                mvpView.hideLoading();
            }
        });
    }

    /**
     * 编辑域名估值
     */
    public void updatedomain(String encMsg, String signMsg, final String msgType, String secretKeyId) {
        mvpView.showLoading();
        addSubscription(apiStores.updatedomain(encMsg, signMsg, msgType, secretKeyId), new ApiCallback<EncryptedResponseModel>(mContext) {
            @Override
            public void onSuccess(EncryptedResponseModel model) {
                LogUtils.loge("MsgType:" + model.getMsgType());
                String msgType = model.getMsgType();
                if (msgType != null && msgType.equals("2")) {//加密
                    String encMsg = model.getEncMsg();
                    LogUtils.loge(model.getEncMsg());
                    try {
                        String platext = DESedeUtil.decrypt(encMsg, spUtils.getSecretKey(), spUtils.getSecretIv());
                        LogUtils.loge("解密后：" + platext);
                        SercetKeyOverdueModel loginModel = GsonTools.getObject(platext, SercetKeyOverdueModel.class);
                        if (loginModel.getHeader().getCode().equals("0000")) {
                            ((CommonView) mvpView).getDataSuccess(loginModel);
                        } else {
                            ((CommonView) mvpView).getDataFail(loginModel.getHeader().getDesc());
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                } else {
                    LogUtils.loge("首页：未加密:" + model.getMsgType());
                }
            }

            @Override
            public void onFailure(String msg) {
                ((CommonView) mvpView).getDataFail(msg);
            }

            @Override
            public void onFinish() {
                mvpView.hideLoading();
            }
        });
    }

    /**
     * 编辑私募基金
     */
    public void updatesmfund(String encMsg, String signMsg, final String msgType, String secretKeyId) {
        mvpView.showLoading();
        addSubscription(apiStores.updatesmfund(encMsg, signMsg, msgType, secretKeyId), new ApiCallback<EncryptedResponseModel>(mContext) {
            @Override
            public void onSuccess(EncryptedResponseModel model) {
                LogUtils.loge("MsgType:" + model.getMsgType());
                String msgType = model.getMsgType();
                if (msgType != null && msgType.equals("2")) {//加密
                    String encMsg = model.getEncMsg();
                    LogUtils.loge(model.getEncMsg());
                    try {
                        String platext = DESedeUtil.decrypt(encMsg, spUtils.getSecretKey(), spUtils.getSecretIv());
                        LogUtils.loge("解密后：" + platext);
                        SercetKeyOverdueModel loginModel = GsonTools.getObject(platext, SercetKeyOverdueModel.class);
                        if (loginModel.getHeader().getCode().equals("0000")) {
                            ((CommonView) mvpView).getDataSuccess(loginModel);
                        } else {
                            ((CommonView) mvpView).getDataFail(loginModel.getHeader().getDesc());
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                } else {
                    LogUtils.loge("首页：未加密:" + model.getMsgType());
                }
            }

            @Override
            public void onFailure(String msg) {
                ((CommonView) mvpView).getDataFail(msg);
            }

            @Override
            public void onFinish() {
                mvpView.hideLoading();
            }
        });
    }

    /**
     * 编辑基金
     */
    public void updatefund(String encMsg, String signMsg, final String msgType, String secretKeyId) {
        mvpView.showLoading();
        addSubscription(apiStores.updatefund(encMsg, signMsg, msgType, secretKeyId), new ApiCallback<EncryptedResponseModel>(mContext) {
            @Override
            public void onSuccess(EncryptedResponseModel model) {
                LogUtils.loge("MsgType:" + model.getMsgType());
                String msgType = model.getMsgType();
                if (msgType != null && msgType.equals("2")) {//加密
                    String encMsg = model.getEncMsg();
                    LogUtils.loge(model.getEncMsg());
                    try {
                        String platext = DESedeUtil.decrypt(encMsg, spUtils.getSecretKey(), spUtils.getSecretIv());
                        LogUtils.loge("解密后：" + platext);
                        SercetKeyOverdueModel loginModel = GsonTools.getObject(platext, SercetKeyOverdueModel.class);
                        if (loginModel.getHeader().getCode().equals("0000")) {
                            ((CommonView) mvpView).getDataSuccess(loginModel);
                        } else {
                            ((CommonView) mvpView).getDataFail(loginModel.getHeader().getDesc());
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                } else {
                    LogUtils.loge("首页：未加密:" + model.getMsgType());
                }
            }

            @Override
            public void onFailure(String msg) {
                ((CommonView) mvpView).getDataFail(msg);
            }

            @Override
            public void onFinish() {
                mvpView.hideLoading();
            }
        });
    }

    /**
     * 编辑报销
     */
    public void updateexpenseaccount(String encMsg, String signMsg, final String msgType, String secretKeyId) {
        mvpView.showLoading();
        addSubscription(apiStores.updateexpenseaccount(encMsg, signMsg, msgType, secretKeyId), new ApiCallback<EncryptedResponseModel>(mContext) {
            @Override
            public void onSuccess(EncryptedResponseModel model) {
                LogUtils.loge("MsgType:" + model.getMsgType());
                String msgType = model.getMsgType();
                if (msgType != null && msgType.equals("2")) {//加密
                    String encMsg = model.getEncMsg();
                    LogUtils.loge(model.getEncMsg());
                    try {
                        String platext = DESedeUtil.decrypt(encMsg, spUtils.getSecretKey(), spUtils.getSecretIv());
                        LogUtils.loge("解密后：" + platext);
                        SercetKeyOverdueModel loginModel = GsonTools.getObject(platext, SercetKeyOverdueModel.class);
                        if (loginModel.getHeader().getCode().equals("0000")) {
                            ((CommonView) mvpView).getDataSuccess(loginModel);
                        } else {
                            ((CommonView) mvpView).getDataFail(loginModel.getHeader().getDesc());
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                } else {
                    LogUtils.loge("首页：未加密:" + model.getMsgType());

                }
            }

            @Override
            public void onFailure(String msg) {
                ((CommonView) mvpView).getDataFail(msg);
            }

            @Override
            public void onFinish() {
                mvpView.hideLoading();
            }
        });
    }

    /**
     * 编辑贷款
     */
    public void updatecreditasset(String encMsg, String signMsg, final String msgType, String secretKeyId) {
        mvpView.showLoading();
        addSubscription(apiStores.updatecreditasset(encMsg, signMsg, msgType, secretKeyId), new ApiCallback<EncryptedResponseModel>(mContext) {
            @Override
            public void onSuccess(EncryptedResponseModel model) {
                LogUtils.loge("MsgType:" + model.getMsgType());
                String msgType = model.getMsgType();
                if (msgType != null && msgType.equals("2")) {//加密
                    String encMsg = model.getEncMsg();
                    LogUtils.loge(model.getEncMsg());
                    try {
                        String platext = DESedeUtil.decrypt(encMsg, spUtils.getSecretKey(), spUtils.getSecretIv());
                        LogUtils.loge("解密后：" + platext);
                        SercetKeyOverdueModel loginModel = GsonTools.getObject(platext, SercetKeyOverdueModel.class);
                        if (loginModel.getHeader().getCode().equals("0000")) {
                            ((CommonView) mvpView).getDataSuccess(loginModel);
                        } else {
                            ((CommonView) mvpView).getDataFail(loginModel.getHeader().getDesc());
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                } else {
                    LogUtils.loge("首页：未加密:" + model.getMsgType());

                }
            }

            @Override
            public void onFailure(String msg) {
                ((CommonView) mvpView).getDataFail(msg);
            }

            @Override
            public void onFinish() {
                mvpView.hideLoading();
            }
        });
    }

    /**
     * 编辑欠款
     */
    public void updatearrearsasset(String encMsg, String signMsg, final String msgType, String secretKeyId) {
//        mvpView.showLoading();
        addSubscription(apiStores.updatearrearsasset(encMsg, signMsg, msgType, secretKeyId), new ApiCallback<EncryptedResponseModel>(mContext) {
            @Override
            public void onSuccess(EncryptedResponseModel model) {
                LogUtils.loge("MsgType:" + model.getMsgType());
                String msgType = model.getMsgType();
                if (msgType != null && msgType.equals("2")) {//加密
                    String encMsg = model.getEncMsg();
                    LogUtils.loge(model.getEncMsg());
                    try {
                        String platext = DESedeUtil.decrypt(encMsg, spUtils.getSecretKey(), spUtils.getSecretIv());
                        LogUtils.loge("解密后：" + platext);
                        SercetKeyOverdueModel loginModel = GsonTools.getObject(platext, SercetKeyOverdueModel.class);
                        if (loginModel.getHeader().getCode().equals("0000")) {
                            ((OtherView) mvpView).onSuccess(loginModel);
                        } else {
                            ((OtherView) mvpView).onFail(loginModel.getHeader().getDesc());
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                } else {
                    LogUtils.loge("首页：未加密:" + model.getMsgType());

                }
            }

            @Override
            public void onFailure(String msg) {
                ((OtherView) mvpView).onFail(msg);
            }

            @Override
            public void onFinish() {
                mvpView.hideLoading();
            }
        });
    }

    /**
     * 海外房产列表
     */
    public void getoverseaslist(String encMsg, String signMsg, final String msgType, String secretKeyId) {
//        mvpView.showLoading();
        addSubscription(apiStores.getoverseaslist(encMsg, signMsg, msgType, secretKeyId), new ApiCallback<EncryptedResponseModel>(mContext) {
            @Override
            public void onSuccess(EncryptedResponseModel model) {
                LogUtils.loge("MsgType:" + model.getMsgType());
                String msgType = model.getMsgType();
                if (msgType != null && msgType.equals("2")) {//加密
                    String encMsg = model.getEncMsg();
                    LogUtils.loge(model.getEncMsg());
                    try {
                        String platext = DESedeUtil.decrypt(encMsg, spUtils.getSecretKey(), spUtils.getSecretIv());
                        LogUtils.logd("解密后：" + platext);
                        OverseasListModel loginModel = GsonTools.getObject(platext, OverseasListModel.class);
                        if (loginModel.getHeader().getCode().equals("0000")) {
                            ((CommonView) mvpView).getDataSuccess(loginModel);
                        } else {
                            ((CommonView) mvpView).getDataFail(loginModel.getHeader().getDesc());
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                } else {
                    LogUtils.loge("首页：未加密:" + model.getMsgType());

                }
            }

            @Override
            public void onFailure(String msg) {
                ((CommonView) mvpView).getDataFail(msg);
            }

            @Override
            public void onFinish() {
                mvpView.hideLoading();
            }
        });
    }

    /**
     * 添加股票
     *
     * @param encMsg
     * @param signMsg
     * @param msgType
     * @param secretKeyId
     */
    public void addstock(String encMsg, String signMsg, final String msgType, String secretKeyId) {
        mvpView.showLoading();
        addSubscription(apiStores.addstock(encMsg, signMsg, msgType, secretKeyId), new ApiCallback<EncryptedResponseModel>(mContext) {
            @Override
            public void onSuccess(EncryptedResponseModel model) {
                LogUtils.loge("MsgType:" + model.getMsgType());
                String msgType = model.getMsgType();
                if (msgType != null && msgType.equals("2")) {//加密
                    String encMsg = model.getEncMsg();
                    LogUtils.loge(model.getEncMsg());
                    try {
                        String platext = DESedeUtil.decrypt(encMsg, spUtils.getSecretKey(), spUtils.getSecretIv());
                        LogUtils.loge("解密后：" + platext);
                        SercetKeyOverdueModel loginModel = GsonTools.getObject(platext, SercetKeyOverdueModel.class);
                        if (loginModel.getHeader().getCode().equals("0000")) {
                            ((ThirdView) mvpView).ThirdSuccess(loginModel);
                        } else {
                            ((ThirdView) mvpView).ThirdFail(loginModel.getHeader().getDesc());
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                } else {
                    LogUtils.loge("首页：未加密:" + model.getMsgType());
                }
            }

            @Override
            public void onFailure(String msg) {
                ((ThirdView) mvpView).ThirdFail(msg);
            }

            @Override
            public void onFinish() {
                mvpView.hideLoading();
            }
        });
    }

    /**
     * 股票列表
     */
    public void getstocklist(String encMsg, String signMsg, final String msgType, String secretKeyId) {
//        mvpView.showLoading();
        addSubscription(apiStores.getstocklist(encMsg, signMsg, msgType, secretKeyId), new ApiCallback<EncryptedResponseModel>(mContext) {
            @Override
            public void onSuccess(EncryptedResponseModel model) {
                LogUtils.loge("MsgType:" + model.getMsgType());
                String msgType = model.getMsgType();
                if (msgType != null && msgType.equals("2")) {//加密
                    String encMsg = model.getEncMsg();
                    LogUtils.loge(model.getEncMsg());
                    try {
                        String platext = DESedeUtil.decrypt(encMsg, spUtils.getSecretKey(), spUtils.getSecretIv());
                        LogUtils.loge("解密后：" + platext);
                        StockListModel loginModel = GsonTools.getObject(platext, StockListModel.class);
                        if (loginModel.getHeader().getCode().equals("0000")) {
                            ((CommonView) mvpView).getDataSuccess(loginModel);
                        } else {
                            ((CommonView) mvpView).getDataFail(loginModel.getHeader().getDesc());
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                } else {
                    LogUtils.loge("首页：未加密:" + model.getMsgType());
                }
            }

            @Override
            public void onFailure(String msg) {
                ((CommonView) mvpView).getDataFail(msg);
            }

            @Override
            public void onFinish() {
                mvpView.hideLoading();
            }
        });
    }

    /**
     * 基金列表
     */
    public void getfundlist(String encMsg, String signMsg, final String msgType, String secretKeyId) {
//        mvpView.showLoading();
        addSubscription(apiStores.getfundlist(encMsg, signMsg, msgType, secretKeyId), new ApiCallback<EncryptedResponseModel>(mContext) {
            @Override
            public void onSuccess(EncryptedResponseModel model) {
                LogUtils.loge("MsgType:" + model.getMsgType());
                String msgType = model.getMsgType();
                if (msgType != null && msgType.equals("2")) {//加密
                    String encMsg = model.getEncMsg();
                    LogUtils.loge(model.getEncMsg());
                    try {
                        String platext = DESedeUtil.decrypt(encMsg, spUtils.getSecretKey(), spUtils.getSecretIv());
                        LogUtils.loge("解密后：" + platext);
                        FundListModel loginModel = GsonTools.getObject(platext, FundListModel.class);
                        if (loginModel.getHeader().getCode().equals("0000")) {
                            ((CommonView) mvpView).getDataSuccess(loginModel);
                        } else {
                            ((CommonView) mvpView).getDataFail(loginModel.getHeader().getDesc());
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                } else {
                    LogUtils.loge("首页：未加密:" + model.getMsgType());
                }
            }

            @Override
            public void onFailure(String msg) {
                ((CommonView) mvpView).getDataFail(msg);
            }

            @Override
            public void onFinish() {
                mvpView.hideLoading();
            }
        });
    }

    /**
     * 添加保险
     */
    public void addinsurance(String encMsg, String signMsg, final String msgType, String secretKeyId) {
        mvpView.showLoading();
        addSubscription(apiStores.addinsurance(encMsg, signMsg, msgType, secretKeyId), new ApiCallback<EncryptedResponseModel>(mContext) {
            @Override
            public void onSuccess(EncryptedResponseModel model) {
                LogUtils.loge("MsgType:" + model.getMsgType());
                String msgType = model.getMsgType();
                if (msgType != null && msgType.equals("2")) {//加密
                    String encMsg = model.getEncMsg();
                    LogUtils.loge(model.getEncMsg());
                    try {
                        String platext = DESedeUtil.decrypt(encMsg, spUtils.getSecretKey(), spUtils.getSecretIv());
                        LogUtils.loge("解密后：" + platext);
                        SercetKeyOverdueModel loginModel = GsonTools.getObject(platext, SercetKeyOverdueModel.class);
                        if (loginModel.getHeader().getCode().equals("0000")) {
                            ((CommonView) mvpView).getDataSuccess(loginModel);
                        } else {
                            ((CommonView) mvpView).getDataFail(loginModel.getHeader().getDesc());
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                } else {
                    LogUtils.loge("首页：未加密:" + model.getMsgType());
                }
            }

            @Override
            public void onFailure(String msg) {
                ((CommonView) mvpView).getDataFail(msg);
            }

            @Override
            public void onFinish() {
                mvpView.hideLoading();
            }
        });
    }

    /**
     * 添加预付卡
     */
    public void addprepaidcard(String encMsg, String signMsg, final String msgType, String secretKeyId) {
        mvpView.showLoading();
        addSubscription(apiStores.addprepaidcard(encMsg, signMsg, msgType, secretKeyId), new ApiCallback<EncryptedResponseModel>(mContext) {
            @Override
            public void onSuccess(EncryptedResponseModel model) {
                LogUtils.loge("MsgType:" + model.getMsgType());
                String msgType = model.getMsgType();
                if (msgType != null && msgType.equals("2")) {//加密
                    String encMsg = model.getEncMsg();
                    LogUtils.loge(model.getEncMsg());
                    try {
                        String platext = DESedeUtil.decrypt(encMsg, spUtils.getSecretKey(), spUtils.getSecretIv());
                        LogUtils.loge("解密后：" + platext);
                        SercetKeyOverdueModel loginModel = GsonTools.getObject(platext, SercetKeyOverdueModel.class);
                        if (loginModel.getHeader().getCode().equals("0000")) {
                            ((CommonView) mvpView).getDataSuccess(loginModel);
                        } else {
                            ((CommonView) mvpView).getDataFail(loginModel.getHeader().getDesc());
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                } else {
                    LogUtils.loge("首页：未加密:" + model.getMsgType());
                }
            }

            @Override
            public void onFailure(String msg) {
                ((CommonView) mvpView).getDataFail(msg);
            }

            @Override
            public void onFinish() {
                mvpView.hideLoading();
            }
        });
    }

    /**
     * 添加借款
     */
    public void addloanasset(String encMsg, String signMsg, final String msgType, String secretKeyId) {
        mvpView.showLoading();
        addSubscription(apiStores.addloanasset(encMsg, signMsg, msgType, secretKeyId), new ApiCallback<EncryptedResponseModel>(mContext) {
            @Override
            public void onSuccess(EncryptedResponseModel model) {
                LogUtils.loge("MsgType:" + model.getMsgType());
                String msgType = model.getMsgType();
                if (msgType != null && msgType.equals("2")) {//加密
                    String encMsg = model.getEncMsg();
                    LogUtils.loge(model.getEncMsg());
                    try {
                        String platext = DESedeUtil.decrypt(encMsg, spUtils.getSecretKey(), spUtils.getSecretIv());
                        LogUtils.loge("解密后：" + platext);
                        SercetKeyOverdueModel loginModel = GsonTools.getObject(platext, SercetKeyOverdueModel.class);
                        if (loginModel.getHeader().getCode().equals("0000")) {
                            ((OtherView) mvpView).onSuccess(loginModel);
                        } else {
                            ((OtherView) mvpView).onFail(loginModel.getHeader().getDesc());
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                } else {
                    LogUtils.loge("首页：未加密:" + model.getMsgType());
                }
            }

            @Override
            public void onFailure(String msg) {
                ((OtherView) mvpView).onFail(msg);
            }

            @Override
            public void onFinish() {
                mvpView.hideLoading();
            }
        });
    }

    /**
     * 添加自定义分类
     */
    public void addcustomasset(String encMsg, String signMsg, final String msgType, String secretKeyId) {
        mvpView.showLoading();
        addSubscription(apiStores.addcustomasset(encMsg, signMsg, msgType, secretKeyId), new ApiCallback<EncryptedResponseModel>(mContext) {
            @Override
            public void onSuccess(EncryptedResponseModel model) {
                LogUtils.loge("MsgType:" + model.getMsgType());
                String msgType = model.getMsgType();
                if (msgType != null && msgType.equals("2")) {//加密
                    String encMsg = model.getEncMsg();
                    LogUtils.loge(model.getEncMsg());
                    try {
                        String platext = DESedeUtil.decrypt(encMsg, spUtils.getSecretKey(), spUtils.getSecretIv());
                        LogUtils.loge("解密后：" + platext);
                        SercetKeyOverdueModel loginModel = GsonTools.getObject(platext, SercetKeyOverdueModel.class);
                        if (loginModel.getHeader().getCode().equals("0000")) {
                            ((CommonView) mvpView).getDataSuccess(loginModel);
                        } else {
                            ((CommonView) mvpView).getDataFail(loginModel.getHeader().getDesc());
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                } else {
                    LogUtils.loge("首页：未加密:" + model.getMsgType());
                }
            }

            @Override
            public void onFailure(String msg) {
                ((CommonView) mvpView).getDataFail(msg);
            }

            @Override
            public void onFinish() {
                mvpView.hideLoading();
            }
        });
    }

    /**
     * 添加游戏
     */
    public void addgame(String encMsg, String signMsg, final String msgType, String secretKeyId) {
        mvpView.showLoading();
        addSubscription(apiStores.addgame(encMsg, signMsg, msgType, secretKeyId), new ApiCallback<EncryptedResponseModel>(mContext) {
            @Override
            public void onSuccess(EncryptedResponseModel model) {
                LogUtils.loge("MsgType:" + model.getMsgType());
                String msgType = model.getMsgType();
                if (msgType != null && msgType.equals("2")) {//加密
                    String encMsg = model.getEncMsg();
                    LogUtils.loge(model.getEncMsg());
                    try {
                        String platext = DESedeUtil.decrypt(encMsg, spUtils.getSecretKey(), spUtils.getSecretIv());
                        LogUtils.loge("解密后：" + platext);
                        SercetKeyOverdueModel loginModel = GsonTools.getObject(platext, SercetKeyOverdueModel.class);
                        if (loginModel.getHeader().getCode().equals("0000")) {
                            ((CommonView) mvpView).getDataSuccess(loginModel);
                        } else {
                            ((CommonView) mvpView).getDataFail(loginModel.getHeader().getDesc());
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                } else {
                    LogUtils.loge("首页：未加密:" + model.getMsgType());
                }
            }

            @Override
            public void onFailure(String msg) {
                ((CommonView) mvpView).getDataFail(msg);
            }

            @Override
            public void onFinish() {
                mvpView.hideLoading();
            }
        });
    }

    /**
     * 编辑保险
     */
    public void updateinsurance(String encMsg, String signMsg, final String msgType, String secretKeyId) {
        mvpView.showLoading();
        addSubscription(apiStores.updateinsurance(encMsg, signMsg, msgType, secretKeyId), new ApiCallback<EncryptedResponseModel>(mContext) {
            @Override
            public void onSuccess(EncryptedResponseModel model) {
                LogUtils.loge("MsgType:" + model.getMsgType());
                String msgType = model.getMsgType();
                if (msgType != null && msgType.equals("2")) {//加密
                    String encMsg = model.getEncMsg();
                    LogUtils.loge(model.getEncMsg());
                    try {
                        String platext = DESedeUtil.decrypt(encMsg, spUtils.getSecretKey(), spUtils.getSecretIv());
                        LogUtils.loge("解密后：" + platext);
                        SercetKeyOverdueModel loginModel = GsonTools.getObject(platext, SercetKeyOverdueModel.class);
                        if (loginModel.getHeader().getCode().equals("0000")) {
                            ((CommonView) mvpView).getDataSuccess(loginModel);
                        } else {
                            ((CommonView) mvpView).getDataFail(loginModel.getHeader().getDesc());
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                } else {
                    LogUtils.loge("首页：未加密:" + model.getMsgType());
                }
            }

            @Override
            public void onFailure(String msg) {
                ((CommonView) mvpView).getDataFail(msg);
            }

            @Override
            public void onFinish() {
                mvpView.hideLoading();
            }
        });
    }


    /**
     * 意见反馈
     */
    public void feedback(String encMsg, String signMsg, final String msgType, String secretKeyId) {
        mvpView.showLoading();
        addSubscription(apiStores.feedback(encMsg, signMsg, msgType, secretKeyId), new ApiCallback<EncryptedResponseModel>(mContext) {
            @Override
            public void onSuccess(EncryptedResponseModel model) {
                LogUtils.loge("MsgType:" + model.getMsgType());
                String msgType = model.getMsgType();
                if (msgType != null && msgType.equals("2")) {//加密
                    String encMsg = model.getEncMsg();
                    LogUtils.loge(model.getEncMsg());
                    try {
                        String platext = DESedeUtil.decrypt(encMsg, spUtils.getSecretKey(), spUtils.getSecretIv());
                        LogUtils.loge("解密后：" + platext);
                        SercetKeyOverdueModel loginModel = GsonTools.getObject(platext, SercetKeyOverdueModel.class);
                        if (loginModel.getHeader().getCode().equals("0000")) {
                            ((CommonView) mvpView).getDataSuccess(loginModel);
                        } else {
                            ((CommonView) mvpView).getDataFail(loginModel.getHeader().getDesc());
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                } else {
                    LogUtils.loge("首页：未加密:" + model.getMsgType());
                }
            }

            @Override
            public void onFailure(String msg) {
                ((CommonView) mvpView).getDataFail(msg);
            }

            @Override
            public void onFinish() {
                mvpView.hideLoading();
            }
        });
    }

    /**
     * 编辑预付卡
     */
    public void updateprepaidcard(String encMsg, String signMsg, final String msgType, String secretKeyId) {
        mvpView.showLoading();
        addSubscription(apiStores.updateprepaidcard(encMsg, signMsg, msgType, secretKeyId), new ApiCallback<EncryptedResponseModel>(mContext) {
            @Override
            public void onSuccess(EncryptedResponseModel model) {
                LogUtils.loge("MsgType:" + model.getMsgType());
                String msgType = model.getMsgType();
                if (msgType != null && msgType.equals("2")) {//加密
                    String encMsg = model.getEncMsg();
                    LogUtils.loge(model.getEncMsg());
                    try {
                        String platext = DESedeUtil.decrypt(encMsg, spUtils.getSecretKey(), spUtils.getSecretIv());
                        LogUtils.loge("解密后：" + platext);
                        SercetKeyOverdueModel loginModel = GsonTools.getObject(platext, SercetKeyOverdueModel.class);
                        if (loginModel.getHeader().getCode().equals("0000")) {
                            ((CommonView) mvpView).getDataSuccess(loginModel);
                        } else {
                            ((CommonView) mvpView).getDataFail(loginModel.getHeader().getDesc());
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                } else {
                    LogUtils.loge("首页：未加密:" + model.getMsgType());
                }
            }

            @Override
            public void onFailure(String msg) {
                ((CommonView) mvpView).getDataFail(msg);
            }

            @Override
            public void onFinish() {
                mvpView.hideLoading();
            }
        });
    }

    /**
     * 编辑借款
     */
    public void updateloanasset(String encMsg, String signMsg, final String msgType, String secretKeyId) {
//        mvpView.showLoading();
        addSubscription(apiStores.updateloanasset(encMsg, signMsg, msgType, secretKeyId), new ApiCallback<EncryptedResponseModel>(mContext) {
            @Override
            public void onSuccess(EncryptedResponseModel model) {
                LogUtils.loge("MsgType:" + model.getMsgType());
                String msgType = model.getMsgType();
                if (msgType != null && msgType.equals("2")) {//加密
                    String encMsg = model.getEncMsg();
                    LogUtils.loge(model.getEncMsg());
                    try {
                        String platext = DESedeUtil.decrypt(encMsg, spUtils.getSecretKey(), spUtils.getSecretIv());
                        LogUtils.loge("解密后：" + platext);
                        SercetKeyOverdueModel loginModel = GsonTools.getObject(platext, SercetKeyOverdueModel.class);
                        if (loginModel.getHeader().getCode().equals("0000")) {
                            ((OtherView) mvpView).onSuccess(loginModel);
                        } else {
                            ((OtherView) mvpView).onFail(loginModel.getHeader().getDesc());
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                } else {
                    LogUtils.loge("首页：未加密:" + model.getMsgType());
                }
            }

            @Override
            public void onFailure(String msg) {
                ((OtherView) mvpView).onFail(msg);
            }

            @Override
            public void onFinish() {
                mvpView.hideLoading();
            }
        });
    }

    /**
     * 编辑自定义分类
     */
    public void updatecustomasset(String encMsg, String signMsg, final String msgType, String secretKeyId) {
//        mvpView.showLoading();
        addSubscription(apiStores.updatecustomasset(encMsg, signMsg, msgType, secretKeyId), new ApiCallback<EncryptedResponseModel>(mContext) {
            @Override
            public void onSuccess(EncryptedResponseModel model) {
                LogUtils.loge("MsgType:" + model.getMsgType());
                String msgType = model.getMsgType();
                if (msgType != null && msgType.equals("2")) {//加密
                    String encMsg = model.getEncMsg();
                    LogUtils.loge(model.getEncMsg());
                    try {
                        String platext = DESedeUtil.decrypt(encMsg, spUtils.getSecretKey(), spUtils.getSecretIv());
                        LogUtils.loge("解密后：" + platext);
                        SercetKeyOverdueModel loginModel = GsonTools.getObject(platext, SercetKeyOverdueModel.class);
                        if (loginModel.getHeader().getCode().equals("0000")) {
                            ((CommonView) mvpView).getDataSuccess(loginModel);
                        } else {
                            ((CommonView) mvpView).getDataFail(loginModel.getHeader().getDesc());
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                } else {
                    LogUtils.loge("首页：未加密:" + model.getMsgType());
                }
            }

            @Override
            public void onFailure(String msg) {
                ((CommonView) mvpView).getDataFail(msg);
            }

            @Override
            public void onFinish() {
                mvpView.hideLoading();
            }
        });
    }

    /**
     * 编辑游戏
     */
    public void updategame(String encMsg, String signMsg, final String msgType, String secretKeyId) {
        mvpView.showLoading();
        addSubscription(apiStores.updategame(encMsg, signMsg, msgType, secretKeyId), new ApiCallback<EncryptedResponseModel>(mContext) {
            @Override
            public void onSuccess(EncryptedResponseModel model) {
                LogUtils.loge("MsgType:" + model.getMsgType());
                String msgType = model.getMsgType();
                if (msgType != null && msgType.equals("2")) {//加密
                    String encMsg = model.getEncMsg();
                    LogUtils.loge(model.getEncMsg());
                    try {
                        String platext = DESedeUtil.decrypt(encMsg, spUtils.getSecretKey(), spUtils.getSecretIv());
                        LogUtils.loge("解密后：" + platext);
                        SercetKeyOverdueModel loginModel = GsonTools.getObject(platext, SercetKeyOverdueModel.class);
                        if (loginModel.getHeader().getCode().equals("0000")) {
                            ((CommonView) mvpView).getDataSuccess(loginModel);
                        } else {
                            ((CommonView) mvpView).getDataFail(loginModel.getHeader().getDesc());
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                } else {
                    LogUtils.loge("首页：未加密:" + model.getMsgType());
                }
            }

            @Override
            public void onFailure(String msg) {
                ((CommonView) mvpView).getDataFail(msg);
            }

            @Override
            public void onFinish() {
                mvpView.hideLoading();
            }
        });
    }

    /**
     * 编辑预付卡金额（新增操作记录）
     */
    public void updateprepaidcardsum(String encMsg, String signMsg, final String msgType, String secretKeyId) {
        mvpView.showLoading();
        addSubscription(apiStores.updateprepaidcardsum(encMsg, signMsg, msgType, secretKeyId), new ApiCallback<EncryptedResponseModel>(mContext) {
            @Override
            public void onSuccess(EncryptedResponseModel model) {
//                LogUtils.loge("MsgType:" + model.getMsgType());
                String msgType = model.getMsgType();
                if (msgType != null && msgType.equals("2")) {//加密
                    String encMsg = model.getEncMsg();
//                    LogUtils.loge(model.getEncMsg());
                    try {
                        String platext = DESedeUtil.decrypt(encMsg, spUtils.getSecretKey(), spUtils.getSecretIv());
                        LogUtils.logd("解密后：" + platext);
                        SercetKeyOverdueModel loginModel = GsonTools.getObject(platext, SercetKeyOverdueModel.class);
                        if (loginModel.getHeader().getCode().equals("0000")) {
                            ((OtherView) mvpView).onSuccess(loginModel);
                        } else {
                            ((OtherView) mvpView).onFail(loginModel.getHeader().getDesc());
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                } else {
                    LogUtils.loge("首页：未加密:" + model.getMsgType());
                }
            }

            @Override
            public void onFailure(String msg) {
                ((OtherView) mvpView).onFail(msg);
            }

            @Override
            public void onFinish() {
                mvpView.hideLoading();
            }
        });
    }

    /**
     * 保险列表
     */
    public void getinsurancelist(String encMsg, String signMsg, final String msgType, String secretKeyId) {
//        mvpView.showLoading();
        addSubscription(apiStores.getinsurancelist(encMsg, signMsg, msgType, secretKeyId), new ApiCallback<EncryptedResponseModel>(mContext) {
            @Override
            public void onSuccess(EncryptedResponseModel model) {
                LogUtils.loge("MsgType:" + model.getMsgType());
                String msgType = model.getMsgType();
                if (msgType != null && msgType.equals("2")) {//加密
                    String encMsg = model.getEncMsg();
                    LogUtils.loge(model.getEncMsg());
                    try {
                        String platext = DESedeUtil.decrypt(encMsg, spUtils.getSecretKey(), spUtils.getSecretIv());
                        LogUtils.loge("解密后：" + platext);
                        InsuranceListModel loginModel = GsonTools.getObject(platext, InsuranceListModel.class);
                        if (loginModel.getHeader().getCode().equals("0000")) {
                            ((CommonView) mvpView).getDataSuccess(loginModel);
                        } else {
                            ((CommonView) mvpView).getDataFail(loginModel.getHeader().getDesc());
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                } else {
                    LogUtils.loge("首页：未加密:" + model.getMsgType());

                }
            }

            @Override
            public void onFailure(String msg) {
                ((CommonView) mvpView).getDataFail(msg);
            }

            @Override
            public void onFinish() {
                mvpView.hideLoading();
            }
        });
    }

    /**
     * 欠款详情页
     */
    public void getarrearsasset(String encMsg, String signMsg, final String msgType, String secretKeyId) {
//        mvpView.showLoading();
        addSubscription(apiStores.getarrearsasset(encMsg, signMsg, msgType, secretKeyId), new ApiCallback<EncryptedResponseModel>(mContext) {
            @Override
            public void onSuccess(EncryptedResponseModel model) {
                LogUtils.loge("MsgType:" + model.getMsgType());
                String msgType = model.getMsgType();
                if (msgType != null && msgType.equals("2")) {//加密
                    String encMsg = model.getEncMsg();
                    LogUtils.loge(model.getEncMsg());
                    try {
                        String platext = DESedeUtil.decrypt(encMsg, spUtils.getSecretKey(), spUtils.getSecretIv());
                        LogUtils.loge("解密后：" + platext);
                        ArrearsDetailModel loginModel = GsonTools.getObject(platext, ArrearsDetailModel.class);
                        if (loginModel.getHeader().getCode().equals("0000")) {
                            ((CommonView) mvpView).getDataSuccess(loginModel);
                        } else {
                            ((CommonView) mvpView).getDataFail(loginModel.getHeader().getDesc());
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                } else {
                    LogUtils.loge("首页：未加密:" + model.getMsgType());

                }
            }

            @Override
            public void onFailure(String msg) {
                ((CommonView) mvpView).getDataFail(msg);
            }

            @Override
            public void onFinish() {
                mvpView.hideLoading();
            }
        });
    }

    /**
     * 借款详情页
     */
    public void getloanasset(String encMsg, String signMsg, final String msgType, String secretKeyId) {
//        mvpView.showLoading();
        addSubscription(apiStores.getloanasset(encMsg, signMsg, msgType, secretKeyId), new ApiCallback<EncryptedResponseModel>(mContext) {
            @Override
            public void onSuccess(EncryptedResponseModel model) {
                LogUtils.loge("MsgType:" + model.getMsgType());
                String msgType = model.getMsgType();
                if (msgType != null && msgType.equals("2")) {//加密
                    String encMsg = model.getEncMsg();
                    LogUtils.loge(model.getEncMsg());
                    try {
                        String platext = DESedeUtil.decrypt(encMsg, spUtils.getSecretKey(), spUtils.getSecretIv());
                        LogUtils.loge("解密后：" + platext);
                        LoandetailModel loginModel = GsonTools.getObject(platext, LoandetailModel.class);
                        if (loginModel.getHeader().getCode().equals("0000")) {
                            ((CommonView) mvpView).getDataSuccess(loginModel);
                        } else {
                            ((CommonView) mvpView).getDataFail(loginModel.getHeader().getDesc());
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                } else {
                    LogUtils.loge("首页：未加密:" + model.getMsgType());

                }
            }

            @Override
            public void onFailure(String msg) {
                ((CommonView) mvpView).getDataFail(msg);
            }

            @Override
            public void onFinish() {
                mvpView.hideLoading();
            }
        });
    }


    /**
     * 基金详情页
     */
    public void getfund(String encMsg, String signMsg, final String msgType, String secretKeyId) {
        mvpView.showLoading();
        addSubscription(apiStores.getfund(encMsg, signMsg, msgType, secretKeyId), new ApiCallback<EncryptedResponseModel>(mContext) {
            @Override
            public void onSuccess(EncryptedResponseModel model) {
                LogUtils.loge("MsgType:" + model.getMsgType());
                String msgType = model.getMsgType();
                if (msgType != null && msgType.equals("2")) {//加密
                    String encMsg = model.getEncMsg();
                    LogUtils.loge(model.getEncMsg());
                    try {
                        String platext = DESedeUtil.decrypt(encMsg, spUtils.getSecretKey(), spUtils.getSecretIv());
                        LogUtils.loge("解密后：" + platext);
                        FundDetailModel loginModel = GsonTools.getObject(platext, FundDetailModel.class);
                        if (loginModel.getHeader().getCode().equals("0000")) {
                            ((CommonView) mvpView).getDataSuccess(loginModel);
                        } else {
                            ((CommonView) mvpView).getDataFail(loginModel.getHeader().getDesc());
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                } else {
                    LogUtils.loge("首页：未加密:" + model.getMsgType());

                }
            }

            @Override
            public void onFailure(String msg) {
                ((CommonView) mvpView).getDataFail(msg);
            }

            @Override
            public void onFinish() {
                mvpView.hideLoading();
            }
        });
    }

    /**
     * 私募详情页
     */
    public void getsmfund(String encMsg, String signMsg, final String msgType, String secretKeyId) {
        mvpView.showLoading();
        addSubscription(apiStores.getsmfund(encMsg, signMsg, msgType, secretKeyId), new ApiCallback<EncryptedResponseModel>(mContext) {
            @Override
            public void onSuccess(EncryptedResponseModel model) {
                LogUtils.loge("MsgType:" + model.getMsgType());
                String msgType = model.getMsgType();
                if (msgType != null && msgType.equals("2")) {//加密
                    String encMsg = model.getEncMsg();
                    LogUtils.loge(model.getEncMsg());
                    try {
                        String platext = DESedeUtil.decrypt(encMsg, spUtils.getSecretKey(), spUtils.getSecretIv());
                        LogUtils.loge("解密后：" + platext);
                        FundDetailModel loginModel = GsonTools.getObject(platext, FundDetailModel.class);
                        if (loginModel.getHeader().getCode().equals("0000")) {
                            ((OtherView) mvpView).onSuccess(loginModel);
                        } else {
                            ((OtherView) mvpView).onFail(loginModel.getHeader().getDesc());
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                } else {
                    LogUtils.loge("首页：未加密:" + model.getMsgType());

                }
            }

            @Override
            public void onFailure(String msg) {
                ((OtherView) mvpView).onFail(msg);
            }

            @Override
            public void onFinish() {
                mvpView.hideLoading();
            }
        });
    }

    /**
     * 股票详情页
     */
    public void getstock(String encMsg, String signMsg, final String msgType, String secretKeyId) {
        mvpView.showLoading();
        addSubscription(apiStores.getstock(encMsg, signMsg, msgType, secretKeyId), new ApiCallback<EncryptedResponseModel>(mContext) {
            @Override
            public void onSuccess(EncryptedResponseModel model) {
                LogUtils.loge("MsgType:" + model.getMsgType());
                String msgType = model.getMsgType();
                if (msgType != null && msgType.equals("2")) {//加密
                    String encMsg = model.getEncMsg();
                    LogUtils.loge(model.getEncMsg());
                    try {
                        String platext = DESedeUtil.decrypt(encMsg, spUtils.getSecretKey(), spUtils.getSecretIv());
                        LogUtils.loge("解密后：" + platext);
                        FundDetailModel loginModel = GsonTools.getObject(platext, FundDetailModel.class);
                        if (loginModel.getHeader().getCode().equals("0000")) {
                            ((ThirdView) mvpView).ThirdSuccess(loginModel);
                        } else {
                            ((ThirdView) mvpView).ThirdFail(loginModel.getHeader().getDesc());
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                } else {
                    LogUtils.loge("首页：未加密:" + model.getMsgType());

                }
            }

            @Override
            public void onFailure(String msg) {
                ((ThirdView) mvpView).ThirdFail(msg);
            }

            @Override
            public void onFinish() {
                mvpView.hideLoading();
            }
        });
    }


    /**
     * 固定收益详情页
     */
    public void gettrust(String encMsg, String signMsg, final String msgType, String secretKeyId) {
//        mvpView.showLoading();
        addSubscription(apiStores.gettrust(encMsg, signMsg, msgType, secretKeyId), new ApiCallback<EncryptedResponseModel>(mContext) {
            @Override
            public void onSuccess(EncryptedResponseModel model) {
                LogUtils.loge("MsgType:" + model.getMsgType());
                String msgType = model.getMsgType();
                if (msgType != null && msgType.equals("2")) {//加密
                    String encMsg = model.getEncMsg();
                    LogUtils.loge(model.getEncMsg());
                    try {
                        String platext = DESedeUtil.decrypt(encMsg, spUtils.getSecretKey(), spUtils.getSecretIv());
                        LogUtils.loge("解密后：" + platext);
                        FixedIncomeDetailModel loginModel = GsonTools.getObject(platext, FixedIncomeDetailModel.class);
                        if (loginModel.getHeader().getCode().equals("0000")) {
                            ((CommonView) mvpView).getDataSuccess(loginModel);
                        } else {
                            ((CommonView) mvpView).getDataFail(loginModel.getHeader().getDesc());
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                } else {
                    LogUtils.loge("首页：未加密:" + model.getMsgType());

                }
            }

            @Override
            public void onFailure(String msg) {
                ((CommonView) mvpView).getDataFail(msg);
            }

            @Override
            public void onFinish() {
                mvpView.hideLoading();
            }
        });
    }

    /**
     * domain详情页
     */
    public void getdomain(String encMsg, String signMsg, final String msgType, String secretKeyId) {
//        mvpView.showLoading();
        addSubscription(apiStores.getdomain(encMsg, signMsg, msgType, secretKeyId), new ApiCallback<EncryptedResponseModel>(mContext) {
            @Override
            public void onSuccess(EncryptedResponseModel model) {
                LogUtils.loge("MsgType:" + model.getMsgType());
                String msgType = model.getMsgType();
                if (msgType != null && msgType.equals("2")) {//加密
                    String encMsg = model.getEncMsg();
                    LogUtils.loge(model.getEncMsg());
                    try {
                        String platext = DESedeUtil.decrypt(encMsg, spUtils.getSecretKey(), spUtils.getSecretIv());
                        LogUtils.loge("解密后：" + platext);
                        DominDetailModel loginModel = GsonTools.getObject(platext, DominDetailModel.class);
                        if (loginModel.getHeader().getCode().equals("0000")) {
                            ((CommonView) mvpView).getDataSuccess(loginModel);
                        } else {
                            ((CommonView) mvpView).getDataFail(loginModel.getHeader().getDesc());
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                } else {
                    LogUtils.loge("首页：未加密:" + model.getMsgType());

                }
            }

            @Override
            public void onFailure(String msg) {
                ((CommonView) mvpView).getDataFail(msg);
            }

            @Override
            public void onFinish() {
                mvpView.hideLoading();
            }
        });
    }

    /**
     * 活期理财列表
     */
    public void getcurfinlist(String encMsg, String signMsg, final String msgType, String secretKeyId) {
//        mvpView.showLoading();
        addSubscription(apiStores.getcurfinlist(encMsg, signMsg, msgType, secretKeyId), new ApiCallback<EncryptedResponseModel>(mContext) {
            @Override
            public void onSuccess(EncryptedResponseModel model) {
                LogUtils.loge("MsgType:" + model.getMsgType());
                String msgType = model.getMsgType();
                if (msgType != null && msgType.equals("2")) {//加密
                    String encMsg = model.getEncMsg();
                    LogUtils.loge(model.getEncMsg());
                    try {
                        String platext = DESedeUtil.decrypt(encMsg, spUtils.getSecretKey(), spUtils.getSecretIv());
                        LogUtils.loge("解密后：" + platext);
                        CurrentFinancialListModel loginModel = GsonTools.getObject(platext, CurrentFinancialListModel.class);
                        if (loginModel.getHeader().getCode().equals("0000")) {
                            ((CommonView) mvpView).getDataSuccess(loginModel);
                        } else {
                            ((CommonView) mvpView).getDataFail(loginModel.getHeader().getDesc());
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                } else {
                    LogUtils.loge("首页：未加密:" + model.getMsgType());

                }
            }

            @Override
            public void onFailure(String msg) {
                ((CommonView) mvpView).getDataFail(msg);
            }

            @Override
            public void onFinish() {
                mvpView.hideLoading();
            }
        });
    }

    /**
     * 活期理财详情
     */
    public void getcurfin(String encMsg, String signMsg, final String msgType, String secretKeyId) {
//        mvpView.showLoading();
        addSubscription(apiStores.getcurfin(encMsg, signMsg, msgType, secretKeyId), new ApiCallback<EncryptedResponseModel>(mContext) {
            @Override
            public void onSuccess(EncryptedResponseModel model) {
                LogUtils.loge("MsgType:" + model.getMsgType());
                String msgType = model.getMsgType();
                if (msgType != null && msgType.equals("2")) {//加密
                    String encMsg = model.getEncMsg();
                    LogUtils.loge(model.getEncMsg());
                    try {
                        String platext = DESedeUtil.decrypt(encMsg, spUtils.getSecretKey(), spUtils.getSecretIv());
                        LogUtils.loge("解密后：" + platext);
                        CurrentFinancialDetailModel loginModel = GsonTools.getObject(platext, CurrentFinancialDetailModel.class);
                        if (loginModel.getHeader().getCode().equals("0000")) {
                            ((CommonView) mvpView).getDataSuccess(loginModel);
                        } else {
                            ((CommonView) mvpView).getDataFail(loginModel.getHeader().getDesc());
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                } else {
                    LogUtils.loge("首页：未加密:" + model.getMsgType());
                }
            }

            @Override
            public void onFailure(String msg) {
                ((CommonView) mvpView).getDataFail(msg);
            }

            @Override
            public void onFinish() {
                mvpView.hideLoading();
            }
        });
    }


    /**
     * 预付卡详情
     */
    public void getprepaidcard(String encMsg, String signMsg, final String msgType, String secretKeyId) {
//        mvpView.showLoading();
        addSubscription(apiStores.getprepaidcard(encMsg, signMsg, msgType, secretKeyId), new ApiCallback<EncryptedResponseModel>(mContext) {
            @Override
            public void onSuccess(EncryptedResponseModel model) {
                LogUtils.loge("MsgType:" + model.getMsgType());
                String msgType = model.getMsgType();
                if (msgType != null && msgType.equals("2")) {//加密
                    String encMsg = model.getEncMsg();
                    LogUtils.loge(model.getEncMsg());
                    try {
                        String platext = DESedeUtil.decrypt(encMsg, spUtils.getSecretKey(), spUtils.getSecretIv());
                        LogUtils.loge("解密后：" + platext);
                        PrepaiddetailModel loginModel = GsonTools.getObject(platext, PrepaiddetailModel.class);
                        if (loginModel.getHeader().getCode().equals("0000")) {
                            ((CommonView) mvpView).getDataSuccess(loginModel);
                        } else {
                            ((CommonView) mvpView).getDataFail(loginModel.getHeader().getDesc());
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                } else {
                    LogUtils.loge("首页：未加密:" + model.getMsgType());
                }
            }

            @Override
            public void onFailure(String msg) {
                ((CommonView) mvpView).getDataFail(msg);
            }

            @Override
            public void onFinish() {
                mvpView.hideLoading();
            }
        });
    }


    /**
     * 添加活期理财
     */
    public void addcurfin(String encMsg, String signMsg, final String msgType, String secretKeyId) {
        mvpView.showLoading();
        addSubscription(apiStores.addcurfin(encMsg, signMsg, msgType, secretKeyId), new ApiCallback<EncryptedResponseModel>(mContext) {
            @Override
            public void onSuccess(EncryptedResponseModel model) {
                LogUtils.loge("MsgType:" + model.getMsgType());
                String msgType = model.getMsgType();
                if (msgType != null && msgType.equals("2")) {//加密
                    String encMsg = model.getEncMsg();
                    LogUtils.loge(model.getEncMsg());
                    try {
                        String platext = DESedeUtil.decrypt(encMsg, spUtils.getSecretKey(), spUtils.getSecretIv());
                        LogUtils.loge("解密后：" + platext);
                        SercetKeyOverdueModel loginModel = GsonTools.getObject(platext, SercetKeyOverdueModel.class);
                        if (loginModel.getHeader().getCode().equals("0000")) {
                            ((CommonView) mvpView).getDataSuccess(loginModel);
                        } else {
                            ((CommonView) mvpView).getDataFail(loginModel.getHeader().getDesc());
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                } else {
                    LogUtils.loge("首页：未加密:" + model.getMsgType());
                }
            }

            @Override
            public void onFailure(String msg) {
                ((CommonView) mvpView).getDataFail(msg);
            }

            @Override
            public void onFinish() {
                mvpView.hideLoading();
            }
        });
    }

    /**
     * 添加互联网账号
     */
    public void addwebaccount(String encMsg, String signMsg, final String msgType, String secretKeyId) {
        mvpView.showLoading();
        addSubscription(apiStores.addwebaccount(encMsg, signMsg, msgType, secretKeyId), new ApiCallback<EncryptedResponseModel>(mContext) {
            @Override
            public void onSuccess(EncryptedResponseModel model) {
                LogUtils.loge("MsgType:" + model.getMsgType());
                String msgType = model.getMsgType();
                if (msgType != null && msgType.equals("2")) {//加密
                    String encMsg = model.getEncMsg();
                    LogUtils.loge(model.getEncMsg());
                    try {
                        String platext = DESedeUtil.decrypt(encMsg, spUtils.getSecretKey(), spUtils.getSecretIv());
                        LogUtils.loge("解密后：" + platext);
                        SercetKeyOverdueModel loginModel = GsonTools.getObject(platext, SercetKeyOverdueModel.class);
                        if (loginModel.getHeader().getCode().equals("0000")) {
                            ((CommonView) mvpView).getDataSuccess(loginModel);
                        } else {
                            ((CommonView) mvpView).getDataFail(loginModel.getHeader().getDesc());
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                } else {
                    LogUtils.loge("首页：未加密:" + model.getMsgType());
                }
            }

            @Override
            public void onFailure(String msg) {
                ((CommonView) mvpView).getDataFail(msg);
            }

            @Override
            public void onFinish() {
                mvpView.hideLoading();
            }
        });
    }

    /**
     * 添加固定收益
     */
    public void addtrust(String encMsg, String signMsg, final String msgType, String secretKeyId) {
        mvpView.showLoading();
        addSubscription(apiStores.addtrust(encMsg, signMsg, msgType, secretKeyId), new ApiCallback<EncryptedResponseModel>(mContext) {
            @Override
            public void onSuccess(EncryptedResponseModel model) {
                LogUtils.loge("MsgType:" + model.getMsgType());
                String msgType = model.getMsgType();
                if (msgType != null && msgType.equals("2")) {//加密
                    String encMsg = model.getEncMsg();
                    LogUtils.loge(model.getEncMsg());
                    try {
                        String platext = DESedeUtil.decrypt(encMsg, spUtils.getSecretKey(), spUtils.getSecretIv());
                        LogUtils.loge("解密后：" + platext);
                        SercetKeyOverdueModel loginModel = GsonTools.getObject(platext, SercetKeyOverdueModel.class);
                        if (loginModel.getHeader().getCode().equals("0000")) {
                            ((CommonView) mvpView).getDataSuccess(loginModel);
                        } else {
                            ((CommonView) mvpView).getDataFail(loginModel.getHeader().getDesc());
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                } else {
                    LogUtils.loge("首页：未加密:" + model.getMsgType());
                }
            }

            @Override
            public void onFailure(String msg) {
                ((CommonView) mvpView).getDataFail(msg);
            }

            @Override
            public void onFinish() {
                mvpView.hideLoading();
            }
        });
    }

    /**
     * 添加银行卡
     */
    public void addbankcard(String encMsg, String signMsg, final String msgType, String secretKeyId) {
        mvpView.showLoading();
        addSubscription(apiStores.addbankcard(encMsg, signMsg, msgType, secretKeyId), new ApiCallback<EncryptedResponseModel>(mContext) {
            @Override
            public void onSuccess(EncryptedResponseModel model) {
                LogUtils.loge("MsgType:" + model.getMsgType());
                String msgType = model.getMsgType();
                if (msgType != null && msgType.equals("2")) {//加密
                    String encMsg = model.getEncMsg();
                    LogUtils.loge(model.getEncMsg());
                    try {
                        String platext = DESedeUtil.decrypt(encMsg, spUtils.getSecretKey(), spUtils.getSecretIv());
                        LogUtils.loge("解密后：" + platext);
                        SercetKeyOverdueModel loginModel = GsonTools.getObject(platext, SercetKeyOverdueModel.class);
                        if (loginModel.getHeader().getCode().equals("0000")) {
                            ((CommonView) mvpView).getDataSuccess(loginModel);
                        } else {
                            ((CommonView) mvpView).getDataFail(loginModel.getHeader().getDesc());
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                } else {
                    LogUtils.loge("首页：未加密:" + model.getMsgType());
                }
            }

            @Override
            public void onFailure(String msg) {
                ((CommonView) mvpView).getDataFail(msg);
            }

            @Override
            public void onFinish() {
                mvpView.hideLoading();
            }
        });
    }

    /**
     * 更新银行卡
     */
    public void updatebankcard(String encMsg, String signMsg, final String msgType, String secretKeyId) {
        mvpView.showLoading();
        addSubscription(apiStores.updatebankcard(encMsg, signMsg, msgType, secretKeyId), new ApiCallback<EncryptedResponseModel>(mContext) {
            @Override
            public void onSuccess(EncryptedResponseModel model) {
                LogUtils.loge("MsgType:" + model.getMsgType());
                String msgType = model.getMsgType();
                if (msgType != null && msgType.equals("2")) {//加密
                    String encMsg = model.getEncMsg();
                    LogUtils.loge(model.getEncMsg());
                    try {
                        String platext = DESedeUtil.decrypt(encMsg, spUtils.getSecretKey(), spUtils.getSecretIv());
                        LogUtils.loge("解密后：" + platext);
                        SercetKeyOverdueModel loginModel = GsonTools.getObject(platext, SercetKeyOverdueModel.class);
                        if (loginModel.getHeader().getCode().equals("0000")) {
                            ((CommonView) mvpView).getDataSuccess(loginModel);
                        } else {
                            ((CommonView) mvpView).getDataFail(loginModel.getHeader().getDesc());
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                } else {
                    LogUtils.loge("首页：未加密:" + model.getMsgType());
                }
            }

            @Override
            public void onFailure(String msg) {
                ((CommonView) mvpView).getDataFail(msg);
            }

            @Override
            public void onFinish() {
                mvpView.hideLoading();
            }
        });
    }


    /**
     * 获取银行卡列表
     *
     * @param encMsg
     * @param signMsg
     * @param msgType
     * @param secretKeyId
     */
    public void getbankcardlist(String encMsg, String signMsg, final String msgType, String secretKeyId) {
//        mvpView.showLoading();
        addSubscription(apiStores.getbankcardlist(encMsg, signMsg, msgType, secretKeyId), new ApiCallback<EncryptedResponseModel>(mContext) {
            @Override
            public void onSuccess(EncryptedResponseModel model) {
                LogUtils.loge("MsgType:" + model.getMsgType());
                String msgType = model.getMsgType();
                if (msgType != null && msgType.equals("2")) {//加密
                    String encMsg = model.getEncMsg();
                    LogUtils.loge(model.getEncMsg());
                    try {
                        String platext = DESedeUtil.decrypt(encMsg, spUtils.getSecretKey(), spUtils.getSecretIv());
                        LogUtils.loge("解密后：" + platext);
                        BankcardListModel loginModel = GsonTools.getObject(platext, BankcardListModel.class);
                        if (loginModel.getHeader().getCode().equals("0000")) {
                            ((CommonView) mvpView).getDataSuccess(loginModel);
                        } else {
                            ((CommonView) mvpView).getDataFail(loginModel.getHeader().getDesc());
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                } else {
                    LogUtils.loge("首页：未加密:" + model.getMsgType());
                }
            }

            @Override
            public void onFailure(String msg) {
                ((CommonView) mvpView).getDataFail(msg);
            }

            @Override
            public void onFinish() {
                mvpView.hideLoading();
            }
        });
    }


    /**
     * 账户首页接口
     */
    public void account(String encMsg, String signMsg, final String msgType, String secretKeyId) {
        mvpView.showLoading();
        addSubscription(apiStores.account(encMsg, signMsg, msgType, secretKeyId), new ApiCallback<EncryptedResponseModel>(mContext) {
            @Override
            public void onSuccess(EncryptedResponseModel model) {
                LogUtils.loge("MsgType:" + model.getMsgType());
                String msgType = model.getMsgType();
                if (msgType != null && msgType.equals("2")) {//加密
                    String encMsg = model.getEncMsg();
                    LogUtils.loge(model.getEncMsg());
                    try {
                        String platext = DESedeUtil.decrypt(encMsg, spUtils.getSecretKey(), spUtils.getSecretIv());
                        LogUtils.loge("解密后：----------账户首页接口----------" + platext);
                        AccountModel accountModel = GsonTools.getObject(platext, AccountModel.class);
                        if (accountModel.getHeader().getCode().equals("0000")) {
                            ((CommonView) mvpView).getDataSuccess(accountModel);
                        } else {
                            ((CommonView) mvpView).getDataFail(accountModel.getHeader().getDesc());
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                } else {
                    LogUtils.loge("首页：未加密:" + model.getMsgType());
                }
            }

            @Override
            public void onFailure(String msg) {
                ((CommonView) mvpView).getDataFail(msg);
            }

            @Override
            public void onFinish() {
                mvpView.hideLoading();
            }
        });
    }

    /**
     * 财富卡列表接口
     */
    public void getCaifucardlist(String encMsg, String signMsg, final String msgType, String secretKeyId) {
        mvpView.showLoading();
        addSubscription(apiStores.getCaifucardlist(encMsg, signMsg, msgType, secretKeyId), new ApiCallback<EncryptedResponseModel>(mContext) {
            @Override
            public void onSuccess(EncryptedResponseModel model) {
                LogUtils.loge("MsgType:" + model.getMsgType());
                String msgType = model.getMsgType();
                if (msgType != null && msgType.equals("2")) {//加密
                    String encMsg = model.getEncMsg();
                    LogUtils.loge(model.getEncMsg());
                    try {
                        String platext = DESedeUtil.decrypt(encMsg, spUtils.getSecretKey(), spUtils.getSecretIv());
                        LogUtils.loge("解密后：财富卡列表接口:" + platext);
                        CaifuCardModel accountModel = GsonTools.getObject(platext, CaifuCardModel.class);
                        if (accountModel.getHeader().getCode().equals("0000")) {
                            ((CommonView) mvpView).getDataSuccess(accountModel);
                        } else {
                            ((CommonView) mvpView).getDataFail(accountModel.getHeader().getDesc());
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                } else {
                    LogUtils.loge("首页：未加密:" + model.getMsgType());
                }
            }

            @Override
            public void onFailure(String msg) {
                ((CommonView) mvpView).getDataFail(msg);
            }

            @Override
            public void onFinish() {
                mvpView.hideLoading();
            }
        });
    }


    /**
     * 我的首页接口
     *
     * @param encMsg
     * @param signMsg
     * @param msgType
     * @param secretKeyId
     */
    public void index(String encMsg, String signMsg, final String msgType, String secretKeyId) {
//        mvpView.showLoading();
        addSubscription(apiStores.index(encMsg, signMsg, msgType, secretKeyId), new ApiCallback<EncryptedResponseModel>(mContext) {
            @Override
            public void onSuccess(EncryptedResponseModel model) {
                LogUtils.loge("MsgType:" + model.getMsgType());
                String msgType = model.getMsgType();
                if (msgType != null && msgType.equals("2")) {//加密
                    String encMsg = model.getEncMsg();
                    LogUtils.loge(model.getEncMsg());
                    try {
                        String platext = DESedeUtil.decrypt(encMsg, spUtils.getSecretKey(), spUtils.getSecretIv());
                        LogUtils.loge("解密后：--------我的首页接口------------" + platext);
                        MyModel myModel = GsonTools.getObject(platext, MyModel.class);
                        if (myModel.getHeader().getCode().equals("0000")) {
                            ((OtherView) mvpView).onSuccess(myModel);
                        } else {
                            ((OtherView) mvpView).onFail(myModel.getHeader().getDesc());
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                } else {
                    LogUtils.loge("首页：未加密:" + model.getMsgType());
                }
            }

            @Override
            public void onFailure(String msg) {
                ((OtherView) mvpView).onFail(msg);
            }

            @Override
            public void onFinish() {
                mvpView.hideLoading();
            }
        });
    }


    /**
     * 进入财商首页
     *
     * @param encMsg
     * @param signMsg
     * @param msgType
     * @param secretKeyId
     */
    public void fqindex(String encMsg, String signMsg, final String msgType, String secretKeyId) {
        mvpView.showLoading();
        addSubscription(apiStores.fqindex(encMsg, signMsg, msgType, secretKeyId), new ApiCallback<EncryptedResponseModel>(mContext) {
            @Override
            public void onSuccess(EncryptedResponseModel model) {
                LogUtils.loge("MsgType:-------进入财商首页-------" + model);
//                LogUtils.loge("MsgType:"+model.getMsgType());
//                String msgType = model.getMsgType();
//                if(msgType != null && msgType.equals("2")){//加密
//                    String encMsg = model.getEncMsg();
//                    LogUtils.loge(model.getEncMsg());
//                    try {
//                        String platext = DESedeUtil.decrypt(encMsg, spUtils.getSecretKey(), spUtils.getSecretIv());
//                        LogUtils.loge("解密后-------进入财商首页---------："+platext);
//                        BankcardListModel loginModel = GsonTools.getObject(platext, BankcardListModel.class);
//                        if(loginModel.getHeader().getCode().equals("0000")){
//                            ((CommonView)mvpView).getDataSuccess(loginModel);
//                        }else{
//                            ((CommonView)mvpView).getDataFail(loginModel.getHeader().getDesc());
//                        }
//                    } catch (Exception e) {
//                        e.printStackTrace();
//                    }
//                }else{
//                    LogUtils.loge("首页：未加密:"+model.getMsgType());
//                }
            }

            @Override
            public void onFailure(String msg) {
                ((CommonView) mvpView).getDataFail(msg);
            }

            @Override
            public void onFinish() {
                mvpView.hideLoading();
            }
        });
    }


    /**
     * 邮箱修改
     *
     * @param encMsg
     * @param signMsg
     * @param msgType
     * @param secretKeyId
     */
    public void modifyemail(String encMsg, String signMsg, final String msgType, String secretKeyId) {
        mvpView.showLoading();
        addSubscription(apiStores.modifyemail(encMsg, signMsg, msgType, secretKeyId), new ApiCallback<EncryptedResponseModel>(mContext) {
            @Override
            public void onSuccess(EncryptedResponseModel model) {
                LogUtils.loge("MsgType:" + model.getMsgType());
                String msgType = model.getMsgType();
                if (msgType != null && msgType.equals("2")) {//加密
                    String encMsg = model.getEncMsg();
                    LogUtils.loge(model.getEncMsg());
                    try {
                        String platext = DESedeUtil.decrypt(encMsg, spUtils.getSecretKey(), spUtils.getSecretIv());
                        LogUtils.loge("解密后：----------邮箱修改-----------" + platext);
                        SercetKeyOverdueModel sercetKeyOverdueModel = GsonTools.getObject(platext, SercetKeyOverdueModel.class);
                        if (sercetKeyOverdueModel != null && sercetKeyOverdueModel.getHeader().getCode().equals("0000")) {
                            ((ChangePhoneView) mvpView).changePhoneSuccess(sercetKeyOverdueModel);
                        } else {
                            ((ChangePhoneView) mvpView).changePhoneFail(sercetKeyOverdueModel.getHeader().getDesc());
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                } else {
                    LogUtils.loge("首页：未加密:" + model.getMsgType());
                }
            }

            @Override
            public void onFailure(String msg) {
                ((ChangePhoneView) mvpView).changePhoneFail(msg);
            }

            @Override
            public void onFinish() {
                mvpView.hideLoading();
            }
        });
    }


    /**
     * 图形验证码
     *
     * @param encMsg
     * @param signMsg
     * @param msgType
     * @param secretKeyId
     */
    public void getpiccode(String encMsg, String signMsg, final String msgType, String secretKeyId) {
//        mvpView.showLoading();
        addSubscription(apiStores.getpiccode(encMsg, signMsg, msgType, secretKeyId), new ApiCallback<EncryptedResponseModel>(mContext) {
            @Override
            public void onSuccess(EncryptedResponseModel model) {
                LogUtils.loge("MsgType:" + model.getMsgType());
                String msgType = model.getMsgType();
                if (msgType != null && msgType.equals("2")) {//加密
                    String encMsg = model.getEncMsg();
                    LogUtils.loge(model.getEncMsg());
                    try {
                        String platext = DESedeUtil.decrypt(encMsg, spUtils.getSecretKey(), spUtils.getSecretIv());
                        LogUtils.loge("解密后：----------图形验证码-----------" + platext);
                        GetPiccodeModel getPiccodeModel = GsonTools.getObject(platext, GetPiccodeModel.class);
                        if (getPiccodeModel != null && getPiccodeModel.getHeader().getCode().equals("0000")) {
                            ((GetPiccodeView) mvpView).getPiccodeSuccess(getPiccodeModel);
                        } else {
                            ((GetPiccodeView) mvpView).getPiccodeFail(getPiccodeModel.getHeader().getDesc());
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                } else {
                    LogUtils.loge("首页：未加密:" + model.getMsgType());
                }
            }

            @Override
            public void onFailure(String msg) {
                ((GetPiccodeView) mvpView).getPiccodeFail(msg);
            }

            @Override
            public void onFinish() {
                mvpView.hideLoading();
            }
        });
    }


    /**
     * 校验图形验证码
     *
     * @param encMsg
     * @param signMsg
     * @param msgType
     * @param secretKeyId
     */
    public void checkpiccode(String encMsg, String signMsg, final String msgType, String secretKeyId) {
        mvpView.showLoading();
        addSubscription(apiStores.checkpiccode(encMsg, signMsg, msgType, secretKeyId), new ApiCallback<EncryptedResponseModel>(mContext) {
            @Override
            public void onSuccess(EncryptedResponseModel model) {
                LogUtils.loge("MsgType:" + model.getMsgType());
                String msgType = model.getMsgType();
                if (msgType != null && msgType.equals("2")) {//加密
                    String encMsg = model.getEncMsg();
                    LogUtils.loge(model.getEncMsg());
                    try {
                        String platext = DESedeUtil.decrypt(encMsg, spUtils.getSecretKey(), spUtils.getSecretIv());
                        LogUtils.loge("解密后：----------校验图形验证码-----------" + platext);

                        CheckPiccodeModel checkPiccodeModel = GsonTools.getObject(platext, CheckPiccodeModel.class);
                        if (checkPiccodeModel != null && checkPiccodeModel.getHeader().getCode().equals("0000")) {
                            ((OtherView) mvpView).onSuccess(checkPiccodeModel);
                        } else {
                            ((OtherView) mvpView).onFail(checkPiccodeModel.getHeader().getDesc());
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                } else {
                    LogUtils.loge("首页：未加密:" + model.getMsgType());
                }
            }

            @Override
            public void onFailure(String msg) {
                ((OtherView) mvpView).onFail(msg);
            }

            @Override
            public void onFinish() {
                mvpView.hideLoading();
            }
        });
    }


    /**
     * 短信校验
     */
    public void checkvalidcode(String encMsg, String signMsg, final String msgType, String secretKeyId) {
//        mvpView.showLoading();
        addSubscription(apiStores.checkvalidcode(encMsg, signMsg, msgType, secretKeyId), new ApiCallback<EncryptedResponseModel>(mContext) {
            @Override
            public void onSuccess(EncryptedResponseModel model) {
                LogUtils.loge("MsgType:" + model.getMsgType());
                String msgType = model.getMsgType();
                if (msgType != null && msgType.equals("2")) {//加密
                    String encMsg = model.getEncMsg();
                    LogUtils.loge(model.getEncMsg());
                    try {
                        String platext = DESedeUtil.decrypt(encMsg, spUtils.getSecretKey(), spUtils.getSecretIv());
                        LogUtils.loge("解密后：----------短信校验-----------" + platext);

                        SercetKeyOverdueModel sercetKeyOverdueModel = GsonTools.getObject(platext, SercetKeyOverdueModel.class);
                        if (sercetKeyOverdueModel != null && sercetKeyOverdueModel.getHeader().getCode().equals("0000")) {
                            ((ValidVercodeView) mvpView).validSuccess(sercetKeyOverdueModel);
                        } else {
                            ((ValidVercodeView) mvpView).validFail(sercetKeyOverdueModel.getHeader().getDesc());
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                } else {
                    LogUtils.loge("首页：未加密:" + model.getMsgType());
                }
            }

            @Override
            public void onFailure(String msg) {
                ((ValidVercodeView) mvpView).validFail(msg);
            }

            @Override
            public void onFinish() {
                mvpView.hideLoading();
            }
        });
    }


    /**
     * 删除保险
     *
     * @param encMsg
     * @param signMsg
     * @param msgType
     * @param secretKeyId
     */
    public void delinsurance(String encMsg, String signMsg, final String msgType, String secretKeyId) {
//        mvpView.showLoading();
        addSubscription(apiStores.delinsurance(encMsg, signMsg, msgType, secretKeyId), new ApiCallback<EncryptedResponseModel>(mContext) {
            @Override
            public void onSuccess(EncryptedResponseModel model) {
                LogUtils.loge("MsgType:" + model.getMsgType());
                String msgType = model.getMsgType();
                if (msgType != null && msgType.equals("2")) {//加密
                    String encMsg = model.getEncMsg();
                    LogUtils.loge(model.getEncMsg());
                    try {
                        String platext = DESedeUtil.decrypt(encMsg, spUtils.getSecretKey(), spUtils.getSecretIv());
                        LogUtils.loge("解密后：" + platext);
                        SercetKeyOverdueModel loginModel = GsonTools.getObject(platext, SercetKeyOverdueModel.class);
                        if (loginModel.getHeader().getCode().equals("0000")) {
                            ((OtherView) mvpView).onSuccess(loginModel);
                        } else {
                            ((OtherView) mvpView).onFail(loginModel.getHeader().getDesc());
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                } else {
                    LogUtils.loge("首页：未加密:" + model.getMsgType());
                }
            }

            @Override
            public void onFailure(String msg) {
                ((OtherView) mvpView).onFail(msg);
            }

            @Override
            public void onFinish() {
                mvpView.hideLoading();
            }
        });
    }


    /**
     * 删除股票
     */
    public void delstock(String encMsg, String signMsg, final String msgType, String secretKeyId) {
//        mvpView.showLoading();
        addSubscription(apiStores.delstock(encMsg, signMsg, msgType, secretKeyId), new ApiCallback<EncryptedResponseModel>(mContext) {
            @Override
            public void onSuccess(EncryptedResponseModel model) {
                LogUtils.loge("MsgType:" + model.getMsgType());
                String msgType = model.getMsgType();
                if (msgType != null && msgType.equals("2")) {//加密
                    String encMsg = model.getEncMsg();
                    LogUtils.loge(model.getEncMsg());
                    try {
                        String platext = DESedeUtil.decrypt(encMsg, spUtils.getSecretKey(), spUtils.getSecretIv());
                        LogUtils.loge("解密后：" + platext);
                        SercetKeyOverdueModel loginModel = GsonTools.getObject(platext, SercetKeyOverdueModel.class);
                        if (loginModel.getHeader().getCode().equals("0000")) {
                            ((OtherView) mvpView).onSuccess(loginModel);
                        } else {
                            ((OtherView) mvpView).onFail(loginModel.getHeader().getDesc());
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                } else {
                    LogUtils.loge("首页：未加密:" + model.getMsgType());
                }
            }

            @Override
            public void onFailure(String msg) {
                ((OtherView) mvpView).onFail(msg);
            }

            @Override
            public void onFinish() {
                mvpView.hideLoading();
            }
        });
    }

    /**
     * 删除基金
     */
    public void delfund(String encMsg, String signMsg, final String msgType, String secretKeyId) {
//        mvpView.showLoading();
        addSubscription(apiStores.delfund(encMsg, signMsg, msgType, secretKeyId), new ApiCallback<EncryptedResponseModel>(mContext) {
            @Override
            public void onSuccess(EncryptedResponseModel model) {
                LogUtils.loge("MsgType:" + model.getMsgType());
                String msgType = model.getMsgType();
                if (msgType != null && msgType.equals("2")) {//加密
                    String encMsg = model.getEncMsg();
                    LogUtils.loge(model.getEncMsg());
                    try {
                        String platext = DESedeUtil.decrypt(encMsg, spUtils.getSecretKey(), spUtils.getSecretIv());
                        LogUtils.loge("解密后：" + platext);
                        SercetKeyOverdueModel loginModel = GsonTools.getObject(platext, SercetKeyOverdueModel.class);
                        if (loginModel.getHeader().getCode().equals("0000")) {
                            ((OtherView) mvpView).onSuccess(loginModel);
                        } else {
                            ((OtherView) mvpView).onFail(loginModel.getHeader().getDesc());
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                } else {
                    LogUtils.loge("首页：未加密:" + model.getMsgType());
                }
            }

            @Override
            public void onFailure(String msg) {
                ((OtherView) mvpView).onFail(msg);
            }

            @Override
            public void onFinish() {
                mvpView.hideLoading();
            }
        });
    }

    /**
     * 删除首页添加的资产模块
     */
    public void delindexzc(String encMsg, String signMsg, final String msgType, String secretKeyId) {
//        mvpView.showLoading();
        addSubscription(apiStores.delindexzc(encMsg, signMsg, msgType, secretKeyId), new ApiCallback<EncryptedResponseModel>(mContext) {
            @Override
            public void onSuccess(EncryptedResponseModel model) {
                LogUtils.loge("MsgType:" + model.getMsgType());
                String msgType = model.getMsgType();
                if (msgType != null && msgType.equals("2")) {//加密
                    String encMsg = model.getEncMsg();
                    LogUtils.loge(model.getEncMsg());
                    try {
                        String platext = DESedeUtil.decrypt(encMsg, spUtils.getSecretKey(), spUtils.getSecretIv());
                        LogUtils.loge("解密后：" + platext);
                        SercetKeyOverdueModel loginModel = GsonTools.getObject(platext, SercetKeyOverdueModel.class);
                        if (loginModel.getHeader().getCode().equals("0000")) {
                            ((OtherView) mvpView).onSuccess(loginModel);
                        } else {
                            ((OtherView) mvpView).onFail(loginModel.getHeader().getDesc());
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                } else {
                    LogUtils.loge("首页：未加密:" + model.getMsgType());
                }
            }

            @Override
            public void onFailure(String msg) {
                ((OtherView) mvpView).onFail(msg);
            }

            @Override
            public void onFinish() {
                mvpView.hideLoading();
            }
        });
    }

    /**
     * 删除海外房产
     *
     * @param encMsg
     * @param signMsg
     * @param msgType
     * @param secretKeyId
     */
    public void deloverseas(String encMsg, String signMsg, final String msgType, String secretKeyId) {
//        mvpView.showLoading();
        addSubscription(apiStores.deloverseas(encMsg, signMsg, msgType, secretKeyId), new ApiCallback<EncryptedResponseModel>(mContext) {
            @Override
            public void onSuccess(EncryptedResponseModel model) {
                LogUtils.loge("MsgType:" + model.getMsgType());
                String msgType = model.getMsgType();
                if (msgType != null && msgType.equals("2")) {//加密
                    String encMsg = model.getEncMsg();
                    LogUtils.loge(model.getEncMsg());
                    try {
                        String platext = DESedeUtil.decrypt(encMsg, spUtils.getSecretKey(), spUtils.getSecretIv());
                        LogUtils.loge("解密后：" + platext);
                        SercetKeyOverdueModel loginModel = GsonTools.getObject(platext, SercetKeyOverdueModel.class);
                        if (loginModel.getHeader().getCode().equals("0000")) {
                            ((OtherView) mvpView).onSuccess(loginModel);
                        } else {
                            ((OtherView) mvpView).onFail(loginModel.getHeader().getDesc());
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                } else {
                    LogUtils.loge("首页：未加密:" + model.getMsgType());
                }
            }

            @Override
            public void onFailure(String msg) {
                ((OtherView) mvpView).onFail(msg);
            }

            @Override
            public void onFinish() {
                mvpView.hideLoading();
            }
        });
    }

    /**
     * 删除理财
     */
    public void delcurfin(String encMsg, String signMsg, final String msgType, String secretKeyId) {
//        mvpView.showLoading();
        addSubscription(apiStores.delcurfin(encMsg, signMsg, msgType, secretKeyId), new ApiCallback<EncryptedResponseModel>(mContext) {
            @Override
            public void onSuccess(EncryptedResponseModel model) {
                LogUtils.loge("MsgType:" + model.getMsgType());
                String msgType = model.getMsgType();
                if (msgType != null && msgType.equals("2")) {//加密
                    String encMsg = model.getEncMsg();
                    LogUtils.loge(model.getEncMsg());
                    try {
                        String platext = DESedeUtil.decrypt(encMsg, spUtils.getSecretKey(), spUtils.getSecretIv());
                        LogUtils.loge("解密后：" + platext);
                        SercetKeyOverdueModel loginModel = GsonTools.getObject(platext, SercetKeyOverdueModel.class);
                        if (loginModel.getHeader().getCode().equals("0000")) {
                            ((OtherView) mvpView).onSuccess(loginModel);
                        } else {
                            ((OtherView) mvpView).onFail(loginModel.getHeader().getDesc());
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                } else {
                    LogUtils.loge("首页：未加密:" + model.getMsgType());
                }
            }

            @Override
            public void onFailure(String msg) {
                ((OtherView) mvpView).onFail(msg);
            }

            @Override
            public void onFinish() {
                mvpView.hideLoading();
            }
        });
    }

    /**
     * 删除欠款
     */
    public void delarrearsasset(String encMsg, String signMsg, final String msgType, String secretKeyId) {
//        mvpView.showLoading();
        addSubscription(apiStores.delarrearsasset(encMsg, signMsg, msgType, secretKeyId), new ApiCallback<EncryptedResponseModel>(mContext) {
            @Override
            public void onSuccess(EncryptedResponseModel model) {
                LogUtils.loge("MsgType:" + model.getMsgType());
                String msgType = model.getMsgType();
                if (msgType != null && msgType.equals("2")) {//加密
                    String encMsg = model.getEncMsg();
                    LogUtils.loge(model.getEncMsg());
                    try {
                        String platext = DESedeUtil.decrypt(encMsg, spUtils.getSecretKey(), spUtils.getSecretIv());
                        LogUtils.loge("解密后：" + platext);
                        SercetKeyOverdueModel loginModel = GsonTools.getObject(platext, SercetKeyOverdueModel.class);
                        if (loginModel.getHeader().getCode().equals("0000")) {
                            ((OtherView) mvpView).onSuccess(loginModel);
                        } else {
                            ((OtherView) mvpView).onFail(loginModel.getHeader().getDesc());
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                } else {
                    LogUtils.loge("首页：未加密:" + model.getMsgType());
                }
            }

            @Override
            public void onFailure(String msg) {
                ((OtherView) mvpView).onFail(msg);
            }

            @Override
            public void onFinish() {
                mvpView.hideLoading();
            }
        });
    }


    /**
     * 删除贷款
     */
    public void delcreditasset(String encMsg, String signMsg, final String msgType, String secretKeyId) {
//        mvpView.showLoading();
        addSubscription(apiStores.delcreditasset(encMsg, signMsg, msgType, secretKeyId), new ApiCallback<EncryptedResponseModel>(mContext) {
            @Override
            public void onSuccess(EncryptedResponseModel model) {
                LogUtils.loge("MsgType:" + model.getMsgType());
                String msgType = model.getMsgType();
                if (msgType != null && msgType.equals("2")) {//加密
                    String encMsg = model.getEncMsg();
                    LogUtils.loge(model.getEncMsg());
                    try {
                        String platext = DESedeUtil.decrypt(encMsg, spUtils.getSecretKey(), spUtils.getSecretIv());
                        LogUtils.loge("解密后：" + platext);
                        SercetKeyOverdueModel loginModel = GsonTools.getObject(platext, SercetKeyOverdueModel.class);
                        if (loginModel.getHeader().getCode().equals("0000")) {
                            ((OtherView) mvpView).onSuccess(loginModel);
                        } else {
                            ((OtherView) mvpView).onFail(loginModel.getHeader().getDesc());
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                } else {
                    LogUtils.loge("首页：未加密:" + model.getMsgType());
                }
            }

            @Override
            public void onFailure(String msg) {
                ((OtherView) mvpView).onFail(msg);
            }

            @Override
            public void onFinish() {
                mvpView.hideLoading();
            }
        });
    }


    /**
     * 删除股权
     */
    public void delequityassets(String encMsg, String signMsg, final String msgType, String secretKeyId) {
//        mvpView.showLoading();
        addSubscription(apiStores.delequityassets(encMsg, signMsg, msgType, secretKeyId), new ApiCallback<EncryptedResponseModel>(mContext) {
            @Override
            public void onSuccess(EncryptedResponseModel model) {
                LogUtils.loge("MsgType:" + model.getMsgType());
                String msgType = model.getMsgType();
                if (msgType != null && msgType.equals("2")) {//加密
                    String encMsg = model.getEncMsg();
                    LogUtils.loge(model.getEncMsg());
                    try {
                        String platext = DESedeUtil.decrypt(encMsg, spUtils.getSecretKey(), spUtils.getSecretIv());
                        LogUtils.loge("解密后：" + platext);
                        SercetKeyOverdueModel loginModel = GsonTools.getObject(platext, SercetKeyOverdueModel.class);
                        if (loginModel.getHeader().getCode().equals("0000")) {
                            ((OtherView) mvpView).onSuccess(loginModel);
                        } else {
                            ((OtherView) mvpView).onFail(loginModel.getHeader().getDesc());
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                } else {
                    LogUtils.loge("首页：未加密:" + model.getMsgType());
                }
            }

            @Override
            public void onFailure(String msg) {
                ((OtherView) mvpView).onFail(msg);
            }

            @Override
            public void onFinish() {
                mvpView.hideLoading();
            }
        });
    }

    /**
     * 删除预付卡
     */
    public void delprepaidcard(String encMsg, String signMsg, final String msgType, String secretKeyId) {
//        mvpView.showLoading();
        addSubscription(apiStores.delprepaidcard(encMsg, signMsg, msgType, secretKeyId), new ApiCallback<EncryptedResponseModel>(mContext) {
            @Override
            public void onSuccess(EncryptedResponseModel model) {
                LogUtils.loge("MsgType:" + model.getMsgType());
                String msgType = model.getMsgType();
                if (msgType != null && msgType.equals("2")) {//加密
                    String encMsg = model.getEncMsg();
                    LogUtils.loge(model.getEncMsg());
                    try {
                        String platext = DESedeUtil.decrypt(encMsg, spUtils.getSecretKey(), spUtils.getSecretIv());
                        LogUtils.loge("解密后：" + platext);
                        SercetKeyOverdueModel loginModel = GsonTools.getObject(platext, SercetKeyOverdueModel.class);
                        if (loginModel.getHeader().getCode().equals("0000")) {
                            ((OtherView) mvpView).onSuccess(loginModel);
                        } else {
                            ((OtherView) mvpView).onFail(loginModel.getHeader().getDesc());
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                } else {
                    LogUtils.loge("首页：未加密:" + model.getMsgType());
                }
            }

            @Override
            public void onFailure(String msg) {
                ((OtherView) mvpView).onFail(msg);
            }

            @Override
            public void onFinish() {
                mvpView.hideLoading();
            }
        });
    }


    /**
     * 删除游戏
     */
    public void delgame(String encMsg, String signMsg, final String msgType, String secretKeyId) {
//        mvpView.showLoading();
        addSubscription(apiStores.delgame(encMsg, signMsg, msgType, secretKeyId), new ApiCallback<EncryptedResponseModel>(mContext) {
            @Override
            public void onSuccess(EncryptedResponseModel model) {
                LogUtils.loge("MsgType:" + model.getMsgType());
                String msgType = model.getMsgType();
                if (msgType != null && msgType.equals("2")) {//加密
                    String encMsg = model.getEncMsg();
                    LogUtils.loge(model.getEncMsg());
                    try {
                        String platext = DESedeUtil.decrypt(encMsg, spUtils.getSecretKey(), spUtils.getSecretIv());
                        LogUtils.loge("解密后：" + platext);
                        SercetKeyOverdueModel loginModel = GsonTools.getObject(platext, SercetKeyOverdueModel.class);
                        if (loginModel.getHeader().getCode().equals("0000")) {
                            ((OtherView) mvpView).onSuccess(loginModel);
                        } else {
                            ((OtherView) mvpView).onFail(loginModel.getHeader().getDesc());
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                } else {
                    LogUtils.loge("首页：未加密:" + model.getMsgType());
                }
            }

            @Override
            public void onFailure(String msg) {
                ((OtherView) mvpView).onFail(msg);
            }

            @Override
            public void onFinish() {
                mvpView.hideLoading();
            }
        });
    }

    /**
     * 删除域名
     */
    public void deldomain(String encMsg, String signMsg, final String msgType, String secretKeyId) {
//        mvpView.showLoading();
        addSubscription(apiStores.deldomain(encMsg, signMsg, msgType, secretKeyId), new ApiCallback<EncryptedResponseModel>(mContext) {
            @Override
            public void onSuccess(EncryptedResponseModel model) {
                LogUtils.loge("MsgType:" + model.getMsgType());
                String msgType = model.getMsgType();
                if (msgType != null && msgType.equals("2")) {//加密
                    String encMsg = model.getEncMsg();
                    LogUtils.loge(model.getEncMsg());
                    try {
                        String platext = DESedeUtil.decrypt(encMsg, spUtils.getSecretKey(), spUtils.getSecretIv());
                        LogUtils.loge("解密后：" + platext);
                        SercetKeyOverdueModel loginModel = GsonTools.getObject(platext, SercetKeyOverdueModel.class);
                        if (loginModel.getHeader().getCode().equals("0000")) {
                            ((OtherView) mvpView).onSuccess(loginModel);
                        } else {
                            ((OtherView) mvpView).onFail(loginModel.getHeader().getDesc());
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                } else {
                    LogUtils.loge("首页：未加密:" + model.getMsgType());
                }
            }

            @Override
            public void onFailure(String msg) {
                ((OtherView) mvpView).onFail(msg);
            }

            @Override
            public void onFinish() {
                mvpView.hideLoading();
            }
        });
    }

    /**
     * 删除自定义分类
     */
    public void delcustomasset(String encMsg, String signMsg, final String msgType, String secretKeyId) {
//        mvpView.showLoading();
        addSubscription(apiStores.delcustomasset(encMsg, signMsg, msgType, secretKeyId), new ApiCallback<EncryptedResponseModel>(mContext) {
            @Override
            public void onSuccess(EncryptedResponseModel model) {
                LogUtils.loge("MsgType:" + model.getMsgType());
                String msgType = model.getMsgType();
                if (msgType != null && msgType.equals("2")) {//加密
                    String encMsg = model.getEncMsg();
                    LogUtils.loge(model.getEncMsg());
                    try {
                        String platext = DESedeUtil.decrypt(encMsg, spUtils.getSecretKey(), spUtils.getSecretIv());
                        LogUtils.loge("解密后：" + platext);
                        SercetKeyOverdueModel loginModel = GsonTools.getObject(platext, SercetKeyOverdueModel.class);
                        if (loginModel.getHeader().getCode().equals("0000")) {
                            ((OtherView) mvpView).onSuccess(loginModel);
                        } else {
                            ((OtherView) mvpView).onFail(loginModel.getHeader().getDesc());
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                } else {
                    LogUtils.loge("首页：未加密:" + model.getMsgType());
                }
            }

            @Override
            public void onFailure(String msg) {
                ((OtherView) mvpView).onFail(msg);
            }

            @Override
            public void onFinish() {
                mvpView.hideLoading();
            }
        });
    }

    /**
     * 删除私募基金
     */
    public void delsmfund(String encMsg, String signMsg, final String msgType, String secretKeyId) {
//        mvpView.showLoading();
        addSubscription(apiStores.delsmfund(encMsg, signMsg, msgType, secretKeyId), new ApiCallback<EncryptedResponseModel>(mContext) {
            @Override
            public void onSuccess(EncryptedResponseModel model) {
                LogUtils.loge("MsgType:" + model.getMsgType());
                String msgType = model.getMsgType();
                if (msgType != null && msgType.equals("2")) {//加密
                    String encMsg = model.getEncMsg();
                    LogUtils.loge(model.getEncMsg());
                    try {
                        String platext = DESedeUtil.decrypt(encMsg, spUtils.getSecretKey(), spUtils.getSecretIv());
                        LogUtils.loge("解密后：" + platext);
                        SercetKeyOverdueModel loginModel = GsonTools.getObject(platext, SercetKeyOverdueModel.class);
                        if (loginModel.getHeader().getCode().equals("0000")) {
                            ((OtherView) mvpView).onSuccess(loginModel);
                        } else {
                            ((OtherView) mvpView).onFail(loginModel.getHeader().getDesc());
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                } else {
                    LogUtils.loge("首页：未加密:" + model.getMsgType());
                }
            }

            @Override
            public void onFailure(String msg) {
                ((OtherView) mvpView).onFail(msg);
            }

            @Override
            public void onFinish() {
                mvpView.hideLoading();
            }
        });
    }

    /**
     * 删除借款
     */
    public void delloanasset(String encMsg, String signMsg, final String msgType, String secretKeyId) {
//        mvpView.showLoading();
        addSubscription(apiStores.delloanasset(encMsg, signMsg, msgType, secretKeyId), new ApiCallback<EncryptedResponseModel>(mContext) {
            @Override
            public void onSuccess(EncryptedResponseModel model) {
                LogUtils.loge("MsgType:" + model.getMsgType());
                String msgType = model.getMsgType();
                if (msgType != null && msgType.equals("2")) {//加密
                    String encMsg = model.getEncMsg();
                    LogUtils.loge(model.getEncMsg());
                    try {
                        String platext = DESedeUtil.decrypt(encMsg, spUtils.getSecretKey(), spUtils.getSecretIv());
                        LogUtils.loge("解密后：" + platext);
                        SercetKeyOverdueModel loginModel = GsonTools.getObject(platext, SercetKeyOverdueModel.class);
                        if (loginModel.getHeader().getCode().equals("0000")) {
                            ((OtherView) mvpView).onSuccess(loginModel);
                        } else {
                            ((OtherView) mvpView).onFail(loginModel.getHeader().getDesc());
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                } else {
                    LogUtils.loge("首页：未加密:" + model.getMsgType());
                }
            }

            @Override
            public void onFailure(String msg) {
                ((OtherView) mvpView).onFail(msg);
            }

            @Override
            public void onFinish() {
                mvpView.hideLoading();
            }
        });
    }

    /**
     * 删除报销单
     */
    public void delexpenseaccount(String encMsg, String signMsg, final String msgType, String secretKeyId) {
//        mvpView.showLoading();
        addSubscription(apiStores.delexpenseaccount(encMsg, signMsg, msgType, secretKeyId), new ApiCallback<EncryptedResponseModel>(mContext) {
            @Override
            public void onSuccess(EncryptedResponseModel model) {
                LogUtils.loge("MsgType:" + model.getMsgType());
                String msgType = model.getMsgType();
                if (msgType != null && msgType.equals("2")) {//加密
                    String encMsg = model.getEncMsg();
                    LogUtils.loge(model.getEncMsg());
                    try {
                        String platext = DESedeUtil.decrypt(encMsg, spUtils.getSecretKey(), spUtils.getSecretIv());
                        LogUtils.loge("解密后：" + platext);
                        SercetKeyOverdueModel loginModel = GsonTools.getObject(platext, SercetKeyOverdueModel.class);
                        if (loginModel.getHeader().getCode().equals("0000")) {
                            ((OtherView) mvpView).onSuccess(loginModel);
                        } else {
                            ((OtherView) mvpView).onFail(loginModel.getHeader().getDesc());
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                } else {
                    LogUtils.loge("首页：未加密:" + model.getMsgType());
                }
            }

            @Override
            public void onFailure(String msg) {
                ((OtherView) mvpView).onFail(msg);
            }

            @Override
            public void onFinish() {
                mvpView.hideLoading();
            }
        });
    }

    /**
     * 删除固定收益
     */
    public void deltrust(String encMsg, String signMsg, final String msgType, String secretKeyId) {
//        mvpView.showLoading();
        addSubscription(apiStores.deltrust(encMsg, signMsg, msgType, secretKeyId), new ApiCallback<EncryptedResponseModel>(mContext) {
            @Override
            public void onSuccess(EncryptedResponseModel model) {
                LogUtils.loge("MsgType:" + model.getMsgType());
                String msgType = model.getMsgType();
                if (msgType != null && msgType.equals("2")) {//加密
                    String encMsg = model.getEncMsg();
                    LogUtils.loge(model.getEncMsg());
                    try {
                        String platext = DESedeUtil.decrypt(encMsg, spUtils.getSecretKey(), spUtils.getSecretIv());
                        LogUtils.loge("解密后：" + platext);
                        SercetKeyOverdueModel loginModel = GsonTools.getObject(platext, SercetKeyOverdueModel.class);
                        if (loginModel.getHeader().getCode().equals("0000")) {
                            ((OtherView) mvpView).onSuccess(loginModel);
                        } else {
                            ((OtherView) mvpView).onFail(loginModel.getHeader().getDesc());
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                } else {
                    LogUtils.loge("首页：未加密:" + model.getMsgType());
                }
            }

            @Override
            public void onFailure(String msg) {
                ((OtherView) mvpView).onFail(msg);
            }

            @Override
            public void onFinish() {
                mvpView.hideLoading();
            }
        });
    }


    /**
     * 删除互联网账号
     */
    public void delwebaccount(String encMsg, String signMsg, final String msgType, String secretKeyId) {
//        mvpView.showLoading();
        addSubscription(apiStores.delwebaccount(encMsg, signMsg, msgType, secretKeyId), new ApiCallback<EncryptedResponseModel>(mContext) {
            @Override
            public void onSuccess(EncryptedResponseModel model) {
                LogUtils.loge("MsgType:" + model.getMsgType());
                String msgType = model.getMsgType();
                if (msgType != null && msgType.equals("2")) {//加密
                    String encMsg = model.getEncMsg();
                    LogUtils.loge(model.getEncMsg());
                    try {
                        String platext = DESedeUtil.decrypt(encMsg, spUtils.getSecretKey(), spUtils.getSecretIv());
                        LogUtils.loge("解密后：" + platext);
                        SercetKeyOverdueModel loginModel = GsonTools.getObject(platext, SercetKeyOverdueModel.class);
                        if (loginModel.getHeader().getCode().equals("0000")) {
                            ((OtherView) mvpView).onSuccess(loginModel);
                        } else {
                            ((OtherView) mvpView).onFail(loginModel.getHeader().getDesc());
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                } else {
                    LogUtils.loge("首页：未加密:" + model.getMsgType());
                }
            }

            @Override
            public void onFailure(String msg) {
                ((OtherView) mvpView).onFail(msg);
            }

            @Override
            public void onFinish() {
                mvpView.hideLoading();
            }
        });
    }

    /**
     * 删除银行卡
     */
    public void delbankcard(String encMsg, String signMsg, final String msgType, String secretKeyId) {
//        mvpView.showLoading();
        addSubscription(apiStores.delbankcard(encMsg, signMsg, msgType, secretKeyId), new ApiCallback<EncryptedResponseModel>(mContext) {
            @Override
            public void onSuccess(EncryptedResponseModel model) {
                LogUtils.loge("MsgType:" + model.getMsgType());
                String msgType = model.getMsgType();
                if (msgType != null && msgType.equals("2")) {//加密
                    String encMsg = model.getEncMsg();
                    LogUtils.loge(model.getEncMsg());
                    try {
                        String platext = DESedeUtil.decrypt(encMsg, spUtils.getSecretKey(), spUtils.getSecretIv());
                        LogUtils.loge("解密后：" + platext);
                        SercetKeyOverdueModel loginModel = GsonTools.getObject(platext, SercetKeyOverdueModel.class);
                        if (loginModel.getHeader().getCode().equals("0000")) {
                            ((OtherView) mvpView).onSuccess(loginModel);
                        } else {
                            ((OtherView) mvpView).onFail(loginModel.getHeader().getDesc());
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                } else {
                    LogUtils.loge("首页：未加密:" + model.getMsgType());
                }
            }

            @Override
            public void onFailure(String msg) {
                ((OtherView) mvpView).onFail(msg);
            }

            @Override
            public void onFinish() {
                mvpView.hideLoading();
            }
        });
    }


    /**
     * 保险详情
     */
    public void getinsurance(String encMsg, String signMsg, final String msgType, String secretKeyId) {
//        mvpView.showLoading();
        addSubscription(apiStores.getinsurance(encMsg, signMsg, msgType, secretKeyId), new ApiCallback<EncryptedResponseModel>(mContext) {
            @Override
            public void onSuccess(EncryptedResponseModel model) {
                LogUtils.loge("MsgType:" + model.getMsgType());
                String msgType = model.getMsgType();
                if (msgType != null && msgType.equals("2")) {//加密
                    String encMsg = model.getEncMsg();
                    LogUtils.loge(model.getEncMsg());
                    try {
                        String platext = DESedeUtil.decrypt(encMsg, spUtils.getSecretKey(), spUtils.getSecretIv());
                        LogUtils.loge("解密后：" + platext);
                        InsuranceDetailModel loginModel = GsonTools.getObject(platext, InsuranceDetailModel.class);
                        if (loginModel.getHeader().getCode().equals("0000")) {
                            ((CommonView) mvpView).getDataSuccess(loginModel);
                        } else {
                            ((CommonView) mvpView).getDataFail(loginModel.getHeader().getDesc());
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                } else {
                    LogUtils.loge("首页：未加密:" + model.getMsgType());
                }
            }

            @Override
            public void onFailure(String msg) {
                ((CommonView) mvpView).getDataFail(msg);
            }

            @Override
            public void onFinish() {
                mvpView.hideLoading();
            }
        });
    }

    /**
     * 互联网账号详情
     */
    public void getwebaccount(String encMsg, String signMsg, final String msgType, String secretKeyId) {
//        mvpView.showLoading();
        addSubscription(apiStores.getwebaccount(encMsg, signMsg, msgType, secretKeyId), new ApiCallback<EncryptedResponseModel>(mContext) {
            @Override
            public void onSuccess(EncryptedResponseModel model) {
                String msgType = model.getMsgType();
                if (msgType != null && msgType.equals("2")) {//加密
                    String encMsg = model.getEncMsg();
                    LogUtils.loge(model.getEncMsg());
                    try {
                        String platext = DESedeUtil.decrypt(encMsg, spUtils.getSecretKey(), spUtils.getSecretIv());
                        LogUtils.loge("解密后：" + platext);
                        InternetDetailModel loginModel = GsonTools.getObject(platext, InternetDetailModel.class);
                        if (loginModel.getHeader().getCode().equals("0000")) {
                            ((CommonView) mvpView).getDataSuccess(loginModel);
                        } else {
                            ((CommonView) mvpView).getDataFail(loginModel.getHeader().getDesc());
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                } else {
                    LogUtils.loge("首页：未加密:" + model.getMsgType());
                }
            }

            @Override
            public void onFailure(String msg) {
                ((CommonView) mvpView).getDataFail(msg);
            }

            @Override
            public void onFinish() {
                mvpView.hideLoading();
            }
        });
    }


    /**
     * 自定义分类详情
     */
    public void getcustomasset(String encMsg, String signMsg, final String msgType, String secretKeyId) {
//        mvpView.showLoading();
        addSubscription(apiStores.getcustomasset(encMsg, signMsg, msgType, secretKeyId), new ApiCallback<EncryptedResponseModel>(mContext) {
            @Override
            public void onSuccess(EncryptedResponseModel model) {
                String msgType = model.getMsgType();
                if (msgType != null && msgType.equals("2")) {//加密
                    String encMsg = model.getEncMsg();
                    LogUtils.loge(model.getEncMsg());
                    try {
                        String platext = DESedeUtil.decrypt(encMsg, spUtils.getSecretKey(), spUtils.getSecretIv());
                        LogUtils.loge("解密后：" + platext);
                        CustomDetailModel loginModel = GsonTools.getObject(platext, CustomDetailModel.class);
                        if (loginModel.getHeader().getCode().equals("0000")) {
                            ((CommonView) mvpView).getDataSuccess(loginModel);
                        } else {
                            ((CommonView) mvpView).getDataFail(loginModel.getHeader().getDesc());
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                } else {
                    LogUtils.loge("首页：未加密:" + model.getMsgType());
                }
            }

            @Override
            public void onFailure(String msg) {
                ((CommonView) mvpView).getDataFail(msg);
            }

            @Override
            public void onFinish() {
                mvpView.hideLoading();
            }
        });
    }

    /**
     * 报销单详情
     */
    public void getexpenseaccount(String encMsg, String signMsg, final String msgType, String secretKeyId) {
//        mvpView.showLoading();
        addSubscription(apiStores.getexpenseaccount(encMsg, signMsg, msgType, secretKeyId), new ApiCallback<EncryptedResponseModel>(mContext) {
            @Override
            public void onSuccess(EncryptedResponseModel model) {
                String msgType = model.getMsgType();
                if (msgType != null && msgType.equals("2")) {//加密
                    String encMsg = model.getEncMsg();
                    LogUtils.loge(model.getEncMsg());
                    try {
                        String platext = DESedeUtil.decrypt(encMsg, spUtils.getSecretKey(), spUtils.getSecretIv());
                        LogUtils.loge("解密后：" + platext);
                        ReimDetailModel loginModel = GsonTools.getObject(platext, ReimDetailModel.class);
                        if (loginModel.getHeader().getCode().equals("0000")) {
                            ((CommonView) mvpView).getDataSuccess(loginModel);
                        } else {
                            ((CommonView) mvpView).getDataFail(loginModel.getHeader().getDesc());
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                } else {
                    LogUtils.loge("首页：未加密:" + model.getMsgType());
                }
            }

            @Override
            public void onFailure(String msg) {
                ((CommonView) mvpView).getDataFail(msg);
            }

            @Override
            public void onFinish() {
                mvpView.hideLoading();
            }
        });
    }

    /**
     * 游戏详情
     */
    public void getgame(String encMsg, String signMsg, final String msgType, String secretKeyId) {
//        mvpView.showLoading();
        addSubscription(apiStores.getgame(encMsg, signMsg, msgType, secretKeyId), new ApiCallback<EncryptedResponseModel>(mContext) {
            @Override
            public void onSuccess(EncryptedResponseModel model) {
                String msgType = model.getMsgType();
                if (msgType != null && msgType.equals("2")) {//加密
                    String encMsg = model.getEncMsg();
                    LogUtils.loge(model.getEncMsg());
                    try {
                        String platext = DESedeUtil.decrypt(encMsg, spUtils.getSecretKey(), spUtils.getSecretIv());
                        LogUtils.loge("解密后：" + platext);
                        ReimDetailModel loginModel = GsonTools.getObject(platext, ReimDetailModel.class);
                        if (loginModel.getHeader().getCode().equals("0000")) {
                            ((CommonView) mvpView).getDataSuccess(loginModel);
                        } else {
                            ((CommonView) mvpView).getDataFail(loginModel.getHeader().getDesc());
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                } else {
                    LogUtils.loge("首页：未加密:" + model.getMsgType());
                }
            }

            @Override
            public void onFailure(String msg) {
                ((CommonView) mvpView).getDataFail(msg);
            }

            @Override
            public void onFinish() {
                mvpView.hideLoading();
            }
        });
    }

    /**
     * 海外房产详情
     */
    public void getoverseas(String encMsg, String signMsg, final String msgType, String secretKeyId) {
//        mvpView.showLoading();
        addSubscription(apiStores.getoverseas(encMsg, signMsg, msgType, secretKeyId), new ApiCallback<EncryptedResponseModel>(mContext) {
            @Override
            public void onSuccess(EncryptedResponseModel model) {
                LogUtils.loge("MsgType:" + model.getMsgType());
                String msgType = model.getMsgType();
                if (msgType != null && msgType.equals("2")) {//加密
                    String encMsg = model.getEncMsg();
                    LogUtils.loge(model.getEncMsg());
                    try {
                        String platext = DESedeUtil.decrypt(encMsg, spUtils.getSecretKey(), spUtils.getSecretIv());
                        LogUtils.loge("解密后：" + platext);
                        OverseasDetailModel loginModel = GsonTools.getObject(platext, OverseasDetailModel.class);
                        if (loginModel.getHeader().getCode().equals("0000")) {
                            ((CommonView) mvpView).getDataSuccess(loginModel);
                        } else {
                            ((CommonView) mvpView).getDataFail(loginModel.getHeader().getDesc());
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                } else {
                    LogUtils.loge("首页：未加密:" + model.getMsgType());
                }
            }

            @Override
            public void onFailure(String msg) {
                ((CommonView) mvpView).getDataFail(msg);
            }

            @Override
            public void onFinish() {
                mvpView.hideLoading();
            }
        });
    }

    /**
     * 活期理财新增交易记录
     */
    public void updatecurfinsum(String encMsg, String signMsg, final String msgType, String secretKeyId) {
        mvpView.showLoading();
        addSubscription(apiStores.updatecurfinsum(encMsg, signMsg, msgType, secretKeyId), new ApiCallback<EncryptedResponseModel>(mContext) {
            @Override
            public void onSuccess(EncryptedResponseModel model) {
                LogUtils.loge("MsgType:" + model.getMsgType());
                String msgType = model.getMsgType();
                if (msgType != null && msgType.equals("2")) {//加密
                    String encMsg = model.getEncMsg();
                    LogUtils.loge(model.getEncMsg());
                    try {
                        String platext = DESedeUtil.decrypt(encMsg, spUtils.getSecretKey(), spUtils.getSecretIv());
                        LogUtils.loge("解密后：" + platext);
                        SercetKeyOverdueModel loginModel = GsonTools.getObject(platext, SercetKeyOverdueModel.class);
                        if (loginModel.getHeader().getCode().equals("0000")) {
                            ((OtherView) mvpView).onSuccess(loginModel);
                        } else {
                            ((OtherView) mvpView).onFail(loginModel.getHeader().getDesc());
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                } else {
                    LogUtils.loge("首页：未加密:" + model.getMsgType());
                }
            }

            @Override
            public void onFailure(String msg) {
                ((OtherView) mvpView).onFail(msg);
            }

            @Override
            public void onFinish() {
                mvpView.hideLoading();
            }
        });
    }

    /**
     * 活期理财更新
     */
    public void updatecurfin(String encMsg, String signMsg, final String msgType, String secretKeyId) {
        mvpView.showLoading();
        addSubscription(apiStores.updatecurfin(encMsg, signMsg, msgType, secretKeyId), new ApiCallback<EncryptedResponseModel>(mContext) {
            @Override
            public void onSuccess(EncryptedResponseModel model) {
                LogUtils.loge("MsgType:" + model.getMsgType());
                String msgType = model.getMsgType();
                if (msgType != null && msgType.equals("2")) {//加密
                    String encMsg = model.getEncMsg();
                    LogUtils.loge(model.getEncMsg());
                    try {
                        String platext = DESedeUtil.decrypt(encMsg, spUtils.getSecretKey(), spUtils.getSecretIv());
                        LogUtils.loge("解密后：" + platext);
                        SercetKeyOverdueModel loginModel = GsonTools.getObject(platext, SercetKeyOverdueModel.class);
                        if (loginModel.getHeader().getCode().equals("0000")) {
                            ((CommonView) mvpView).getDataSuccess(loginModel);
                        } else {
                            ((CommonView) mvpView).getDataFail(loginModel.getHeader().getDesc());
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                } else {
                    LogUtils.loge("首页：未加密:" + model.getMsgType());
                }
            }

            @Override
            public void onFailure(String msg) {
                ((CommonView) mvpView).getDataFail(msg);
            }

            @Override
            public void onFinish() {
                mvpView.hideLoading();
            }
        });
    }

    /**
     * 互联网账号更新
     */
    public void updatewebaccount(String encMsg, String signMsg, final String msgType, String secretKeyId) {
        mvpView.showLoading();
        addSubscription(apiStores.updatewebaccount(encMsg, signMsg, msgType, secretKeyId), new ApiCallback<EncryptedResponseModel>(mContext) {
            @Override
            public void onSuccess(EncryptedResponseModel model) {
                LogUtils.loge("MsgType:" + model.getMsgType());
                String msgType = model.getMsgType();
                if (msgType != null && msgType.equals("2")) {//加密
                    String encMsg = model.getEncMsg();
                    LogUtils.loge(model.getEncMsg());
                    try {
                        String platext = DESedeUtil.decrypt(encMsg, spUtils.getSecretKey(), spUtils.getSecretIv());
                        LogUtils.loge("解密后：" + platext);
                        SercetKeyOverdueModel loginModel = GsonTools.getObject(platext, SercetKeyOverdueModel.class);
                        if (loginModel.getHeader().getCode().equals("0000")) {
                            ((CommonView) mvpView).getDataSuccess(loginModel);
                        } else {
                            ((CommonView) mvpView).getDataFail(loginModel.getHeader().getDesc());
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                } else {
                    LogUtils.loge("首页：未加密:" + model.getMsgType());
                }
            }

            @Override
            public void onFailure(String msg) {
                ((CommonView) mvpView).getDataFail(msg);
            }

            @Override
            public void onFinish() {
                mvpView.hideLoading();
            }
        });
    }

    /**
     * 固定收益更新
     */
    public void updatetrust(String encMsg, String signMsg, final String msgType, String secretKeyId) {
        mvpView.showLoading();
        addSubscription(apiStores.updatetrust(encMsg, signMsg, msgType, secretKeyId), new ApiCallback<EncryptedResponseModel>(mContext) {
            @Override
            public void onSuccess(EncryptedResponseModel model) {
                LogUtils.loge("MsgType:" + model.getMsgType());
                String msgType = model.getMsgType();
                if (msgType != null && msgType.equals("2")) {//加密
                    String encMsg = model.getEncMsg();
                    LogUtils.loge(model.getEncMsg());
                    try {
                        String platext = DESedeUtil.decrypt(encMsg, spUtils.getSecretKey(), spUtils.getSecretIv());
                        LogUtils.loge("解密后：" + platext);
                        SercetKeyOverdueModel loginModel = GsonTools.getObject(platext, SercetKeyOverdueModel.class);
                        if (loginModel.getHeader().getCode().equals("0000")) {
                            ((CommonView) mvpView).getDataSuccess(loginModel);
                        } else {
                            ((CommonView) mvpView).getDataFail(loginModel.getHeader().getDesc());
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                } else {
                    LogUtils.loge("首页：未加密:" + model.getMsgType());
                }
            }

            @Override
            public void onFailure(String msg) {
                ((CommonView) mvpView).getDataFail(msg);
            }

            @Override
            public void onFinish() {
                mvpView.hideLoading();
            }
        });
    }

    /**
     * 活期理财交易记录列表
     */
    public void geturrentrecordList(String encMsg, String signMsg, final String msgType, String secretKeyId) {
//        mvpView.showLoading();
        addSubscription(apiStores.geturrentrecordList(encMsg, signMsg, msgType, secretKeyId), new ApiCallback<EncryptedResponseModel>(mContext) {
            @Override
            public void onSuccess(EncryptedResponseModel model) {
                LogUtils.loge("MsgType:" + model.getMsgType());
                String msgType = model.getMsgType();
                if (msgType != null && msgType.equals("2")) {//加密
                    String encMsg = model.getEncMsg();
                    LogUtils.loge(model.getEncMsg());
                    try {
                        String platext = DESedeUtil.decrypt(encMsg, spUtils.getSecretKey(), spUtils.getSecretIv());
                        LogUtils.loge("解密后：" + platext);
                        OperationRecordModel loginModel = GsonTools.getObject(platext, OperationRecordModel.class);
                        if (loginModel.getHeader().getCode().equals("0000")) {
                            ((CommonView) mvpView).getDataSuccess(loginModel);
                        } else {
                            ((CommonView) mvpView).getDataFail(loginModel.getHeader().getDesc());
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                } else {
                    LogUtils.loge("首页：未加密:" + model.getMsgType());
                }
            }

            @Override
            public void onFailure(String msg) {
                ((CommonView) mvpView).getDataFail(msg);
            }

            @Override
            public void onFinish() {
                mvpView.hideLoading();
            }
        });
    }

    /**
     * 预付卡交易记录列表
     */
    public void getprrecordList(String encMsg, String signMsg, final String msgType, String secretKeyId) {
//        mvpView.showLoading();
        addSubscription(apiStores.getprrecordList(encMsg, signMsg, msgType, secretKeyId), new ApiCallback<EncryptedResponseModel>(mContext) {
            @Override
            public void onSuccess(EncryptedResponseModel model) {
                LogUtils.loge("MsgType:" + model.getMsgType());
                String msgType = model.getMsgType();
                if (msgType != null && msgType.equals("2")) {//加密
                    String encMsg = model.getEncMsg();
                    LogUtils.loge(model.getEncMsg());
                    try {
                        String platext = DESedeUtil.decrypt(encMsg, spUtils.getSecretKey(), spUtils.getSecretIv());
                        LogUtils.loge("解密后：" + platext);
                        PrepaidRecordListModel loginModel = GsonTools.getObject(platext, PrepaidRecordListModel.class);
                        if (loginModel.getHeader().getCode().equals("0000")) {
                            ((CommonView) mvpView).getDataSuccess(loginModel);
                        } else {
                            ((CommonView) mvpView).getDataFail(loginModel.getHeader().getDesc());
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                } else {
                    LogUtils.loge("首页：未加密:" + model.getMsgType());
                }
            }

            @Override
            public void onFailure(String msg) {
                ((CommonView) mvpView).getDataFail(msg);
            }

            @Override
            public void onFinish() {
                mvpView.hideLoading();
            }
        });
    }


    /**
     * 银行卡详情
     */
    public void getbankcard(String encMsg, String signMsg, final String msgType, String secretKeyId) {
//        mvpView.showLoading();
        addSubscription(apiStores.getbankcard(encMsg, signMsg, msgType, secretKeyId), new ApiCallback<EncryptedResponseModel>(mContext) {
            @Override
            public void onSuccess(EncryptedResponseModel model) {
                LogUtils.loge("MsgType:" + model.getMsgType());
                String msgType = model.getMsgType();
                if (msgType != null && msgType.equals("2")) {//加密
                    String encMsg = model.getEncMsg();
                    LogUtils.loge(model.getEncMsg());
                    try {
                        String platext = DESedeUtil.decrypt(encMsg, spUtils.getSecretKey(), spUtils.getSecretIv());
                        LogUtils.loge("解密后：" + platext);
                        BankcardDetailModel loginModel = GsonTools.getObject(platext, BankcardDetailModel.class);
                        if (loginModel.getHeader().getCode().equals("0000")) {
                            ((CommonView) mvpView).getDataSuccess(loginModel);
                        } else {
                            ((CommonView) mvpView).getDataFail(loginModel.getHeader().getDesc());
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                } else {
                    LogUtils.loge("首页：未加密:" + model.getMsgType());
                }
            }

            @Override
            public void onFailure(String msg) {
                ((CommonView) mvpView).getDataFail(msg);
            }

            @Override
            public void onFinish() {
                mvpView.hideLoading();
            }
        });
    }


    /**
     * 查询交易密码是否设置接口
     */
    public void querytxpwd(String encMsg, String signMsg, final String msgType, String secretKeyId) {
        mvpView.showLoading();
        addSubscription(apiStores.querytxpwd(encMsg, signMsg, msgType, secretKeyId), new ApiCallback<EncryptedResponseModel>(mContext) {
            @Override
            public void onSuccess(EncryptedResponseModel model) {
                LogUtils.loge("MsgType:" + model.getMsgType());
                String msgType = model.getMsgType();
                if (msgType != null && msgType.equals("2")) {//加密
                    String encMsg = model.getEncMsg();
                    LogUtils.loge(model.getEncMsg());
                    try {
                        String platext = DESedeUtil.decrypt(encMsg, spUtils.getSecretKey(), spUtils.getSecretIv());
                        LogUtils.loge("解密后：" + platext);
                        PayPasswdQueryModel loginModel = GsonTools.getObject(platext, PayPasswdQueryModel.class);
                        if (loginModel.getHeader().getCode().equals("0000")) {
                            ((CommonView) mvpView).getDataSuccess(loginModel);
                        } else {
                            ((CommonView) mvpView).getDataFail(loginModel.getHeader().getDesc());
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                } else {
                    LogUtils.loge("首页：未加密:" + model.getMsgType());
                }
            }

            @Override
            public void onFailure(String msg) {
                ((CommonView) mvpView).getDataFail(msg);
            }

            @Override
            public void onFinish() {
                mvpView.hideLoading();
            }
        });
    }

    /**
     * 支付密码设置接口
     */
    public void settxpwd(String encMsg, String signMsg, final String msgType, String secretKeyId) {
        mvpView.showLoading();
        addSubscription(apiStores.settxpwd(encMsg, signMsg, msgType, secretKeyId), new ApiCallback<EncryptedResponseModel>(mContext) {
            @Override
            public void onSuccess(EncryptedResponseModel model) {
                LogUtils.loge("MsgType:" + model.getMsgType());
                String msgType = model.getMsgType();
                if (msgType != null && msgType.equals("2")) {//加密
                    String encMsg = model.getEncMsg();
                    LogUtils.loge(model.getEncMsg());
                    try {
                        String platext = DESedeUtil.decrypt(encMsg, spUtils.getSecretKey(), spUtils.getSecretIv());
                        LogUtils.loge("设置支付密码：" + platext);
                        SercetKeyOverdueModel loginModel = GsonTools.getObject(platext, SercetKeyOverdueModel.class);
                        if (loginModel.getHeader().getCode().equals("0000")) {
                            ((CommonView) mvpView).getDataSuccess(loginModel);
                        } else {
                            ((CommonView) mvpView).getDataFail(loginModel.getHeader().getDesc());
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                } else {
                    LogUtils.loge("首页：未加密:" + model.getMsgType());
                }
            }

            @Override
            public void onFailure(String msg) {
                ((CommonView) mvpView).getDataFail(msg);
            }

            @Override
            public void onFinish() {
                mvpView.hideLoading();
            }
        });
    }

    /**
     * 校验交易密码是否正确
     */
    public void checktxpwd(String encMsg, String signMsg, final String msgType, String secretKeyId) {
        mvpView.showLoading();
        addSubscription(apiStores.checktxpwd(encMsg, signMsg, msgType, secretKeyId), new ApiCallback<EncryptedResponseModel>(mContext) {
            @Override
            public void onSuccess(EncryptedResponseModel model) {
                LogUtils.loge("MsgType:" + model.getMsgType());
                String msgType = model.getMsgType();
                if (msgType != null && msgType.equals("2")) {//加密
                    String encMsg = model.getEncMsg();
                    LogUtils.loge(model.getEncMsg());
                    try {
                        String platext = DESedeUtil.decrypt(encMsg, spUtils.getSecretKey(), spUtils.getSecretIv());
                        LogUtils.loge("设置支付密码：" + platext);
                        PayPasswdQueryModel loginModel = GsonTools.getObject(platext, PayPasswdQueryModel.class);
                        if (loginModel.getHeader().getCode().equals("0000")) {
                            ((CommonView) mvpView).getDataSuccess(loginModel);
                        } else {
                            ((CommonView) mvpView).getDataFail(loginModel.getHeader().getDesc());
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                } else {
                    LogUtils.loge("首页：未加密:" + model.getMsgType());
                }
            }

            @Override
            public void onFailure(String msg) {
                ((CommonView) mvpView).getDataFail(msg);
            }

            @Override
            public void onFinish() {
                mvpView.hideLoading();
            }
        });
    }


    /**
     * 获取支持的银行列表
     */
    public void getSupportBank(String encMsg, String signMsg, final String msgType, String secretKeyId) {
//        mvpView.showLoading();
        addSubscription(apiStores.getSupportBank(encMsg, signMsg, msgType, secretKeyId), new ApiCallback<EncryptedResponseModel>(mContext) {
            @Override
            public void onSuccess(EncryptedResponseModel model) {
                LogUtils.loge("MsgType:" + model.getMsgType());
                String msgType = model.getMsgType();
                if (msgType != null && msgType.equals("2")) {//加密
                    String encMsg = model.getEncMsg();
                    LogUtils.loge(model.getEncMsg());
                    try {
                        String platext = DESedeUtil.decrypt(encMsg, spUtils.getSecretKey(), spUtils.getSecretIv());
                        LogUtils.loge("解密后：" + platext);
                        SupportBankModel loginModel = GsonTools.getObject(platext, SupportBankModel.class);
                        if (loginModel.getHeader().getCode().equals("0000")) {
                            ((CommonView) mvpView).getDataSuccess(loginModel);
                        } else {
                            ((CommonView) mvpView).getDataFail(loginModel.getHeader().getDesc());
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                } else {
                    LogUtils.loge("首页：未加密:" + model.getMsgType());
                }

            }

            @Override
            public void onFailure(String msg) {
                ((CommonView) mvpView).getDataFail(msg);
            }

            @Override
            public void onFinish() {
                mvpView.hideLoading();
            }
        });
    }

    /**
     * 添加资产页面
     */
    public void defzc(String encMsg, String signMsg, final String msgType, String secretKeyId) {
        mvpView.showLoading();
        addSubscription(apiStores.defzc(encMsg, signMsg, msgType, secretKeyId), new ApiCallback<EncryptedResponseModel>(mContext) {
            @Override
            public void onSuccess(EncryptedResponseModel model) {
                LogUtils.loge("MsgType:" + model.getMsgType());
                String msgType = model.getMsgType();
                if (msgType != null && msgType.equals("2")) {//加密
                    String encMsg = model.getEncMsg();
                    LogUtils.loge(model.getEncMsg());
                    try {
                        String platext = DESedeUtil.decrypt(encMsg, spUtils.getSecretKey(), spUtils.getSecretIv());
                        LogUtils.loge("解密后：" + platext);
                        AssetClassModel loginModel = GsonTools.getObject(platext, AssetClassModel.class);
                        if (loginModel.getHeader().getCode().equals("0000")) {
                            ((CommonView) mvpView).getDataSuccess(loginModel);
                        } else {
                            ((CommonView) mvpView).getDataFail(loginModel.getHeader().getDesc());
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                } else {
                    LogUtils.loge("首页：未加密:" + model.getMsgType());
                }

            }

            @Override
            public void onFailure(String msg) {
                ((CommonView) mvpView).getDataFail(msg);
            }

            @Override
            public void onFinish() {
                mvpView.hideLoading();
            }
        });
    }

    /**
     * 获取支持的货币列表
     */
    public void getCurrencylist(String encMsg, String signMsg, final String msgType, String secretKeyId) {
//        mvpView.showLoading();
        addSubscription(apiStores.getCurrencylist(encMsg, signMsg, msgType, secretKeyId), new ApiCallback<EncryptedResponseModel>(mContext) {
            @Override
            public void onSuccess(EncryptedResponseModel model) {
                LogUtils.loge("MsgType:" + model.getMsgType());
                String msgType = model.getMsgType();
                if (msgType != null && msgType.equals("2")) {//加密
                    String encMsg = model.getEncMsg();
                    LogUtils.loge(model.getEncMsg());
                    try {
                        String platext = DESedeUtil.decrypt(encMsg, spUtils.getSecretKey(), spUtils.getSecretIv());
                        LogUtils.loge("解密后：" + platext);
                        CurrencyModel loginModel = GsonTools.getObject(platext, CurrencyModel.class);
                        if (loginModel.getHeader().getCode().equals("0000")) {
                            ((CommonView) mvpView).getDataSuccess(loginModel);
                        } else {
                            ((CommonView) mvpView).getDataFail(loginModel.getHeader().getDesc());
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                } else {
                    LogUtils.loge("首页：未加密:" + model.getMsgType());
                }

            }

            @Override
            public void onFailure(String msg) {
                ((CommonView) mvpView).getDataFail(msg);
            }

            @Override
            public void onFinish() {
                mvpView.hideLoading();
            }
        });
    }

    /**
     * 股票详情
     */
    public void stockpro(String encMsg, String signMsg, final String msgType, String secretKeyId) {
//        mvpView.showLoading();
        addSubscription(apiStores.stockpro(encMsg, signMsg, msgType, secretKeyId), new ApiCallback<EncryptedResponseModel>(mContext) {
            @Override
            public void onSuccess(EncryptedResponseModel model) {
                LogUtils.loge("MsgType:" + model.getMsgType());
                String msgType = model.getMsgType();
                if (msgType != null && msgType.equals("2")) {//加密
                    String encMsg = model.getEncMsg();
                    LogUtils.loge(model.getEncMsg());
                    try {
                        String platext = DESedeUtil.decrypt(encMsg, spUtils.getSecretKey(), spUtils.getSecretIv());
                        LogUtils.loge("解密后：" + platext);
                        StockListModel loginModel = GsonTools.getObject(platext, StockListModel.class);
                        if (loginModel.getHeader().getCode().equals("0000")) {
                            ((CommonView) mvpView).getDataSuccess(loginModel);
                        } else {
                            ((CommonView) mvpView).getDataFail(loginModel.getHeader().getDesc());
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                } else {
                    LogUtils.loge("首页：未加密:" + model.getMsgType());
                }
            }

            @Override
            public void onFailure(String msg) {
                ((CommonView) mvpView).getDataFail(msg);
            }

            @Override
            public void onFinish() {
                mvpView.hideLoading();
            }
        });
    }


    /**
     * 编辑股票
     */
    public void updatestock(String encMsg, String signMsg, final String msgType, String secretKeyId) {
//        mvpView.showLoading();
        addSubscription(apiStores.updatestock(encMsg, signMsg, msgType, secretKeyId), new ApiCallback<EncryptedResponseModel>(mContext) {
            @Override
            public void onSuccess(EncryptedResponseModel model) {
                LogUtils.loge("MsgType:" + model.getMsgType());
                String msgType = model.getMsgType();
                if (msgType != null && msgType.equals("2")) {//加密
                    String encMsg = model.getEncMsg();
                    LogUtils.loge(model.getEncMsg());
                    try {
                        String platext = DESedeUtil.decrypt(encMsg, spUtils.getSecretKey(), spUtils.getSecretIv());
                        LogUtils.loge("解密后：" + platext);
                        StockEditModel loginModel = GsonTools.getObject(platext, StockEditModel.class);
                        if (loginModel.getHeader().getCode().equals("0000")) {
                            ((CommonView) mvpView).getDataSuccess(loginModel);
                        } else {
                            ((CommonView) mvpView).getDataFail(loginModel.getHeader().getDesc());
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                } else {
                    LogUtils.loge("首页：未加密:" + model.getMsgType());
                }
            }

            @Override
            public void onFailure(String msg) {
                ((CommonView) mvpView).getDataFail(msg);
            }

            @Override
            public void onFinish() {
                mvpView.hideLoading();
            }
        });
    }


    /**
     * 股票操作记录列表
     */
    public void getstockrecordlist(String encMsg, String signMsg, final String msgType, String secretKeyId) {
//        mvpView.showLoading();
        addSubscription(apiStores.getstockrecordlist(encMsg, signMsg, msgType, secretKeyId), new ApiCallback<EncryptedResponseModel>(mContext) {
            @Override
            public void onSuccess(EncryptedResponseModel model) {
                LogUtils.loge("MsgType:" + model.getMsgType());
                String msgType = model.getMsgType();
                if (msgType != null && msgType.equals("2")) {//加密
                    String encMsg = model.getEncMsg();
                    LogUtils.loge(model.getEncMsg());
                    try {
                        String platext = DESedeUtil.decrypt(encMsg, spUtils.getSecretKey(), spUtils.getSecretIv());
                        LogUtils.loge("解密后：" + platext);
                        StockRecordModel loginModel = GsonTools.getObject(platext, StockRecordModel.class);
                        if (loginModel.getHeader().getCode().equals("0000")) {
                            ((CommonView) mvpView).getDataSuccess(loginModel);
                        } else {
                            ((CommonView) mvpView).getDataFail(loginModel.getHeader().getDesc());
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                } else {
                    LogUtils.loge("首页：未加密:" + model.getMsgType());
                }
            }

            @Override
            public void onFailure(String msg) {
                ((CommonView) mvpView).getDataFail(msg);
            }

            @Override
            public void onFinish() {
                mvpView.hideLoading();
            }
        });
    }

    /**
     * 私募操作记录列表
     */
    public void getsmfundrecordlist(String encMsg, String signMsg, final String msgType, String secretKeyId) {
//        mvpView.showLoading();
        addSubscription(apiStores.getsmfundrecordlist(encMsg, signMsg, msgType, secretKeyId), new ApiCallback<EncryptedResponseModel>(mContext) {
            @Override
            public void onSuccess(EncryptedResponseModel model) {
                LogUtils.loge("MsgType:" + model.getMsgType());
                String msgType = model.getMsgType();
                if (msgType != null && msgType.equals("2")) {//加密
                    String encMsg = model.getEncMsg();
                    LogUtils.loge(model.getEncMsg());
                    try {
                        String platext = DESedeUtil.decrypt(encMsg, spUtils.getSecretKey(), spUtils.getSecretIv());
                        LogUtils.loge("解密后：" + platext);
                        StockRecordModel loginModel = GsonTools.getObject(platext, StockRecordModel.class);
                        if (loginModel.getHeader().getCode().equals("0000")) {
                            ((CommonView) mvpView).getDataSuccess(loginModel);
                        } else {
                            ((CommonView) mvpView).getDataFail(loginModel.getHeader().getDesc());
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                } else {
                    LogUtils.loge("首页：未加密:" + model.getMsgType());
                }
            }

            @Override
            public void onFailure(String msg) {
                ((CommonView) mvpView).getDataFail(msg);
            }

            @Override
            public void onFinish() {
                mvpView.hideLoading();
            }
        });
    }

    /**
     * 基金操作记录列表
     */
    public void getfundrecordlist(String encMsg, String signMsg, final String msgType, String secretKeyId) {
//        mvpView.showLoading();
        addSubscription(apiStores.getfundrecordlist(encMsg, signMsg, msgType, secretKeyId), new ApiCallback<EncryptedResponseModel>(mContext) {
            @Override
            public void onSuccess(EncryptedResponseModel model) {
                LogUtils.loge("MsgType:" + model.getMsgType());
                String msgType = model.getMsgType();
                if (msgType != null && msgType.equals("2")) {//加密
                    String encMsg = model.getEncMsg();
                    LogUtils.loge(model.getEncMsg());
                    try {
                        String platext = DESedeUtil.decrypt(encMsg, spUtils.getSecretKey(), spUtils.getSecretIv());
                        LogUtils.loge("解密后：" + platext);
                        StockRecordModel loginModel = GsonTools.getObject(platext, StockRecordModel.class);
                        if (loginModel.getHeader().getCode().equals("0000")) {
                            ((CommonView) mvpView).getDataSuccess(loginModel);
                        } else {
                            ((CommonView) mvpView).getDataFail(loginModel.getHeader().getDesc());
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                } else {
                    LogUtils.loge("首页：未加密:" + model.getMsgType());
                }
            }

            @Override
            public void onFailure(String msg) {
                ((CommonView) mvpView).getDataFail(msg);
            }

            @Override
            public void onFinish() {
                mvpView.hideLoading();
            }
        });
    }

    /**
     * 股票搜索
     */
    public void loadwordsstock(String callback, String words) {
//        mvpView.showLoading();
        addSubscription(apiStoresSearch.loadwordsstock(callback, words), new ApiCallback<StockSearchModel>(mContext) {
            @Override
            public void onSuccess(StockSearchModel model) {
                ((OtherView) mvpView).onSuccess(model);
            }

            @Override
            public void onFailure(String msg) {
                ((OtherView) mvpView).onFail(msg);
            }

            @Override
            public void onFinish() {
                mvpView.hideLoading();
            }
        });
    }

    /**
     * 私募搜索
     */
    public void loadwordssm(String callback, String words) {
//        mvpView.showLoading();
        addSubscription(apiStoresSearch.loadwordssm(callback, words), new ApiCallback<StockSearchModel>(mContext) {
            @Override
            public void onSuccess(StockSearchModel model) {
                ((OtherView) mvpView).onSuccess(model);
            }

            @Override
            public void onFailure(String msg) {
                ((OtherView) mvpView).onFail(msg);
            }

            @Override
            public void onFinish() {
                mvpView.hideLoading();
            }
        });
    }


    /**
     * 基金搜索
     */
    public void loadwordsgm(String callback, String words) {
//        mvpView.showLoading();
        addSubscription(apiStoresSearch.loadwordsgm(callback, words), new ApiCallback<StockSearchModel>(mContext) {
            @Override
            public void onSuccess(StockSearchModel model) {
                ((OtherView) mvpView).onSuccess(model);
            }

            @Override
            public void onFailure(String msg) {
                ((OtherView) mvpView).onFail(msg);
            }

            @Override
            public void onFinish() {
                mvpView.hideLoading();
            }
        });
    }

    /**
     * 财商首页数据
     */
    public void indexdata(String encMsg, String signMsg, final String msgType, String secretKeyId) {
//        mvpView.showLoading();
        addSubscription(apiStores.indexdata(encMsg, signMsg, msgType, secretKeyId), new ApiCallback<EncryptedResponseModel>(mContext) {
            @Override
            public void onSuccess(EncryptedResponseModel model) {
                LogUtils.loge("MsgType:" + model.getMsgType());
                String msgType = model.getMsgType();
                if (msgType != null && msgType.equals("2")) {//加密
                    String encMsg = model.getEncMsg();
                    LogUtils.loge(model.getEncMsg());
                    try {
                        String platext = DESedeUtil.decrypt(encMsg, spUtils.getSecretKey(), spUtils.getSecretIv());
                        LogUtils.loge("解密后---------财商首页数据----------：" + platext);
                        CsIndexDataModel csIndexDataModel = GsonTools.getObject(platext, CsIndexDataModel.class);
                        if (csIndexDataModel.getHeader().getCode().equals("0000")) {
                            ((CommonView) mvpView).getDataSuccess(csIndexDataModel);
                        } else {
                            ((CommonView) mvpView).getDataFail(csIndexDataModel.getHeader().getDesc());
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                } else {
                    LogUtils.loge("首页：未加密:" + model.getMsgType());
                }
            }

            @Override
            public void onFailure(String msg) {
                ((CommonView) mvpView).getDataFail(msg);
            }

            @Override
            public void onFinish() {
                mvpView.hideLoading();
            }
        });
    }


    /**
     * 添加欠款
     */
    public void addarrearsasset(String encMsg, String signMsg, final String msgType, String secretKeyId) {
        mvpView.showLoading();
        addSubscription(apiStores.addarrearsasset(encMsg, signMsg, msgType, secretKeyId), new ApiCallback<EncryptedResponseModel>(mContext) {
            @Override
            public void onSuccess(EncryptedResponseModel model) {
                LogUtils.loge("MsgType:" + model.getMsgType());
                String msgType = model.getMsgType();
                if (msgType != null && msgType.equals("2")) {//加密
                    String encMsg = model.getEncMsg();
                    LogUtils.loge(model.getEncMsg());
                    try {
                        String platext = DESedeUtil.decrypt(encMsg, spUtils.getSecretKey(), spUtils.getSecretIv());
                        LogUtils.loge("解密后：" + platext);
                        SercetKeyOverdueModel loginModel = GsonTools.getObject(platext, SercetKeyOverdueModel.class);
                        if (loginModel.getHeader().getCode().equals("0000")) {
                            ((OtherView) mvpView).onSuccess(loginModel);
                        } else {
                            ((OtherView) mvpView).onFail(loginModel.getHeader().getDesc());
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                } else {
                    LogUtils.loge("首页：未加密:" + model.getMsgType());

                }
            }

            @Override
            public void onFailure(String msg) {
                ((OtherView) mvpView).onFail(msg);
            }

            @Override
            public void onFinish() {
                mvpView.hideLoading();
            }
        });
    }


    /**
     * 人脉徽章埋点
     */
    public void invited(String userNo) {
//        mvpView.showLoading();
        addSubscription(apiStores.invited(userNo), new ApiCallback<InviteModel>(mContext) {
            @Override
            public void onSuccess(InviteModel model) {
                LogUtils.loge("MsgType:" + model.toString());
                ((CommonView) mvpView).getDataSuccess(model);
            }

            @Override
            public void onFailure(String msg) {
//                ((CommonView) mvpView).getDataFail(msg);
            }

            @Override
            public void onFinish() {
                mvpView.hideLoading();
            }
        });
    }

    /**
     * 添加贷款
     */
    public void addCreditasset(String encMsg, String signMsg, final String msgType, String secretKeyId) {
        mvpView.showLoading();
        addSubscription(apiStores.addCreditasset(encMsg, signMsg, msgType, secretKeyId), new ApiCallback<EncryptedResponseModel>(mContext) {
            @Override
            public void onSuccess(EncryptedResponseModel model) {
                LogUtils.loge("MsgType:" + model.getMsgType());
                String msgType = model.getMsgType();
                if (msgType != null && msgType.equals("2")) {//加密
                    String encMsg = model.getEncMsg();
                    LogUtils.loge(model.getEncMsg());
                    try {
                        String platext = DESedeUtil.decrypt(encMsg, spUtils.getSecretKey(), spUtils.getSecretIv());
                        LogUtils.loge("解密后：" + platext);
                        SercetKeyOverdueModel loginModel = GsonTools.getObject(platext, SercetKeyOverdueModel.class);
                        if (loginModel.getHeader().getCode().equals("0000")) {
                            ((CommonView) mvpView).getDataSuccess(loginModel);
                        } else {
                            ((CommonView) mvpView).getDataFail(loginModel.getHeader().getDesc());
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                } else {
                    LogUtils.loge("首页：未加密:" + model.getMsgType());
                }
            }

            @Override
            public void onFailure(String msg) {
                ((CommonView) mvpView).getDataFail(msg);
            }

            @Override
            public void onFinish() {
                mvpView.hideLoading();
            }
        });
    }


    /**
     * 添加股权
     */
    public void addEquityassets(String encMsg, String signMsg, final String msgType, String secretKeyId) {
        mvpView.showLoading();
        addSubscription(apiStores.addEquityassets(encMsg, signMsg, msgType, secretKeyId), new ApiCallback<EncryptedResponseModel>(mContext) {
            @Override
            public void onSuccess(EncryptedResponseModel model) {
                LogUtils.loge("MsgType:" + model.getMsgType());
                String msgType = model.getMsgType();
                if (msgType != null && msgType.equals("2")) {//加密
                    String encMsg = model.getEncMsg();
                    LogUtils.loge(model.getEncMsg());
                    try {
                        String platext = DESedeUtil.decrypt(encMsg, spUtils.getSecretKey(), spUtils.getSecretIv());
                        LogUtils.loge("解密后：" + platext);
                        SercetKeyOverdueModel loginModel = GsonTools.getObject(platext, SercetKeyOverdueModel.class);
                        if (loginModel.getHeader().getCode().equals("0000")) {
                            ((CommonView) mvpView).getDataSuccess(loginModel);
                        } else {
                            ((CommonView) mvpView).getDataFail(loginModel.getHeader().getDesc());
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                } else {
                    LogUtils.loge("首页：未加密:" + model.getMsgType());
                }
            }

            @Override
            public void onFailure(String msg) {
                ((CommonView) mvpView).getDataFail(msg);
            }

            @Override
            public void onFinish() {
                mvpView.hideLoading();
            }
        });
    }

    /**
     * 添加域名估值
     */
    public void adddomain(String encMsg, String signMsg, final String msgType, String secretKeyId) {
        mvpView.showLoading();
        addSubscription(apiStores.adddomain(encMsg, signMsg, msgType, secretKeyId), new ApiCallback<EncryptedResponseModel>(mContext) {
            @Override
            public void onSuccess(EncryptedResponseModel model) {
                LogUtils.loge("MsgType:" + model.getMsgType());
                String msgType = model.getMsgType();
                if (msgType != null && msgType.equals("2")) {//加密
                    String encMsg = model.getEncMsg();
                    LogUtils.loge(model.getEncMsg());
                    try {
                        String platext = DESedeUtil.decrypt(encMsg, spUtils.getSecretKey(), spUtils.getSecretIv());
                        LogUtils.loge("解密后：" + platext);
                        SercetKeyOverdueModel loginModel = GsonTools.getObject(platext, SercetKeyOverdueModel.class);
                        if (loginModel.getHeader().getCode().equals("0000")) {
                            ((CommonView) mvpView).getDataSuccess(loginModel);
                        } else {
                            ((CommonView) mvpView).getDataFail(loginModel.getHeader().getDesc());
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                } else {
                    LogUtils.loge("首页：未加密:" + model.getMsgType());
                }
            }

            @Override
            public void onFailure(String msg) {
                ((CommonView) mvpView).getDataFail(msg);
            }

            @Override
            public void onFinish() {
                mvpView.hideLoading();
            }
        });
    }

    /**
     * 添加私募基金
     */
    public void addsmfund(String encMsg, String signMsg, final String msgType, String secretKeyId) {
        mvpView.showLoading();
        addSubscription(apiStores.addsmfund(encMsg, signMsg, msgType, secretKeyId), new ApiCallback<EncryptedResponseModel>(mContext) {
            @Override
            public void onSuccess(EncryptedResponseModel model) {
                LogUtils.loge("MsgType:" + model.getMsgType());
                String msgType = model.getMsgType();
                if (msgType != null && msgType.equals("2")) {//加密
                    String encMsg = model.getEncMsg();
                    LogUtils.loge(model.getEncMsg());
                    try {
                        String platext = DESedeUtil.decrypt(encMsg, spUtils.getSecretKey(), spUtils.getSecretIv());
                        LogUtils.loge("解密后：" + platext);
                        SercetKeyOverdueModel loginModel = GsonTools.getObject(platext, SercetKeyOverdueModel.class);
                        if (loginModel.getHeader().getCode().equals("0000")) {
                            ((CommonView) mvpView).getDataSuccess(loginModel);
                        } else {
                            ((CommonView) mvpView).getDataFail(loginModel.getHeader().getDesc());
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                } else {
                    LogUtils.loge("首页：未加密:" + model.getMsgType());
                }
            }

            @Override
            public void onFailure(String msg) {
                ((CommonView) mvpView).getDataFail(msg);
            }

            @Override
            public void onFinish() {
                mvpView.hideLoading();
            }
        });
    }

    /**
     * 添加基金
     */
    public void addfund(String encMsg, String signMsg, final String msgType, String secretKeyId) {
        mvpView.showLoading();
        addSubscription(apiStores.addfund(encMsg, signMsg, msgType, secretKeyId), new ApiCallback<EncryptedResponseModel>(mContext) {
            @Override
            public void onSuccess(EncryptedResponseModel model) {
                LogUtils.loge("MsgType:" + model.getMsgType());
                String msgType = model.getMsgType();
                if (msgType != null && msgType.equals("2")) {//加密
                    String encMsg = model.getEncMsg();
                    LogUtils.loge(model.getEncMsg());
                    try {
                        String platext = DESedeUtil.decrypt(encMsg, spUtils.getSecretKey(), spUtils.getSecretIv());
                        LogUtils.loge("解密后：" + platext);
                        SercetKeyOverdueModel loginModel = GsonTools.getObject(platext, SercetKeyOverdueModel.class);
                        if (loginModel.getHeader().getCode().equals("0000")) {
                            ((CommonView) mvpView).getDataSuccess(loginModel);
                        } else {
                            ((CommonView) mvpView).getDataFail(loginModel.getHeader().getDesc());
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                } else {
                    LogUtils.loge("首页：未加密:" + model.getMsgType());
                }
            }

            @Override
            public void onFailure(String msg) {
                ((CommonView) mvpView).getDataFail(msg);
            }

            @Override
            public void onFinish() {
                mvpView.hideLoading();
            }
        });
    }

    /**
     * 编辑股权资产
     */
    public void updateequityassets(String encMsg, String signMsg, final String msgType, String secretKeyId) {
        mvpView.showLoading();
        addSubscription(apiStores.updateequityassets(encMsg, signMsg, msgType, secretKeyId), new ApiCallback<EncryptedResponseModel>(mContext) {
            @Override
            public void onSuccess(EncryptedResponseModel model) {
                LogUtils.loge("MsgType:" + model.getMsgType());
                String msgType = model.getMsgType();
                if (msgType != null && msgType.equals("2")) {//加密
                    String encMsg = model.getEncMsg();
                    LogUtils.loge(model.getEncMsg());
                    try {
                        String platext = DESedeUtil.decrypt(encMsg, spUtils.getSecretKey(), spUtils.getSecretIv());
                        LogUtils.loge("解密后：" + platext);
                        SercetKeyOverdueModel loginModel = GsonTools.getObject(platext, SercetKeyOverdueModel.class);
                        if (loginModel.getHeader().getCode().equals("0000")) {
                            ((CommonView) mvpView).getDataSuccess(loginModel);
                        } else {
                            ((CommonView) mvpView).getDataFail(loginModel.getHeader().getDesc());
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                } else {
                    LogUtils.loge("首页：未加密:" + model.getMsgType());
                }
            }

            @Override
            public void onFailure(String msg) {
                ((CommonView) mvpView).getDataFail(msg);
            }

            @Override
            public void onFinish() {
                mvpView.hideLoading();
            }
        });
    }


    /**
     * 欠款列表
     */
    public void getArrearsList(String encMsg, String signMsg, final String msgType, String secretKeyId) {
//        mvpView.showLoading();
        addSubscription(apiStores.getArrearsList(encMsg, signMsg, msgType, secretKeyId), new ApiCallback<EncryptedResponseModel>(mContext) {
            @Override
            public void onSuccess(EncryptedResponseModel model) {
                LogUtils.loge("MsgType:" + model.getMsgType());
                String msgType = model.getMsgType();
                if (msgType != null && msgType.equals("2")) {//加密
                    String encMsg = model.getEncMsg();
                    LogUtils.loge(model.getEncMsg());
                    try {
                        String platext = DESedeUtil.decrypt(encMsg, spUtils.getSecretKey(), spUtils.getSecretIv());
                        LogUtils.loge("解密后：" + platext);
                        ArrearsModel loginModel = GsonTools.getObject(platext, ArrearsModel.class);
                        if (loginModel.getHeader().getCode().equals("0000")) {
                            ((CommonView) mvpView).getDataSuccess(loginModel);
                        } else {
                            ((CommonView) mvpView).getDataFail(loginModel.getHeader().getDesc());
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                } else {
                    LogUtils.loge("首页：未加密:" + model.getMsgType());

                }
            }

            @Override
            public void onFailure(String msg) {
                ((CommonView) mvpView).getDataFail(msg);
            }

            @Override
            public void onFinish() {
                mvpView.hideLoading();
            }
        });
    }

    /**
     * 欠款列表
     */
    public void getCreditList(String encMsg, String signMsg, final String msgType, String secretKeyId) {
//        mvpView.showLoading();
        addSubscription(apiStores.getCreditList(encMsg, signMsg, msgType, secretKeyId), new ApiCallback<EncryptedResponseModel>(mContext) {
            @Override
            public void onSuccess(EncryptedResponseModel model) {
                LogUtils.loge("MsgType:" + model.getMsgType());
                String msgType = model.getMsgType();
                if (msgType != null && msgType.equals("2")) {//加密
                    String encMsg = model.getEncMsg();
                    LogUtils.loge(model.getEncMsg());
                    try {
                        String platext = DESedeUtil.decrypt(encMsg, spUtils.getSecretKey(), spUtils.getSecretIv());
                        LogUtils.loge("解密后：" + platext);
                        CreditModel loginModel = GsonTools.getObject(platext, CreditModel.class);
                        if (loginModel.getHeader().getCode().equals("0000")) {
                            ((CommonView) mvpView).getDataSuccess(loginModel);
                        } else {
                            ((CommonView) mvpView).getDataFail(loginModel.getHeader().getDesc());
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                } else {
                    LogUtils.loge("首页：未加密:" + model.getMsgType());

                }
            }

            @Override
            public void onFailure(String msg) {
                ((CommonView) mvpView).getDataFail(msg);
            }

            @Override
            public void onFinish() {
                mvpView.hideLoading();
            }
        });
    }

    /**
     * 股权列表
     */
    public void getEquityList(String encMsg, String signMsg, final String msgType, String secretKeyId) {
//        mvpView.showLoading();
        addSubscription(apiStores.getEquityList(encMsg, signMsg, msgType, secretKeyId), new ApiCallback<EncryptedResponseModel>(mContext) {
            @Override
            public void onSuccess(EncryptedResponseModel model) {
                LogUtils.loge("MsgType:" + model.getMsgType());
                String msgType = model.getMsgType();
                if (msgType != null && msgType.equals("2")) {//加密
                    String encMsg = model.getEncMsg();
                    LogUtils.loge(model.getEncMsg());
                    try {
                        String platext = DESedeUtil.decrypt(encMsg, spUtils.getSecretKey(), spUtils.getSecretIv());
                        LogUtils.loge("解密后：" + platext);
                        EquityModel loginModel = GsonTools.getObject(platext, EquityModel.class);
                        if (loginModel.getHeader().getCode().equals("0000")) {
                            ((CommonView) mvpView).getDataSuccess(loginModel);
                        } else {
                            ((CommonView) mvpView).getDataFail(loginModel.getHeader().getDesc());
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                } else {
                    LogUtils.loge("首页：未加密:" + model.getMsgType());

                }
            }

            @Override
            public void onFailure(String msg) {
                ((CommonView) mvpView).getDataFail(msg);
            }

            @Override
            public void onFinish() {
                mvpView.hideLoading();
            }
        });
    }

    /**
     * 预付卡列表
     */
    public void getprepaidcardlist(String encMsg, String signMsg, final String msgType, String secretKeyId) {
//        mvpView.showLoading();
        addSubscription(apiStores.getprepaidcardlist(encMsg, signMsg, msgType, secretKeyId), new ApiCallback<EncryptedResponseModel>(mContext) {
            @Override
            public void onSuccess(EncryptedResponseModel model) {
                String msgType = model.getMsgType();
                if (msgType != null && msgType.equals("2")) {//加密
                    String encMsg = model.getEncMsg();
                    try {
                        String platext = DESedeUtil.decrypt(encMsg, spUtils.getSecretKey(), spUtils.getSecretIv());
                        LogUtils.loge("解密后：" + platext);
                        PrepaidListModel loginModel = GsonTools.getObject(platext, PrepaidListModel.class);
                        if (loginModel.getHeader().getCode().equals("0000")) {
                            ((CommonView) mvpView).getDataSuccess(loginModel);
                        } else {
                            ((CommonView) mvpView).getDataFail(loginModel.getHeader().getDesc());
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                } else {
                    LogUtils.loge("首页：未加密:" + model.getMsgType());
                }
            }

            @Override
            public void onFailure(String msg) {
                ((CommonView) mvpView).getDataFail(msg);
            }

            @Override
            public void onFinish() {
                mvpView.hideLoading();
            }
        });
    }

    /**
     * 域名估值列表
     */
    public void getdomainlist(String encMsg, String signMsg, final String msgType, String secretKeyId) {
//        mvpView.showLoading();
        addSubscription(apiStores.getdomainlist(encMsg, signMsg, msgType, secretKeyId), new ApiCallback<EncryptedResponseModel>(mContext) {
            @Override
            public void onSuccess(EncryptedResponseModel model) {
                String msgType = model.getMsgType();
                if (msgType != null && msgType.equals("2")) {//加密
                    String encMsg = model.getEncMsg();
                    try {
                        String platext = DESedeUtil.decrypt(encMsg, spUtils.getSecretKey(), spUtils.getSecretIv());
                        LogUtils.loge("解密后：" + platext);
                        DominListModel loginModel = GsonTools.getObject(platext, DominListModel.class);
                        if (loginModel.getHeader().getCode().equals("0000")) {
                            ((CommonView) mvpView).getDataSuccess(loginModel);
                        } else {
                            ((CommonView) mvpView).getDataFail(loginModel.getHeader().getDesc());
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                } else {
                    LogUtils.loge("首页：未加密:" + model.getMsgType());
                }
            }

            @Override
            public void onFailure(String msg) {
                ((CommonView) mvpView).getDataFail(msg);
            }

            @Override
            public void onFinish() {
                mvpView.hideLoading();
            }
        });
    }

    /**
     * 自定义分类列表
     */
    public void getcustomassetlist(String encMsg, String signMsg, final String msgType, String secretKeyId) {
//        mvpView.showLoading();
        addSubscription(apiStores.getcustomassetlist(encMsg, signMsg, msgType, secretKeyId), new ApiCallback<EncryptedResponseModel>(mContext) {
            @Override
            public void onSuccess(EncryptedResponseModel model) {
                String msgType = model.getMsgType();
                if (msgType != null && msgType.equals("2")) {//加密
                    String encMsg = model.getEncMsg();
                    try {
                        String platext = DESedeUtil.decrypt(encMsg, spUtils.getSecretKey(), spUtils.getSecretIv());
                        LogUtils.loge("解密后：" + platext);
                        CustomListModel loginModel = GsonTools.getObject(platext, CustomListModel.class);
                        if (loginModel.getHeader().getCode().equals("0000")) {
                            ((CommonView) mvpView).getDataSuccess(loginModel);
                        } else {
                            ((CommonView) mvpView).getDataFail(loginModel.getHeader().getDesc());
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                } else {
                    LogUtils.loge("首页：未加密:" + model.getMsgType());
                }
            }

            @Override
            public void onFailure(String msg) {
                ((CommonView) mvpView).getDataFail(msg);
            }

            @Override
            public void onFinish() {
                mvpView.hideLoading();
            }
        });
    }

    /**
     * 私募基金列表
     */
    public void getsmfundlist(String encMsg, String signMsg, final String msgType, String secretKeyId) {
//        mvpView.showLoading();
        addSubscription(apiStores.getsmfundlist(encMsg, signMsg, msgType, secretKeyId), new ApiCallback<EncryptedResponseModel>(mContext) {
            @Override
            public void onSuccess(EncryptedResponseModel model) {
                String msgType = model.getMsgType();
                if (msgType != null && msgType.equals("2")) {//加密
                    String encMsg = model.getEncMsg();
                    try {
                        String platext = DESedeUtil.decrypt(encMsg, spUtils.getSecretKey(), spUtils.getSecretIv());
                        LogUtils.loge("解密后：" + platext);
                        PrivateOfferingModel loginModel = GsonTools.getObject(platext, PrivateOfferingModel.class);
                        if (loginModel.getHeader().getCode().equals("0000")) {
                            ((CommonView) mvpView).getDataSuccess(loginModel);
                        } else {
                            ((CommonView) mvpView).getDataFail(loginModel.getHeader().getDesc());
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                } else {
                    LogUtils.loge("首页：未加密:" + model.getMsgType());
                }
            }

            @Override
            public void onFailure(String msg) {
                ((CommonView) mvpView).getDataFail(msg);
            }

            @Override
            public void onFinish() {
                mvpView.hideLoading();
            }
        });
    }

    /**
     * 借款列表
     */
    public void getloanassetlist(String encMsg, String signMsg, final String msgType, String secretKeyId) {
//        mvpView.showLoading();
        addSubscription(apiStores.getloanassetlist(encMsg, signMsg, msgType, secretKeyId), new ApiCallback<EncryptedResponseModel>(mContext) {
            @Override
            public void onSuccess(EncryptedResponseModel model) {
                String msgType = model.getMsgType();
                if (msgType != null && msgType.equals("2")) {//加密
                    String encMsg = model.getEncMsg();
                    try {
                        String platext = DESedeUtil.decrypt(encMsg, spUtils.getSecretKey(), spUtils.getSecretIv());
                        LogUtils.loge("解密后：" + platext);
                        LoanlistModel loginModel = GsonTools.getObject(platext, LoanlistModel.class);
                        if (loginModel.getHeader().getCode().equals("0000")) {
                            ((CommonView) mvpView).getDataSuccess(loginModel);
                        } else {
                            ((CommonView) mvpView).getDataFail(loginModel.getHeader().getDesc());
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                } else {
                    LogUtils.loge("首页：未加密:" + model.getMsgType());
                }
            }

            @Override
            public void onFailure(String msg) {
                ((CommonView) mvpView).getDataFail(msg);
            }

            @Override
            public void onFinish() {
                mvpView.hideLoading();
            }
        });
    }

    /**
     * 游戏列表
     */
    public void getgamelist(String encMsg, String signMsg, final String msgType, String secretKeyId) {
//        mvpView.showLoading();
        addSubscription(apiStores.getgamelist(encMsg, signMsg, msgType, secretKeyId), new ApiCallback<EncryptedResponseModel>(mContext) {
            @Override
            public void onSuccess(EncryptedResponseModel model) {
                String msgType = model.getMsgType();
                if (msgType != null && msgType.equals("2")) {//加密
                    String encMsg = model.getEncMsg();
                    try {
                        String platext = DESedeUtil.decrypt(encMsg, spUtils.getSecretKey(), spUtils.getSecretIv());
                        LogUtils.loge("解密后：" + platext);
                        GameListModel loginModel = GsonTools.getObject(platext, GameListModel.class);
                        if (loginModel.getHeader().getCode().equals("0000")) {
                            ((CommonView) mvpView).getDataSuccess(loginModel);
                        } else {
                            ((CommonView) mvpView).getDataFail(loginModel.getHeader().getDesc());
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                } else {
                    LogUtils.loge("首页：未加密:" + model.getMsgType());
                }
            }

            @Override
            public void onFailure(String msg) {
                ((CommonView) mvpView).getDataFail(msg);
            }

            @Override
            public void onFinish() {
                mvpView.hideLoading();
            }
        });
    }

    /**
     * 报销单列表
     */
    public void getexpenseaccountlist(String encMsg, String signMsg, final String msgType, String secretKeyId) {
//        mvpView.showLoading();
        addSubscription(apiStores.getexpenseaccountlist(encMsg, signMsg, msgType, secretKeyId), new ApiCallback<EncryptedResponseModel>(mContext) {
            @Override
            public void onSuccess(EncryptedResponseModel model) {
                String msgType = model.getMsgType();
                if (msgType != null && msgType.equals("2")) {//加密
                    String encMsg = model.getEncMsg();
                    try {
                        String platext = DESedeUtil.decrypt(encMsg, spUtils.getSecretKey(), spUtils.getSecretIv());
                        LogUtils.loge("解密后：" + platext);
                        ReimburseListModel loginModel = GsonTools.getObject(platext, ReimburseListModel.class);
                        if (loginModel.getHeader().getCode().equals("0000")) {
                            ((CommonView) mvpView).getDataSuccess(loginModel);
                        } else {
                            ((CommonView) mvpView).getDataFail(loginModel.getHeader().getDesc());
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                } else {
                    LogUtils.loge("首页：未加密:" + model.getMsgType());
                }
            }

            @Override
            public void onFailure(String msg) {
                ((CommonView) mvpView).getDataFail(msg);
            }

            @Override
            public void onFinish() {
                mvpView.hideLoading();
            }
        });
    }

    /**
     * 固定收益列表
     */
    public void gettrustlist(String encMsg, String signMsg, final String msgType, String secretKeyId) {
//        mvpView.showLoading();
        addSubscription(apiStores.gettrustlist(encMsg, signMsg, msgType, secretKeyId), new ApiCallback<EncryptedResponseModel>(mContext) {
            @Override
            public void onSuccess(EncryptedResponseModel model) {
                LogUtils.loge("MsgType:" + model.getMsgType());
                String msgType = model.getMsgType();
                if (msgType != null && msgType.equals("2")) {//加密
                    String encMsg = model.getEncMsg();
                    LogUtils.loge(model.getEncMsg());
                    try {
                        String platext = DESedeUtil.decrypt(encMsg, spUtils.getSecretKey(), spUtils.getSecretIv());
                        LogUtils.loge("解密后：" + platext);
                        EquityModel loginModel = GsonTools.getObject(platext, EquityModel.class);
                        if (loginModel.getHeader().getCode().equals("0000")) {
                            ((CommonView) mvpView).getDataSuccess(loginModel);
                        } else {
                            ((CommonView) mvpView).getDataFail(loginModel.getHeader().getDesc());
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                } else {
                    LogUtils.loge("首页：未加密:" + model.getMsgType());

                }
            }

            @Override
            public void onFailure(String msg) {
                ((CommonView) mvpView).getDataFail(msg);
            }

            @Override
            public void onFinish() {
                mvpView.hideLoading();
            }
        });
    }


    /**
     * 互联网账号列表
     */
    public void getwebaccountlist(String encMsg, String signMsg, final String msgType, String secretKeyId) {
//        mvpView.showLoading();
        addSubscription(apiStores.getwebaccountlist(encMsg, signMsg, msgType, secretKeyId), new ApiCallback<EncryptedResponseModel>(mContext) {
            @Override
            public void onSuccess(EncryptedResponseModel model) {
                LogUtils.loge("MsgType:" + model.getMsgType());
                String msgType = model.getMsgType();
                if (msgType != null && msgType.equals("2")) {//加密
                    String encMsg = model.getEncMsg();
                    LogUtils.loge(model.getEncMsg());
                    try {
                        String platext = DESedeUtil.decrypt(encMsg, spUtils.getSecretKey(), spUtils.getSecretIv());
                        LogUtils.loge("解密后：" + platext);
                        InternetListModel loginModel = GsonTools.getObject(platext, InternetListModel.class);
                        if (loginModel.getHeader().getCode().equals("0000")) {
                            ((CommonView) mvpView).getDataSuccess(loginModel);
                        } else {
                            ((CommonView) mvpView).getDataFail(loginModel.getHeader().getDesc());
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                } else {
                    LogUtils.loge("首页：未加密:" + model.getMsgType());

                }
            }

            @Override
            public void onFailure(String msg) {
                ((CommonView) mvpView).getDataFail(msg);
            }

            @Override
            public void onFinish() {
                mvpView.hideLoading();
            }
        });
    }

    /**
     * 添加报销单
     */
    public void addExpenseaccount(String encMsg, String signMsg, final String msgType, String secretKeyId) {
        mvpView.showLoading();
        addSubscription(apiStores.addExpenseaccount(encMsg, signMsg, msgType, secretKeyId), new ApiCallback<EncryptedResponseModel>(mContext) {
            @Override
            public void onSuccess(EncryptedResponseModel model) {
                LogUtils.loge("MsgType:" + model.getMsgType());
                String msgType = model.getMsgType();
                if (msgType != null && msgType.equals("2")) {//加密
                    String encMsg = model.getEncMsg();
                    LogUtils.loge(model.getEncMsg());
                    try {
                        String platext = DESedeUtil.decrypt(encMsg, spUtils.getSecretKey(), spUtils.getSecretIv());
                        LogUtils.loge("解密后：" + platext);
                        SercetKeyOverdueModel loginModel = GsonTools.getObject(platext, SercetKeyOverdueModel.class);
                        if (loginModel.getHeader().getCode().equals("0000")) {
                            ((CommonView) mvpView).getDataSuccess(loginModel);
                        } else {
                            ((CommonView) mvpView).getDataFail(loginModel.getHeader().getDesc());
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                } else {
                    LogUtils.loge("首页：未加密:" + model.getMsgType());
                }
            }

            @Override
            public void onFailure(String msg) {
                ((CommonView) mvpView).getDataFail(msg);
            }

            @Override
            public void onFinish() {
                mvpView.hideLoading();
            }
        });
    }


    /**
     * 股权详情
     */
    public void getequityassets(String encMsg, String signMsg, final String msgType, String secretKeyId) {
//        mvpView.showLoading();
        addSubscription(apiStores.getequityassets(encMsg, signMsg, msgType, secretKeyId), new ApiCallback<EncryptedResponseModel>(mContext) {
            @Override
            public void onSuccess(EncryptedResponseModel model) {
                LogUtils.loge("MsgType:" + model.getMsgType());
                String msgType = model.getMsgType();
                if (msgType != null && msgType.equals("2")) {//加密
                    String encMsg = model.getEncMsg();
                    LogUtils.loge(model.getEncMsg());
                    try {
                        String platext = DESedeUtil.decrypt(encMsg, spUtils.getSecretKey(), spUtils.getSecretIv());
                        LogUtils.loge("解密后：" + platext);
                        EquityDetailModel loginModel = GsonTools.getObject(platext, EquityDetailModel.class);
                        if (loginModel.getHeader().getCode().equals("0000")) {
                            ((CommonView) mvpView).getDataSuccess(loginModel);
                        } else {
                            ((CommonView) mvpView).getDataFail(loginModel.getHeader().getDesc());
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                } else {
                    LogUtils.loge("首页：未加密:" + model.getMsgType());
                }
            }

            @Override
            public void onFailure(String msg) {
                ((CommonView) mvpView).getDataFail(msg);
            }

            @Override
            public void onFinish() {
                mvpView.hideLoading();
            }
        });
    }

    /**
     * 贷款详情
     */
    public void getcreditasset(String encMsg, String signMsg, final String msgType, String secretKeyId) {
//        mvpView.showLoading();
        addSubscription(apiStores.getcreditasset(encMsg, signMsg, msgType, secretKeyId), new ApiCallback<EncryptedResponseModel>(mContext) {
            @Override
            public void onSuccess(EncryptedResponseModel model) {
                LogUtils.loge("MsgType:" + model.getMsgType());
                String msgType = model.getMsgType();
                if (msgType != null && msgType.equals("2")) {//加密
                    String encMsg = model.getEncMsg();
                    LogUtils.loge(model.getEncMsg());
                    try {
                        String platext = DESedeUtil.decrypt(encMsg, spUtils.getSecretKey(), spUtils.getSecretIv());
                        LogUtils.loge("解密后：" + platext);
                        CreditDetailModel loginModel = GsonTools.getObject(platext, CreditDetailModel.class);
                        if (loginModel.getHeader().getCode().equals("0000")) {
                            ((CommonView) mvpView).getDataSuccess(loginModel);
                        } else {
                            ((CommonView) mvpView).getDataFail(loginModel.getHeader().getDesc());
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                } else {
                    LogUtils.loge("首页：未加密:" + model.getMsgType());
                }
            }

            @Override
            public void onFailure(String msg) {
                ((CommonView) mvpView).getDataFail(msg);
            }

            @Override
            public void onFinish() {
                mvpView.hideLoading();
            }
        });
    }


    /**
     * 新增黑匣子
     *
     * @param encMsg
     * @param signMsg
     * @param msgType
     * @param secretKeyId
     */
    public void addhjz(String encMsg, String signMsg, final String msgType, String secretKeyId) {
        mvpView.showLoading();
        addSubscription(apiStores.addhjz(encMsg, signMsg, msgType, secretKeyId), new ApiCallback<EncryptedResponseModel>(mContext) {
            @Override
            public void onSuccess(EncryptedResponseModel model) {
                LogUtils.loge("MsgType:" + model.getMsgType());
                String msgType = model.getMsgType();
                if (msgType != null && msgType.equals("2")) {//加密
                    String encMsg = model.getEncMsg();
                    LogUtils.loge(model.getEncMsg());
                    try {
                        String platext = DESedeUtil.decrypt(encMsg, spUtils.getSecretKey(), spUtils.getSecretIv());
                        LogUtils.loge("解密后：" + platext);
                        SercetKeyOverdueModel sercetKeyOverdueModel = GsonTools.getObject(platext, SercetKeyOverdueModel.class);
                        if (sercetKeyOverdueModel.getHeader().getCode().equals("0000")) {
                            ((ThirdView) mvpView).ThirdSuccess(sercetKeyOverdueModel);
                        } else {
                            ((ThirdView) mvpView).ThirdFail(sercetKeyOverdueModel.getHeader().getDesc());
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                } else {
                    LogUtils.loge("首页：未加密:" + model.getMsgType());
                }
            }

            @Override
            public void onFailure(String msg) {
                ((ThirdView) mvpView).ThirdFail(msg);
            }

            @Override
            public void onFinish() {
                mvpView.hideLoading();
            }
        });
    }

    /**
     * 修改黑匣子
     *
     * @param encMsg
     * @param signMsg
     * @param msgType
     * @param secretKeyId
     */
    public void updatehjz(String encMsg, String signMsg, final String msgType, String secretKeyId) {
//        mvpView.showLoading();
        addSubscription(apiStores.updatehjz(encMsg, signMsg, msgType, secretKeyId), new ApiCallback<EncryptedResponseModel>(mContext) {
            @Override
            public void onSuccess(EncryptedResponseModel model) {
                LogUtils.loge("MsgType:" + model.getMsgType());
                String msgType = model.getMsgType();
                if (msgType != null && msgType.equals("2")) {//加密
                    String encMsg = model.getEncMsg();
                    LogUtils.loge(model.getEncMsg());
                    try {
                        String platext = DESedeUtil.decrypt(encMsg, spUtils.getSecretKey(), spUtils.getSecretIv());
                        LogUtils.loge("解密后：------修改黑匣子------" + platext);
                        SercetKeyOverdueModel sercetKeyOverdueModel = GsonTools.getObject(platext, SercetKeyOverdueModel.class);
                        if (sercetKeyOverdueModel.getHeader().getCode().equals("0000")) {
                            ((ThirdView) mvpView).ThirdSuccess(sercetKeyOverdueModel);
                        } else {
                            ((ThirdView) mvpView).ThirdFail(sercetKeyOverdueModel.getHeader().getDesc());
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                } else {
                    LogUtils.loge("首页：未加密:" + model.getMsgType());
                }
            }

            @Override
            public void onFailure(String msg) {
                ((ThirdView) mvpView).ThirdFail(msg);
            }

            @Override
            public void onFinish() {
                mvpView.hideLoading();
            }
        });
    }

    /**
     * 查询黑匣子
     *
     * @param encMsg
     * @param signMsg
     * @param msgType
     * @param secretKeyId
     */
    public void gethjz(String encMsg, String signMsg, final String msgType, String secretKeyId) {
        mvpView.showLoading();
        addSubscription(apiStores.gethjz(encMsg, signMsg, msgType, secretKeyId), new ApiCallback<EncryptedResponseModel>(mContext) {
            @Override
            public void onSuccess(EncryptedResponseModel model) {
                LogUtils.loge("MsgType:" + model.getMsgType());
                String msgType = model.getMsgType();
                if (msgType != null && msgType.equals("2")) {//加密
                    String encMsg = model.getEncMsg();
                    LogUtils.loge(model.getEncMsg());
                    try {
                        String platext = DESedeUtil.decrypt(encMsg, spUtils.getSecretKey(), spUtils.getSecretIv());
                        LogUtils.loge("解密后：----查询黑匣子--------" + platext);
                        GetHjzModel getHjzModel = GsonTools.getObject(platext, GetHjzModel.class);
                        if (getHjzModel.getHeader().getCode().equals("0000")) {
                            ((CommonView) mvpView).getDataSuccess(getHjzModel);
                        } else {
                            ((CommonView) mvpView).getDataFail(getHjzModel.getHeader().getDesc());
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                } else {
                    LogUtils.loge("首页：未加密:" + model.getMsgType());
                }
            }

            @Override
            public void onFailure(String msg) {
                ((CommonView) mvpView).getDataFail(msg);
            }

            @Override
            public void onFinish() {
                mvpView.hideLoading();
            }
        });
    }


    /**
     * 删除黑匣子
     */
    public void delhjz(String encMsg, String signMsg, final String msgType, String secretKeyId) {
//        mvpView.showLoading();
        addSubscription(apiStores.delhjz(encMsg, signMsg, msgType, secretKeyId), new ApiCallback<EncryptedResponseModel>(mContext) {
            @Override
            public void onSuccess(EncryptedResponseModel model) {
                LogUtils.loge("MsgType:" + model.getMsgType());
                String msgType = model.getMsgType();
                if (msgType != null && msgType.equals("2")) {//加密
                    String encMsg = model.getEncMsg();
                    LogUtils.loge(model.getEncMsg());
                    try {
                        String platext = DESedeUtil.decrypt(encMsg, spUtils.getSecretKey(), spUtils.getSecretIv());
                        LogUtils.loge("解密后：----删除黑匣子--------" + platext);
                        SercetKeyOverdueModel sercetKeyOverdueModel = GsonTools.getObject(platext, SercetKeyOverdueModel.class);
                        if (sercetKeyOverdueModel.getHeader().getCode().equals("0000")) {
                            ((OtherView) mvpView).onSuccess(sercetKeyOverdueModel);
                        } else {
                            ((OtherView) mvpView).onFail(sercetKeyOverdueModel.getHeader().getDesc());
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                } else {
                    LogUtils.loge("首页：未加密:" + model.getMsgType());
                }
            }

            @Override
            public void onFailure(String msg) {
                ((OtherView) mvpView).onFail(msg);
            }

            @Override
            public void onFinish() {
                mvpView.hideLoading();
            }
        });
    }


    /**
     * 活动列表接口
     *
     * @param encMsg
     * @param signMsg
     * @param msgType
     * @param secretKeyId
     */
    public void activitylist(String encMsg, String signMsg, final String msgType, String secretKeyId) {
//        mvpView.showLoading();
        addSubscription(apiStores.activitylist(encMsg, signMsg, msgType, secretKeyId), new ApiCallback<EncryptedResponseModel>(mContext) {
            @Override
            public void onSuccess(EncryptedResponseModel model) {
                LogUtils.loge("MsgType:" + model.getMsgType());
                String msgType = model.getMsgType();
                if (msgType != null && msgType.equals("2")) {//加密
                    String encMsg = model.getEncMsg();
                    LogUtils.loge(model.getEncMsg());
                    try {
                        String platext = DESedeUtil.decrypt(encMsg, spUtils.getSecretKey(), spUtils.getSecretIv());
                        LogUtils.loge("解密后：----活动列表接口--------" + platext);
                        HuodongListModel huodongListModel = GsonTools.getObject(platext, HuodongListModel.class);
                        if (huodongListModel.getHeader().getCode().equals("0000")) {
                            ((OtherView) mvpView).onSuccess(huodongListModel);
                        } else {
                            ((OtherView) mvpView).onFail(huodongListModel.getHeader().getDesc());
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                } else {
                    LogUtils.loge("首页：未加密:" + model.getMsgType());
                }
            }

            @Override
            public void onFailure(String msg) {
                ((OtherView) mvpView).onFail(msg);
            }

            @Override
            public void onFinish() {
                mvpView.hideLoading();
            }
        });
    }


    /**
     * 消息列表接口
     *
     * @param encMsg
     * @param signMsg
     * @param msgType
     * @param secretKeyId
     */
    public void messagelist(String encMsg, String signMsg, final String msgType, String secretKeyId) {
//        mvpView.showLoading();
        addSubscription(apiStores.messagelist(encMsg, signMsg, msgType, secretKeyId), new ApiCallback<EncryptedResponseModel>(mContext) {
            @Override
            public void onSuccess(EncryptedResponseModel model) {
                LogUtils.loge("MsgType:" + model.getMsgType());
                String msgType = model.getMsgType();
                if (msgType != null && msgType.equals("2")) {//加密
                    String encMsg = model.getEncMsg();
                    LogUtils.loge(model.getEncMsg());
                    try {
                        String platext = DESedeUtil.decrypt(encMsg, spUtils.getSecretKey(), spUtils.getSecretIv());
                        LogUtils.loge("解密后：----消息列表接口--------" + platext);
                        MessageListModel messageListModel = GsonTools.getObject(platext, MessageListModel.class);
                        if (messageListModel.getHeader().getCode().equals("0000")) {
                            ((OtherView) mvpView).onSuccess(messageListModel);
                        } else {
                            ((OtherView) mvpView).onFail(messageListModel.getHeader().getDesc());
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                } else {
                    LogUtils.loge("首页：未加密:" + model.getMsgType());
                }
            }

            @Override
            public void onFailure(String msg) {
                ((OtherView) mvpView).onFail(msg);
            }

            @Override
            public void onFinish() {
                mvpView.hideLoading();
            }
        });
    }

    /**
     * 徽章大类
     */
    public void badgetypelist(String encMsg, String signMsg, final String msgType, String secretKeyId) {
        mvpView.showLoading();
        addSubscription(apiStores.badgetypelist(encMsg, signMsg, msgType, secretKeyId), new ApiCallback<EncryptedResponseModel>(mContext) {
            @Override
            public void onSuccess(EncryptedResponseModel model) {
                LogUtils.loge("MsgType:" + model.getMsgType());
                String msgType = model.getMsgType();
                if (msgType != null && msgType.equals("2")) {//加密
                    String encMsg = model.getEncMsg();
//                    LogUtils.loge(model.getEncMsg());
                    try {
                        String platext = DESedeUtil.decrypt(encMsg, spUtils.getSecretKey(), spUtils.getSecretIv());
                        LogUtils.loge("解密后：" + platext);
                        HuizhangBigClassModel loginModel = GsonTools.getObject(platext, HuizhangBigClassModel.class);
                        if (loginModel.getHeader().getCode().equals("0000")) {
                            ((CommonView) mvpView).getDataSuccess(loginModel);
                        } else {
                            ((CommonView) mvpView).getDataFail(loginModel.getHeader().getDesc());
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                } else {
                    LogUtils.loge("首页：未加密:" + model.getMsgType());
                }
            }

            @Override
            public void onFailure(String msg) {
                ((CommonView) mvpView).getDataFail(msg);
            }

            @Override
            public void onFinish() {
                mvpView.hideLoading();
            }
        });
    }


    /**
     * 徽章二级类
     */
    public void badgedetaillist(String encMsg, String signMsg, final String msgType, String secretKeyId) {
//        mvpView.showLoading();
        addSubscription(apiStores.badgedetaillist(encMsg, signMsg, msgType, secretKeyId), new ApiCallback<EncryptedResponseModel>(mContext) {
            @Override
            public void onSuccess(EncryptedResponseModel model) {
                LogUtils.loge("MsgType:" + model.getMsgType());
                String msgType = model.getMsgType();
                if (msgType != null && msgType.equals("2")) {//加密
                    String encMsg = model.getEncMsg();
//                    LogUtils.loge(model.getEncMsg());
                    try {
                        String platext = DESedeUtil.decrypt(encMsg, spUtils.getSecretKey(), spUtils.getSecretIv());
                        LogUtils.loge("解密后：" + platext);
                        HuizhangSecondClassModel loginModel = GsonTools.getObject(platext, HuizhangSecondClassModel.class);
                        if (loginModel.getHeader().getCode().equals("0000")) {
                            ((OtherView) mvpView).onSuccess(loginModel);
                        } else {
                            ((OtherView) mvpView).onFail(loginModel.getHeader().getDesc());
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                } else {
                    LogUtils.loge("首页：未加密:" + model.getMsgType());
                }
            }

            @Override
            public void onFailure(String msg) {
                ((OtherView) mvpView).onFail(msg);
            }

            @Override
            public void onFinish() {
                mvpView.hideLoading();
            }
        });
    }


    /**
     * 财富试听获取分享数据
     *
     * @param encMsg
     * @param signMsg
     * @param msgType
     * @param secretKeyId
     */
    public void getavdata(String encMsg, String signMsg, final String msgType, String secretKeyId) {
//        mvpView.showLoading();
        addSubscription(apiStores.getavdata(encMsg, signMsg, msgType, secretKeyId), new ApiCallback<EncryptedResponseModel>(mContext) {
            @Override
            public void onSuccess(EncryptedResponseModel model) {
                LogUtils.loge("MsgType:" + model.getMsgType());
                String msgType = model.getMsgType();
                if (msgType != null && msgType.equals("2")) {//加密
                    String encMsg = model.getEncMsg();
                    LogUtils.loge(model.getEncMsg());
                    try {
                        String platext = DESedeUtil.decrypt(encMsg, spUtils.getSecretKey(), spUtils.getSecretIv());
                        LogUtils.loge("解密后：----财富试听获取分享数据--------" + platext);
                        GetavdataModel getavdataModel = GsonTools.getObject(platext, GetavdataModel.class);
                        if (getavdataModel.getHeader().getCode().equals("0000")) {
                            ((OtherView) mvpView).onSuccess(getavdataModel);
                        } else {
                            ((OtherView) mvpView).onFail(getavdataModel.getHeader().getDesc());
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                } else {
                    LogUtils.loge("首页：未加密:" + model.getMsgType());
                }
            }

            @Override
            public void onFailure(String msg) {
                ((OtherView) mvpView).onFail(msg);
            }

            @Override
            public void onFinish() {
                mvpView.hideLoading();
            }
        });
    }


    /**
     * 三方登录
     */
    public void thirdlogin(String encMsg, String signMsg, final String msgType, String secretKeyId) {
//        mvpView.showLoading();
        addSubscription(apiStores.thirdlogin(encMsg, signMsg, msgType, secretKeyId), new ApiCallback<EncryptedResponseModel>(mContext) {
            @Override
            public void onSuccess(EncryptedResponseModel model) {
                LogUtils.loge("MsgType:" + model.getMsgType());
                String msgType = model.getMsgType();
                if (msgType != null && msgType.equals("2")) {//加密
                    String encMsg = model.getEncMsg();
                    LogUtils.loge(model.getEncMsg());
                    try {
                        String platext = DESedeUtil.decrypt(encMsg, spUtils.getSecretKey(), spUtils.getSecretIv());
                        LogUtils.loge("解密后：----三方登录--------" + platext);
                        ThirdLoginModel thirdLoginModel = GsonTools.getObject(platext, ThirdLoginModel.class);
                        if (thirdLoginModel.getHeader().getCode().equals("0000")) {
                            ((ThirdLoginView) mvpView).loginSuccess(thirdLoginModel);
                        } else {
                            ((ThirdLoginView) mvpView).loginFail(thirdLoginModel.getHeader().getDesc());
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                } else {
                    LogUtils.loge("首页：未加密:" + model.getMsgType());
                }
            }

            @Override
            public void onFailure(String msg) {
                ((ThirdLoginView) mvpView).loginFail(msg);
            }

            @Override
            public void onFinish() {
                mvpView.hideLoading();
            }
        });
    }


    /**
     * 绑定手机号
     *
     * @param encMsg
     * @param signMsg
     * @param msgType
     * @param secretKeyId
     */
    public void thirdbind(String encMsg, String signMsg, final String msgType, String secretKeyId) {
        mvpView.showLoading();
        addSubscription(apiStores.thirdbind(encMsg, signMsg, msgType, secretKeyId), new ApiCallback<EncryptedResponseModel>(mContext) {
            @Override
            public void onSuccess(EncryptedResponseModel model) {
                LogUtils.loge("MsgType:" + model.getMsgType());
                String msgType = model.getMsgType();
                if (msgType != null && msgType.equals("2")) {//加密
                    String encMsg = model.getEncMsg();
                    LogUtils.loge(model.getEncMsg());
                    try {
                        String platext = DESedeUtil.decrypt(encMsg, spUtils.getSecretKey(), spUtils.getSecretIv());
                        LogUtils.loge("解密后：----绑定手机号--------" + platext);
                        BindModel bindModel = GsonTools.getObject(platext, BindModel.class);
                        if (bindModel.getHeader().getCode().equals("0000")) {
                            ((ThirdLoginView) mvpView).loginSuccess(bindModel);
                        } else {
                            ((ThirdLoginView) mvpView).loginFail(bindModel.getHeader().getDesc());
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                } else {
                    LogUtils.loge("首页：未加密:" + model.getMsgType());
                }
            }

            @Override
            public void onFailure(String msg) {
                ((ThirdLoginView) mvpView).loginFail(msg);
            }

            @Override
            public void onFinish() {
                mvpView.hideLoading();
            }
        });
    }


    /**
     * 三方登录解除绑定接口
     */
    public void releasebind(String encMsg, String signMsg, final String msgType, String secretKeyId) {
//        mvpView.showLoading();
        addSubscription(apiStores.releasebind(encMsg, signMsg, msgType, secretKeyId), new ApiCallback<EncryptedResponseModel>(mContext) {
            @Override
            public void onSuccess(EncryptedResponseModel model) {
                LogUtils.loge("MsgType:" + model.getMsgType());
                String msgType = model.getMsgType();
                if (msgType != null && msgType.equals("2")) {//加密
                    String encMsg = model.getEncMsg();
                    LogUtils.loge(model.getEncMsg());
                    try {
                        String platext = DESedeUtil.decrypt(encMsg, spUtils.getSecretKey(), spUtils.getSecretIv());
                        LogUtils.loge("解密后：----三方登录解除绑定接口--------" + platext);
                        SercetKeyOverdueModel sercetKeyOverdueModel = GsonTools.getObject(platext, SercetKeyOverdueModel.class);
                        if (sercetKeyOverdueModel.getHeader().getCode().equals("0000")) {
                            ((CommonView) mvpView).getDataSuccess(sercetKeyOverdueModel);
                        } else {
                            ((CommonView) mvpView).getDataFail(sercetKeyOverdueModel.getHeader().getDesc());
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                } else {
                    LogUtils.loge("首页：未加密:" + model.getMsgType());
                }
            }

            @Override
            public void onFailure(String msg) {
                ((CommonView) mvpView).getDataFail(msg);
            }

            @Override
            public void onFinish() {
                mvpView.hideLoading();
            }
        });
    }


    /**
     * 账户首页绑定三方账号接口
     *
     * @param encMsg
     * @param signMsg
     * @param msgType
     * @param secretKeyId
     */
    public void accountbind(String encMsg, String signMsg, final String msgType, String secretKeyId) {
//        mvpView.showLoading();
        addSubscription(apiStores.accountbind(encMsg, signMsg, msgType, secretKeyId), new ApiCallback<EncryptedResponseModel>(mContext) {
            @Override
            public void onSuccess(EncryptedResponseModel model) {
                LogUtils.loge("MsgType:" + model.getMsgType());
                String msgType = model.getMsgType();
                if (msgType != null && msgType.equals("2")) {//加密
                    String encMsg = model.getEncMsg();
                    LogUtils.loge(model.getEncMsg());
                    try {
                        String platext = DESedeUtil.decrypt(encMsg, spUtils.getSecretKey(), spUtils.getSecretIv());
                        LogUtils.loge("解密后：----账户首页绑定三方账号接口--------" + platext);
                        SercetKeyOverdueModel sercetKeyOverdueModel = GsonTools.getObject(platext, SercetKeyOverdueModel.class);
                        if (sercetKeyOverdueModel.getHeader().getCode().equals("0000")) {
                            ((OtherView) mvpView).onSuccess(sercetKeyOverdueModel);
                        } else {
                            ((OtherView) mvpView).onFail(sercetKeyOverdueModel.getHeader().getDesc());
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                } else {
                    LogUtils.loge("首页：未加密:" + model.getMsgType());
                }
            }

            @Override
            public void onFailure(String msg) {
                ((OtherView) mvpView).onFail(msg);
            }

            @Override
            public void onFinish() {
                mvpView.hideLoading();
            }
        });
    }


    /**
     * 获取短链接接口
     */
    public void getShorturl(String encMsg, String signMsg, final String msgType, String secretKeyId) {
        mvpView.showLoading();
        addSubscription(apiStores.getShorturl(encMsg, signMsg, msgType, secretKeyId), new ApiCallback<EncryptedResponseModel>(mContext) {
            @Override
            public void onSuccess(EncryptedResponseModel model) {
                LogUtils.loge("MsgType:" + model.getMsgType());
                String msgType = model.getMsgType();
                if (msgType != null && msgType.equals("2")) {//加密
                    String encMsg = model.getEncMsg();
                    LogUtils.loge(model.getEncMsg());
                    try {
                        String platext = DESedeUtil.decrypt(encMsg, spUtils.getSecretKey(), spUtils.getSecretIv());
                        LogUtils.loge("解密后：获取短链接接口:" + platext);
                        ShortUrlModel sercetKeyOverdueModel = GsonTools.getObject(platext, ShortUrlModel.class);
                        if (sercetKeyOverdueModel.getHeader().getCode().equals("0000")) {
                            ((OtherView) mvpView).onSuccess(sercetKeyOverdueModel);
                        } else {
                            ((OtherView) mvpView).onFail(sercetKeyOverdueModel.getHeader().getDesc());
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                } else {
                    LogUtils.loge("首页：未加密:" + model.getMsgType());
                }
            }

            @Override
            public void onFailure(String msg) {
                ((OtherView) mvpView).onFail(msg);
            }

            @Override
            public void onFinish() {
                mvpView.hideLoading();
            }
        });
    }


    /**
     * 启动接口
     */
    public void getStart(String encMsg, String signMsg, final String msgType, String secretKeyId) {
        mvpView.showLoading();
        addSubscription(apiStores.getStart(encMsg, signMsg, msgType, secretKeyId), new ApiCallback<EncryptedResponseModel>(mContext) {
            @Override
            public void onSuccess(EncryptedResponseModel model) {
                LogUtils.loge("MsgType:" + model.getMsgType());
                String msgType = model.getMsgType();
                if (msgType != null && msgType.equals("2")) {//加密
                    String encMsg = model.getEncMsg();
                    LogUtils.loge(model.getEncMsg());
                    try {
                        String platext = DESedeUtil.decrypt(encMsg, spUtils.getSecretKey(), spUtils.getSecretIv());
                        LogUtils.loge("启动接口-------:" + platext);
                        StartModel startModel = GsonTools.getObject(platext, StartModel.class);
                        if (startModel.getHeader().getCode().equals("0000")) {
                            ((GetPiccodeView) mvpView).getPiccodeSuccess(startModel);
                        } else {
                            ((GetPiccodeView) mvpView).getPiccodeFail(startModel.getHeader().getDesc());
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                } else {
                    LogUtils.loge("首页：未加密:" + model.getMsgType());
                }
            }

            @Override
            public void onFailure(String msg) {
                ((GetPiccodeView) mvpView).getPiccodeFail(msg);
            }

            @Override
            public void onFinish() {
                mvpView.hideLoading();
            }
        });
    }
}
