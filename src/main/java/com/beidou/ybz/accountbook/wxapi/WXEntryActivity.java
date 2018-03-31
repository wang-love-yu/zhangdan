package com.beidou.ybz.accountbook.wxapi;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;
import android.widget.Toast;

import com.baidu.mobstat.StatService;
import com.beidou.ybz.accountbook.R;
import com.beidou.ybz.accountbook.mvp.entity.AddOverseasRequestModel;
import com.beidou.ybz.accountbook.mvp.entity.EncryptedResponseModel;
import com.beidou.ybz.accountbook.mvp.entity.RequestBody;
import com.beidou.ybz.accountbook.mvp.entity.SercetKeyOverdueModel;
import com.beidou.ybz.accountbook.mvp.entity.ThirdLoginModel;
import com.beidou.ybz.accountbook.retrofit.ApiStores;
import com.beidou.ybz.accountbook.retrofit.AppClient;
import com.beidou.ybz.accountbook.ui.BindActivity;
import com.beidou.ybz.accountbook.ui.MainActivity;
import com.beidou.ybz.accountbook.util.ActivityUtils;
import com.beidou.ybz.accountbook.util.DESedeUtil;
import com.beidou.ybz.accountbook.util.GsonTools;
import com.beidou.ybz.accountbook.util.LogUtils;
import com.beidou.ybz.accountbook.util.SharePreferenceUtil;
import com.beidou.ybz.accountbook.util.ToastUtils;
import com.beidou.ybz.accountbook.util.Utils;
import com.beidou.ybz.accountbook.widget.LoadingDialog;
import com.google.gson.Gson;
import com.tencent.mm.sdk.constants.ConstantsAPI;
import com.tencent.mm.sdk.modelbase.BaseReq;
import com.tencent.mm.sdk.modelbase.BaseResp;
import com.tencent.mm.sdk.modelmsg.SendAuth;
import com.tencent.mm.sdk.modelmsg.WXAppExtendObject;
import com.tencent.mm.sdk.modelmsg.WXMediaMessage;
import com.tencent.mm.sdk.openapi.IWXAPI;
import com.tencent.mm.sdk.openapi.IWXAPIEventHandler;
import com.tencent.mm.sdk.openapi.WXAPIFactory;

