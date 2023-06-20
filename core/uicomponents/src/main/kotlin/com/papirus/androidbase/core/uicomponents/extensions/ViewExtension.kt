package com.papirus.androidbase.core.uicomponents.extensions

import android.content.Context
import android.os.SystemClock
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager

object ViewExtension {
    fun View.hideKeyboard() {
        (context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager)
            .hideSoftInputFromWindow(windowToken, 0)
    }

    fun View.showKeyboard() {
        (context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager)
            .showSoftInput(this, InputMethodManager.SHOW_IMPLICIT)
    }

    fun View.setOnDebouncedClickListener(action: () -> Unit) {
        val actionDebouncer = ActionDebouncer(action)

        // This is the only place in the project where we should actually use setOnClickListener
        setOnClickListener {
            actionDebouncer.notifyAction()
        }
    }

    fun View.setAlpha(isEnabled: Boolean = true) {
        alpha = if (isEnabled) 1f else 0.5f
    }

    fun View.forEachChildView(closure: (View) -> Unit) {
        closure(this)
        val groupView = this as? ViewGroup ?: return
        val size = groupView.childCount - 1
        for (i in 0..size) {
            groupView.getChildAt(i).forEachChildView(closure)
        }
    }

    fun View.setEnabledWithAlpha(isEnabled: Boolean = true) {
        setAlpha(isEnabled = isEnabled)
        setEnabled(isEnabled)
    }


    fun View.removeOnDebouncedClickListener() {
        setOnClickListener(null)
        isClickable = false
    }

    private class ActionDebouncer(private val action: () -> Unit) {

        companion object {
            const val DEBOUNCE_INTERVAL_MILLISECONDS = 600L
        }

        private var lastActionTime = 0L

        fun notifyAction() {
            val now = SystemClock.elapsedRealtime()

            val millisecondsPassed = now - lastActionTime
            val actionAllowed = millisecondsPassed > DEBOUNCE_INTERVAL_MILLISECONDS
            lastActionTime = now

            if (actionAllowed) {
                action.invoke()
            }
        }
    }
}
