package com.hkucs.pocketcoach.ui.learn

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.chip.Chip
import com.hkucs.pocketcoach.R
import com.hkucs.pocketcoach.databinding.FragmentLearnBinding

class LearnFragment : Fragment() {

    private var _binding: FragmentLearnBinding? = null
    private val binding get() = _binding!!
    private val viewModel: LearnViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentLearnBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val cardAdapter = KnowledgeCardAdapter { cardId ->
            viewModel.saveCard(cardId)
        }
        binding.rvCards.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = cardAdapter
        }

        // Build tag chips
        viewModel.tags.forEach { tag ->
            val chip = Chip(requireContext()).apply {
                text = tag
                isCheckable = false
                isClickable = true
                chipCornerRadius = 100f
                textSize = 10f
                setTextColor(ContextCompat.getColorStateList(requireContext(), R.color.chip_text_selector))
                chipBackgroundColor = ContextCompat.getColorStateList(requireContext(), R.color.chip_bg_selector)
                setOnClickListener { viewModel.selectTag(tag) }
            }
            binding.chipGroupTags.addView(chip)
        }

        viewModel.selectedTag.observe(viewLifecycleOwner) { selectedTag ->
            for (i in 0 until binding.chipGroupTags.childCount) {
                val chip = binding.chipGroupTags.getChildAt(i) as? Chip ?: continue
                val isSelected = chip.text == selectedTag
                chip.setChipBackgroundColorResource(
                    if (isSelected) R.color.navy else R.color.chip_bg_unselected
                )
                chip.setTextColor(
                    ContextCompat.getColor(
                        requireContext(),
                        if (isSelected) android.R.color.white else R.color.text_secondary
                    )
                )
            }
        }

        viewModel.cards.observe(viewLifecycleOwner) { cards ->
            cardAdapter.submitList(cards)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
