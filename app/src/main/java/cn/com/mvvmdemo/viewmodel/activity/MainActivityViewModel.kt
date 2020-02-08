package cn.com.mvvmdemo.viewmodel.activity

import cn.com.base.mvvm.activity.IBaseView
import cn.com.base.mvvm.viewmodel.BaseViewModel

/**
 *  Created by yinzhengwei on 2020-02-07.
 *  @Function
 */
class MainActivityViewModel(private var mView: IBaseView) : BaseViewModel() {

    //todo 这里要使用ohkttp请求网络数据
    override fun loadData(params: Any?) {
    }

    //通知activity或fragment刷新
    override fun loadFinish(result: Any?) {
        mView.requestFinish(null)
    }

    //通知activity或fragment刷新
    override fun loadError(msg: String) {
        mView.requestError(msg)
    }

    override fun cancelJob() {

    }
}