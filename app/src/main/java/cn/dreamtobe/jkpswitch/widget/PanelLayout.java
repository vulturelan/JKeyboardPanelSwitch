package cn.dreamtobe.jkpswitch.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

import cn.dreamtobe.jkpswitch.R;


/**
 * Created by Jacksgong on 15/6/29.
 * <p/>
 * Detail: http://blog.dreamtobe.cn/2015/06/29/keybord-panel-switch/
 */
public class PanelLayout extends BasePanelRootLayout {

    private final static String TAG = "JFrame.InputPanelLayout";

    private int mHeight;

    public PanelLayout(Context context) {
        super(context);
        init();
    }

    public PanelLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public PanelLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        mHeight = getResources().getDimensionPixelSize(R.dimen.panel_height);
    }

    @Override
    public void setVisibility(int visibility) {
        if (visibility == getVisibility()) {
            return;
        }

        if (mIsKeybordShowing) {
            //只有在键盘显示的时候才需要处理 keybord -> panel切换的布局冲突问题
            setIsNeedHeight(false);

            ViewGroup.LayoutParams l = getLayoutParams();
            l.height = 0;
            setLayoutParams(l);
        }
        super.setVisibility(visibility);

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {

        if (getVisibility() == View.VISIBLE && mIsNeedHeight) {
            // 真正需要高度的时候（是否需要高度由是否是键盘触发布局真正要发生变化时告知 & visible）。
            ViewGroup.LayoutParams l = getLayoutParams();
            setVisibility(View.VISIBLE);
            l.height = mHeight;
            heightMeasureSpec = MeasureSpec.makeMeasureSpec(mHeight, MeasureSpec.EXACTLY);
        }

        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }


    private boolean mIsKeybordShowing = false;
    public void setIsKeybordShowing(final boolean isKeybordShowing) {
        this.mIsKeybordShowing = isKeybordShowing;
    }

    private boolean mIsNeedHeight = true;
    public void setIsNeedHeight(final boolean isNeedheight) {
        this.mIsNeedHeight = isNeedheight;
    }

    @Override
    public void setLayoutParams(ViewGroup.LayoutParams params) {
        super.setLayoutParams(params);
        Log.d(TAG, "get new height: " + params.height);
    }
}