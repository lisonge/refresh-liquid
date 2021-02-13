package li.songe.liquid_refresh.ui

import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseDataBindingHolder
import li.songe.liquid_refresh.R
import li.songe.liquid_refresh.data.DesignData
import li.songe.liquid_refresh.databinding.ItemDesignBinding

class DesignRcvAdapter :
    BaseQuickAdapter<
            DesignData,
            BaseDataBindingHolder<ItemDesignBinding>
            >
        (R.layout.item_design, null) {
    override fun convert(holder: BaseDataBindingHolder<ItemDesignBinding>, item: DesignData) {
        holder.dataBinding?.design = item
    }
}