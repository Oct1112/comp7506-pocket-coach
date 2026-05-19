package com.hkucs.pocketcoach.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.hkucs.pocketcoach.R
import com.hkucs.pocketcoach.databinding.ItemBookCardBinding
import com.hkucs.pocketcoach.model.Book

class BookCardAdapter : ListAdapter<Book, BookCardAdapter.BookViewHolder>(DIFF) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookViewHolder {
        val binding = ItemBookCardBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return BookViewHolder(binding)
    }

    override fun onBindViewHolder(holder: BookViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class BookViewHolder(private val binding: ItemBookCardBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(book: Book) {
            binding.tvBookTitle.text = book.title
            binding.tvBookAuthor.text = book.author
            binding.ivBookCover.setImageResource(getCoverRes(book))
        }

        private fun getCoverRes(book: Book): Int {
            return when (book.title) {
                "The Weirdest People in the World" -> R.drawable.book_weirdest_people
                "Writing My Wrongs" -> R.drawable.book_writing_my_wrongs
                "How to Be Free" -> R.drawable.book_how_to_be_free
                else -> R.drawable.ic_book_open
            }
        }
    }

    companion object {
        private val DIFF = object : DiffUtil.ItemCallback<Book>() {
            override fun areItemsTheSame(a: Book, b: Book) = a.title == b.title
            override fun areContentsTheSame(a: Book, b: Book) = a == b
        }
    }
}
