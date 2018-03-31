package com.beidou.ybz.accountbook.util;

import android.app.Activity;
import android.app.DownloadManager;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.os.Environment;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.v4.app.AppOpsManagerCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.util.ArrayMap;
import android.text.Editable;
import android.text.InputFilter;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Base64;
import android.util.TypedValue;
import android.view.View;
import android.webkit.WebView;
import android.widget.EditText;
import android.widget.ImageView;

import com.beidou.ybz.accountbook.BuildConfig;
import com.orhanobut.logger.Logger;
import com.tencent.mm.sdk.openapi.IWXAPI;
import com.tencent.mm.sdk.openapi.WXAPIFactory;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.net.HttpURLConnection;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.MalformedURLException;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.URL;
import java.net.URLConnection;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Enumeration;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by lenovo on 2016/10/10.
 */
public class Utils {

    private final static String DEFAULT_APK_PATH = "/Biscuit/";
    private final static String DEFAULT_IMAGE_PATH = "image/";
    private final static InputFilter[] inputFilters12 = new InputFilter[]{new InputFilter.LengthFilter(12)};
    private final static InputFilter[] inputFilters9 = new InputFilter[]{new InputFilter.LengthFilter(9)};

    public static String getImageDir() {
        String root = getAppPath() + DEFAULT_IMAGE_PATH;
        File file = new File(root);
        if (!file.exists()) file.mkdir();
        return root;
    }


    /**
     * 将每三个数字加上逗号处理（通常使用金额方面的编辑）
     *
     * @param str 无逗号的数字 <a
     *            href="http://home.51cto.com/index.php?s=/space/34010"
     *            target="_blank">@return</a> 加上逗号的数字
     */
    public static String addCommaContainsPoint(String str) throws Exception {
        String unit = "";
        String prefix = "";
        if (str != null && str.contains(",")) {
            return str;
        } else if (str != null && str.equals("****")) {
            return "****";
        } else if (str == null || str.equals("") || !isNumeric(str)) {
            return "0.00";
        }

        if (str != null && str.startsWith("-")) {
            prefix = "-";
            str = str.substring(1);
        }

        if (Double.parseDouble(str) >= 1000000 && Double.parseDouble(str) < 100000000) {// 百万<金额<亿 ,以万为单位
            double result1 = Double.parseDouble(str) / 10000f;
            BigDecimal b = new BigDecimal(result1);
            double f1 = b.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
            str = String.valueOf(f1);
            unit = "万";
        } else if (Double.parseDouble(str) >= 100000000) {
            double result1 = Double.parseDouble(str) / 100000000f;
            BigDecimal b = new BigDecimal(result1);
            double f1 = b.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();//4舍5入
            str = String.valueOf(f1);
            unit = "亿";
        } else {
            double result1 = Double.parseDouble(str);
            BigDecimal b = new BigDecimal(result1);
            double f1 = b.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();//4舍5入
            str = String.valueOf(f1);
            unit = "";
        }

        if (str.contains(".")) {//1,201,560
            String s1 = str.split("\\.")[0];//120
            String s2 = str.split("\\.")[1];//1560

            // 将传进数字反转
            String reverseStr = new StringBuilder(s1).reverse().toString();
            String strTemp = "";
            for (int i = 0; i < reverseStr.length(); i++) {
                if (i * 3 + 3 > reverseStr.length()) {
                    strTemp += reverseStr.substring(i * 3,
                            reverseStr.length());
                    break;
                }
                strTemp += reverseStr.substring(i * 3, i * 3 + 3) + ",";
            }
            // 将[789,456,] 中最后一个[,]去除
            if (strTemp.endsWith(",")) {
                strTemp = strTemp.substring(0, strTemp.length() - 1);
            }
            // 将数字重新反转
            String resultStr = new StringBuilder(strTemp).reverse()
                    .toString();
            if (s2.length() >= 2) {
                return prefix + resultStr + "." + s2.substring(0, 2) + unit;
            } else {
                return prefix + resultStr + "." + s2 + "0" + unit;
            }
        } else {

            String st = (new DecimalFormat("0").format(Double.parseDouble(str)));
            if (st != null && st.length() < 4) {
                return prefix + new DecimalFormat("0.00").format(Double.parseDouble(st)) + unit;
            } else {
                // 将传进数字反转
                String reverseStr = new StringBuilder(st).reverse().toString();
                System.out.println("reverseStr==>" + reverseStr);
                String strTemp = "";
                for (int i = 0; i < reverseStr.length(); i++) {
                    if (i * 3 + 3 > reverseStr.length()) {
                        strTemp += reverseStr.substring(i * 3,
                                reverseStr.length());
                        break;
                    }
                    strTemp += reverseStr.substring(i * 3, i * 3 + 3) + ",";
                }
                // 将[789,456,] 中最后一个[,]去除
                if (strTemp.endsWith(",")) {
                    strTemp = strTemp.substring(0, strTemp.length() - 1);
                }
                // 将数字重新反转
                String resultStr = new StringBuilder(strTemp).reverse()
                        .toString();
                return prefix + resultStr + ".00" + unit;
            }
        }
    }

