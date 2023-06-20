package com.papirus.androidbase.core.uicomponents.extensions

import android.widget.Toast
import androidx.annotation.StringRes
import androidx.fragment.app.Fragment
import com.papirus.androidbase.core.uicomponents.extensions.ContextExtension.toastMessage

object FragmentExtension {
    fun Fragment.toastMessage(message: String, duration: Int = Toast.LENGTH_SHORT) =
        requireContext().toastMessage(message = message, duration = duration)

    fun Fragment.toastMessage(@StringRes message: Int, duration: Int = Toast.LENGTH_SHORT) =
        requireContext().toastMessage(message = message, duration = duration)
}