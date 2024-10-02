package com.example.starwaska

import android.graphics.Paint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs

class DetalleFragment : Fragment() {

    private val args: DetalleFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_detalle, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
//        super.onViewCreated(view, savedInstanceState)

        (activity as AppCompatActivity).supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val detalleNom = args.detalleNom
        val detalleDescrip = args.detalleDescrip
        val detallePrecio = args.detallePrecio
        val detalleImagen = args.detalleImagen
        val detalleDesc = args.detalleDesc
        val detalleTit = args.detalleTit

        val txtTitCateg = view.findViewById<TextView>(R.id.Tit_Categoria)
        val txtDetNom = view.findViewById<TextView>(R.id.nom_Detalle)
        val txtDetDescrip = view.findViewById<TextView>(R.id.dscrp_Detalle)
        val txtDetPrecio = view.findViewById<TextView>(R.id.precio_Detalle)
        val imagenDet = view.findViewById<ImageView>(R.id.img_Detalle)
        val txtAntPrecio = view.findViewById<TextView>(R.id.ant_precio)

        txtTitCateg.text = detalleTit
        txtDetNom.text = detalleNom
        txtDetDescrip.text = detalleDescrip

        val precioFinal = detallePrecio * (1 - detalleDesc)
        txtDetPrecio.text = String.format("%.2f", precioFinal)

        txtAntPrecio.text = "S/ " + String.format("%.2f", detallePrecio)
        txtAntPrecio.paintFlags = Paint.STRIKE_THRU_TEXT_FLAG

        val imageName = detalleImagen
        val imageResourceId = resources.getIdentifier(imageName, "drawable", requireContext().packageName)
        imagenDet.setImageResource(imageResourceId)

        val btnAñadir = view.findViewById<Button>(R.id.btn_addProducto)
        //val btnMuestra = view.findViewById<Button>(R.id.btn_sample)

        btnAñadir.setOnClickListener {
            val action = DetalleFragmentDirections.actionDetalleFragmentToCarritoFragment()
            findNavController().navigate(action)
        }

        /*btnMuestra.setOnClickListener {
            val action = DetalleFragmentDirections.actionDetalleFragmentToMuestraFragment()
            findNavController().navigate(action)
        }*/

    }
}