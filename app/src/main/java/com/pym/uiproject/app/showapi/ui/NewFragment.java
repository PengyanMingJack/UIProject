package com.pym.uiproject.app.showapi.ui;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;

import com.google.gson.Gson;
import com.pym.uiproject.R;
import com.pym.uiproject.app.message.Result;
import com.pym.uiproject.app.showapi.ShowApiRequest;
import com.pym.uiproject.base.BindingFragment;
import com.pym.uiproject.databinding.FragNewMainBinding;
import com.pym.uiproject.widget.AutoLoadRecyclerView;

import java.util.ArrayList;
import java.util.List;

/**
 * Peng YanMing on 2018\8\3 0003
 */
public class NewFragment extends BindingFragment<FragNewMainBinding> {

    private List<NewBean.ShowapiResBodyBean.PagebeanBean.ContentlistBean> results;
    BigImageAdapter bigImageAdapter;

    @Override
    protected int getLayoutId() {
        return R.layout.frag_new_main;
    }

    @Override
    protected void afterCreate(@Nullable Bundle savedInstanceState) {
        results = new ArrayList<>();
        bigImageAdapter = new BigImageAdapter(results);
        binding.recycler.setAdapter(bigImageAdapter);
        new Thread(runnable).start();
        binding.recycler.setOnLoadListener(new AutoLoadRecyclerView.OnLoadListener() {
            @Override
            public void onLoad() {
                new Thread(runnable).start();
            }
        });
        binding.refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                new Thread(runnable).start();
            }
        });

    }

    @SuppressLint("HandlerLeak")
    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            NewBean newBean = (NewBean) msg.obj;
            List<NewBean.ShowapiResBodyBean.PagebeanBean.ContentlistBean> contentlist = newBean.getShowapi_res_body().getPagebean().getContentlist();
            results.clear();
            results.addAll(contentlist);
            bigImageAdapter.notifyDataSetChanged();
            Log.i("请求结果", "请求结果:" + newBean.toString());
            binding.refreshLayout.setRefreshing(false);
            binding.recycler.loading = false;
        }
    };
    Runnable runnable = new Runnable() {
        @Override
        public void run() {
            final String res = new ShowApiRequest("http://route.showapi.com/109-35", "71528", "60e603bd86014b2c85c4873bbea7e200")
                    .addTextPara("channelId", "")
                    .addTextPara("channelName", "")
                    .addTextPara("title", "电影最新")
                    .addTextPara("page", "1")
                    .addTextPara("needContent", "0")
                    .addTextPara("needHtml", "0")
                    .addTextPara("needAllList", "0")
                    .addTextPara("maxResult", "100")
                    .addTextPara("id", "")
                    .post();
            Gson gson = new Gson();
            NewBean newBean = gson.fromJson(res, NewBean.class);
            Message msg = new Message();
            msg.obj = newBean;
            handler.sendMessage(msg);
        }
    };
}