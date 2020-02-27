package cn.com.mvvmdemo.fragment

import androidx.lifecycle.MutableLiveData
import cn.com.base.mvvm.activity.BaseFragment
import cn.com.mvvmdemo.R
import cn.com.mvvmdemo.databinding.FgFriendLayoutBinding
import cn.com.mvvmdemo.viewmodel.fragment.FriendFragmentViewModel

/**
 *  Created by yinzhengwei on 2020-02-05.
 *  @Function
 */
class FriendFragment : BaseFragment<FgFriendLayoutBinding, FriendFragmentViewModel>() {

    override fun createViewModel() =
        FriendFragmentViewModel(this)

    override fun getLayoutId() = R.layout.fg_friend_layout

    override fun initView() {
    }

    override fun loadData() {

    }

    override fun requestFinish(result: MutableLiveData<*>?) {
    }

    override fun requestError(msg: String) {
    }

}