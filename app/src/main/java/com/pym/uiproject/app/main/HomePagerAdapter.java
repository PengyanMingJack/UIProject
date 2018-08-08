package com.pym.uiproject.app.main;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.List;

class HomePagerAdapter extends FragmentStatePagerAdapter {
    List<NewTitle.ShowapiResBodyBean.ChannelListBean> titleListBeans;
    HomePagerAdapter(FragmentManager fm,List<NewTitle.ShowapiResBodyBean.ChannelListBean> titleListBeans) {
        super(fm);
        this.titleListBeans=titleListBeans;
    }

    @Override
    public Fragment getItem(int position) {
        return FirstFragment.newInstance(titleListBeans.get(position).getName());
    }

    @Override
    public int getCount() {
        return titleListBeans.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return  titleListBeans.get(position).getName();
    }
}
