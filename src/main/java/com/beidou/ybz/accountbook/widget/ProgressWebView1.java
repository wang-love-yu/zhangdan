package com.beidou.ybz.accountbook.widget;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import android.util.AttributeSet;
import android.view.View;
import android.webkit.JavascriptInterface;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.baidu.mobstat.StatService;
import com.beidou.ybz.accountbook.R;
import com.beidou.ybz.accountbook.mvp.entity.AddOverseasRequestModel;
import com.beidou.ybz.accountbook.mvp.entity.EncryptedResponseModel;
import com.beidou.ybz.accountbook.mvp.entity.GetHjzModel;
import com.beidou.ybz.accountbook.mvp.entity.RequestBody;
import com.beidou.ybz.accountbook.retrofit.ApiStores;
import com.beidou.ybz.accountbook.retrofit.AppClient;
import com.beidou.ybz.accountbook.ui.BlackboxGuideActivity;
import com.beidou.ybz.accountbook.ui.CaishangDetailActivity;
import com.beidou.ybz.accountbook.ui.CaishangListActivity;
import com.beidou.ybz.accountbook.ui.CaishangVedioActivity;
import com.beidou.ybz.accountbook.ui.LoginActivity;
import com.beidou.ybz.accountbook.ui.MyBlackboxDetailActivity;
import com.beidou.ybz.accountbook.ui.TestResultActivity;
import com.beidou.ybz.accountbook.ui.X5Web2Activity;
import com.beidou.ybz.accountbook.ui.X5WebActivity;
import com.beidou.ybz.accountbook.util.ActivityUtils;
import com.beidou.ybz.accountbook.util.DESedeUtil;
import com.beidou.ybz.accountbook.util.GsonTools;
import com.beidou.ybz.accountbook.util.LogUtils;
import com.beidou.ybz.accountbook.util.SharePreferenceUtil;
import com.beidou.ybz.accountbook.util.TestUrl;
import com.beidou.ybz.accountbook.util.Utils;
import com.google.gson.Gson;
import com.orhanobut.logger.Logger;
import com.tbruyelle.rxpermissions.RxPermissions;

import java.util.Map;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

/**
 * Created by Administrator on 2016/8/5.
 */
public class ProgressWebView1 extends WebView {
    private ProgressBar progressbar;
    boolean blockLoadingNetworkImage = false;
    private String mFailingUrl, mHtml;
    public TextView tv;
    private Activity activity;

    OnContentListener onContentListener;
    public SharePreferenceUtil spUtils;

    @SuppressLint("JavascriptInterface")
    public ProgressWebView1(Activity context, AttributeSet attrs, TextView tvTitle) {
        super(context, attrs);
        activity = context;
        progressbar = new ProgressBar(context, null,
                android.R.attr.progressBarStyleHorizontal);
        progressbar.setLayoutParams(new LayoutParams(LayoutParams.FILL_PARENT,
                4, 0, 0));
        tv = tvTitle;
        spUtils = new SharePreferenceUtil(activity, "xinliangbao");
        Drawable drawable = context.getResources().getDrawable(R.drawable.progress_bar_states);
        progressbar.setProgressDrawable(drawable);
        progressbar.setMax(100);
        addView(progressbar);
        setWebViewClient(new WebViewClient());
        setWebChromeClient(new WebChromeClient());
        //是否可以缩放
        getSettings().setSupportZoom(true);
        getSettings().setBuiltInZoomControls(true);
        //不显示webview缩放按钮
        getSettings().setDisplayZoomControls(false);
        getSettings().setBlockNetworkImage(true);
        getSettings().setJavaScriptEnabled(true);
        getSettings().setUseWideViewPort(true);// 设置此属性，可任意比例缩放
        getSettings().setLoadWithOverviewMode(true);//适配
        getSettings().setAppCacheEnabled(true);//开启 Application Caches 功能

        if (Build.VERSION.SDK_INT >= 21) {
            getSettings().setMixedContentMode(WebSettings.MIXED_CONTENT_ALWAYS_ALLOW);
        }

        blockLoadingNetworkImage = true;
        addJavascriptInterface(new InJavaScriptLocalObj(), "local_obj");
    }

    final class InJavaScriptLocalObj {
        @JavascriptInterface
        public void showSource(String html) {
            LogUtils.logi("html内容:" + html);
            onContentListener.setContent(html);
        }

