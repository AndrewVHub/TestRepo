package com.example.testrepo.utils.decorator

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView

class LinearLayoutItemDecorator(
    private val top: Int = 0,
    private val bottom: Int = 0,
    private val left: Int = 0,
    private val right: Int = 0,
    private val divider: Int = 0,
    private val orientation: Int = RecyclerView.VERTICAL
) : RecyclerView.ItemDecoration() {

    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        super.getItemOffsets(outRect, view, parent, state)

        val position = parent.getChildAdapterPosition(view)
        val lastItemPosition = (parent.adapter?.itemCount ?: 0) - 1
        if (orientation == RecyclerView.VERTICAL) {
            outRect.left = left
            outRect.right = right
            outRect.top = if (position == 0) top else divider
            if (position == lastItemPosition) outRect.bottom = bottom
        } else {
            outRect.top = top
            outRect.bottom = bottom
            outRect.left = if (position == 0) left else divider
            if (position == lastItemPosition) outRect.right = right
        }
    }
}