package com.longing.beatbox

import android.graphics.Rect
import android.util.Log
import android.view.View
import androidx.annotation.Px
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView

class GridSpacingDecoration(
    @Px private val spacing: Int
) : RecyclerView.ItemDecoration() {
    override fun getItemOffsets(
        outRect: Rect,
        child: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        val itemPosition = parent.getChildAdapterPosition(child)
        val itemCount = parent.adapter?.itemCount ?: 0

        val lm = parent.layoutManager as GridLayoutManager
        val spanCount = lm.spanCount
        val spanIndex = lm.spanSizeLookup.getSpanIndex(itemPosition, spanCount)

        val isTopRow = itemPosition < spanCount

        val bottomRowIsComplete = itemCount % spanCount == 0
        val isBottomRow =
            if (bottomRowIsComplete) itemPosition >= itemCount - spanCount else itemPosition >= itemCount - (itemCount % spanCount)

        outRect.top = if (isTopRow) spacing else spacing / 2
        outRect.bottom = if (isBottomRow) spacing else spacing / 2
        outRect.left = (spacing * ((spanCount - spanIndex) / spanCount.toFloat())).toInt()
        outRect.right = (spacing * ((spanIndex + 1) / spanCount.toFloat())).toInt()

    }
}