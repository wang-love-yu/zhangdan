package com.beidou.ybz.accountbook.ui.fragment;

import android.annotation.TargetApi;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.support.design.widget.AppBarLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.ArrayMap;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.baidu.mobstat.StatService;
import com.beidou.ybz.accountbook.R;
import com.beidou.ybz.accountbook.adapter.AdapterRecommend;
import com.beidou.ybz.accountbook.adapter.AdapterZichan;
import com.beidou.ybz.accountbook.adapter.AdapterZichan2;
import com.beidou.ybz.accountbook.mvp.entity.AccountIndexModel;
import com.beidou.ybz.accountbook.mvp.entity.AddOverseasRequestModel;
import com.beidou.ybz.accountbook.mvp.entity.EncryptedResponseModel;
import com.beidou.ybz.accountbook.mvp.entity.GetHjzModel;
import com.beidou.ybz.accountbook.mvp.entity.RequestBody;
import com.beidou.ybz.accountbook.mvp.entity.RequestBody2;
import com.beidou.ybz.accountbook.mvp.entity.SecretKeyResponseModel;
import com.beidou.ybz.accountbook.mvp.entity.SercetKeyOverdueModel;
import com.beidou.ybz.accountbook.mvp.entity.StartModel;
import com.beidou.ybz.accountbook.mvp.other.MvpFragment;
import com.beidou.ybz.accountbook.mvp.presenter.CommonPresenter;
import com.beidou.ybz.accountbook.mvp.view.CommonView;
import com.beidou.ybz.accountbook.mvp.view.GetPiccodeView;
import com.beidou.ybz.accountbook.mvp.view.OtherView;
import com.beidou.ybz.accountbook.mvp.view.ThirdView;
import com.beidou.ybz.accountbook.retrofit.ApiStores;
import com.beidou.ybz.accountbook.retrofit.AppClient;
import com.beidou.ybz.accountbook.ui.AboutUsActivity;
import com.beidou.ybz.accountbook.ui.AddArrears;
import com.beidou.ybz.accountbook.ui.AddAssetsActivity;
import com.beidou.ybz.accountbook.ui.AddBankCard;
import com.beidou.ybz.accountbook.ui.AddCredit;
import com.beidou.ybz.accountbook.ui.AddCurrentFinancial;
import com.beidou.ybz.accountbook.ui.AddCustom;
import com.beidou.ybz.accountbook.ui.AddDomin;
import com.beidou.ybz.accountbook.ui.AddEquityAssets;
import com.beidou.ybz.accountbook.ui.AddFixedIncome;
import com.beidou.ybz.accountbook.ui.AddFundAssets;
import com.beidou.ybz.accountbook.ui.AddGame;
import com.beidou.ybz.accountbook.ui.AddInsuranceAssets;
import com.beidou.ybz.accountbook.ui.AddInternetAccount;
import com.beidou.ybz.accountbook.ui.AddLoan;
import com.beidou.ybz.accountbook.ui.AddOverseasAssets;
import com.beidou.ybz.accountbook.ui.AddPrepaidCard;
import com.beidou.ybz.accountbook.ui.AddPrivateOfferingFund;
import com.beidou.ybz.accountbook.ui.AddReimbursement;
import com.beidou.ybz.accountbook.ui.AddStockAssets;
import com.beidou.ybz.accountbook.ui.ArrearsassetActivity;
import com.beidou.ybz.accountbook.ui.BankCardActivity;
import com.beidou.ybz.accountbook.ui.BlackboxGuideActivity;
import com.beidou.ybz.accountbook.ui.CreditActivity;
import com.beidou.ybz.accountbook.ui.CurrentFinancialActivity;
import com.beidou.ybz.accountbook.ui.CustomActivity;
import com.beidou.ybz.accountbook.ui.DominActivity;
import com.beidou.ybz.accountbook.ui.EquityActivity;
import com.beidou.ybz.accountbook.ui.FixedIncomeActivity;
import com.beidou.ybz.accountbook.ui.FundActivity;
import com.beidou.ybz.accountbook.ui.GameActivity;
import com.beidou.ybz.accountbook.ui.HuodongDetailActivity;
import com.beidou.ybz.accountbook.ui.InsuranceActivity;
import com.beidou.ybz.accountbook.ui.InternetActivity;
import com.beidou.ybz.accountbook.ui.LoanActivity;
import com.beidou.ybz.accountbook.ui.LoginActivity;
import com.beidou.ybz.accountbook.ui.MyBlackboxDetailActivity;
import com.beidou.ybz.accountbook.ui.OverseasActivity;
import com.beidou.ybz.accountbook.ui.PrepaidActivity;
import com.beidou.ybz.accountbook.ui.PrivateOfferingActivity;
import com.beidou.ybz.accountbook.ui.ReimbursementActivity;
import com.beidou.ybz.accountbook.ui.StockActivity;
import com.beidou.ybz.accountbook.ui.X5TitleWebActivity;
import com.beidou.ybz.accountbook.ui.X5WebActivity;
import com.beidou.ybz.accountbook.util.ActivityUtils;
import com.beidou.ybz.accountbook.util.AlertDialogUtils;
import com.beidou.ybz.accountbook.util.AppBarStateChangeListener;
import com.beidou.ybz.accountbook.util.DESedeUtil;
import com.beidou.ybz.accountbook.util.DialogClickListener;
import com.beidou.ybz.accountbook.util.GsonTools;
import com.beidou.ybz.accountbook.util.LogUtils;
import com.beidou.ybz.accountbook.util.SppaConstant;
import com.beidou.ybz.accountbook.util.Utils;
import com.beidou.ybz.accountbook.widget.MaterialDialog1;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.google.gson.Gson;
import com.orhanobut.logger.Logger;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.OnClick;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Author: Bob on 2017/10/30 15:16
 * QQ:754444814
 * E-mail:754444814@qq.com
 */
