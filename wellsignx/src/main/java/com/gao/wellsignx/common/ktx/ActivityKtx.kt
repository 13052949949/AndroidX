package com.gao.wellsignx.common.ktx

import android.app.Activity
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding

/**
 * @author：gao
 * @date：2022/11/24
 * @desc：Activity类的扩展函数和扩展属性
 **/

fun <T : ViewDataBinding> Activity.bind(@LayoutRes resId: Int) {
    DataBindingUtil.setContentView<T>(this, resId)
}

