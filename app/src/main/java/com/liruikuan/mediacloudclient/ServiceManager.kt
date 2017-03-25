package com.liruikuan.mediacloudclient

/**
 * Created by liruikuan on 2017/3/25.
 */

class ServiceManager {
    fun getFiles(path: String): List<MediaFileInfo> {
        var list = listOf<MediaFileInfo>()
        list.plus(MediaFileInfo("movie",true,0L,"/movie/movie"))
        list.plus(MediaFileInfo("XE16OnTV_high.mp4",false,600L,"/movie/XE16OnTV_high.mp4"))
        list.plus(MediaFileInfo("孤独のグルメ EP01 720p x264 AAC-NGB.mkv",false,600L,"/movie/孤独のグルメ EP01 720p x264 AAC-NGB.mkv"))
        return list
    }
}