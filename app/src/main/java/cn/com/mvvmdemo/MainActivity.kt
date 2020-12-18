package cn.com.mvvmdemo

import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.core.view.get
import androidx.fragment.app.Fragment
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModelProvider
import cn.com.base.mvvm.activity.BaseActivity
import cn.com.mvvmdemo.databinding.ActivityMainBinding
import cn.com.mvvmdemo.fragment.FriendFragment
import cn.com.mvvmdemo.fragment.HomeFragment
import cn.com.mvvmdemo.fragment.MineFragment
import cn.com.mvvmdemo.viewmodel.activity.MainActivityViewModel
import com.google.android.material.bottomnavigation.BottomNavigationMenuView
import com.google.android.material.bottomnavigation.LabelVisibilityMode
import q.rorbin.badgeview.QBadgeView

class MainActivity : BaseActivity<ActivityMainBinding, MainActivityViewModel>() {

    private val homeFragment = HomeFragment()
    private val friendFragment = FriendFragment()
    private val mineFragment = MineFragment()
    private var fromFragment = Fragment()

    override fun getLayoutId(): Int = R.layout.activity_main

    override fun createViewModel(viewModel: ViewModelProvider) =
        viewModel.get(MainActivityViewModel::class.java)

    override fun initView() {
        setSupportActionBar(mBinding.toolbar)
        supportActionBar?.run {
            setDisplayShowHomeEnabled(true)
            setDisplayHomeAsUpEnabled(true)
        }

        val fgt = supportFragmentManager.beginTransaction()
        fgt.replace(mBinding.container?.id ?: 0, homeFragment)
        fgt.commit()
        fromFragment = homeFragment
        supportActionBar?.title = mBinding.bottom?.menu?.get(0)?.title

        //tab切换时图标内容跟着变化
        mBinding.bottom?.labelVisibilityMode = LabelVisibilityMode.LABEL_VISIBILITY_LABELED

        mBinding.bottom?.setOnNavigationItemSelectedListener {
            val fg = when (it.itemId) {
                R.id.navigation_home -> homeFragment
                R.id.navigation_dashboard -> friendFragment
                R.id.navigation_notifications -> mineFragment
                else -> homeFragment
            }
            if (supportActionBar != null) {
                supportActionBar?.title = it.title
            }
            switchFragment(fromFragment, fg)
            fromFragment = fg

            true
        }

        showBadgeView(2, 5)
    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> finish()

        }
        return true
    }

    //创建右上角的菜单
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu, menu)
        return true
    }

    private fun switchFragment(from: Fragment, to: Fragment) {
        if (fromFragment == to)
            return
        val transaction = supportFragmentManager.beginTransaction()
        if (!to.isAdded) {
            transaction.hide(from)
            transaction.add(R.id.container, to).commit()
        } else {
            transaction.hide(from)
            transaction.show(to).commit()
        }
    }

    /**
     * viewIndex第几个View
     * showNumber 显示的数字
     */
    private fun showBadgeView(viewIndex: Int, showNumber: Int) {
        val navigationMenuView = mBinding.bottom?.getChildAt(0) as BottomNavigationMenuView
        if (viewIndex < navigationMenuView.childCount) {
            val view = navigationMenuView.getChildAt(viewIndex)
            val icon = view.findViewById<View>(R.id.icon)
            val iconWidth = icon.width
            val tabWidth = view.width / 2

            val spaceWidth = tabWidth - iconWidth

            QBadgeView(this).bindTarget(view).setGravityOffset(spaceWidth + 80f, 5f, false)
                .badgeNumber =
                showNumber
        }
    }

}