    /**
     * get app root path
     *
     * @return
     */
    public static String getAppPath() {
        String path = null;
        String state = Environment.getExternalStorageState();
        if (state.equals(Environment.MEDIA_MOUNTED)) {
            path = Environment.getExternalStorageDirectory() + DEFAULT_APK_PATH;
        }
        File file = new File(path);
        if (!file.exists()) file.mkdir();
        return path;
    }

    /**
     * 将每三个数字加上逗号处理（通常使用金额方面的编辑）
     *
     * @param str 无逗号的数字 <a
     *            href="http://home.51cto.com/index.php?s=/space/34010"
     *            target="_blank">@return</a> 加上逗号的数字
     */
    public static String numberFormat(String str) throws Exception {
        if (str != null && str.contains(",")) {
            return str;
        } else if (str == null || str.equals("") || !isNumeric(str)) {
            return "0.00";
        } else {
            String st = (new DecimalFormat("0").format(Double.parseDouble(str)));
            // 将传进数字反转
            String reverseStr = new StringBuilder(st).reverse().toString();
            System.out.println("reverseStr==>" + reverseStr);
            String strTemp = "";
            for (int i = 0; i < reverseStr.length(); i++) {
                if (i * 3 + 3 > reverseStr.length()) {
                    strTemp += reverseStr.substring(i * 3, reverseStr.length());
                    break;
                }
                strTemp += reverseStr.substring(i * 3, i * 3 + 3) + ",";
            }
            // 将[789,456,] 中最后一个[,]去除
            if (strTemp.endsWith(",")) {
                strTemp = strTemp.substring(0, strTemp.length() - 1);
            }
            // 将数字重新反转
            String resultStr = new StringBuilder(strTemp).reverse().toString();
            return resultStr;
        }
    }

//    /**
//     * 将每三个数字加上逗号处理（通常使用金额方面的编辑）
//     *
//     * @param str
//     *            无逗号的数字 <a
//     *            href="http://home.51cto.com/index.php?s=/space/34010"
//     *            target="_blank">@return</a> 加上逗号的数字
//     */
//    public static String addCommaContainsPoint(String str) throws Exception{//0.029166666666666664
//        if(str != null && str.contains(",")){
//            return str;
//        }else if (str == null || str.equals("") || !isNumeric(str) ) {
//            return "0.00";
//        } else if(str.contains(".")){
//            String s1 = str.split("\\.")[0];
//            String s2 = str.split("\\.")[1];
//
//            // 将传进数字反转
//            String reverseStr = new StringBuilder(s1).reverse().toString();
//            String strTemp = "";
//            for (int i = 0; i < reverseStr.length(); i++) {
//                if (i * 3 + 3 > reverseStr.length()) {
//                    strTemp += reverseStr.substring(i * 3,
//                            reverseStr.length());
//                    break;
//                }
//                strTemp += reverseStr.substring(i * 3, i * 3 + 3) + ",";
//            }
//            // 将[789,456,] 中最后一个[,]去除
//            if (strTemp.endsWith(",")) {
//                strTemp = strTemp.substring(0, strTemp.length() - 1);
//            }
//            // 将数字重新反转
//            String resultStr = new StringBuilder(strTemp).reverse()
//                    .toString();
//            if(s2.length() >= 2){
//                return resultStr+"."+s2.substring(0, 2);
//            }else{
//                return resultStr+"."+s2+"0";
//            }
//
//            // return new
//            // DecimalFormat("0.00").format(Double.parseDouble(resultStr));
//        }else {
//            String st = (new DecimalFormat("0").format(Double.parseDouble(str)));
////            String st = new BigDecimal(Double.parseDouble(str)).setScale(2,BigDecimal.ROUND_HALF_UP).doubleValue()+"";
//            if (st != null && st.length() < 4) {
//                return new DecimalFormat("0.00").format(Double.parseDouble(st));
////                return new BigDecimal(Double.parseDouble(st)).setScale(2,BigDecimal.ROUND_HALF_UP).doubleValue()+"";
//            } else {
//                // 将传进数字反转
//                String reverseStr = new StringBuilder(st).reverse().toString();
//                System.out.println("reverseStr==>" + reverseStr);
//                String strTemp = "";
//                for (int i = 0; i < reverseStr.length(); i++) {
//                    if (i * 3 + 3 > reverseStr.length()) {
//                        strTemp += reverseStr.substring(i * 3,
//                                reverseStr.length());
//                        break;
//                    }
//                    strTemp += reverseStr.substring(i * 3, i * 3 + 3) + ",";
//                }
//                // 将[789,456,] 中最后一个[,]去除
//                if (strTemp.endsWith(",")) {
//                    strTemp = strTemp.substring(0, strTemp.length() - 1);
//                }
//                // 将数字重新反转
//                String resultStr = new StringBuilder(strTemp).reverse()
//                        .toString();
//                return resultStr+".00";
//                // return new
//                // DecimalFormat("0.00").format(Double.parseDouble(resultStr));
//            }
//        }
//    }

