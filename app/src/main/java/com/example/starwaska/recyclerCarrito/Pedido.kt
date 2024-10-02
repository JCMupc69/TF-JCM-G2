package com.example.starwaska.recyclerCarrito

data class Pedido (
    val id: Int,
    val categ: String,
    val nombre: String,
    val descripcion: String,
    val precio: Double,
    var cantidad: Int = 1,
    var subtotal: Double = 0.0
)
