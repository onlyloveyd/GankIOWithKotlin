package onlyloveyd.com.gankioclient.view

import android.animation.ObjectAnimator
import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View
import java.util.*

/**
 * Created by yidong on 2015/4/1.
 */
class PathTextView(context: Context, attrs: AttributeSet) : View(context, attrs) {
    private val DEFAULT_COLOR = Color.WHITE
    private val DEFAULT_WIDTH = 8.0f
    private val mSvgLock = Any()
    private var mText = "Kotlin"
    private var mDatas: ArrayList<FloatArray>? = null
    private val mPaths = ArrayList<Path>()
    private val mPaint = Paint()
    private var mSvgAnimator: ObjectAnimator? = null
    var phase: Float = 0.toFloat()
        set(phase) {
            field = phase
            synchronized(mSvgLock) {
                updatePathsPhaseLocked()
            }
            invalidate()
        }
    private val mType = TYPE.SINGLE
    private val mScaleFactor = 1.0f

    init {

        mPaint.style = Paint.Style.STROKE
        mPaint.pathEffect = CornerPathEffect(4f)
        mPaint.isAntiAlias = true
        mPaint.strokeCap = Paint.Cap.ROUND

        mPaint.color = DEFAULT_COLOR
        mPaint.strokeWidth = DEFAULT_WIDTH
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        setMeasuredDimension(measureWidth(widthMeasureSpec),
                measureHeight(heightMeasureSpec))

    }

    fun init(text: String?) {
        if (text == null || text.length == 0) {
            return
        }

        requestLayout()
        invalidate()

        mText = text
        mDatas = MatchPath.getPath(mText)
        mSvgAnimator = ObjectAnimator.ofFloat(this, "phase", 0.0f, 1.0f).setDuration(2000)
        mSvgAnimator!!.start()
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        canvas.translate(4f, 4f)
        if (mPaths == null) {
            return
        }
        synchronized(mSvgLock) {
            for (i in mPaths.indices) {
                canvas.drawPath(mPaths[i], mPaint)
            }
        }
    }

    private fun updatePathsPhaseLocked() {
        mPaths.clear()
        val singlefactor = phase * mDatas!!.size
        for (i in mDatas!!.indices) {
            val path = Path()
            path.moveTo(mDatas!![i][0], mDatas!![i][1])
            path.lineTo(mDatas!![i][2], mDatas!![i][3])

            if (mType == TYPE.MULTIPY) {
                val measure = PathMeasure(path, false)
                val dst = Path()
                measure.getSegment(0.0f, phase * measure.length, dst, true)
                mPaths.add(dst)
            } else {
                //Fuck! can't compare float and int
                //Sometimes, at the end of animation , the value is  -9.5176697E-4 or other tiny
                // value.
                if (singlefactor - (i + 1) >= -0.01) {
                    mPaths.add(path)
                } else if (i - Math.floor(singlefactor.toDouble()) < 0.0001) {
                    val dst = Path()
                    val measure = PathMeasure(path, false)
                    measure.getSegment(0.0f, singlefactor % 1 * measure.length, dst, true)
                    mPaths.add(dst)
                }
            }

        }
    }

    private fun measureWidth(measureSpec: Int): Int {
        var result = 0
        val specMode = View.MeasureSpec.getMode(measureSpec)
        val specSize = View.MeasureSpec.getSize(measureSpec)

        if (specMode == View.MeasureSpec.EXACTLY) {
            // We were told how big to be
            result = specSize
        } else {
            // Measure the text
            result = (mText.length.toFloat() * BASE_SQUARE_UNIT * mScaleFactor + paddingLeft.toFloat()
                    + paddingRight.toFloat()).toInt()
            if (specMode == View.MeasureSpec.AT_MOST) {
                // Respect AT_MOST value if that was what is called for by measureSpec
                result = Math.min(result, specSize)
            }
        }

        return result
    }

    private fun measureHeight(measureSpec: Int): Int {
        var result = 0
        val specMode = View.MeasureSpec.getMode(measureSpec)
        val specSize = View.MeasureSpec.getSize(measureSpec)

        if (specMode == View.MeasureSpec.EXACTLY) {
            // We were told how big to be
            result = specSize
        } else {
            // Measure the text (beware: ascent is a negative number)
            result = (BASE_SQUARE_UNIT * mScaleFactor).toInt() + paddingTop
            +paddingBottom
            if (specMode == View.MeasureSpec.AT_MOST) {
                // Respect AT_MOST value if that was what is called for by measureSpec
                result = Math.min(result, specSize)
            }
        }
        return result
    }

    enum class TYPE {
        SINGLE, MULTIPY
    }

    companion object {
        private val BASE_SQUARE_UNIT = 72f
    }

}
