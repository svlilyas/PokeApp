package com.papirus.androidbase.core.uicomponents.binding

import android.graphics.drawable.Drawable
import androidx.annotation.DrawableRes
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment

object FragmentBinding {
    fun Fragment.getDrawable(@DrawableRes drawableRes: Int): Drawable? =
        ContextCompat.getDrawable(requireContext(), drawableRes)
}
