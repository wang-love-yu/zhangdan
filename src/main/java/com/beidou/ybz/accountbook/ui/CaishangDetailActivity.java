package com.beidou.ybz.accountbook.ui;

import android.Manifest;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.WebView;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.baidu.mobstat.StatService;
import com.beidou.ybz.accountbook.R;
import com.beidou.ybz.accountbook.mvp.entity.AddOverseasRequestModel;
import com.beidou.ybz.accountbook.mvp.entity.GetavdataModel;
import com.beidou.ybz.accountbook.mvp.entity.RequestBody;
import com.beidou.ybz.accountbook.mvp.entity.ShareModel;
import com.beidou.ybz.accountbook.mvp.other.MvpActivity;
import com.beidou.ybz.accountbook.mvp.presenter.CommonPresenter;
import com.beidou.ybz.accountbook.mvp.view.OtherView;
import com.beidou.ybz.accountbook.retrofit.ApiStores;
import com.beidou.ybz.accountbook.util.ActivityUtils;
import com.beidou.ybz.accountbook.util.AlertDialogUtils;
import com.beidou.ybz.accountbook.util.DESedeUtil;
import com.beidou.ybz.accountbook.util.DialogClickListener;
import com.beidou.ybz.accountbook.util.LogUtils;
import com.beidou.ybz.accountbook.util.TestUrl;
import com.beidou.ybz.accountbook.util.Utils;
import com.beidou.ybz.accountbook.widget.ProgressWebView1;
import com.beidou.ybz.accountbook.widget.morewindow.MoreWindow;
import com.google.gson.Gson;
import com.tbruyelle.rxpermissions.RxPermissions;

import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import rx.functions.Action1;

public class CaishangDetailActivity extends MvpActivity<CommonPresenter> implements OtherView<GetavdataModel> {

    ProgressWebView1 mWebView;
    String url;
    @Bind(R.id.ib_back)
    ImageButton ibBack;
    @Bind(R.id.share)
    ImageButton share;
    @Bind(R.id.relativelayout)
    LinearLayout relativelayout;
    @Bind(R.id.tvTitle)
    TextView tvTitle;
    @Bind(R.id.linear)
    LinearLayout linear;

    private String encMsg, signMsg;
    //财富视听详情页分享数据
    private String id;
    private String shareurl;
    private String imagePath;
    private String intro;
    private String title;


