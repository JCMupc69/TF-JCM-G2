package com.example.starwaska.recyclerListado

import android.content.Context
import android.content.res.Resources
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.example.starwaska.R
import com.example.starwaska.recyclerCategoria.Producto

class MiAdapterListado(private val categLista:MutableList<Categoria>,
                       private val listener: OnItemClickListener,
                       private val resources: Resources,
                       private val context: Context
): RecyclerView.Adapter<MiAdapterListado.MiViewHolder>() {

    interface OnItemClickListener {
        fun onItemClick(item: Categoria, Cat: String)
    }


    inner class MiViewHolder(view: View): RecyclerView.ViewHolder(view) {
        val cardListado: CardView = view.findViewById(R.id.cv_listado)
        val imagenListado: ImageView = view.findViewById(R.id.listado_imagen)
        val txtListadoCateg: TextView = view.findViewById(R.id.listado_categ)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MiViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_listado, parent, false)
        return MiViewHolder(view)
    }

    override fun getItemCount(): Int {
       return categLista.size
    }

    override fun onBindViewHolder(holder: MiViewHolder, position: Int) {
        val categoria = categLista[position]

        holder.txtListadoCateg.text = categoria.descripcion

        val imageName = categoria.imagen
        val imageResourceId = resources.getIdentifier(imageName, "drawable", context.packageName)
        holder.imagenListado.setImageResource(imageResourceId)

        holder.cardListado.setOnClickListener {
            if (position != RecyclerView.NO_POSITION) {
                listener.onItemClick(categoria, categoria.descripcion)
            }
        }


    }
}