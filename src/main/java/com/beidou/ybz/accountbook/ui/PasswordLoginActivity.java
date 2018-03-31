package com.beidou.ybz.accountbook.ui;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.baidu.mobstat.StatService;
import com.beidou.ybz.accountbook.R;
import com.beidou.ybz.accountbook.mvp.entity.LoginModel;
import com.beidou.ybz.accountbook.mvp.entity.LoginRequestModel;
import com.beidou.ybz.accountbook.mvp.entity.RequestBody;
import com.beidou.ybz.accountbook.mvp.other.MvpActivity;
import com.beidou.ybz.accountbook.mvp.presenter.CommonPresenter;
import com.beidou.ybz.accountbook.mvp.view.ThirdView;
import com.beidou.ybz.accountbook.util.ActivityUtils;
import com.beidou.ybz.accountbook.util.DESedeUtil;
import com.beidou.ybz.accountbook.util.IEditTextChangeListener;
import com.beidou.ybz.accountbook.util.ImeUtil;
import com.beidou.ybz.accountbook.util.LogUtils;
import com.beidou.ybz.accountbook.util.Utils;
import com.beidou.ybz.accountbook.util.WorksSizeCheckUtil;
import com.beidou.ybz.accountbook.widget.ClearEditText;
import com.beidou.ybz.accountbook.widget.PasswordEditText;
import com.google.gson.Gson;

import org.greenrobot.eventbus.EventBus;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class PasswordLoginActivity extends MvpActivity<CommonPresenter> implements ThirdView<LoginModel> {
    @Bind(R.id.toolbar)
    Toolbar toolbar;
    @Bind(R.id.et_phone)
    ClearEditText etPhone;
    @Bind(R.id.et_yzm)
    PasswordEditText etYzm;
    @Bind(R.id.btn_login)
    Button btnLogin;
    @Bind(R.id.tv_title)
    TextView tvTitle;
    private String encMsg, signMsg, from;
    private boolean jumpToMain;//是否调跳到首页
    private boolean jumpToGuide;//是否调跳到引导页
    private boolean jumpToHuodong;//是否跳到活动详情页
    //    AlertDialogUtils alertDialogUtils;
    private boolean hascontent1, hascontent2;
    //声明一个AlertDialog构造器
    private AlertDialog.Builder builder;
    private AlertDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_password_login);
        ButterKnife.bind(this);

        btnLogin.setEnabled(false);
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
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

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
        tvTitle.setText("密码登录");
        tvTitle.setTextColor(getResources().getColor(R.color.txt_color));
        toolbar.setNavigationIcon(R.drawable.back_black);
        toolbar.setBackgroundColor(getResources().getColor(R.color.colorWhite));
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ImeUtil.hideSoftKeyboard(v);
                LogUtils.loge("-------jumpToMain--------" + jumpToMain);
                if (jumpToMain) {
//                    ActivityUtils.startActivity(mActivity, MainActivity.class);
                }
                ActivityUtils.finishActivity(PasswordLoginActivity.this);
            }
        });

//        alertDialogUtils = new AlertDialogUtils(mActivity);
//        alertDialogUtils.setOnDialogClickListener(new DialogClickListener() {
//            @Override
//            public void clickYes(int which) {
//                ActivityUtils.startActivity(mActivity, MainActivity.class);
//            }
//
//            @Override
//            public void clickNo() {
//                ActivityUtils.startActivity(mActivity, MainActivity.class);
//            }
//        });
        showSimpleDialog();
    }


    //显示基本Dialog
    private void showSimpleDialog() {
        builder = new AlertDialog.Builder(this);

        builder.setTitle("提示");
        builder.setMessage("账号密码错误5次，已达到最大错误数，请2小时候后再使用密码登录！");

        //监听下方button点击事件
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                ActivityUtils.startActivity(mActivity, MainActivity.class);
            }
        });
//        builder.setNegativeButton(R.string.negative_button, new DialogInterface.OnClickListener() {
//            @Override
//            public void onClick(DialogInterface dialogInterface, int i) {
//                Toast.makeText(getApplicationContext(), R.string.toast_negative, Toast.LENGTH_SHORT).show();
//            }
//        });

        //设置对话框是可取消的
        builder.setCancelable(true);
        dialog = builder.create();
//        dialog.show();
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
        } else if(from != null && from.equals("huodongdetail")){//来自黑匣子引导页面
            jumpToHuodong = true;
        }else {
            jumpToMain = false;
        }
    }

    @Override
    protected CommonPresenter createPresenter() {
        return new CommonPresenter(this, this);
    }

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
        StatService.onEvent(mActivity, "密码登录成功toast", "[密码登录成功]",1);
        if (jumpToMain) {
//            ActivityUtils.startActivity(mActivity, MainActivity.class);
            ActivityUtils.startActivityRightInWithFrom(mActivity, MainActivity.class, "main");
        }else if(jumpToGuide){
            ActivityUtils.startActivity(mActivity, BlackboxGuideActivity.class);
        }else if(jumpToHuodong){
            ActivityUtils.startActivity(mActivity, HuodongDetailActivity.class);
        }else{
//            ActivityUtils.startActivity(mActivity, MainActivity.class);
        }
        ActivityUtils.finishActivity(mActivity);
    }

    @Override
    public void ThirdFail(String msg) {
        if (msg.equals("密码错误五次")) {//--------账号密码错误5次，已达到最大错误数，请2小时候后再使用密码登录！
//            alertDialogUtils.setMessage("账号密码错误5次，已达到最大错误数，请2小时候后再使用密码登录！");
//            alertDialogUtils.show();
            dialog.show();
        } else if (msg.contains("网络") || msg.contains("服务器")) {
            toastShow(msg);
        } else {
            toastShow("账号密码输入错误，请重试");
        }
    }

    @OnClick(R.id.btn_login)
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_login:
                StatService.onEvent(mActivity, "点击密码登录按钮", "[尝试密码登录]",1);
                if (etPhone.getText().toString().trim().equals("")) {
                    toastShow("请输入正确的手机号");
                } else if (!Utils.isPhone(etPhone.getText().toString())) {
                    toastShow("请输入正确的手机号");
                } else if (etYzm.getText().toString().trim().equals("")) {
                    toastShow("请输入密码");
                } else {
                    Login();
                }
                break;
        }
    }

    private void Login() {
        RequestBody<LoginRequestModel> loginRequestModel = new RequestBody<>();
        RequestBody.HeaderBean headerBean = new RequestBody.HeaderBean(Utils.getIPAddress(mActivity), spUtils.getSecretKeyId());
        LoginRequestModel loginRequestModel1 = new LoginRequestModel();
        loginRequestModel1.setMobile(etPhone.getText().toString());
        loginRequestModel1.setLoginMode("2");//短信登录
//        loginRequestModel1.setMessageCode(etYzm.getText().toString());
        loginRequestModel1.setPwd(etYzm.getText().toString());
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

    @Override
    protected void onResume() {
        super.onResume();
        StatService.onPageStart(mActivity, "密码登录页面");
    }

    @Override
    protected void onPause() {
        super.onPause();
        ImeUtil.hideSoftKeyboard(etPhone);
        StatService.onPageEnd(mActivity, "密码登录页面");
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK
                && event.getAction() == KeyEvent.ACTION_DOWN) {
            if (jumpToMain) {
//                ActivityUtils.startActivity(mActivity, MainActivity.class);
            }
            ActivityUtils.finishActivity(mActivity);
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}
