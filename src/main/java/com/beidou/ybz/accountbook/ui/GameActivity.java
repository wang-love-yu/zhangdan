package com.beidou.ybz.accountbook.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.baidu.mobstat.StatService;
import com.beidou.ybz.accountbook.R;
import com.beidou.ybz.accountbook.adapter.AdapterGame;
import com.beidou.ybz.accountbook.adapter.AdapterPrepaid;
import com.beidou.ybz.accountbook.mvp.entity.AddOverseasRequestModel;
import com.beidou.ybz.accountbook.mvp.entity.GameListModel;
import com.beidou.ybz.accountbook.mvp.entity.PrepaidListModel;
import com.beidou.ybz.accountbook.mvp.entity.RequestBody;
import com.beidou.ybz.accountbook.mvp.entity.SercetKeyOverdueModel;
import com.beidou.ybz.accountbook.mvp.entity.UserNoModel;
import com.beidou.ybz.accountbook.mvp.other.MvpActivity;
import com.beidou.ybz.accountbook.mvp.presenter.CommonPresenter;
import com.beidou.ybz.accountbook.mvp.view.CommonView;
import com.beidou.ybz.accountbook.mvp.view.OtherView;
import com.beidou.ybz.accountbook.util.ActivityUtils;
import com.beidou.ybz.accountbook.util.AlertDialogUtils;
import com.beidou.ybz.accountbook.util.AppBarStateChangeListener;
import com.beidou.ybz.accountbook.util.DESedeUtil;
import com.beidou.ybz.accountbook.util.DialogClickListener;
import com.beidou.ybz.accountbook.util.LogUtils;
import com.beidou.ybz.accountbook.util.Utils;
import com.beidou.ybz.accountbook.widget.EmptyView;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.google.gson.Gson;

import java.math.BigDecimal;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Author: xu.yang on 2017/12/30
 * QQ:754444814
 * E-mail:754444814@qq.com
 * module: 游戏界面
 */
