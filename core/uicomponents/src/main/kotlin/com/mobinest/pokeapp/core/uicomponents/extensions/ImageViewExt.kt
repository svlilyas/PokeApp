package com.mobinest.pokeapp.core.uicomponents.extensions

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import coil.load
import coil.request.CachePolicy
import com.mobinest.pokeapp.core.uicomponents.R

object ImageViewExt {
    @BindingAdapter(
        "android:imageSource",
        "android:placeholder",
        "android:errorDrawable",
        requireAll = false
    )
    @JvmStatic
    fun ImageView.loadImage(
        imageSource: Any? = null,
        placeholder: Int? = null,
        errorDrawable: Int? = null
    ) {
        load(imageSource) {
            placeholder(placeholder ?: R.drawable.ic_image_placeholder)
            error(errorDrawable ?: R.drawable.ic_image_error)
            networkCachePolicy(CachePolicy.ENABLED)
            diskCachePolicy(CachePolicy.ENABLED)
            crossfade(false)
        }
    }
}
