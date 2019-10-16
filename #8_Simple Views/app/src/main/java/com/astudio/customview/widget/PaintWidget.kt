package com.astudio.customview.widget

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.drawable.GradientDrawable
import android.graphics.drawable.LayerDrawable
import android.graphics.drawable.ScaleDrawable
import android.util.AttributeSet
import android.view.View
import android.widget.*
import androidx.core.view.get
import androidx.core.view.size
import com.astudio.customview.R
import kotlinx.android.synthetic.main.widget_paint.view.*
import java.text.FieldPosition

class PaintWidget @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : FrameLayout(context, attrs, defStyleAttr) {

    companion object {
        private const val DEFAULT_MAX_WIDTH = 10
        private const val DEFAULT_COLOR_POSITION = 3
        private const val DEFAULT_FIRST_ITEM_COLOR = 1
    }

    private var maxWidth = DEFAULT_MAX_WIDTH
    private var defaultColorPosition = DEFAULT_COLOR_POSITION
    private var firstItemColor = DEFAULT_FIRST_ITEM_COLOR

    var onPaintWidgetChangeListener: OnPaintWidgetChangeListener =
        context as OnPaintWidgetChangeListener

    private val layerDrawable by lazy { seekBar.progressDrawable as LayerDrawable }
    private val rightPart by lazy { layerDrawable.findDrawableByLayerId(R.id.seekbar_right_part) as GradientDrawable }
    private val leftPart by lazy { layerDrawable.findDrawableByLayerId(R.id.seekbar_left_part) as ScaleDrawable }

    init {
        View.inflate(context, R.layout.widget_paint, this)

        initAttrs(attrs)
        initViews()
    }

    private fun initAttrs(attrs: AttributeSet?) {
        val a = context.obtainStyledAttributes(attrs, R.styleable.PaintWidget)
        maxWidth = a.getInteger(R.styleable.PaintWidget_pw_maxWidth, DEFAULT_MAX_WIDTH)
        defaultColorPosition =
            a.getColor(R.styleable.PaintWidget_pw_defaultColorPosition, DEFAULT_COLOR_POSITION)
        firstItemColor =
            a.getResourceId(R.styleable.PaintWidget_pw_FirstItemColor, DEFAULT_FIRST_ITEM_COLOR)
        a?.recycle()
    }

    private fun initViews() {
        setMaxWidth(maxWidth)
        setDefaultColorPosition(defaultColorPosition)
        setFirstItemColor(firstItemColor)

        seekBar.setOnSeekBarChangeListener(SeekBarChangeListener())
        radioGroup.setOnCheckedChangeListener(RadioGroupCheckedListener())
    }

    private fun setMaxWidth(value: Int) {
        maxWidth = value
        seekBar.max = value
    }

    private fun setDefaultColorPosition(position: Int) {
        when (position) {
            0 -> {
                rb_first.isChecked = true
                setSeekBarColor(0)
            }
            1 -> {
                rb_second.isChecked = true
                setSeekBarColor(1)
            }
            2 -> {
                rb_third.isChecked = true
                setSeekBarColor(2)
            }
            3 -> {
                rb_fourth.isChecked = true
                setSeekBarColor(3)
            }
            else -> throw IllegalArgumentException("The position must be in the range 0..3")
        }
    }

    private fun setFirstItemColor(colorValue: Int) {
        firstItemColor = colorValue
        val radioButton = radioGroup[0] as CustomRadioButton
        radioButton.setRadioButtonColor(colorValue)
        if (radioButton.isChecked) {
            changeSeekBarColor(
                radioButton.ovalColor,
                radioButton.strokeColor
            )
        }
    }

    private fun setSeekBarColor(n: Int) {
        val radioButton = radioGroup[n] as CustomRadioButton
        changeSeekBarColor(radioButton.ovalColor, radioButton.strokeColor)
    }

    private fun changeSeekBarColor(shapeColor: Int?, strokeColor: Int?) {
        shapeColor?.let { leftPart.setTint(it) }
        strokeColor?.let { rightPart.setColor(it) }
    }

    inner class SeekBarChangeListener : SeekBar.OnSeekBarChangeListener {
        override fun onProgressChanged(sb: SeekBar?, progress: Int, fromUser: Boolean) {
            tv_seekBarWidth.text = progress.toString()
            onPaintWidgetChangeListener.onWidthChanged(progress)
        }

        override fun onStartTrackingTouch(sb: SeekBar?) {}

        override fun onStopTrackingTouch(sb: SeekBar?) {}
    }

    inner class RadioGroupCheckedListener : RadioGroup.OnCheckedChangeListener {
        override fun onCheckedChanged(rb: RadioGroup?, checkedId: Int) {
            val checkedRadioButton = findViewById<CustomRadioButton>(checkedId)
            val leftColor = checkedRadioButton?.ovalColor
            val rightColor = checkedRadioButton?.strokeColor
            changeSeekBarColor(leftColor, rightColor)
            leftColor?.let {
                onPaintWidgetChangeListener.onColorChanged(it)
            }
        }
    }

    interface OnPaintWidgetChangeListener {
        fun onWidthChanged(widthValue: Int)
        fun onColorChanged(colorValue: Int)
    }
}




