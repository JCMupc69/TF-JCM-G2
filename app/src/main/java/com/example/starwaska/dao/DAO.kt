package com.example.starwaska.dao

import android.annotation.SuppressLint
import android.content.ContentValues
import android.database.Cursor
import com.example.starwaska.recyclerCategoria.Producto
import com.example.starwaska.recyclerListado.Categoria

class DAO (private val dataBaseHelper: DataBaseHelper) {

    fun insertarCat(id: Int,
                             descripcion: String,
                             imagen: String): Long {
        val indice: Long
        val values = ContentValues().apply {
            put("id", id)
            put("descripcion", descripcion)
            put("imagen", imagen)
        }
        val db = dataBaseHelper.writableDatabase
        try {
            indice = db.insert("Categoria",null, values)
            return indice
        } catch (e: Exception) {
            throw DAOException("DAO: Error al insertar: " + e.message)
        } finally {
            db.close()
        }
    }

    fun obtenerCat(): MutableList<Categoria>  {
        val db = dataBaseHelper.readableDatabase
        val lista = mutableListOf<Categoria>()
        try {
            val c: Cursor = db.rawQuery("SELECT * FROM Categoria", null)
            if (c.count > 0) {
                c.moveToFirst()
                do {
                    val id: Int = c.getInt(c.getColumnIndexOrThrow("id"))
                    val descripcion: String = c.getString(c.getColumnIndexOrThrow("descripcion"))
                    val imagen: String = c.getString(c.getColumnIndexOrThrow("imagen"))
                    var modelo = Categoria(id, descripcion, imagen)
                    lista.add(modelo)
                } while (c.moveToNext())
            }
            c.close()
        } catch (e: Exception) {
            throw DAOException("DAO: Error al obtener: " + e.message)
        } finally {
            db.close()
        }
        return lista
    }

    fun eliminarCat(id: Int) {
        val db = dataBaseHelper.writableDatabase
        try {
            val args = arrayOf(id.toString())
            db.execSQL("DELETE FROM Categoria WHERE id=?", args)
        } catch (e: Exception) {
            throw DAOException("DAO: Error al eliminar: " + e.message)
        } finally {
            db.close()
        }
    }

    fun insertarProd(id: Int,
                     categoria: String,
                     nombre: String,
                     descripcion: String,
                     precio: Double,
                     descuento: Double,
                     imagen: String,
                     clickeado: Boolean): Long {

        val indice: Long
        val values = ContentValues().apply {
            put("id", id)
            put("categoria", categoria)
            put("nombre", nombre)
            put("descripcion", descripcion)
            put("precio", precio)
            put("descuento", descuento)
            put("imagen", imagen)
            put("clickeado", clickeado)
        }
        val db = dataBaseHelper.writableDatabase
        try {
            indice = db.insert("Producto",null, values)
            return indice
        } catch (e: Exception) {
            throw DAOException("DAO: Error al insertar: " + e.message)
        } finally {
            db.close()
        }
    }

    fun obtenerProd(categ: String): List<Producto> {
        val db = dataBaseHelper.readableDatabase
        val lista = mutableListOf<Producto>()
        try {
            val c: Cursor = db.rawQuery("SELECT * FROM Producto WHERE categoria = '$categ'", null)
            if (c.count > 0) {
                c.moveToFirst()
                do {
                    val id: Int = c.getInt(c.getColumnIndexOrThrow("id"))
                    val categoria: String = c.getString(c.getColumnIndexOrThrow("categoria"))
                    val nombre: String = c.getString(c.getColumnIndexOrThrow("nombre")).replace("\\n", "\n")
                    val descripcion: String = c.getString(c.getColumnIndexOrThrow("descripcion"))
                    val precio: Double = c.getDouble(c.getColumnIndexOrThrow("precio"))
                    val descuento: Double = c.getDouble(c.getColumnIndexOrThrow("descuento"))
                    val imagen: String = c.getString(c.getColumnIndexOrThrow("imagen"))
                    val clickeado: Boolean = c.getInt(c.getColumnIndexOrThrow("clickeado")) == 1

                    val modelo = Producto(id, categoria, nombre, descripcion, precio, descuento, imagen, clickeado)
                    lista.add(modelo)
                } while (c.moveToNext())
            }
            c.close()
        } catch (e: Exception) {
            throw DAOException("DAO: Error al obtener: " + e.message)
        } finally {
            db.close()
        }
        return lista
    }

    fun actualizarClick(id: Int, click: Int) {
        val db = dataBaseHelper.writableDatabase
        try {
            val args = arrayOf(id.toString())
            db.execSQL("UPDATE Producto SET clickeado = $click WHERE id = ?", args)
        } catch (e: Exception) {
            throw DAOException("DAO: Error al actualizar: " + e.message)
        } finally {
            db.close()
        }
    }

    fun clickToFalse() {
        val db = dataBaseHelper.writableDatabase
        try {
            db.execSQL("UPDATE Producto SET clickeado = 0 WHERE clickeado = 1")
        } catch (e: Exception) {
            throw DAOException("Error al actualizar: " + e.message)
        } finally {
            db.close()
        }
    }

    fun eliminar(id: Int) {
        val db = dataBaseHelper.writableDatabase
        try {
            val args = arrayOf(id.toString())
            db.execSQL("DELETE FROM Producto WHERE id=?", args)
        } catch (e: Exception) {
            throw DAOException("DAO: Error al eliminar: " + e.message)
        } finally {
            db.close()
        }
    }

}