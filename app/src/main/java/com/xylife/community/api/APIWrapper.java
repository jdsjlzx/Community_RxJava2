package com.xylife.community.api;


import com.xylife.community.api.util.RetrofitUtil;
import com.xylife.community.bean.Advertisement;
import com.xylife.community.bean.Exercise;
import com.xylife.community.bean.JavaResponse;
import com.xylife.community.bean.Response;
import com.xylife.community.utils.Constant;

import java.util.List;

import io.reactivex.Flowable;
import io.reactivex.Observable;

public class APIWrapper extends RetrofitUtil{

    private static APIWrapper mAPIWrapper;

    public APIWrapper(){
    }

    public static APIWrapper getInstance(){
        if(mAPIWrapper == null) {
            mAPIWrapper = new APIWrapper();
        }
        return mAPIWrapper;
    }

    public Flowable<Response<List<Exercise>>> queryLookUp(String keyword, int page) {
        Flowable<Response<List<Exercise>>> observable = getAPIService().getFamousResult(Constant.APIKEY, keyword, page, 10);
        return observable;
    }


    public Observable<JavaResponse<List<Advertisement>>> getBannerImages() {
        Observable<JavaResponse<List<Advertisement>>> observable = getAPIService().getBannerImages();
        return observable;
    }


}
