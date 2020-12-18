package cn.com.base.mvvm.activity

import android.app.ProgressDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
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

    abstract fun loadData()

    lateinit var mBinding: T
    lateinit var mViewModel: VM

    //Fragment的View加载完毕的标记
    private var isViewCreated: Boolean = false

    //Fragment对用户可见的标记
    var isUIVisible: Boolean = false
    protected var isLoadCompleted: Boolean = false

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        mBinding = DataBindingUtil.inflate(inflater, getLayoutId(), container, false)

        mViewModel = createViewModel()

//        mViewModel = ViewModelProvider(this).get(createViewModel()::class.java)
//        mViewModel = defaultViewModelProviderFactory.create(createViewModel())

        mBinding.executePendingBindings()

        isViewCreated = true

        return mBinding.root
    }

    override fun setUserVisibleHint(isVisibleToUser: Boolean) {
        super.setUserVisibleHint(isVisibleToUser)
        //isVisibleToUser这个boolean值表示:该Fragment的UI 用户是否可见
        if (isVisibleToUser && !isLoadCompleted) {
            isUIVisible = true
            lazyLoad()
        } else {
            isUIVisible = false
        }
    }

    fun <R> observe(callback: (R) -> Unit) {
        mViewModel?.liveData<R>()?.observe(this, Observer<R> {
            callback(it)
        })
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        initView()
        if (userVisibleHint && !isLoadCompleted) {
            // 此处不需要判断isViewCreated，因为这个方法在onCreateView方法之后执行
            lazyLoad()
        }
    }

    override fun onHiddenChanged(hidden: Boolean) {
        super.onHiddenChanged(hidden)
        isUIVisible = !hidden
    }

    protected fun lazyLoad() {
        //这里进行双重标记判断,是因为setUserVisibleHint会多次回调,并且会在onCreateView执行前回调,必须确保onCreateView加载完毕且页面可见,才加载数据
        if (isViewCreated && isUIVisible) {
            loadData()
            //数据加载完毕,恢复标记,防止重复加载
            //            isViewCreated = false;
            //            isUIVisible = false;
            isLoadCompleted = true
        }
    }


    override fun onDestroyView() {
        super.onDestroyView()
        mViewModel.cancelJob()
        unBinding()
    }

    override fun unBinding() {
        mBinding.unbind()
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

    fun <R> fetData(result: MutableLiveData<*>?, callback: (R) -> Unit) {
        result?.observe(viewLifecycleOwner, Observer {
            callback(it as R)
        })
    }
}