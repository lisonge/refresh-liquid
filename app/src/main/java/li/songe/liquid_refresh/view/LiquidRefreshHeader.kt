package li.songe.liquid_refresh.view

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View
import com.blankj.utilcode.util.ImageUtils
import com.blankj.utilcode.util.LogUtils
import com.scwang.smart.refresh.layout.api.RefreshHeader
import com.scwang.smart.refresh.layout.api.RefreshKernel
import com.scwang.smart.refresh.layout.api.RefreshLayout
import com.scwang.smart.refresh.layout.constant.RefreshState
import com.scwang.smart.refresh.layout.constant.SpinnerStyle
import kotlin.concurrent.thread
import kotlin.random.Random

class LiquidRefreshHeader @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr), RefreshHeader {
    override fun onStateChanged(
        refreshLayout: RefreshLayout,
        oldState: RefreshState,
        newState: RefreshState
    ) {
        when (newState) {
            RefreshState.PullDownToRefresh -> {

            }
            RefreshState.Refreshing -> {

            }
            RefreshState.ReleaseToRefresh -> {

            }
            else -> {

            }
        }
    }

    override fun getView(): View = this

    override fun getSpinnerStyle(): SpinnerStyle = SpinnerStyle.FixedBehind

    override fun setPrimaryColors(vararg colors: Int) = Unit

    override fun onInitialized(kernel: RefreshKernel, height: Int, maxDragHeight: Int) {
    }

    override fun onMoving(
        isDragging: Boolean,
        percent: Float,
        offset: Int,
        height: Int,
        maxDragHeight: Int
    ) {
        this.offset = offset
        this.percent = percent
    }

    override fun onReleased(refreshLayout: RefreshLayout, height: Int, maxDragHeight: Int) {
        refreshLayout.autoRefresh()
    }

    override fun onStartAnimator(refreshLayout: RefreshLayout, height: Int, maxDragHeight: Int) {

    }

    override fun onFinish(refreshLayout: RefreshLayout, success: Boolean): Int {
        return 0
    }

    override fun onHorizontalDrag(percentX: Float, offsetX: Int, offsetMax: Int) = Unit

    override fun isSupportHorizontalDrag(): Boolean = false

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas!!)
        canvasPlus
        circleList
        if (percent >= 0.05f) {
            canvas.drawRect(0f, 0f, width.toFloat(), height.toFloat(), redPaint)
            canvasPlus.drawColor(Color.TRANSPARENT, PorterDuff.Mode.CLEAR)
            circleList.forEach { circle ->
                if (circle.y < 100f) {
                    circle.or = circle.r * (1f - percent) * 0.5f
                }
                if (circle.r > 1f) {
                    canvasPlus.drawCircle(circle.x, circle.y, circle.or, whitePaint)
                }
            }
            ImageUtils.stackBlur(bitmap, 4, true)
            canvas.drawBitmap(bitmap, 0f, 0f, filterPaint)
        }
        invalidate()
    }


    data class Circle(var x: Float, var y: Float, var r: Float, var or: Float = r)

    private val circleList: List<Circle> by lazy {
        val list: MutableList<Circle> = mutableListOf()
        repeat(9) { j ->
            repeat(20) { i ->
                list.add(
                    Circle(
                        r = (90 + Random.nextInt(-45, 45)).toFloat(),
                        x = 0f + i * 60f + Random.nextInt(-30, 30),
                        y = 0f + j * 60f + Random.nextInt(-40, 40),
                    )
                )
            }
        }
        return@lazy list
    }

    private var offset = 0
    private var percent = 0f
    private val bitmap by lazy {
        Bitmap.createBitmap(
            width,
            height,
            Bitmap.Config.ARGB_8888
        )
    }
    private val canvasPlus by lazy { Canvas(bitmap) }
    private val filterPaint by lazy {
        Paint().apply {
//            isAntiAlias = true
            colorFilter = ColorMatrixColorFilter(
                floatArrayOf(
                    1f, 0f, 0f, 0f, 0f,
                    0f, 1f, 0f, 0f, 0f,
                    0f, 0f, 1f, 0f, 0f,
                    0f, 0f, 0f, 60f, -7f,
                )
            )
        }
    }
    private val redPaint by lazy {
        Paint().apply {
            color = Color.rgb(0xd6, 0x39, 0x73)
        }
    }
    private val whitePaint by lazy {
        Paint().apply {
            color = Color.WHITE
        }
    }
}