    /**
     * 判断是否是数字
     *
     * @param str
     * @return
     */
    public static boolean isNumeric(String str) {
        if (str != null && str.startsWith("-")) {
            str = str.substring(1);
        }
        Pattern pattern = Pattern.compile("[.0-9]*");

        Matcher isNum = pattern.matcher(str);

        if (!isNum.matches()) {

            return false;

        }

        return true;

    }


    /**
     * 字符串转换成double
     */
    public static String strToDouble(String str) {
        String a = "";
        if (str != null && isNumeric(str)) {
            a = new DecimalFormat("0.00").format(Double.parseDouble(str));
//            a = Double.parseDouble(str);
        } else {
            a = "0.00";
        }
        return a;
    }

    public static String doubleFormat(double str) {
        String a = new DecimalFormat("0.00").format(str);
        LogUtils.loge(Double.parseDouble(a) + "::" + a);
        return a;
    }

    public static Bitmap getBitmap(String url) {
        Bitmap bm = null;
        try {
            URL iconUrl = new URL(url);
            URLConnection conn = iconUrl.openConnection();
            HttpURLConnection http = (HttpURLConnection) conn;

            int length = http.getContentLength();

            conn.connect();
            // 获得图像的字符流
            InputStream is = conn.getInputStream();
            BufferedInputStream bis = new BufferedInputStream(is, length);
            bm = BitmapFactory.decodeStream(bis);
            bis.close();
            is.close();// 关闭流
        } catch (Exception e) {
            e.printStackTrace();
        }
        return bm;
    }


