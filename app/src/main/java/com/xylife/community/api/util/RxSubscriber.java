package com.xylife.community.api.util;

import com.android.framewok.util.DialogHelper;

import io.reactivex.subscribers.DefaultSubscriber;

/**
 * Created by Lzx on 2016/5/27.
 */

public abstract class RxSubscriber<T> extends DefaultSubscriber<T> {

    @Override
    public void onComplete() {
        DialogHelper.stopProgressDlg();
    }

    @Override
    public void onError(Throwable e) {
        e.printStackTrace();
        _onError(e.toString());

    }

    @Override
    public void onNext(T t) {
        _onNext(t);
    }

    public abstract void _onNext(T t);

    public abstract void _onError(String msg);
}
