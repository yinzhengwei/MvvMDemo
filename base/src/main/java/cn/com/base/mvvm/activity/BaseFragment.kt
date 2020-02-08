package cn.com.base.mvvm.activity

import android.app.ProgressDialog
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import cn.com.base.mvvm.viewmodel.BaseViewModel

/**
 *  Created by yinzhengwei on 2020-02-05.
 *  @Function
 */
abstract class BaseFragment<T : ViewDataBinding, VM : BaseViewModel> : Fragment(), IBaseView {

    @LayoutRes
    abstract fun getLayoutId(): Int

    abstract fun createViewModel(): VM

    abstract fun initView()

    lateinit var mBinding: T
    lateinit var mViewModel: VM

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mBinding = DataBindingUtil.inflate(inflater, getLayoutId(), container, false)

        mViewModel = createViewModel()

        initView()

        mBinding.executePendingBindings()

        return mBinding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        mViewModel.cancelJob()
        unBinding()
    }

    override fun unBinding() {
//        mBinding.unbind()
//        mBinding = null
//        mViewModel = null
    }

    private var progressBar: ProgressDialog? = null
    //展示loading
    fun showLading(msg: String) {
        progressBar = ProgressDialog.show(activity, "", msg)
    }

    //重载方法！为了提供提示文案的动态可切换
    override fun showLading() {
        showLading("正在加载数据中...")
    }

    override fun hiddenLading() {
        if (progressBar != null && progressBar?.isShowing == true)
            progressBar?.dismiss()
    }
}