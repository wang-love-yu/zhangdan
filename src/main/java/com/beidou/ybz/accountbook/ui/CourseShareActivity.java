package com.beidou.ybz.accountbook.ui;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.WebView;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.beidou.ybz.accountbook.R;
import com.beidou.ybz.accountbook.mvp.entity.GetavdataModel;
import com.beidou.ybz.accountbook.mvp.entity.ShareModel;
import com.beidou.ybz.accountbook.mvp.other.MvpActivity;
import com.beidou.ybz.accountbook.mvp.presenter.CommonPresenter;
import com.beidou.ybz.accountbook.mvp.view.OtherView;
import com.beidou.ybz.accountbook.util.ActivityUtils;
import com.beidou.ybz.accountbook.util.LogUtils;
import com.beidou.ybz.accountbook.util.SimpleUtils;
import com.beidou.ybz.accountbook.util.Utils;
import com.beidou.ybz.accountbook.widget.ProgressWebView1;
import com.beidou.ybz.accountbook.widget.morewindow.MoreWindow;

import java.util.Random;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class CourseShareActivity extends MvpActivity<CommonPresenter> implements OtherView<GetavdataModel> {

    ProgressWebView1 mWebView;
    String url;
    @Bind(R.id.relativelayout)
    LinearLayout relativelayout;
    @Bind(R.id.ib_back)
    ImageButton ibBack;
    @Bind(R.id.linear)
    LinearLayout linear;
    @Bind(R.id.tvTitle)
    TextView tvTitle;
    private MoreWindow mMoreWindow;
    private ShareModel shareModel;
    private String fileName2;
    private String path;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course_share);
        ButterKnife.bind(this);
        initView();
    }

    @Override
    protected CommonPresenter createPresenter() {
        return new CommonPresenter(this, this);
    }

    @Override
    public void onSuccess(GetavdataModel model) {

    }

    @Override
    public void onFail(String model) {

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
//            toastShow("分享页面生成中...");

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


    @SuppressLint("NewApi")
    public void initView() {
        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            path = Environment.getExternalStorageDirectory().getPath() + "/images/";
        } else {
            path = Environment.getRootDirectory().getPath() + "/images";
        }
        mWebView.enableSlowWholeDocumentDraw();
        mWebView = new ProgressWebView1(CourseShareActivity.this, null, tvTitle);
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
        mWebView.setLayoutParams(lp);
        mWebView.setWebViewClient(new WebViewClient());
        relativelayout.addView(mWebView);
        Intent intent = getIntent();
        url = intent.getStringExtra("url");
        LogUtils.loge(url);
        if (url != null) {
            mWebView.loadUrl(url);
        }
        shareModel = new ShareModel();
    }


    @OnClick({R.id.ib_back})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ib_back:
                if (mMoreWindow != null) {
                    ActivityUtils.finishActivity(CourseShareActivity.this);
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

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_DOWN) {
            if (mMoreWindow != null) {
                return true;
            }
        }
        return super.onKeyDown(keyCode, event);
    }
}
