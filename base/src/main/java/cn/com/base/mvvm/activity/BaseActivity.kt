package cn.com.base.mvvm.activity

import android.app.ProgressDialog
import android.content.pm.ActivityInfo
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import cn.com.base.mvvm.viewmodel.BaseViewModel
import com.kingja.loadsir.callback.Callback
import com.kingja.loadsir.core.LoadService
import com.kingja.loadsir.core.LoadSir

/**
 *  Created by yinzhengwei on 2020-02-05.
 *  @Function
 */
abstract class BaseActivity<T : ViewDataBinding, VM : BaseViewModel> : AppCompatActivity(),
    IBaseView {

    @LayoutRes
    abstract fun getLayoutId(): Int

    abstract fun createViewModel(): VM

    abstract fun initView()

    lateinit var mBinding: T
    lateinit var mViewModel: VM

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //竖屏
        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT

        mBinding = DataBindingUtil.setContentView(this, getLayoutId());

        mViewModel = createViewModel()

        initView()
    }

    override fun onDestroy() {
        super.onDestroy()
        mViewModel.cancelJob()
        unBinding()
    }

    override fun unBinding() {
        mBinding.unbind()
    }

    private var loadService: LoadService<*>? = null
    private var progressBar: ProgressDialog? = null
    //展示loading
    fun showLading(msg: String) {
        progressBar = ProgressDialog.show(this, "", msg)

//        loadService = LoadSir.getDefault().register(view) {
//            //重试
//            //onRetryBtnClick()
//        }
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