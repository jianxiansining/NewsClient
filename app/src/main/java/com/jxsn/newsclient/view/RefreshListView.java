package com.jxsn.newsclient.view;

import android.animation.ValueAnimator;
import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.widget.AbsListView;
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
public class RefreshListView extends ListView implements AbsListView.OnScrollListener
{


    private static final String TAG = "RefreshListView";

    private int mRefreshHeadHeight;

    private int mLoadMeasuredHeight;

    private LinearLayout mViewHeader;

    private LinearLayout mViewFoot;

    private int mDownX;

    private int mDownY;

    //下拉刷新的状态
    private static final int PULL_DOWN_REFRESHSTATE = 1;

    //松开刷新的状态
    private static final int PULL_UP_REFRESHSTATE = 2;

    //正在刷新的状态
    private static final int PULL_RUNING_REFRESHSTATE = 3;

    //设置默认状态
    private static final int DEFAULT_STATE = 0;

    //设置当前状态为下拉刷新
    private int mCurrentState = DEFAULT_STATE;

    private ProgressBar mProgress;

    private ImageView mIvArr;

    private TextView mUpdate;

    private TextView mDate;

    private ProgressBar mFootProgress;

    private TextView mFootLoad;

    private OnRefreshFinishListener mRefreshListener;

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
        //初始化listView的头信息
        initHeader();
        //初始化listView的尾信息
        initFooter();
    }

    private void initFooter(){

        mViewFoot = (LinearLayout) View.inflate(getContext(), R.layout.load_foot, null);
        mFootProgress = (ProgressBar) mViewFoot.findViewById(R.id.load_foot_pb);
        mFootLoad= (TextView) mViewFoot.findViewById(R.id.load_foot_load);

        //添加尾信息
        addFooterView(mViewFoot);
        //设置按照自己本身来设置大小
        mViewFoot.measure(0,0);
        //获得测量后的高度
        mLoadMeasuredHeight = mViewFoot.getMeasuredHeight();
        //隐藏尾信息
        mViewFoot.setPadding(0, -mLoadMeasuredHeight, 0, 0);

        //设置listView监听
        setOnScrollListener(this);
    }

    private void initHeader()
    {

        mViewHeader = (LinearLayout) View.inflate(getContext(), R.layout.refresh_head, null);

        mProgress = (ProgressBar) mViewHeader.findViewById(R.id.refresh_head_pb);
        mIvArr = (ImageView) mViewHeader.findViewById(R.id.refresh_head_arr);
        mUpdate = (TextView) mViewHeader.findViewById(R.id.refresh_head_update);
        mDate = (TextView) mViewHeader.findViewById(R.id.refresh_head_date);

        //初始化时间
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        String date = format.format(new Date(System.currentTimeMillis()));
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

                //如果当前刷新头是正在刷新，则不能做下拉的动作
                if (mCurrentState == PULL_RUNING_REFRESHSTATE)
                {
                    break;
                }

                if (diffx > 0 && getFirstVisiblePosition() == 0)
                {
                    mViewHeader.setPadding(0, diffx - mRefreshHeadHeight, 0, 0);
                    //判断是否
                    if (diffx - mRefreshHeadHeight < 0 && mCurrentState != PULL_DOWN_REFRESHSTATE)
                    {
                        mCurrentState = PULL_DOWN_REFRESHSTATE;
                        //下拉刷新的情况
                        Log.d(TAG, "下拉刷新");
                        //更新UI
                        updateUI();
                    } else if (diffx - mRefreshHeadHeight >= 0 && mCurrentState != PULL_UP_REFRESHSTATE)
                    {
                        mCurrentState = PULL_UP_REFRESHSTATE;
                        //松开刷新的情况
                        Log.d(TAG, "松开刷新");
                        updateUI();
                    }
                    //因为默认返回父类的onTouch事件。它会实时去更改孩子布局，
                    // 所以这里返回true,不去返回父类的onTouch
                    return true;
                }
                //Log.d(TAG," 总的条目:"+getAdapter().getCount()+"..当前显示的第一个条目:"+getFirstVisiblePosition());
                break;
            case MotionEvent.ACTION_UP:

                if (mCurrentState == PULL_DOWN_REFRESHSTATE)
                {
                    //如果放开时候处于下拉刷新状态，则设置隐藏
                    mViewHeader.setPadding(0, -mRefreshHeadHeight, 0, 0);
                } else if (mCurrentState == PULL_UP_REFRESHSTATE)
                {
                    //如果是当前设置是松开刷新，则设置状态为正在刷新，并且只显示一定的宽高
                    mCurrentState = PULL_RUNING_REFRESHSTATE;
                    //更新为正在刷新的UI
                    updateUI();
                    //mViewHeader.setPadding(0,0,0,0);
                    //当前的paddingTop值
                    int start = mViewHeader.getPaddingTop();
                    int end = 0;
                    addHeadAnimation(start, end);
                    //设置回调方法
                    if(mRefreshListener!=null){
                        mRefreshListener.OnRefreshFinish();
                    }
                }
                break;
            default:
                break;
        }
        return super.onTouchEvent(ev);
    }


    //添加头的动画
    private void addHeadAnimation(int start, int end)
    {
        //获得value动画的对象，并设置动画参数，用于数值的变化
        ValueAnimator valueAnimator = ValueAnimator.ofInt(start, end);
        valueAnimator.setDuration(500);
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener()
        {
            @Override
            public void onAnimationUpdate(ValueAnimator animation)
            {

                int value = (int) animation.getAnimatedValue();
                mViewHeader.setPadding(0, value, 0, 0);
            }
        });
        //插入器(加速)效果
        valueAnimator.setInterpolator(new AccelerateDecelerateInterpolator());
        //开始做动画
        valueAnimator.start();
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
                RotateAnimation downAnimation = new RotateAnimation(180, 360,
                        Animation.RELATIVE_TO_SELF, 0.5f,
                        Animation.RELATIVE_TO_SELF, 0.5f
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
                RotateAnimation upAnimation = new RotateAnimation(0, 180,
                        Animation.RELATIVE_TO_SELF, 0.5f,
                        Animation.RELATIVE_TO_SELF, 0.5f
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

    public void setActionFinish(boolean isRefresh){
        if(isRefresh){
            //是下拉刷新完后的操作

            //改变状态为下拉刷新的状态
            mCurrentState=PULL_DOWN_REFRESHSTATE;
            //更新UI
            updateUI();
            //隐藏header
            mViewHeader.setPadding(0,-mRefreshHeadHeight,0,0);
            //设置更新时间
            //初始化时间
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
            String date = format.format(new Date(System.currentTimeMillis()));
            mDate.setText(date);
        }else{
            //上拉加载完成后的操作

            //隐藏加载更多的footer界面
            mViewFoot.setPadding(0,-mLoadMeasuredHeight,0,0);
        }
    }


    /**
     * 设置滑动过程中的监听，用于监听滑动到最后一位时候，并且固定时候，显示加载更多的尾信息
     * @param view
     * @param scrollState
     */
    @Override
    public void onScrollStateChanged(AbsListView view, int scrollState)
    {
        //获得当前看到最后一个条目的位置
        int position=view.getLastVisiblePosition();
        //当显示最后一个条目，并且是固定时候，显示加载显示
        if(position==getAdapter().getCount()-1 && scrollState==SCROLL_STATE_IDLE){
            //显示尾信息
            mViewFoot.setPadding(0,0,0,0);
            //选中尾信息
            this.setSelection(getAdapter().getCount());
        }

        if(mRefreshListener!=null)
        {
            mRefreshListener.OnLoadingFinish();
        }
    }


    @Override
    public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount)
    {

    }


    //接口回调的接口
    public interface OnRefreshFinishListener
    {
        void OnRefreshFinish();

        void OnLoadingFinish();
    }
    //接口回调的方法
    public void setOnRefreshFinishListener(OnRefreshFinishListener refreshListener){
        this.mRefreshListener =refreshListener;
    }
}
