package cn.com.mvvmdemo.fragment.item

import cn.com.base.mvvm.activity.BaseFragment
import cn.com.mvvmdemo.R
import cn.com.mvvmdemo.databinding.FgItemLayoutBinding
import cn.com.mvvmdemo.viewmodel.fragment.ItemFragmentViewModel

/**
 *  Created by yinzhengwei on 2020-02-05.
 *  @Function
 *
 *  @param mBinding xml文件中控件的集合
 *  @param mViewModel 处理逻辑的实体
 *
 */
class ItemFragment : BaseFragment<FgItemLayoutBinding, ItemFragmentViewModel>() {

    private val mAdapter = ItemHomeAdapter("")
    private var current = 0

    override fun getLayoutId() = R.layout.fg_item_layout

    override fun createViewModel() = ItemFragmentViewModel(this)

    override fun initView() {
        mViewModel.setContent(arguments?.getString("title") ?: "")
        mAdapter.mTitle = mViewModel.fetMsg()

        mBinding.swipe?.setOnRefreshListener {
            current++

            //调用逻辑处理实体中的数据加载
            mViewModel.loadData(null)
        }
        mViewModel.loadData(null)

        mBinding.recycler.adapter = mAdapter
    }

    override fun requestFinish(result: Any?) {
        mAdapter.setNewData(result as MutableList<ItemTextBean>)

        //结束刷新
        mBinding.swipe?.isRefreshing = false
    }

    override fun requestError(msg: String) {
    }

}