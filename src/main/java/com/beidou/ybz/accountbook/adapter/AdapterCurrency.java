package com.beidou.ybz.accountbook.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.beidou.ybz.accountbook.R;
import com.beidou.ybz.accountbook.mvp.entity.CurrencyModel;
import com.beidou.ybz.accountbook.util.StringFormatUtil;
import com.beidou.ybz.accountbook.widget.StickyRecyclerHeader.StickyRecyclerHeadersAdapter;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

/**http://www.jianshu.com/p/b343fcff51b0
 * Created by lenovo on 2017/9/7.
 * 货币列表adapter
 */
public class AdapterCurrency extends BaseQuickAdapter<CurrencyModel.BodyBean.CurrencyListBean,BaseViewHolder> implements StickyRecyclerHeadersAdapter<RecyclerView.ViewHolder> {
    String bankType, mKey;
    public AdapterCurrency(int layoutResId, List data) {
        super(layoutResId, data);
    }

    public void setKey(String key) {
        mKey = key;
        notifyDataSetChanged();
    }

    @Override
    protected void convert(BaseViewHolder helper, CurrencyModel.BodyBean.CurrencyListBean item) {
        helper.setText(R.id.tvName,item.getCurrencyName()+" ("+item.getCurrencyNo()+")");

        StringFormatUtil spanCode = new StringFormatUtil(mContext, item.getCurrencyName()+" ("+item.getCurrencyNo()+")",
                mKey, R.color.colorGold).fillColor();
        if (spanCode != null) {
            helper.setText(R.id.tvName, spanCode.getResult());
        } else {
            helper.setText(R.id.tvName,item.getCurrencyName()+" ("+item.getCurrencyNo()+")");
        }


    }

    @Override
    public long getHeaderId(int position) {
        return getItem(position).getInitials().charAt(0);
    }

    @Override
    public RecyclerView.ViewHolder onCreateHeaderViewHolder(ViewGroup parent) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.contact_header, parent, false);
        return new RecyclerView.ViewHolder(view){};
    }

    @Override
    public void onBindHeaderViewHolder(RecyclerView.ViewHolder holder, int position) {
        try {
            TextView textView = (TextView) holder.itemView;
            String showValue = String.valueOf(getItem(position).getInitials().charAt(0));
            textView.setText(showValue);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
