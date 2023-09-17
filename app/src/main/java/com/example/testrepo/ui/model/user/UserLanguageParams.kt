package com.example.testrepo.ui.model.user

import androidx.annotation.ColorRes
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.example.testrepo.R

enum class UserLanguageParams(
    @DrawableRes
    val image: Int,
    @ColorRes
    val colorBox: Int,
    @StringRes
    val hashtag: Int
) {
    KOTLIN(
        R.drawable.ic_kotlin,
        R.color.icon_language_blue_15,
        R.string.language_kotlin
    ),
    JAVA(
        R.drawable.ic_java,
        R.color.icon_language_red_15,
        R.string.language_java
    ),
    UNKNOWN(
        R.drawable.ic_not_found,
        R.color.background_secondary,
        R.string.common_no_data
    )
}

fun getLanguageTitleAndImage(type: String?): UserLanguageParams =
    when (type) {
        "Kotlin" -> {
            UserLanguageParams.KOTLIN
        }

        "Java" -> {
            UserLanguageParams.JAVA
        }

        else -> {
            UserLanguageParams.UNKNOWN
        }
    }