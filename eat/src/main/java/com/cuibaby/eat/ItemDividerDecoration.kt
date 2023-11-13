package com.cuibaby.eat

import android.content.Context
import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView

class ItemDividerDecoration(mContext: Context) : RecyclerView.ItemDecoration() {

    private var margin: Int = 0

    init {
        margin = mContext.resources.getDimensionPixelOffset(R.dimen.item_margin_vertical)
    }

    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        super.getItemOffsets(outRect, view, parent, state)
        outRect.set(0, 0, 0, margin)
    }
}