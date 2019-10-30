package com.limpatprananda.plugin.coroutines

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val ioScope = CoroutineScope(Dispatchers.IO)

        CoroutineScope(Dispatchers.Default).launch {
            val result1 = ioScope.async { get1Result() }
            val resul2 = ioScope.async { get2Result() }

            println("Log Result : " + result1 + " " + resul2)
            println("Log Result.await() : " + result1.await() + " " + resul2.await())
        }
        println("Log #3 Thread: " + Thread.currentThread().name)
    }

    private suspend fun get1Result(): String{
        println("Log #1 Thread: " + Thread.currentThread().name)
        delay(3000)
        return "Result #1"
    }

    private suspend fun get2Result(): String{
        println("Log #2 Thread: " + Thread.currentThread().name)
        delay(2000)
        return "Result #2"
    }
}
