package com.mau.entrega2
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast

class MainActivity : AppCompatActivity() {

    lateinit var Id:EditText
    lateinit var nombre:EditText
    lateinit var apellidos:EditText
    lateinit var edad:EditText
    lateinit var telefono:EditText
    lateinit var direccion:EditText
    lateinit var email:EditText
    lateinit var genero:EditText
    lateinit var listaD:TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Id=findViewById(R.id.txtid)
        nombre=findViewById(R.id.txtnombre)
        apellidos=findViewById(R.id.txtapellidos)
        edad=findViewById(R.id.txtedad)
        telefono=findViewById(R.id.txttelefono)
        direccion=findViewById(R.id.txtdireccion)
        email=findViewById(R.id.txtemail)
        genero=findViewById(R.id.txtgenero)
        listaD=findViewById(R.id.txtlista)
    }
    fun guardar(view: View){
        var db= BaseDeDatos(this)
        var usu= Usuario()
        if (nombre.text.toString().length>0 && edad.text.toString().length>0 && apellidos.text.toString().length>0
            && telefono.text.toString().length>0 && direccion.text.toString().length>0 && email.text.toString().length>0
            && genero.text.toString().length>0){
            usu.nombre=nombre.text.toString()
            usu.edad=edad.text.toString().toInt()
            usu.apellidos=apellidos.text.toString()
            usu.telefono=telefono.text.toString().toInt()
            usu.direccion=direccion.text.toString()
            usu.email=email.text.toString()
            usu.genero=genero.text.toString()
            var mensaje = db.insertardatos(usu)
            Toast.makeText(this, mensaje, Toast.LENGTH_SHORT).show()
        }
    }

    fun actualizar(view: View){
        var db= BaseDeDatos(this)
        var usu = Usuario()
        if (Id.text.toString().length>0 && nombre.text.toString().length>0 && edad.text.toString().length>0 && apellidos.text.toString().length>0
            && telefono.text.toString().length>0 && direccion.text.toString().length>0 && email.text.toString().length>0
            && genero.text.toString().length>0){

            usu.nombre=nombre.text.toString()
            usu.edad=edad.text.toString().toInt()
            usu.apellidos=apellidos.text.toString()
            usu.telefono=telefono.text.toString().toInt()
            usu.direccion=direccion.text.toString()
            usu.email=email.text.toString()
            usu.genero=genero.text.toString()
            var mensaje = db.actualizardatos(Id.text.toString(), nombre.text.toString(), edad.text.toString().toInt(), apellidos.text.toString(), telefono.text.toString(), email.text.toString(), direccion.text.toString(), genero.text.toString())
            Toast.makeText(this, mensaje, Toast.LENGTH_SHORT).show()
        }
    }
    fun ListaDatos(view: View){
        listaD.text=""
        var db = BaseDeDatos(this)
        if (Id.text.toString().length>0){
            var datosL= db.listardatos()
            for (i in 0..datosL.size-1){
                listaD.append(" Id "+datosL.get(i).id+", nombre "
                +datosL.get(i).nombre+", edad "+datosL.get(i).edad.toString()+", apellidos "+datosL.get(i).apellidos+", telefono "+datosL.get(i).telefono.toString()+", direccion "+datosL.get(i).direccion+", email "+datosL.get(i).email
                        +", genero "+datosL.get(i).genero+"\n")
            }
        }
    }
    fun borrardatos(view: View){
        var db = BaseDeDatos(this)
        if (Id.text.toString().length>0){
            db.borrar(Id.text.toString())
        }
    }
}