public class MainFragment extends MvpFragment<CommonPresenter> implements CommonView<AccountIndexModel>,
        OtherView<SercetKeyOverdueModel>, ThirdView<SecretKeyResponseModel>, GetPiccodeView<StartModel> {
    @Bind(R.id.appbar)
    AppBarLayout appbar;
    @Bind(R.id.tvXinyuan)
    TextView tvXinyuan;
    @Bind(R.id.ivHeixiazi)
    ImageView ivHeixiazi;
    @Bind(R.id.rvZichan)
    RecyclerView rvZichan;
    @Bind(R.id.rvZichan2)
    RecyclerView rvZichan2;
    @Bind(R.id.rvRecommend)
    RecyclerView rvRecommend;
    @Bind(R.id.swipeRefresh)
    SwipeRefreshLayout mSwipeRefreshLayout;
    @Bind(R.id.tvJzc)
    TextView tvJzc;
    @Bind(R.id.tvZzc)
    TextView tvZzc;
    @Bind(R.id.tvZfz)
    TextView tvZfz;
    @Bind(R.id.ivVisible)
    ImageView ivVisible;
    @Bind(R.id.llRecommend)
    LinearLayout llRecommend;
    @Bind(R.id.llZichanEmpty)
    LinearLayout llZichanEmpty;
    private AdapterZichan mAdapter;
    private AdapterZichan2 mAdapter2;
    private AdapterRecommend mAdapterRecommend;
    private List<String> list, listKey;
    AlertDialogUtils alertDialogUtils, alertDialogOther;
    AlertDialogUtils alertDialogSingle;
    private String encMsg, signMsg;
    private ArrayMap<String, String> map;
    private List<AccountIndexModel.BodyBean.FieldViewAmountListBean> fieldViewAmountListBeanList, fieldViewAmountListBeanListCopy;
    private List<AccountIndexModel.BodyBean.FieldViewdelListBean> fieldViewdelListBeanList;
    private List<AccountIndexModel.BodyBean.ReProListBean> reProListBeanList;
    private boolean isLoadMore, isVisible;
    private String jzc, zzc, zqk;
    private List<String> fieldnListCopy;
    private int count = 0;
    private int mPosition;//删除的position
    private MaterialDialog1 materialDialog1;
    private TextView tvDesc, tvUpdate;
    private String updateUrl;

    @Override
    public int getLayoutRes() {
        count = 0;

        View view = LayoutInflater.from(getActivity()).inflate(R.layout.layout_newversion, null);
        tvDesc = ((TextView) view.findViewById(R.id.tvdesc));
        tvUpdate = ((TextView) view.findViewById(R.id.tvUpdateDate));
        materialDialog1 = new MaterialDialog1(getActivity()).setContentView(view);
        tvUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Uri uri = Uri.parse(updateUrl);
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);
            }
        });

        return R.layout.fragment_main;
    }

    void getSecretkey() {
        RequestBody2 requestBody = new RequestBody2();
        RequestBody2.HeaderBean headerBean = new RequestBody2.HeaderBean();
        requestBody.setHeader(headerBean);
        RequestBody2.BodyBean bodyBean = new RequestBody2.BodyBean();
        requestBody.setBody(bodyBean);
        Gson gson = new Gson();
        String json = gson.toJson(requestBody);
        Logger.e("aaaa:：" + json);
        Logger.json(json);
        mvpPresenter.getSecretkey(json);
    }

    /**
     * 请求
     */
    void getRequest(String id) {
        RequestBody<AddOverseasRequestModel> yzmModelRequestBody = new RequestBody<>();
        RequestBody.HeaderBean headerBean2 = new RequestBody.HeaderBean(Utils.getIPAddress(mActivity), spUtils.getSecretKeyId());
        LogUtils.logd("ip:" + Utils.getIPAddress(mActivity));
        AddOverseasRequestModel userNoModel = new AddOverseasRequestModel();
        userNoModel.setUserNo(spUtils.getUserId());//
        LogUtils.logd("UserNo:" + spUtils.getUserId());
        userNoModel.setId(id);
        yzmModelRequestBody.setBody(userNoModel);
        yzmModelRequestBody.setHeader(headerBean2);

        Gson gson2 = new Gson();
        String json2 = gson2.toJson(yzmModelRequestBody);
        LogUtils.logd("参数:" + json2);
        try {
            encMsg = DESedeUtil.getRequestAfter3DES(spUtils.getSecretKey(), spUtils.getSecretIv(), json2);
            signMsg = DESedeUtil.getRequestAfterSign(encMsg);
        } catch (Exception e) {
            e.printStackTrace();
        }
        LogUtils.logd("id is empty:" + TextUtils.isEmpty(id));
        if (TextUtils.isEmpty(id)) {
            mvpPresenter.getAccountIndex(encMsg, signMsg, "2", spUtils.getSecretKeyId());
        } else {
            mvpPresenter.delindexzc(encMsg, signMsg, "2", spUtils.getSecretKeyId());
        }
    }

    @Override
    public void onResume() {
        super.onResume();

        StatService.onPageStart(mActivity, "首页-首页tab");

        LogUtils.loge("onResume count=" + count);
        LogUtils.loge("11111111111::"+("1.0.0".compareTo("1.0.1")));//-1
        LogUtils.loge("22222222222::"+("1.0.1".compareTo("1.0.0")));//1
        LogUtils.loge("11111111111::"+("1.0.0".compareTo("1.2")));//-2
        LogUtils.loge("22222222222::"+("1.4".compareTo("1.0.0")));//4
//        if (count != 0) {
        getRequest("");
//        }
    }

    private String getAppInfo() {
        try {
            String pkName = getActivity().getPackageName();
            String versionName = getActivity().getPackageManager().getPackageInfo(
                    pkName, 0).versionName;
            int versionCode = getActivity().getPackageManager()
                    .getPackageInfo(pkName, 0).versionCode;
            return pkName + "   " + versionName + "  " + versionCode;
        } catch (Exception e) {
        }
        return null;
    }

    @TargetApi(Build.VERSION_CODES.KITKAT)
    @Override
    public void initView() {
        alertDialogUtils = new AlertDialogUtils(mActivity);
        alertDialogOther = new AlertDialogUtils(mActivity);
        alertDialogSingle = new AlertDialogUtils(mActivity, false);

        LogUtils.loge("initView()------count:" + count);
        LogUtils.logd("应用包名：" + getAppInfo());
        LogUtils.loge("initView count=" + count);
        if (count == 0) {//每次重启，获取一次秘钥
            getSecretkey();
        }
        isVisible = true;

        rvZichan.setLayoutManager(new LinearLayoutManager(mActivity));
        rvZichan2.setLayoutManager(new LinearLayoutManager(mActivity));
        rvRecommend.setLayoutManager(new LinearLayoutManager(mActivity));
        rvZichan.setNestedScrollingEnabled(false);
        rvZichan2.setNestedScrollingEnabled(false);
        rvRecommend.setNestedScrollingEnabled(false);

        mAdapter = new AdapterZichan(R.layout.main_zichan_item2, null);//用户自添加
        rvZichan.setAdapter(mAdapter);
        mAdapter2 = new AdapterZichan2(R.layout.main_zichan_item, null);//示例资产
        rvZichan2.setAdapter(mAdapter2);
        mAdapterRecommend = new AdapterRecommend(R.layout.main_ecommend_item, null);
        rvRecommend.setAdapter(mAdapterRecommend);

        mAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                Intent in = null;
                switch (fieldViewAmountListBeanList.get(position).getNameValue()) {
                    case "0001":
                        StatService.onEvent(mActivity, "首页点击股票资产", "[首页查看股票]", 1);
                        in = new Intent(mActivity, StockActivity.class);//股票
                        break;
                    case "0002":
                        StatService.onEvent(mActivity, "首页点击基金资产", "[首页查看基金]", 1);
                        in = new Intent(mActivity, FundActivity.class);//基金
                        break;
                    case "0003":
                        StatService.onEvent(mActivity, "首页点击活期理财资产", "[首页查看活期理财]", 1);
                        in = new Intent(mActivity, CurrentFinancialActivity.class);//活期理财
                        break;
                    case "0004":
                        StatService.onEvent(mActivity, "首页点击银行卡资产", "[首页查看银行卡]", 1);
                        in = new Intent(mActivity, BankCardActivity.class);//银行卡
                        break;
                    case "0005":
                        StatService.onEvent(mActivity, "首页点击预付卡资产", "[首页查看预付卡]", 1);
                        in = new Intent(mActivity, PrepaidActivity.class);//预付卡
                        break;
                    case "0006":
                        StatService.onEvent(mActivity, "首页点击欠款资产", "[首页查看欠款]", 1);
                        in = new Intent(mActivity, ArrearsassetActivity.class);//欠款
                        break;
                    case "0007":
                        StatService.onEvent(mActivity, "首页点击贷款资产", "[首页查看贷款]", 1);
                        in = new Intent(mActivity, CreditActivity.class);//贷款
                        break;
                    case "0008":
                        StatService.onEvent(mActivity, "首页点击股权资产", "[首页查看股权]", 1);
                        in = new Intent(mActivity, EquityActivity.class);//股权
                        break;
                    case "0009":
                        StatService.onEvent(mActivity, "首页点击固定收益资产", "[首页查看固定收益]", 1);
                        in = new Intent(mActivity, FixedIncomeActivity.class);//固定收益
                        break;
                    case "0010":
                        StatService.onEvent(mActivity, "首页点击私募资产", "[首页查看私募]", 1);
                        in = new Intent(mActivity, PrivateOfferingActivity.class);//私募
                        break;
                    case "0011":
                        StatService.onEvent(mActivity, "首页点击保险资产", "[首页查看保险]", 1);
                        in = new Intent(mActivity, InsuranceActivity.class);//保险
                        break;
                    case "0012":
                        StatService.onEvent(mActivity, "首页点击房产资产", "[首页查看房产]", 1);
                        in = new Intent(mActivity, OverseasActivity.class);//海外房产
                        break;
                    case "0013":
                        StatService.onEvent(mActivity, "首页点击报销资产", "[首页查看报销]", 1);
                        in = new Intent(mActivity, ReimbursementActivity.class);//报销单
                        break;
                    case "0014":
                        StatService.onEvent(mActivity, "首页点击域名资产", "[首页查看域名]", 1);
                        in = new Intent(mActivity, DominActivity.class);//域名估值
                        break;
                    case "0015":
                        StatService.onEvent(mActivity, "首页点击账号资产", "[首页查看账号]", 1);
                        in = new Intent(mActivity, InternetActivity.class);//互联网账号
                        break;
                    case "0016":
                        StatService.onEvent(mActivity, "首页点击游戏资产", "[首页查看游戏]", 1);
                        in = new Intent(mActivity, GameActivity.class);//游戏
                        break;
                    case "0017":
                        StatService.onEvent(mActivity, "首页点击借款资产", "[首页查看借款]", 1);
                        in = new Intent(mActivity, LoanActivity.class);//借款
                        break;
                    case "9999":
                        StatService.onEvent(mActivity, "首页点击其他资产", "[首页查看其他]", 1);
                        in = new Intent(mActivity, CustomActivity.class);//其它
                        break;
                    default:
                        in = new Intent(mActivity, FundActivity.class);//基金
                        break;
                }
                if (in != null) {
                    mActivity.startActivity(in);
                    mActivity.overridePendingTransition(R.anim.left_in, 0);
                }
            }
        });

        mAdapter2.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                LogUtils.logd("点击财富宝");
