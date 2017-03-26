package com.liruikuan.mediacloudclient

import android.content.Intent
import android.net.Uri
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.SimpleAdapter
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private var configManager = ConfigManager(this)
    private var serviceManager = ServiceManager()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onResume() {
        super.onResume()
        if(configManager.loadServer().isNullOrBlank())
        {
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
        var lowerName = fileName.toLowerCase()
        return mediaFileTypeList.any { lowerName.endsWith(it) }
    }

    private var m_fileList: List<MediaFileInfo>? = null

    private fun listPathContents(path: String?) {
        var pathToList = "/"
        if (path != null) {
            pathToList = path
        }
        var fullPath = getFullPath(pathToList)
        var fileList = serviceManager.getFiles(fullPath)
        m_fileList = fileList;
        var source = fileList.map { hashMapOf("name" to it.name, "icon" to getIcon(it)) }
        var adapter = SimpleAdapter(applicationContext, source, R.layout.file_item, arrayOf("name", "icon"), arrayOf(R.id.file_name, R.id.file_icon).toIntArray())
        main_view.adapter = adapter
        main_view.setOnItemClickListener { _, _, position, _ ->
            run {
                var fileInfo = m_fileList!![position]
                if (fileInfo.isDirectory) {
                    navigateTo(fileInfo.fileUrl)
                } else {
                    if (isMediaFile(fileInfo.name)) {
                        playMedia(fileInfo.fileUrl)
                    }
                }
            }
        }
    }

    private fun navigateTo(path: String) {
        var intent = Intent(this, MainActivity::class.java)
        intent.putExtra(EXTRA_PATH, path)
        startActivity(intent)
    }

    private fun playMedia(url: String) {
        var fullUrl = getFullPath(url)
        var intent = Intent(Intent.ACTION_VIEW)
        intent.setDataAndType(Uri.parse(fullUrl), "video/*")
        show_path.text = fullUrl
        startActivity(intent)
    }

    private fun getFullPath(url: String): String
            = "${configManager.loadServer()}$url"

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // 为ActionBar扩展菜单项
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
