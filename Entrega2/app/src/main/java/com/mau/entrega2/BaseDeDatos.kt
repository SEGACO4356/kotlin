package com.mau.entrega2
import android.database.sqlite.SQLiteOpenHelper
import android.database.sqlite.SQLiteDatabase
import android.content.Context
import android.content.ContentValues
import android.annotation.SuppressLint
import android.provider.ContactsContract.CommonDataKinds.Email

class BaseDeDatos (context: Context):SQLiteOpenHelper(context, "DB", null,1){
    override fun onCreate(db: SQLiteDatabase?) {

        var sql="CREATE TABLE usuario (id INTEGER PRIMARY KEY AUTOINCREMENT, nombre VACRHAR(250), apellidos VARCHAR(250) ,edad INTEGER, telefono VARCHAR(250), email VARCHAR(250), direccion VARCHAR(250), genero VARCHAR(250))"
    db?.execSQL(sql)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int){
        TODO("Not yet implemented")
    }
    fun borrar(id:String) : String {
            val db = this.writableDatabase
            try {
                if (id.length > 0) {

                    db.delete("Usuario", "id=?", arrayOf(id))
                    db.close()
                    return "Se borró exitosamente"
                }
                else{
                    return "Ha ocurrido un error"
                }
            }catch (ex:Exception){
                return ex.message.toString()
            }

    }

    fun actualizardatos(id: String, nombre:String, edad:Int, apellidos:String, telefono:String, email:String, direccion:String, genero:String):String{
        val db = this.writableDatabase
        var contenedor = ContentValues()

        contenedor.put("nombre", nombre)
        contenedor.put("apellidos", apellidos)
        contenedor.put("edad",edad)
        contenedor.put("telefono",telefono)
        contenedor.put("email",email)
        contenedor.put("direccion",direccion)
        contenedor.put("genero", genero)

    try {

        var resultado = db.update("usuario", contenedor, "id=?", arrayOf(id))
        if (resultado > 0) {
            return "Actualización realizada"
        } else {
            return "No actualizado"
            }
        }catch (ex:Exception){
            return ex.message.toString()
        }finally {
            db.close()
        }
    }
    fun insertardatos(usuario: Usuario): String {
        val db = this.writableDatabase
        var contenedor = ContentValues()
        contenedor.put("nombre", usuario.nombre)
        contenedor.put("edad", usuario.edad)
        contenedor.put("apellidos", usuario.apellidos)
        contenedor.put("telefono",usuario.telefono)
        contenedor.put("email",usuario.email)
        contenedor.put("direccion",usuario.direccion)
        contenedor.put("genero", usuario.genero)
        try {


            var resultado = db.insert("usuario", null, contenedor)
            if (resultado == -1.toLong()) {
                return "Existió un error en la base de datos"
            }else{
                return "Insertado"
            }
        }catch (ex:Exception){
            return ex.message.toString()
        }finally {
            db.close()
        }
    }
    @SuppressLint("Range")
    fun listardatos():MutableList<Usuario>{
        val lista:MutableList<Usuario> = ArrayList()
        val db = this.readableDatabase
        val sql = "select * from usuario"
        var resultado = db.rawQuery(sql, null)
        if (resultado.moveToFirst()){
            do {
                var usu = Usuario()
                usu.id = resultado.getString(resultado.getColumnIndex("id")).toInt()
                usu.nombre = resultado.getString(resultado.getColumnIndex("nombre"))
                usu.apellidos = resultado.getString(resultado.getColumnIndex("apellidos"))
                usu.edad = resultado.getString(resultado.getColumnIndex("edad")).toInt()
                usu.email = resultado.getString(resultado.getColumnIndex("email"))
                usu.telefono = resultado.getString(resultado.getColumnIndex("telefono")).toInt()
                usu.direccion = resultado.getString(resultado.getColumnIndex("direccion"))
                usu.genero = resultado.getString(resultado.getColumnIndex("genero"))
                lista.add(usu)
            }while (resultado.moveToNext())
            resultado.close()
            db.close()
        }
        return lista
    }
}