package com.beidou.ybz.accountbook.ui;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Process;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.beidou.ybz.accountbook.R;
import com.beidou.ybz.accountbook.mvp.entity.AddOverseasRequestModel;
import com.beidou.ybz.accountbook.mvp.entity.RequestBody;
import com.beidou.ybz.accountbook.retrofit.ApiStores;
import com.beidou.ybz.accountbook.ui.fragment.CaishangFragment;
import com.beidou.ybz.accountbook.ui.fragment.LoanFragment;
import com.beidou.ybz.accountbook.ui.fragment.MainFragment;
import com.beidou.ybz.accountbook.ui.fragment.MyFragment;
import com.beidou.ybz.accountbook.util.ActivityUtils;
import com.beidou.ybz.accountbook.util.DESedeUtil;
import com.beidou.ybz.accountbook.util.LogUtils;
import com.beidou.ybz.accountbook.util.SppaConstant;
import com.beidou.ybz.accountbook.util.Utils;
import com.google.gson.Gson;
import com.orhanobut.logger.Logger;
import com.umeng.analytics.MobclickAgent;

import org.greenrobot.eventbus.EventBus;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends BaseActivity {
    Fragment fragmentMain, fragmentZengzhi, fragmentCaishang, fragmentMy;
    @Bind(R.id.iv_main)
    ImageView ivMain;
    @Bind(R.id.tv_main)
    TextView tvMain;
    @Bind(R.id.iv_zengzhi)
    ImageView ivZengzhi;
    @Bind(R.id.tv_zengzhi)
    TextView tvZengzhi;
    @Bind(R.id.iv_caishang)
    ImageView ivcaishang;
    @Bind(R.id.tv_caishang)
    TextView tvcaishang;
    @Bind(R.id.iv_my)
    ImageView ivMy;
    @Bind(R.id.tv_my)
    TextView tvMy;
    long exittime = 0;
    @Bind(R.id.ivNewGuide)
    ImageView ivNewGuide;
    private String from;
    private String encMsg, signMsg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
//        ActivityUtils.setStatusBar(MainActivity.this);
        setSwipeBackEnable(false);
        setSelect(0);

        SppaConstant.getHeight(mActivity);

        handleIntent(getIntent());

        if(spUtils.getIsNewUser()){
            ivNewGuide.setVisibility(View.VISIBLE);
        }else{
            ivNewGuide.setVisibility(View.GONE);
        }

        EventBus.getDefault().post("loginout");//跳转到首页，关闭启动页
    }


    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        Logger.e("MainActivity_:onNewIntent" + this.getTaskId());
        handleIntent(intent);
    }

    private void handleIntent(Intent in) {
        if (in != null) {
            from = in.getStringExtra("from");
            if (from != null && from.equals("main")) {
                setSelect(0);
            } else if (from != null && from.equals("zengzhi")) {
                setSelect(1);
            } else if (from != null && from.equals("caishang")) {
                setSelect(2);
            }

            String action = in.getAction();
            if (Intent.ACTION_VIEW.equals(action)) {
                Uri uri = in.getData();
                if (uri != null) {
                    Log.e("", "scheme: " + uri.getScheme());
                    Log.e("", "host: " + uri.getHost());
                    String host = uri.getHost();
                    if (host != null && host.equals("badge")) {
//                        ActivityUtils.startActivityRightIn(this,HuiZhangActivity.class);
                    } else if (host != null && host.equals("financial")) {//财商首页
                        setSelect(2);
                    }else if(host != null && host.equals("fqAvRadioDetail")){//音频
//                        ActivityUtils.startActivityRightIn(mActivity,CaishangDetailActivity.class);
                        String id = uri.getQueryParameter("id");
                        jumptToRadio(id,"YPXQ");
                        LogUtils.loge("idididididididididididididid:"+id);
                    }else if(host != null && host.equals("fqAvVideoDetail")){//视频
                        String id = uri.getQueryParameter("id");
                        jumptToRadio(id,"SPXQ");
                        LogUtils.loge("idididididididididididididid:"+id);
                    }

//                    String age= uri.getQueryParameter("age");

                }

            }
        }
    }

    void jumptToRadio(String id,String type){
        getVedioDetailEncMsg(id);
        String CS_URL = ApiStores.CAISHANG_URL + "fq/coursedetail.htm?" + "encMsg=" + encMsg + "&signMsg=" + signMsg + "&msgType=" + "2" + "&secretKeyId=" + spUtils.getSecretKeyId();
        Intent intent = null;
        if(type != null && type.equals("YPXQ")) {
            intent = new Intent(mActivity, CaishangDetailActivity.class);
        }else{
            intent = new Intent(mActivity, CaishangVedioActivity.class);
        }
        intent.putExtra("url", CS_URL)
                .putExtra("id", id)
                .putExtra("type", type)
                .putExtra("cj", "1");

        startActivity(intent);
        overridePendingTransition(R.anim.left_in, 0);
    }

    private void getVedioDetailEncMsg(String id) {
        RequestBody<AddOverseasRequestModel> requestBody = new RequestBody<>();
        RequestBody.HeaderBean headerBean = new RequestBody.HeaderBean(Utils.getIPAddress(mActivity), spUtils.getSecretKeyId());
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
     * 把图片设置为亮色
     * 设置内容区域
     * @param i
     */
    private void setSelect(int i) {
        resetImages();
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction transaction = fm.beginTransaction();
        hideFragment(transaction);
        switch (i) {
            case 0:
                if (fragmentMain == null) {
                    fragmentMain = new MainFragment();
                    transaction.add(R.id.id_content, fragmentMain, fragmentMain.getClass().getName());
                } else {
                    transaction.show(fragmentMain);
                }
                ivMain.setImageResource(R.drawable.main_selected);
                tvMain.setTextColor(getResources().getColor(R.color.textcheck));
                break;
            case 1:
                if (fragmentZengzhi == null) {
                    fragmentZengzhi = new LoanFragment();
                    transaction.add(R.id.id_content, fragmentZengzhi, fragmentZengzhi.getClass().getName());
                } else {
                    transaction.show(fragmentZengzhi);
                }

                ivZengzhi.setImageResource(R.drawable.zengzhi_selected);
                tvZengzhi.setTextColor(getResources().getColor(R.color.textcheck));
                break;
            case 2:
                if (fragmentCaishang == null) {
                    fragmentCaishang = new CaishangFragment();
                    transaction.add(R.id.id_content, fragmentCaishang, fragmentCaishang.getClass().getName());
                } else {
                    transaction.show(fragmentCaishang);
                }

                ivcaishang.setImageResource(R.drawable.caishang_selected);
                tvcaishang.setTextColor(getResources().getColor(R.color.textcheck));
                break;
            case 3:
                if (fragmentMy == null) {
                    fragmentMy = new MyFragment();
                    transaction.add(R.id.id_content, fragmentMy, fragmentMy.getClass().getName());
                } else {
                    transaction.show(fragmentMy);
                }
                ivMy.setImageResource(R.drawable.my_selected);
                tvMy.setTextColor(getResources().getColor(R.color.textcheck));

                break;
        }
        transaction.commit();
    }


    /**
     * 隐藏所有的Fragment
     *
     * @param transaction
     */
    private void hideFragment(FragmentTransaction transaction) {
        if (fragmentMain != null) {
            transaction.hide(fragmentMain);
        }
        if (fragmentZengzhi != null) {
            transaction.hide(fragmentZengzhi);
        }
        if (fragmentCaishang != null) {
            transaction.hide(fragmentCaishang);
        }
        if (fragmentMy != null) {
            transaction.hide(fragmentMy);
        }

    }

    /**
     * 切换图片,文字至暗色
     */
    void resetImages() {
        ivMain.setImageResource(R.drawable.main_unselected);
        ivZengzhi.setImageResource(R.drawable.zengzhi_unselected);
        ivcaishang.setImageResource(R.drawable.caishang_unselected);
        ivMy.setImageResource(R.drawable.my_unselected);

        tvMain.setTextColor(getResources().getColor(R.color.textuncheck));
        tvZengzhi.setTextColor(getResources().getColor(R.color.textuncheck));
        tvcaishang.setTextColor(getResources().getColor(R.color.textuncheck));
        tvMy.setTextColor(getResources().getColor(R.color.textuncheck));
    }

    @Override
    protected void onResume() {
        super.onResume();
        LogUtils.loge("MainActivity——Resume");
    }

    @OnClick({R.id.ll_main, R.id.ll_zengzhi, R.id.ll_caishang, R.id.ll_my})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ll_main:
                setSelect(0);
                break;
            case R.id.ll_zengzhi:
                setSelect(1);
                break;
            case R.id.ll_caishang:
                setSelect(2);
                break;
            case R.id.ll_my:
                setSelect(3);
                break;
        }
    }


    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK
                && event.getAction() == KeyEvent.ACTION_DOWN) {
            if ((System.currentTimeMillis() - exittime) > 2000) {
                toastShow("再按一次退出程序", R.drawable.tan_toast);
                exittime = System.currentTimeMillis();
            } else {
                /**
                 * 如果调用Process.kill或者System.exit之类的方法杀死进程，请务必在此之前调用
                 * MobclickAgent.onKillProcess(Context context)方法，用来保存统计数据
                 */
                MobclickAgent.onKillProcess(this);
                System.exit(0);
                //杀死该应用进程
                Process.killProcess(Process.myPid());

                finish();
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    @OnClick(R.id.ivNewGuide)
    public void onViewClicked() {
        ivNewGuide.animate().translationX(-2000f).setDuration(500).start();
        spUtils.setIsNewUser(false);
    }

}
