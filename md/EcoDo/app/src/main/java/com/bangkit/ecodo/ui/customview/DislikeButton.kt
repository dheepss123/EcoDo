package com.bangkit.ecodo.ui.customview

import android.content.Context
import android.util.AttributeSet
import android.widget.Toast
import androidx.appcompat.widget.AppCompatButton
import androidx.core.content.ContextCompat
import com.bangkit.ecodo.R

class DislikeButton: AppCompatButton {
    constructor(context: Context) : super(context) {
        init()
    }

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        init()
    }

    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        init()
    }

    private fun init() {
        isClickable = true
        setOnClickListener {
            isSelected = !isSelected
        }
    }

    override fun drawableStateChanged() {
        super.drawableStateChanged()
        if (isSelected) {
            background = ContextCompat.getDrawable(context, R.drawable.img_click_dislike)
            Toast.makeText(context, "Kami akan perbaiki kekurangan dari feedback anda, terimakasih", Toast.LENGTH_SHORT).show()
        } else {
            background = ContextCompat.getDrawable(context, R.drawable.img_btn_dislike)
        }
    }
}