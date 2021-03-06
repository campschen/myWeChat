package com.camps.frame.widgets.recyclerview;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import com.camps.frame.utils.DisplayUtils;

import androidx.recyclerview.widget.RecyclerView;
import butterknife.ButterKnife;

/**
 * 自定义的ViewHolder
 */
public abstract class TGRecyclerViewHolder<T>
{
    private Context context;

    /**
     * 搭配使用的Adapter
     */
    private TGRecyclerViewAdapter adapter;

    protected RecyclerView parent;

    /**
     * 列表行视图
     */
    protected View convertView;

    /**
     * Adapter真正使用的ViewHolder
     */
    private TGRecyclerViewAdapter.InternalRecyclerViewHolder<T> holder;

    /**
     * 列表行点击事件
     */
    private TGRecyclerView.OnItemClickListener onItemClickListener;

    /**
     * 默认设置的列表行点击事件
     */
    private InternalOnClickListener internalOnClickListener;

    /**
     * 是否已被回收
     */
    private boolean recycled = true;


    public TGRecyclerViewHolder()
    {
    }

    /**
     * 初始化列表行视图
     * @return 需要返回convertView
     */
    public View initView(ViewGroup parent, int viewType)
    {
        View convertView = LayoutInflater.from(getContext()).inflate(getLayoutId(), parent, false);
        ButterKnife.bind(this, convertView);

        return convertView;
    }

    /**
     * 获取LayoutId
     * @return
     */
    protected abstract int getLayoutId();

    protected void afterInitView(int viewType)
    {

    }

    /**
     * 更新列表行的尺寸
     * @param itemData
     * @param position
     */
    public void updateViewDimension(T itemData, int position, int viewType)
    {

    }

    /**
     * 填充数据
     * @param itemData
     * @param position
     */
    public abstract void fillData(T itemData, int position, int viewType);

    /**
     * 获取列表行ViewType
     * @param position
     * @return
     */
    public int getItemViewType(int position)
    {
        return TGRecyclerViewAdapter.NONE_VIEW_TYPE;
    }

    /**
     * 设置列表行点击事件
     * @param onItemClickListener
     */
    void setOnItemClickListener(TGRecyclerView.OnItemClickListener onItemClickListener)
    {
        this.onItemClickListener = onItemClickListener;
        if(null != onItemClickListener)
        {
            this.internalOnClickListener = new InternalOnClickListener();
        }
    }

    /**
     * 获取onItemClickListener
     * @return
     */
    protected TGRecyclerView.OnItemClickListener getOnItemClickListener()
    {
        return onItemClickListener;
    }

    /**
     * 将OnItemClick事件绑定到View上
     * @param convertView
     */
    void attachOnItemClickListener(View convertView)
    {
        //防止在没有设置onItemClickListener时, internalOnClickListener覆盖ViewHolder中设置的OnClickListener
        if(null != onItemClickListener)
        {
            convertView.setOnClickListener(internalOnClickListener);
        }
    }

    /**
     * 执行列表行点击事件
     */
    protected void performOnItemClick()
    {
        if(null != onItemClickListener)
        {
            onItemClickListener.onItemClick(parent, holder.itemView, getPosition(), holder.getItemId());
        }
    }

    /**
     * 获取匹配的Adapter
     * @return
     */
    public TGRecyclerViewAdapter getAdapter()
    {
        return adapter;
    }

    /**
     * 设置匹配的Adapter
     * @param adapter
     */
    void setAdapter(TGRecyclerViewAdapter adapter)
    {
        this.adapter = adapter;
    }

    protected Context getContext()
    {
        return context;
    }

    void setContext(Context context)
    {
        this.context = context;
    }

    /**
     * 设置内部的ViewHolder
     * @param holder
     */
    void setInternalRecyclerViewHolder(TGRecyclerViewAdapter.InternalRecyclerViewHolder<T> holder)
    {
        this.holder = holder;
    }

    /**
     * 获取内部的ViewHolder
     * @return
     */
    protected TGRecyclerViewAdapter.InternalRecyclerViewHolder<T> getInternalRecyclerViewHolder()
    {
        return holder;
    }

    public void onViewAttachedToWindow()
    {

    }

    public void onViewDetachedFromWindow()
    {

    }

    /**
     * 获取当前填充的列表行的位置
     * @return
     */
    public int getPosition()
    {
        return holder.getCurrentPosition();
    }

    /**
     * 设置ViewHolder是否支持重用
     * @return
     */
    public boolean recyclable()
    {
        return true;
    }

    /**
     * 设置列表行是否独占一整行（瀑布流时生效）
     * @param position
     * @return
     */
    public boolean isFullSpan(int position)
    {
        return false;
    }

    /**
     * 使用GridLayoutManager填充数据时，控制单个Grid大小的参数，默认为1
     * @param position
     * @return
     */
    public int getSpanSize(int position)
    {
        return 1;
    }

    /**
     * 是否已被回收
     * @return
     */
    boolean isRecycled()
    {
        return recycled;
    }

    /**
     * 是否已经被回收
     * @param recycled
     */
    void setRecycled(boolean recycled)
    {
        this.recycled = recycled;
        if(this.recycled)
        {
            onRecycled();
        }
    }

    protected void onRecycled()
    {

    }

    protected int getColor(int resId)
    {
        return getContext().getResources().getColor(resId);
    }

    protected Drawable getDrawable(int resId)
    {
        return getContext().getResources().getDrawable(resId);
    }

    protected int getDimensionPixelSize(int resId)
    {
        return getContext().getResources().getDimensionPixelSize(resId);
    }

    public float getTextSize(int resId)
    {
        return DisplayUtils.px2sp(getContext(), getDimensionPixelSize(resId));
    }

    public String getString(int resId)
    {
        return context.getString(resId);
    }

    public String getString(int resId, Object... formatArgs)
    {
        return context.getString(resId,formatArgs);
    }

    /**
     * 列表行点击事件
     */
    private final class InternalOnClickListener implements View.OnClickListener
    {
        @Override
        public void onClick(View v)
        {
            performOnItemClick();
        }
    }
}
