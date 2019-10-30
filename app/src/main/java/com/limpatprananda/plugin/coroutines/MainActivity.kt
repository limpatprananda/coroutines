package com.limpatprananda.plugin.coroutines

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        CoroutineScope(Dispatchers.Default).launch {
            println("Log #1 Thread: " + Thread.currentThread().name)
            delay(3000)
            println("Log #2 Thread: " + Thread.currentThread().name)
        }
        println("Log #3 Thread: " + Thread.currentThread().name)
    }
}
