package com.liruikuan.mediacloudclient

import android.support.annotation.NonNull

/**
 * Created by liruikuan on 2017/3/25.
 */
class ConfigManager {
    //todo: not set a magic string for test. need set to null.
    fun loadServer(): String {
        if (currentServer == null) {
            currentServer = loadServerFromStore()
        }
        return currentServer!!
    }

    private fun loadServerFromStore(): String {
        throw NotImplementedError("not finish yet")
    }

    fun saveServer(server: String) {
        currentServer = server
        saveServerToStore(server)
    }

    private fun saveServerToStore(server: String) {
        throw NotImplementedError("not finish yet")
    }

    companion object {
        var currentServer: String? = "http://localhost:8000"
    }
}