        @JavascriptInterface
        public void errorReload() {
            Logger.i("reload");

            new Handler(Looper.getMainLooper()).post(new Runnable() {

                @Override
                public void run() {
                    if (mFailingUrl != null) {
                        loadUrl(mFailingUrl);
                    }
                }
            });
        }
    }


    public class WebChromeClient extends android.webkit.WebChromeClient {

        @Override
        public void onProgressChanged(WebView view, int newProgress) {
            Logger.e(newProgress + "==");

            progressbar.setProgress(newProgress);
            progressbar.postInvalidate();
            if (newProgress >= 80) {
                progressbar.setVisibility(View.GONE);
            } else {
                progressbar.setVisibility(View.VISIBLE);
            }
            if (newProgress > 70) {
                if (blockLoadingNetworkImage) {
                    getSettings().setBlockNetworkImage(false);
                    blockLoadingNetworkImage = false;
                }
            }
            super.onProgressChanged(view, newProgress);
        }

        @Override
        public void onReceivedTitle(WebView view, String title) {
            super.onReceivedTitle(view, title);
            LogUtils.logd("title:" + title);
            if (tv != null) {
                tv.setText(title);
            }
//            onContentListener.setTitle(title);
        }
    }

    String CS_URL;
    private String encMsg, signMsg;
    // 两次点击按钮之间的点击间隔不能少于1000毫秒
    private static final int MIN_CLICK_DELAY_TIME = 1000;
    private static long lastClickTime;

