package com.example.testrepo.utils

import android.view.View
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.testrepo.utils.extension.translationIn
import com.example.testrepo.utils.extension.translationOut

private const val SHOW_ARROW_AFTER = 4
private const val DURATION_MILLIS = 100L

class ScrollCustom(private val arrow: View) : RecyclerView.OnScrollListener() {
    private var isVisible = false
    override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
        super.onScrolled(recyclerView, dx, dy)
        (recyclerView.layoutManager as? LinearLayoutManager)?.findFirstVisibleItemPosition()
            ?.let { position ->
                if (position >= SHOW_ARROW_AFTER) {
                    if (!isVisible) {
                        isVisible = true
                        arrow.isVisible = isVisible
                        arrow.translationIn(durationMillis = DURATION_MILLIS)
                    }
                } else {
                    if (isVisible) {
                        isVisible = false
                        arrow.translationOut(durationMillis = DURATION_MILLIS) {
                            arrow.isVisible = isVisible
                        }
                    }
                }
            }
    }
}