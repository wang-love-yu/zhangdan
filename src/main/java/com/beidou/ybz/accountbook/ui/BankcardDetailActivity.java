package com.beidou.ybz.accountbook.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.baidu.mobstat.StatService;
import com.beidou.ybz.accountbook.R;
import com.beidou.ybz.accountbook.mvp.entity.AddOverseasRequestModel;
import com.beidou.ybz.accountbook.mvp.entity.BankcardDetailModel;
import com.beidou.ybz.accountbook.mvp.entity.InsuranceListModel;
import com.beidou.ybz.accountbook.mvp.entity.RequestBody;
import com.beidou.ybz.accountbook.mvp.other.MvpActivity;
import com.beidou.ybz.accountbook.mvp.presenter.CommonPresenter;
import com.beidou.ybz.accountbook.mvp.view.CommonView;
import com.beidou.ybz.accountbook.retrofit.ApiStores;
import com.beidou.ybz.accountbook.util.DESedeUtil;
import com.beidou.ybz.accountbook.util.LogUtils;
import com.beidou.ybz.accountbook.util.Utils;
import com.beidou.ybz.accountbook.widget.ClearEditText;
import com.beidou.ybz.accountbook.util.ActivityUtils;
import com.bumptech.glide.Glide;
import com.google.gson.Gson;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Author: xu.yang on 2017/12/6
 * QQ:754444814
 * E-mail:754444814@qq.com
 * module: 银行卡详情界面
 */
public class BankcardDetailActivity extends MvpActivity<CommonPresenter> implements CommonView<BankcardDetailModel>, View.OnClickListener {
    @Bind(R.id.tv_title)
    TextView tvTitle;
    @Bind(R.id.toolbar)
    Toolbar toolbar;
    @Bind(R.id.tv_right)
    TextView tvRight;
    @Bind(R.id.ivBankIcon)
    ImageView ivBankIcon;
    @Bind(R.id.tvCardname)
    TextView tvCardname;
    @Bind(R.id.tvBind)
    TextView tvBind;
    @Bind(R.id.tvBankCardNo)
    TextView tvBankCardNo;
    @Bind(R.id.tvMemo)
    TextView tvMemo;
    @Bind(R.id.llMemo)
    LinearLayout llMemo;
    @Bind(R.id.viewEdu)
    View viewEdu;
    @Bind(R.id.tvEdu)
    TextView tvEdu;
    private String encMsg, signMsg;
    private List<InsuranceListModel.BodyBean.ProListBean> proListBeanlist;

    private TextView tvZhuanru, tvZhuanchu;
    private ClearEditText cetMoney;
    private String andorsub;
    private boolean isEdit;
    String id, name, bankNo, bankId, bankType, memo, amount, bankname, bankTypeName,bankLogo;
    private boolean isCredit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bankcarddetail);
        ButterKnife.bind(this);

        Intent in = getIntent();
        if (in != null) {
            id = in.getStringExtra("id");
            name = in.getStringExtra("name");
            bankLogo = in.getStringExtra("logo");
        }

        andorsub = "1";//默认为加
        toolbar.setNavigationIcon(R.drawable.back_black);
        tvTitle.setText(name);
        tvRight.setText("编辑");
        tvRight.setTextColor(getResources().getColor(R.color.txt_color));
        tvTitle.setTextColor(getResources().getColor(R.color.txt_color));
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ActivityUtils.finishActivity(mActivity);
            }
        });


    }

    @Override
    protected void onResume() {
        super.onResume();
        LogUtils.logd("onResume");
        isEdit = false;
        request();
        StatService.onPageStart(mActivity, "银行卡详情页面");
    }
    @Override
    protected void onPause() {
        super.onPause();
        StatService.onPageEnd(mActivity, "银行卡详情页面");
    }


    @Override
    protected CommonPresenter createPresenter() {
        return new CommonPresenter(this, this);
    }

