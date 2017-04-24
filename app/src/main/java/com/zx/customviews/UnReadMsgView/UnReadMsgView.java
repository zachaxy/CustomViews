package com.zx.customviews.UnReadMsgView;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.RadioButton;

import com.zx.customviews.R;


/**
 * Created by zhangxin on 2017/4/21 0021.
 * <p>
 * Description :仿微信底部未读消息,在右上角显示未读消息数量;
 */

public class UnReadMsgView extends RadioButton {

    private Paint mBgPaint;
    private Paint mTextPaint;
    private int mNum;

    /**
     * 需要绘制的数字大小
     * 默认大小为8sp
     */
    private float mTextSize = sp2px(8);

    /**
     * 默认背景颜色,默认为红色;
     */
    private int mBgColor = 0xffff0000;

    /**
     * 默认字体颜色,白色;
     */
    private int mTextColor = 0xffffffff;

    //圆心坐标
    private int mX = 0;
    private int mY = 0;

    public UnReadMsgView(Context context) {
        this(context, null);
    }

    public UnReadMsgView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public UnReadMsgView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    //执行初始化操作;
    private void init(Context context, AttributeSet attrs) {

        //获取xml布局文件中定义的属性,如果没有定义的话,使用第二个参数定义的默认属性;
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.UnReadMsgView);

        //文字颜色,默认白色
        mTextColor = a.getColor(R.styleable.UnReadMsgView_edge_txt_color, 0xffffffff);

        //北京颜色,默认红色
        mBgColor = a.getColor(R.styleable.UnReadMsgView_edge_bg_color, 0xffff0000);

        mTextSize = a.getDimensionPixelSize(R.styleable.UnReadMsgView_edge_txt_size, (int) sp2px(8));
        mNum = a.getInteger(R.styleable.UnReadMsgView_edge_txt_num, -1);

        //画背景圆形,设置抗锯齿
        mBgPaint = new Paint();
        mBgPaint.setAntiAlias(true);

        //绘制数字
        mTextPaint = new Paint();
        mTextPaint.setTypeface(Typeface.DEFAULT_BOLD);  //设置字体样式,这里使用的是加粗;
        mTextPaint.setAntiAlias(true);

        a.recycle();

        //必须设置这一句,否则可能会出现点击,设置的selector无效,当然如果为其设置点击事件,那么会自动改为可点击的
        setClickable(true);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        //获取圆心坐标; x在宽度的2/3处,y在高度的1/4出,大约在右上角的位置
        mX = getWidth() * 2 / 3;
        mY = getHeight() / 4;
        mTextPaint.setTextSize(mTextSize);

        //如果设置的数字>0才会绘制,否则不会绘制右上角
        if (mNum > 0) {
            mBgPaint.setColor(mBgColor);
            //感觉这里将半径设置为
            canvas.drawCircle(mX, mY, dp2px(10), mBgPaint);

            String str = null;
            if (mNum > 99) {
                str = "99+";
            } else {
                str = new String(mNum + "");
            }
            //绘制的文本
            mTextPaint.setColor(mTextColor);
            canvas.drawText(str, mX - mTextPaint.measureText(str) / 2,
                    mY + mTextPaint.getFontMetrics().bottom * 1.2f, mTextPaint);
        } else {
            //其它情况,就不绘制右上角圆心了;(绘制了一个透明的)
            mBgPaint.setColor(0x00000000);
            canvas.drawCircle(mX, mY, dp2px(10), mBgPaint);
            String str = "";
            //颜色
            mTextPaint.setColor(0x00ffffff);
            canvas.drawText(str, mX - mTextPaint.measureText(str) / 2,
                    mY + mTextPaint.getFontMetrics().bottom * 1.2f, mTextPaint);
        }
    }

    /**
     * 设置提醒数字
     *
     * @param mNum
     */
    public void setmNum(int mNum) {
        this.mNum = mNum;
        invalidate();
    }

    /**
     * 清除提醒数字
     */
    public void clearNum() {
        this.mNum = -1;
        invalidate();
    }

    /**
     * 设置数字颜色
     *
     * @param textColor
     */
    public void setTextColor(int textColor) {
        this.mTextColor = textColor;
    }

    /**
     * 设置数字背景
     *
     * @param bgColor
     */
    public void setBgColor(int bgColor) {
        this.mBgColor = bgColor;
    }

    /**
     * 设置提示数字大小，单位sp
     *
     * @param textSize
     */
    public void setTextSize(int textSize) {
        this.mTextSize = textSize;
    }

    /**
     * 将dp转为px
     *
     * @param dp
     * @return
     */
    private int dp2px(int dp) {
        int ret = 0;
        ret = (int) (dp * getContext().getResources().getDisplayMetrics().density);
        return ret;
    }

    /**
     * 将sp值转换为px值
     *
     * @param sp
     * @return
     */
    private float sp2px(int sp) {
        float fontScale = getContext().getResources().getDisplayMetrics().scaledDensity;
        return (int) (sp * fontScale + 0.5f);
    }
}

