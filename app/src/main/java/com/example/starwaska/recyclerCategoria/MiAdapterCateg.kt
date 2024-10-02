package com.example.starwaska.recyclerCategoria

import android.annotation.SuppressLint
import android.content.Context
import android.content.res.Resources
import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat
import androidx.core.content.res.ResourcesCompat
import androidx.core.view.marginTop
import androidx.recyclerview.widget.RecyclerView
import com.example.starwaska.R
import com.example.starwaska.dao.DAO
import com.example.starwaska.dao.DataBaseHelper

class MiAdapterCateg(private val productoLista:List<Producto>,
                     private val listener: OnItemClickListener,
                     private val resources: Resources,
                     val context: Context)
    : RecyclerView.Adapter<MiAdapterCateg.MiViewHolder>() {

    private var dbHelper = DataBaseHelper(context)
    private var db: DAO = DAO(dbHelper)
    private val lista = productoLista

    interface OnItemClickListener {
        fun onItemClick(item: Producto)
        fun onAgregarClick(item: Producto, position: Int)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MiViewHolder {

        if (lista[0].categ == "Vinos") {
            val view =
                LayoutInflater.from(parent.context).inflate(R.layout.item_producto2, parent, false)
            return MiViewHolder(view)
        } else {
            val view =
                LayoutInflater.from(parent.context).inflate(R.layout.item_producto, parent, false)
            return MiViewHolder(view)
        }

        /*val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_producto, parent, false)
        return MiViewHolder(view)*/
    }

    override fun getItemCount(): Int {
        return productoLista.size
    }

    @SuppressLint("DefaultLocale")
    override fun onBindViewHolder(holder: MiViewHolder, position: Int) {

        val productos = productoLista[position]

        if (productos.categ == "Vinos") {
            holder.txtNombre.text = productos.nombre
        } else {
            holder.txtNombre.text = productos.nombre.replace("\n", " ")
        }

        holder.txtDescrip.text = productos.descripcion

        val precioFinal = productos.precio * (1 - productos.descuento)
        holder.txtPrecio.text = String.format("%.2f", precioFinal)

        val imageName = productos.imagen
        val imageResourceId = resources.getIdentifier(imageName, "drawable", context.packageName)
        holder.imgProducto.setImageResource(imageResourceId)

        if (productos.clickeado) {
            val imgCheck: Drawable? = ResourcesCompat.getDrawable(context.resources, R.drawable.ic_check2, null)
            imgCheck?.setBounds(0, 0, 50, 50)

            holder.txtAgregar.setCompoundDrawablesRelative(null,null,imgCheck,null)
            holder.txtAgregar.compoundDrawablePadding = -10
            holder.txtAgregar.text = "Añadido"
            holder.txtAgregar.setTextSize(18.0F)
            holder.txtAgregar.setBackgroundColor(ContextCompat.getColor(holder.itemView.context, R.color.white))
            holder.txtAgregar.setTextColor(ContextCompat.getColor(holder.itemView.context, R.color.green))
            holder.txtAgregar.setPaddingRelative(0,0,10,0)

        } else {
            holder.txtAgregar.setCompoundDrawablesRelative(null,null,null,null)
            holder.txtAgregar.compoundDrawablePadding = 0
            holder.txtAgregar.setBackgroundResource(R.drawable.caja_agregar)
            holder.txtAgregar.text = "Añadir"
            holder.txtAgregar.setTextSize(16.0F)
            holder.txtAgregar.setTextColor(ContextCompat.getColor(holder.itemView.context, R.color.white))
            holder.txtAgregar.setPaddingRelative(0,0,0,0)
        }

        holder.txtAgregar.setOnClickListener {
            if (!productos.clickeado) {
                productos.clickeado = true
                db.actualizarClick(productos.id, 1)
                notifyItemChanged(position)

                if (position != RecyclerView.NO_POSITION) {
                    listener.onAgregarClick(productos, position)
                }
            }
        }

        holder.cardView.setOnClickListener {
            if (position != RecyclerView.NO_POSITION) {
                listener.onItemClick(productos)
            }
        }
    }

    inner class MiViewHolder(view: View): RecyclerView.ViewHolder(view) {
        val imgProducto: ImageView = view.findViewById(R.id.producto_imagen)
        var txtNombre: TextView = view.findViewById(R.id.producto_nom)
        val txtDescrip: TextView = view.findViewById(R.id.producto_descrip)
        val txtPrecio: TextView = view.findViewById(R.id.producto_precio)
        val cardView : CardView = view.findViewById(R.id.cardView_producto)
        val txtAgregar: TextView = view.findViewById((R.id.agregar_Prod))
    }

    fun dibujarBoton(holder: MiViewHolder, categ: Producto) {
        if (categ.clickeado) {
            val imgCheck: Drawable? = ResourcesCompat.getDrawable(context.resources, R.drawable.ic_check2, null)
            imgCheck?.setBounds(0, 0, 50, 50)

            holder.txtAgregar.setCompoundDrawablesRelative(null,null,imgCheck,null)
            holder.txtAgregar.compoundDrawablePadding = -10
            holder.txtAgregar.text = "Añadido"
            holder.txtAgregar.setTextSize(18.0F)
            holder.txtAgregar.setBackgroundColor(ContextCompat.getColor(holder.itemView.context, R.color.white))
            holder.txtAgregar.setTextColor(ContextCompat.getColor(holder.itemView.context, R.color.green))
            holder.txtAgregar.setPaddingRelative(0,0,10,0)

        } else {
            holder.txtAgregar.setCompoundDrawablesRelative(null,null,null,null)
            holder.txtAgregar.compoundDrawablePadding = 0
            holder.txtAgregar.setBackgroundResource(R.drawable.caja_agregar)
            holder.txtAgregar.text = "Añadir"
            holder.txtAgregar.setTextSize(16.0F)
            holder.txtAgregar.setTextColor(ContextCompat.getColor(holder.itemView.context, R.color.white))
            holder.txtAgregar.setPaddingRelative(0,0,0,0)
        }

    }

}