//                if (spUtils.getIsLogin()){
                requestUrl();
//                }else {
//                    mActivity.startActivity(new Intent(mActivity,LoginActivity.class));
//                    mActivity.overridePendingTransition(R.anim.left_in, 0);
//                }

            }
        });

        mAdapter2.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                Intent in = null;
                if (!spUtils.getIsLogin()) {
                    ActivityUtils.startActivityRightInWithFrom(getActivity(), LoginActivity.class, "forgetGes");
                } else {
                    switch (fieldViewdelListBeanList.get(position).getNameValue()) {
                        case "0001":
                            in = new Intent(mActivity, AddStockAssets.class);//股票
                            break;
                        case "0002":
                            in = new Intent(mActivity, AddFundAssets.class);//基金
                            break;
                        case "0003":
                            in = new Intent(mActivity, AddCurrentFinancial.class);//活期理财
                            break;
                        case "0004":
                            in = new Intent(mActivity, AddBankCard.class);//银行卡
                            break;
                        case "0005":
                            in = new Intent(mActivity, AddPrepaidCard.class);//预付卡
                            break;
                        case "0006":
                            in = new Intent(mActivity, AddArrears.class);//欠款
                            break;
                        case "0007":
                            in = new Intent(mActivity, AddCredit.class);//贷款
                            break;
                        case "0008":
                            in = new Intent(mActivity, AddEquityAssets.class);//股权
                            break;
                        case "0009":
                            in = new Intent(mActivity, AddFixedIncome.class);//固定收益
                            break;
                        case "0010":
                            in = new Intent(mActivity, AddPrivateOfferingFund.class);//私募
                            break;
                        case "0011":
                            in = new Intent(mActivity, AddInsuranceAssets.class);//保险
                            break;
                        case "0012":
                            in = new Intent(mActivity, AddOverseasAssets.class);//海外房产
                            break;
                        case "0013":
                            in = new Intent(mActivity, AddReimbursement.class);//报销单
                            break;
                        case "0014":
                            in = new Intent(mActivity, AddDomin.class);//域名估值
                            break;
                        case "0015":
                            in = new Intent(mActivity, AddInternetAccount.class);//互联网账号
                            break;
                        case "0016":
                            in = new Intent(mActivity, AddGame.class);//游戏
                            break;
                        case "0017":
                            in = new Intent(mActivity, AddLoan.class);//借款
                            break;
                        case "9999":
                            in = new Intent(mActivity, AddCustom.class);//其它
                            break;
                        default:
                            in = new Intent(mActivity, AddFundAssets.class);//基金
                            break;
                    }
                    if (in != null) {
                        mActivity.startActivity(in);
                        mActivity.overridePendingTransition(R.anim.left_in, 0);
                    }
                }
            }
        });

        mAdapter.setOnItemLongClickListener(new BaseQuickAdapter.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(BaseQuickAdapter adapter, View view, int position) {
                String bblx = fieldViewAmountListBeanList.get(position).getBblx();
                String hwfcFlag = fieldViewAmountListBeanList.get(position).getHwfcFlag();
                if (bblx != null && !TextUtils.isEmpty(bblx)) {
                    alertDialogSingle.setMessage("当前有资产不可删除~");
                    alertDialogSingle.show();
                } else if (hwfcFlag != null && hwfcFlag.equals("default")) {
                    alertDialogSingle.setMessage("当前有资产不可删除~");
                    alertDialogSingle.show();
                } else {
                    alertDialogUtils.setMessage("确定要删除" + fieldViewAmountListBeanList.get(position).getNameView() + "？");
                    alertDialogUtils.setPosition(position);
                    alertDialogUtils.show();
                }

                return true;
                //返回false时点击事件和长按事件会有冲突，设为true,事件拦截，冲突解除
            }
        });

        mAdapterRecommend.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                StatService.onEvent(mActivity, "首页点击广告位" + position, "[首页广告" + position + "点击]", 1);