//    void request(String id) {
//        RequestBody<AddOverseasRequestModel> requestBody = new RequestBody<>();
//        RequestBody.HeaderBean headerBean = new RequestBody.HeaderBean(Utils.getIPAddress(mActivity), spUtils.getSecretKeyId());
//        AddOverseasRequestModel requestModel = new AddOverseasRequestModel();
//        requestModel.setUserNo(spUtils.getUserId());
//        requestModel.setId(id);//amount
//
//        requestBody.setBody(requestModel);
//        requestBody.setHeader(headerBean);
//
//        Gson gson = new Gson();
//        String json = gson.toJson(requestBody);
//        LogUtils.logd("参数：" + json);
//        LogUtils.logd("isEdit：" + isEdit);
//        try {
//            encMsg = DESedeUtil.getRequestAfter3DES(spUtils.getSecretKey(), spUtils.getSecretIv(), json);
//            signMsg = DESedeUtil.getRequestAfterSign(encMsg);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        if (isEdit) {
//            mvpPresenter.updatecurfinsum(encMsg, signMsg, "2", spUtils.getSecretKeyId());
//        } else {
//            mvpPresenter.getcurfin(encMsg, signMsg, "2", spUtils.getSecretKeyId());
//        }
//    }

    void request() {
        RequestBody<AddOverseasRequestModel> requestBody = new RequestBody<>();
        RequestBody.HeaderBean headerBean = new RequestBody.HeaderBean(Utils.getIPAddress(mActivity), spUtils.getSecretKeyId());
        AddOverseasRequestModel requestModel = new AddOverseasRequestModel();
        requestModel.setUserNo(spUtils.getUserId());
        requestModel.setId(id);

        requestBody.setBody(requestModel);
        requestBody.setHeader(headerBean);

        Gson gson2 = new Gson();
        String json2 = gson2.toJson(requestBody);

        try {
            encMsg = DESedeUtil.getRequestAfter3DES(spUtils.getSecretKey(), spUtils.getSecretIv(), json2);
            signMsg = DESedeUtil.getRequestAfterSign(encMsg);
        } catch (Exception e) {
            e.printStackTrace();
        }
        mvpPresenter.getbankcard(encMsg, signMsg, "2", spUtils.getSecretKeyId());
    }

    @OnClick({R.id.tv_right})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_right:
                Intent in = new Intent(mActivity, AddBankCard.class);
                in.putExtra("id", id);
                in.putExtra("name", name);
                in.putExtra("bankNo", bankNo);
                in.putExtra("bankType", bankType);
                in.putExtra("memo", memo);
                in.putExtra("bankId", bankId);
                if(isCredit && Utils.isNumeric(amount) && Double.parseDouble(amount) > 0) {
                    in.putExtra("amount", amount);
                }
                startActivity(in);
                overridePendingTransition(R.anim.left_in, 0);
                break;
        }
    }

    @Override
    public void getDataSuccess(BankcardDetailModel model) {
        try {
//            tvAmount.setText(Utils.addCommaContainsPoint(model.getBody().getCurfinDto().getAmount()));

            bankId = model.getBody().getCurfinDto().getBankId();
            bankNo = model.getBody().getCurfinDto().getBankNo();
            memo = model.getBody().getCurfinDto().getMemo();
            amount = model.getBody().getCurfinDto().getAmount();//信用卡额度
            bankType = model.getBody().getCurfinDto().getBankType();
            bankname = model.getBody().getCurfinDto().getBankName();
            if (bankType != null && bankType.equals("1")) {
                isCredit = false;
                bankTypeName = "借记卡";
                tvEdu.setVisibility(View.GONE);
                viewEdu.setVisibility(View.GONE);
            } else {
                bankTypeName = "信用卡";
                isCredit = true;
                if(amount != null && Utils.isNumeric(amount) && Double.parseDouble(amount) > 0){
                    tvEdu.setText("当前额度 "+Utils.addCommaContainsPoint(amount));
                    tvEdu.setVisibility(View.VISIBLE);
                    viewEdu.setVisibility(View.VISIBLE);
                }else{
                    tvEdu.setVisibility(View.GONE);
                    viewEdu.setVisibility(View.GONE);
                }
            }

            if (memo != null && !TextUtils.isEmpty(memo)) {
                llMemo.setVisibility(View.VISIBLE);
            } else {
                llMemo.setVisibility(View.GONE);
            }
            tvBankCardNo.setText(bankNo);
            if (bankNo != null && !TextUtils.isEmpty(bankNo)) {
                if (bankNo.length() <= 4) {
                    tvBankCardNo.setText(bankNo);
                } else if(bankNo.length() > 4 && bankNo.length() < 16){
                    tvBankCardNo.setText(ss(Utils.intervalString(bankNo).trim()));
                }else{//>=16位
//                    tvBankCardNo.setText(ss2(Utils.intervalString(bankNo)));
                    tvBankCardNo.setText(ss2(bankNo));
                }

            } else {
                tvBankCardNo.setText("您还没有填写银行卡号哦~");
            }

            tvCardname.setText(bankname + bankTypeName);
            tvMemo.setText(memo);

            Glide.with(mActivity).load(ApiStores.supportbankPrefix+bankLogo)/*.crossFade()*/.into(ivBankIcon);

            LogUtils.logd(ss(Utils.intervalString(bankNo)));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**
     * 16位以下
     * @param input
     * @return
     */
    String ss(String input){//1222 5555 5555
        int length = input.length();
        StringBuilder sb = new StringBuilder();
        for (int i = 1; i < length; i++) {
            if(i%5 != 0){
                sb.append("*");
            }else{
                sb.append(" ");
            }
        }
        LogUtils.logd("sb.toString():"+sb.toString()+"\ninput:"+input.substring(length-1));
        return sb.toString()+input.substring(length-1);
    }

    /**
     * 16位以上
     * @param input
     * @return
     */
    String ss2(String input){
        int length = input.length();
        return "**** **** **** "+input.substring(length-4);
    }


    @Override
    public void getDataFail(String msg) {
        toastShow(msg);
    }

}
