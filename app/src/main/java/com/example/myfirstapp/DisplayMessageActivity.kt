package com.example.myfirstapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import android.content.Intent

class DisplayMessageActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_display_message)

        // gets EXTRA MESSAGE from intent
        // val message = intent.getStringExtra(EXTRA_MESSAGE);

        // sets the text in the textView to message
        //val textView = findViewById<TextView>(R.id.textView).apply { text = message }
        // textView.text = message
    }
}