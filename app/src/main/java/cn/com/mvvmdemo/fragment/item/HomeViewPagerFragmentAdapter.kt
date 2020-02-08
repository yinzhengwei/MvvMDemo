package cn.com.mvvmdemo.fragment.item

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter

class HomeViewPagerFragmentAdapter(fragmentManager: FragmentManager, var list: MutableList<String>) :
    FragmentPagerAdapter(fragmentManager) {

    override fun getItem(position: Int): Fragment {
        val bundle = Bundle()
        bundle.putString("title", list[position])
        bundle.putString("position", position.toString())
        val fg = ItemFragment()
        fg.arguments = bundle
        return  fg
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return list[position]
    }

    override fun getCount() = list.size
}
