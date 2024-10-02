package com.example.starwaska

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ProgressBar
import androidx.navigation.fragment.findNavController
import com.example.starwaska.databinding.FragmentMetodoBinding

class MetodoFragment : Fragment() {

    private var _binding: FragmentMetodoBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentMetodoBinding.inflate(inflater, container, false)

        val distritos = resources.getStringArray(R.array.distritos)
        val arrayAdapter = ArrayAdapter(requireContext(), R.layout.dropdown_item, distritos)
        binding.autoCompleteTextView.setAdapter(arrayAdapter)

        return binding.root



    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.msgConforme.visibility = View.GONE
        binding.progressBar.visibility = View.GONE
        binding.btnContinuar1.visibility = View.GONE

        binding.btnVerificar.setOnClickListener {
            binding.btnVerificar.visibility = View.GONE
            binding.progressBar.visibility = View.VISIBLE
            Handler(Looper.getMainLooper()).postDelayed({
                binding.progressBar.visibility = View.GONE
                binding.msgConforme.visibility = View.VISIBLE
                binding.btnContinuar1.visibility = View.VISIBLE
            }, 4000)
        }

        binding.btnContinuar1.setOnClickListener {
            val action = MetodoFragmentDirections.actionMetodoFragmentToPagoFragment()
            findNavController().navigate(action)
        }

    }

}

