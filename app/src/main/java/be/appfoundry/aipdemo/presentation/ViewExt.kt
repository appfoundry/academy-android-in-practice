package be.appfoundry.aipdemo.presentation

import androidx.recyclerview.widget.RecyclerView

fun <T : RecyclerView.ViewHolder> T.whenClicked(clickListener: (position: Int) -> Unit): T {
    itemView.setOnClickListener {
        clickListener.invoke(absoluteAdapterPosition)
    }
    return this
}