package com.app.storitest.core

import android.content.Context
import android.widget.Toast
import androidx.annotation.StringRes

fun Context.toast(text: String, duration: Int = Toast.LENGTH_SHORT): Toast =
        Toast.makeText(this, text, duration).apply { show() }

fun Context.toast(@StringRes resId: Int, duration: Int = Toast.LENGTH_SHORT): Toast =
        Toast.makeText(this, resId, duration).apply { show() }
