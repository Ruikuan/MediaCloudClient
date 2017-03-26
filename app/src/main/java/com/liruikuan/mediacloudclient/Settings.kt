package com.liruikuan.mediacloudclient

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_settings.*

class Settings : AppCompatActivity() {

    private var configManager = ConfigManager(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings)
        var server = configManager.loadServer()
        if (server == null) {
            server = ""
        }
        edit_server.text.insert(0, server)
    }

    fun saveServer(view: View) {
        configManager.saveServer(edit_server.text.toString())
        finish()
    }
}
