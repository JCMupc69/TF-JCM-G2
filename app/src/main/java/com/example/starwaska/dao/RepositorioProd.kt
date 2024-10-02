package com.example.starwaska.dao

import com.example.starwaska.recyclerCategoria.Producto

class RepositorioProd(private val dao: DAO) {

    fun insertarProd(id: Int,
                     categoria: String,
                     nombre: String,
                     descripcion: String,
                     precio: Double,
                     descuento: Double,
                     imagen: String,
                     clickeado: Boolean): Long {
        return dao.insertarProd(id, categoria, nombre, descripcion,
            precio, descuento, imagen, clickeado)
    }

    fun obtenerProd(categ: String): List<Producto> {
        return dao.obtenerProd(categ)

    }
    fun actualizarClick(id: Int, click: Int) {
        dao.actualizarClick(id, click)
    }
    fun eliminarProd(id: Int) {
        dao.eliminar(id)
    }
}
