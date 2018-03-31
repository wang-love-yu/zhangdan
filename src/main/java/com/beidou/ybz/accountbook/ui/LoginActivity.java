package com.beidou.ybz.accountbook.ui;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.os.Message;
import android.os.SystemClock;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.baidu.mobstat.StatService;
import com.beidou.ybz.accountbook.R;
import com.beidou.ybz.accountbook.mvp.entity.AddOverseasRequestModel;
import com.beidou.ybz.accountbook.mvp.entity.CheckPiccodeModel;
import com.beidou.ybz.accountbook.mvp.entity.GetPiccodeModel;
import com.beidou.ybz.accountbook.mvp.entity.LoginModel;
import com.beidou.ybz.accountbook.mvp.entity.LoginRequestModel;
import com.beidou.ybz.accountbook.mvp.entity.RequestBody;
import com.beidou.ybz.accountbook.mvp.entity.SercetKeyOverdueModel;
import com.beidou.ybz.accountbook.mvp.entity.ThirdLoginModel;
import com.beidou.ybz.accountbook.mvp.entity.YZMModel;
import com.beidou.ybz.accountbook.mvp.entity.YZMResponseModel;
import com.beidou.ybz.accountbook.mvp.other.MvpActivity;
import com.beidou.ybz.accountbook.mvp.presenter.CommonPresenter;
import com.beidou.ybz.accountbook.mvp.view.CommonView;
import com.beidou.ybz.accountbook.mvp.view.GetPiccodeView;
import com.beidou.ybz.accountbook.mvp.view.OtherView;
import com.beidou.ybz.accountbook.mvp.view.ThirdLoginView;
import com.beidou.ybz.accountbook.mvp.view.ThirdView;
import com.beidou.ybz.accountbook.mvp.view.ValidVercodeView;
import com.beidou.ybz.accountbook.retrofit.ApiStores;
import com.beidou.ybz.accountbook.util.ActivityUtils;
import com.beidou.ybz.accountbook.util.DESedeUtil;
import com.beidou.ybz.accountbook.util.IEditTextChangeListener;
import com.beidou.ybz.accountbook.util.ImeUtil;
import com.beidou.ybz.accountbook.util.LogUtils;
import com.beidou.ybz.accountbook.util.Utils;
import com.beidou.ybz.accountbook.util.WorksSizeCheckUtil;
import com.beidou.ybz.accountbook.widget.ClearEditText;
import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.tencent.connect.UserInfo;
import com.tencent.connect.common.Constants;
import com.tencent.mm.sdk.modelmsg.SendAuth;
import com.tencent.mm.sdk.openapi.IWXAPI;
import com.tencent.mm.sdk.openapi.WXAPIFactory;
import com.tencent.tauth.IUiListener;
import com.tencent.tauth.Tencent;
import com.tencent.tauth.UiError;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.json.JSONObject;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Author: xu.yang on 2017/12/8
 * qq_share:754444814
 * E-mail:754444814@qq.com
 * module:登录
 */
