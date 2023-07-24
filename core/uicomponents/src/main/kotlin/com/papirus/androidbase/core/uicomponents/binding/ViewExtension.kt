package com.papirus.androidbase.core.uicomponents.binding

import android.view.View

object ViewExtension {

    fun View.visible(isVisible: Boolean = true) {
        visibility = if (isVisible) View.VISIBLE else View.GONE
    }

    fun View.gone(isGone: Boolean = true) {
        visibility = if (isGone) View.GONE else View.VISIBLE
    }

    fun View.invisible(isInvisible: Boolean = true) {
        visibility = if (isInvisible) View.INVISIBLE else View.VISIBLE
    }
}
