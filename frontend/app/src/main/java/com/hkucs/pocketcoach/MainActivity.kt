package com.hkucs.pocketcoach

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.hkucs.pocketcoach.databinding.ActivityMainBinding
import com.hkucs.pocketcoach.ui.ask.AskFragment
import com.hkucs.pocketcoach.ui.home.HomeFragment
import com.hkucs.pocketcoach.ui.learn.LearnFragment
import com.hkucs.pocketcoach.ui.library.LibraryFragment

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private val homeFragment = HomeFragment()
    private val learnFragment = LearnFragment()
    private val askFragment = AskFragment()
    private val libraryFragment = LibraryFragment()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        if (savedInstanceState == null) {
            showFragment(homeFragment, "home")
        }

        binding.bottomNav.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.nav_home    -> { showFragment(homeFragment, "home"); true }
                R.id.nav_learn   -> { showFragment(learnFragment, "learn"); true }
                R.id.nav_ask     -> { showFragment(askFragment, "ask"); true }
                R.id.nav_library -> { showFragment(libraryFragment, "library"); true }
                else -> false
            }
        }
    }

    private fun showFragment(fragment: Fragment, tag: String) {
        val fm = supportFragmentManager
        val tx = fm.beginTransaction()

        if (!fragment.isAdded) {
            tx.add(R.id.fragment_container, fragment, tag)
        }

        listOf(homeFragment, learnFragment, askFragment, libraryFragment).forEach {
            if (it.isAdded && it !== fragment) tx.hide(it)
        }
        tx.show(fragment)
        tx.commit()
    }
}
