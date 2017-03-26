package com.liruikuan.mediacloudclient

import android.content.Intent
import android.net.Uri
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.SimpleAdapter
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.experimental.android.UI
import kotlinx.coroutines.experimental.launch


class MainActivity : AppCompatActivity() {
    private var configManager = ConfigManager(this)
    private var serviceManager = ServiceManager(configManager)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onResume() {
        super.onResume()
        if (configManager.getServer().isNullOrBlank()) {
            openSettings()
            return
        }
        val path = intent.getStringExtra(MainActivity.EXTRA_PATH)
        listPathContents(path)
    }

    private fun getIcon(fileInfo: MediaFileInfo): Int {
        if (fileInfo.isDirectory) return R.drawable.folder
        if (isMediaFile(fileInfo.name)) {
            return R.drawable.movie
        } else {
            return R.drawable.file
        }
    }

    private fun isMediaFile(fileName: String): Boolean {
        val lowerName = fileName.toLowerCase()
        return mediaFileTypeList.any { lowerName.endsWith(it) }
    }

    private var m_fileList: List<MediaFileInfo>? = null

    private fun listPathContents(path: String?) {
        if (m_fileList != null) return
        
        var pathToList = "/"
        if (path != null) {
            pathToList = path
        }
        launch(UI) {
            try {
                val call = serviceManager.getFiles(pathToList)
                val fileList = call.await()
                m_fileList = fileList
                val source = fileList.map { hashMapOf("name" to it.name, "icon" to getIcon(it)) }
                val adapter = SimpleAdapter(applicationContext, source, R.layout.file_item, arrayOf("name", "icon"), arrayOf(R.id.file_name, R.id.file_icon).toIntArray())
                main_view.adapter = adapter
                main_view.setOnItemClickListener { _, _, position, _ ->
                    run {
                        val fileInfo = m_fileList!![position]
                        if (fileInfo.isDirectory) {
                            navigateTo(fileInfo.fileUrl)
                        } else {
                            if (isMediaFile(fileInfo.name)) {
                                playMedia(fileInfo.fileUrl)
                            }
                        }
                    }
                }
            } catch (ex: Exception) {
                // just don't do anything.
            }
        }
    }

    private fun navigateTo(path: String) {
        val intent = Intent(this, MainActivity::class.java)
        intent.putExtra(EXTRA_PATH, path)
        startActivity(intent)
    }

    private fun playMedia(url: String) {
        val fullUrl = getFullPath(url)
        val intent = Intent(Intent.ACTION_VIEW)
        intent.setDataAndType(Uri.parse(fullUrl), "video/*")
        startActivity(intent)
    }

    private fun getFullPath(url: String): String
            = "${configManager.getServer()}$url"

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.main_activity_actions, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // 处理动作按钮的点击事件
        when (item.itemId) {
            R.id.action_settings -> {
                openSettings()
                return true
            }
            else -> return super.onOptionsItemSelected(item)
        }
    }

    private fun openSettings() {
        val intent = Intent(this, Settings::class.java)
        startActivity(intent)
    }

    companion object {
        var EXTRA_PATH = "com.liruikuan.mediaCloudClient.PATH"
        var mediaFileTypeList = listOf(".mp4", ".mkv", ".flv", ".mov", ".wmv", ".asf")
    }
}
