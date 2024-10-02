package com.example.starwaska

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.starwaska.dao.RepositorioCat
import com.example.starwaska.dao.RepositorioProd
import com.example.starwaska.recyclerCarrito.Pedido
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SharedViewModel
@Inject constructor(private val repoCat: RepositorioCat,
                    private val repoProd: RepositorioProd ) : ViewModel() {

    private val _pedidos = MutableLiveData<List<Pedido>>()
    val pedidos: LiveData<List<Pedido>> get() = _pedidos

    private val _contador = MutableLiveData<Int>()
    val contador: LiveData<Int> get() = _contador

    fun agregarPedido(pedido: Pedido) {
        val currentPedidos = _pedidos.value.orEmpty().toMutableList()
        currentPedidos.add(pedido)
        _pedidos.value = currentPedidos
    }

    fun eliminarPedido(position: Int) {
        val currentPedidos = _pedidos.value.orEmpty().toMutableList()
        currentPedidos.removeAt(position)
        _pedidos.value = currentPedidos
    }

    fun obtenerListaPedidos(): List<Pedido> {
        return _pedidos.value.orEmpty()
    }

    fun clickStatus(position: Int) {
        val lista = repoProd.obtenerProd("Whiskeys")
        lista[position].clickeado = true

    }

    fun clickStatusFalse(pedido: Pedido) {
        val lista = repoProd.obtenerProd(pedido.categ)
        for (it in lista) {
            if (it.nombre == pedido.nombre)
                repoProd.actualizarClick(it.id, 0)
        }
    }

    fun sumarSubtotal(): Double {
        val lista = _pedidos.value.orEmpty()
        val total = lista.sumOf { it.subtotal }
        return total
    }

    fun setSubtotal(position: Int, cantidad: Int, subtotal: Double) {
        _pedidos.value?.get(position)?.cantidad = cantidad
        _pedidos.value?.get(position)?.subtotal = subtotal
        _pedidos.value = _pedidos.value
    }

    fun contador() {
        _contador.value = _pedidos.value?.size
        _contador.value = _contador.value
    }

    /*private val productos = MutableLiveData(mutableListOf(
        Producto("Whiskey","Johnnie Walker\nRed Label", "Botella 700 ml.", 112.5,0.2, "j_walker_red"),
        Producto("Whiskey", "Johnnie Walker\nBlack Label","Botella 700 ml.",124.875,0.2, "j_walker_black"),
        Producto("Whiskey","Chivas Regal\n12 años", "Botella 700 ml.", 143.75, 0.2,"chivas_regal_12" ),
        Producto("Whiskey","Jack Daniels\nBottled In Bond", "Botella 700 ml.", 398.75,0.2, "j_daniels_bond"),
        Producto("Whiskey", "Ballantines\n12 años","Botella 750 ml.", 146.25,0.2,"ballantines_12"),
        Producto("Whiskey", "Buchanans\n18 años","Botella 750 ml.",374.875, 0.2,"buchanans_18"),
        Producto("Whiskey", "Black Whiskey\nU (especial)","Botella 700 ml.",412.375,0.2,"black_whiskey"),
        Producto("Whiskey", "Old Parr\nWhiskey","Botella 750 ml.",136.25,0.2,"old_parr"),
        Producto("Whiskey", "Glenfiddich\n12 años","Botella 750 ml.",249.875,0.2,"glenfiddich"),
        Producto("Whiskey", "Johnnie Walker\nSwing","Botella 750 ml.",175.00,0.2,"swing_jw"),
        Producto("Whiskey", "Chivas Regal\nRoyal Salute","Botella 700 ml.",873.75,0.2,"cr_royalsalute"),
        Producto("Whiskey", "Tomatin\nSpecial Whiskey","Botella 700 ml.",474.875,0.2,"tomatin"),
        Producto("Whiskey", "Something Special\nWhiskey","Botella 700 ml.",93.75,0.2,"something_sp"),
        Producto("Whiskey", "The Glenlivet\n18 años","Botella 700 ml.",623.75,0.2,"glenlivet_18"),
        Producto("Whiskey", "Macallan\nDouble Cask","Botella 700 ml.",2749.875,0.2,"macallan_18")
    ))

    fun obtenerProductos(): List<Producto> {
        return productos.value.orEmpty()
    }*/


    /*val prodMarcado = productos.value?.get(position)
       prodMarcado?.clickeado = true
       productos.value = productos.value*/

    /*val prodMarcado = productos.value.orEmpty()
        for (it in prodMarcado) {
            if (it.nombre == pedido.nombre)
                it.clickeado = false
        }
        productos.value = productos.value*/

    //val lista = repository.obtener("Whiskeys")

}


