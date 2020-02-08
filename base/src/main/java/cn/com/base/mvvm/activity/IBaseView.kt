package cn.com.base.mvvm.activity

/**
 *  Created by yinzhengwei on 2020-02-07.
 *  @Function
 */
interface IBaseView {

    fun showLading()
    fun hiddenLading()
    fun requestFinish(result: Any?)
    fun requestError(msg: String)

    //解绑数据
    fun unBinding()
}