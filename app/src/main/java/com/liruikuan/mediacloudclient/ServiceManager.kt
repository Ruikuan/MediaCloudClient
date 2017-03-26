package com.liruikuan.mediacloudclient

/**
 * Created by liruikuan on 2017/3/25.
 */

class ServiceManager {
    fun getFiles(path: String): List<MediaFileInfo> {
        if(path == "/") {
            var list = mutableListOf<MediaFileInfo>()
            list.add(MediaFileInfo("movie", true, 0L, "/movie/movie"))
            list.add(MediaFileInfo("XE16OnTV_high.mp4", false, 600L, "/movie/XE16OnTV_high.mp4"))
            return list
        }else
        {
            var list = mutableListOf<MediaFileInfo>()
            list.add(MediaFileInfo("movie", true, 0L, "/movie/movie"))
            list.add(MediaFileInfo("XE16OnTV_high.mp4", false, 600L, "/movie/XE16OnTV_high.mp4"))
            list.add(MediaFileInfo("孤独のグルメ EP01 720p x264 AAC-NGB.mkv", false, 600L, "/movie/孤独のグルメ EP01 720p x264 AAC-NGB.mkv"))
            return list
        }
    }
}