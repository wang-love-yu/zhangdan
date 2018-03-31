package com.beidou.ybz.accountbook.ui;

import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

import com.baidu.mobstat.StatService;
import com.beidou.ybz.accountbook.R;
import com.beidou.ybz.accountbook.util.ToastUtils;
import com.beidou.ybz.accountbook.widget.Lock9View;
import com.beidou.ybz.accountbook.widget.LockIndicator;
import com.beidou.ybz.accountbook.util.ActivityUtils;
import com.bumptech.glide.Glide;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 手势密码编辑界面
 * Created by Bob on 2016/7/1.
 * https://github.com/TakWolf/Android-Lock9View
 */
public class GestureEditActivity extends BaseActivity {
    @Bind(R.id.lock_9_view)
    Lock9View lock9View;
    @Bind(R.id.lock_indicator)
    LockIndicator mLockIndicator;
    @Bind(R.id.text_tip)
    TextView mTexTip;
    @Bind(R.id.text_reset)
    TextView mTextReset;
    @Bind(R.id.tv_title)
    TextView mTvTitle;
    private boolean mIsFirstInput = true;//是否是第一次输入手势密码
    private String mFirstPassword = null;//第一次输入的密码
    private String flag = "";
    private Intent intent = null;
    Animation shakeAnimation;
    private boolean isAlter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gestureedit);
        ButterKnife.bind(this);
//        sp = new SharePreferenceUtil(this,"Login");
        ActivityUtils.setStatusBar(mActivity);
        setSwipeBackEnable(false);

        intent = getIntent();
        if (intent != null) {
            flag = intent.getStringExtra("flag");
        }

        initView();
    }


    public void initView() {
        shakeAnimation = AnimationUtils.loadAnimation(GestureEditActivity.this, R.anim.shake);
        mTextReset.setClickable(false);

        lock9View.setCallBack(new Lock9View.CallBack() {
            @Override
            public void onFinish(String inputCode) {
                if (mIsFirstInput) { //第一次输入
                    if (!isInputPassValidate(inputCode)) {
                        mTexTip.setText(Html.fromHtml("<font color='#FA5553'>需至少链接4个点，请重新绘制</font>"));
                        //左右移动动画
                        mTexTip.startAnimation(shakeAnimation);
                        return;
                    }else{
                        mFirstPassword = inputCode;//记录第一次密码
                        updateCodeList(inputCode);
                        mTextReset.setClickable(true);
                        mTextReset.setText("重新绘制");
                        mTexTip.setText(Html.fromHtml("<font color='#ffffff'>请再次绘制手势密码</font>"));
                    }
                } else { //第二次输入
                    if (inputCode.equals(mFirstPassword)) { //两次录入手势密码一致 （录入成功！）
                        mTexTip.setText(Html.fromHtml("<font color='#ffffff'>绘制成功</font>"));
                        spUtils.setIsGesture(true);
                        spUtils.setGesture(inputCode);
                        if (!TextUtils.isEmpty(flag) && flag.equals("forget")) {
                            ToastUtils.toast(GestureEditActivity.this, R.drawable.gou_toast, "设置成功");
                            Intent ins = new Intent(GestureEditActivity.this, MainActivity.class);
                            spUtils.setIsGesture(true);
                            startActivity(ins);
                        } else if (!TextUtils.isEmpty(flag) && flag.equals("set")) {
                            ToastUtils.toast(GestureEditActivity.this, R.drawable.gou_toast, "设置成功");
                            spUtils.setIsGesture(true);
                            ActivityUtils.finishActivity(mActivity);
                        } else {
                            ToastUtils.toast(GestureEditActivity.this, R.drawable.gou_toast, "手势密码已修改");
                            StatService.onEvent(mActivity, "手势密码已修改toast", "[手势密码修改]",1);
                            spUtils.setIsGesture(true);
                            ActivityUtils.finishActivity(mActivity);
                        }
                    } else if(!inputCode.equals(mFirstPassword)){//两次录入手势密码不一致 （录入失败!）
                        mTexTip.setText(Html.fromHtml("<font color='#FA5553'>与上次绘制不一致，请重新绘制</font>"));
                        mTexTip.startAnimation(shakeAnimation);
                    }else if(!isInputPassValidate(inputCode)){
                        mTexTip.setText(Html.fromHtml("<font color='#FA5553'>需至少链接4个点，请重新绘制</font>"));
                        //左右移动动画
                        mTexTip.startAnimation(shakeAnimation);
                        return;
                    }
                }

                mIsFirstInput = false;
            }

        });
    }


    private void updateCodeList(String inputCode) {
        // 更新选择的图案
        mLockIndicator.setPath(inputCode);
    }

    private boolean isInputPassValidate(String inputPassword) {
        if (TextUtils.isEmpty(inputPassword) || inputPassword.length() < 4) {
            return false;
        }
        return true;
    }

    @OnClick({R.id.text_reset})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.text_reset:
                mIsFirstInput = true;
                updateCodeList("");
                if(isAlter) {
                    mTexTip.setText(getString(R.string.set_gesture_alter));
                }else{
                    mTexTip.setText(getString(R.string.set_gesture_pattern));
                }
                mTextReset.setText("");
                mTextReset.setClickable(false);
                break;

        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        StatService.onPageEnd(mActivity,"手势密码设置页面");
    }

    @Override
    protected void onResume() {
        super.onResume();
        StatService.onPageStart(mActivity,"手势密码设置页面");
        if (!TextUtils.isEmpty(flag) && flag.equals("alter")) {
//            mTvTitle.setText("请设置新手势密码");
            mTexTip.setText(Html.fromHtml("<font color='#ffffff'>请绘制新手势密码</font>"));
            mTvTitle.setVisibility(View.GONE);
            isAlter = true;
        } else {
            mTvTitle.setText("设置手势密码");
            mTvTitle.setVisibility(View.VISIBLE);
            isAlter = false;
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK
                && event.getAction() == KeyEvent.ACTION_DOWN) {
            if (!TextUtils.isEmpty(flag) && flag.equals("forget")) {//忘记手势密码
                spUtils.clear();
                Intent ins = new Intent(GestureEditActivity.this, MainActivity.class);
                startActivity(ins);
                ActivityUtils.finishActivity(mActivity);
            } else if (!TextUtils.isEmpty(flag) && flag.equals("alter")) {//修改手势密码
                ActivityUtils.finishActivity(mActivity);
            } else {
                ActivityUtils.finishActivity(mActivity);
                spUtils.setIsGesture(false);
            }

        }
        return true;
    }

    @OnClick(R.id.ivBack)
    public void onViewClicked() {
        ActivityUtils.finishActivity(mActivity);
    }
}
