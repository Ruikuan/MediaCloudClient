package com.liruikuan.mediacloudclient

import android.util.Log
import kotlinx.coroutines.experimental.CommonPool
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


/**
 * Created by liruikuan on 2017/3/25.
 */

class ServiceManager(var configManager: ConfigManager) {

    fun getFiles(path: String): Call<List<MediaFileInfo>> {
        var server = configManager.getServer()
        val retrofit = Retrofit.Builder()
                .baseUrl(server)
                .addConverterFactory(GsonConverterFactory.create())
                .build()

        val service = retrofit.create<FileFetchService>(FileFetchService::class.java!!)
        var call = service.getFiles(path)
        return call
    }
}