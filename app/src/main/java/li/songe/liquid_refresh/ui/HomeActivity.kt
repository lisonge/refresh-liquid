package li.songe.liquid_refresh.ui

import android.graphics.Color
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.blankj.utilcode.util.LogUtils
import com.scwang.smart.refresh.layout.listener.OnRefreshLoadMoreListener
import li.songe.liquid_refresh.R
import li.songe.liquid_refresh.data.DesignData
import li.songe.liquid_refresh.databinding.ActivityHomeBinding
import li.songe.liquid_refresh.view.LiquidRefreshHeader
import kotlin.random.Random

class HomeActivity : AppCompatActivity() {
    private val binding by lazy { ActivityHomeBinding.inflate(layoutInflater) }
    private val adapter by lazy { DesignRcvAdapter() }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        binding.smartRefreshLayout.apply {
            post {
                setRefreshHeader(LiquidRefreshHeader(this@HomeActivity).apply {
//                    setBackgroundColor(Color.WHITE)
                }, width, (width / 4.5f).toInt())
            }
        }
        binding.smartRefreshLayout.setOnRefreshListener {
            it.finishRefresh(1000)
        }
        binding.recyclerView.adapter = adapter

        adapter.setNewInstance(mutableListOf())
        val designResIdList = listOf(
            R.drawable.ic_food_1,
            R.drawable.ic_food_2,
            R.drawable.ic_food_3,
            R.drawable.ic_food_4,
            R.drawable.ic_food_5,
            R.drawable.ic_food_6,
            R.drawable.ic_food_7,
            R.drawable.ic_food_8,
        ).shuffled()
        val authorAvatarResIdList = listOf(
            R.drawable.ic_avatar_1,
            R.drawable.ic_avatar_2,
            R.drawable.ic_avatar_3,
            R.drawable.ic_avatar_4,
            R.drawable.ic_avatar_5,
            R.drawable.ic_avatar_6,
            R.drawable.ic_avatar_7,
            R.drawable.ic_avatar_8,
        ).shuffled()
        val authorNameList = listOf(
            "Nadia Mcfarland",
            "Chante Pace",
            "Kelvin Bradley",
            "Idrees Brooks",
            "Susan Fisher",
            "Cristina Harrington",
            "India Lindsay",
            "Corinne Vaughn",
            "Franco Guthrie",
            "Samera Walters",
        ).shuffled()
        repeat(11) {
            adapter.addData(
                DesignData(
                    resId = designResIdList.random(),
                    authorAvatarResId = authorAvatarResIdList.random(),
                    authorName = authorNameList.random(),
                    viewNum = 9527 + Random.nextInt(-5000, 5000),
                    likeNum = 985 + Random.nextInt(-500, 500),
                    commentNum = 211 + Random.nextInt(-100, 100)
                )
            )
        }


    }
}