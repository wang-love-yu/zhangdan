package com.beidou.ybz.accountbook.ui;

import android.animation.ObjectAnimator;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.beidou.ybz.accountbook.R;
import com.beidou.ybz.accountbook.mvp.entity.IndexModel;
import com.beidou.ybz.accountbook.mvp.other.MvpActivity;
import com.beidou.ybz.accountbook.mvp.presenter.CommonPresenter;
import com.beidou.ybz.accountbook.mvp.view.CommonView;
import com.beidou.ybz.accountbook.retrofit.TimeCountInterface;
import com.beidou.ybz.accountbook.util.ActivityUtils;
import com.beidou.ybz.accountbook.util.LogUtils;
import com.beidou.ybz.accountbook.util.SharePreferenceUtil;
import com.beidou.ybz.accountbook.util.TimeCount;
import com.beidou.ybz.accountbook.util.Utils;
import com.beidou.ybz.accountbook.widget.CircleProgressView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.io.IOException;
import java.util.List;

import butterknife.Bind;

/**
 * Author: xu.yang on 2017/12/28
 * QQ:754444814
 * E-mail:754444814@qq.com
 * module:
 */
public class StartUpActivity extends BaseActivity implements View.OnClickListener,TimeCountInterface {
    @Bind(R.id.viewPager)
    ViewPager mViewPager;
    @Bind(R.id.rl_viewpager)
    RelativeLayout rlViewpager;
    @Bind(R.id.iv_banner)
    ImageView ivBanner;
    @Bind(R.id.circleProgressbar)
    CircleProgressView mCircleBar;
    private List<ImageView> imageViewList;
    private TimeCount time;
    /**
     * 装ImageView数组
     */
    private ImageView[] mImageViews;
    /**
     * 图片资源id
     */
    private int[] imgIdArray ;
    private boolean isLastPager;
    private Handler mHandler = new Handler();
    private Runnable runnable = new Runnable() {
        @Override
        public void run() {
            enterMain();
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_startup);
        ActivityUtils.setStatusBar(mActivity);

//        if(spUtils.getIsGesture()){
//            Intent in = new Intent(mActivity, GestureValidActivity.class);
//            in.putExtra("flag", "launch");
//            startActivity(in);
//            finish();
//            overridePendingTransition(R.anim.left_in, 0);
//        }else{
//            Intent in = new Intent(mActivity, MainActivity.class);
//            startActivity(in);
//            finish();
//            overridePendingTransition(R.anim.left_in, 0);
//        }

        initView();

    }

    void initView(){
        EventBus.getDefault().register(this);//在当前界面注册一个订阅者
        mCircleBar.setProgress(100);
        time = new TimeCount(this, 3000, 10);
//        String img = spUtils.getStartUpImg();
//        if(!TextUtils.isEmpty(img)){
            Glide.with(mActivity)
                    .load(R.drawable.launch)
                    .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                    .into(ivBanner);
//        }
        ivBanner.setVisibility(View.VISIBLE);
        if(time != null){
            time.start();
        }

        //载入图片资源ID
        imgIdArray = new int[]{R.drawable.guide1, R.drawable.guide2, R.drawable.guide3};

        //将图片装载到数组中
        mImageViews = new ImageView[imgIdArray.length];
        for(int i=0; i<mImageViews.length; i++){
            ImageView imageView = new ImageView(this);

            ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.MATCH_PARENT);
            imageView.setLayoutParams(params);
            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
            mImageViews[i] = imageView;
            try {
                Utils.getBitmapForImgResourse(this,imgIdArray[i],imageView);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }



        mViewPager.setAdapter(new ViewPagerAdapter());

        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {}

            @Override
            public void onPageSelected(int position) {
                if(position == 2){
                    LogUtils.loge("position--isLastPager:"+position+"--"+isLastPager);
//                    mHandler.postDelayed(runnable,1000);
                    isLastPager = true;
                }else{
                    isLastPager = false;
                    mHandler.removeCallbacks(runnable);
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });


        mCircleBar.setOnClickListener(this);
    }

    class ViewPagerAdapter extends PagerAdapter {

        @Override
        public int getCount() {
            return mImageViews.length;
        }

        /**
         * 判断出去的view是否等于进来的view 如果为true直接复用
         */
        @Override
        public boolean isViewFromObject(View arg0, Object arg1) {
            return arg0 == arg1;
        }


        /**
         * 销毁预加载以外的view对象, 会把需要销毁的对象的索引位置传进来就是position
         */
        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView(mImageViews[position]);
        }

        /**
         * 创建一个view
         */
        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            View view = mImageViews[position];
            if(position == 2) {
                view.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        enterMain();
                    }
                });
            }
            container.addView(view);
            return view;
        }
    }


    @Override
    public void onTick(long l) {
        mCircleBar.setProgress((int)((l+2l) *20 / 1000));
    }

    @Override
    public void onFinish() {
        if(spUtils.getIsNewUser()){//新用户
            ObjectAnimator.ofFloat(ivBanner,"alpha", 1f, 0f).setDuration(1000).start();
            ObjectAnimator.ofFloat(rlViewpager,"alpha", 0f, 1f).setDuration(1000).start();
            ObjectAnimator.ofFloat(mCircleBar,"alpha", 1f, 0f).setDuration(1000).start();
        }else {//老用户
            rlViewpager.setVisibility(View.GONE);
            ivBanner.setVisibility(View.VISIBLE);
            enterMain();
        }
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
           /* case R.id.btn_entermain:
                enterMain();
                break;*/
            case R.id.circleProgressbar:
                enterMain();
                break;
        }
    }

    void enterMain(){

        if(spUtils.getIsGesture()){
            Intent in = new Intent(mActivity, GestureValidActivity.class);
            in.putExtra("flag", "launch");
            startActivity(in);
//            finish();
            overridePendingTransition(R.anim.left_in, 0);
        }else{
            Intent in = new Intent(mActivity, MainActivity.class);
            startActivity(in);
//            finish();
            overridePendingTransition(
                    R.anim.fade_in2, R.anim.fade_out2);
        }

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
