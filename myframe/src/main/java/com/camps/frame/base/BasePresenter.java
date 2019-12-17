package com.camps.frame.base;


import java.lang.ref.WeakReference;
import java.util.HashMap;

public abstract class BasePresenter<V extends IView> implements IPresenter {

    private WeakReference actReference;

    protected V iView;

    public abstract HashMap<String, IModel> getiModelMap();

    @Override
    public void attachView(IView IView) {
        actReference = new WeakReference(IView);
    }

    @Override
    public void detachView() {
        if (actReference != null) {
            actReference.clear();
            actReference = null;
        }
    }

    @Override
    public V getIView() {
        return (V) actReference.get();
    }

    /**
     * 添加多个model,如有需要
     */
    public abstract HashMap<String, IModel> loadModelMap(IModel... models);

}
