package com.xylife.community.fragment;

import android.support.v7.widget.LinearLayoutManager;
import android.view.View;

import com.android.framewok.base.ListBaseAdapter;
import com.github.jdsjlzx.view.CommonHeader;
import com.xylife.community.R;
import com.xylife.community.adapter.ExerciseListAdapter;
import com.xylife.community.api.APIWrapper;
import com.xylife.community.base.BaseListFragment;
import com.xylife.community.bean.Exercise;
import com.xylife.community.bean.Response;

import java.util.List;

import io.reactivex.Flowable;


public class HomeFragment extends BaseListFragment<Exercise,List<Exercise>> {

    @Override
    public void initView(View view) {
        super.initView(view);

        headerView = new CommonHeader(getActivity(), R.layout.layout_home_header);
        mRecyclerViewAdapter.addHeaderView(headerView);
    }

    @Override
    protected ListBaseAdapter<Exercise> getListAdapter() {
        return new ExerciseListAdapter(0);
    }


    @Override
    protected void onRefresh() {

    }

    @Override
    public void initData() {
        super.initData();
    }

    @Override
    protected Flowable<Response<List<Exercise>>> sendRequestData() {
        return APIWrapper.getInstance().queryLookUp("人才",mCurrentPage);
    }

    @Override
    protected void initLayoutManager() {
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
    }


}
