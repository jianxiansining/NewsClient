package com.jxsn.newsclient.view;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.jxsn.newsclient.R;

import java.text.SimpleDateFormat;
import java.util.Date;


/**
 * @项目名称:NewsClient
 * @包名:com.jxsn.newsclient.view
 * @作者:djn
 * @创建日期:2015/9/27 19:54
 * @描述:TODO
 * @SVN版本号:$$Rev$$
 * @修改人:$$Author$$
 * @修改时间:$$Data$$
 * @修改内容:TODO
 */
public class RefreshListView extends ListView
{


    private static final String TAG = "RefreshListView";

    private int mRefreshHeadHeight;

    private LinearLayout mViewHeader;

    private int mDownX;

    private int mDownY;

    //下拉刷新的状态
    private static final int PULL_DOWN_REFRESHSTATE = 1;

    //松开刷新的状态
    private static final int PULL_UP_REFRESHSTATE = 2;

    //正在刷新的状态
    private static final int PULL_RUNING_REFRESHSTATE = 3;

    //设置默认状态
    private static final int DEFAULT_STATE=0;
    //设置当前状态为下拉刷新
    private int mCurrentState = DEFAULT_STATE;

    private ProgressBar mProgress;

    private ImageView mIvArr;

    private TextView mUpdate;

    private TextView mDate;


    public RefreshListView(Context context)
    {

        this(context, null);
    }


    public RefreshListView(Context context, AttributeSet attrs)
    {

        super(context, attrs);
        //设置刷新头信息
        refreshHeader();
    }


    //刷新头信息
    private void refreshHeader()
    {

        mViewHeader = (LinearLayout) View.inflate(getContext(), R.layout.refresh_head, null);

        mProgress = (ProgressBar) mViewHeader.findViewById(R.id.refresh_head_pb);
        mIvArr = (ImageView) mViewHeader.findViewById(R.id.refresh_head_arr);
        mUpdate = (TextView) mViewHeader.findViewById(R.id.refresh_head_update);
        mDate = (TextView) mViewHeader.findViewById(R.id.refresh_head_date);

        //初始化时间
        SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd HH:mm");
        String date=format.format(new Date(System.currentTimeMillis()));
        mDate.setText(date);
        //添加刷新头信息
        addHeaderView(mViewHeader);

        /**
         * 设置一开始隐藏
         */
        //设置随意设置，就是按照自己的布局来显示
        mViewHeader.measure(0, 0);
        mRefreshHeadHeight = mViewHeader.getMeasuredHeight();
        Log.d(TAG, "" + mRefreshHeadHeight);
        //一开始的位置
        mViewHeader.setPadding(0, -mRefreshHeadHeight, 0, 0);

    }


    @Override
    public boolean onTouchEvent(MotionEvent ev)
    {
        //设置触摸监听
        switch (ev.getAction())
        {
            case MotionEvent.ACTION_DOWN:
                mDownX = (int) (ev.getX() + 0.5f);
                mDownY = (int) (ev.getY() + 0.5f);
                break;
            case MotionEvent.ACTION_MOVE:
                int moveX = (int) (ev.getX() + 0.5f);
                int moveY = (int) (ev.getY() + 0.5f);

                //获得滑动距离
                int diffx = moveY - mDownY;
                if (diffx > 0 && getFirstVisiblePosition() == 0)
                {
                    mViewHeader.setPadding(0, diffx - mRefreshHeadHeight, 0, 0);
                    //判断是否
                    if (diffx - mRefreshHeadHeight < 0 &&mCurrentState!=PULL_DOWN_REFRESHSTATE)
                    {
                        mCurrentState = PULL_DOWN_REFRESHSTATE;
                        //下拉刷新的情况
                        Log.d(TAG, "下拉刷新");
                        //更新UI
                        updateUI();
                    } else if (diffx - mRefreshHeadHeight >=0 && mCurrentState!=PULL_UP_REFRESHSTATE)
                    {
                        mCurrentState = PULL_UP_REFRESHSTATE;
                        //松开刷新的情况
                        Log.d(TAG, "松开刷新");
                        updateUI();
                    }
                }
                //Log.d(TAG," 总的条目:"+getAdapter().getCount()+"..当前显示的第一个条目:"+getFirstVisiblePosition());
                break;
            case MotionEvent.ACTION_UP:

                if(mCurrentState==PULL_DOWN_REFRESHSTATE){
                    //如果放开时候处于下拉刷新状态，则设置隐藏
                    mViewHeader.setPadding(0,-mRefreshHeadHeight,0,0);
                }else if(mCurrentState==PULL_UP_REFRESHSTATE){
                    //如果是当前设置是松开刷新，则设置状态为正在刷新，并且只显示一定的宽高
                    mCurrentState=PULL_RUNING_REFRESHSTATE;
                    //更新为正在刷新的UI
                    updateUI();
                    mViewHeader.setPadding(0,0,0,0);
                }
                break;
            default:
                break;
        }
        return super.onTouchEvent(ev);
    }


    //设置更新UI
    private void updateUI()
    {
        switch (mCurrentState)
        {
            case PULL_DOWN_REFRESHSTATE:
                //当处于下拉刷新时候
                mUpdate.setText("下拉刷新");

                //隐藏进度
                mProgress.setVisibility(View.GONE);
                //显示箭头
                mIvArr.setVisibility(View.VISIBLE);
                //做动画
                RotateAnimation downAnimation=new RotateAnimation(180,360,
                        Animation.RELATIVE_TO_SELF,0.5f,
                        Animation.RELATIVE_TO_SELF,0.5f
                );
                downAnimation.setDuration(500);
                downAnimation.setFillAfter(true);
                //开始动画
                mIvArr.startAnimation(downAnimation);
                break;
            case PULL_UP_REFRESHSTATE:
                //当处于松开刷新时候
                mUpdate.setText("松开刷新");
                //隐藏进度
                mProgress.setVisibility(View.GONE);
                //显示箭头
                mIvArr.setVisibility(View.VISIBLE);
                //做动画
                //做动画
                RotateAnimation upAnimation=new RotateAnimation(0,180,
                        Animation.RELATIVE_TO_SELF,0.5f,
                        Animation.RELATIVE_TO_SELF,0.5f
                );
                upAnimation.setDuration(500);
                upAnimation.setFillAfter(true);
                //开始动画
                mIvArr.startAnimation(upAnimation);
                break;
            case PULL_RUNING_REFRESHSTATE:
                //当处于正在刷新时候
                mUpdate.setText("正在刷新");
                //清空动画
                mIvArr.clearAnimation();
                //隐藏箭头
                mIvArr.setVisibility(View.GONE);
                //显示进度
                mProgress.setVisibility(View.VISIBLE);
                break;
            default:
                break;
        }
    }
}
