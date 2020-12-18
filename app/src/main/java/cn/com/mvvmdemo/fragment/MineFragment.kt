package cn.com.mvvmdemo.fragment

import androidx.lifecycle.ViewModelProvider
import cn.com.base.mvvm.activity.BaseFragment
import cn.com.mvvmdemo.R
import cn.com.mvvmdemo.databinding.FgMineLayoutBinding
import cn.com.mvvmdemo.viewmodel.fragment.HomeFragmentViewModel
import cn.com.mvvmdemo.viewmodel.fragment.MineFragmentViewModel

/**
 *  Created by yinzhengwei on 2020-02-05.
 *  @Function
 */
class MineFragment : BaseFragment<FgMineLayoutBinding, MineFragmentViewModel>() {

    override fun createViewModel(provider: ViewModelProvider) =
        provider.get(MineFragmentViewModel::class.java)

    override fun getLayoutId() = R.layout.fg_mine_layout

    override fun initView() {

//        mBinding.tvTitle.text = "yzw"
    }

    override fun loadData() {

    }

}