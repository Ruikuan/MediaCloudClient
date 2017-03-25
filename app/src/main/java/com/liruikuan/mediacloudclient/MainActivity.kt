package com.liruikuan.mediacloudclient

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.widget.EditText
import android.widget.SimpleAdapter
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val path = intent.getStringExtra(MainActivity.EXTRA_PATH)
        listPathContents(path)
    }

    fun sendMessage(view: View) {
        val intent = Intent(this, DisplayMessageActivity::class.java)
        val message = "test"
        intent.putExtra(EXTRA_MESSAGE, message)
        startActivity(intent)
    }

    private fun navigateTo(path: String) {
        var intent = Intent(this, MainActivity::class.java)
        intent.putExtra(EXTRA_PATH, path)
        startActivity(intent)
    }

    private fun getIcon(fileInfo: MediaFileInfo): Int {
        if (fileInfo.isDirectory) return R.drawable.folder
        var lowName = fileInfo.name.toLowerCase()
        if (mediaFileTypeList.any { lowName.endsWith(it) }) {
            return R.drawable.movie
        } else {
            return R.drawable.file
        }
    }

    private var fileMap: Map<String, MediaFileInfo>? = null

    private fun listPathContents(path: String?) {
        var pathToList = "/"
        if (path != null) {
            pathToList = path
        }
        var fileList = ServiceManager().getFiles(pathToList)
        fileMap = fileList.associateBy({ it.name }, { it })
        var source = fileList.map { hashMapOf("name" to it.name, "icon" to getIcon(it)) }
        var adapter = SimpleAdapter(applicationContext, source, R.layout.file_item, arrayOf("name", "icon"), arrayOf(R.id.file_icon, R.id.file_name).toIntArray())
        main_view.adapter = adapter
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // 为ActionBar扩展菜单项
        val inflater = menuInflater
        inflater.inflate(R.menu.main_activity_actions, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // 处理动作按钮的点击事件
        when (item.itemId) {
            R.id.action_search -> {
                //openSearch();
                setText("search")
                return true
            }
            R.id.action_settings -> {
                //openSettings();
                setText("setting")
                return true
            }
            else -> return super.onOptionsItemSelected(item)
        }
    }

    private fun setText(text: String) {

    }

    companion object {
        val EXTRA_MESSAGE = "com.liruikuan.mediaCloudClient.MESSAGE"
        var EXTRA_PATH = "com.liruikuan.mediaCloudClient.PATH"
        var mediaFileTypeList = listOf(".mp4", ".mkv", ".flv", ".mov", ".wmv", ".asf")
    }
}