    public static Bitmap returnBitmap(String url) {
        URL fileUrl = null;
        Bitmap bitmap = null;

        try {
            fileUrl = new URL(url);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        try {
            HttpURLConnection conn = (HttpURLConnection) fileUrl
                    .openConnection();
            conn.setDoInput(true);
            conn.connect();
            InputStream is = conn.getInputStream();
            bitmap = BitmapFactory.decodeStream(is);
            is.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        LogUtils.loge("bitmap == null:" + (bitmap == null) + "count:" + bitmap.getByteCount());
        byte[] data = bitmap2Bytes(bitmap, 1);
        return BitmapFactory.decodeByteArray(data, 0, data.length);

    }

    /**
     * Bitmap转换成byte[]并且进行压缩,压缩到不大于IMAGE_SIZE
     *
     * @param bitmap
     * @param IMAGE_SIZE
     * @return
     */
    public static byte[] bitmap2Bytes(Bitmap bitmap, int IMAGE_SIZE) {
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, output);
        int options = 100;
        while (output.toByteArray().length > IMAGE_SIZE && options != 10) {
            output.reset(); //清空output
            bitmap.compress(Bitmap.CompressFormat.JPEG, options, output);//这里压缩options%，把压缩后的数据存放到output中
            options -= 10;
        }
        LogUtils.loge("output.toByteArray().length:" + output.toByteArray().length);
        return output.toByteArray();
    }

    /**
     * 获取设备唯一标识符
     *
     * @param context
     * @return device_id ，mac
     */
//    public static String getDeviceInfo(Context context) {
//        try {
//            Logger.i("getDeviceInfo");
//            android.telephony.TelephonyManager tm = (android.telephony.TelephonyManager) context
//                    .getSystemService(Context.TELEPHONY_SERVICE);
//
//            String device_id = tm.getDeviceId();
//
//            if (TextUtils.isEmpty(device_id)) {
//                device_id = android.provider.Settings.Secure.getString(
//                        context.getContentResolver(),
//                        android.provider.Settings.Secure.ANDROID_ID);
//            }
//
//            return device_id;
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return null;
//    }

    /**
     * 从AndroidManifest.xml中获取channel出现错误提示：Key xx expected String but value was
     * a java.lang.Integer. The default value <null> was reurned。
     * 对于AndroidManifest.xml配置channel名称时，当直接使用数字字符串时，会出现如上所示，获取到的channel值为null。
     * 或者说，并不建议使用数字字符串直接作为渠道名称。
     *
     * @param context
     * @return
     */
    public static String getChannel(Context context) {
        String dataName = null;
        try {
            ApplicationInfo appInfo = context.getPackageManager()
                    .getApplicationInfo(context.getPackageName(),
                            PackageManager.GET_META_DATA);

            dataName = appInfo.metaData.getString("UMENG_CHANNEL");
            if (dataName == null) {
                dataName = String.valueOf(appInfo.metaData
                        .getInt("UMENG_CHANNEL"));
            }
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }

        return dataName;
    }

    static public int getVersion() {
        int versionCode = BuildConfig.VERSION_CODE;
        String versionName = BuildConfig.VERSION_NAME;

        Logger.e("versionCode:" + versionCode + "\nversionName:" + versionName);
        return versionCode;
    }


    /**
     * 获取当前时间
     *
     * @param ts
     * @return
     */
    public static String getCurrentTime(Long ts) {
        if (ts == null) {
            return "";
        }
        //当时间不为空时进行转化
//        SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日HH:mm:ss");
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String result = sdf.format(new Date(ts));
        return result;
    }

    static public String getVersionName() {
        int versionCode = BuildConfig.VERSION_CODE;
        String versionName = BuildConfig.VERSION_NAME;

        Logger.e("versionCode:" + versionCode + "\nversionName:" + versionName);
        return versionName;
    }

    /**
     * 2      * 该方法是调用了系统的下载管理器
     * 3
     */
    public static void downLoadApk(Context context, String url) {
        /**
         6          * 在这里返回的 reference 变量是系统为当前的下载请求分配的一个唯一的ID，
         7          * 我们可以通过这个ID重新获得这个下载任务，进行一些自己想要进行的操作
         8          * 或者查询下载的状态以及取消下载等等
         9          */
        Uri uri = Uri.parse(url);        //下载连接
        DownloadManager manager = (DownloadManager) context.getSystemService(context.DOWNLOAD_SERVICE);  //得到系统的下载管理
        DownloadManager.Request requestApk = new DownloadManager.Request(uri);  //得到连接请求对象
        requestApk.setAllowedNetworkTypes(DownloadManager.Request.NETWORK_WIFI);   //指定在什么网络下进行下载，这里我指定了WIFI网络
        requestApk.setDestinationInExternalPublicDir(context.getPackageName() + "/myDownLoad", "xiaoyuantong.apk");  //制定下载文件的保存路径，我这里保存到根目录
        requestApk.setVisibleInDownloadsUi(true);  //设置显示下载界面
        requestApk.allowScanningByMediaScanner();  //表示允许MediaScanner扫描到这个文件，默认不允许。
        requestApk.setTitle("xxx更新下载");      //设置下载中通知栏的提示消息
        requestApk.setDescription("xxx更新下载");//设置设置下载中通知栏提示的介绍
        long downLoadId = manager.enqueue(requestApk);               //启动下载,该方法返回系统为当前下载请求分配的一个唯一的ID
    }


    /**
     * 大图片处理机制
     * 利用Bitmap 转存 R图片
     */
    public static Bitmap btp;

    public static void getBitmapForImgResourse(Context mContext, int imgId, ImageView mImageView) throws IOException {
        InputStream is = mContext.getResources().openRawResource(imgId);
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = false;
        options.inPreferredConfig = Bitmap.Config.RGB_565;
        options.inPurgeable = true;
        options.inInputShareable = true;
        options.inSampleSize = 1;
        btp = BitmapFactory.decodeStream(is, null, options);
        mImageView.setImageBitmap(btp);
        is.close();
    }

    /**
     * 大图片处理机制
     * 利用Bitmap 转存 R图片
     */
    public static Bitmap btp2;

    public static void getBitmapForBackgroundResourse(Context mContext, int imgId, View view) throws IOException {
        InputStream is = mContext.getResources().openRawResource(imgId);
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = false;
        options.inPreferredConfig = Bitmap.Config.RGB_565;
        options.inPurgeable = true;
        options.inInputShareable = true;
        options.inSampleSize = 2;
        btp2 = BitmapFactory.decodeStream(is, null, options);
        Drawable drawable = new BitmapDrawable(btp2);
        view.setBackground(drawable);
        is.close();
    }


    /**
     * 不变形加载网络图片
     *
     * @param context
     *            上下文
     * @param url
     *            图片链接
    //     * @param errorPic
     *            加载失败默认图片
    //     * @param loadingPic
     *            加载中默认图片
     * @param imageView
     *            要显示图片的控件
     */
//    public static void setGlideImage(Context context, String url, ImageView imageView) {
//        Glide.with(context)
//                .load(url)
//                .asBitmap()
//                .dontAnimate()
////                .centerCrop()
//                .placeholder(R.drawable.placeholder_big)
//                .error(R.drawable.placeholder_big)
//                .into(new MyBitmapImageViewTarget(imageView));
//    }


    /**
     * 不变形加载网络图片
     *
     * @param context
     *            上下文
     * @param url
     *            图片链接
    //     * @param errorPic
     *            加载失败默认图片
    //     * @param loadingPic
     *            加载中默认图片
     * @param imageView
     *            要显示图片的控件
     */
//    public static void setGlideImageRound(Context context, String url, ImageView imageView) {
//        Glide.with(context)
//                .load(url)
//                .asBitmap()
//                .dontAnimate()
////                .centerCrop()
//                .placeholder(R.drawable.placeholder_big)
//                .error(R.drawable.placeholder_big)
//                .transform(new GlideRoundTransform(context, 4))
//                .into(new MyBitmapImageViewTarget(imageView));
//    }

//    public static void setGlideImageRoundCorner(Context context, String url, ImageView imageView) {
//        Glide.with(context)
//                .load(url)
//                .asBitmap()
//                .dontAnimate()
////                .centerCrop()
//                .placeholder(R.drawable.placeholder_big)
//                .error(R.drawable.placeholder_big)
//                .transform(new GlideRoundTransform(context, 60))
//                .into(new MyBitmapImageViewTarget(imageView));
//    }

    /**
     * 根据包名判断app是否安装
     *
     * @param context
     * @param packageName
     * @return
     */
    @NonNull
    public static boolean isAppInstalled(Context context, String packageName) {
        final PackageManager packageManager = context.getPackageManager();
        List<PackageInfo> pinfo = packageManager.getInstalledPackages(0);
        List<String> pName = new ArrayList<String>();
        if (pinfo != null) {
            for (int i = 0; i < pinfo.size(); i++) {
                String pn = pinfo.get(i).packageName;
                pName.add(pn);
            }
        }
        return pName.contains(packageName);
    }

    /**
     * 将字符串转成MD5值
     *
     * @param string
     * @return
     */
    public static String stringToMD5(String string) {
        byte[] hash;

        try {
            hash = MessageDigest.getInstance("MD5").digest(string.getBytes("UTF-8"));
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return null;
        }

        StringBuilder hex = new StringBuilder(hash.length * 2);
        for (byte b : hash) {
            if ((b & 0xFF) < 0x10)
                hex.append("0");
            hex.append(Integer.toHexString(b & 0xFF));
        }

        return hex.toString();
    }

    /**
     * 通过uri获取图片并进行压缩
     *
     * @param uri
     */
    public static Bitmap getBitmapFormUri(Activity ac, Uri uri) throws FileNotFoundException, IOException {
        InputStream input = ac.getContentResolver().openInputStream(uri);
        BitmapFactory.Options onlyBoundsOptions = new BitmapFactory.Options();
        onlyBoundsOptions.inJustDecodeBounds = true;
        onlyBoundsOptions.inDither = true;//optional
        onlyBoundsOptions.inPreferredConfig = Bitmap.Config.ARGB_8888;//optional
        BitmapFactory.decodeStream(input, null, onlyBoundsOptions);
        input.close();
        int originalWidth = onlyBoundsOptions.outWidth;
        int originalHeight = onlyBoundsOptions.outHeight;
        if ((originalWidth == -1) || (originalHeight == -1))
            return null;
        //图片分辨率以480x800为标准
        float hh = 800f;//这里设置高度为800f
        float ww = 480f;//这里设置宽度为480f
        //缩放比。由于是固定比例缩放，只用高或者宽其中一个数据进行计算即可
        int be = 1;//be=1表示不缩放
        if (originalWidth > originalHeight && originalWidth > ww) {//如果宽度大的话根据宽度固定大小缩放
            be = (int) (originalWidth / ww);
        } else if (originalWidth < originalHeight && originalHeight > hh) {//如果高度高的话根据宽度固定大小缩放
            be = (int) (originalHeight / hh);
        }
        if (be <= 0)
            be = 1;
        //比例压缩
        BitmapFactory.Options bitmapOptions = new BitmapFactory.Options();
        bitmapOptions.inSampleSize = be;//设置缩放比例
        bitmapOptions.inDither = true;//optional
        bitmapOptions.inPreferredConfig = Bitmap.Config.ARGB_4444;//optional
        input = ac.getContentResolver().openInputStream(uri);
        Bitmap bitmap = BitmapFactory.decodeStream(input, null, bitmapOptions);
        input.close();

        return bitmap;//再进行质量压缩
    }

    /**
     * bitmap转为base64
     *
     * @param bitmap
     * @return
     */
    public static String bitmapToBase64(Bitmap bitmap) {

        String result = null;
        ByteArrayOutputStream baos = null;
        try {
            if (bitmap != null) {
                baos = new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);

                baos.flush();
                baos.close();

                byte[] bitmapBytes = baos.toByteArray();
                result = Base64.encodeToString(bitmapBytes, Base64.DEFAULT);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (baos != null) {
                    baos.flush();
                    baos.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return result;
    }

    public static ArrayMap<String, String> map;

    static {
        map = new ArrayMap<String, String>();
    }

    public static void saveQueryInfo(int pos, String dateStr) {
        map.put(pos + "", dateStr);
    }


//    public static ArrayMap<String, String> map;
//
//    static {
//        map = new ArrayMap<String, String>();
//    }
//
//    public static void saveQueryInfo(int pos, String dateStr) {
//        map.put(pos + "", dateStr);
//    }


    /**
     * 计算图片尺寸
     *
     * @param srcImg
     * @return
     */
    public static int[] computeSize(File srcImg) {
        int[] size = new int[2];

        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        options.inSampleSize = 1;

        BitmapFactory.decodeFile(srcImg.getAbsolutePath(), options);
        size[0] = options.outWidth;
        size[1] = options.outHeight;

        return size;
    }

    /**
     * 判断网络是否连接
     *
     * @param c
     * @return
     */
    public static boolean isNetworkAvailable(Context c) {
        try {
            ConnectivityManager connectivity = (ConnectivityManager) c
                    .getSystemService(Context.CONNECTIVITY_SERVICE);
            if (connectivity == null) {
                return false;
            } else {
                NetworkInfo info = connectivity.getActiveNetworkInfo();
                if (info.isAvailable()) {
                    return true;
                }
            }
        } catch (Exception e) {
        }
        return false;
    }


    /**
     * Android获取如何获取当前手机IP地址
     *
     * @param context
     * @return
     */
    public static String getIPAddress(Context context) {
        NetworkInfo info = ((ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE)).getActiveNetworkInfo();
        if (info != null && info.isConnected()) {
            if (info.getType() == ConnectivityManager.TYPE_MOBILE) {//当前使用2G/3G/4G网络
                try {
                    //Enumeration<NetworkInterface> en=NetworkInterface.getNetworkInterfaces();
                    for (Enumeration<NetworkInterface> en = NetworkInterface.getNetworkInterfaces(); en.hasMoreElements(); ) {
                        NetworkInterface intf = en.nextElement();
                        for (Enumeration<InetAddress> enumIpAddr = intf.getInetAddresses(); enumIpAddr.hasMoreElements(); ) {
                            InetAddress inetAddress = enumIpAddr.nextElement();
                            if (!inetAddress.isLoopbackAddress() && inetAddress instanceof Inet4Address) {
                                return inetAddress.getHostAddress();
                            }
                        }
                    }
                } catch (SocketException e) {
                    e.printStackTrace();
                }

            } else if (info.getType() == ConnectivityManager.TYPE_WIFI) {//当前使用无线网络
                WifiManager wifiManager = (WifiManager) context.getSystemService(Context.WIFI_SERVICE);
                WifiInfo wifiInfo = wifiManager.getConnectionInfo();
                String ipAddress = intIP2StringIP(wifiInfo.getIpAddress());//得到IPV4地址
                return ipAddress;
            }
        } else {
            //当前无网络连接,请在设置中打开网络
        }
        return null;
    }

    /**
     * 将得到的int类型的IP转换为String类型
     *
     * @param ip
     * @return
     */
    public static String intIP2StringIP(int ip) {
        return (ip & 0xFF) + "." +
                ((ip >> 8) & 0xFF) + "." +
                ((ip >> 16) & 0xFF) + "." +
                (ip >> 24 & 0xFF);
    }


    //判断email格式是否正确
    public static boolean isEmail(String email) {
        String str = "^([a-zA-Z0-9_\\-\\.]+)@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.)|(([a-zA-Z0-9\\-]+\\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})(\\]?)$";
        Pattern p = Pattern.compile(str);
        Matcher m = p.matcher(email);

        return m.matches();
    }

    /**
     * 添加监听，在hint时和text时切换字体大小
     * 一般用于输入金额时
     *
     * @param editText
     * @return
     */
    public static void textChangedListener(final EditText editText) {
        InputFilter[] filters;
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length() == 0) {
                    editText.setTextSize(TypedValue.COMPLEX_UNIT_SP, 14);
                } else {
                    editText.setTextSize(TypedValue.COMPLEX_UNIT_SP, 30);
                }

                //限制只能输入到小数点后两位
                if (s.toString().contains(".")) {
//                    editText.setFilters(inputFilters12);
                    if (s.length() - 1 - s.toString().indexOf(".") > 2) {//DECIMAL_DIGITS
                        s = s.toString().subSequence(0, s.toString().indexOf(".") + 2 + 1);//DECIMAL_DIGITS
                        editText.setText(s);
                        editText.setSelection(s.length());
                    }
                }

                //带小数12位 整数9位的逻辑
                //如果超过9位且第十位是小数点，可继续输入，否则不可输入
                if (s.length() >= 10 && !s.toString().substring(0, 9).contains(".") && !s.toString().subSequence(9, 10).equals(".")) {
                    LogUtils.logd("9------" + s.toString().subSequence(9, 10).toString());
                    s = s.toString().subSequence(0, 9);
                    editText.setText(s);
                    editText.setSelection(s.length());
                }


                if (s.toString().trim().substring(0).equals(".")) {//如果第一位输入为.，则自动加上0.
                    s = "0" + s;
                    editText.setText(s);
                    editText.setSelection(2);
                }
                if (s.toString().startsWith("0")
                        && s.toString().trim().length() > 1) {//如果是0开头且继续输入，则除了小数点不能输入其他内容
                    if (!s.toString().substring(1, 2).equals(".")) {
                        editText.setText(s.subSequence(0, 1));
                        editText.setSelection(1);
                        return;
                    }
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });
    }

    /**
     * 限制只能输入到小数点后两位
     * 一般用于输入金额时
     *
     * @param editText
     * @return
     */
    public static void numberPointLimitListener(final EditText editText) {
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                //限制只能输入到小数点后两位
                if (s.toString().contains(".")) {
                    if (s.length() - 1 - s.toString().indexOf(".") > 2) {//DECIMAL_DIGITS
                        s = s.toString().subSequence(0,
                                s.toString().indexOf(".") + 2 + 1);//DECIMAL_DIGITS
                        editText.setText(s);
                        editText.setSelection(s.length());
                    }
                }

                //带小数12位 整数9位的逻辑
                //如果超过9位且第十位是小数点，可继续输入，否则不可输入
                if (s.length() >= 10 && !s.toString().subSequence(9, 10).equals(".")) {
                    LogUtils.logd("9------" + s.toString().subSequence(9, 10).toString());
                    s = s.toString().subSequence(0, 9);
                    editText.setText(s);
                    editText.setSelection(s.length());
                }

                if (s.toString().trim().substring(0).equals(".")) {
                    s = "0" + s;
                    editText.setText(s);
                    editText.setSelection(2);
                }
                if (s.toString().startsWith("0")
                        && s.toString().trim().length() > 1) {
                    if (!s.toString().substring(1, 2).equals(".")) {
                        editText.setText(s.subSequence(0, 1));
                        editText.setSelection(1);
                        return;
                    }
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });
    }

