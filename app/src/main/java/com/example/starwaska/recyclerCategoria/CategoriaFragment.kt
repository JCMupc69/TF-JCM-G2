package com.example.starwaska.recyclerCategoria

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.starwaska.R
import com.example.starwaska.SharedViewModel
import com.example.starwaska.dao.RepositorioProd
import com.example.starwaska.databinding.FragmentCategoriaBinding
import com.example.starwaska.recyclerCarrito.Pedido
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class CategoriaFragment : Fragment(), MiAdapterCateg.OnItemClickListener {

    @Inject
    lateinit var db: RepositorioProd

    private val args: CategoriaFragmentArgs by navArgs()

    private var _binding: FragmentCategoriaBinding? = null
    private val binding get() = _binding!!

    private val sharedViewModel: SharedViewModel by activityViewModels()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentCategoriaBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val categ = args.Categ

        binding.recyclerCategoria.layoutManager = LinearLayoutManager(requireContext())

        val divItemDec = DividerItemDecoration(
            binding.recyclerCategoria.context,
            (binding.recyclerCategoria.layoutManager as LinearLayoutManager).orientation)
        binding.recyclerCategoria.addItemDecoration(divItemDec)

        val listaCocteles = db.obtenerProd(categ)

        val txtTitulo = binding.tituloCategoria
        txtTitulo.text = categ

        val adapterRecycler: RecyclerView.Adapter<MiAdapterCateg.MiViewHolder>
                = MiAdapterCateg(listaCocteles, this, resources, requireContext())
        binding.recyclerCategoria.adapter = adapterRecycler

        sharedViewModel.contador.observe(viewLifecycleOwner) { newValue ->
            // Update the UI with the new value
            val txtCantPedidos = binding.tvCantProductos
            txtCantPedidos.text = newValue.toString()
        }

        val carritoImg = view.findViewById<ImageView>(R.id.img_carrito)

        carritoImg.setOnClickListener {
            val action = CategoriaFragmentDirections.actionCategoriaFragmentToCarritoFragment()
            findNavController().navigate(action)
        }
    }

    override fun onItemClick(item: Producto) {
        val action = CategoriaFragmentDirections
            .actionCategoriaFragmentToDetalleFragment(detalleNom = item.nombre,
                detalleDescrip = item.descripcion, detallePrecio = item.precio.toFloat(),
                detalleImagen = item.imagen, detalleDesc = item.descuento.toFloat(), detalleTit = item.categ)
        findNavController().navigate(action)
    }

    override fun onAgregarClick(item: Producto, position: Int) {
        val pUnitario = item.precio * (1 - item.descuento)
        val pedido = Pedido(item.id, item.categ, item.nombre, item.descripcion, pUnitario,1, pUnitario)
        sharedViewModel.agregarPedido(pedido)
        sharedViewModel.contador()

    }

}