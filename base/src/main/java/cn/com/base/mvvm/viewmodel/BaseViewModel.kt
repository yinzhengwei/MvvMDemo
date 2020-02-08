package cn.com.base.mvvm.viewmodel

/**
 *  Created by yinzhengwei on 2020-02-07.
 *  @Function
 */
abstract class BaseViewModel {

    abstract fun loadData(params: Any?)

    abstract fun loadFinish(result:Any?)

    abstract fun loadError(msg: String)

    abstract fun cancelJob()

}