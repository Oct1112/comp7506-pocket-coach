package com.hkucs.pocketcoach.ui.library

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.hkucs.pocketcoach.R
import com.hkucs.pocketcoach.databinding.ItemInProgressBookBinding
import com.hkucs.pocketcoach.model.InProgressBook

class InProgressBookAdapter :
    ListAdapter<InProgressBook, InProgressBookAdapter.ViewHolder>(DIFF) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemInProgressBookBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class ViewHolder(private val binding: ItemInProgressBookBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(book: InProgressBook) {
            binding.tvBookTitle.text = book.title
            if (book.started) {
                binding.tvBookAuthor.text = book.author
                binding.tvBookAuthor.visibility = View.VISIBLE
                binding.progressBar.visibility = View.VISIBLE
                binding.progressBar.progress = book.progressPercent
                binding.btnPlay.visibility = View.VISIBLE
                binding.tvNotStarted.visibility = View.GONE
                binding.ivBookIcon.setColorFilter(ContextCompat.getColor(binding.root.context, R.color.white))
                binding.layoutBookCover.background =
                    ContextCompat.getDrawable(binding.root.context, R.drawable.bg_book_cover_started)
            } else {
                binding.tvBookAuthor.text = ""
                binding.tvBookAuthor.visibility = View.GONE
                binding.progressBar.visibility = View.GONE
                binding.btnPlay.visibility = View.GONE
                binding.tvNotStarted.visibility = View.VISIBLE
                binding.ivBookIcon.setColorFilter(
                    ContextCompat.getColor(binding.root.context, R.color.text_tertiary)
                )
                binding.layoutBookCover.background =
                    ContextCompat.getDrawable(binding.root.context, R.drawable.bg_book_cover_idle)
            }
        }
    }

    companion object {
        private val DIFF = object : DiffUtil.ItemCallback<InProgressBook>() {
            override fun areItemsTheSame(a: InProgressBook, b: InProgressBook) = a.title == b.title
            override fun areContentsTheSame(a: InProgressBook, b: InProgressBook) = a == b
        }
    }
}
