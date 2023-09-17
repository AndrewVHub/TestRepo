package com.example.testrepo.utils.extension

import androidx.annotation.StringRes
import androidx.recyclerview.widget.RecyclerView

inline fun RecyclerView.ViewHolder.ifHasPosition(crossinline action: (Int) -> Unit) {
    if (absoluteAdapterPosition != RecyclerView.NO_POSITION) {
        action.invoke(absoluteAdapterPosition)
    }
}

fun RecyclerView.ViewHolder.getString(@StringRes id: Int) = itemView.resources.getString(id)