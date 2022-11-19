package com.gao.mvvm

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.OnLifecycleEvent
import androidx.lifecycle.ViewModelProvider

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        lifecycle.addObserver(object :LifecycleEventObserver{
            override fun onStateChanged(source: LifecycleOwner, event: Lifecycle.Event) {
                println(event.name)
            }
        })

        lifecycle.addObserver(object :LifecycleObserver{
            @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
            fun onCreate(){
                println("annotation:ON_CREATE")
            }
            @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
            fun onResume(){
                println("annotation:ON_RESUME")
            }
            //...
        })

//        val viewModel = ViewModelProvider(this,ViewModelProvider.NewInstanceFactory()).get(MainViewModel::class.java)
        val viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        lifecycle.addObserver(viewModel)

    }
}