package com.xylife.community.api.util;


import com.android.framewok.util.DialogHelper;
import com.trello.rxlifecycle2.RxLifecycle;
import com.trello.rxlifecycle2.android.ActivityEvent;
import com.trello.rxlifecycle2.components.support.RxAppCompatActivity;

import org.reactivestreams.Publisher;
import org.reactivestreams.Subscription;

import io.reactivex.Flowable;
import io.reactivex.FlowableTransformer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class RxHelper<T> {
    private String mMessage;

    public RxHelper(String message){
        mMessage = message;
    }

    //子线程运行，主线程回调
    public FlowableTransformer<T, T> io_main(final RxAppCompatActivity context) {
        return new FlowableTransformer<T, T>() {

            @Override
            public Publisher<T> apply(Flowable<T> flowable) {

                flowable
                        .subscribeOn(Schedulers.io())
                        .doOnSubscribe(new Consumer<Subscription>() {
                            @Override
                            public void accept(Subscription subscription) throws Exception {
                                DialogHelper.showProgressDlg(context, mMessage);
                            }
                        })
                        .subscribeOn(AndroidSchedulers.mainThread())
                        .observeOn(AndroidSchedulers.mainThread())
                        .compose(RxLifecycle.<T, ActivityEvent>bindUntilEvent(context.lifecycle(), ActivityEvent.DESTROY));
                return null;
            }
        };
    }

/*
    public Observable.Transformer<T, T> io_main(final RxAppCompatActivity context) {
        return new Observable.Transformer<T, T>() {

            @Override
            public Observable<T> call(Observable<T> tObservable) {

                Observable<T> observable = (Observable<T>) tObservable
                        .subscribeOn(Schedulers.io())
                        .doOnSubscribe(new Action0() {
                            @Override
                            public void call() {
                                DialogHelper.showProgressDlg(context, mMessage);
                            }
                        })
                        .subscribeOn(AndroidSchedulers.mainThread())
                        .observeOn(AndroidSchedulers.mainThread())
                        .compose(RxLifecycle.bindUntilEvent(context.lifecycle(), ActivityEvent.STOP));

                return observable;

            }
        };
    }
*/

}
