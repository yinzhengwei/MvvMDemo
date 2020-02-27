package cn.com.mvvmdemo.viewmodel.fragment

import androidx.lifecycle.MutableLiveData
import cn.com.base.mvvm.activity.IBaseView
import cn.com.base.mvvm.viewmodel.BaseViewModel

/**
 *  Created by yinzhengwei on 2020-02-07.
 *  @Function
 */
class FriendFragmentViewModel(private var mView: IBaseView) : BaseViewModel() {

    //todo 这里要使用ohkttp请求网络数据
    override fun <T> loadData(params: T?) {
    }

    //通知activity或fragment刷新
    override fun loadFinish(result: MutableLiveData<*>?) {
        mView.requestFinish(null)
    }

    //通知activity或fragment刷新
    override fun loadError(msg: String) {
        mView.requestError(msg)
    }

    override fun cancelJob() {

    }
}