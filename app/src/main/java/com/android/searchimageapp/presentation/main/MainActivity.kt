package com.android.searchimageapp.presentation.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import com.android.searchimageapp.R
import com.android.searchimageapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private var currentFragment: Fragment? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initSearchFragmentView()

        binding.apply {

            btnMainSearch.setOnClickListener {
                if (currentFragment !is SearchFragment) {
                    initSearchFragmentView()
                    currentFragment = SearchFragment()
                }
            }
            btnMainStorage.setOnClickListener {
                if(currentFragment !is StorageFragment) {
                    initStorageFragmentView()
                    currentFragment = StorageFragment()
                }
            }
        }
    }

    private fun initSearchFragmentView() {
        supportFragmentManager.commit {
            replace(R.id.fr_main, SearchFragment())
            setReorderingAllowed(true)
            addToBackStack(null)
        }
    }

    private fun initStorageFragmentView() {
        supportFragmentManager.commit {
            replace(R.id.fr_main, StorageFragment())
            setReorderingAllowed(true)
            addToBackStack(null)
        }
    }
}