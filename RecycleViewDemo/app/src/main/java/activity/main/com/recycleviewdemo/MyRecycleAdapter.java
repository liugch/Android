package activity.main.com.recycleviewdemo;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class MyRecycleAdapter extends RecyclerView.Adapter<MyRecycleAdapter.MyViewHolder> {
    protected LayoutInflater mInflater;
    protected Context mContxt;
    protected List<String> mDatas;

    //自己添加一个 Item 点击事件
    public interface OnItemClickListener {
        void onItemClick(View view, int position);

        void onItemLongClick(View view, int position);
    }
    private OnItemClickListener mOnItemClickListener;

    public void setmOnItemClickListener(OnItemClickListener listener) {
        this.mOnItemClickListener = listener;
    }

    public MyRecycleAdapter(Context context, List<String> data) {
        this.mContxt = context;
        this.mDatas = data;
        this.mInflater = LayoutInflater.from(context);
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = mInflater.inflate(R.layout.item_single_textview, parent, false);
        MyViewHolder myViewHolder = new MyViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
        holder.tv.setText(mDatas.get(position));
        setUpItemEvent(holder);
    }

    @Override
    public int getItemCount() {

        return mDatas.size();
    }

    protected void setUpItemEvent(final MyViewHolder holder) {

        if (mOnItemClickListener != null) {

            // 各组件设置一个点击事件  holder.itemView 返回一个view
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // 获取相对于当前布局的位置
                    int layoutPosition = holder.getLayoutPosition();
                    // 进而个每一个 item 添加一个点击事件(把相关的参数传递过去)
                    mOnItemClickListener.onItemClick(holder.itemView, layoutPosition);
                }
            });

            holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    //获取点击的位置
                    int layoutPosition = holder.getLayoutPosition();
                    mOnItemClickListener.onItemLongClick(holder.itemView, layoutPosition);
                    return false;
                }
            });
        }
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView tv;

        public MyViewHolder(View itemView) {
            super(itemView);
            tv = (TextView) itemView.findViewById(R.id.id_tv);
        }
    }

    // 添加一个数据
    public void addData(int position) {

        mDatas.add(position, "insert one");
        // notifyDataSetChanged();
        //通知数据已经发生的了改变<执行了一个item>
        notifyItemInserted(position);
    }


    public void deleteData(int position) {

        mDatas.remove(position);
        // 移除一个item
        notifyItemRemoved(position);
    }

}