public class LoginActivity extends MvpActivity<CommonPresenter> implements ThirdView<LoginModel>, CommonView<YZMResponseModel>,
        GetPiccodeView<GetPiccodeModel>, OtherView<CheckPiccodeModel>, ValidVercodeView<SercetKeyOverdueModel>,
        ThirdLoginView<ThirdLoginModel> {
    @Bind(R.id.tv_title)
    TextView tvTitle;
    @Bind(R.id.toolbar)
    Toolbar toolbar;
    @Bind(R.id.et_phone)
    ClearEditText etPhone;
    @Bind(R.id.et_yzm)
    EditText etYzm;
    @Bind(R.id.btn_login)
    Button btnLogin;
    @Bind(R.id.btn_yzm)
    Button btnYzm;
    @Bind(R.id.wechatLogin)
    LinearLayout wechatLogin;
    @Bind(R.id.qqLogin)
    LinearLayout qqLogin;
    @Bind(R.id.passwordLogin)
    LinearLayout passwordLogin;
    @Bind(R.id.tvxieyi)
    TextView tvxieyi;
    private String encMsg, signMsg, from;
    AlertDialog dialog;
    ImageView security_layout_close, security_layout_img;
    TextView security_layout_sure;
    EditText security_layout_edittext;
    private boolean jumpToMain;//是否跳到首页
    private boolean jumpToGuide;//是否跳到引导页
    private boolean jumpToHuodong;//是否跳到活动详情页
    private boolean hascontent1, hascontent2;
    public static Tencent mTencent;
    private static boolean isServerSideLogin = false;
    private UserInfo mInfo;
    private IWXAPI api;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);

        //注册微信
        regToWx();
        mTencent = Tencent.createInstance(ApiStores.QQ_APPID, this);

        toolbar.setNavigationIcon(R.drawable.back_black);
        toolbar.setBackgroundColor(getResources().getColor(R.color.colorWhite));
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ImeUtil.hideSoftKeyboard(v);
//                ActivityUtils.finishActivity(LoginActivity.this);
                if (jumpToMain) {
                    ActivityUtils.startActivity(mActivity, MainActivity.class);
                }
                ActivityUtils.finishActivity(LoginActivity.this);
            }
        });

        btnLogin.setEnabled(false);

        EventBus.getDefault().register(this);//在当前界面注册一个订阅者

        WorksSizeCheckUtil.textChangeListener textChangeListener = new WorksSizeCheckUtil.textChangeListener(btnLogin);
        textChangeListener.addAllEditText(etYzm);
        WorksSizeCheckUtil.setChangeListener(new IEditTextChangeListener() {
            @Override
            public void textChange(boolean isHasContent) {
                if (isHasContent) {
                    hascontent2 = true;
                    btnLogin.setEnabled(true);
                    btnLogin.setBackgroundResource(R.drawable.bg1);
                } else {
                    hascontent2 = false;
                    if (!hascontent1) {
                        btnLogin.setEnabled(false);
                        btnLogin.setBackgroundResource(R.drawable.bg_unenabled);
                    }
                }
            }
        });

        etPhone.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {}
            @Override
            public void afterTextChanged(Editable editable) {
                if (!editable.toString().equals("")) {
                    hascontent1 = true;
                    btnLogin.setEnabled(true);
                    btnLogin.setBackgroundResource(R.drawable.bg1);
                } else {
                    hascontent1 = false;
                    if (!hascontent2) {
                        btnLogin.setEnabled(false);
                        btnLogin.setBackgroundResource(R.drawable.bg_unenabled);
                    }
                }
            }
        });

        handlerIntent(getIntent());

        LayoutInflater inflater = LayoutInflater.from(this);
        RelativeLayout layout = (RelativeLayout) inflater.inflate(R.layout.security_layout, null);
        security_layout_close = (ImageView) layout.findViewById(R.id.security_layout_close);
        security_layout_img = (ImageView) layout.findViewById(R.id.security_layout_img);
        security_layout_sure = (TextView) layout.findViewById(R.id.security_layout_sure);
        security_layout_edittext = (EditText) layout.findViewById(R.id.security_layout_edittext);
        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        //通过setView设置我们自己的布局
        builder.setView(layout);
        dialog = builder.create();

        security_layout_close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (dialog != null & dialog.isShowing()) {
                    dialog.dismiss();
                }
            }
        });

        security_layout_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getPiccode();
            }
        });

