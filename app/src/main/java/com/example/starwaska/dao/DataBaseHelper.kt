package com.example.starwaska.dao

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DataBaseHelper(context: Context)
    : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

        companion object {
            private const val DATABASE_NAME = "StarWanka.db"
            private const val DATABASE_VERSION = 2

    }

    override fun onCreate(db: SQLiteDatabase) {

        val sql =
            "CREATE TABLE IF NOT EXISTS Producto (id INTEGER PRIMARY KEY AUTOINCREMENT, categoria TEXT NOT NULL, nombre TEXT NOT NULL, descripcion TEXT NOT NULL, precio REAL NOT NULL, descuento REAL NOT NULL, imagen TEXT NOT NULL, clickeado BOOLEAN NOT NULL)"
        db.execSQL(sql)

    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {

        if (oldVersion < 2) {
            val sql2 = "CREATE TABLE IF NOT EXISTS Categoria (id INTEGER PRIMARY KEY AUTOINCREMENT, descripcion TEXT NOT NULL, imagen TEXT NOT NULL)"
            db.execSQL(sql2)
        }

    }

}

/*
db.execSQL("DROP TABLE IF EXISTS Producto")
onCreate(db)

db.execSQL("DROP TABLE IF EXISTS Categoria")
onCreate(db)*/
