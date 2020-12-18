package cn.com.mvvmdemo.viewmodel.fragment

import cn.com.base.mvvm.activity.IBaseView
import cn.com.base.mvvm.viewmodel.BaseViewModel

/**
 *  Created by yinzhengwei on 2020-02-07.
 *  @Function
 */
class MineFragmentViewModel: BaseViewModel() {

    //接收activity传来的参数
    fun setContent(title: String) {
    }

    //==========================以下方法是专门为xml里和activity提供的==========================

    //todo 这里要使用ohkttp请求网络数据
    override fun <T> loadData(params: T?) {
    }

    override fun cancelJob() {

    }
}