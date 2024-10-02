package com.example.starwaska

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.starwaska.databinding.FragmentProcesoBinding

class ProcesoFragment : Fragment() {

    private var _binding: FragmentProcesoBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentProcesoBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnProcesar.setOnClickListener {
            val action = ProcesoFragmentDirections.actionProcesoFragmentToMetodoFragment()
            findNavController().navigate(action)

        }
    }



}