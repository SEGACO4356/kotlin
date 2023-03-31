package com.mau.entrega2

class Usuario {
    constructor(nombre: String, apellidos:String ,edad: Int, telefono: Int, email: String,
                direccion: String, genero: String){
        this.nombre = nombre
        this.edad = edad
        this.apellidos = apellidos
        this.telefono = telefono
        this.email = email
        this.direccion = direccion
        this.genero = genero
    }

    var id:Int=0
    var nombre:String=""
    var apellidos:String=""
    var edad:Int=0
    var telefono:Int=0
    var email:String=""
    var direccion:String=""
    var genero:String=""
    constructor(){

    }
}