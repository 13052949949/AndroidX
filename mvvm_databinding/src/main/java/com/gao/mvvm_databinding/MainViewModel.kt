package com.gao.mvvm_databinding

import android.app.Application
import android.view.View
import android.widget.Toast
import androidx.databinding.ObservableBoolean
import androidx.databinding.ObservableField
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import kotlin.math.absoluteValue
import kotlin.random.Random

/**
 * @author：gao
 * @date：2022/11/22
 * @desc：
 **/
class MainViewModel(application: Application) : AndroidViewModel(application) {



    val userName = ObservableField("gaolei")
    val password = ObservableField("123456")
    val showPassword = ObservableBoolean(false)

    fun onLoginClick(view: View) {
        if ("admin" == userName.get() && "123" == password.get()) {
            Toast.makeText(getApplication(), "登录成功", Toast.LENGTH_LONG).show()
        } else {
            Toast.makeText(getApplication(), "登录失败 ", Toast.LENGTH_LONG).show()
        }
    }

    fun onRandomClick(view: View){
        userName.set(randomString())
    }

    fun onTogglePasswordType(view: View){
        showPassword.set(!showPassword.get())
    }

    fun onPrivacyPolicyClick(view: View){
        Toast.makeText(getApplication(),"点击了用户协议",Toast.LENGTH_LONG).show()
    }


    fun randomString(): String {
        val random = Random(System.currentTimeMillis())
        val builder = StringBuilder()
        for (i in 1..10) {
            val idx = random.nextInt().absoluteValue % 26
            builder.append('a' + idx)
        }
        return builder.toString()
    }

}