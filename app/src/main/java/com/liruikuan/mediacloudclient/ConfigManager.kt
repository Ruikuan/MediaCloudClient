package com.liruikuan.mediacloudclient

import android.support.annotation.NonNull

/**
 * Created by liruikuan on 2017/3/25.
 */
class configManager {
    //todo: not set a magic string for test. need set to null.
    private var currentServer: String? = "http://localhost:8000"

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
        if (server == null) throw NullPointerException()
        currentServer = server
        SaveServerToStore(server)
    }

    private fun SaveServerToStore(server: String) {
        throw NotImplementedError("not finish yet")
    }
}