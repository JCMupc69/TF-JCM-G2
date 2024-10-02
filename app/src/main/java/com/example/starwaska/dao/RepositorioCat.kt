package com.example.starwaska.dao

import com.example.starwaska.recyclerListado.Categoria

class RepositorioCat(private val dao: DAO) {

    fun insertarCat(id: Int,
                    descripcion: String,
                    imagen: String): Long {
        return dao.insertarCat(id, descripcion, imagen)
    }

    fun obtenerCat(): MutableList<Categoria> {
        return dao.obtenerCat()
    }

    fun eliminar(id: Int) {
        dao.eliminarCat(id)
    }

}