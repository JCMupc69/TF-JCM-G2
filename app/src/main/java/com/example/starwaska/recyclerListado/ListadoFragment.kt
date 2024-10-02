package com.example.starwaska.recyclerListado

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.starwaska.dao.RepositorioCat
import com.example.starwaska.databinding.FragmentListadoBinding
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class ListadoFragment : Fragment(), MiAdapterListado.OnItemClickListener {

    @Inject
    lateinit var db2: RepositorioCat

    private val listaCateg by lazy { db2.obtenerCat() }

    private var _binding: FragmentListadoBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentListadoBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //val listaCateg = db2.obtenerCat()


        //val db = ProductoDAO(requireContext())

        binding.rvListado.layoutManager = LinearLayoutManager(requireContext(),
            LinearLayoutManager.HORIZONTAL, false)



        val adapterRecycler: RecyclerView.Adapter<MiAdapterListado.MiViewHolder>
                = MiAdapterListado(listaCateg,this, resources, requireContext())

        binding.rvListado.adapter = adapterRecycler

    }

    override fun onItemClick(item: Categoria, Cat: String) {
        val action = ListadoFragmentDirections
            .actionListadoFragmentToCategoriaFragment(Categ = Cat)
        findNavController().navigate(action)

    }

}