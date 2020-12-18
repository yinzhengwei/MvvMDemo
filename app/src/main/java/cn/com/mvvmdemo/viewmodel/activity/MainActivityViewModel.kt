package cn.com.mvvmdemo.viewmodel.activity

import androidx.lifecycle.MutableLiveData
import cn.com.base.mvvm.activity.IBaseView
import cn.com.base.mvvm.viewmodel.BaseViewModel

/**
 *  Created by yinzhengwei on 2020-02-07.
 *  @Function
 */
class MainActivityViewModel : BaseViewModel() {

    //todo 这里要使用ohkttp请求网络数据
    override fun <T> loadData(params: T?) {

        //通知activity或fragment刷新
        liveData<String>().value = ""
    }

    override fun cancelJob() {

    }
}