    /**
     * 判断手机号码
     *
     * @param inputText
     * @return
     */
    public static boolean isPhone(String inputText) {
        Pattern p = Pattern.compile("^((14[0-9])|(13[0-9])|(15[0-9])|(18[0-9])|(17[0-9]))\\d{8}$");
        Matcher m = p.matcher(inputText);
        return m.matches();
    }


    /**
     * 字符串每隔4位间隔
     *
     * @param input 原字符串
     * @return 间隔后的字符串
     * @throws Exception
     */
    public static String intervalString(String input) throws Exception {
        String regex = "(.{4})";
        input = input.replaceAll(regex, "$1 ");
        LogUtils.logd(input);
        return input;
    }

    /**
     * @param text
     * @return
     */
    public static boolean isTextEmpty(String text) {
        return text == null || TextUtils.isEmpty(text);
    }

    /**
     * 获取手机Id
     */
    public static String GetDeviceID(Context context) {
        return Settings.Secure.getString(context.getContentResolver(), Settings.Secure.ANDROID_ID);
    }


    /**
     * 系统层的权限判断
     *
     * @param context     上下文
     * @param permissions 申请的权限 Manifest.permission.READ_CONTACTS
     * @return 是否有权限 ：其中有一个获取不了就是失败了
     */
    public static boolean hasPermission(@NonNull Context context, @NonNull List<String> permissions) {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) return true;
        for (String permission : permissions) {
            String op = AppOpsManagerCompat.permissionToOp(permission);
            if (TextUtils.isEmpty(op)) continue;
            int result = AppOpsManagerCompat.noteProxyOp(context, op, context.getPackageName());
            if (result == AppOpsManagerCompat.MODE_IGNORED) return false;
            result = ContextCompat.checkSelfPermission(context, permission);
            if (result != PackageManager.PERMISSION_GRANTED) return false;
        }
        return true;
    }

    /**
     * webview绘制图片
     *
     * @param wv_capture
     * @return
     */
    public static Bitmap captureWebView(WebView wv_capture) {
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
     * 检测是否有emoji表情
     *
     * @param source
     * @return
     */
    public static boolean containsEmoji(String source) {
        int len = source.length();
        for (int i = 0; i < len; i++) {
            char codePoint = source.charAt(i);
            if (!isEmojiCharacter(codePoint)) { //如果不能匹配,则该字符是Emoji表情
                return true;
            }
        }
        return false;
    }

    /**
     * 判断是否是Emoji
     *
     * @param codePoint 比较的单个字符
     * @return
     */
    private static boolean isEmojiCharacter(char codePoint) {
        return (codePoint == 0x0) || (codePoint == 0x9) || (codePoint == 0xA) ||
                (codePoint == 0xD) || ((codePoint >= 0x20) && (codePoint <= 0xD7FF)) ||
                ((codePoint >= 0xE000) && (codePoint <= 0xFFFD)) || ((codePoint >= 0x10000)
                && (codePoint <= 0x10FFFF));
    }

    /**
     * 判断sd卡是否存在某张图片
     *
     * @param strFile
     * @return
     */
    public static boolean fileIsExists(String strFile) {
        try {
            File f = new File(strFile);
            if (!f.exists()) {
                return false;
            }
        } catch (Exception e) {
            return false;
        }
        return true;
    }


    public static abstract class OnMultiClickListener implements View.OnClickListener {
        // 两次点击按钮之间的点击间隔不能少于1000毫秒
        private static final int MIN_CLICK_DELAY_TIME = 1000;
        private long lastClickTime;

        public abstract void onMultiClick(View v);

        @Override
        public void onClick(View v) {
            long curClickTime = System.currentTimeMillis();
            if ((curClickTime - lastClickTime) >= MIN_CLICK_DELAY_TIME) {
                // 超过点击间隔后再将lastClickTime重置为当前点击时间
                lastClickTime = curClickTime;
                onMultiClick(v);
            }
        }
    }


    private static IWXAPI api; // 相应的包，请集成SDK后自行引入

    /**
     * 判断微信客户端是否存在
     *
     * @return true安装, false未安装
     */
    public static boolean isWeChatAppInstalled(Context context) {
        api = WXAPIFactory.createWXAPI(context, "Your WeChat AppId");
        if (api.isWXAppInstalled() && api.isWXAppSupportAPI()) {
            return true;
        } else {
            final PackageManager packageManager = context.getPackageManager();// 获取packagemanager
            List<PackageInfo> pinfo = packageManager.getInstalledPackages(0);// 获取所有已安装程序的包信息
            if (pinfo != null) {
                for (int i = 0; i < pinfo.size(); i++) {
                    String pn = pinfo.get(i).packageName;
                    if (pn.equalsIgnoreCase("com.tencent.mm")) {
                        return true;
                    }
                }
            }
            return false;
        }
    }

    public static boolean isQQClientAvailable(Context context) {
        final PackageManager packageManager = context.getPackageManager();
        List<PackageInfo> pinfo = packageManager.getInstalledPackages(0);
        if (pinfo != null) {
            for (int i = 0; i < pinfo.size(); i++) {
                String pn = pinfo.get(i).packageName;
                if (pn.equals("com.tencent.mobileqq")) {
                    return true;
                }
            }
        }
        return false;
    }

}




