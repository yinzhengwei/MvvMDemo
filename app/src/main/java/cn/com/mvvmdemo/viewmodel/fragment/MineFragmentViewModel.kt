package cn.com.mvvmdemo.viewmodel.fragment

import android.widget.Toast
import cn.com.base.mvvm.activity.IBaseView
import cn.com.base.mvvm.viewmodel.BaseViewModel

/**
 *  Created by yinzhengwei on 2020-02-07.
 *  @Function
 */
class MineFragmentViewModel(private var mView: IBaseView) : BaseViewModel() {

    //接收activity传来的参数
    fun setContent(title: String) {
    }

    //==========================以下方法是专门为xml里和activity提供的==========================

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