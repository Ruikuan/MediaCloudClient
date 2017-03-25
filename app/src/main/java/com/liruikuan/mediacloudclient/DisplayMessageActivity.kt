package com.liruikuan.mediacloudclient

import android.os.Bundle
import android.support.constraint.ConstraintLayout
import android.support.v7.app.AppCompatActivity
import android.widget.TextView

class DisplayMessageActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_display_message)
        val message = intent.getStringExtra(MainActivity.EXTRA_MESSAGE)
        val textView = TextView(this)
        textView.textSize = 40f
        textView.text = message
        val layout = findViewById(R.id.content) as ConstraintLayout?
        layout!!.addView(textView)
    }
}
