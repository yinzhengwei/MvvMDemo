package cn.com.mvvmdemo.fragment

import androidx.lifecycle.ViewModelProvider
import cn.com.base.mvvm.activity.BaseFragment
import cn.com.mvvmdemo.R
import cn.com.mvvmdemo.databinding.FgFriendLayoutBinding
import cn.com.mvvmdemo.viewmodel.fragment.FriendFragmentViewModel

/**
 *  Created by yinzhengwei on 2020-02-05.
 *  @Function
 */
class FriendFragment : BaseFragment<FgFriendLayoutBinding, FriendFragmentViewModel>() {

    override fun getLayoutId() = R.layout.fg_friend_layout

    override fun createViewModel(provider: ViewModelProvider) =
        provider.get(FriendFragmentViewModel::class.java)

    override fun initView() {
    }

    override fun loadData() {

    }

}