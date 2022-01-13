package com.zwy.xtextview

import android.graphics.Typeface

data class XTextBean(
    val text: String,
    val color: String? = null,
    val isUnderline: Boolean? = false,
    val typeface: Typeface? = null,
    val onClickListener: (() -> Unit)? = null
)
