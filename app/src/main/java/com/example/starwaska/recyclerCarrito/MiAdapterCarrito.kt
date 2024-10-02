package com.example.starwaska.recyclerCarrito

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.starwaska.R

class MiAdapterCarrito( val pedidoLista:MutableList<Pedido>,
                       private val listener: OnItemClickListener)
    : RecyclerView.Adapter<MiAdapterCarrito.MiViewHolder>() {

    interface OnItemClickListener {
        fun onItemClick(position: Int, cantidad: Int, subtotal: Double )
        fun onCantidadCero(pedido: Pedido, position: Int)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MiViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_carrito, parent, false)
        return MiViewHolder(view)


    }

    override fun onBindViewHolder(holder: MiViewHolder, position: Int) {
        val pedido = pedidoLista[position]

        holder.txtCarritoNom.text = pedido.nombre.replace("\n", " ")
        holder.txtCarritoDescrip.text = pedido.descripcion
        val precioFinal = pedido.precio * pedido.cantidad
        holder.txtCarritoPrecio.text = String.format("%.2f", precioFinal)
        holder.txtCantidad.text = pedido.cantidad.toString()


        holder.btMas.setOnClickListener {
            var cantActual = holder.txtCantidad.text.toString().toInt()
            cantActual++
            holder.txtCantidad.text = cantActual.toString()
            val subtotal = pedido.precio * cantActual
            holder.txtCarritoPrecio.text = String.format("%.2f", subtotal)
            listener.onItemClick(position, cantActual, subtotal)
        }

        holder.btMenos.setOnClickListener {
            var cantActual = holder.txtCantidad.text.toString().toInt()
            cantActual--
            if (cantActual >= 1) {
                holder.txtCantidad.text = cantActual.toString()
                val subtotal = pedido.precio * cantActual
                holder.txtCarritoPrecio.text = String.format("%.2f", subtotal)
                listener.onItemClick(position, cantActual, subtotal)
            } else listener.onCantidadCero(pedido, position)

        }

    }

    override fun getItemCount(): Int {
        return pedidoLista.size
    }

    class MiViewHolder(view: View): RecyclerView.ViewHolder(view) {
        val txtCarritoNom: TextView = view.findViewById(R.id.carrito_nom)
        val txtCarritoDescrip: TextView = view.findViewById(R.id.carrito_Descrip)
        val txtCarritoPrecio: TextView = view.findViewById(R.id.carrito_precio)
        val txtCantidad: TextView = view.findViewById(R.id.carrito_cant)
        val btMas: TextView = view.findViewById(R.id.bt_plus)
        val btMenos: TextView = view.findViewById(R.id.bt_minus)
    }

}


