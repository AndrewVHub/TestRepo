package com.example.testrepo.utils.extension

import android.widget.ImageView
import androidx.annotation.DrawableRes
import coil.load
import coil.request.CachePolicy
import coil.request.ImageRequest
import com.example.testrepo.R


fun ImageView.loadImage(
    data: Any,
    isUsePlaceholder: Boolean = true,
    @DrawableRes
    placeholder: Int? = null,
    builder: ((ImageRequest.Builder) -> Unit)? = null
) {
    if (data is Int) {
        setImageResource(data)
    } else {
        load(data) {
            if (isUsePlaceholder) placeholder(placeholder ?: R.drawable.ic_not_found)
            error(R.drawable.ic_not_found)
            crossfade(true)
            memoryCachePolicy(CachePolicy.ENABLED)
            diskCachePolicy(CachePolicy.ENABLED)
            builder?.invoke(this)
        }
    }
}