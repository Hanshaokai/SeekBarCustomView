package com.example.lxr_yfb.seekbarcustomview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by hanshaokai on 2017/3/27 18:09
 */
public class SlidingBlockView extends View {
    private float mWidth;// view 的宽
    private float mHeight;//view 的高
    private Paint mBorderPaint;//外滑块
    private Paint mProgressPaint;//内滑块
    private float moveX = 0;//滑动长度
    private int mountFlip = 3;//总长度 的总共 滑块长度数量
    private int mdefaultSelecItemsTotal = 1;
    private float xToLeftView = 0.03f; //倍距

    private float yToTopView = 0.2f;
    private float mInerWidth;
    private SelecteItemTotalNumLisnter mlistener;
    private Paint mSidePaint; //外滑块边沿
    private Paint mDecrPaint;

    public interface SelecteItemTotalNumLisnter {
        void getSelectItemTotalNum(int totalNum);
    }

    public void setSelecteItemTotalNumLisnter(int mountItemsTotal, int defaultSelecItemsTotal, SelecteItemTotalNumLisnter listener) {
        mountFlip = mountItemsTotal;
        mlistener = listener;
        mdefaultSelecItemsTotal = defaultSelecItemsTotal;
        // 有几个数值就把x轴分成几份
        Log.d("tag", mWidth + "initData mWidth");
        initData();
        invalidate();
    }

   /* public void setSelecteItemTotalNumLisnter(SelecteItemTotalNumLisnter listener) {
        this.mlistener = listener;

    }*/

    private void initData() {
        Log.d("tag", mWidth + "initData mWidth");
        mInerWidth = mWidth * (1 - 2 * xToLeftView) / mountFlip;
        Log.d("tag", mInerWidth + "mInerWidth");
        moveX = (mdefaultSelecItemsTotal - 1) * mInerWidth + xToLeftView * mWidth;
    }

    public SlidingBlockView(Context context) {
        super(context);
        initView();
    }

