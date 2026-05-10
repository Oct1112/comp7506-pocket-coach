package com.hkucs.pocketcoach.ui.library

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.hkucs.pocketcoach.databinding.FragmentLibraryBinding

class LibraryFragment : Fragment() {

    private var _binding: FragmentLibraryBinding? = null
    private val binding get() = _binding!!
    private val viewModel: LibraryViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentLibraryBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val inProgressAdapter = InProgressBookAdapter()
        binding.rvInProgress.apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            adapter = inProgressAdapter
        }

        viewModel.libraryData.observe(viewLifecycleOwner) { data ->
            inProgressAdapter.submitList(data.inProgressBooks)
            binding.tvSavedCount.text = "${data.savedCount} items"
            binding.tvDownloadsCount.text = "${data.downloadsCount} items"
            binding.tvExtractsCount.text = "${data.extractsCount} items"
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
