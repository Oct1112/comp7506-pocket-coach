package com.hkucs.pocketcoach.ui.home.detail

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.hkucs.pocketcoach.databinding.ActivityFiveMinReadDetailBinding

class FiveMinReadDetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityFiveMinReadDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFiveMinReadDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.tvPageTitle.text = intent.getStringExtra(EXTRA_TITLE).orEmpty()
        binding.tvSummaryContent.text = intent.getStringExtra(EXTRA_SUMMARY).orEmpty()
        binding.btnBack.setOnClickListener { finish() }
    }

    companion object {
        const val EXTRA_TITLE = "extra_title"
        const val EXTRA_SUMMARY = "extra_summary"
    }
}