//        security_layout_sure.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                if (security_layout_edittext.getText().toString().trim().equals("")) {
//                    toastShow("图形验证码不能为空");
//                } else {
//                    checkpiccode();
//                }
//            }
//        });
        security_layout_sure.setOnClickListener(new Utils.OnMultiClickListener() {
            @Override
            public void onMultiClick(View v) {
                if (security_layout_edittext.getText().toString().trim().equals("")) {
                    toastShow("图形验证码不能为空");
                } else {
                    checkpiccode();
                }
            }
        });
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        handlerIntent(intent);
    }

    void handlerIntent(Intent intent) {
        if (intent != null) {
            from = intent.getStringExtra("from");
        }

        if (from != null && from.equals("gesvalidfail") || from != null && from.equals("forgetGes")) {//来自手势验证未通过/忘记手势密码/输入5次为通过
            spUtils.setIsGesture(false);
            spUtils.setIsLogin(false);
            spUtils.setUserId("");
            jumpToMain = true;
        }else if(from != null && from.equals("blackboxguide")){//来自黑匣子引导页面
            jumpToGuide = true;
        }else if(from != null && from.equals("huodongdetail")){//来自黑匣子引导页面
            jumpToHuodong = true;
        } else {
            jumpToMain = false;
        }
    }

    /**
     * 验证码登录成功
     * @param model
     */
    @Override
    public void ThirdSuccess(LoginModel model) {
        spUtils.setUserId(model.getBody().getUserNo());
        spUtils.setPhone(model.getBody().getMobile());
        spUtils.setHidePhone(model.getBody().getHideMobile());
        spUtils.setNickName(model.getBody().getNickName());
        spUtils.setIsLogin(true);

        //关闭当前activity
        toastShow("登录成功", R.drawable.gou_toast);
        EventBus.getDefault().post("loginout");//登录成功，通知登录页、启动页面关闭
        if (jumpToMain) {
//            ActivityUtils.startActivity(mActivity, MainActivity.class);
            ActivityUtils.startActivityRightInWithFrom(mActivity, MainActivity.class, "main");
        }else{
//            ActivityUtils.startActivity(mActivity, MainActivity.class);
        }
        ActivityUtils.finishActivity(mActivity);
    }

    @Override
    public void ThirdFail(String msg) {
//        AlerterFailed(msg);
        toastShow(msg);
    }


    @Override
    protected CommonPresenter createPresenter() {
        return new CommonPresenter(this, this);
    }

    private void Login() {
        RequestBody<LoginRequestModel> loginRequestModel = new RequestBody<>();
        RequestBody.HeaderBean headerBean = new RequestBody.HeaderBean(Utils.getIPAddress(mActivity), spUtils.getSecretKeyId());
        LoginRequestModel loginRequestModel1 = new LoginRequestModel();
        loginRequestModel1.setMobile(etPhone.getText().toString());
        loginRequestModel1.setLoginMode("1");//短信登录
        loginRequestModel1.setMessageCode(etYzm.getText().toString());
        loginRequestModel1.setPwd("");

        loginRequestModel.setBody(loginRequestModel1);
        loginRequestModel.setHeader(headerBean);

        Gson gson2 = new Gson();
        String json2 = gson2.toJson(loginRequestModel);

        try {
            encMsg = DESedeUtil.getRequestAfter3DES(spUtils.getSecretKey(), spUtils.getSecretIv(), json2);
            signMsg = DESedeUtil.getRequestAfterSign(encMsg);
        } catch (Exception e) {
            e.printStackTrace();
        }
        mvpPresenter.login(encMsg, signMsg, "2", spUtils.getSecretKeyId());
    }


    @OnClick({R.id.btn_login, R.id.btn_yzm, R.id.wechatLogin, R.id.qqLogin, R.id.passwordLogin, R.id.tvxieyi})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tvxieyi:
                ActivityUtils.startActivityRightInWithUrl(mActivity, WebActivity.class, ApiStores.XIEYI_URL);
                break;
            case R.id.btn_login:
                if (etPhone.getText().toString().trim().equals("")) {
                    toastShow("请输入正确的手机号");
                } else if (!Utils.isPhone(etPhone.getText().toString().trim())) {
                    toastShow("请输入正确的手机号");
                } else if (etYzm.getText().toString().trim().equals("")) {
                    toastShow("验证码输入错误");
                } else if (etYzm.getText().toString().trim().length() != 6) {
                    toastShow("验证码输入错误");
                } else {
//                    checkvalidcode();//-----验证验证码
                    Login();
                }
                break;
            case R.id.btn_yzm:
                if (etPhone.getText().toString().trim().equals("")) {
                    toastShow("请输入正确的手机号");
                } else if (!Utils.isPhone(etPhone.getText().toString())) {
                    toastShow("请输入正确的手机号");
                } else {
                    sendValidcode();
                }
                break;
            case R.id.wechatLogin:
                LogUtils.loge("----------------"+Utils.isWeChatAppInstalled(mActivity));
                StatService.onEvent(mActivity, "点击微信登录按钮", "[尝试微信登录]",1);
                if (Utils.isWeChatAppInstalled(mActivity)){
                    //登录微信
                    final SendAuth.Req req = new SendAuth.Req();//021lJ33h2ac7FH0OIJ0h2P883h2lJ33C
                    req.scope = "snsapi_userinfo";
                    req.state = "app_wechat";
                    api.sendReq(req);
                }else {
                    toastShow("本设备未安装微信，无法使用微信登录功能");
                }
                break;
            case R.id.qqLogin:
                StatService.onEvent(mActivity, "点击QQ登录按钮", "[尝试QQ登录]",1);
                onClickLogin();
                break;
            case R.id.passwordLogin:
                ActivityUtils.startActivityRightInWithFrom(this, PasswordLoginActivity.class, from);
