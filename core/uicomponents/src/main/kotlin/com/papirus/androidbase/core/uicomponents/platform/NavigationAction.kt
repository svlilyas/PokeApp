package com.papirus.androidbase.core.uicomponents.platform

import androidx.navigation.NavDirections

sealed class NavigationAction {
    class ToDirection(val direction: NavDirections) : NavigationAction()
    object Back : NavigationAction()
}
