package com.hkucs.pocketcoach.ui.learn

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.hkucs.pocketcoach.databinding.ItemKnowledgeCardBinding
import com.hkucs.pocketcoach.model.Card

class KnowledgeCardAdapter(
    private val onSave: (String) -> Unit
) : ListAdapter<Card, KnowledgeCardAdapter.CardViewHolder>(DIFF) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardViewHolder {
        val binding = ItemKnowledgeCardBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return CardViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CardViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class CardViewHolder(private val binding: ItemKnowledgeCardBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(card: Card) {
            binding.tvTypeLabel.text = card.typeLabel
            binding.tvKeyInsight.text = card.keyInsight
            binding.tvExplanation.text = card.explanation
            binding.tvQuote.text = "\u201C${card.quote}\u201D"
            binding.tvActionItem.text = card.actionItem
            binding.tvSource.text = "SOURCE: ${card.sourceLabel}"
            binding.tvSaveLabel.text = if (card.isSaved) "SAVED" else "SAVE"

            // Glossary section
            if (card.glossary.isEmpty()) {
                binding.layoutGlossary.visibility = View.GONE
            } else {
                binding.layoutGlossary.visibility = View.VISIBLE
                binding.layoutGlossaryItems.removeAllViews()
                card.glossary.forEach { item ->
                    val tv = android.widget.TextView(binding.root.context).apply {
                        text = "\u2022 ${item.term}: ${item.definition}"
                        textSize = 12f
                        setTextColor(
                            androidx.core.content.ContextCompat.getColor(
                                context, com.hkucs.pocketcoach.R.color.text_secondary
                            )
                        )
                        val dp4 = (4 * resources.displayMetrics.density).toInt()
                        val dp2 = (2 * resources.displayMetrics.density).toInt()
                        setPadding(0, dp4, 0, dp2)
                    }
                    binding.layoutGlossaryItems.addView(tv)
                }
            }

            binding.btnSave.setOnClickListener { onSave(card.id) }
        }
    }

    companion object {
        private val DIFF = object : DiffUtil.ItemCallback<Card>() {
            override fun areItemsTheSame(a: Card, b: Card) = a.id == b.id
            override fun areContentsTheSame(a: Card, b: Card) = a == b
        }
    }
}
