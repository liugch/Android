package activity.main.com.recycleviewdemo;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class StaggeredAdapter extends MyRecycleAdapter {

    private List<Integer> mHeight;

    public StaggeredAdapter(Context context, List<String> data) {
        super(context, data);
        // 生成一个100 到400 之间的 高度
        // 生成一个100 到400 之间的 高度
        mHeight = new ArrayList<Integer>();

        for (int i = 0; i < mDatas.size(); i++) {
            mHeight.add((int) (100 + Math.random() * 300));
        }
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        // 获取高度
        ViewGroup.LayoutParams lp = holder.itemView.getLayoutParams();
        lp.height = mHeight.get(position);
        holder.itemView.setLayoutParams(lp);

        holder.tv.setText(mDatas.get(position));
        setUpItemEvent(holder);

    }


}