    public class WebViewClient extends android.webkit.WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            LogUtils.loge("url:---" + url);
//            url = "en://vaBanner?title=%E6%96%B0%E5%B1%B1%E7%BF%A1%E7%BF%A0%E6%B9%BE%E5%85%AC%E5%AF%93&url=http://m.haitoujia.test/subject/htjxsfcw.html&source=ybz";
            Map<String, String> paramMap = TestUrl.urlSplit(url);
            LogUtils.loge("paramMap:----------------" + paramMap.toString());
            long curClickTime = System.currentTimeMillis();
            if (url.startsWith("en://fqTestLogin")) {//------财商测试，需要登录
                if (spUtils.getIsLogin()) {
                    StatService.onEvent(activity, "财商弹窗点测试按钮", "财商弹窗点测试按钮", 1);
                    getZaoduEncMsg();
                    CS_URL = ApiStores.CAISHANG_URL + "fq/testquestions.htm?" + "encMsg=" + encMsg + "&signMsg=" + signMsg + "&msgType=" + "2" + "&secretKeyId=" + spUtils.getSecretKeyId();
                    activity.startActivity(new Intent(activity, CaishangDetailActivity.class)
                            .putExtra("url", CS_URL)
                            .putExtra("tag", "1")
                            .putExtra("cstc", "1"));//用来判断测试弹窗的
                    activity.overridePendingTransition(R.anim.left_in, 0);
                } else {
                    ActivityUtils.startActivityRightIn(activity, LoginActivity.class);
                }
            } else if (url.startsWith("en://fqCourseDetail?")) {//------------en://fqCourseDetail?id=xxx & type=1(type=1视频，2音频)     打开课程详情页
                String type = paramMap.get("type");
                String id = paramMap.get("id");
                getVedioDetailEncMsg(id);
                CS_URL = ApiStores.CAISHANG_URL + "fq/coursedetail.htm?" + "encMsg=" + encMsg + "&signMsg=" + signMsg + "&msgType=" + "2" + "&secretKeyId=" + spUtils.getSecretKeyId();
                if ((curClickTime - lastClickTime) >= MIN_CLICK_DELAY_TIME) {
                    // 超过点击间隔后再将lastClickTime重置为当前点击时间
                    lastClickTime = curClickTime;
                    if (type.equals("1")) {//--------视频详情(当前)
                        activity.startActivity(new Intent(activity, CaishangVedioActivity.class)
                                .putExtra("url", CS_URL)
                                .putExtra("id", id)
                                .putExtra("type", "SPXQ"));
                        activity.overridePendingTransition(R.anim.left_in, 0);
                    } else if (type.equals("2")) {//------音频详情（当前）
                        activity.startActivity(new Intent(activity, CaishangDetailActivity.class)
                                .putExtra("url", CS_URL)
                                .putExtra("id", id)
                                .putExtra("type", "YPXQ"));
                        activity.overridePendingTransition(R.anim.left_in, 0);
                    }
                }
            } else if (url.startsWith("en://fqRadioList")) {//----打开早读列表页
                getZaoduEncMsg();
                CS_URL = ApiStores.CAISHANG_URL + "fq/radiolist.htm?" + "encMsg=" + encMsg + "&signMsg=" + signMsg + "&msgType=" + "2" + "&secretKeyId=" + spUtils.getSecretKeyId();
                ActivityUtils.startActivityRightInWithUrl(activity, CaishangListActivity.class, CS_URL);
            } else if (url.startsWith("en://fqRadioDetail?")) {//-------en://fqRadioDetail?id=xx----打开早读电台详情页
                String id = paramMap.get("id");
                getZaoduDetailEncMsg(id);
                CS_URL = ApiStores.CAISHANG_URL + "fq/radiodetail.htm?" + "encMsg=" + encMsg + "&signMsg=" + signMsg + "&msgType=" + "2" + "&secretKeyId=" + spUtils.getSecretKeyId();
                if ((curClickTime - lastClickTime) >= MIN_CLICK_DELAY_TIME) {
                    // 超过点击间隔后再将lastClickTime重置为当前点击时间
                    lastClickTime = curClickTime;
                    activity.startActivity(new Intent(activity, CaishangDetailActivity.class)
                            .putExtra("url", CS_URL)
                            .putExtra("id", id)
                            .putExtra("type", "ZDDT"));
                    activity.overridePendingTransition(R.anim.left_in, 0);
                }
            } else if (url.startsWith("en://fqAvList")) {
                //---------打开财富视听列表页
                getZaoduEncMsg();
                CS_URL = ApiStores.CAISHANG_URL + "fq/avlist.htm?" + "encMsg=" + encMsg + "&signMsg=" + signMsg + "&msgType=" + "2" + "&secretKeyId=" + spUtils.getSecretKeyId();
                ActivityUtils.startActivityRightInWithUrl(activity, CaishangListActivity.class, CS_URL);
            } else if (url.startsWith("en://fqAvDetail?")) {//------en://fqAvDetail?id=xxx &type=0(0为图文 1为文字 2为音频 3 为视频)
                //------------打开财富视听详情页
                String id = paramMap.get("id");
                getZaoduDetailEncMsg(id);
                String type = paramMap.get("type");
                CS_URL = ApiStores.CAISHANG_URL + "fq/av/detail.htm?" + "id=" + id;
                if ((curClickTime - lastClickTime) >= MIN_CLICK_DELAY_TIME) {
                    // 超过点击间隔后再将lastClickTime重置为当前点击时间
                    lastClickTime = curClickTime;
                    if (type.equals("3")) {
                        activity.startActivity(new Intent(activity, CaishangVedioActivity.class)
                                .putExtra("url", CS_URL)
                                .putExtra("id", id)
                                .putExtra("type", "CFST"));
                        activity.overridePendingTransition(R.anim.left_in, 0);
                    } else {
                        activity.startActivity(new Intent(activity, CaishangDetailActivity.class)
                                .putExtra("url", CS_URL)
                                .putExtra("id", id)
                                .putExtra("type", "CFST"));
                        activity.overridePendingTransition(R.anim.left_in, 0);
                    }
                }
            } else if (url.startsWith("en://fqScList")) {//----------------打开已学课程列表
                getZaoduEncMsg1();
                CS_URL = ApiStores.CAISHANG_URL + "fq/tosclist.htm?" + "encMsg=" + encMsg + "&signMsg=" + signMsg + "&msgType=" + "2" + "&secretKeyId=" + spUtils.getSecretKeyId();
                ActivityUtils.startActivityRightInWithUrl(activity, CaishangListActivity.class, CS_URL);
            } else if (url.startsWith("en://fqScDetail?")) {//------en://fqScDetail?id=xx & type=xx & studyNo=xx------打开已学课程详情
                //------打开已学课程详情
                String id = paramMap.get("id");
                String studyNo = paramMap.get("studyno");
                String type = paramMap.get("type");
                getZaoduEncMsg2(id, studyNo, spUtils.getPortraitUrl());
                CS_URL = ApiStores.CAISHANG_URL + "fq//scdetail.htm?" + "encMsg=" + encMsg + "&signMsg=" + signMsg + "&msgType=" + "2" + "&secretKeyId=" + spUtils.getSecretKeyId();
                if ((curClickTime - lastClickTime) >= MIN_CLICK_DELAY_TIME) {
                    // 超过点击间隔后再将lastClickTime重置为当前点击时间
                    lastClickTime = curClickTime;
                    switch (type) {
                        case "1":
                            activity.startActivity(new Intent(activity, CaishangVedioActivity.class)
                                    .putExtra("url", CS_URL)
                                    .putExtra("id", id)
                                    .putExtra("type", "SPXQ"));
                            activity.overridePendingTransition(R.anim.left_in, 0);
                            break;
                        case "2":
                            activity.startActivity(new Intent(activity, CaishangDetailActivity.class)
                                    .putExtra("url", CS_URL)
                                    .putExtra("id", id)
                                    .putExtra("type", "YPXQ"));
                            activity.overridePendingTransition(R.anim.left_in, 0);
//                        ActivityUtils.startActivityRightInWithUrl(activity, CaishangDetailActivity.class, CS_URL);
                            break;
                    }
                }
            } else if (url.equals("en://fqTest")) {
                //------进入财商测试页
//                Toast.makeText(activity,"fqTest",Toast.LENGTH_LONG).show();
                StatService.onEvent(activity, "财商弹窗点测试按钮", "财商弹窗点测试按钮", 1);
                getZaoduEncMsg();
                CS_URL = ApiStores.CAISHANG_URL + "fq/testquestions.htm?" + "encMsg=" + encMsg + "&signMsg=" + signMsg + "&msgType=" + "2" + "&secretKeyId=" + spUtils.getSecretKeyId();
                activity.startActivity(new Intent(activity, CaishangDetailActivity.class)
                        .putExtra("url", CS_URL)
                        .putExtra("tag", "1")
                        .putExtra("cstc", "1"));//用来判断测试弹窗的
                activity.overridePendingTransition(R.anim.left_in, 0);
            } else if (url.equals("en://fqTestResult")) {
                //-----进入测试结果页
                getZaoduEncMsg();
                CS_URL = ApiStores.CAISHANG_URL + "fq/testresult.htm?" + "encMsg=" + encMsg + "&signMsg=" + signMsg + "&msgType=" + "2" + "&secretKeyId=" + spUtils.getSecretKeyId();
                activity.startActivity(new Intent(activity, CaishangListActivity.class)
                        .putExtra("url", CS_URL)
                        .putExtra("tag", "1")
                        .putExtra("cj", "1"));
                activity.overridePendingTransition(R.anim.left_in, 0);
//                ActivityUtils.startActivityRightInWithUrl(activity, CaishangListActivity.class, CS_URL);
            } else if (url.equals("en://fqTestShare")) {
                //-----进入测试分享页
                RxPermissions.getInstance(activity)
                        .request(Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE)//这里填写所需要的权限
                        .subscribe(new Action1<Boolean>() {
                            @Override
                            public void call(Boolean aBoolean) {
                                if (aBoolean) {//true表示获取权限成功（注意这里在android6.0以下默认为true）
                                    StatService.onEvent(activity, "测试结果页点炫耀按钮", "测试结果页点炫耀按钮", 1);
                                    getZaoduEncMsg();
                                    CS_URL = ApiStores.CAISHANG_URL + "fq/testshare.htm?" + "encMsg=" + encMsg + "&signMsg=" + signMsg + "&msgType=" + "2" + "&secretKeyId=" + spUtils.getSecretKeyId();
                                    activity.startActivity(new Intent(activity, TestResultActivity.class)
                                            .putExtra("url", CS_URL)
                                            .putExtra("tag", "1"));
                                    activity.overridePendingTransition(R.anim.left_in, 0);
                                } else {

                                }
                            }
                        });
            } else if (url.startsWith("en://startStudy?")) {
                //开始学习  en://startStudy?id=xx&type=1(type=1视频，2音频)
                String type = paramMap.get("type");
                String id = paramMap.get("id");
                if (type.equals("1")) {//--------视频详情(当前)
                    getVedioDetailEncMsg(id);
                    CS_URL = ApiStores.CAISHANG_URL + "fq/coursedetail.htm?" + "encMsg=" + encMsg + "&signMsg=" + signMsg + "&msgType=" + "2" + "&secretKeyId=" + spUtils.getSecretKeyId();
                    activity.startActivity(new Intent(activity, CaishangVedioActivity.class)
                            .putExtra("url", CS_URL)
                            .putExtra("id", id)
                            .putExtra("type", "SPXQ")
                            .putExtra("cj", "1"));
                    activity.overridePendingTransition(R.anim.left_in, 0);
                } else if (type.equals("2")) {//------音频详情（当前）
                    getVedioDetailEncMsg(id);
                    CS_URL = ApiStores.CAISHANG_URL + "fq/coursedetail.htm?" + "encMsg=" + encMsg + "&signMsg=" + signMsg + "&msgType=" + "2" + "&secretKeyId=" + spUtils.getSecretKeyId();
                    activity.startActivity(new Intent(activity, CaishangDetailActivity.class)
                            .putExtra("url", CS_URL)
                            .putExtra("id", id)
                            .putExtra("type", "YPXQ")
                            .putExtra("cj", "1"));
                    activity.overridePendingTransition(R.anim.left_in, 0);
                }
            } else if (url.startsWith("en://startFqTest")) {
                //------进入财商测试页
                StatService.onEvent(activity, "财商首页Banner点测试按钮", "财商首页Banner点测试按钮", 1);
                getZaoduEncMsg();
                CS_URL = ApiStores.CAISHANG_URL + "fq/testquestions.htm?" + "encMsg=" + encMsg + "&signMsg=" + signMsg + "&msgType=" + "2" + "&secretKeyId=" + spUtils.getSecretKeyId();
                activity.startActivity(new Intent(activity, CaishangDetailActivity.class)
                        .putExtra("url", CS_URL)
                        .putExtra("tag", "1")
                        .putExtra("cstc", "1"));
                activity.overridePendingTransition(R.anim.left_in, 0);
            } else if (url.startsWith("en://againFqTest")) {
                //------重新测试
                StatService.onEvent(activity, "测试结果页点重测按钮", "测试结果页点重测按钮", 1);
                getZaoduEncMsg();
                CS_URL = ApiStores.CAISHANG_URL + "fq/testquestions.htm?" + "encMsg=" + encMsg + "&signMsg=" + signMsg + "&msgType=" + "2" + "&secretKeyId=" + spUtils.getSecretKeyId();
                activity.startActivity(new Intent(activity, CaishangDetailActivity.class)
                        .putExtra("url", CS_URL)
                        .putExtra("tag", "1")
                        .putExtra("cstc", "1"));
                activity.overridePendingTransition(R.anim.left_in, 0);
            } else if (url.startsWith("en://vaPinxuan")) {//点击进入品宣
                CS_URL = paramMap.get("url");
                if (CS_URL.equals(ApiStores.CaifubaoIndex)) {
//                    if (spUtils.getIsLogin()) {
                    requestUrl();
//                    } else {
//                        activity.startActivity(new Intent(activity, LoginActivity.class));
//                    }
                } else {
                    activity.startActivity(new Intent(activity, X5WebActivity.class)
                            .putExtra("url", CS_URL));
                }
            } else if (url.startsWith("en://vaBanner")) {//打开banner图
                CS_URL = paramMap.get("url");
                String source = paramMap.get("source");
                activity.startActivity(new Intent(activity, X5WebActivity.class)
                        .putExtra("url", CS_URL + "?source=" + source));
            } else if (url.startsWith("en://goCfb")) {//打开财富宝首页
//                if (spUtils.getIsLogin()){
                requestUrl();
//                }else {
//                    activity.startActivity(new Intent(activity, LoginActivity.class));
//                }
            } else if (url.startsWith("en://vaSxLc")) {//打开省心理财产品详情页
                CS_URL = paramMap.get("url");
                String source = paramMap.get("source");
                activity.startActivity(new Intent(activity, X5WebActivity.class)
                        .putExtra("url", CS_URL + "?source=" + source));
            } else if (url.startsWith("en://vaHlhs")) {//打开汇率换算
                String source = paramMap.get("source");
                activity.startActivity(new Intent(activity, X5WebActivity.class)
                        .putExtra("url", ApiStores.Hlhs + "?source=" + source));
            } else if (url.startsWith("en://vaDkjs")) {//打开贷款计算
                String source = paramMap.get("source");
                activity.startActivity(new Intent(activity, X5WebActivity.class)
                        .putExtra("url", ApiStores.Dkjs + "?source=" + source));
            } else if (url.startsWith("en://fqHxz")) {//打开黑匣子
                if (spUtils.getIsLogin()) {
                    gethjz();//查询黑匣子是否开启
                } else {
                    ActivityUtils.startActivityRightIn(activity, BlackboxGuideActivity.class);
                }
            } else if (url.startsWith("en://fqTestSuject")) {//财商首页点击立即测试 进入测试专题页
                getZaoduEncMsg();
                CS_URL = ApiStores.CAISHANG_URL + "fq/cstestsubject.htm?" + "encMsg=" + encMsg + "&signMsg=" + signMsg + "&msgType=" + "2" + "&secretKeyId=" + spUtils.getSecretKeyId();
                activity.startActivity(new Intent(activity, CaishangDetailActivity.class)
                        .putExtra("url", CS_URL)
                        .putExtra("titleGone", "titleGone"));
                activity.overridePendingTransition(R.anim.left_in, 0);
            } else {
                view.loadUrl(url);
            }
            return true;
        }

        @Override
        public void onPageFinished(WebView view, String url) {
            super.onPageFinished(view, url);
            tv.setText(view.getTitle());
            view.loadUrl("javascript:window.local_obj.showSource(" +
                    "document.getElementById('js_content').innerText);");
        }

        @Override
        public void onReceivedError(WebView view, int errorCode,
                                    String description, String failingUrl) {
            super.onReceivedError(view, errorCode, description, failingUrl);
            mFailingUrl = failingUrl;
            Logger.d(failingUrl);
            //加载出错的自定义界面
            view.loadUrl("file:///android_asset/error.html");
        }
    }

    @Override
    protected void onScrollChanged(int l, int t, int oldl, int oldt) {
        LayoutParams lp = (LayoutParams) progressbar.getLayoutParams();
        lp.x = l;
        lp.y = t;
        progressbar.setLayoutParams(lp);
        super.onScrollChanged(l, t, oldl, oldt);
    }

    public interface OnContentListener {
        void setContent(String content);

        void setTitle(String title);
    }


    private void getVedioDetailEncMsg(String id) {
        RequestBody<AddOverseasRequestModel> requestBody = new RequestBody<>();
        RequestBody.HeaderBean headerBean = new RequestBody.HeaderBean(Utils.getIPAddress(activity), spUtils.getSecretKeyId());
        AddOverseasRequestModel addOverseasRequestModel = new AddOverseasRequestModel();

        addOverseasRequestModel.setUserNo(spUtils.getUserId());
        addOverseasRequestModel.setId(id);
        addOverseasRequestModel.setPortraitUrl(spUtils.getPortraitUrl());

        requestBody.setBody(addOverseasRequestModel);
        requestBody.setHeader(headerBean);
        Gson gson2 = new Gson();
        String json2 = gson2.toJson(requestBody);
        LogUtils.loge("-------铭文------" + json2);
        try {
            encMsg = DESedeUtil.getRequestAfter3DES(spUtils.getSecretKey(), spUtils.getSecretIv(), json2);
            signMsg = DESedeUtil.getRequestAfterSign(encMsg);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**
     * 什么都不传
     */
    private void getZaoduEncMsg() {
        RequestBody<AddOverseasRequestModel> requestBody = new RequestBody<>();
        RequestBody.HeaderBean headerBean = new RequestBody.HeaderBean(Utils.getIPAddress(activity), spUtils.getSecretKeyId());
        AddOverseasRequestModel addOverseasRequestModel = new AddOverseasRequestModel();
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

    /**
     * 只传用户号
     */
    private void getZaoduEncMsg1() {
        RequestBody<AddOverseasRequestModel> requestBody = new RequestBody<>();
        RequestBody.HeaderBean headerBean = new RequestBody.HeaderBean(Utils.getIPAddress(activity), spUtils.getSecretKeyId());
        AddOverseasRequestModel addOverseasRequestModel = new AddOverseasRequestModel();
        addOverseasRequestModel.setUserNo(spUtils.getUserId());
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


    private void getZaoduEncMsg2(String id, String studyNo, String portraitUrl) {
        RequestBody<AddOverseasRequestModel> requestBody = new RequestBody<>();
        RequestBody.HeaderBean headerBean = new RequestBody.HeaderBean(Utils.getIPAddress(activity), spUtils.getSecretKeyId());
        AddOverseasRequestModel addOverseasRequestModel = new AddOverseasRequestModel();
        addOverseasRequestModel.setId(id);
        addOverseasRequestModel.setStudyNo(studyNo);
        addOverseasRequestModel.setPortraitUrl(portraitUrl);
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


    private void getZaoduDetailEncMsg(String id) {
        RequestBody<AddOverseasRequestModel> requestBody = new RequestBody<>();
        RequestBody.HeaderBean headerBean = new RequestBody.HeaderBean(Utils.getIPAddress(activity), spUtils.getSecretKeyId());
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


    /**
     * 查询黑匣子
     */
    public void gethjz() {
        RequestBody<AddOverseasRequestModel> loginRequestModel = new RequestBody<>();
        RequestBody.HeaderBean headerBean = new RequestBody.HeaderBean(Utils.getIPAddress(activity), spUtils.getSecretKeyId());
        AddOverseasRequestModel requestModel = new AddOverseasRequestModel();
        requestModel.setUserNo(spUtils.getUserId());
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
//        showLoadingDialog();
        ApiStores apiStores = AppClient.retrofit(getContext()).create(ApiStores.class);
        Observable<EncryptedResponseModel> observable = apiStores.gethjz(encMsg, signMsg, "2", spUtils.getSecretKeyId());
        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<EncryptedResponseModel>() {
                    @Override
                    public void onCompleted() {
//                        activity.dismissLoadingDialog();
                    }

                    @Override
                    public void onError(Throwable e) {
                    }

                    @Override
                    public void onNext(EncryptedResponseModel model) {
                        LogUtils.loge("MsgType:" + model.getMsgType());
                        String msgType = model.getMsgType();
                        if (msgType != null && msgType.equals("2")) {//加密
                            String encMsg = model.getEncMsg();
                            LogUtils.loge(model.getEncMsg());
                            try {
                                String platext = DESedeUtil.decrypt(encMsg, spUtils.getSecretKey(), spUtils.getSecretIv());
                                LogUtils.loge("解密后：------查询黑匣子----------" + platext);
                                GetHjzModel getHjzModel = GsonTools.getObject(platext, GetHjzModel.class);
                                if (getHjzModel.getHeader().getCode().equals("0000")) {
                                    if (getHjzModel.getBody().getInfoDto() == null) {
                                        spUtils.setOpenblackBox(false);
                                        ActivityUtils.startActivityRightIn(activity, BlackboxGuideActivity.class);//BlackboxUnsettingActivity
                                    } else {
                                        spUtils.setOpenblackBox(true);
                                        ActivityUtils.startActivityRightIn(activity, MyBlackboxDetailActivity.class);//BlackboxDetailActivity
                                    }
                                } else {
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


    //财富宝首页
    public void requestUrl() {
        RequestBody<AddOverseasRequestModel> requestBody = new RequestBody<>();
        RequestBody.HeaderBean headerBean = new RequestBody.HeaderBean(Utils.getIPAddress(activity), spUtils.getSecretKeyId());
        AddOverseasRequestModel requestModel = new AddOverseasRequestModel();
        requestModel.setUserNo(spUtils.getUserId());

        requestBody.setBody(requestModel);
        requestBody.setHeader(headerBean);

        Gson gson2 = new Gson();
        String json2 = gson2.toJson(requestBody);
        LogUtils.loge("请求参数：" + json2);
        try {
            encMsg = DESedeUtil.getRequestAfter3DES(ApiStores.key, ApiStores.iv, json2);
            LogUtils.loge("-----ggggg-----" + DESedeUtil.decrypt(encMsg, ApiStores.key, ApiStores.iv));
            signMsg = DESedeUtil.getRequestAfterSign(encMsg);
        } catch (Exception e) {
            e.printStackTrace();
        }

        String url = ApiStores.CaifubaoIndex + "?encMsg=" + encMsg;
        LogUtils.loge("请求url：" + url);
        Intent in = new Intent(activity, X5Web2Activity.class);
        in.putExtra("url", url);
        activity.startActivity(in);
        activity.overridePendingTransition(R.anim.left_in, 0);
    }

}
