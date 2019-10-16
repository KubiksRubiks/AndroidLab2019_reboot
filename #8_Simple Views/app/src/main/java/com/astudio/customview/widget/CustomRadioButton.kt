package com.astudio.customview.widget

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatRadioButton
import com.astudio.customview.R
import kotlin.math.min

/**
 * Class that implements custom RadioButton.
 *
 * @author Anna Zholud
 */
class CustomRadioButton @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : AppCompatRadioButton(context, attrs, defStyleAttr) {

    companion object {
        const val DEFAULT_COLOR: Int = Color.BLACK
    }

    private val paint = Paint(Paint.ANTI_ALIAS_FLAG)
    var ovalColor = DEFAULT_COLOR
        private set
    var strokeColor = DEFAULT_COLOR
        private set

    init {
        val a = context.obtainStyledAttributes(attrs, R.styleable.CustomRadioButton)
        ovalColor = a.getColor(R.styleable.CustomRadioButton_crb_bgColor, DEFAULT_COLOR)
        a.recycle()
    }

    override fun onDraw(canvas: Canvas?) {

        val halfWidth = width / 2F
        val halfHeight = height / 2F
        val radius = min(halfWidth, halfHeight)

        paint.style = Paint.Style.FILL

        if (isChecked) {
            drawCheckedRB(canvas, halfWidth, halfHeight, radius)
        } else {
            drawUncheckedRB(canvas, halfWidth, halfHeight, radius)
        }
    }

    private fun drawCheckedRB(c: Canvas?, halfW: Float, halfH: Float, rad: Float) {
        paint.color = strokeColor
        c?.drawCircle(halfW, halfH, rad, paint)
        paint.color = ovalColor
        c?.drawCircle(halfW, halfH, rad * 3 / 4, paint)
    }

    private fun drawUncheckedRB(c: Canvas?, halfW: Float, halfH: Float, rad: Float) {
        paint.color = strokeColor
        c?.drawCircle(halfW, halfH, rad, paint)
    }

    fun setRadioButtonColor(color: Int) {
        ovalColor = color
        setStrokeColor(color)
    }

    private fun setStrokeColor(value:Int){
        strokeColor = Color.argb(
            (Color.alpha(value)*0.4).toInt(),
            Color.red(value),
            Color.green(value),
            Color.blue(value)
        )
    }
}