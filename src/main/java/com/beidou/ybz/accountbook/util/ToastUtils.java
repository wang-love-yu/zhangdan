package com.beidou.ybz.accountbook.util;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.graphics.Typeface;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.beidou.ybz.accountbook.R;


/**
 * Created by Administrator on 2016/6/27.
 */
public class ToastUtils {

    private static View view;
    private static Toast toast,toast1;
    private static Typeface typeFace;
    public static int toastTime = 2000;
    public static void toast(Context context, String content)
    {
//        typeFace = Typeface.createFromAsset(context.getAssets(), "fonts/PingFang-Light.ttf");
        view= LayoutInflater.from(context.getApplicationContext()).inflate(R.layout.toast_item, null);
        TextView textView=(TextView) view.findViewById(R.id.toast_text);
        textView.setTypeface(typeFace);
        if(toast == null){
            textView.setText(content);
            toast = new Toast(context.getApplicationContext());
            toast.setDuration(Toast.LENGTH_SHORT);
            toast.setGravity(Gravity.CENTER, Gravity.CENTER, Gravity.CENTER);
            toast.setView(view);
        }else{
            textView.setText(content);
            toast.setDuration(Toast.LENGTH_SHORT);
            toast.setGravity(Gravity.CENTER, Gravity.CENTER, Gravity.CENTER);
            toast.setView(view);
        }
        toast.show();
    }

    public static void toast(Context context, int id, String content)
    {
//        typeFace = Typeface.createFromAsset(context.getAssets(), "fonts/PingFang-Light.ttf");
        view = LayoutInflater.from(context.getApplicationContext()).inflate(R.layout.toast_item2, null);
        TextView textView = (TextView) view.findViewById(R.id.toast_text);
//        textView.setTypeface(typeFace);
        ImageView imageView = (ImageView) view.findViewById(R.id.toast_img);
        if(id == 0){
            imageView.setVisibility(View.GONE);
        }else{
            imageView.setVisibility(View.VISIBLE);
            imageView.setBackgroundResource(id);
        }

        textView.setText(content);
        if(toast1 == null){
            toast1=new Toast(context.getApplicationContext());
            toast1.setDuration(Toast.LENGTH_SHORT);
            toast1.setGravity(Gravity.CENTER, Gravity.CENTER, 0);
            toast1.setView(view);
        }else{
            toast1.setDuration(Toast.LENGTH_SHORT);
            toast1.setGravity(Gravity.CENTER, Gravity.CENTER, 0);
            toast1.setView(view);
        }

        toast1.show();
        if(imageView != null){
            ObjectAnimator animatoriv1 = ObjectAnimator.ofFloat(imageView,"scaleX", 0f, 1.4f,1.0f);
            ObjectAnimator animatoriv2 = ObjectAnimator.ofFloat(imageView,"scaleY", 0f, 1.4f,1.0f);
            ObjectAnimator animatoriv3 = ObjectAnimator.ofFloat(imageView,"alpha", 0f, 1f);
            AnimatorSet animSet = new AnimatorSet();

            animSet.play(animatoriv1).with(animatoriv2).with(animatoriv3);
            animSet.setDuration(500);
            animSet.setStartDelay(200);
            animSet.setInterpolator(new AccelerateDecelerateInterpolator());
            animSet.start();
        }
    }

}
