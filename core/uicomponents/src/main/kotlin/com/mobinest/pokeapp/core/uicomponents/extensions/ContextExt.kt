package com.mobinest.pokeapp.core.uicomponents.extensions

import android.content.Context
import android.widget.Toast
import androidx.annotation.StringRes

object ContextExt {
    fun Context.toastMessage(message: String, duration: Int = Toast.LENGTH_SHORT) {
        Toast.makeText(this, message, duration).show()
    }

    fun Context.toastMessage(@StringRes message: Int, duration: Int = Toast.LENGTH_SHORT) {
        Toast.makeText(this, getString(message), duration).show()
    }
}