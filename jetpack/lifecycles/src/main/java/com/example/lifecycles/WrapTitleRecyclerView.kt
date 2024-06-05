package com.example.lifecycles

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.util.TypedValue
import androidx.recyclerview.widget.RecyclerView
import com.example.lifecycles.R

class WrapTitleRecyclerView : RecyclerView {

    private var mSelectBackgroundWidth: Float = 0f
    private var mSelectBackgroundHeight: Float = 0f
    private var mSelectBackground: Bitmap? = null

    private var mTitle: String = ""
    private var mTitleColor = Color.WHITE
    private var mTitleSize = 12f
    private var mTitleMarginTop = 30f

    private var mSelectBgRect = Rect()
    private var mSelectBgDstRect = RectF()

    private var mWidth = 0f
    private var mHeight = 0f

    private var mBgPaint: Paint = Paint()
    private var mTitlePaint: Paint = Paint()

    init {
        mBgPaint.isAntiAlias = true
        mBgPaint.isDither = true
        mBgPaint.style = Paint.Style.FILL

        mTitlePaint.isAntiAlias = true
        mTitlePaint.isDither = true
        mTitlePaint.textAlign = Paint.Align.CENTER
        mTitlePaint.style = Paint.Style.FILL
        mTitlePaint.color = mTitleColor
        mTitlePaint.textSize = mTitleSize
    }

    constructor(context: Context) : super(context) {
        init(context, null)
    }

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs) {
        init(context, attrs)
    }

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    ) {
        init(context, attrs)
    }

    private fun init(context: Context, attrs: AttributeSet?) {
        val typeArray = context.obtainStyledAttributes(attrs,
            R.styleable.WrapTitleRecyclerView
        )
        mSelectBackgroundWidth =
            typeArray.getDimension(
                R.styleable.WrapTitleRecyclerView_select_background_Width,
                dip2px(112f)
            )

        mSelectBackgroundHeight = typeArray.getDimension(
            R.styleable.WrapTitleRecyclerView_select_background_height,
            dip2px(54.67f)
        )

        val resourceId =
            typeArray.getResourceId(R.styleable.WrapTitleRecyclerView_select_background, -1)

        val title = typeArray.getString(R.styleable.WrapTitleRecyclerView_title)

        mTitleColor =
            typeArray.getColor(R.styleable.WrapTitleRecyclerView_title_text_color, Color.WHITE)
        mTitleSize =
            typeArray.getDimension(R.styleable.WrapTitleRecyclerView_title_text_size, dip2px(12f))
        mTitleMarginTop =
            typeArray.getDimension(
                R.styleable.WrapTitleRecyclerView_title_margin_top,
                dip2px(dip2px(10f))
            )
        typeArray.recycle()

        if (resourceId != -1) {
            mSelectBackground = BitmapFactory.decodeResource(resources, resourceId)
            mSelectBackground?.let {
                mSelectBgRect.set(0, 0, it.width, it.height)
            }
        }

        title?.let { mTitle = it }

        mTitlePaint.color = mTitleColor
        mTitlePaint.textSize = mTitleSize

        if(paddingBottom == 0 || paddingBottom == 0){
            setPadding(0,dip2px(40f).toInt(),0,dip2px(40f).toInt())
        }
    }

    fun setTitle(title: String?) {
        this.mTitle = title ?: ""
        invalidate()
    }

    fun setTitleSize(size: Float) {
        this.mTitleSize = dip2px(size)
        mTitlePaint.textSize = mTitleSize
        invalidate()
    }

    fun setTitleColor(color: Int) {
        this.mTitleColor = color
        mTitlePaint.color = mTitleColor
        invalidate()
    }

    fun setTileMarginTop(top: Float) {
        this.mTitleMarginTop = top
        invalidate()
    }

    fun setSelectBackground(resourceId: Int) {
        val drawable = BitmapFactory.decodeResource(resources, resourceId)
        this.mSelectBackground = drawable
        mSelectBackground?.let {
            mSelectBgRect.set(0, 0, it.width, it.height)
        }
        invalidate()
    }

    fun setSelectBackgroundWidth(width: Float) {
        this.mSelectBackgroundWidth = width
        invalidate()
    }

    fun setSelectBackgroundHeight(height: Float) {
        this.mSelectBackgroundHeight = height
        invalidate()
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        mSelectBgDstRect.set(
            w / 2 - mSelectBackgroundWidth / 2,
            h / 2 - mSelectBackgroundHeight / 2,
            w / 2 + mSelectBackgroundWidth / 2,
            h / 2 + mSelectBackgroundHeight / 2
        )

        mWidth = w.toFloat()
        mHeight = h.toFloat()

    }

    override fun dispatchDraw(canvas: Canvas) {
        drawBackground(canvas)
        drawTitle(canvas)
        super.dispatchDraw(canvas)
    }

    private fun drawBackground(canvas: Canvas) {
        mSelectBackground?.let {
            canvas.saveLayer(0f, 0f, mWidth, mHeight, null)
            canvas.clipRect(mSelectBgDstRect)
            canvas.drawBitmap(it, mSelectBgRect, mSelectBgDstRect, mBgPaint)
            canvas.restore()
        }
    }

    private fun drawTitle(canvas: Canvas) {
        val fontMetrics = mTitlePaint.fontMetrics
        val top = mTitleMarginTop - fontMetrics.top
        canvas.saveLayer(0f, 0f, mWidth, mHeight, null)
        canvas.drawText(mTitle, mWidth / 2, top, mTitlePaint)
        canvas.restore()
    }

    private fun dip2px(dip: Float): Float =
        TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dip, resources.displayMetrics)

}