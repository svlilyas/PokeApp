package com.papirus.androidbase.core.uicomponents.extensions

import android.content.Context
import android.util.TypedValue
import android.widget.Toast
import androidx.annotation.StringRes

object ContextExtension {
    fun Context.toastMessage(message: String, duration: Int = Toast.LENGTH_SHORT) {
        Toast.makeText(this, message, duration).show()
    }

    fun Context.toastMessage(@StringRes message: Int, duration: Int = Toast.LENGTH_SHORT) {
        Toast.makeText(this, getString(message), duration).show()
    }
}