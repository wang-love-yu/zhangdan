package com.beidou.ybz.accountbook.ui;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.ClipboardManager;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Picture;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.baidu.mobstat.StatService;
import com.beidou.ybz.accountbook.R;
import com.beidou.ybz.accountbook.mvp.other.MvpActivity;
import com.beidou.ybz.accountbook.mvp.presenter.CommonPresenter;
import com.beidou.ybz.accountbook.util.ActivityUtils;
import com.beidou.ybz.accountbook.util.AlertDialogUtils;
import com.beidou.ybz.accountbook.util.DialogClickListener;
import com.beidou.ybz.accountbook.util.ImeUtil;
import com.beidou.ybz.accountbook.util.LogUtils;
import com.beidou.ybz.accountbook.util.SimpleUtils;
import com.beidou.ybz.accountbook.util.Utils;
import com.beidou.ybz.accountbook.widget.ProgressWebView1;
import com.tbruyelle.rxpermissions.RxPermissions;

import java.util.Random;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import rx.functions.Action1;

public class AboutUsActivity extends BaseActivity {
    ProgressWebView1 mWebView;
    String url;
    @Bind(R.id.ib_back)
    ImageButton ibBack;
    @Bind(R.id.tvTitle)
    TextView tvTitle;
    @Bind(R.id.relativelayout)
    LinearLayout relativelayout;
    private AlertDialog alertDialog;
    private String dialNumber;
    AlertDialogUtils alertDialogUtils;
    private String fileName2;
    Random random = new Random();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_us);
        ButterKnife.bind(this);
        initView();
    }

    @SuppressLint("NewApi")
    private void initView() {
        mWebView.enableSlowWholeDocumentDraw();
        mWebView = new ProgressWebView1(AboutUsActivity.this, null, tvTitle);
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
        alertDialog = new AlertDialog.Builder(mActivity)
                .setTitle("拨号")
                .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                })
                .setPositiveButton("呼叫", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        alertDialog.dismiss();
                        String url = "tel:" + dialNumber;//"4006006788";
                        Intent in = new Intent(Intent.ACTION_DIAL, Uri.parse(url));
                        mActivity.startActivity(in);
                    }
                })
//                .setMessage(dialNumber)
                .setCancelable(false)
                .create();
        alertDialog.getWindow().setWindowAnimations(R.style.dialogAnimation);

        alertDialogUtils = new AlertDialogUtils(mActivity);
        alertDialogUtils.setOnDialogClickListener(new DialogClickListener() {
            @Override
            public void clickYes(int which) {

                RxPermissions.getInstance(AboutUsActivity.this)
                        .request(Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE)//这里填写所需要的权限
                        .subscribe(new Action1<Boolean>() {
                            @Override
                            public void call(Boolean aBoolean) {
                                if (aBoolean) {//true表示获取权限成功（注意这里在android6.0以下默认为true）
                                    fileName2 = String.valueOf(random.nextInt(Integer.MAX_VALUE));
                                    SimpleUtils.saveBitmapToSdCard(mActivity, captureWebView(mWebView), fileName2);
                                }
                            }
                        });

            }

            @Override
            public void clickNo() {
            }
        });

    }


    @OnClick({R.id.ib_back})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ib_back:
                ActivityUtils.finishActivity(AboutUsActivity.this);
                break;
        }
    }


    public class WebViewClient extends android.webkit.WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            LogUtils.logd("拨号：：" + url);//url:tel:400-600-6788
            // 调用拨号程序
            if (url.startsWith("geo:") || url.startsWith("tel:")) {
                dialNumber = url.split(":")[1];
                LogUtils.logd("拨号号码：：" + dialNumber);
                alertDialog.setMessage(dialNumber);
                alertDialog.show();
                return true;
            } else if (url.contains("weixin://")) {//跳转到微信
                if (Utils.isWeChatAppInstalled(mActivity)) {
                    /**
                     * 复制内容到剪切板
                     */
                    ClipboardManager clipboard =
                            (ClipboardManager) getSystemService(CLIPBOARD_SERVICE);

                    clipboard.setText("club360cf");
                    Intent intent = getPackageManager().getLaunchIntentForPackage("com.tencent.mm");
                    startActivity(intent);
                } else {
                    toastShow("本设备未安装微信，无法使用微信功能");
                }
            } else {
                view.loadUrl(url);
            }
            return true;
        }

        @Override
        public void onPageFinished(WebView view, String url) {
            super.onPageFinished(view, url);
            LogUtils.logd("onPageFinished：：");
            mWebView.loadUrl("javascript:appHide()");
            mWebView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View view) {
                    alertDialogUtils.setMessage("保存图片？");
                    alertDialogUtils.show();
                    return true;
                }
            });
        }

        @Override
        public void onReceivedError(WebView view, int errorCode,
                                    String description, String failingUrl) {
            super.onReceivedError(view, errorCode, description, failingUrl);
            view.loadUrl("file:///android_asset/error.html");
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        StatService.onPageStart(mActivity, "关于我们页面");
    }

    @Override
    protected void onPause() {
        super.onPause();
        StatService.onPageEnd(mActivity, "关于我们页面");
        ImeUtil.hideSoftKeyboard(mWebView);
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

    /**
     * 截取webView快照(webView加载的整个内容的大小)
     *
     * @param webView
     * @return
     */
    public static Bitmap captureWebView1(WebView webView) {
        webView.setDrawingCacheEnabled(true);
        webView.buildDrawingCache();
        Picture snapShot = webView.capturePicture();
        Bitmap bmp = Bitmap.createBitmap(snapShot.getWidth(), snapShot.getHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bmp);
        snapShot.draw(canvas);
        canvas.save();
        canvas.restore();
        //      webView.dispatchDraw(canvas);
        webView.destroyDrawingCache();
        return bmp;
    }
}
