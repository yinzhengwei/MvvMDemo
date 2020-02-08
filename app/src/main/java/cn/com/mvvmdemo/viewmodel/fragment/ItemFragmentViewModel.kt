package cn.com.mvvmdemo.viewmodel.fragment

import cn.com.base.mvvm.activity.IBaseView
import cn.com.base.mvvm.viewmodel.BaseViewModel
import cn.com.mvvmdemo.fragment.item.ItemTextBean
import cn.com.mvvmdemo.helper.launch
import cn.com.mvvmdemo.helper.launchUi
import kotlinx.coroutines.Job

/**
 *  Created by yinzhengwei on 2020-02-07.
 *  @Function 逻辑处理
 *
 *  @param mView activity或fragment对象的引用
 */
class ItemFragmentViewModel(private var mView: IBaseView) : BaseViewModel() {

    lateinit var job: Job
    private var content = ""
    //接收activity传来的参数
    fun setContent(title: String) {
        this.content = title
    }

    //==========================以下方法是专门为xml里和activity提供的==========================
    fun fetMsg(): String {
        return content
    }

    //todo 这里要使用ohkttp请求网络数据
    override fun loadData(params: Any?) {

        mView.showLading()

        //暂时用Thread模拟
        job = launch {
            val list = mutableListOf<ItemTextBean>()
            for (i in 0..10) {
                list.add(ItemTextBean("", "第${(i + 1)}个条目"))
            }

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
        if (job != null)
            job.cancel()
    }

}