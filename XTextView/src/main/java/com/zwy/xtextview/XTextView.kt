package com.zwy.xtextview

import android.content.Context
import android.graphics.Color
import android.text.SpannableString
import android.text.Spanned
import android.text.TextPaint
import android.text.method.LinkMovementMethod
import android.text.style.ClickableSpan
import android.util.AttributeSet
import android.view.View


class XTextView : androidx.appcompat.widget.AppCompatTextView {

   private var mText: String? = null
    private  var mSpannableString: SpannableString? = null

    constructor(context: Context) : super(context, null)
    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs)

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    )

    private fun init() {
        movementMethod = LinkMovementMethod.getInstance()
        highlightColor = Color.TRANSPARENT
    }

    /**
     * 优先调用，设置文本
     */
    fun buildText(content: String): XTextView {
        init()
        mText = content
        mSpannableString = SpannableString(mText)
        return this
    }

    /**
     * 给部分文本设置不同颜色以及点击事件等
     */
    fun addCustomText(customTextBean: XTextBean): XTextView {
        if (mText.isNullOrEmpty() || mSpannableString == null) {
            throw NullPointerException("请优先调用XTextView.buildText设置全部文本后再试！")
        }
        mSpannableString!!.setSpan(
            object : ClickableSpan() {

                override fun onClick(p0: View) {
                    customTextBean.onClickListener?.invoke()
                }

                override fun updateDrawState(ds: TextPaint) {
                    super.updateDrawState(ds)

                    customTextBean.typeface?.let {
                        ds.typeface = it
                    }

                    customTextBean.isUnderline?.let {
                        ds.isUnderlineText = it
                    }

                    customTextBean.color?.let {
                        ds.color = Color.parseColor(it)
                    }

                }
            },

            mText!!.indexOf(customTextBean.text),
            mText!!.indexOf(customTextBean.text) + customTextBean.text.length,
            Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
        )
        return this
    }

    fun show() {
        text = mSpannableString
    }


}