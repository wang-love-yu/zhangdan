package com.beidou.ybz.accountbook.ui;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.WebView;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.baidu.mobstat.StatService;
import com.beidou.ybz.accountbook.R;
import com.beidou.ybz.accountbook.util.ActivityUtils;
import com.beidou.ybz.accountbook.widget.ProgressWebView1;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class CaishangListActivity extends BaseActivity {


    ProgressWebView1 mWebView;
    String url;
    @Bind(R.id.ib_back)
    ImageButton ibBack;
    @Bind(R.id.tvTitle)
    TextView tvTitle;
    @Bind(R.id.top)
    RelativeLayout top;
    @Bind(R.id.relativelayout)
    LinearLayout relativelayout;
    private String tag;
    private String cj;// TODO: 2018/1/17 区分场景，来分辨返回键事件

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_caishang_list);
        ButterKnife.bind(this);
        initView();
    }

    public void initView() {
        setSwipeBackEnable(false);
        mWebView = new ProgressWebView1(CaishangListActivity.this, null, tvTitle);
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);

        mWebView.setLayoutParams(lp);
//        mWebView.setWebViewClient(new WebViewClient());
        relativelayout.addView(mWebView);

        Intent intent = getIntent();
        url = intent.getStringExtra("url");
        if (url != null) {
            mWebView.loadUrl(url);
        }

        tag = intent.getStringExtra("tag");
        if (tag != null) {
//            ibBack.setVisibility(View.GONE);
        }
        cj = intent.getStringExtra("cj");
    }

    public class WebViewClient extends android.webkit.WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            return true;
        }

        @Override
        public void onPageFinished(WebView view, String url) {
            super.onPageFinished(view, url);
            view.loadUrl("javascript:window.local_obj.showSource(" +
                    "document.getElementById('js_content').innerText);");
        }

        @Override
        public void onReceivedError(WebView view, int errorCode,
                                    String description, String failingUrl) {
            super.onReceivedError(view, errorCode, description, failingUrl);
            view.loadUrl("file:///android_asset/error.html");
        }
    }


    @OnClick({R.id.ib_back})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ib_back:
                if (tag!=null){
                    ActivityUtils.startActivityRightInWithFrom(mActivity, MainActivity.class, "caishang");
                    ActivityUtils.finishActivity(mActivity);
                }else {
                    ActivityUtils.finishActivity(CaishangListActivity.this);
                }
                break;
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

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_DOWN) {
            if (cj!=null){
//                return true;
                ActivityUtils.startActivityRightInWithFrom(mActivity, MainActivity.class, "caishang");
                ActivityUtils.finishActivity(mActivity);
            }
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    protected void onResume() {
        super.onResume();
        StatService.onPageStart(this, "测试结果页");
        StatService.onPageStart(this, "财商列表页");
        StatService.onPageStart(this, "早读电台列表页");
        StatService.onPageStart(this, "财富视听列表页");
    }

    @Override
    protected void onPause() {
        super.onPause();
        StatService.onPageEnd(this, "测试结果页");
        StatService.onPageEnd(this, "财商列表页");
        StatService.onPageEnd(this, "早读电台列表页");
        StatService.onPageEnd(this, "财富视听列表页");
    }
}
