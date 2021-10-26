package com.uyghar.yuzjumle

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import java.io.File
import java.io.FileOutputStream
import java.io.IOException
import java.lang.RuntimeException

class JumleHelper(val context: Context) {
    val dbName = "jumle.db"
    val database: SQLiteDatabase
    init {
       database = openDatabase()
    }

    private fun openDatabase(): SQLiteDatabase {
        val dbFile = context.getDatabasePath(dbName)
        if (!dbFile.exists()) {
            try {
                val db = context.openOrCreateDatabase(dbName, Context.MODE_PRIVATE, null)
                db.close()
                copyDatabase(dbFile)
            } catch (e: IOException) {
                throw RuntimeException("Hataliq koruldi")
            }
        }
        return SQLiteDatabase.openDatabase(dbFile.path, null, SQLiteDatabase.OPEN_READWRITE)

    }

    fun jumliler(talla:Boolean): ArrayList<JumleModel> {
        val shert = if (talla) " WHERE talla = 1" else ""
        val cursor = database.rawQuery("SELECT * FROM jumle" + shert,null)
        var tizim = ArrayList<JumleModel>()
        while (cursor.moveToNext()) {
            val id = cursor.getInt(0)
            val tr = cursor.getString(1)
            val uy = cursor.getString(2)
            val talla = cursor.getInt(3)
            val jumle = JumleModel(id, tr, uy, talla == 1)
            tizim.add(jumle)
        }
        return tizim
    }

    private fun copyDatabase(dbFile: File) {
        val esli = context.assets.open(dbName)
        val sandan = FileOutputStream(dbFile)

        val buffer = ByteArray(1024)
        while (esli.read(buffer) > 0) {
            sandan.write(buffer)
        }
        sandan.flush()
        sandan.close()
        esli.close()
    }
}