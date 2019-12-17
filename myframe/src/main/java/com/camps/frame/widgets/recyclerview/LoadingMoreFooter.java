package com.camps.frame.widgets.recyclerview;

import android.content.Context;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.camps.frame.R;


public class LoadingMoreFooter extends LinearLayout
{
    private Context mContext;
    public final static int STATE_LAODING = 0;
    public final static int STATE_COMPLETE = 1;
    public final static int STATE_NOMORE = 2;
    private TextView mText;

    public LoadingMoreFooter(Context context)
    {
        super(context);
        initView(context);
    }

    /**
     * @param context
     * @param attrs
     */
    public LoadingMoreFooter(Context context, AttributeSet attrs)
    {
        super(context, attrs);
        initView(context);
    }

    public void initView(Context context)
    {
        mContext = context;
        setGravity(Gravity.CENTER);
        setLayoutParams(new ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        mText = new TextView(context);
        mText.setText(mContext.getText(R.string.tiger_listview_loading));
        mText.setTextColor(0xffB5B5B5);

        LayoutParams layoutParams = new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        layoutParams.setMargins(10, 0, 0, 0);

        mText.setLayoutParams(layoutParams);
        addView(mText);
    }


    public void setState(int state)
    {
        switch (state)
        {
            case STATE_LAODING:
                mText.setText(mContext.getText(R.string.tiger_listview_loading));
                this.setVisibility(View.VISIBLE);
                break;
            case STATE_COMPLETE:
                mText.setText(mContext.getText(R.string.tiger_listview_loading));
                this.setVisibility(View.GONE);
                break;
            case STATE_NOMORE:
                mText.setText(mContext.getText(R.string.tiger_nomore_loading));
                this.setVisibility(View.VISIBLE);
                break;
        }

    }
}
