package com.uyghar.yuzjumle

import android.content.ContentValues
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.uyghar.yuzjumle.databinding.FragmentJumleBinding



class JumleFragment : Fragment() {

    private var _binding: FragmentJumleBinding? = null
    val binding get() = _binding!!
    var jumle: JumleModel? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentJumleBinding.inflate(inflater, container, false)
        binding.textTr.setText(jumle?.tr)
        binding.textUy.setText(jumle?.uy)
        binding.buttonTalla.isChecked = jumle?.talla ?: false
        binding.buttonQaytish.setOnClickListener {
            (activity as MainActivity).binding.viewPager.setCurrentItem(0)
        }
        binding.buttonTalla.setOnCheckedChangeListener { compoundButton, b ->
            jumle?.talla = b
            val talla_val = if (b) 1 else 0
            var contentValues = ContentValues()
            contentValues.put("talla", talla_val)
            (activity as MainActivity).jumleHelper.database.update("jumle",contentValues,"id=?",arrayOf(jumle?.id.toString()))

        }
        return binding.root
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }


}