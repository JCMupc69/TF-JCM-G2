package com.example.starwaska.recyclerCarrito

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.starwaska.R
import com.example.starwaska.SharedViewModel
import com.example.starwaska.databinding.FragmentCarritoBinding

class CarritoFragment : Fragment(), MiAdapterCarrito.OnItemClickListener {

    private var _binding: FragmentCarritoBinding? = null
    private val binding get() = _binding!!

    private val sharedViewModel: SharedViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentCarritoBinding.inflate(inflater, container, false)
        return binding.root
    }

    @SuppressLint("DefaultLocale")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        (activity as AppCompatActivity).supportActionBar?.setDisplayHomeAsUpEnabled(true)

        binding.recyclerCarrito.layoutManager = LinearLayoutManager(requireContext())

        val listaPedidos = sharedViewModel.obtenerListaPedidos().toMutableList()

        if (listaPedidos.isEmpty()) {
            binding.carritoVacio.visibility = View.VISIBLE
        } else {
            binding.carritoVacio.visibility = View.GONE
        }

        val adapterRecycler = MiAdapterCarrito(listaPedidos, this)
        binding.recyclerCarrito.adapter = adapterRecycler

        val txtSubtotal: TextView = view.findViewById(R.id.total_final)
        txtSubtotal.text = String.format("%.2f", sharedViewModel.sumarSubtotal())

        binding.btnIrPago.setOnClickListener {
            if (!listaPedidos.isEmpty()) {
                val action = CarritoFragmentDirections.actionCarritoFragmentToProcesoFragment()
                findNavController().navigate(action)
            } else {
                Toast.makeText(requireContext(), "Primero añada productos", Toast.LENGTH_SHORT).show()
            }

        }

    }



    override fun onItemClick(position: Int, cantidad: Int, subtotal: Double) {
        sharedViewModel.setSubtotal(position, cantidad, subtotal)
        val newSubtotal = sharedViewModel.sumarSubtotal()
        val txtSubtotal: TextView = requireView().findViewById(R.id.total_final)
        txtSubtotal.text = String.format("%.2f", newSubtotal)
    }

    override fun onCantidadCero(pedido: Pedido, position: Int) {
        sharedViewModel.eliminarPedido(position)
        sharedViewModel.clickStatusFalse(pedido)
        sharedViewModel.contador()
        (binding.recyclerCarrito.adapter as MiAdapterCarrito).pedidoLista.clear()
        val newPedidos = sharedViewModel.obtenerListaPedidos()
        (binding.recyclerCarrito.adapter as MiAdapterCarrito).pedidoLista.addAll(newPedidos)
        binding.recyclerCarrito.adapter?.notifyDataSetChanged()

        val newSubtotal = sharedViewModel.sumarSubtotal()
        val txtSubtotal: TextView = requireView().findViewById(R.id.total_final)
        txtSubtotal.text = String.format("%.2f", newSubtotal)
        if (newPedidos.isEmpty()) {
            binding.carritoVacio.visibility = View.VISIBLE
        }
    }

}


