package activity.main.com.recycleviewdemo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import Animation.ScaleInOutItemAnimator;

public class MainActivity extends AppCompatActivity {
    private RecyclerView mRecycleView;
    private List<String> mDatas;
    private MyRecycleAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initDatas();
        initViews();
        mAdapter = new MyRecycleAdapter(this, mDatas);
        mAdapter.setmOnItemClickListener(new MyRecycleAdapter.OnItemClickListener() {

            @Override
            public void onItemClick(View view, int position) {
                Toast.makeText(MainActivity.this, "click" + position, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onItemLongClick(View view, int position) {
                Toast.makeText(MainActivity.this, "Longclick" + position, Toast.LENGTH_SHORT).show();
            }
        });
        mRecycleView.setAdapter(mAdapter);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this,
                LinearLayoutManager.VERTICAL, false);
        //  linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);

        mRecycleView.setLayoutManager(linearLayoutManager);

        // 设置分割线 第三方的分割线  如果是简单的话可以用 margin 来讲进行设置
        mRecycleView.addItemDecoration(new DividerItemDecoration(this,
                DividerItemDecoration.VERTICAL_LIST));


        // 添加 增加和删除时的动画   导入第三方的动画
        mRecycleView.setItemAnimator(new ScaleInOutItemAnimator(mRecycleView));
    }

    private void initViews() {

        mRecycleView = (RecyclerView) findViewById(R.id.id_recyclerView);
    }

    private void initDatas() {
        mDatas = new ArrayList<String>();
        for (int i = 'A'; i <= 'z'; i++) {
            mDatas.add("" + (char) i);
        }
    }


    //创建菜单
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //新建的xml文件  把菜单 加入进来
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    //当菜单选项被选中的时候
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // 获取你点击的菜单item 项
        int id = item.getItemId();
        switch (id) {
            case R.id.action_add:
                mAdapter.addData(1);
                break;
            case R.id.action_delete:
                mAdapter.deleteData(1);
                break;
            case R.id.action_gridview:
                //生成一个三列的grid 表格列表
                mRecycleView.setLayoutManager(new GridLayoutManager(this, 3));
                break;
            case R.id.action_listview:
                mRecycleView.setLayoutManager(new LinearLayoutManager(this));
                break;
            case R.id.action_staggered:
                Intent intent = new Intent(MainActivity.this, StaggeredGridLayoutActity.class);
                startActivity(intent);
                break;
            case R.id.action_hor_gridview:
                // 生成一个 五行 的 数据表格   横向的
                mRecycleView.setLayoutManager(new StaggeredGridLayoutManager(5,
                        StaggeredGridLayoutManager.HORIZONTAL));
                break;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
