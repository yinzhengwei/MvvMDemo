package cn.com.mvvmdemo.fragment

import cn.com.base.mvvm.activity.BaseFragment
import cn.com.mvvmdemo.R
import cn.com.mvvmdemo.databinding.FgHomeLayoutBinding
import cn.com.mvvmdemo.fragment.item.HomeViewPagerFragmentAdapter
import cn.com.mvvmdemo.viewmodel.fragment.HomeFragmentViewModel

/**
 *  Created by yinzhengwei on 2020-02-05.
 *  @Function
 */
class HomeFragment : BaseFragment<FgHomeLayoutBinding, HomeFragmentViewModel>() {

    override fun createViewModel() = HomeFragmentViewModel(this)

    override fun getLayoutId() = R.layout.fg_home_layout

    override fun initView() {

        val tabCount = mBinding.layoutTab?.tabCount ?: 0
        //加载数据
        mViewModel.loadData(tabCount)
    }

    override fun requestFinish(result: Any?) {
        val list = result as MutableList<String>

        activity?.supportFragmentManager?.run {
            mBinding.viewPager?.adapter = HomeViewPagerFragmentAdapter(this, list)
        }

        mBinding.viewPager?.offscreenPageLimit = list.size
        mBinding.layoutTab?.setupWithViewPager(mBinding.viewPager)
    }

    override fun requestError(msg: String) {
    }

}