package com.uniquindio.stressapp.models

import java.io.Serializable
import java.util.ArrayList

class Usuario(var nombre: String, var correo: String, var programa: String) :
    Serializable {
    var calificacion1 = -1
    var calificacion2 = -1
    var calificacion3 = -1
    var sugerencia="n.a"
    var isNeedHelp: Boolean
    var encuesta1: ArrayList<Int>
    var encuesta2: ArrayList<Int>

    init {
        encuesta1 = ArrayList()
        encuesta2 = ArrayList()
        isNeedHelp = false
    }
}
