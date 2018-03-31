package com.beidou.ybz.accountbook.ui.fragment;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.baidu.mobstat.StatService;
import com.beidou.ybz.accountbook.R;
import com.beidou.ybz.accountbook.mvp.entity.AddOverseasRequestModel;
import com.beidou.ybz.accountbook.mvp.entity.EncryptedResponseModel;
import com.beidou.ybz.accountbook.mvp.entity.GetHjzModel;
import com.beidou.ybz.accountbook.mvp.entity.MyModel;
import com.beidou.ybz.accountbook.mvp.entity.RequestBody;
import com.beidou.ybz.accountbook.mvp.other.MvpFragment;
import com.beidou.ybz.accountbook.mvp.presenter.CommonPresenter;
import com.beidou.ybz.accountbook.mvp.view.OtherView;
import com.beidou.ybz.accountbook.retrofit.ApiStores;
import com.beidou.ybz.accountbook.retrofit.AppClient;
import com.beidou.ybz.accountbook.ui.AboutUsActivity;
import com.beidou.ybz.accountbook.ui.BlackboxGuideActivity;
import com.beidou.ybz.accountbook.ui.CaifuCardActivity;
import com.beidou.ybz.accountbook.ui.HuiZhangActivity;
import com.beidou.ybz.accountbook.ui.HuodongDetailActivity;
import com.beidou.ybz.accountbook.ui.HuodongListActivity;
import com.beidou.ybz.accountbook.ui.LoginActivity;
import com.beidou.ybz.accountbook.ui.MessageListActivity;
import com.beidou.ybz.accountbook.ui.MyBlackboxDetailActivity;
import com.beidou.ybz.accountbook.ui.PersonalInfoActivity;
import com.beidou.ybz.accountbook.ui.RenmaiActivity;
import com.beidou.ybz.accountbook.ui.SettingActivity;
import com.beidou.ybz.accountbook.ui.TongbuActivity;
import com.beidou.ybz.accountbook.ui.X5WebActivity;
import com.beidou.ybz.accountbook.util.ActivityUtils;
import com.beidou.ybz.accountbook.util.DESedeUtil;
import com.beidou.ybz.accountbook.util.GsonTools;
import com.beidou.ybz.accountbook.util.LogUtils;
import com.beidou.ybz.accountbook.util.Utils;
import com.bumptech.glide.Glide;
import com.google.gson.Gson;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

//import android.app.AlertDialog;
//import android.app.AlertDialog;

/**
 * Author: Bob on 2017/10/30 15:16
 * QQ:754444814
 * E-mail:754444814@qq.com
 */
public class MyFragment extends MvpFragment<CommonPresenter> implements OtherView<MyModel> {
    @Bind(R.id.llUnLogin)
    LinearLayout llUnLogin;
    @Bind(R.id.tvNicheng)
    TextView tvNicheng;
    @Bind(R.id.llUserCenter)
    LinearLayout llUserCenter;
    @Bind(R.id.fragment_my_huizhang)
    LinearLayout fragmentMyHuizhang;
    @Bind(R.id.huodong_img1)
    ImageView huodongImg1;
    @Bind(R.id.huodong_img2)
    ImageView huodongImg2;
    @Bind(R.id.huodong_img3)
    ImageView huodongImg3;
    @Bind(R.id.huodong_linear)
    LinearLayout huodongLinear;
    @Bind(R.id.huodong_next)
    RelativeLayout huodongNext;
    @Bind(R.id.ivTouxiang)
    ImageView ivTouxiang;
    @Bind(R.id.tvMessage)
    TextView tvMessage;
    @Bind(R.id.ivHuizhang)
    ImageView ivHuizhang;
    @Bind(R.id.tvHuizhang)
    TextView tvHuizhang;
    private String encMsg, signMsg;
    private String url1, url2, url3;
    private String title1, title2, title3;
    private AlertDialog alertDialog;


    @Override
    public int getLayoutRes() {
        return R.layout.fragment_my;
    }

