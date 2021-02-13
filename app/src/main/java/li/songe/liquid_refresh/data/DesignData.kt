package li.songe.liquid_refresh.data

import androidx.annotation.DrawableRes

data class DesignData(
    @DrawableRes
    val resId: Int,
    @DrawableRes
    val authorAvatarResId: Int,
    val authorName: String,
    val viewNum: Int,
    val likeNum: Int,
    val commentNum: Int
)
