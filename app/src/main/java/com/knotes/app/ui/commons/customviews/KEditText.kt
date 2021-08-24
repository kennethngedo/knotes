package com.knotes.app.ui.commons.customviews

import android.content.Context
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatEditText


class KEditText : AppCompatEditText {
    constructor(context: Context) : super(context) {}

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs) {
        //init(context, attrs)
    }

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    ) {
        //init(context, attrs)
    }

//    private fun init(context: Context, attrs: AttributeSet?) {
//        val ta = context.obtainStyledAttributes(attrs, R.stylea, 0, 0)
//        try {
//            typefaceType = ta.getInt(R.styleable.CustomFontTextView_customTypeface, 0)
//        } finally {
//            ta.recycle()
//        }
//        initTypeface(context)
//    }

    var validation: ((String) -> String?)? = null
}