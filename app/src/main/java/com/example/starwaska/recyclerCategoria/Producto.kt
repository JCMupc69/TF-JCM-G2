package com.example.starwaska.recyclerCategoria

data class Producto(
    val id: Int,
    val categ: String,
    val nombre: String,
    val descripcion: String,
    val precio: Double,
    val descuento: Double,
    val imagen: String,
    var clickeado: Boolean = false
)
