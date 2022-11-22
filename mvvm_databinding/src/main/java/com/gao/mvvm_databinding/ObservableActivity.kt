package com.gao.mvvm_databinding

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.Observable
import androidx.databinding.Observable.OnPropertyChangedCallback
import androidx.databinding.ObservableField
import androidx.lifecycle.MutableLiveData

class ObservableActivity : AppCompatActivity() {

    private var observableField = ObservableField(0)

    private var mutableLiveData = MutableLiveData(0)


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_observerble)

        observableField.addOnPropertyChangedCallback(object : OnPropertyChangedCallback() {
            override fun onPropertyChanged(sender: Observable?, propertyId: Int) {
                println("observableField = ${observableField.get()}")
            }
        })

        mutableLiveData.observe(this){
            println("mutableLiveData = ${mutableLiveData.value}")
        }


        Thread({
            while (true){
                Thread.sleep(1000)
                observableField.set(observableField.get()!! + 1)
                mutableLiveData.postValue(mutableLiveData.value!! + 1)
            }
        }).start()

    }
}