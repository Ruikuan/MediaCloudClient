package com.liruikuan.mediacloudclient

import android.provider.MediaStore
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

/**
 * Created by liruikuan on 2017/3/26.
 */
interface FileFetchService {
    @GET("{path}?json")
    fun getFiles(@Path("path") path: String): Call<List<MediaFileInfo>>
}