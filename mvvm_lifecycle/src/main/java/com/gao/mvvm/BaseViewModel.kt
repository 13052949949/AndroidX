package com.gao.mvvm

import androidx.lifecycle.ViewModel

/**
 * @author：gao
 * @date：2022/11/19
 * @desc：
 **/
open class BaseViewModel :ViewModel(),BaseViewModelLifecycleObserver{
    override fun onCreate() {

    }

    override fun onStart() {

    }

    override fun onResume() {

    }

    override fun onPause() {

    }

    override fun onStop() {

    }

    override fun onDestroy() {

    }
}