import org.greenrobot.eventbus.EventBus;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class WXEntryActivity extends AppCompatActivity implements IWXAPIEventHandler {
    SharePreferenceUtil spUtils;
    private LoadingDialog loadingDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
//        setContentView(R.layout.activity_weixin);

        IWXAPI api = WXAPIFactory.createWXAPI(this, ApiStores.WEIXIN_APP_ID, true);
        api.handleIntent(getIntent(), this);
        spUtils = new SharePreferenceUtil(this, "xinliangbao");
        loadingDialog = new LoadingDialog(this);
        LogUtils.loge("------onCreate-------");
        loadingDialog.show();
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        IWXAPI api = WXAPIFactory.createWXAPI(this, ApiStores.WEIXIN_APP_ID, true);
        api.handleIntent(getIntent(), this);
        spUtils = new SharePreferenceUtil(this, "xinliangbao");
        LogUtils.loge("------onNewIntent-------");
    }

    public void onResume() {
        super.onResume();
    }

    public void onPause() {
        super.onPause();
    }


    @Override
    public void onReq(BaseReq baseReq) {
        switch (baseReq.getType()) {
            case ConstantsAPI.COMMAND_GETMESSAGE_FROM_WX:
                break;
            case ConstantsAPI.COMMAND_SHOWMESSAGE_FROM_WX:
                break;
        }
    }

    @Override
    public void onResp(BaseResp baseResp) {
        LogUtils.loge("------微信登录的code-----onResp--");
        int result = 0;
        switch (baseResp.errCode) {
            case BaseResp.ErrCode.ERR_OK:
                switch (baseResp.getType()) {
                    case ConstantsAPI.COMMAND_SENDAUTH:
                        //登录回调,处理登录成功的逻辑
                        //获取code
                        if (spUtils == null) {
                            spUtils = new SharePreferenceUtil(this, "xinliangbao");
                        }
                        String code = ((SendAuth.Resp) baseResp).code;//先强转 然后.code
                        LogUtils.loge("------微信登录的code-------" + code);
                        if (spUtils.getAccountbindWechat() != null && spUtils.getAccountbindWechat().equals("1")) {
                            accountbind(code);
                        } else {
                            retrofit(code);
                        }
                        break;
                    case ConstantsAPI.COMMAND_SENDMESSAGE_TO_WX:
                        //分享回调,处理分享成功后的逻辑
                        ToastUtils.toast(WXEntryActivity.this, R.drawable.gou_toast, "分享成功");
                        finish();
                        break;
                }
                LogUtils.loge("ERR_OK");

                break;
            case BaseResp.ErrCode.ERR_USER_CANCEL:
                WXEntryActivity.this.finish();
                break;
            case BaseResp.ErrCode.ERR_AUTH_DENIED:
                WXEntryActivity.this.finish();
                break;
            default:
                WXEntryActivity.this.finish();
                break;
        }
    }

    /**
     * 账户首页绑定三方账号接口(微信)
     */
    private void accountbind(String code) {
        if (spUtils == null) {
            spUtils = new SharePreferenceUtil(this, "xinliangbao");
        }
        RequestBody<AddOverseasRequestModel> loginRequestModel = new RequestBody<>();
        RequestBody.HeaderBean headerBean = new RequestBody.HeaderBean(Utils.getIPAddress(this), spUtils.getSecretKeyId());
        AddOverseasRequestModel requestModel = new AddOverseasRequestModel();
        requestModel.setAcctChannel("1");//qq
        requestModel.setCode(code);
        requestModel.setUserNo(spUtils.getUserId());
        loginRequestModel.setBody(requestModel);
        loginRequestModel.setHeader(headerBean);
        Gson gson2 = new Gson();
        String json2 = gson2.toJson(loginRequestModel);
        try {
            encMsg = DESedeUtil.getRequestAfter3DES(spUtils.getSecretKey(), spUtils.getSecretIv(), json2);
            signMsg = DESedeUtil.getRequestAfterSign(encMsg);
        } catch (Exception e) {
            e.printStackTrace();
        }
        ApiStores apiStores = AppClient.retrofit(this).create(ApiStores.class);
        Observable<EncryptedResponseModel> observable = apiStores.accountbind(encMsg, signMsg, "2", spUtils.getSecretKeyId());
        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<EncryptedResponseModel>() {
                    @Override
                    public void onCompleted() {
                        LogUtils.loge("onCompleted()");
                    }

                    @Override
                    public void onError(Throwable e) {
                        LogUtils.loge("onError()" + e.getMessage());
                    }

                    @Override
                    public void onNext(EncryptedResponseModel model) {
                        if(loadingDialog != null) loadingDialog.dismiss();
                        LogUtils.loge("MsgType:" + model.getMsgType());
                        String msgType = model.getMsgType();
                        if (msgType != null && msgType.equals("2")) {//加密
                            String encMsg = model.getEncMsg();
                            LogUtils.loge(model.getEncMsg());
                            try {
                                String platext = DESedeUtil.decrypt(encMsg, spUtils.getSecretKey(), spUtils.getSecretIv());
                                LogUtils.loge("解密后：------账户首页绑定三方账号接口----------" + platext);
                                SercetKeyOverdueModel sercetKeyOverdueModel = GsonTools.getObject(platext, SercetKeyOverdueModel.class);
                                if (sercetKeyOverdueModel.getHeader().getCode().equals("0000")) {
                                    LogUtils.logd("登录成功22222222222222222222222222");
//                                            ActivityUtils.startActivityRightIn(WXEntryActivity.this, PersonalInfoActivity.class);
                                    ActivityUtils.finishActivity(WXEntryActivity.this);
                                } else {
                                    ToastUtils.toast(WXEntryActivity.this, sercetKeyOverdueModel.getHeader().getDesc());
                                    ActivityUtils.finishActivity(WXEntryActivity.this);
                                }
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        } else {
                            LogUtils.loge("首页：未加密:" + model.getMsgType());
                        }
                    }

                });
    }

    private String encMsg, signMsg;

    private void retrofit(String code) {
        if (spUtils == null) {
            spUtils = new SharePreferenceUtil(this, "xinliangbao");
        }
        RequestBody<AddOverseasRequestModel> loginRequestModel = new RequestBody<>();
        RequestBody.HeaderBean headerBean = new RequestBody.HeaderBean(Utils.getIPAddress(this), spUtils.getSecretKeyId());
        AddOverseasRequestModel requestModel = new AddOverseasRequestModel();

        LogUtils.loge("spUtils.getSecretKeyId()：：" + spUtils.getSecretKeyId());

        requestModel.setAcctChannel("1");
        requestModel.setCode(code);
//        requestModel.setOpenId("");

        loginRequestModel.setBody(requestModel);
        loginRequestModel.setHeader(headerBean);
        Gson gson2 = new Gson();
        String json2 = gson2.toJson(loginRequestModel);
        try {
            encMsg = DESedeUtil.getRequestAfter3DES(spUtils.getSecretKey(), spUtils.getSecretIv(), json2);
            signMsg = DESedeUtil.getRequestAfterSign(encMsg);
        } catch (Exception e) {
            e.printStackTrace();
        }

        ApiStores apiStores = AppClient.retrofit(this).create(ApiStores.class);
        Observable<EncryptedResponseModel> observable = apiStores.thirdlogin(encMsg, signMsg, "2", spUtils.getSecretKeyId());
        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<EncryptedResponseModel>() {
                    @Override
                    public void onCompleted() {
                        LogUtils.loge("onCompleted()");
                    }

                    @Override
                    public void onError(Throwable e) {
                        LogUtils.loge("onError()" + e.getMessage());
                    }

                    @Override
                    public void onNext(EncryptedResponseModel model) {
//                        dismissLoadingDialog();
                        if(loadingDialog != null) loadingDialog.dismiss();
                        StatService.onEvent(WXEntryActivity.this, "微信登录成功toast", "[微信成功登录]",1);
                        LogUtils.loge("解密后：------微信登录成功toast----------");
                        LogUtils.loge("MsgType:" + model.getMsgType());
                        String msgType = model.getMsgType();
                        if (msgType != null && msgType.equals("2")) {//加密
                            String encMsg = model.getEncMsg();
                            LogUtils.loge(model.getEncMsg());
                            try {
                                String platext = DESedeUtil.decrypt(encMsg, spUtils.getSecretKey(), spUtils.getSecretIv());
                                LogUtils.loge("解密后：------微信登陆----------" + platext);
                                ThirdLoginModel thirdLoginModel = GsonTools.getObject(platext, ThirdLoginModel.class);
                                if (thirdLoginModel.getHeader().getCode().equals("0000")) {
                                    spUtils.setUserId(thirdLoginModel.getBody().getUserNo());
                                    spUtils.setPhone(thirdLoginModel.getBody().getMobile());
                                    spUtils.setHidePhone(thirdLoginModel.getBody().getHideMobile());

//                                    //----------用户号
//                                    if (!thirdLoginModel.getBody().getUserNo().equals("")) {
//                                        spUtils.setUserId(thirdLoginModel.getBody().getUserNo());
//                                    } else {
//                                        spUtils.setUserId("");
//                                    }
//                                    //----------手机号
//                                    if (!thirdLoginModel.getBody().getMobile().equals("")) {
//                                        spUtils.setPhone(thirdLoginModel.getBody().getMobile());
//                                    } else {
//                                        spUtils.setPhone("");
//                                    }
                                    //---------隐藏的手机号
//                                    if (!thirdLoginModel.getBody().getHideMobile().equals("")) {
//                                        spUtils.setHidePhone(thirdLoginModel.getBody().getHideMobile());
//                                    } else {
//                                        spUtils.setHidePhone("");
//                                    }
                                    //----------昵称
                                    spUtils.setNickName(thirdLoginModel.getBody().getNickName());
                                    //----------头像
                                    spUtils.setPortraitUrl(thirdLoginModel.getBody().getPortraitUrl());
                                    //----------是否绑定
//                                    spUtils.setBindFlag(thirdLoginModel.getBody().getBindFlag());
                                    //----------登录类型
                                    spUtils.setAcctChannel("1");//QQ
                                    //----------三方账号openId
                                    spUtils.setThirdAcctNo(thirdLoginModel.getBody().getThirdAcctNo());

                                    if (thirdLoginModel.getBody().getBindFlag().equals("0")) {
                                        ActivityUtils.finishActivity(WXEntryActivity.this);
                                        ActivityUtils.startActivityRightIn(WXEntryActivity.this, BindActivity.class);
                                    } else {
                                        spUtils.setIsLogin(true);
                                        //关闭当前activity
                                        LogUtils.logd("登录成功1111111111111111111111111111");
                                        ToastUtils.toast(WXEntryActivity.this,R.drawable.gou_toast, "登录成功");

//                                        if (jumpToMain) {
//                                            ActivityUtils.startActivity(mActivity, MainActivity.class);
//                                        }
                                        if(spUtils.getIsFromGes()) {
                                            ActivityUtils.startActivity(WXEntryActivity.this, MainActivity.class);
                                            overridePendingTransition(R.anim.fade_in2, R.anim.fade_out2);
                                        }
//                                        ActivityUtils.startActivityRightInWithFrom(mActivity,MainActivity.class,"main");
                                        ActivityUtils.finishActivity(WXEntryActivity.this);
                                        EventBus.getDefault().post("loginout");//登录成功，通知登录页面关闭
                                    }
                                } else {
                                    ToastUtils.toast(WXEntryActivity.this, thirdLoginModel.getHeader().getDesc());
                                    ActivityUtils.finishActivity(WXEntryActivity.this);
                                }
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        } else {
                            LogUtils.loge("首页：未加密:" + model.getMsgType());
                        }
                    }

                });
    }

    /**
     * 处理微信发出的向第三方应用请求app message
     * <p>
     * 在微信客户端中的聊天页面有“添加工具”，可以将本应用的图标添加到其中
     * 此后点击图标，下面的代码会被执行。Demo仅仅只是打开自己而已，但你可
     * 做点其他的事情，包括根本不打开任何页面
     */
    public void onGetMessageFromWXReq(WXMediaMessage msg) {
        if (msg != null) {
            Intent iLaunchMyself = getPackageManager().getLaunchIntentForPackage(getPackageName());
            startActivity(iLaunchMyself);
        }
    }

    /**
     * 处理微信向第三方应用发起的消息
     * <p>
     * 此处用来接收从微信发送过来的消息，比方说本demo在wechatpage里面分享
     * 应用时可以不分享应用文件，而分享一段应用的自定义信息。接受方的微信
     * 客户端会通过这个方法，将这个信息发送回接收方手机上的本demo中，当作
     * 回调。
     * <p>
     * 本Demo只是将信息展示出来，但你可做点其他的事情，而不仅仅只是Toast
     */
    @SuppressLint("WrongConstant")
    public void onShowMessageFromWXReq(WXMediaMessage msg) {
        if (msg != null && msg.mediaObject != null
                && (msg.mediaObject instanceof WXAppExtendObject)) {
            WXAppExtendObject obj = (WXAppExtendObject) msg.mediaObject;
            Toast.makeText(this, obj.extInfo, Toast.LENGTH_SHORT).show();
        }
    }
}
