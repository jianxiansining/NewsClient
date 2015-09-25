package com.jxsn.newsclient.fragment;

import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.jxsn.newsclient.R;
import com.jxsn.newsclient.bean.NewsCenterBean;
import com.jxsn.newsclient.ui.HomeUi;

import java.util.List;


/**
 * @项目名称:NewsClient
 * @包名:com.jxsn.newsclient.fragment
 * @作者:djn
 * @创建日期:2015/9/23 13:57
 * @描述:菜单部分的Fragment
 * @SVN版本号:$$Rev$$
 * @修改人:$$Author$$
 * @修改时间:$$Data$$
 * @修改内容:TODO
 */
public class MenuFragment extends BaseFragment implements AdapterView.OnItemClickListener
{


    private static final String TAG ="MenuFragment";

    private ListView mListView;
    private int mCurrentPosition;

    private List<NewsCenterBean.MenuBean> mListDatas;

    private MenuAdapter mMenuAdapter;


    //实现父类提供的方法，为自己布局
    @Override
    protected View initView()
    {
        //加载view
        mListView = (ListView) View.inflate(getActivity(), R.layout.ui_menu, null);
        return mListView;
    }

    //To analyze data 加载数据
    @Override
    protected void initData()
    {

    }
    public void setData(List<NewsCenterBean.MenuBean> list){
        mListDatas=list;
        //获得适配器对象
        mMenuAdapter = new MenuAdapter();
        mListView.setAdapter(mMenuAdapter);

        //设置监听
        mListView.setOnItemClickListener(this);
    }

    //监听事件
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id)
    {
        //如果选中的是当前条目，则不做任何事
        if(mCurrentPosition==position){
            return;
        }
        Log.d(TAG,"点击了"+position);
        //需要通知新闻中心界面去改变对应的显示
        HomeUi ui= (HomeUi) mContext;
        //获得新闻中心界面操作对象
        ContentFragment contentFragment = ui.getContentFragment();
        //切换相应显示界面
        contentFragment.switchMenu(position);


        //获得当前点击的条目位置
        mCurrentPosition=position;

        //更新UI
        mMenuAdapter.notifyDataSetChanged();

        //关闭菜单
        ui.getSlidingMenu().toggle();
    }

    //自定义继承baseAdapter
    public class MenuAdapter extends BaseAdapter{

        @Override
        public int getCount()
        {
            if(mListDatas!=null){
                return mListDatas.size();
            }
            return 0;
        }


        @Override
        public Object getItem(int position)
        {
            if(mListDatas!=null){
                return mListDatas.get(position);
            }
            return null;
        }


        @Override
        public long getItemId(int position)
        {

            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent)
        {
            ViewHolder holder=null;
            if(convertView==null){
                //加载view
                convertView = View.inflate(getActivity(),R.layout.ui_menu_item, null);
                //初始化holder
                holder=new ViewHolder();
                //设置标记
                convertView.setTag(holder);
                //holder缓存view
                holder.tvMenuData= (TextView) convertView.findViewById(R.id.ui_memu_items);
            }else{
                holder= (ViewHolder) convertView.getTag();
            }
            //获得数据对象,并设置菜单条目内容
            holder.tvMenuData.setText(mListDatas.get(position).title);
            if(mCurrentPosition==position){
                holder.tvMenuData.setEnabled(true);
            }else{
                holder.tvMenuData.setEnabled(false);
            }
            return convertView;
        }
    }
    private class ViewHolder{
        TextView tvMenuData;
    }
}
