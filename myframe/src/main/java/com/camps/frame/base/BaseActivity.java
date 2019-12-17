package com.camps.frame.base;

import android.os.Bundle;

import android.view.View;
import android.widget.Toast;


import com.camps.frame.utils.log.Logger;

import androidx.appcompat.app.AppCompatActivity;

/**
 * Activity基类
 */
public abstract class BaseActivity<P extends BasePresenter> extends AppCompatActivity implements IView {

    private static final Logger LOG = Logger.getLogger(BaseActivity.class);

    protected View view;

    protected P mPresenter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getView());
        mPresenter = loadPresenter();
        initCommonData();
        initView();
        initListener();
        initData();
    }

    protected abstract P loadPresenter();

    private void initCommonData() {
        if (mPresenter != null)
            mPresenter.attachView(this);
    }


    protected abstract void initView();

    protected abstract void initListener();

    protected abstract void initData();


    protected abstract int getLayoutId();


    /**
     * @return 显示的内容
     */
    public View getView() {
        view = View.inflate(this, getLayoutId(), null);
        return view;
    }


    /**
     * @param str 显示一个内容为str的toast
     */
    public void toast(String str) {
        Toast.makeText(this, str, Toast.LENGTH_SHORT).show();
    }

    /**
     * @param contentId 显示一个内容为contentId指定的toast
     */
    public void toast(int contentId) {
        Toast.makeText(this, contentId, Toast.LENGTH_SHORT).show();
    }


    /**
     * @param str 日志的处理
     */
    public void LogE(String str) {
        LOG.e(str);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mPresenter != null)
            mPresenter.detachView();
    }
}

