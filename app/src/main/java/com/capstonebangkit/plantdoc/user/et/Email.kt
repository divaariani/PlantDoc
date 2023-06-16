package com.capstonebangkit.plantdoc.user.et

import android.content.Context
import android.graphics.Canvas
import android.text.TextUtils
import android.util.AttributeSet
import android.util.Patterns
import android.view.View
import androidx.appcompat.widget.AppCompatEditText
import androidx.core.widget.doOnTextChanged
import com.capstonebangkit.plantdoc.R

class Email : AppCompatEditText {
    constructor(context: Context) : super(context) {
        init()
    }

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        init()
    }

    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    ) {
        init()
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        hint = "Enter your Email"

        textAlignment = View.TEXT_ALIGNMENT_VIEW_START
    }

    private fun init() {
        doOnTextChanged { text, _, _, _ ->
            val email = text.toString()
            when {
                email.isEmpty() -> error = context.getString(R.string.email_cannot_be_empty)
                !email.isEmailValid() -> error =
                    context.getString(R.string.invalid_email_address)
            }
        }
    }

    private fun String.isEmailValid(): Boolean {
        return (!TextUtils.isEmpty(this) && Patterns.EMAIL_ADDRESS.matcher(this).matches())
    }
}