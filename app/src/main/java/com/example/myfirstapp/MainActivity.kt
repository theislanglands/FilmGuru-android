package com.example.myfirstapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.EditText

// val: Read-only local variables They can be assigned a value only once. (constant)
// var: Variables that can be reassigned

const val EXTRA_MESSAGE = "com.example.myfirstapp.MESSAGE"

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun sendMessage(view: View){
        // respond to button
        val editText = findViewById<EditText>(R.id.editTextTextPersonName2);
        val message = editText.text.toString();
        Log.i("message", message);
        val intent = Intent(this, DisplayMessageActivity::class.java).apply { putExtra(EXTRA_MESSAGE, message)}
        startActivity(intent);
    }

    // FUNCTIONS TO HOOK INTO LIFECYCLE
    override fun onStart() {
        super.onStart()
        Log.i("Test", "STARTED")
    }

    override fun onResume() {
        super.onResume()
        Log.i("Test", "RESUMED")
    }

    override fun onPause() {
        super.onPause()
        Log.i("Test","PAUSE");
    }

    override fun onStop() {
        super.onStop()
        Log.i("Test", "STOP")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.i("Test", "DESTROY")
    }
}