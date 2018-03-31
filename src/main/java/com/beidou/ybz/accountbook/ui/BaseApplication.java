package com.beidou.ybz.accountbook.ui;

import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.Typeface;
import android.support.multidex.MultiDex;
import android.support.multidex.MultiDexApplication;
import android.support.v4.util.ArrayMap;
import android.util.DisplayMetrics;
import android.util.Log;

import com.baidu.mobstat.StatService;
import com.beidou.ybz.accountbook.R;
import com.beidou.ybz.accountbook.mvp.entity.RequestBody2;
import com.beidou.ybz.accountbook.mvp.entity.SecretKeyResponseModel;
import com.beidou.ybz.accountbook.mvp.entity.UnencryptedResponseModel;
import com.beidou.ybz.accountbook.retrofit.ApiStores;
import com.beidou.ybz.accountbook.retrofit.AppClient;
import com.beidou.ybz.accountbook.util.GsonTools;
import com.beidou.ybz.accountbook.util.LogUtils;
import com.beidou.ybz.accountbook.util.MyPushIntentService;
import com.beidou.ybz.accountbook.util.SharePreferenceUtil;
import com.google.gson.Gson;
import com.orhanobut.logger.Logger;
//import com.umeng.commonsdk.UMConfigure;
import com.tencent.smtt.sdk.QbSdk;
import com.umeng.message.IUmengRegisterCallback;
import com.umeng.message.PushAgent;

import java.util.ArrayList;
import java.util.Locale;
import java.util.Timer;
import java.util.TimerTask;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;


/**
 * 自定义应用入口
 *
 * @author Ht
 */
public class BaseApplication extends MultiDexApplication {
    private static BaseApplication mInstance;
    ArrayList<Activity> activityList = new ArrayList<Activity>();
    /**
     * 屏幕宽度
     */
    public static int screenWidth;
    /**
     * 屏幕高度
     */
    public static int screenHeight;
    /**
     * 屏幕密度
     */
    public static float screenDensity;
    private static ArrayMap<String, Integer> map;//资产分类背景
    private static ArrayMap<String, Integer> mapIcon;//首页资产icon
    public static Typeface typeFace;
    private Timer timer;
    public SharePreferenceUtil spUtils;
    @Override
    public void onCreate() {
        super.onCreate();
        //替换整个应用内字体
//        setTypeface();

        //检测内存泄露
        // http://www.liaohuqiu.net/cn/posts/leak-canary/
//        LeakCanary.install(this);

        //初始化imageloader
//        initImageLoader();

        QbSdk.PreInitCallback cb = new QbSdk.PreInitCallback() {

            @Override
            public void onViewInitFinished(boolean arg0) {
                // TODO Auto-generated method stub
                //x5內核初始化完成的回调，为true表示x5内核加载成功，否则表示x5内核加载失败，会自动切换到系统内核。
                Log.d("app", " onViewInitFinished is " + arg0);
            }

            @Override
            public void onCoreInitFinished() {
                // TODO Auto-generated method stub
            }
        };
        //x5内核初始化接口
        QbSdk.initX5Environment(getApplicationContext(),  cb);


        /**
         * 初始化common库
         * 参数1:上下文，不能为空
         * 参数2:设备类型，UMConfigure.DEVICE_TYPE_PHONE为手机、UMConfigure.DEVICE_TYPE_BOX为盒子，默认为手机
         * 参数3:Push推送业务的secret
         */
//        UMConfigure.init(this, UMConfigure.DEVICE_TYPE_PHONE, "a7bac039226637eee27d7abd057495a0");

        //初始化当前设备屏幕宽高
        initScreenSize();
        initArrayMap();
        initClassicon();
        spUtils =  new SharePreferenceUtil(getApplicationContext(), "xinliangbao");
        timer = new Timer();
        timer.schedule(new TimerTask(){
            public void run(){
                System.out.println("Time's up!");
                getSecretkey();
//                timer.cancel();
            }
        }, 60*1000,5*60*1000);

        umengPush();
        baiduAnalytics();
    }

