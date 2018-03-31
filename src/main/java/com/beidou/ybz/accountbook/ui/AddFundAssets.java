package com.beidou.ybz.accountbook.ui;

import android.Manifest;
import android.animation.ValueAnimator;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.util.TypedValue;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.baidu.mobstat.StatService;
import com.beidou.ybz.accountbook.R;
import com.beidou.ybz.accountbook.adapter.AdapterStockSearch;
import com.beidou.ybz.accountbook.mvp.entity.AddOverseasRequestModel;
import com.beidou.ybz.accountbook.mvp.entity.IndexModel;
import com.beidou.ybz.accountbook.mvp.entity.RequestBody;
import com.beidou.ybz.accountbook.mvp.entity.SercetKeyOverdueModel;
import com.beidou.ybz.accountbook.mvp.entity.StockSearchModel;
import com.beidou.ybz.accountbook.mvp.main.TextWatchView;
import com.beidou.ybz.accountbook.mvp.presenter.CommonPresenter;
import com.beidou.ybz.accountbook.mvp.view.CommonView;
import com.beidou.ybz.accountbook.mvp.view.OtherView;
import com.beidou.ybz.accountbook.util.DESedeUtil;
import com.beidou.ybz.accountbook.util.ImeUtil;
import com.beidou.ybz.accountbook.util.LogUtils;
import com.beidou.ybz.accountbook.util.SppaConstant;
import com.beidou.ybz.accountbook.util.ToastUtils;
import com.beidou.ybz.accountbook.widget.ClearEditText;
import com.beidou.ybz.accountbook.mvp.other.MvpActivity;
import com.beidou.ybz.accountbook.util.ActivityUtils;
import com.beidou.ybz.accountbook.util.Utils;
import com.beidou.ybz.accountbook.widget.NewWatcher;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.google.gson.Gson;
import com.jakewharton.rxbinding.widget.RxTextView;
import com.tbruyelle.rxpermissions.RxPermissions;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import rx.Observable;
import rx.functions.Action1;
import rx.functions.Func4;

/**
 * Author: xu.yang on 2017/12/1
 * QQ:754444814
 * E-mail:754444814@qq.com
 * module: 添加基金资产
 */
