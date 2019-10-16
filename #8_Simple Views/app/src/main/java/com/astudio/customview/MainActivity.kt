package com.astudio.customview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.core.view.isVisible
import com.astudio.customview.widget.PaintWidget
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.widget_paint.view.*

class MainActivity : AppCompatActivity(), PaintWidget.OnPaintWidgetChangeListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        tb_showAndHide.setOnCheckedChangeListener { _, isChecked ->

            paintWidget.isVisible = isChecked
        }
    }

    override fun onWidthChanged(widthValue: Int) {
        Toast.makeText(
            applicationContext,
            "${R.string.width_changed} $widthValue",
            Toast.LENGTH_SHORT
        ).show()
    }

    override fun onColorChanged(colorValue: Int) {
        Toast.makeText(
            applicationContext,
            "${R.string.color_changed} $colorValue",
            Toast.LENGTH_SHORT
        ).show()
    }
}
