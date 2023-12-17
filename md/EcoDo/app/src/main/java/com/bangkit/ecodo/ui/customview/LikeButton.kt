package com.bangkit.ecodo.ui.customview

import android.content.Context
import android.util.AttributeSet
import android.widget.Toast
import androidx.appcompat.widget.AppCompatButton
import androidx.core.content.ContextCompat
import com.bangkit.ecodo.R

class LikeButton: AppCompatButton {
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
            background = ContextCompat.getDrawable(context, R.drawable.img_click_like)
            Toast.makeText(context, "Terimakasih telah memberikan feedback yang baik", Toast.LENGTH_SHORT).show()
        } else {
            background = ContextCompat.getDrawable(context, R.drawable.img_btn)
        }
    }
}