package cn.com.mvvmdemo.viewmodel.fragment

import cn.com.base.mvvm.activity.IBaseView
import cn.com.base.mvvm.viewmodel.BaseViewModel
import cn.com.mvvmdemo.helper.launch
import cn.com.mvvmdemo.helper.launchUi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.cancelAndJoin

/**
 *  Created by yinzhengwei on 2020-02-07.
 *  @Function
 */
class HomeFragmentViewModel(private var mView: IBaseView) : BaseViewModel() {

    lateinit var job: Job

    //接收activity传来的参数
    fun setContent(title: String) {
    }

    fun fetMsg(): String {
        return "yzw"
    }

    //==========================以下方法是专门为xml里和activity提供的==========================

    //todo 这里要使用ohkttp请求网络数据
    override fun loadData(params: Any?) {
        mView.showLading()
        job = launch {
            val list = listOf("拼多多", "抖音", "聚划算", "饿了吗", "淘宝", "京东", "苏宁易购", "锦鲤口袋", "美团")

            //却换到主线程
            launchUi {
                loadFinish(list)
            }
        }
    }

    //通知activity或fragment刷新
    override fun loadFinish(result: Any?) {
        mView.hiddenLading()
        mView.requestFinish(result)
    }

    //通知activity或fragment刷新
    override fun loadError(msg: String) {
        mView.requestError(msg)
    }

    //取消任务
    override fun cancelJob() {
        job.cancel()
    }
}