public class GameActivity extends MvpActivity<CommonPresenter> implements CommonView<GameListModel>, OtherView<SercetKeyOverdueModel> {
    @Bind(R.id.bob_empty_view)
    EmptyView mEmptyView;
    @Bind(R.id.appbar)
    AppBarLayout appbar;
    @Bind(R.id.tv_title)
    TextView tvTitle;
    @Bind(R.id.tvAddAssets)
    TextView tvAddAssets;
    @Bind(R.id.toolbar)
    Toolbar toolbar;
    @Bind(R.id.ib_back)
    ImageButton ibBack;
    @Bind(R.id.swipeRefresh)
    SwipeRefreshLayout mSwipeRefreshLayout;
    @Bind(R.id.rv)
    RecyclerView rv;
    @Bind(R.id.tvZczj)
    TextView tvZczj;
    @Bind(R.id.tvMingcheng)
    TextView tvMingcheng;
    @Bind(R.id.tvName)
    TextView tvName;
    @Bind(R.id.tvValue)
    TextView tvValue;
    private String encMsg, signMsg, from;
    private List<GameListModel.BodyBean.ProListBean> proListBeanList;
    private AdapterGame mAdapter;
    AlertDialogUtils alertDialogUtils;
    private int mPosition;//删除的position
    private int count;
    private String zzzj;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_currentfinancial);
        ButterKnife.bind(this);

        tvAddAssets.setText("+  新建游戏");
        tvTitle.setText("游戏");
        tvMingcheng.setText("价值总计(元)");
        tvTitle.setTextColor(getResources().getColor(R.color.txt_color));
        alertDialogUtils = new AlertDialogUtils(mActivity);
        mEmptyView.bindView(rv); // 设置bindView
        mEmptyView.buttonClick(this, "request"); // 当button点击时调用哪个方法
        count = 0;
        rv.setLayoutManager(new LinearLayoutManager(this));
        mAdapter = new AdapterGame(R.layout.overseaslist_item, null);
        rv.setAdapter(mAdapter);

        tvName.setText("游戏名称");
        tvValue.setText("价值");

        handleIntent(getIntent());

        mAdapter.setOnItemLongClickListener(new BaseQuickAdapter.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(BaseQuickAdapter adapter, View view, int position) {
                alertDialogUtils.setMessage("确定要删除" + proListBeanList.get(position).getGameName() + "？");
                alertDialogUtils.setPosition(position);
                alertDialogUtils.show();
                return true;
                //返回false时点击事件和长按事件会有冲突，设为true,事件拦截，冲突解除
            }
        });

        mAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                Intent in = new Intent(mActivity, GameDetailActivity.class);
                in.putExtra("id", proListBeanList.get(position).getId());
                in.putExtra("name", proListBeanList.get(position).getGameName());
                startActivity(in);
                overridePendingTransition(R.anim.left_in, 0);
            }
        });

        alertDialogUtils.setOnDialogClickListener(new DialogClickListener() {
            @Override
            public void clickYes(int which) {
                StatService.onEvent(mActivity, "点击游戏列表任一删除按钮", "删除游戏具体资产", 1);
                LogUtils.logd("which:" + which);
                mPosition = which;
                del(proListBeanList.get(which).getId());
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
                request();
            }
        });
        appbar.addOnOffsetChangedListener(new AppBarStateChangeListener() {
            @Override
            public void onOffset(AppBarLayout appBarLayout, int i) {
                int totalOffset = appBarLayout.getTotalScrollRange();
                float scale = (float) (totalOffset - i) / (float) totalOffset;//展开-1 收起-0

                /**
                 * 根据appbar收放改变字体颜色
                 */
                if (scale < 0.5) {
                    tvTitle.setTextColor(getResources().getColor(R.color.colorBlack));
                    tvTitle.setAlpha(1 - scale * 2);
                    ibBack.setImageResource(R.drawable.back_black);
                    ibBack.setAlpha(1 - scale * 2);
                } else {
                    tvTitle.setTextColor(getResources().getColor(R.color.colorWhite));
                    tvTitle.setAlpha(2 * scale - 1);
                    ibBack.setImageResource(R.drawable.back);
                    ibBack.setAlpha(2 * scale - 1);
                }
            }

            @Override
            public void onStateChanged(AppBarLayout appBarLayout, State state) {
                //判断appbar状态，防止与SwipeRefreshLayout下拉事件冲突
                if (state == State.EXPANDED) {//展开状态
                    mSwipeRefreshLayout.setEnabled(true);
                } else if (state == State.COLLAPSED) {//折叠状态
                    mSwipeRefreshLayout.setEnabled(false);
                } else if (state == State.IDLE) {//折叠状态
                    mSwipeRefreshLayout.setEnabled(false);
                }
            }
        });
    }


    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        handleIntent(intent);
    }

    private void handleIntent(Intent in) {
        if (in != null) {
            from = in.getStringExtra("from");
        }

//        if (from != null && from.equals("add")) {
//            isFromAdd = true;
//        } else {
//            isFromAdd = false;
//        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        request();
        StatService.onPageStart(mActivity, "游戏列表页面");
    }

    @Override
    protected void onPause() {
        super.onPause();
        StatService.onPageEnd(mActivity, "游戏列表页面");
    }

    void request() {
        RequestBody<UserNoModel> requestBody = new RequestBody<>();
        RequestBody.HeaderBean headerBean = new RequestBody.HeaderBean(Utils.getIPAddress(mActivity), spUtils.getSecretKeyId());
        UserNoModel requestModel = new UserNoModel();
        requestModel.setUserNo(spUtils.getUserId());

        requestBody.setBody(requestModel);
        requestBody.setHeader(headerBean);

        Gson gson2 = new Gson();
        String json2 = gson2.toJson(requestBody);
        LogUtils.logd("参数：" + json2);
        try {
            encMsg = DESedeUtil.getRequestAfter3DES(spUtils.getSecretKey(), spUtils.getSecretIv(), json2);
            signMsg = DESedeUtil.getRequestAfterSign(encMsg);
        } catch (Exception e) {
            e.printStackTrace();
        }
        mvpPresenter.getgamelist(encMsg, signMsg, "2", spUtils.getSecretKeyId());
        if (count == 0) {
            mEmptyView.loading();
        }
    }

    @Override
    protected CommonPresenter createPresenter() {
        return new CommonPresenter(this, this);
    }

    @OnClick({R.id.ib_back, R.id.tvAddAssets})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ib_back:
                ActivityUtils.startActivity(mActivity, MainActivity.class);
                ActivityUtils.finishActivity(mActivity);
                break;
            case R.id.tvAddAssets:
                StatService.onEvent(mActivity, "游戏列表页点击新增", "新增游戏", 1);
                Intent in = new Intent(mActivity, AddGame.class);
                in.putExtra("from", "list");
                startActivity(in);
                overridePendingTransition(R.anim.left_in, 0);
                break;
        }
    }

    @Override
    public void getDataSuccess(GameListModel model) {
        count++;
        try {
            mSwipeRefreshLayout.setRefreshing(false);
            proListBeanList = model.getBody().getProList();
            zzzj = model.getBody().getZsz();
            tvZczj.setText(Utils.addCommaContainsPoint(zzzj));
            if (proListBeanList != null && proListBeanList.size() > 0) {
                mEmptyView.success();
                mAdapter.setNewData(proListBeanList);
            } else {
                mEmptyView.setEmptyText("还没有游戏账号哦～");
                mEmptyView.empty();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void getDataFail(String msg) {
        toastShow(msg);
        mSwipeRefreshLayout.setRefreshing(false);
        mEmptyView.loadfail();
    }

    void del(String id) {
        RequestBody<AddOverseasRequestModel> requestBody = new RequestBody<>();
        RequestBody.HeaderBean headerBean = new RequestBody.HeaderBean(Utils.getIPAddress(mActivity), spUtils.getSecretKeyId());
        AddOverseasRequestModel requestModel = new AddOverseasRequestModel();
        requestModel.setUserNo(spUtils.getUserId());
        requestModel.setId(id);

        requestBody.setBody(requestModel);
        requestBody.setHeader(headerBean);

        Gson gson = new Gson();
        String json = gson.toJson(requestBody);
        LogUtils.loge("参数：" + json);
        try {
            encMsg = DESedeUtil.getRequestAfter3DES(spUtils.getSecretKey(), spUtils.getSecretIv(), json);
            signMsg = DESedeUtil.getRequestAfterSign(encMsg);
        } catch (Exception e) {
            e.printStackTrace();
        }
        mvpPresenter.delgame(encMsg, signMsg, "2", spUtils.getSecretKeyId());
    }

    @Override
    public void onSuccess(SercetKeyOverdueModel model) {
        toastShow("删除成功", R.drawable.gou_toast);
        try {
            zzzj = String.valueOf(new BigDecimal(Double.parseDouble(zzzj) - Double.parseDouble(proListBeanList.get(mPosition).getAmount())));
            tvZczj.setText(Utils.addCommaContainsPoint(zzzj));
            proListBeanList.remove(mPosition);
            mAdapter.notifyItemRemoved(mPosition);//推荐用这个
            if (mAdapter.getItemCount() == 0) {
                mEmptyView.setEmptyText("还没有游戏账号哦～");
                mEmptyView.empty();
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
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK
                && event.getAction() == KeyEvent.ACTION_DOWN) {
            ActivityUtils.startActivity(mActivity, MainActivity.class);
            ActivityUtils.finishActivity(mActivity);
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}
