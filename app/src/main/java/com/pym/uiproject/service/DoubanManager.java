package com.pym.uiproject.service;

import android.support.v4.app.Fragment;

import com.pym.uiproject.app.main.NewHomeInfo;
import com.pym.uiproject.app.message.News;
import com.pym.uiproject.app.message.Result;
import com.pym.uiproject.app.playvideo.VideoLiveList;
import com.pym.uiproject.app.playvideo.VideoLiveTable;
import com.pym.uiproject.service.base.BaseManager;
import com.pym.uiproject.service.base.DataLayer;

import io.reactivex.Observable;

/**
 * @author Peng YanMing 2017/4/28
 */
public class DoubanManager extends BaseManager implements DataLayer.DoubanService {

    @Override
    public Observable<NewHomeInfo> getNewHomeList(Fragment fragment) {
        return getApi().getVideoListInfo("41657683759","wifi","vivo","32","video_article","664","6.6.4","android","a","vivo+X20A","vivo", "zh","25","7.1.1","867649036961114","d38a3a1ca6fafba1","264",1080*2160,"480","66404","1533708507711","Funtouch+OS_3.2_PD1709_A_1.14.3");
    }

    @Override
    public Observable<VideoLiveTable> getVideoLiveTable(Fragment fragment) {
        return getApi().getVideoliveTable();
    }

    @Override
    public Observable<VideoLiveList> getVideoLiveList(Fragment fragment, int id,int page) {
        return getApi().getVideoliveList(id,page);
    }

    @Override
    public Observable<Result> getShow(Fragment fragment, String type, int page) {
//        return getApi().getDoubanShow(type, page)
//                .map(ResponseBody::string)
//                .map(Jsoup::parse)
//                .map(document -> document.select("div[class=thumbnail] div[class=img_single] img"))
//                .flatMap(Observable::fromIterable)
//                .map(element -> element.attr("src"))
//                .map(s -> Image.newImage(fragment, s));
        return null;
    }
}
