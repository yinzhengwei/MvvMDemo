package cn.com.base.mvvm.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

/**
 *  Created by yinzhengwei on 2020-02-07.
 *  @Function
 */
abstract class BaseViewModel : ViewModel() {

    private var liveData: MutableLiveData<*>? = null
    fun <T> liveData(): MutableLiveData<T> {
        if (liveData == null)
            liveData = MutableLiveData<T>()
        return liveData as MutableLiveData<T>
    }

    abstract fun <T> loadData(params: T?)

//    abstract fun loadFinish(result: MutableLiveData<*>?)
//
//    abstract fun loadError(msg: String)

    abstract fun cancelJob()

}