    /**
     * 友盟推送
     */
    void umengPush(){
        PushAgent mPushAgent = PushAgent.getInstance(this);
        mPushAgent.setDebugMode(true);
        PushAgent.getInstance(this).onAppStart();
        //注册推送服务，每次调用register方法都会回调该接口
        mPushAgent.register(new IUmengRegisterCallback() {
            @Override
            public void onSuccess(String deviceToken) {
                //注册成功会返回device token
                LogUtils.loge("------deviceToken--------"+deviceToken);
                spUtils.setDeviceToken(deviceToken);
            }

            @Override
            public void onFailure(String s, String s1) {
            }
        });
        mPushAgent.setPushIntentServiceClass(MyPushIntentService.class);
    }

    void baiduAnalytics(){
//        StatService.autoTrace(getApplicationContext(),true, false);
        StatService.start(this);
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }

    void getSecretkey() {
       /* RequestBody2 requestBody = new RequestBody2();
        RequestBody2.HeaderBean headerBean = new RequestBody2.HeaderBean();
        requestBody.setHeader(headerBean);
        RequestBody2.BodyBean bodyBean = new RequestBody2.BodyBean();
        requestBody.setBody(bodyBean);
        Gson gson = new Gson();
        String json = gson.toJson(requestBody);
        Logger.e("aaaa:：" + json);
        Logger.json(json);
        mvpPresenter.getSecretkey(json);*/
        RequestBody2 requestBody = new RequestBody2();
        RequestBody2.HeaderBean headerBean = new RequestBody2.HeaderBean();
        requestBody.setHeader(headerBean);
        RequestBody2.BodyBean bodyBean = new RequestBody2.BodyBean();
        requestBody.setBody(bodyBean);
        Gson gson = new Gson();
        String json = gson.toJson(requestBody);
        Logger.e("aaaa:："+json);
        Logger.json(json);

        ApiStores apiStores = AppClient.retrofit(getApplicationContext()).create(ApiStores.class);//retrofit.create(ApiStores.class);
        Observable<UnencryptedResponseModel> observable = apiStores.getSecretkey("1",json);
        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<UnencryptedResponseModel>() {
                    @Override
                    public void onCompleted() {LogUtils.loge("onCompleted()");}
                    @Override
                    public void onError(Throwable e) {LogUtils.loge("onError()"+e.getMessage());}
                    @Override
                    public void onNext(UnencryptedResponseModel movieEntity) {
                        LogUtils.loge("onNext()"+movieEntity.getMsg());
                        SecretKeyResponseModel secretKeyResponseModel = GsonTools.getObject(movieEntity.getMsg(), SecretKeyResponseModel.class);
                        LogUtils.loge(secretKeyResponseModel.getHeader().getCode());
                        LogUtils.loge(secretKeyResponseModel.getBody().getSecretKeyId());

                        String secretKey = secretKeyResponseModel.getBody().getSecretKey();
                        String secretIv= secretKeyResponseModel.getBody().getSecretIv();
                        String secretKeyId = secretKeyResponseModel.getBody().getSecretKeyId();
                        LogUtils.loge("onNext()+secretKeyId::"+secretKeyId);
                        spUtils.setSecretKey(secretKey);
                        spUtils.setSecretIv(secretIv);
                        spUtils.setSecretKeyId(secretKeyId);

                    }
                });
    }

    /**
     * 添加资产类别背景
     */
    void initArrayMap(){
        LogUtils.logd("map初始化");
        map = new ArrayMap<>();
        map.put("0001", R.drawable.stock);//股票
        map.put("0002", R.drawable.fund);//基金
        map.put("0003", R.drawable.currentfinancial);//理财
        map.put("0004", R.drawable.bankcard);//银行卡***
        map.put("0005", R.drawable.prepaidcard);//预付卡
        map.put("0006", R.drawable.arrears);//欠款
        map.put("0007", R.drawable.credit);//贷款***
        map.put("0008", R.drawable.equity);//股权
        map.put("0009", R.drawable.fixedincome);//固定收益
        map.put("0010", R.drawable.privateoffering);//私募
        map.put("0011", R.drawable.insurance);//保险
        map.put("0012", R.drawable.overseas);//海外房产
        map.put("0013", R.drawable.reimbursement);//报销单
        map.put("0014", R.drawable.domin);//域名估值
        map.put("0015", R.drawable.internetaccount);//互联网账号估值
        map.put("0016", R.drawable.game);//游戏
        map.put("0017", R.drawable.loan);//借款
        map.put("9999", R.drawable.custom);//其它
    }

    /**
     * 首页类别icon
     */
    void initClassicon(){
        LogUtils.logd("mapIcon初始化");
        mapIcon = new ArrayMap<>();
        mapIcon.put("0001", R.drawable.stock_icon);//股票
        mapIcon.put("0002", R.drawable.fund_icon);//基金
        mapIcon.put("0003", R.drawable.currentfinancial_icon);//理财
        mapIcon.put("0004", R.drawable.bankcard_icon);//银行卡***
        mapIcon.put("0005", R.drawable.prepaidcard_icon);//预付卡
        mapIcon.put("0006", R.drawable.arrears_icon);//欠款
        mapIcon.put("0007", R.drawable.credit_icon);//贷款***
        mapIcon.put("0008", R.drawable.equity_icon);//股权
        mapIcon.put("0009", R.drawable.fixedincome_icon);//固定收益
        mapIcon.put("0010", R.drawable.privateoffering_icon);//私募
        mapIcon.put("0011", R.drawable.insurance_icon);//保险
        mapIcon.put("0012", R.drawable.overseas_icon);//海外房产
        mapIcon.put("0013", R.drawable.reimbursement_icon);//报销单
        mapIcon.put("0014", R.drawable.domin_icon);//域名估值
        mapIcon.put("0015", R.drawable.internetaccount_icon);//互联网账号估值
        mapIcon.put("0016", R.drawable.game_icon);//游戏
        mapIcon.put("0017", R.drawable.loan_icon);//借款
        mapIcon.put("9999", R.drawable.custom_icon);//其它
    }

    /**
     * 获取分类背景图片
     * @param key
     * @return
     */
    public static int getClassifiBackground(String key){
        LogUtils.logd("map == null:"+(map == null));
        if(map != null) {
            return map.get(key);
        }
        return R.drawable.stock;
    }

    /**
     * 首页获取分类icon
     * @param key
     * @return
     */
    public static int getClassifiIcon(String key){
//        LogUtils.logd("mapIcon == null:"+(mapIcon == null));
        if(mapIcon != null) {
            return mapIcon.get(key);
        }
        return R.drawable.stock_icon;
    }

    /**
     * 替换系统默认字体
     */
