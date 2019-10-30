package com.limpatprananda.plugin.coroutines

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        CoroutineScope(Dispatchers.Main).launch {
            val result1Api = get1Api()
            val result2Api = get2Api(result1Api)
            println("Log result: " + result2Api)
        }
        println("Log Thread: " + Thread.currentThread().name)
    }

    private suspend fun get1Api(): String{
        delay(3000)
        println("Log Thread: " + Thread.currentThread().name)
        return "getApi1"
    }

    private suspend fun get2Api(input: String): String{
        delay(2000)
        println("Log Thread: " + Thread.currentThread().name)
        return input + " -> getApi2"
    }
}
