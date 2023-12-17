package com.bangkit.ecodo.ui.customview

import android.content.Context
import android.graphics.Canvas
import android.graphics.drawable.Drawable
import android.util.AttributeSet
import android.view.Gravity
import androidx.appcompat.widget.AppCompatButton
import androidx.core.content.ContextCompat
import com.bangkit.ecodo.R

class CustomButtonFilterOn : AppCompatButton {
    private lateinit var defaultBackground: Drawable
    private lateinit var clickedBackground: Drawable
    private var defaultTextColor: Int = 0
    private var clickedTextColor: Int = 0
    private var isClicked: Boolean = false

    constructor(context: Context) : super(context) {
        init()
    }

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        init()
    }

    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        init()
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        if (isClicked) {
            background = ContextCompat.getDrawable(context, R.drawable.filter_button_off)
            setTextColor(ContextCompat.getColor(context, R.color.black))
        } else {
            background = ContextCompat.getDrawable(context, R.drawable.filter_button_on)
            setTextColor(ContextCompat.getColor(context, R.color.white))
        }
        textSize = 12f
        gravity = Gravity.CENTER
        text = resources.getString(R.string.filter_2_s)
    }

    private fun init() {
        setOnClickListener {
            isClicked = !isClicked
            invalidate()
        }
    }
}