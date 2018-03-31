package com.beidou.ybz.accountbook.ui;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.view.View;
import android.webkit.WebView;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.beidou.ybz.accountbook.R;
import com.beidou.ybz.accountbook.mvp.entity.ShareModel;
import com.beidou.ybz.accountbook.mvp.other.MvpActivity;
import com.beidou.ybz.accountbook.mvp.presenter.CommonPresenter;
import com.beidou.ybz.accountbook.util.ActivityUtils;
import com.beidou.ybz.accountbook.util.SimpleUtils;
import com.beidou.ybz.accountbook.util.Utils;
import com.beidou.ybz.accountbook.widget.ProgressWebView1;
import com.beidou.ybz.accountbook.widget.morewindow.MoreWindow;

import java.util.Random;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * 测试结果页面
 */
public class TestResultActivity extends MvpActivity<CommonPresenter> {

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
    //----分享
    private MoreWindow mMoreWindow;
    private ShareModel shareModel;
    private String fileName2;
    private String path;

    private String tag;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_result);
        initView();
    }

    @Override
    protected CommonPresenter createPresenter() {
        return null;
    }

    public void initView() {
        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            path = Environment.getExternalStorageDirectory().getPath() + "/images/";
        } else {
            path = Environment.getRootDirectory().getPath() + "/images";
        }
        mWebView = new ProgressWebView1(TestResultActivity.this, null, tvTitle);
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
        mWebView.setLayoutParams(lp);
        mWebView.setWebViewClient(new WebViewClient());
        relativelayout.addView(mWebView);
        Intent intent = getIntent();
        url = intent.getStringExtra("url");
        if (url != null) {
            mWebView.loadUrl(url);
        }
        shareModel = new ShareModel();

        tag = intent.getStringExtra("tag");
        if (tag != null) {
            share.setVisibility(View.GONE);
        }
    }

    Handler handler = new Handler();
    public class WebViewClient extends android.webkit.WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            // 调用拨号程序
            return true;
        }

        @Override
        public void onPageFinished(WebView view, String url) {
            super.onPageFinished(view, url);
            toastShow("分享页面生成中...");
//            view.loadUrl("javascript:window.local_obj.showSource(" +
//                    "document.getElementById('js_content').innerText);");
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    Random random = new Random();
                    fileName2 = String.valueOf(random.nextInt(Integer.MAX_VALUE));
                    SimpleUtils.saveBitmapToSdCard1(mActivity, captureWebView(mWebView), fileName2);
                    if (Utils.fileIsExists(path + fileName2 + ".jpg")){
                        showMoreWindow(linear);
                        shareModel.setImgurl(path + fileName2 + ".jpg");
                    }
                }
            }, 3000);
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
                if (mMoreWindow!=null) {
                    ActivityUtils.finishActivity(TestResultActivity.this);
                }
                break;
            case R.id.share:
                showMoreWindow(linear);
//                ActivityUtils.startActivityRightInWithUrl(mActivity, CourseShareActivity.class, url);
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


    private void showMoreWindow(View view) {
        if (null == mMoreWindow) {
            mMoreWindow = new MoreWindow(mActivity, "img", shareModel);
            mMoreWindow.init();
        }
        mMoreWindow.showMoreWindow(view, 100);
    }


    private static Bitmap captureWebView(WebView wv_capture) {
        //获取webview缩放率
        if (wv_capture != null) {
            float scale = wv_capture.getScale();
            //得到缩放后webview内容的高度
            int webViewHeight = (int) (wv_capture.getContentHeight() * scale);
            Bitmap bitmap = Bitmap.createBitmap(wv_capture.getWidth(), webViewHeight, Bitmap.Config.ARGB_8888);
            Canvas canvas = new Canvas(bitmap);
            //绘制
            wv_capture.draw(canvas);
            return bitmap;
        }
        return null;
    }
}
