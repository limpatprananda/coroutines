package com.limpatprananda.plugin.coroutines

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val job = Job()
        CoroutineScope(Dispatchers.Default).launch {
            CoroutineScope(Dispatchers.Default + job).launch {
                repeat(10){
                    val result1 = get1Result()
                    val resul2 = get2Result()
                    println("Log Result : " + result1 + " " + resul2)
                }
            }
            delay(6000)
            job.cancel()
            println("Log : Break role")
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
