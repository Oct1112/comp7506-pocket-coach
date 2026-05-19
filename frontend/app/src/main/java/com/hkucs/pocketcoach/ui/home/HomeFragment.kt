package com.hkucs.pocketcoach.ui.home

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.hkucs.pocketcoach.databinding.FragmentHomeBinding
import com.hkucs.pocketcoach.ui.home.detail.FiveMinReadDetailActivity

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private val viewModel: HomeViewModel by viewModels()
    private var deepDiveUrl: String = ""
    private var fiveMinReadTitle: String = ""
    private var fiveMinReadSummary: String = ""

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val bookAdapter = BookCardAdapter()
        binding.rvBooks.apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            adapter = bookAdapter
        }

        viewModel.todaysLesson.observe(viewLifecycleOwner) { lesson ->
            deepDiveUrl = lesson.deepDiveUrl
            binding.tvLessonTitle.text = lesson.title
            binding.tvLessonDesc.text = lesson.description
            binding.tvDeepDiveLabel.text = lesson.ctaLabel
        }

        viewModel.fiveMinRead.observe(viewLifecycleOwner) { summary ->
            fiveMinReadTitle = summary.title
            fiveMinReadSummary = summary.fullSummary
            binding.tv5MinTitle.text = summary.title
            binding.tvTakeaway1.text = summary.keyTakeaways.getOrElse(0) { "" }
            binding.tvTakeaway2.text = summary.keyTakeaways.getOrElse(1) { "" }
            binding.tvTakeaway3.text = summary.keyTakeaways.getOrElse(2) { "" }
            binding.btnReadSummary.text = summary.buttonLabel
        }

        val openSummary = {
            if (fiveMinReadSummary.isNotBlank()) {
                val intent = Intent(requireContext(), FiveMinReadDetailActivity::class.java).apply {
                    putExtra(FiveMinReadDetailActivity.EXTRA_TITLE, fiveMinReadTitle)
                    putExtra(FiveMinReadDetailActivity.EXTRA_SUMMARY, fiveMinReadSummary)
                }
                startActivity(intent)
            }
        }

        binding.btnReadSummary.setOnClickListener { openSummary() }
        binding.layoutViewAll.setOnClickListener { openSummary() }
        binding.layoutDeepDive.setOnClickListener {
            if (deepDiveUrl.isNotBlank()) {
                startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(deepDiveUrl)))
            }
        }

        viewModel.books.observe(viewLifecycleOwner) { books ->
            bookAdapter.submitList(books)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
