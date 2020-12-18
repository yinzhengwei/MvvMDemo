package cn.com.mvvmdemo.viewmodel.fragment

import cn.com.base.mvvm.activity.IBaseView
import cn.com.base.mvvm.viewmodel.BaseViewModel
import cn.com.mvvmdemo.helper.launch
import cn.com.mvvmdemo.helper.launchUi
import kotlinx.coroutines.Job

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
    override fun <T> loadData(params: T?) {
        mView.showLading()
        job = launch {
            val list =
                mutableListOf("拼多多", "抖音", "聚划算", "饿了吗", "淘宝", "京东", "苏宁易购", "锦鲤口袋", "美团")

            //却换到主线程
            //通知activity或fragment刷新
            launchUi {
                mView.hiddenLading()
                liveData<MutableList<String>>().value = list
            }
        }

//        runBlocking {
//            job = launchAsyn {
//
//            }
//            job.await()
//        }

    }

    //取消任务
    override fun cancelJob() {
        job.cancel()
    }
}