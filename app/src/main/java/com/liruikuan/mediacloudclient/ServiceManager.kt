package com.liruikuan.mediacloudclient

/**
 * Created by liruikuan on 2017/3/25.
 */

class ServiceManager {
    fun getFiles(path: String): List<MediaFileInfo> {
        if(path == "/") {
            var list = mutableListOf<MediaFileInfo>()
            list.add(MediaFileInfo("movie", true, 0L, "/movie"))
            list.add(MediaFileInfo("02.mp4", false, 600L, "/tv/龙樱/02.mp4"))
            return list
        }else
        {
            var list = mutableListOf<MediaFileInfo>()
            list.add(MediaFileInfo("movie", true, 0L, "/movie/movie"))
            list.add(MediaFileInfo("02.mp4", false, 600L, "/tv/龙樱/02.mp4"))
            list.add(MediaFileInfo("03.mp4", false, 600L, "/tv/龙樱/03.mp4"))
            return list
        }
    }
}