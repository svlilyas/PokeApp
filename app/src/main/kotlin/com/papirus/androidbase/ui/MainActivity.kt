package com.papirus.androidbase.ui

import android.os.Bundle
import com.papirus.androidbase.R
import com.papirus.androidbase.core.uicomponents.binding.BindingActivity
import com.papirus.androidbase.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BindingActivity<ActivityMainBinding>(R.layout.activity_main) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(binding.root)
    }
}