    @SuppressLint("NewApi")
    @Override
    public void initView() {
        try {
            alertDialog = new AlertDialog.Builder(mActivity)
                    .setView(R.layout.layout_newversion)
                    .create();
        } catch (Exception e) {
            e.printStackTrace();
        }
//        getStart();
//        alertDialog.show();
    }

    /**
     * 我的首页接口
     */
    private void index() {
        RequestBody<AddOverseasRequestModel> requestBody = new RequestBody<>();
        RequestBody.HeaderBean headerBean = new RequestBody.HeaderBean(Utils.getIPAddress(mActivity), spUtils.getSecretKeyId());
        AddOverseasRequestModel addOverseasRequestModel = new AddOverseasRequestModel();
        addOverseasRequestModel.setUserNo(spUtils.getUserId());
        addOverseasRequestModel.setVisitTime(spUtils.getMessageTime());
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
        mvpPresenter.index(encMsg, signMsg, "2", spUtils.getSecretKeyId());
    }


    @Override
    public void onResume() {
        super.onResume();
        LogUtils.loge("MyFragment——Resume");
        StatService.onPageStart(mActivity, "首页-我的tab");
        if (spUtils.getIsLogin()) {
            index();
//            gethjz();//查询黑匣子
            llUnLogin.setVisibility(View.GONE);
            llUserCenter.setVisibility(View.VISIBLE);
        } else {
            index();
            llUnLogin.setVisibility(View.VISIBLE);
            llUserCenter.setVisibility(View.GONE);
            tvMessage.setVisibility(View.INVISIBLE);
        }
    }

    public String formatBirthTime(Long ts) {
        @SuppressLint("SimpleDateFormat") SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String timeStr = formatter.format(new Date(ts)); //将日期时间格式化
        return timeStr;
    }

    @OnClick({R.id.tvLogin, R.id.llRenmai, R.id.llCaifuhui, R.id.llHeixiazi, R.id.llSetting, R.id.llAboutus, R.id.rlMessage, R.id.llUserCenter,
            R.id.fragment_my_huizhang, R.id.huodong_img1, R.id.huodong_img2, R.id.huodong_img3, R.id.huodong_next, R.id.llCaifuka,R.id.llFocus, R.id.llDianping})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.llFocus:
                if(spUtils.getIsLogin()) {
                    ActivityUtils.startActivityRightInWithUrl(mActivity, X5WebActivity.class, ApiStores.Focus + spUtils.getUserId());
                }else{
                    ActivityUtils.startActivityRightIn(mActivity, LoginActivity.class);
                }
                break;
            case R.id.llDianping:
                if(spUtils.getIsLogin()) {
                    ActivityUtils.startActivityRightInWithUrl(mActivity, X5WebActivity.class, ApiStores.Dianping+spUtils.getUserId());
                }else{
                    ActivityUtils.startActivityRightIn(mActivity, LoginActivity.class);
                }
                break;
            case R.id.llCaifuka:
                StatService.onEvent(mActivity, "我的页面点财富卡", "[财富卡点击量]", 1);
                if(spUtils.getIsLogin()) {
                    ActivityUtils.startActivityRightIn(mActivity, CaifuCardActivity.class);
                }else{
                    ActivityUtils.startActivityRightIn(mActivity, LoginActivity.class);
                }