//                ActivityUtils.startActivityRightInWithUrl(mActivity, HuodongDetailActivity.class, reProListBeanList.get(position).getProductUrl());

                Intent in = new Intent(mActivity, HuodongDetailActivity.class);
                in.putExtra("url", reProListBeanList.get(position).getProductUrl());
                in.putExtra("title", reProListBeanList.get(position).getProductName());
                startActivity(in);
                getActivity().overridePendingTransition(R.anim.left_in, 0);

//                Intent in = new Intent(getActivity(), WebActivity.class);
//                in.putExtra("url", reProListBeanList.get(position).getProductUrl());
//                getActivity().startActivity(in);
//                getActivity().overridePendingTransition(R.anim.left_in, 0);
            }
        });


        alertDialogUtils.setOnDialogClickListener(new DialogClickListener() {
            @Override
            public void clickYes(int which) {
                StatService.onEvent(mActivity, "点击首页的" + fieldViewAmountListBeanList.get(which).getNameView() + "删除按钮", "首页删除" + fieldViewAmountListBeanList.get(which).getNameView(), 1);
                mPosition = which;
                getRequest(fieldViewAmountListBeanList.get(which).getNameValue());
            }

            @Override
            public void clickNo() {
            }
        });

        alertDialogSingle.setOnDialogClickListener(new DialogClickListener() {
            @Override
            public void clickYes(int which) {
//                alertDialogSingle.show();
            }

            @Override
            public void clickNo() {
            }
        });


        //下拉刷新条的颜色
        mSwipeRefreshLayout.setColorSchemeResources(R.color.colorGold);

        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                isLoadMore = false;
                getRequest("");
            }
        });

        appbar.addOnOffsetChangedListener(new AppBarStateChangeListener() {
            @Override
            public void onOffset(AppBarLayout appBarLayout, int i) {
                int totalOffset = appBarLayout.getTotalScrollRange();
                float scale = (float) (totalOffset - i) / (float) totalOffset;//展开-1 收起-0
//                LogUtils.logd("appBar-totalOffset:"+totalOffset+"--scale:"+scale);
//                if(scale == 1){
//                    mSwipeRefreshLayout.setEnabled(true);
//                }else{
//                    mSwipeRefreshLayout.setEnabled(false);
//                }

                if (scale < 0.5) {
//                    tvHeixiazi.setTextColor(getResources().getColor(R.color.colorBlack));
                    ivHeixiazi.setImageResource(R.drawable.heixia_black);
                    tvXinyuan.setTextColor(getResources().getColor(R.color.colorBlack));
                    ivHeixiazi.setAlpha(1 - scale * 2);
                    tvXinyuan.setAlpha(1 - scale * 2);
                } else {
//                    tvHeixiazi.setTextColor(getResources().getColor(R.color.colorWhite));
                    ivHeixiazi.setImageResource(R.drawable.heixia_white);
                    tvXinyuan.setTextColor(getResources().getColor(R.color.colorWhite));
                    ivHeixiazi.setAlpha(2 * scale - 1);
                    tvXinyuan.setAlpha(2 * scale - 1);
                }
            }

            @Override
            public void onStateChanged(AppBarLayout appBarLayout, State state) {
                //判断appbar状态，防止与SwipeRefreshLayout下拉事件冲突
                //这个判断有问题，它至于完全展开和完全折叠状态会触发，其他中间状态不触发
                //发现还有一个状态-IDLE，从EXPANDED、COLLAPSED滑动到中间某个位置，第一次会触发
                if (state == State.EXPANDED) {//展开状态
                    LogUtils.logd("appBar-EXPANDED");
                    mSwipeRefreshLayout.setEnabled(true);
                } else if (state == State.COLLAPSED) {//折叠状态
                    LogUtils.logd("appBar-COLLAPSED");
                    mSwipeRefreshLayout.setEnabled(false);
                } else if (state == State.IDLE) {//中间状态
                    LogUtils.logd("appBar-IDLE");
                    mSwipeRefreshLayout.setEnabled(false);
                }
            }
        });
    }

    @Override
    protected CommonPresenter createPresenter() {
        return new CommonPresenter(getActivity(), this);
    }

    @Override
    public void getDataSuccess(AccountIndexModel model) {
        mSwipeRefreshLayout.setRefreshing(false);
        fieldViewAmountListBeanList = model.getBody().getFieldViewAmountList();
        fieldViewAmountListBeanListCopy = model.getBody().getFieldViewAmountList();
        fieldViewdelListBeanList = model.getBody().getFieldViewdelList();
        reProListBeanList = model.getBody().getReProList();
        fieldnListCopy = new ArrayList<>();
        for (int i = 0; i < fieldViewAmountListBeanList.size(); i++) {
            fieldnListCopy.add(fieldViewAmountListBeanList.get(i).getAmount());
        }


        if (reProListBeanList != null && reProListBeanList.size() > 0) {
            llRecommend.setVisibility(View.VISIBLE);
            rvRecommend.setVisibility(View.VISIBLE);
            mAdapterRecommend.setNewData(reProListBeanList);
        } else {
            llRecommend.setVisibility(View.GONE);
            rvRecommend.setVisibility(View.GONE);
        }

        mAdapter.setNewData(fieldViewAmountListBeanList);
        mAdapter2.setNewData(fieldViewdelListBeanList);

        jzc = model.getBody().getUserDayAssetJzc();//净资产
        zzc = model.getBody().getUserDayAssetZzc();//总资产
        zqk = model.getBody().getUserDayAssetZqk();//总负债

        if (fieldViewAmountListBeanList == null || fieldViewAmountListBeanList.size() < 1) {  //用户还未记账或全部删除
            ivVisible.setVisibility(View.GONE);

            if (spUtils.getIsAddAssets()) {
                llZichanEmpty.setVisibility(View.VISIBLE);
                rvZichan2.setVisibility(View.GONE);
            } else {
                llZichanEmpty.setVisibility(View.GONE);
                rvZichan2.setVisibility(View.VISIBLE);
            }
            tvJzc.setTextSize(22);
            tvJzc.setTextColor(getResources().getColor(R.color.E2E6EF));
            mAdapter2.setNh7r(model.getBody().getNh7r());
            tvJzc.setText("财富人生从记账开始");

            tvZzc.setText("0.00");
            tvZfz.setText("0.00");

        } else {
            spUtils.setIsAddAssets(true);//标记用户已添加过资产
            llZichanEmpty.setVisibility(View.GONE);
            ivVisible.setVisibility(View.VISIBLE);
            tvJzc.setTextSize(30);
            tvJzc.setTextColor(getResources().getColor(R.color.colorGold));

            try {
                if (!isVisible) {
                    tvJzc.setText("****");
                    tvZzc.setText("****");
                    tvZfz.setText("****");

                    for (int i = 0; i < fieldViewAmountListBeanList.size(); i++) {
                        mAdapter.getItem(i).setAmount("****");
                    }
                    mAdapter.notifyDataSetChanged();
                } else {
                    tvJzc.setText(SppaConstant.addCommaContainsPoint(jzc));
                    tvZzc.setText(SppaConstant.addCommaContainsPoint(zzc));
                    tvZfz.setText(SppaConstant.addCommaContainsPoint(zqk));

                    for (int i = 0; i < fieldViewAmountListBeanList.size(); i++) {
                        mAdapter.getItem(i).setAmount((SppaConstant.addCommaContainsPoint(fieldnListCopy.get(i))));
                    }
                    mAdapter.notifyDataSetChanged();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void getDataFail(String msg) {
        mSwipeRefreshLayout.setRefreshing(false);
        toastShow("网络链接异常，请重试~");
    }


    @OnClick({R.id.tvAddAssets, R.id.ivVisible, R.id.tvXinyuan, R.id.ivHeixiazi, R.id.llZcfx})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.llZcfx:
//                String url = "http://static.haitoujia.com/html/haitoujiawap/htjhouse/map.html?lat=101.669820&long=3.175702";
//                Intent in1 = new Intent(getActivity(), AboutUsActivity.class);
//                in1.putExtra("url", url);
//                startActivity(in1);
//                getActivity().overridePendingTransition(R.anim.left_in, 0);

//                EventBus.getDefault().post("loginout");

                zcfx();


//                if (!spUtils.getIsLogin()) {
//                    ActivityUtils.startActivityRightInWithFrom(getActivity(), LoginActivity.class, "forgetGes");
//                } else {
//                    zcfx();
//                }
                break;
            case R.id.ivVisible:
                try {
                    if (isVisible) {
                        tvJzc.setText("****");
                        tvZzc.setText("****");
                        tvZfz.setText("****");
                        isVisible = false;
                        ivVisible.setImageResource(R.drawable.invisible);

                        for (int i = 0; i < fieldViewAmountListBeanList.size(); i++) {
                            mAdapter.getItem(i).setAmount("****");
                        }
                        mAdapter.notifyDataSetChanged();
                    } else {
                        tvJzc.setText(SppaConstant.addCommaContainsPoint(jzc));
                        tvZzc.setText(SppaConstant.addCommaContainsPoint(zzc));
                        tvZfz.setText(SppaConstant.addCommaContainsPoint(zqk));
                        isVisible = true;
                        ivVisible.setImageResource(R.drawable.visible);

                        for (int i = 0; i < fieldViewAmountListBeanList.size(); i++) {
                            LogUtils.logd(i + ":" + fieldnListCopy.get(i));
                            LogUtils.logd(i + ":" + fieldViewAmountListBeanListCopy.get(i).getAmount());
                            mAdapter.getItem(i).setAmount((SppaConstant.addCommaContainsPoint(fieldnListCopy.get(i))));
                        }
                        mAdapter.notifyDataSetChanged();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            case R.id.tvAddAssets:
                StatService.onEvent(mActivity, "首页点击添加资产按钮", "[首页添加资产]", 1);
                if (spUtils.getIsLogin()) {
                    ActivityUtils.startActivityRightIn(getActivity(), AddAssetsActivity.class);
                } else {
                    ActivityUtils.startActivityRightInWithFrom(getActivity(), LoginActivity.class, "forgetGes");
                }

                break;
            case R.id.tvXinyuan:

//                String ss = "en://share?title=title1&intro=intro2&image=image3";
                String ss = "en://share?title=%E6%BE%B3%E5%A4%A7%E5%88%A9%E4%BA%9A%E5%A2%A8%E5%B0%94%E6%9C%AC-The Peak%E7%B2%BE%E5%93%81%E5%85%AC%E5%AF%93 &intro=The Peak%E4%BD%8D%E4%BA%8E%E4%B8%96%E7%95%8C%E6%9C%80%E5%AE%9C%E5%B1%85%E5%9F%8E%E5%B8%82%E4%B9%8B%E4%B8%80%E7%9A%84%E5%9F%8E%E5%B8%82%E2%80%94%E2%80%94%E5%A2%A8%E5%B0%94%E6%9C%AC%EF%BC%8C%E4%BA%AB%E6%9C%89%E6%95%B4%E4%B8%AA%E5%9F%8E%E5%B8%82%E7%9A%84%E5%A3%AE%E8%A7%82%E6%99%AF%E8%89%B2%EF%BC%8C%E4%BB%8E%E8%BF%9C%E5%A4%84%E7%9A%84Vista%E5%88%B0%E8%BF%91%E5%A4%84%E7%9A%84Carlton Gardens%E6%97%A0%E7%96%91%E9%83%BD%E6%98%AF%E7%BE%8E%E6%99%AF%E3%80%82The Peak%E7%9A%84%E7%89%B9%E8%89%B2%E5%B0%B1%E6%98%AF%E5%BB%BA%E7%AB%8B%E4%B8%80%E4%B8%AA%E5%A4%9A%E6%A0%B7%E5%8C%96%E3%80%81%E5%A4%9A%E5%B1%82%E6%AC%A1%E6%96%87%E5%8C%96%E4%B8%BA%E5%9F%BA%E7%A1%80%EF%BC%8C%E6%8B%A5%E6%9C%89%E8%87%AA%E5%B7%B1%E7%8B%AC%E7%89%B9%E7%89%B9%E8%89%B2%E7%9A%84%E9%A1%B9%E7%9B%AE%E3%80%82%E7%94%B1%E4%BA%8E%E9%A1%B9%E7%9B%AE%E4%BD%8D%E4%BA%8E%E5%A2%A8%E5%B0%94%E6%9C%AC%E7%9A%84%E5%BF%83%E8%84%8F%E5%9C%B0%E5%B8%A6%EF%BC%8C%E8%B4%AD%E7%89%A9%E3%80%81%E9%A4%90%E9%A5%AE%E3%80%81%E5%A8%B1%E4%B9%90%E4%B8%80%E5%88%87%E9%83%BD%E8%BF%91%E5%9C%A8%E5%92%AB%E5%B0%BA%EF%BC%8C%E5%9C%A8%E8%BF%99%E9%87%8C%E4%BD%A0%E5%8F%AF%E4%BB%A5%E6%84%9F%E5%8F%97%E5%88%B0%E5%A2%A8%E5%B0%94%E6%9C%AC%E6%9C%80%E9%A1%B6%E7%BA%A7%E7%9A%84%E7%94%9F%E6%B4%BB%E3%80%82%E9%A1%B9%E7%9B%AE%E8%B7%9D%E5%A2%A8%E5%B0%94%E6%9C%AC%E5%A4%A7%E5%AD%A6%E5%92%8C%E5%A2%A8%E5%B0%94%E6%9C%AC%E7%9A%87%E5%AE%B6%E7%90%86%E5%B7%A5%E5%A4%A7%E5%AD%A6%E9%9D%9E%E5%B8%B8%E8%BF%91%E3%80%82%E5%9C%A8The Peak%E9%99%84%E8%BF%91%E7%9A%84Collins%E8%A1%97%E6%8B%A5%E6%9C%89%E6%9D%A5%E8%87%AA%E5%85%A8%E7%90%83%E5%90%84%E7%A7%8D%E9%AB%98%E7%AB%AF%E5%93%81%E7%89%8C%E3%80%82%E4%BD%A0%E4%B9%9F%E5%8F%AF%E4%BB%A5%E6%89%BE%E5%88%B0%E5%90%84%E7%A7%8D%E5%90%84%E6%A0%B7%E7%9A%84%E5%95%86%E5%9C%BA%E5%92%8C%E8%B4%AD%E7%89%A9%E4%B8%AD%E5%BF%83%EF%BC%8CMelbourne Central%E3%80%81QV, %E4%BB%A5%E5%8F%8A%E6%A0%87%E5%BF%97%E6%80%A7%E7%9A%84%E7%99%BE%E8%B4%A7Myer%E5%92%8CDavid Jones.&image=/upload/prdcenterproduct/OP201801265316/e71ec374d4374ba590914b58ed2b3822.jpg\n";
                String s1 = ss.substring(ss.indexOf("?") + 1);
                LogUtils.loge("s1:" + s1);//title=title1&intro=intro2&image=image3"
                String[] s2 = s1.split("&");
                //s2[0]  title=title1
                //s2[1]  intro=intro2
                //s2[2]  image=image3
                LogUtils.loge("s2[0]:" + s2[0] + "\ns2[1]:" + s2[1] + "\ns2[2]:" + s2[2]);
                String title = null, intro = null, image = null;
                for (int i = 0; i < s2.length; i++) {
                    title = s2[0].substring(s2[0].indexOf("=") + 1);
                    intro = s2[1].substring(s2[1].indexOf("=") + 1);
                    image = s2[2].substring(s2[2].indexOf("=") + 1);
                }

                LogUtils.loge("title:" + title + "\nintro:" + intro + "\nimage:" + image);


//                ActivityUtils.startActivityRightIn(getActivity(), WebViewActivity.class);
//                String mUrl = "http://192.168.1.171:13006/trade//upload/upload.htm?encMsg=a/gixlx9lUpvMeyUAnG6u7AZ6GPGCp4OwOV+vWBBmEQYw5+ru5dKZMydZJ72WqBOQ02LVShfrgh3uWv8/TNSDx9GLFIEORNrGi9umPlZbnrr/poqUvQGssWHtvrvWdpwHjdLoUZbJBkCjAgMv8EEXury4d6Ky0qs01Gan2znI41yLyrmrqry0B2nbFLM6DWPJQH/iKs/NCVVh6FMY+uJt6Ua+VGEy9X9\n";
//
// String mUrl = "http://www.360caifu.test/ybz/api/fq/av/detail.htm?id=4";
//                Intent in = new Intent(getActivity(), X5WebActivity.class);
//                in.putExtra("url", mUrl);
//                startActivity(in);
//                getActivity().overridePendingTransition(R.anim.left_in, 0);

//                requestUrl();

                break;
            case R.id.ivHeixiazi:
                if (spUtils.getIsLogin()) {
                    StatService.onEvent(mActivity, "首页点击黑匣子", "[首页点击黑匣子]");
//                    ActivityUtils.startActivityRightIn(getActivity(), BlackboxGuideActivity.class);
                    gethjz();//查询黑匣子是否开启
                } else {
//                    ActivityUtils.startActivityRightInWithFrom(getActivity(), LoginActivity.class, "forgetGes");

                    ActivityUtils.startActivityRightIn(getActivity(), BlackboxGuideActivity.class);
                }
                break;
        }
    }

    public String splitData(String str, String strStart, String strEnd) {
        String tempStr;
        tempStr = str.substring(str.indexOf(strStart) + 1, str.lastIndexOf(strEnd));
        return tempStr;
    }

    //财富宝首页
    void requestUrl() {
        RequestBody<AddOverseasRequestModel> requestBody = new RequestBody<>();
        RequestBody.HeaderBean headerBean = new RequestBody.HeaderBean(Utils.getIPAddress(mActivity), spUtils.getSecretKeyId());
        AddOverseasRequestModel requestModel = new AddOverseasRequestModel();//10051547
        requestModel.setUserNo(spUtils.getUserId());//spUtils.getUserId()
//        requestModel.setSource("1000");

        requestBody.setBody(requestModel);
        requestBody.setHeader(headerBean);

        Gson gson2 = new Gson();
        String json2 = gson2.toJson(requestBody);
        LogUtils.loge("请求参数：" + json2);
        try {
            encMsg = DESedeUtil.getRequestAfter3DES(ApiStores.key, ApiStores.iv, json2);
            signMsg = DESedeUtil.getRequestAfterSign(encMsg);
        } catch (Exception e) {
            e.printStackTrace();
        }

        String url = ApiStores.CaifubaoIndex + "?encMsg=" + encMsg;
//        String url = "http://192.168.1.18:8020/F2E/trade/static/html/trade/financing/uploadcard.html?__hbt=1520502609940";
        LogUtils.loge("请求url：" + url);
        // requestUrl(id,stockCode,stockName,positionNumber,brokerName,memo);from
        Intent in = new Intent(getActivity(), X5WebActivity.class);//CurrentFinancialWebActivity
        in.putExtra("url", url);
        startActivity(in);
        getActivity().overridePendingTransition(R.anim.left_in, 0);
    }

    //资产分析首页
    void zcfx() {
        RequestBody<AddOverseasRequestModel> requestBody = new RequestBody<>();
        RequestBody.HeaderBean headerBean = new RequestBody.HeaderBean(Utils.getIPAddress(mActivity), spUtils.getSecretKeyId());
        AddOverseasRequestModel requestModel = new AddOverseasRequestModel();
        requestModel.setUserNo(spUtils.getUserId());

        requestBody.setBody(requestModel);
        requestBody.setHeader(headerBean);

        Gson gson2 = new Gson();
        String json2 = gson2.toJson(requestBody);
        LogUtils.loge("请求参数：" + json2);
        try {
            encMsg = DESedeUtil.getRequestAfter3DES(spUtils.getSecretKey(), spUtils.getSecretIv(), json2);
            signMsg = DESedeUtil.getRequestAfterSign(encMsg);
        } catch (Exception e) {
            e.printStackTrace();
        }

        String url = ApiStores.ZCFX + "?encMsg=" + encMsg + "&signMsg=" + signMsg + "&msgType=2" + "&secretKeyId=" + spUtils.getSecretKeyId();
        LogUtils.loge("请求url：" + url);
        Intent in = new Intent(getActivity(), X5TitleWebActivity.class);//WebActivity
        in.putExtra("url", url);
        startActivity(in);
        getActivity().overridePendingTransition(R.anim.left_in, 0);
    }

    //删除模块回调
    @Override
    public void onSuccess(SercetKeyOverdueModel model) {
        toastShow("删除成功", R.drawable.gou_toast);
        try {
            fieldViewAmountListBeanList.remove(mPosition);
            mAdapter.notifyItemRemoved(mPosition);//推荐用这个
            getRequest("");//删除完成，刷新界面
            if (mAdapter.getItemCount() == 0) {
                llZichanEmpty.setVisibility(View.VISIBLE);
                rvZichan2.setVisibility(View.GONE);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onFail(String model) {
        toastShow(model);
    }


    @Override
    public void ThirdSuccess(SecretKeyResponseModel model) {
        count++;//秘钥获取成功后，count++；
        getRequest("");
//        getStart();
    }

    @Override
    public void ThirdFail(String msg) {
        toastShow(msg);
    }

    /**
     * 查询黑匣子
     */
    public void gethjz() {
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
                        LogUtils.loge("MsgType:" + model.getMsgType());
                        String msgType = model.getMsgType();
                        if (msgType != null && msgType.equals("2")) {//加密
                            String encMsg = model.getEncMsg();
                            LogUtils.loge(model.getEncMsg());
                            try {
                                String platext = DESedeUtil.decrypt(encMsg, spUtils.getSecretKey(), spUtils.getSecretIv());
                                LogUtils.loge("解密后：------查询黑匣子----------" + platext);
                                GetHjzModel getHjzModel = GsonTools.getObject(platext, GetHjzModel.class);
                                if (getHjzModel.getHeader().getCode().equals("0000")) {
                                    if (getHjzModel.getBody().getInfoDto() == null) {
                                        spUtils.setOpenblackBox(false);
                                        ActivityUtils.startActivityRightIn(getActivity(), BlackboxGuideActivity.class);//BlackboxUnsettingActivity
                                    } else {
                                        spUtils.setOpenblackBox(true);
                                        ActivityUtils.startActivityRightIn(getActivity(), MyBlackboxDetailActivity.class);//BlackboxDetailActivity
                                    }
                                } else {
                                    toastShow(getHjzModel.getHeader().getDesc());
                                }
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        } else {
                            LogUtils.loge("首页：未加密:" + model.getMsgType());
                        }
                    }

                });
    }

    /**
     * 启动接口
     */
    private void getStart() {
        LogUtils.loge("-------启动接口------");
        RequestBody<AddOverseasRequestModel> requestBody = new RequestBody<>();
        RequestBody.HeaderBean headerBean = new RequestBody.HeaderBean(Utils.getIPAddress(mActivity), spUtils.getSecretKeyId());
        AddOverseasRequestModel addOverseasRequestModel = new AddOverseasRequestModel();

        addOverseasRequestModel.setUserNo(spUtils.getUserId());

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
        mvpPresenter.getStart(encMsg, signMsg, "2", spUtils.getSecretKeyId());
    }

    /**
     * 启动接口（版本更新）
     *
     * @param model
     */
    @Override
    public void getPiccodeSuccess(StartModel model) {
        if (model.getBody() != null) {
            String mustUpdate = model.getBody().getMustUpdate();
            updateUrl = model.getBody().getDownloadUrl();
            tvDesc.setText(model.getBody().getDesc());

            /**
             * 版本号比较
             */
            String versionName = Utils.getVersionName();
            LogUtils.loge("--------" + versionName);

            String newestVersion = model.getBody().getNewestVersion();
            LogUtils.loge("--------" + newestVersion);

            int result = versionName.compareTo(newestVersion);
            LogUtils.loge("--------" + result);


            if (result < 0) {
                if (mustUpdate != null) {
                    if (mustUpdate.equals("0")) {//不强制
                        materialDialog1.setNegativeButton("s", new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                materialDialog1.dismiss();
                            }
                        });
                        materialDialog1.show();
                    } else {
                        materialDialog1.show();
                    }
                }
            }
        }
    }

    @Override
    public void getPiccodeFail(String msg) {
        toastShow(msg);
    }
}
