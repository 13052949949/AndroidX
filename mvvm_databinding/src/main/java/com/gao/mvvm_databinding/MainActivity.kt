package com.gao.mvvm_databinding

import android.accounts.Account
import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.databinding.ObservableField
import com.gao.mvvm_databinding.databinding.ActivityMainBinding
import kotlin.math.absoluteValue
import kotlin.random.Random

class MainActivity : AppCompatActivity() {

    private var userName = ObservableField("gaolei")
    private var password = ObservableField("123456")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val binding =
            DataBindingUtil.setContentView<ActivityMainBinding>(this, R.layout.activity_main)

//        binding.setVariable(BR.userName,userName)
//        binding.setVariable(BR.password,password)

        binding.userName = userName
        binding.password = password

        binding.onRandomClick = View.OnClickListener {
            userName.set(randomString())
        }

        binding.onLoginClick = View.OnClickListener {
            login(binding.userName?.get(),binding.password?.get())
//            Toast.makeText(this, binding.userName, Toast.LENGTH_LONG).show()

        }

    }

    private fun login(account: String?, password: String?) {
        if ("admin" == account && "123" == password) {
            Toast.makeText(this, "登录成功", Toast.LENGTH_LONG).show()
        } else {
            Toast.makeText(this, "登录失败 ", Toast.LENGTH_LONG).show()
        }
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