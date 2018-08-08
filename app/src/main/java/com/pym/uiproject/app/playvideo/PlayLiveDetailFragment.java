package com.pym.uiproject.app.playvideo;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.CoordinatorLayout;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;

import com.kk.taurus.playerbase.entity.DataSource;
import com.pym.uiproject.R;
import com.pym.uiproject.base.BindingFragment;
import com.pym.uiproject.databinding.FragPlayLiveDetailBinding;
import com.pym.uiproject.util.DisplayUtil;
import com.pym.uiproject.util.ImageLoader;
import com.pym.uiproject.util.play.AssistPlayer;

/**
 * Peng YanMing on 2018\8\7 0007
 */
public class PlayLiveDetailFragment extends BindingFragment<FragPlayLiveDetailBinding> {

    private static  VideoLiveList.HomeDivsBean.HomePartitonBean.RoomInfoBean infoBean;

    public static PlayLiveDetailFragment newInstance(Bundle bundle) {
        infoBean = (VideoLiveList.HomeDivsBean.HomePartitonBean.RoomInfoBean) bundle.getSerializable("info");
        PlayLiveDetailFragment detailFragment = new PlayLiveDetailFragment();
        return detailFragment;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.frag_play_live_detail;
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
    }

    @Override
    public void onSupportVisible() {
        super.onSupportVisible();
    }

    @Override
    public void onPause() {
        super.onPause();
        AssistPlayer.get().pause();
    }

    @Override
    public void onResume() {
        super.onResume();
        AssistPlayer.get().resume();
    }

    @Override
    protected void afterCreate(@Nullable Bundle savedInstanceState) {
        DataSource dataSource = new DataSource(infoBean.getLive_info().getStream_url().getPullUrl().getFULL_HD1().getHls());
        AssistPlayer.get().play(binding.frameVideo, dataSource);
        if(infoBean.getLive_info().getOrientation()==0){
            CoordinatorLayout.LayoutParams layoutParams = new CoordinatorLayout.LayoutParams(CoordinatorLayout.LayoutParams.MATCH_PARENT, CoordinatorLayout.LayoutParams.MATCH_PARENT);
            binding.frameVideo.setLayoutParams(layoutParams);
            ImageLoader.loadCircleImage(binding.ivHeadImage,infoBean.getUser_info().getAvatar_url());
            binding.tvName.setText(infoBean.getUser_info().getName());
            binding.tvFans.setText(infoBean.getUser_info().getFans_count()+"粉丝");

        }else{
            CoordinatorLayout.LayoutParams layoutParams = new CoordinatorLayout.LayoutParams(CoordinatorLayout.LayoutParams.MATCH_PARENT, DisplayUtil.dp2px(220));
            binding.frameVideo.setLayoutParams(layoutParams);
        }

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        AssistPlayer.get().destroy();
    }
}