    private String type;// TODO: 2018/1/5 区分来自什么的分享
    private boolean isgo = false;
    private String tag;// TODO: 2018/1/12 区分是否隐藏右上角的分享按钮
    //----分享
    private MoreWindow mMoreWindow;
    private ShareModel shareModel;
    private String cj;//场景
    private String cstc;//新加的测试弹窗
    private String titleGone;//标题不可见
    AlertDialogUtils alertDialogUtils;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_caishang_detail);
        ButterKnife.bind(this);
        initView();
    }

    @Override
    protected CommonPresenter createPresenter() {
        return new CommonPresenter(this, this);
    }

    private void permissions() {
        RxPermissions.getInstance(CaishangDetailActivity.this)
                .request(Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE)//这里填写所需要的权限
                .subscribe(new Action1<Boolean>() {
                    @Override
                    public void call(Boolean aBoolean) {
                        if (aBoolean) {//true表示获取权限成功（注意这里在android6.0以下默认为true）
//                                    toastShow("成功");
                            isgo = true;
                        } else {
                            isgo = false;
                        }
                    }
                });
    }


    public void initView() {
//        permissions();
        setSwipeBackEnable(false);
        mWebView = new ProgressWebView1(CaishangDetailActivity.this, null, tvTitle);
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
        mWebView.setLayoutParams(lp);
//        mWebView.setWebViewClient(new WebViewClient());
        relativelayout.addView(mWebView);
        Intent intent = getIntent();
        url = intent.getStringExtra("url");
        tag = intent.getStringExtra("tag");
        cstc = intent.getStringExtra("cstc");//测试弹窗
        if (tag != null) {
            share.setVisibility(View.GONE);
            tvTitle.setVisibility(View.VISIBLE);
//            ibBack.setVisibility(View.GONE);
        }
        if (url != null) {
            mWebView.loadUrl(url);
        }
        cj = getIntent().getStringExtra("cj");
        id = getIntent().getStringExtra("id");
        titleGone = getIntent().getStringExtra("titleGone");
        if (id != null && !id.equals("")) {
            getavdata(id);
            shareurl = ApiStores.CAISHANG_URL + "fq/av/detail.htm?" + "id=" + id + "&share=" + "1";
        }
        if (titleGone!=null){
            tvTitle.setVisibility(View.GONE);
            share.setVisibility(View.GONE);
        }
        type = getIntent().getStringExtra("type");
        shareModel = new ShareModel();

        alertDialogUtils = new AlertDialogUtils(mActivity);
        alertDialogUtils.setOnDialogClickListener(new DialogClickListener() {
            @Override
            public void clickYes(int which) {
                ActivityUtils.finishActivity(mActivity);
            }

            @Override
            public void clickNo() {
            }
        });
        alertDialogUtils.setMessage("您是否确认要结束本次测试？");
    }

    private void getavdata(String id) {
        RequestBody<AddOverseasRequestModel> loginRequestModel = new RequestBody<>();
        RequestBody.HeaderBean headerBean = new RequestBody.HeaderBean(Utils.getIPAddress(mActivity), spUtils.getSecretKeyId());
        AddOverseasRequestModel requestModel = new AddOverseasRequestModel();

        requestModel.setId(id);

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
        mvpPresenter.getavdata(encMsg, signMsg, "2", spUtils.getSecretKeyId());
    }


    @Override
    protected void onResume() {
        super.onResume();
        mWebView.reload();
        StatService.onPageStart(this, "财商测试");//财商详情页  早读详情页  财富视听详情页
        StatService.onPageStart(this, "财商详情页");
        StatService.onPageStart(this, "早读详情页");
        StatService.onPageStart(this, "财富视听详情页");
    }

    @Override
    protected void onPause() {
        super.onPause();
        StatService.onPageEnd(this, "财商测试");
        StatService.onPageEnd(this, "财商详情页");
        StatService.onPageEnd(this, "早读详情页");
        StatService.onPageEnd(this, "财富视听详情页");
    }

    @Override
    public void onSuccess(GetavdataModel model) {
        imagePath = ApiStores.CMS_IMG_URL + model.getBody().getImagePath();
        intro = model.getBody().getIntro();
        title = model.getBody().getTitle();

        shareModel.setHtmlurl(shareurl);
        shareModel.setTitle("财富视听 | " + title);
        shareModel.setContent(intro);
        shareModel.setImgurl(imagePath);
    }

    @Override
    public void onFail(String model) {

    }

    String CS_URL;

    public class WebViewClient extends android.webkit.WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            LogUtils.loge("-----------哈哈哈-------" + url);
            Map<String, String> paramMap = TestUrl.urlSplit(url);
            if (url.equals("en://zengzhiTab")) {
                //-------进入增值tab
                ActivityUtils.startActivityRightInWithFrom(mActivity, MainActivity.class, "zengzhi");
                ActivityUtils.finishActivity(mActivity);
            } else if (url.startsWith("en://fqContinueStudy?")) {//------------en://fqCourseDetail?id=xxx & type=1(type=1视频，2音频)     打开课程详情页
                //继续学习
                StatService.onEvent(mActivity, "测试结果页点学习按钮", "测试结果页点学习按钮", 1);
                String type = paramMap.get("type");
                String id = paramMap.get("id");
                getVedioDetailEncMsg(id);
                CS_URL = ApiStores.CAISHANG_URL + "fq/coursedetail.htm?" + "encMsg=" + encMsg + "&signMsg=" + signMsg + "&msgType=" + "2" + "&secretKeyId=" + spUtils.getSecretKeyId();
                if (type.equals("1")) {//--------视频详情(当前)
                    startActivity(new Intent(mActivity, CaishangVedioActivity.class)
                            .putExtra("url", CS_URL)
                            .putExtra("id", id)
                            .putExtra("type", "SPXQ")
                            .putExtra("cj", "1"));
                    mActivity.overridePendingTransition(R.anim.left_in, 0);
                } else if (type.equals("2")) {//------音频详情（当前）
                    mActivity.startActivity(new Intent(mActivity, CaishangDetailActivity.class)
                            .putExtra("url", CS_URL)
                            .putExtra("id", id)
                            .putExtra("type", "YPXQ")
                            .putExtra("cj", "1"));
                    mActivity.overridePendingTransition(R.anim.left_in, 0);
                }
            }
            return true;
        }

        @Override
        public void onPageFinished(WebView view, String url) {
            super.onPageFinished(view, url);
            view.loadUrl("javascript:window.local_obj.showSource(" +
                    "document.getElementById('js_content').innerText);");
            LogUtils.loge("------------测试结果-----------" + url);
        }

        @Override
        public void onReceivedError(WebView view, int errorCode,
                                    String description, String failingUrl) {
            super.onReceivedError(view, errorCode, description, failingUrl);
            view.loadUrl("file:///android_asset/error.html");
        }
    }


    @OnClick({R.id.ib_back, R.id.share})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ib_back:
                if (cj != null) {
                    ActivityUtils.startActivityRightInWithFrom(mActivity, MainActivity.class, "caishang");
                    ActivityUtils.finishActivity(mActivity);
                }else if(cstc != null){
                    alertDialogUtils.show();
                } else {
                    ActivityUtils.finishActivity(CaishangDetailActivity.this);
                }
                break;
            case R.id.share:
                if (type != null) {
                    if (type.equals("YPXQ")) {
//                        if (isgo) {
//                            getVedioDetailEncMsg(id);
//                            String CS_URL = ApiStores.CAISHANG_URL + "fq/courseshare.htm?" + "encMsg=" + encMsg + "&signMsg=" + signMsg + "&msgType=" + "2" + "&secretKeyId=" + spUtils.getSecretKeyId();
////                            String CS_URL = ApiStores.CAISHANG_URL + "fq/courseshare.htm?" + "id=" + id;
//                            ActivityUtils.startActivityRightInWithUrl(mActivity, CourseShareActivity.class, CS_URL);
//                        } else {
//                            permissions();
//                        }
                        getVedioDetailEncMsg(id);
                        String CS_URL = ApiStores.CAISHANG_URL + "fq/courseshare.htm?" + "encMsg=" + encMsg + "&signMsg=" + signMsg + "&msgType=" + "2" + "&secretKeyId=" + spUtils.getSecretKeyId();
//                            String CS_URL = ApiStores.CAISHANG_URL + "fq/courseshare.htm?" + "id=" + id;
                        ActivityUtils.startActivityRightInWithUrl(mActivity, CourseShareActivity.class, CS_URL);
                    } else if (type.equals("CFST")) {
                        showMoreWindow(linear);
                    } else if (type.equals("ZDDT")) {
                        if (isgo) {
                            getVedioDetailEncMsg(id);
                            String CS_URL = ApiStores.CAISHANG_URL + "fq/radioshare.htm?" + "encMsg=" + encMsg + "&signMsg=" + signMsg + "&msgType=" + "2" + "&secretKeyId=" + spUtils.getSecretKeyId();
                            ActivityUtils.startActivityRightInWithUrl(mActivity, CourseShareActivity.class, CS_URL);
                        } else {
                            permissions();
                        }
                    }
                }
                break;
        }
    }

    private void getVedioDetailEncMsg(String id) {
        RequestBody<AddOverseasRequestModel> requestBody = new RequestBody<>();
        RequestBody.HeaderBean headerBean = new RequestBody.HeaderBean(Utils.getIPAddress(mActivity), spUtils.getSecretKeyId());
        AddOverseasRequestModel addOverseasRequestModel = new AddOverseasRequestModel();
        addOverseasRequestModel.setId(id);
        requestBody.setBody(addOverseasRequestModel);
        requestBody.setHeader(headerBean);
        Gson gson2 = new Gson();
        String json2 = gson2.toJson(requestBody);
        try {
            encMsg = DESedeUtil.getRequestAfter3DES(spUtils.getSecretKey(), spUtils.getSecretIv(), json2);
            signMsg = DESedeUtil.getRequestAfterSign(encMsg);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mWebView != null) {
            mWebView.clearCache(true); //清空缓存
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                if (relativelayout != null) {
                    relativelayout.removeView(mWebView);
                }
                mWebView.removeAllViews();
                mWebView.destroy();
            } else {
                mWebView.removeAllViews();
                mWebView.destroy();
                if (relativelayout != null) {
                    relativelayout.removeView(mWebView);
                }
            }
            mWebView = null;
        }
    }

    private void showMoreWindow(View view) {
        if (null == mMoreWindow) {
            mMoreWindow = new MoreWindow(mActivity, "web", shareModel);
            mMoreWindow.init();
        }
        mMoreWindow.showMoreWindow(view, 100);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_DOWN) {
            if (cj != null) {
                ActivityUtils.startActivityRightInWithFrom(mActivity, MainActivity.class, "caishang");
                ActivityUtils.finishActivity(mActivity);
            }else if (cstc !=null){
                alertDialogUtils.show();
            }else {
                ActivityUtils.finishActivity(CaishangDetailActivity.this);
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}