    public SlidingBlockView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView();
    }

    public SlidingBlockView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();

    }

    private void initView() {


        mProgressPaint = new Paint();
        mProgressPaint.setColor(0x55A9A9A9);
        mProgressPaint.setStrokeWidth(1);
        mProgressPaint.setStyle(Paint.Style.FILL);
        mProgressPaint.setAntiAlias(true);

        mBorderPaint = new Paint();
        mBorderPaint.setStrokeWidth(1);
        mBorderPaint.setColor(0x33D3D3D3);
        mBorderPaint.setStyle(Paint.Style.FILL);
        mBorderPaint.setAntiAlias(true);


        mDecrPaint = new Paint();
        mDecrPaint.setStrokeWidth(1);
        mDecrPaint.setColor(Color.GRAY);
        mDecrPaint.setStyle(Paint.Style.STROKE);
        mDecrPaint.setAntiAlias(true);
        mSidePaint = new Paint();
        mSidePaint.setStrokeWidth(1);
        mSidePaint.setColor(Color.LTGRAY);
        mSidePaint.setStyle(Paint.Style.STROKE);
        mSidePaint.setAntiAlias(true);
    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int modeH = MeasureSpec.getMode(heightMeasureSpec);
        int modeW = MeasureSpec.getMode(widthMeasureSpec);

        int sizeW = MeasureSpec.getSize(widthMeasureSpec);
        int sizeH = MeasureSpec.getSize(heightMeasureSpec);

        /*switch (modeH) {
            case MeasureSpec.UNSPECIFIED: //如果没有指定大小，就设置为默认大小
                break;
            case MeasureSpec.AT_MOST: //如果测量模式是最大取值为size，wrap_content
                //我们将大小取最大值,你也可以取其他值
                break;
            case MeasureSpec.EXACTLY: //如果是固定的大小，和默认大小比较，取最大值。match_parent or 固定大小
                break;
        }*/
        setMeasuredDimension(sizeW, sizeH);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Log.d("tag", mWidth + "mWidth");

        Log.d("tag", moveX + "moveX");
        //描边
        canvas.drawRect(xToLeftView * mWidth, yToTopView * mHeight, mWidth * (1 - xToLeftView), mHeight * (1 - yToTopView), mSidePaint);
        //填充透明灰
        canvas.drawRect(xToLeftView * mWidth, yToTopView * mHeight, mWidth * (1 - xToLeftView), mHeight * (1 - yToTopView), mBorderPaint);
        //画内滑块
        canvas.drawRect(moveX - mInerWidth / 2, yToTopView * mHeight, mInerWidth + moveX - mInerWidth / 2, mHeight * (1 - yToTopView), mProgressPaint);
        //画内滑块两边标识 左边
        canvas.drawRect(moveX - mInerWidth / 2, yToTopView * mHeight + 25, 5 + moveX - mInerWidth / 2, mHeight * (1 - yToTopView) - 25, mDecrPaint);
        //横线
        canvas.drawLine(moveX - mInerWidth / 2, ((yToTopView * mHeight + 18) + (mHeight * (1 - yToTopView) - 18)) / 2,
                5 + moveX - mInerWidth / 2, ((yToTopView * mHeight + 18) + (mHeight * (1 - yToTopView) - 18)) / 2, mDecrPaint);
        //竖线
        canvas.drawLine((moveX - mInerWidth / 2 + 5 + moveX - mInerWidth / 2) / 2, yToTopView * mHeight + 15
                , (moveX - mInerWidth / 2 + 5 + moveX - mInerWidth / 2) / 2, mHeight * (1 - yToTopView) - 15, mDecrPaint);
        //画内滑块两边标识 右边
        canvas.drawRect(moveX - mInerWidth / 2 + mInerWidth - 5, yToTopView * mHeight + 25, moveX - mInerWidth / 2 + mInerWidth, mHeight * (1 - yToTopView) - 25, mDecrPaint);
        //横线
        canvas.drawLine(moveX - mInerWidth / 2 + mInerWidth - 5, ((yToTopView * mHeight + 18) + (mHeight * (1 - yToTopView) - 18)) / 2,
                moveX - mInerWidth / 2 + mInerWidth, ((yToTopView * mHeight + 18) + (mHeight * (1 - yToTopView) - 18)) / 2, mDecrPaint);
        //竖线
        canvas.drawLine((moveX - mInerWidth / 2 + moveX - mInerWidth / 2 - 5.5f) / 2 + mInerWidth, yToTopView * mHeight + 15
                , (moveX - mInerWidth / 2 + moveX - mInerWidth / 2 - 5.5f) / 2 + mInerWidth, mHeight * (1 - yToTopView) - 15, mDecrPaint);
        Log.d("tag", mWidth + "2mWidth");
        Log.d("tag", mHeight + "2mHeight");
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        Log.d("onSizeChanged", "测量" + w + "  " + h);
        mWidth = w;
        mHeight = h;
        initData();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        getParent().requestDisallowInterceptTouchEvent(true);
        float x = event.getX();
        float y = event.getY();
//        Log.i(TAG, "onTouchEvent: x：" + x);
        switch (event.getAction()) {
            case MotionEvent.ACTION_MOVE:
                //获取滑动 x坐标
                if ((xToLeftView * mWidth + mInerWidth / 2 < x && x < (1 - xToLeftView) * mWidth - mInerWidth + mInerWidth / 2)) {

                    for (int i = 0; i < mountFlip; i++) {
                        if (xToLeftView * mWidth + i * mInerWidth < x && x < (i + 1) * mInerWidth + xToLeftView * mWidth) {
                            //找到所在区间 回调位置
                            moveX = x;
                            Log.d("tag", "i" + i);
                            mlistener.getSelectItemTotalNum(i + 1);
                            invalidate();
                            return true;
                        }
                    }
                }
                return true;
            case MotionEvent.ACTION_DOWN:

                return true;
            case MotionEvent.ACTION_UP:

                return true;
            default:
                break;
        }
        return super.onTouchEvent(event);
    }
}