//    public void setTypeface(){
//        CalligraphyConfig.initDefault(new CalligraphyConfig.Builder()
//                .setDefaultFontPath("fonts/PingFang-Light.ttf")//fangzhenglantingxihei
//                .setFontAttrId(R.attr.fontPath)
//                .build()
//        );
//    }



    public static BaseApplication getInstance() {
        if (null == mInstance) {
            mInstance = new BaseApplication();
        }
        return mInstance;
    }

    // 添加Activity 到容器中
    public void addActivity(Activity activity) {
        activityList.add(activity);
    }

    // 遍历 ?  Activity 并finish

    public void exit() {

        for (Activity activity : activityList) {
            activity.finish();
        }

        mInstance = null;
        System.exit(0);
        //杀死该应用进程
        android.os.Process.killProcess(android.os.Process.myPid());

    }

    /**
     * 获取版本号
     *
     * @return 当前应用的版本号
     */
    public static String getVersion() {
        try {
            PackageManager manager = mInstance.getPackageManager();
            PackageInfo info = manager.getPackageInfo(mInstance.getPackageName(), 0);
            String version = info.versionName;
            return version;
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    /**
     * 获取当前系统语言
     *
     * @return 当前系统语言
     */
    public static String getLanguage() {
        Locale locale = mInstance.getResources().getConfiguration().locale;
        String language = locale.getDefault().toString();
        return language;
    }

    /**
     * 初始化当前设备屏幕宽高
     */
    private void initScreenSize() {
        DisplayMetrics curMetrics = getApplicationContext().getResources().getDisplayMetrics();
        screenWidth = curMetrics.widthPixels;
        screenHeight = curMetrics.heightPixels;
        screenDensity = curMetrics.density;
    }


}