                break;
            case R.id.llUserCenter://用户中心
                StatService.onEvent(mActivity, "我的页面点个人信息", "[个人模块点击量]", 1);
//                if (spUtils.getBindFlag().equals("0")){
//                    ActivityUtils.startActivityRightIn(mActivity, BindActivity.class);
//                }else {
//                    ActivityUtils.startActivityRightIn(mActivity, PersonalInfoActivity.class);
//                }
                ActivityUtils.startActivityRightIn(mActivity, PersonalInfoActivity.class);
                break;
            case R.id.rlMessage://消息中心不需要登陆
                spUtils.setMessageTime(formatBirthTime(System.currentTimeMillis()));
                ActivityUtils.startActivityRightIn(mActivity, MessageListActivity.class);
                break;
            case R.id.tvLogin://登陆注册
//                ActivityUtils.startActivityRightIn(mActivity, LoginActivity.class);
                ActivityUtils.startActivityRightInWithFrom(mActivity, LoginActivity.class, "forgetGes");
                break;
            case R.id.llRenmai://人脉
                StatService.onEvent(getActivity(), "我的页面点我的人脉", "[我的页面点我的人脉]", 1);
                if (spUtils.getIsLogin()) {
//                    ActivityUtils.startActivityRightIn(mActivity, TongbuActivity.class);
                    if (spUtils.getIsSave()) {
                        String jsonstring = spUtils.getcontactJson();
                        List<String> contractList = GsonTools.getObjectList(jsonstring, String.class);
                        ActivityUtils.startActivityRightInWithList(mActivity, RenmaiActivity.class, contractList);
                    } else {
                        ActivityUtils.startActivityRightIn(mActivity, TongbuActivity.class);
                    }
                } else {
                    ActivityUtils.startActivityRightIn(mActivity, LoginActivity.class);
                }
                break;
            case R.id.llCaifuhui://--------财富汇
                StatService.onEvent(mActivity, "我的页面点财富绘", "财富绘点击量", 1);
                ActivityUtils.startActivityRightInWithUrl(mActivity, AboutUsActivity.class, ApiStores.CaiFuHui);
                break;
            case R.id.llHeixiazi://黑匣子
                if (spUtils.getIsLogin()) {
                    StatService.onEvent(getActivity(), "我的页面点我的黑匣子", "[我的页面点我的黑匣子]", 1);
                    gethjz();
                   /* if (spUtils.getOpenblackBox()) {
                        ActivityUtils.startActivityRightIn(getActivity(), BlackboxDetailActivity.class);
                    } else {
                        ActivityUtils.startActivityRightIn(getActivity(), BlackboxUnsettingActivity.class);
                    }*/
                } else {
                    ActivityUtils.startActivityRightIn(getActivity(), BlackboxGuideActivity.class);
//                    ActivityUtils.startActivityRightIn(mActivity, LoginActivity.class);
                }
                break;
            case R.id.llSetting://设置
                StatService.onEvent(getActivity(), "我的页面点设置", "[我的页面点设置]", 1);
                if (spUtils.getIsLogin()) {
                    ActivityUtils.startActivityRightIn(mActivity, SettingActivity.class);
                } else {
                    ActivityUtils.startActivityRightInWithFrom(mActivity, LoginActivity.class, "forgetGes");
                }
                break;
            case R.id.llAboutus://关于我们
                StatService.onEvent(mActivity, "我的页面点关于我们", "[关于我们点击量]", 1);
                ActivityUtils.startActivityRightInWithUrl(mActivity, AboutUsActivity.class, ApiStores.ABOUT_URL);
                break;
            case R.id.fragment_my_huizhang://徽章墙
                StatService.onEvent(mActivity, "我的页面点徽章", "[徽章模块点击量]", 1);
                if (spUtils.getIsLogin()) {
                    ActivityUtils.startActivityRightIn(mActivity, HuiZhangActivity.class);
                } else {
//                    ActivityUtils.startActivityRightIn(mActivity, LoginActivity.class);
                    ActivityUtils.startActivityRightInWithFrom(mActivity, LoginActivity.class, "forgetGes");
                }
                break;
            case R.id.huodong_img1:
//                ActivityUtils.startActivityRightInWithUrl(getActivity(), HuodongDetailActivity.class, url1);
                Intent in = new Intent(mActivity, HuodongDetailActivity.class);
                in.putExtra("url",url1);
                in.putExtra("title",title1);
                startActivity(in);
                getActivity().overridePendingTransition(R.anim.left_in, 0);
                break;
            case R.id.huodong_img2:
//                ActivityUtils.startActivityRightInWithUrl(getActivity(), HuodongDetailActivity.class, url2);
                Intent in2 = new Intent(mActivity, HuodongDetailActivity.class);
                in2.putExtra("url",url2);
                in2.putExtra("title",title2);
                startActivity(in2);
                getActivity().overridePendingTransition(R.anim.left_in, 0);
                break;
            case R.id.huodong_img3:
//                ActivityUtils.startActivityRightInWithUrl(getActivity(), HuodongDetailActivity.class, url3);
                Intent in3 = new Intent(mActivity, HuodongDetailActivity.class);
                in3.putExtra("url",url3);
                in3.putExtra("title",title3);
                startActivity(in3);
                getActivity().overridePendingTransition(R.anim.left_in, 0);
                break;
            case R.id.huodong_next:
                StatService.onEvent(mActivity, "我的页面点活动精选", "[活动精选点击量]", 1);
                ActivityUtils.startActivityRightIn(mActivity, HuodongListActivity.class);
                break;
        }
    }


    @Override
    protected CommonPresenter createPresenter() {
        if (mActivity != null) {
            return new CommonPresenter(mActivity, this);
        } else {
            return new CommonPresenter(getActivity(), this);
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    /**
     * 我的
     *
     * @param model
     */
    @Override
    public void onSuccess(MyModel model) {
        if (model != null) {
            String messageCount = model.getBody().getMessageCount();
            if (Utils.isNumeric(messageCount)) {
                int count = Integer.parseInt(messageCount);
                if (count < 1) {
                    tvMessage.setVisibility(View.INVISIBLE);
                } else if (count < 10) {
                    tvMessage.setVisibility(View.VISIBLE);
                    tvMessage.setTextSize(10);
                } else if (count < 100) {
                    tvMessage.setVisibility(View.VISIBLE);
                    tvMessage.setTextSize(9);
                } else {
                    tvMessage.setVisibility(View.VISIBLE);
                    messageCount = "99+";
                    tvMessage.setTextSize(8);
                }
            } else {
                tvMessage.setVisibility(View.INVISIBLE);
            }

            tvMessage.setText(messageCount);

            String badgeCount = model.getBody().getBadgeCount();
            if (Utils.isNumeric(badgeCount)) {
                int badgecount = Integer.parseInt(badgeCount);
                if (badgecount > 0) {
                    ivHuizhang.setImageResource(R.drawable.huizhang_you);
                    tvHuizhang.setText("共" + badgeCount + "枚");
                } else {
                    ivHuizhang.setImageResource(R.drawable.huizhang_wu);
                    tvHuizhang.setText("无");
                }
            } else {
                ivHuizhang.setImageResource(R.drawable.huizhang_wu);
                tvHuizhang.setText("无");
            }

            //头像
            String portraitUrl = model.getBody().getPortraitUrl();
            if (!portraitUrl.equals("") && !portraitUrl.startsWith("http")) {
                portraitUrl = ApiStores.OTHER_IMG_URL + portraitUrl;
            }
            Glide.with(mActivity)
                    .load(portraitUrl)
//                .placeholder(R.drawable.defaultavatar)
                    .error(R.drawable.defaultavatar)
                    .into(ivTouxiang);
            spUtils.setPortraitUrl(portraitUrl);
            if (model.getBody().getNickName().equals("")) {
                tvNicheng.setText("我是默认昵称");
            } else {
                tvNicheng.setText(model.getBody().getNickName());
                spUtils.setNickName(model.getBody().getNickName());
            }
            int size = model.getBody().getActivityList().size();
            if (size == 0) {//------没有活动
                huodongNext.setVisibility(View.GONE);
                huodongImg1.setVisibility(View.GONE);
                huodongLinear.setVisibility(View.GONE);
            } else if (size == 1) {
                huodongNext.setVisibility(View.VISIBLE);
                huodongImg1.setVisibility(View.VISIBLE);
                huodongLinear.setVisibility(View.GONE);
                Glide.with(getContext())
                        .load(ApiStores.CMS_IMG_URL + model.getBody().getActivityList().get(0).getActivityPic())
                        .into(huodongImg1);
                if (model.getBody().getActivityList().get(0).getActivityUrl() != null && model.getBody().getActivityList().get(0).getActivityUrl() != "") {
                    url1 = model.getBody().getActivityList().get(0).getActivityUrl();
                    title1 = model.getBody().getActivityList().get(0).getActivityTitle();
                }
            } else if (size == 2) {
                huodongNext.setVisibility(View.VISIBLE);
                huodongImg1.setVisibility(View.GONE);
                huodongLinear.setVisibility(View.VISIBLE);
                Glide.with(getContext())
                        .load(ApiStores.CMS_IMG_URL + model.getBody().getActivityList().get(0).getActivityPic())
                        .into(huodongImg2);
                Glide.with(getContext())
                        .load(ApiStores.CMS_IMG_URL + model.getBody().getActivityList().get(1).getActivityPic())
                        .into(huodongImg3);
                if (model.getBody().getActivityList().get(0).getActivityUrl() != null && model.getBody().getActivityList().get(0).getActivityUrl() != "") {
                    url2 = model.getBody().getActivityList().get(0).getActivityUrl();
                    title2 = model.getBody().getActivityList().get(0).getActivityTitle();
                }
                if (model.getBody().getActivityList().get(1).getActivityUrl() != null && model.getBody().getActivityList().get(1).getActivityUrl() != "") {
                    url3 = model.getBody().getActivityList().get(1).getActivityUrl();
                    title3 = model.getBody().getActivityList().get(1).getActivityTitle();
                }
            }
        }
    }

    @Override
    public void onFail(String model) {
        toastShow(model);
    }

    /**
     * 查询黑匣子
     */
    private void gethjz() {
        RequestBody<AddOverseasRequestModel> loginRequestModel = new RequestBody<>();
        RequestBody.HeaderBean headerBean = new RequestBody.HeaderBean(Utils.getIPAddress(mActivity), spUtils.getSecretKeyId());
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
//        mvpPresenter.gethjz(encMsg, signMsg, "2", spUtils.getSecretKeyId());
        showLoadingDialog();
        ApiStores apiStores = AppClient.retrofit(getContext()).create(ApiStores.class);
        Observable<EncryptedResponseModel> observable = apiStores.gethjz(encMsg, signMsg, "2", spUtils.getSecretKeyId());
        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<EncryptedResponseModel>() {
                    @Override
                    public void onCompleted() {
                        dismissLoadingDialog();
                    }

                    @Override
                    public void onError(Throwable e) {
                    }

                    @Override
                    public void onNext(EncryptedResponseModel model) {
                        String msgType = model.getMsgType();
                        if (msgType != null && msgType.equals("2")) {//加密
                            String encMsg = model.getEncMsg();
                            LogUtils.loge(model.getEncMsg());
                            try {
                                String platext = DESedeUtil.decrypt(encMsg, spUtils.getSecretKey(), spUtils.getSecretIv());
                                LogUtils.loge("解密后：查询黑匣子" + platext);
                                GetHjzModel getHjzModel = GsonTools.getObject(platext, GetHjzModel.class);
                                if (getHjzModel.getHeader().getCode().equals("0000")) {
                                    if (getHjzModel.getBody().getInfoDto() == null) {
                                        spUtils.setOpenblackBox(false);
                                        ActivityUtils.startActivityRightIn(getActivity(), BlackboxGuideActivity.class);
                                        /*if (spUtils.getOpenblackBox()) {
                                            ActivityUtils.startActivityRightIn(getActivity(), BlackboxDetailActivity.class);
                                        } else {
                                            ActivityUtils.startActivityRightIn(getActivity(), BlackboxUnsettingActivity.class);
                                        }*/
                                    } else {
                                        spUtils.setOpenblackBox(true);
                                        ActivityUtils.startActivityRightIn(getActivity(), MyBlackboxDetailActivity.class);
                                    }
                                } else {
                                    toastShow(getHjzModel.getHeader().getDesc());
                                }
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        } else {
                        }
                    }

                });
    }


}
