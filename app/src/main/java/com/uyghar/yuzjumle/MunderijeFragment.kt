package com.uyghar.yuzjumle

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import com.uyghar.yuzjumle.databinding.FragmentJumleBinding
import com.uyghar.yuzjumle.databinding.FragmentMunderijeBinding


class MunderijeFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var _binding: FragmentMunderijeBinding? = null
    val binding get() = _binding!!
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentMunderijeBinding.inflate(inflater, container, false)
        val jumleHelper = JumleHelper(requireContext())
        var tizim = jumleHelper.jumliler(false)
        //val tizim_str = tizim.map { it.tr }
        binding.listMunderije.adapter = ArrayAdapter(requireContext(),android.R.layout.simple_list_item_1,tizim)
        binding.listMunderije.setOnItemClickListener { adapterView, view, i, l ->
            (activity as MainActivity).binding.viewPager.setCurrentItem(i + 1)
        }
        binding.buttonTallanghanlar.setOnCheckedChangeListener { compoundButton, b ->
            tizim = jumleHelper.jumliler(b)
            binding.listMunderije.adapter = ArrayAdapter(requireContext(),android.R.layout.simple_list_item_1,tizim)

        }
        return binding.root
    }


}