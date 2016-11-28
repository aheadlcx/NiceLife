package me.aheadlcx.health.ui.health;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

import me.aheadlcx.health.R;
import me.aheadlcx.health.api.ApiConstant;
import me.aheadlcx.health.model.HealthNewsItem;

/**
 * Description:
 * Creator: aheadlcx
 * Date:2016/11/27 下午8:58
 */

public class HealthNewsAdapter extends RecyclerView.Adapter<HealthNewsAdapter.VH> {

    private List<HealthNewsItem> mList;
    private final Context mContext;

    public HealthNewsAdapter(List<HealthNewsItem> list, Context context) {
        mList = list;
        mContext = context;
    }

    public HealthNewsAdapter setList(List<HealthNewsItem> list) {
        mList = list;
        return this;
    }

    @Override
    public VH onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout
                .item_health_news_list, parent, false);

        VH holder = new VH(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(VH holder, int position) {
        holder.itemTitle.setText(mList.get(position).getTitle());
        holder.txtItemDes.setText(mList.get(position).getDescription());
        holder.txtItemLabel.setText(mList.get(position).getKeywords());
        Glide.with(mContext).load(ApiConstant.base_img_url + mList.get(position).getImg()).into
                (holder.itemImage);
    }

    @Override
    public int getItemCount() {
        if (null == mList) {
            return 0;
        }
        return mList.size();
    }

    public static class VH extends RecyclerView.ViewHolder {
        TextView txtItemLabel;
        TextView txtItemDes;
        ImageView itemImage;
        TextView itemTitle;

        public VH(View view) {
            super(view);
            txtItemLabel = (TextView) view.findViewById(R.id.txtItemLabel);
            txtItemDes = (TextView) view.findViewById(R.id.txtItemDes);
            itemImage = (ImageView) view.findViewById(R.id.itemImage);
            itemTitle = (TextView) view.findViewById(R.id.itemTitle);
        }
    }
}
