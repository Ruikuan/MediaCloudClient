package com.liruikuan.mediacloudclient

import android.content.Context
import java.io.File

/**
 * Created by liruikuan on 2017/3/25.
 */
class ConfigManager(var context: Context) {

    fun loadServer(): String? {
        if (currentServer == null) {
            currentServer = loadServerFromStore()
        }
        return currentServer
    }

    private fun loadServerFromStore(): String? {
        var file = context.getFileStreamPath(configFileName)
        if (file.isFile && file.canRead()) {
            return file.readText()
        }
        return null
    }

    fun saveServer(server: String) {
        currentServer = server
        saveServerToStore(server)
    }

    private fun saveServerToStore(server: String) {

        var file = context.getFileStreamPath(configFileName)
        file.writeText(server)
    }

    companion object {
        var currentServer: String? = null
        var configFileName: String = "server.txt"
    }
}