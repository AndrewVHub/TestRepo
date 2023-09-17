package com.example.testrepo.utils.extension

import android.view.View
import android.view.animation.Animation
import android.view.animation.TranslateAnimation

fun View.translationIn(durationMillis: Long = 300, onStart: () -> Unit = {}) {
    this.startAnimation(
        TranslateAnimation(0F, 0F, height.toFloat(), 0f).apply {
            duration = durationMillis
            fillAfter = true
            setAnimationListener(object : Animation.AnimationListener {
                override fun onAnimationStart(animation: Animation?) {
                    onStart()
                }

                override fun onAnimationEnd(animation: Animation?) {
                }

                override fun onAnimationRepeat(animation: Animation?) {
                }
            })
        }
    )
}

fun View.translationOut(
    durationMillis: Long = 300,
    fillAfter: Boolean = true,
    onStart: () -> Unit = {}
) {
    this.startAnimation(
        TranslateAnimation(0F, 0F, 0F, height.toFloat()).apply {
            duration = durationMillis
            this.fillAfter = fillAfter
            setAnimationListener(object : Animation.AnimationListener {
                override fun onAnimationStart(animation: Animation?) {
                    onStart()
                }

                override fun onAnimationEnd(animation: Animation?) {}

                override fun onAnimationRepeat(animation: Animation?) {}
            })
        }
    )
}