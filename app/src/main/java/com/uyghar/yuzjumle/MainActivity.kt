package com.uyghar.yuzjumle

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.android.material.tabs.TabLayoutMediator
import com.uyghar.yuzjumle.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    lateinit var jumleHelper: JumleHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        jumleHelper = JumleHelper(this)
        val tizim = jumleHelper.jumliler(false)

        binding.viewPager.adapter = JumleAdapter(tizim, supportFragmentManager, lifecycle)
        TabLayoutMediator(binding.tabLayout, binding.viewPager) { tab, position ->
            tab.text = "${position + 1}"
        }.attach()
        setContentView(binding.root)
    }
}