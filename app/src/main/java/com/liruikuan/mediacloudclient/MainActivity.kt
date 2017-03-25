package com.liruikuan.mediacloudclient

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.widget.EditText

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun sendMessage(view: View) {
        val intent = Intent(this, DisplayMessageActivity::class.java)
        val message = "test"
        intent.putExtra(EXTRA_MESSAGE, message)
        startActivity(intent)
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
        val EXTRA_MESSAGE = "com.mycompany.myfirstapp.MESSAGE"
    }
}
