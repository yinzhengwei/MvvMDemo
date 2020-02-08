package cn.com.mvvmdemo.fragment.item

import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import cn.com.mvvmdemo.R
import cn.com.mvvmdemo.databinding.AdapterItemLayoutBinding
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder

/**
 *  Created by yinzhengwei on 2020-02-07.
 *  @Function
 */
class ItemHomeAdapter(var mTitle: String) :
    BaseQuickAdapter<ItemTextBean, BaseViewHolder>(R.layout.adapter_item_layout) {

    /**
     * 当 ViewHolder 创建完毕以后，会执行此回掉
     * 可以在这里做任何你想做的事情
     */
    override fun onItemViewHolderCreated(viewHolder: BaseViewHolder, viewType: Int) {
        // 绑定 view
        DataBindingUtil.bind<ViewDataBinding>(viewHolder.itemView)
    }

    override fun convert(helper: BaseViewHolder, item: ItemTextBean?) {
        if (item == null) {
            return
        }
        // 获取 Binding
        val binding = helper.getBinding<AdapterItemLayoutBinding>()
        if (binding != null) {
            // 设置数据
            binding.item = item.apply { title = mTitle }

            binding.executePendingBindings()
        }
    }

}