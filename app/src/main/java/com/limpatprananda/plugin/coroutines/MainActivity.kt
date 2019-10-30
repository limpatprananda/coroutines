package com.limpatprananda.plugin.coroutines

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.*

class MainActivity : AppCompatActivity() {

    private val RESULT_1 = "Result #1"
    private val RESULT_2 = "Result #2"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        button.setOnClickListener {

            /*IO = input output(internet or internal request), Main (UI Thread), Default (Heavy computation work)*/
            CoroutineScope(Dispatchers.IO).launch {
                fakeApiRequest()
            }
        }
    }

    private fun setNewText(input: String){
        val newText = text.text.toString() + "\n$input"
        text.text = newText
    }

    private suspend fun setTextOnMainThread(input: String){
        withContext(Dispatchers.Main){
            setNewText(input)
            logThread("setTextOnMainThread")
        }
    }

    private suspend fun fakeApiRequest(){
        val result1 = getResult1FromApi()
        setTextOnMainThread(result1)

        val result2 = getResult2FromApi(result1)
        setTextOnMainThread(result2)
    }

    private suspend fun getResult1FromApi(): String{
        logThread("getResult1FromApi")
        delay(1000)
        return RESULT_1
    }

    private suspend fun getResult2FromApi(result1: String): String{
        logThread("getResult2FromApi")
        delay(5000)
        return RESULT_2
    }

    private fun logThread(methodName: String){
        println("debug: ${methodName}: ${Thread.currentThread().name}")
    }
}
