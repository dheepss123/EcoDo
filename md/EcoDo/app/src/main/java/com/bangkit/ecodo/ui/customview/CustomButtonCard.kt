package com.bangkit.ecodo.ui.customview

import android.content.Context
import android.graphics.Canvas
import android.graphics.drawable.Drawable
import android.util.AttributeSet
import android.view.Gravity
import androidx.appcompat.widget.AppCompatButton
import androidx.core.content.ContextCompat
import com.bangkit.ecodo.R

class CustomButtonCard : AppCompatButton {
    private lateinit var defaultBackground: Drawable
    private lateinit var clickedBackground: Drawable
    private var defaultTextColor: Int = 0
    private var clickedTextColor: Int = 0

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
        background = if (isPressed) clickedBackground else defaultBackground
        setTextColor(if (isPressed) clickedTextColor else defaultTextColor)
        textSize = 12f
        gravity = Gravity.CENTER
        text = if (isPressed) resources.getString(R.string.btn_cek_video) else  resources.getString(R.string.btn_cek_video)
    }

    private fun init() {
        defaultTextColor = ContextCompat.getColor(context, android.R.color.background_dark)
        clickedTextColor = ContextCompat.getColor(context, android.R.color.white)
        defaultBackground = ContextCompat.getDrawable(context, R.drawable.btn_cek_video) as Drawable
        clickedBackground = ContextCompat.getDrawable(context, R.drawable.btn_cek_desc) as Drawable
    }
}
