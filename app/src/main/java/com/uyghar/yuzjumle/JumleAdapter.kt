package com.uyghar.yuzjumle

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter

class JumleAdapter(val tizim: ArrayList<JumleModel>, fragmentManager: FragmentManager, lifeCycle: Lifecycle): FragmentStateAdapter(fragmentManager, lifeCycle) {
    override fun getItemCount(): Int {
        return tizim.size + 1
    }

    override fun createFragment(position: Int): Fragment {
        if (position == 0) {
            val fragment = MunderijeFragment()
            return fragment
        } else {
            val fragment = JumleFragment()
            fragment.jumle = tizim.get(position - 1)
            return fragment
        }
    }
}