//                ActivityUtils.finishActivity(mActivity);
                break;
        }
    }


    /**
     * qq登录
     */
    private void thirdlogin_qq(String code, String openId) {
        RequestBody<AddOverseasRequestModel> loginRequestModel = new RequestBody<>();
        RequestBody.HeaderBean headerBean = new RequestBody.HeaderBean(Utils.getIPAddress(mActivity), spUtils.getSecretKeyId());
        AddOverseasRequestModel requestModel = new AddOverseasRequestModel();

        requestModel.setAcctChannel("2");
        requestModel.setCode(code);
        requestModel.setOpenId(openId);

        loginRequestModel.setBody(requestModel);
        loginRequestModel.setHeader(headerBean);
        Gson gson2 = new Gson();
        String json2 = gson2.toJson(loginRequestModel);
        LogUtils.logd(json2);
        try {
            encMsg = DESedeUtil.getRequestAfter3DES(spUtils.getSecretKey(), spUtils.getSecretIv(), json2);
            signMsg = DESedeUtil.getRequestAfterSign(encMsg);
        } catch (Exception e) {
            e.printStackTrace();
        }
        mvpPresenter.thirdlogin(encMsg, signMsg, "2", spUtils.getSecretKeyId());
    }


    /**
     * 微信登录
     */
    private void thirdlogin_wechat(String code) {
        RequestBody<AddOverseasRequestModel> loginRequestModel = new RequestBody<>();
        RequestBody.HeaderBean headerBean = new RequestBody.HeaderBean(Utils.getIPAddress(mActivity), spUtils.getSecretKeyId());
        AddOverseasRequestModel requestModel = new AddOverseasRequestModel();

        requestModel.setAcctChannel("1");
        requestModel.setCode(code);
        requestModel.setOpenId("");

        loginRequestModel.setBody(requestModel);
        loginRequestModel.setHeader(headerBean);
        Gson gson2 = new Gson();
        String json2 = gson2.toJson(loginRequestModel);
        LogUtils.logd(json2);
        try {
            encMsg = DESedeUtil.getRequestAfter3DES(spUtils.getSecretKey(), spUtils.getSecretIv(), json2);
            signMsg = DESedeUtil.getRequestAfterSign(encMsg);
        } catch (Exception e) {
            e.printStackTrace();
        }
        mvpPresenter.thirdlogin(encMsg, signMsg, "2", spUtils.getSecretKeyId());
    }

    /**
     * 获取短信的倒计时线程
     */
    private CountDownTimer SMSTimer = new CountDownTimer(60 * 1000, 1000) {
        @Override
        public void onTick(long millisUntilFinished) {
//            btnYzm.setText("(" + (millisUntilFinished / 1000) + "s" + ")");
//            btnYzm.setClickable(false);

            btnYzm.setText((millisUntilFinished / 1000) + "s" + "可重发");
            btnYzm.setClickable(false);
            btnYzm.setBackgroundResource(R.drawable.bg_unenabled);
            btnYzm.setTextColor(getResources().getColor(R.color.detailColor));
        }

        @Override
        public void onFinish() {
            btnYzm.setText("发送验证码");
            btnYzm.setClickable(true);
            btnYzm.setBackgroundResource(R.drawable.bg_gold);
            btnYzm.setTextColor(getResources().getColor(R.color.colorGold));
        }
    };

    /**
     * 发送验证码
     */
    void sendValidcode() {
        RequestBody<YZMModel> yzmModelRequestBody = new RequestBody<YZMModel>();
        RequestBody.HeaderBean headerBean2 = new RequestBody.HeaderBean(Utils.getIPAddress(mActivity), spUtils.getSecretKeyId());
        YZMModel yzmModel = new YZMModel();
        yzmModel.setValidType("1");//注册
        yzmModel.setMobile(etPhone.getText().toString());
        yzmModelRequestBody.setBody(yzmModel);
        yzmModelRequestBody.setHeader(headerBean2);
        Gson gson2 = new Gson();
        String json2 = gson2.toJson(yzmModelRequestBody);
        try {
            encMsg = DESedeUtil.getRequestAfter3DES(spUtils.getSecretKey(), spUtils.getSecretIv(), json2);
            signMsg = DESedeUtil.getRequestAfterSign(encMsg);
        } catch (Exception e) {
            e.printStackTrace();
        }
        mvpPresenter.sendValidcode(encMsg, signMsg, "2", spUtils.getSecretKeyId());
    }

    /**
     * 验证验证码
     */
    void checkvalidcode() {
        //验证通过，继续下一步
        RequestBody<AddOverseasRequestModel> changeMobileModelRequestBody = new RequestBody<AddOverseasRequestModel>();
        RequestBody.HeaderBean headerBean1 = new RequestBody.HeaderBean(Utils.getIPAddress(mActivity), spUtils.getSecretKeyId());
        AddOverseasRequestModel addOverseasRequestModel = new AddOverseasRequestModel();
        addOverseasRequestModel.setMobile(etPhone.getText().toString());
        addOverseasRequestModel.setValidType("1");
        addOverseasRequestModel.setMessageCode(etYzm.getText().toString().trim());
        changeMobileModelRequestBody.setBody(addOverseasRequestModel);
        changeMobileModelRequestBody.setHeader(headerBean1);
        Gson gson1 = new Gson();
        String json1 = gson1.toJson(changeMobileModelRequestBody);
        try {
            encMsg = DESedeUtil.getRequestAfter3DES(spUtils.getSecretKey(), spUtils.getSecretIv(), json1);
            signMsg = DESedeUtil.getRequestAfterSign(encMsg);
        } catch (Exception e) {
            e.printStackTrace();
        }
        mvpPresenter.checkvalidcode(encMsg, signMsg, "2", spUtils.getSecretKeyId());
    }

    /**
     * 获取图形验证码
     */
    void getPiccode() {
        RequestBody<AddOverseasRequestModel> changeMobileModelRequestBody = new RequestBody<AddOverseasRequestModel>();
        RequestBody.HeaderBean headerBean1 = new RequestBody.HeaderBean(Utils.getIPAddress(mActivity), spUtils.getSecretKeyId());
        AddOverseasRequestModel addOverseasRequestModel = new AddOverseasRequestModel();

        //----添加手机号
        addOverseasRequestModel.setMobile(etPhone.getText().toString());

        changeMobileModelRequestBody.setBody(addOverseasRequestModel);
        changeMobileModelRequestBody.setHeader(headerBean1);

        Gson gson1 = new Gson();
        String json1 = gson1.toJson(changeMobileModelRequestBody);
        try {
            encMsg = DESedeUtil.getRequestAfter3DES(spUtils.getSecretKey(), spUtils.getSecretIv(), json1);
            signMsg = DESedeUtil.getRequestAfterSign(encMsg);
        } catch (Exception e) {
            e.printStackTrace();
        }
        mvpPresenter.getpiccode(encMsg, signMsg, "2", spUtils.getSecretKeyId());
    }

    /**
     * 校验图形验证码
     */
    void checkpiccode() {
        RequestBody<AddOverseasRequestModel> changeMobileModelRequestBody = new RequestBody<AddOverseasRequestModel>();
        RequestBody.HeaderBean headerBean1 = new RequestBody.HeaderBean(Utils.getIPAddress(mActivity), spUtils.getSecretKeyId());
        AddOverseasRequestModel addOverseasRequestModel = new AddOverseasRequestModel();

        addOverseasRequestModel.setPicCode(security_layout_edittext.getText().toString().trim());
        addOverseasRequestModel.setMobile(etPhone.getText().toString());

        changeMobileModelRequestBody.setBody(addOverseasRequestModel);
        changeMobileModelRequestBody.setHeader(headerBean1);
        Gson gson1 = new Gson();
        String json1 = gson1.toJson(changeMobileModelRequestBody);
        try {
            encMsg = DESedeUtil.getRequestAfter3DES(spUtils.getSecretKey(), spUtils.getSecretIv(), json1);
            signMsg = DESedeUtil.getRequestAfterSign(encMsg);
        } catch (Exception e) {
            e.printStackTrace();
        }
        mvpPresenter.checkpiccode(encMsg, signMsg, "2", spUtils.getSecretKeyId());
    }

    private String codeFlag;

    @Override
    public void getDataSuccess(YZMResponseModel model) {
        codeFlag = String.valueOf(model.getBody().getCodeFlag());

        if (!String.valueOf(model.getBody().getCodeFlag()).equals("1")) {
            toastShow("验证码获取成功", R.drawable.gou_toast);
            SMSTimer.start();
        }

        if (codeFlag != null && codeFlag.equals("1")) {
            dialog.show();
            getPiccode();
        }
    }

    @Override
    public void getDataFail(String msg) {
        toastShow(msg);
    }

    @Override
    public void onSuccess(CheckPiccodeModel model) {
        dialog.dismiss();
        if (String.valueOf(model.getBody().getCheckFlag()).equals("1")) {
            SMSTimer.start();
        }
        toastShow("验证成功", R.drawable.gou_toast);
        security_layout_edittext.postDelayed(new Runnable() {
            @Override
            public void run() {
                ImeUtil.hideSoftKeyboard(security_layout_edittext);
            }
        },300);

    }

    @Override
    public void onFail(String model) {
        toastShow("图形验证失败,请重新验证");
        security_layout_edittext.setText("");
        getPiccode();
    }

    @Override
    public void validSuccess(SercetKeyOverdueModel model) {
        Login();
    }

    @Override
    public void validFail(String model) {
        toastShow(model);
    }

    @Override
    public void getPiccodeSuccess(GetPiccodeModel model) {
        Glide.with(this)
                .load(ApiStores.YZM_URL + model.getBody().getPicUrl())
                .into(security_layout_img);
    }

    @Override
    public void getPiccodeFail(String msg) {
        toastShow(msg);
    }


    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK
                && event.getAction() == KeyEvent.ACTION_DOWN) {
            if (jumpToMain) {
                ActivityUtils.startActivity(mActivity, MainActivity.class);
            }
            ActivityUtils.finishActivity(LoginActivity.this);
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }



    /**
     * QQ登录
     */
    private void onClickLogin() {
        if (!mTencent.isSessionValid()) {
            mTencent.login(this, "all", loginListener);
            isServerSideLogin = false;
            Log.d("SDKQQAgentPref", "FirstLaunch_SDK:" + SystemClock.elapsedRealtime());
        } else {
            if (isServerSideLogin) { // Server-Side 模式的登陆, 先退出，再进行SSO登陆
                mTencent.logout(this);
                mTencent.login(this, "all", loginListener);
                isServerSideLogin = false;
                Log.d("SDKQQAgentPref", "FirstLaunch_SDK:" + SystemClock.elapsedRealtime());
                return;
            }
            mTencent.logout(this);
            updateUserInfo();
        }
    }

    IUiListener loginListener = new BaseUiListener() {
        @Override
        protected void doComplete(JSONObject values) {
            Log.d("SDKQQAgentPref", "AuthorSwitch_SDK:" + SystemClock.elapsedRealtime());
            initOpenidAndToken(values);
//            updateUserInfo();
        }
    };


    private class BaseUiListener implements IUiListener {

        @Override
        public void onComplete(Object response) {
            if (null == response) {
//                Util.showResultDialog(MainActivity.this, "返回为空", "登录失败");
                return;
            }
            JSONObject jsonResponse = (JSONObject) response;
            if (null != jsonResponse && jsonResponse.length() == 0) {
//                Util.showResultDialog(MainActivity.this, "返回为空", "登录失败");
                return;
            }
//            Util.showResultDialog(MainActivity.this, response.toString(), "登录成功");
            doComplete((JSONObject) response);
        }

        protected void doComplete(JSONObject values) {

        }

        @Override
        public void onError(UiError e) {
//            Util.toastMessage(MainActivity.this, "onError: " + e.errorDetail);
//            Util.dismissDialog();
        }

        @Override
        public void onCancel() {
//            Util.toastMessage(MainActivity.this, "onCancel: ");
//            Util.dismissDialog();
//            if (isServerSideLogin) {
//                isServerSideLogin = false;
//            }
        }
    }

    public void initOpenidAndToken(JSONObject jsonObject) {
        showLoadingDialog();
        try {
            String token = jsonObject.getString(Constants.PARAM_ACCESS_TOKEN);
            String expires = jsonObject.getString(Constants.PARAM_EXPIRES_IN);
            String openId = jsonObject.getString(Constants.PARAM_OPEN_ID);
            if (!TextUtils.isEmpty(token) && !TextUtils.isEmpty(expires)
                    && !TextUtils.isEmpty(openId)) {
                mTencent.setAccessToken(token, expires);
                mTencent.setOpenId(openId);
                LogUtils.loge("------------" + token + "------------" + expires + "-------------" + openId);
                thirdlogin_qq(token, openId);
            }
        } catch (Exception e) {
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//        Log.d(TAG, "-->onActivityResult " + requestCode  + " resultCode=" + resultCode);
        if (requestCode == Constants.REQUEST_LOGIN ||
                requestCode == Constants.REQUEST_APPBAR) {
            Tencent.onActivityResultData(requestCode, resultCode, data, loginListener);
        }
        super.onActivityResult(requestCode, resultCode, data);
    }


    private void updateUserInfo() {
        if (mTencent != null && mTencent.isSessionValid()) {
            IUiListener listener = new IUiListener() {

                @Override
                public void onError(UiError e) {

                }

                @Override
                public void onComplete(final Object response) {
                    Message msg = new Message();
                    msg.obj = response;
                    msg.what = 0;
                    mHandler.sendMessage(msg);
                    new Thread() {

                        @Override
                        public void run() {
                            JSONObject json = (JSONObject) response;
                            LogUtils.loge(json + "");
//                            if(json.has("figureurl")){
//                                Bitmap bitmap = null;
//                                try {
//                                    bitmap = Util.getbitmap(json.getString("figureurl_qq_2"));
//                                } catch (JSONException e) {
//
//                                }
//                                Message msg = new Message();
//                                msg.obj = bitmap;
//                                msg.what = 1;
//                                mHandler.sendMessage(msg);
//                            }
                        }

                    }.start();
                }

                @Override
                public void onCancel() {

                }
            };
            mInfo = new UserInfo(this, mTencent.getQQToken());
            mInfo.getUserInfo(listener);

        } else {
//            mUserInfo.setText("");
//            mUserInfo.setVisibility(android.view.View.GONE);
//            mUserLogo.setVisibility(android.view.View.GONE);
        }
    }

    @SuppressLint("HandlerLeak")
    Handler mHandler = new Handler() {

        @Override
        public void handleMessage(Message msg) {
            if (msg.what == 0) {
                JSONObject response = (JSONObject) msg.obj;
                if (response.has("nickname")) {
//                    try {
//                        mUserInfo.setVisibility(android.view.View.VISIBLE);
//                        mUserInfo.setText(response.getString("nickname"));
//                    } catch (JSONException e) {
//                        e.printStackTrace();
//                    }
                }
            } else if (msg.what == 1) {
//                Bitmap bitmap = (Bitmap)msg.obj;
//                mUserLogo.setImageBitmap(bitmap);
//                mUserLogo.setVisibility(android.view.View.VISIBLE);
            }
        }

    };


    /**
     * 微信登陆
     */
    private void regToWx() {
        api = WXAPIFactory.createWXAPI(this, ApiStores.WEIXIN_APP_ID, true);
        api.registerApp(ApiStores.WEIXIN_APP_ID);
    }

    /**
     * QQ登录成功
     *
     * @param model
     */
    @Override
    public void loginSuccess(ThirdLoginModel model) {
        dismissLoadingDialog();
        StatService.onEvent(mActivity, "QQ登录成功toast", "[QQ成功登录]",1);
        spUtils.setUserId(model.getBody().getUserNo());
        spUtils.setPhone(model.getBody().getMobile());
        spUtils.setHidePhone(model.getBody().getHideMobile());

        LogUtils.loge("--------验证-----"+spUtils.getHidePhone());
        spUtils.setNickName(model.getBody().getNickName());
        spUtils.setPortraitUrl(model.getBody().getPortraitUrl());
        //----------登录类型
        spUtils.setAcctChannel("2");//QQ
        //----------三方账号openId
        spUtils.setThirdAcctNo(model.getBody().getThirdAcctNo());
        String bindFlag = model.getBody().getBindFlag();
        /**
         * 根据舒服绑定判断是跳到绑定页面还是登录成功页面
         */
        if (bindFlag != null && bindFlag.equals("0")) {
//            ActivityUtils.finishActivity(mActivity);//不需要在这里关闭，绑定成功后会通知关闭
            ActivityUtils.startActivityRightIn(mActivity, BindActivity.class);
        } else {
            spUtils.setIsLogin(true);
            //关闭当前activity
            toastShow("登录成功", R.drawable.gou_toast);
            EventBus.getDefault().post("loginout");//登录成功，通知登录页、启动页面关闭
            if (jumpToMain) {
//                ActivityUtils.startActivity(mActivity, MainActivity.class);
                ActivityUtils.startActivityRightInWithFrom(mActivity,MainActivity.class,"main");
            }else{
//                ActivityUtils.startActivity(mActivity, MainActivity.class);
            }
            ActivityUtils.finishActivity(mActivity);
        }
    }

    @Override
    public void loginFail(String model) {
        dismissLoadingDialog();
    }

    @Override
    protected void onResume() {
        super.onResume();
        StatService.onPageStart(mActivity,"登录页面");
    }

    @Override
    protected void onPause() {
        super.onPause();
        ImeUtil.hideSoftKeyboard(etPhone);
        StatService.onPageEnd(mActivity,"登录页面");
    }


    @Subscribe
    public void onEventMainThread(String a) {
//        toastShow(a);
        if(a != null && a.equals("loginout")) {
           ActivityUtils.finishActivity(mActivity);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);//取消注册
    }
}