public class AddFundAssets extends MvpActivity<CommonPresenter> implements CommonView<SercetKeyOverdueModel>, OtherView<StockSearchModel> {
    @Bind(R.id.tv_title)
    TextView tvTitle;
    @Bind(R.id.cetMoney)
    ClearEditText cetMoney;
    @Bind(R.id.toolbar)
    Toolbar toolbar;
    @Bind(R.id.ivShowMore)
    ImageView ivShowMore;
    @Bind(R.id.llShowMore)
    LinearLayout llShowMore;
    @Bind(R.id.llMore)
    LinearLayout llMore;
    @Bind(R.id.btnSave)
    Button btnSave;
    @Bind(R.id.cetName)
    ClearEditText cetName;
    @Bind(R.id.cetBrokerName)
    ClearEditText cetBrokerName;
    @Bind(R.id.cetMemo)
    ClearEditText cetMemo;
    @Bind(R.id.rvSearch)
    RecyclerView rvSearch;
    @Bind(R.id.tvSearchMore)
    TextView tvSearchMore;
    @Bind(R.id.llSearch)
    LinearLayout llSearch;
    private boolean isMoreShow;
    private int height;
    private String encMsg, signMsg;
    private ValueAnimator valueAnimatorShow, valueAnimatorHide;
    String id, stockCode, stockName, positionNumber, brokerName, memo, from;
    private boolean isEdit;//true-编辑 false-新增
    private ArrayList<StockSearchModel.ResultBean> resultBeans, resultBeansSub;
    private boolean isFromList;
    private AdapterStockSearch mAdapter;
    TextWatcher textWatcher;
    String keyword;
    private boolean isFromAddasset;//来自首次添加页面
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addfundassets);
        ButterKnife.bind(this);

        tvTitle.setTextColor(getResources().getColor(R.color.txt_color));
        toolbar.setBackgroundColor(getResources().getColor(R.color.colorWhite));
        toolbar.setNavigationIcon(R.drawable.back_black);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                StatService.onEvent(mActivity,"点击基金资产编辑页返回按钮","放弃基金资产编辑",1);
                ActivityUtils.finishActivity(mActivity);
                ImeUtil.hideSoftKeyboard(cetName);
            }
        });

        textWatcher = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                LogUtils.logd("onTextChanged清空:" + stockCode);
                stockName = "";
                stockCode = "";
            }
            @Override
            public void afterTextChanged(Editable s) {
//                LogUtils.logd("afterTextChanged清空:" + stockCode);
                keyword = s.toString();
                if (TextUtils.isEmpty(keyword)) {
                    llSearch.setVisibility(View.GONE);
                } else {
                    search(keyword);
                    mAdapter.setKey(keyword);
                }
            }
        };

        initView();
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        handleIntent(intent);
    }

    private void handleIntent(Intent in) {
        //计算llMore的高度
        int w = View.MeasureSpec.makeMeasureSpec(0,
                View.MeasureSpec.UNSPECIFIED);
        int h = View.MeasureSpec.makeMeasureSpec(0,
                View.MeasureSpec.UNSPECIFIED);
        llMore.measure(w, h);
        height = llMore.getMeasuredHeight();
        Log.e("height", "initView: " + height);

        initAnimator();

        if (in != null) {
            id = in.getStringExtra("id");
            stockCode = in.getStringExtra("stockCode");
            stockName = in.getStringExtra("stockName");
            positionNumber = in.getStringExtra("positionNumber");
            brokerName = in.getStringExtra("brokerName");
            memo = in.getStringExtra("memo");
            from = in.getStringExtra("from");
        }

        if(memo != null && !TextUtils.isEmpty(memo)
                || brokerName != null && !TextUtils.isEmpty(brokerName)){
            cetName.postDelayed(new Runnable() {
                @Override
                public void run() {
                    valueAnimatorShow.start();
                    isMoreShow = true;
                    ivShowMore.animate().rotation(180f).setDuration(400).start();
                }
            },300);
        }

        LogUtils.logd(stockName+"::"+stockCode);

        if (stockName != null && !TextUtils.isEmpty(stockName)) {
            isEdit = true;
            cetName.setText(stockName + stockCode);
            LogUtils.logd("length:" + cetName.getText().toString().length());
            cetName.setSelection(cetName.getText().toString().length());
        } else {
            isEdit = false;
        }
        LogUtils.logd("isEdit:" + isEdit);
        if (isEdit) {
            tvTitle.setText("编辑基金资产");
        } else {
            tvTitle.setText("添加基金资产");
        }

        if (from != null && from.equals("list")) {
            isFromList = true;
        } else {
            isFromList = false;
        }
        if(from != null && from.equals("addasset")){
            isFromAddasset = true;
        }else{
            isFromAddasset = false;
        }


        if (stockName != null && stockName.length() > 0) {
            cetName.setSelection(cetName.getText().toString().length());
        }
        cetMoney.setText(positionNumber);
        cetBrokerName.setText(brokerName);
        cetMemo.setText(memo);
        if (!TextUtils.isEmpty(cetMoney.getText().toString())) {
            cetMoney.setTextSize(TypedValue.COMPLEX_UNIT_SP, 30);
        }
    }

    void initView() {
        bindViewByRxBinding();
        Utils.textChangedListener(cetMoney);
        handleIntent(getIntent());

        rvSearch.setLayoutManager(new LinearLayoutManager(this));
        mAdapter = new AdapterStockSearch(R.layout.stocksearch_item, null);
        rvSearch.setAdapter(mAdapter);

        mAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                stockName = resultBeans.get(position).getName();
                stockCode = resultBeans.get(position).getCode();
                cetName.removeTextChangedListener(textWatcher);
                cetName.setText(stockName+stockCode);
                cetName.setSelection(cetName.getText().toString().length());
                llSearch.setVisibility(View.GONE);
                cetName.addTextChangedListener(textWatcher);

            }
        });

        cetName.addTextChangedListener(textWatcher);
    }

    void search(String keyword) {
        mvpPresenter.loadwordsgm("searchResult", keyword);
    }

    /**
     * 用combineLatest处理表单验证
     */
    private void bindViewByRxBinding() {
        Observable<CharSequence> Observable1 = RxTextView.textChanges(cetName);
        Observable<CharSequence> Observable2 = RxTextView.textChanges(cetMoney);
        Observable<CharSequence> Observable3 = RxTextView.textChanges(cetBrokerName);
        Observable<CharSequence> Observable4 = RxTextView.textChanges(cetMemo);

        Observable.combineLatest(Observable1, Observable2, Observable3, Observable4,
                new Func4<CharSequence, CharSequence, CharSequence, CharSequence, Boolean>() {
                    @Override
                    public Boolean call(CharSequence string1, CharSequence string2, CharSequence string3, CharSequence string4 ) {
                        return !Utils.isTextEmpty(string1.toString()) || !Utils.isTextEmpty(string2.toString())
                                || !Utils.isTextEmpty(string3.toString()) || !Utils.isTextEmpty(string4.toString());
                    }
                }).subscribe(new Action1<Boolean>() {
            @Override
            public void call(Boolean verify) {
                if (verify) {
                    btnSave.setEnabled(true);
                    btnSave.setBackgroundResource(R.drawable.bg1);
                } else {
                    btnSave.setEnabled(false);
                    btnSave.setBackgroundResource(R.drawable.bg_unenabled);
                }
            }
        });
    }

    /**
     * 布局展开/隐藏动画
     * 属性动画-动态改变布局高度
     */
    void initAnimator() {
        final ValueAnimator.AnimatorUpdateListener ani = new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                int h = (Integer) animation.getAnimatedValue();
                //动态更新view的高度
                llMore.getLayoutParams().height = h;
                llMore.requestLayout();
            }
        };

        valueAnimatorHide = ValueAnimator.ofInt(height, 0);
        valueAnimatorShow = ValueAnimator.ofInt(0, height);

        valueAnimatorHide.addUpdateListener(ani);
        valueAnimatorShow.addUpdateListener(ani);

        valueAnimatorShow.setDuration(400);
        valueAnimatorHide.setDuration(400);

    }

    void save() {
        RequestBody<AddOverseasRequestModel> loginRequestModel = new RequestBody<>();
        RequestBody.HeaderBean headerBean = new RequestBody.HeaderBean(Utils.getIPAddress(mActivity), spUtils.getSecretKeyId());
        AddOverseasRequestModel requestModel = new AddOverseasRequestModel();
        requestModel.setUserNo(spUtils.getUserId());
        requestModel.setId(id);
        requestModel.setFundName(stockName);
        requestModel.setFundCode(stockCode);
        requestModel.setPositionNumber(cetMoney.getText().toString());
        requestModel.setBrokerName(cetBrokerName.getText().toString());
        requestModel.setMemo(cetMemo.getText().toString());

        loginRequestModel.setBody(requestModel);
        loginRequestModel.setHeader(headerBean);

        Gson gson2 = new Gson();
        String json2 = gson2.toJson(loginRequestModel);
        LogUtils.logd("Channel:"+ SppaConstant.getChannel(mActivity));
        LogUtils.logd(json2);
        try {
            encMsg = DESedeUtil.getRequestAfter3DES(spUtils.getSecretKey(), spUtils.getSecretIv(), json2);
            signMsg = DESedeUtil.getRequestAfterSign(encMsg);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (isEdit) {
            mvpPresenter.updatefund(encMsg, signMsg, "2", spUtils.getSecretKeyId());
        } else {
            mvpPresenter.addfund(encMsg, signMsg, "2", spUtils.getSecretKeyId());
        }
    }

    @Override
    public void getDataSuccess(SercetKeyOverdueModel model) {
        if (isEdit) {
            toastShow("编辑成功", R.drawable.gou_toast);
            cetName.postDelayed(new Runnable() {
                @Override
                public void run() {
                    ActivityUtils.startActivity(mActivity,FundActivity.class);//从详情页跳转过来编辑成功后，回到列表页
                    ActivityUtils.finishActivity(mActivity);
                }
            }, ToastUtils.toastTime);

        } else {
            toastShow("添加成功", R.drawable.gou_toast);
            cetName.postDelayed(new Runnable() {
                @Override
                public void run() {
                    if (isFromList) {
                        ActivityUtils.finishActivity(mActivity);//从列表过来，保存成功后直接返回到列表
                    } else {
                        Intent in = new Intent(mActivity, FundActivity.class);
                        in.putExtra("from", "add");
                        startActivity(in);
                        overridePendingTransition(R.anim.left_in, 0);
                    }
                }
            },ToastUtils.toastTime);

        }
    }

    @Override
    public void getDataFail(String msg) {

    }

    @Override
    protected CommonPresenter createPresenter() {
        return new CommonPresenter(this,this);
    }

    @OnClick({R.id.llShowMore, R.id.btnSave,  R.id.tvSearchMore})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.llShowMore:
                StatService.onEvent(mActivity,"添加基金资产时点击更多","添加更多基金信息",1);
                ImeUtil.hideSoftKeyboard(cetName);
                if (!isMoreShow) {
                    valueAnimatorShow.start();
                    isMoreShow = true;
                    ivShowMore.animate().rotation(180f).setDuration(400).start();
                } else {
                    valueAnimatorHide.start();
                    isMoreShow = false;
                    ivShowMore.animate().rotation(0f).setDuration(400).start();
                }
                break;
            case R.id.btnSave:
                if(isEdit){
                    StatService.onEvent(mActivity,"点击基金资产编辑页确认按钮","编辑基金资产",1);
                }else if(isFromAddasset){
                    StatService.onEvent(mActivity,"点击基金资产添加页确认按钮","首次新增基金",1);
                }else{
                    StatService.onEvent(mActivity,"基金列表进入添加页并点击确认按钮","确认新增基金",1);
                }

                if (valid()) {
                    save();
                }
                break;
            case R.id.tvSearchMore:
                StatService.onEvent(mActivity, "添加基金时点击下拉框更多按钮", "检索更多基金", 1);
                Intent in = new Intent(mActivity, StockSearchMoreActivity.class);
                in.putExtra("key",keyword);
                in.putParcelableArrayListExtra("resultBeans", resultBeans);
                startActivityForResult(in, 0013);
                overridePendingTransition(R.anim.left_in, 0);
                StatService.onEvent(mActivity, "添加股票时点击下拉框更多按钮", "检索更多股票", 1);

                break;
        }
    }

    /**
     * 提交前判断输入是否为空
     *
     * @return
     */
    boolean valid() {
        if (TextUtils.isEmpty(cetName.getText().toString()) ) {
            toastShow("请输入基金名称/代码");
            return false;
        }else if(TextUtils.isEmpty(cetMoney.getText().toString())){
            toastShow("请输入持仓份额");
            return false;
        }  else if (TextUtils.isEmpty(stockCode)) {
            toastShow("您输入的基金名称/代码不存在，请重新输入");
            return false;
        }else {
            return true;
        }
    }


    //搜索结果
    @Override
    public void onSuccess(StockSearchModel model) {
        LogUtils.logd(model.toString());
        resultBeans = (ArrayList<StockSearchModel.ResultBean>) model.getResult();
        if (resultBeans != null && resultBeans.size() > 4) {
            tvSearchMore.setVisibility(View.VISIBLE);
            resultBeansSub = new ArrayList<StockSearchModel.ResultBean>(resultBeans.subList(0, 4));
        } else {
            tvSearchMore.setVisibility(View.GONE);
            resultBeansSub = resultBeans;
        }

        if (resultBeans.size() > 0) {
            llSearch.setVisibility(View.VISIBLE);
        } else {
            llSearch.setVisibility(View.GONE);
        }

        mAdapter.setNewData(resultBeansSub);
    }

    @Override
    public void onFail(String model) {

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            stockName = data.getStringExtra("stockName");
            stockCode = data.getStringExtra("stockCode");
            if (requestCode == 0013) {
                cetName.removeTextChangedListener(textWatcher);
                cetName.setText(stockName+stockCode);
                cetName.setSelection(cetName.getText().toString().length());
                llSearch.setVisibility(View.GONE);
                cetName.addTextChangedListener(textWatcher);
            }
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        if(isEdit) {
            StatService.onPageStart(mActivity, "编辑基金页面");
        }else{
            StatService.onPageStart(mActivity, "添加基金页面");
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        ImeUtil.hideSoftKeyboard(cetName);

        if(isEdit) {
            StatService.onPageEnd(mActivity, "编辑基金页面");
        }else{
            StatService.onPageEnd(mActivity, "添加基金页面");
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK
                && event.getAction() == KeyEvent.ACTION_DOWN) {
            StatService.onEvent(mActivity,"点击基金资产编辑页返回按钮","放弃基金资产编辑",1);
            ActivityUtils.finishActivity(mActivity);
            ImeUtil.hideSoftKeyboard(cetName);
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}
