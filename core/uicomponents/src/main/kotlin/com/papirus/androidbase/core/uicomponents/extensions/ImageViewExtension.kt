package com.papirus.androidbase.core.uicomponents.extensions

import android.widget.ImageView
import coil.load
import coil.request.CachePolicy
import com.papirus.androidbase.core.uicomponents.R

object ImageViewExtension {

    fun ImageView.loadImage(
        imageSource: Any?,
        placeholder: Int = R.drawable.ic_image_placeholder,
        errorDrawable: Int = R.drawable.ic_image_error
    ) {
        load(imageSource) {
            placeholder(placeholder)
            error(errorDrawable)
            networkCachePolicy(CachePolicy.ENABLED)
            memoryCachePolicy(CachePolicy.ENABLED)
            diskCachePolicy(CachePolicy.ENABLED)
            crossfade(true)
        }
    }
}
