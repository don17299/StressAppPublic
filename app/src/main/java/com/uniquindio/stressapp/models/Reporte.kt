package com.uniquindio.stressapp.models

import java.util.ArrayList

class Reporte() {

    var usuarios:ArrayList<Map<String,*>> = ArrayList()

    fun mostrarCalificaciones():String{
        var reporte="Personas por cada nivel de Calificacion:\n\n"
        var cali11=0
        var cali12=0
        var cali13=0
        var cali14=0
        var cali15=0
        var cali21=0
        var cali22=0
        var cali23=0
        var cali24=0
        var cali25=0
        var cali31=0
        var cali32=0
        var cali33=0
        var cali34=0
        var cali35=0
        var caliNo=0

        for (user in usuarios) {
            val c1 = (user["calificacion1"] as Long).toInt()
            val c2 = (user["calificacion2"] as Long).toInt()
            val c3 = (user["calificacion3"] as Long).toInt()

            if (c1!=-1 && c2!=-1 && c3!=-1) {
                when (c1) {
                    1 -> cali11 += 1
                    2 -> cali12 += 1
                    3 -> cali13 += 1
                    4 -> cali14 += 1
                    5 -> cali15 += 1
                }
                when (c2) {
                    1 -> cali21 += 1
                    2 -> cali22 += 1
                    3 -> cali23 += 1
                    4 -> cali24 += 1
                    5 -> cali25 += 1
                }
                when (c3) {
                    1 -> cali31 += 1
                    2 -> cali32 += 1
                    3 -> cali33 += 1
                    4 -> cali34 += 1
                    5 -> cali35 += 1
                }
            }else{
                caliNo+=1
            }
        }

        reporte+="Funcionalidad de la App:\nEstrellas   ||   #Personas\n"
        reporte+="1 *:  || ${cali11}\n"
        reporte+="2 *:  || ${cali12}\n"
        reporte+="3 *:  || ${cali13}\n"
        reporte+="4 *:  || ${cali14}\n"
        reporte+="5 *:  || ${cali15}\n\n"

        reporte+="Calidad del contenido:\nEstrellas   ||   #Personas\n"
        reporte+="1 *:  || ${cali21}\n"
        reporte+="2 *:  || ${cali22}\n"
        reporte+="3 *:  || ${cali23}\n"
        reporte+="4 *:  || ${cali24}\n"
        reporte+="5 *:  || ${cali25}\n\n"
        reporte+="Accesibilidad de la App:\nEstrellas   ||   #Personas\n"
        reporte+="1 *:  || ${cali31}\n"
        reporte+="2 *:  || ${cali32}\n"
        reporte+="3 *:  || ${cali33}\n"
        reporte+="4 *:  || ${cali34}\n"
        reporte+="5 *:  || ${cali35}\n\n"
        reporte+="Personas que no calificaron: ${caliNo}\n\n"



        return reporte
    }

    fun mostrarAyuda():String{
        var reporte="Personas Que solicitaron ayuda:\nnombre          ||correo\n\n"

        for (user in usuarios) {
            val needHelp = user["needHelp"].toString().toBoolean()

            if (needHelp) {
                reporte+=user["nombre"].toString() + " || " + user["correo"].toString()+"\n"
            }
        }

        return reporte
    }

    fun mostrarMejoria():String{
        var reporte="Personas Que presentaron mejoria en la segunda encuesta:\nnombre||correo|| Puntaje E1|| Puntaje E2\n\n"
        var puntaje1=0
        var puntaje2=0
        for (user in usuarios) {
            val encuesta1 = user["encuesta1"] as List<*>
            val encuesta2 = user["encuesta2"] as List<*>
            for (i in 0 until (encuesta1.size)){
                puntaje1+= (encuesta1[i] as Long).toInt()
            }
            for (i in 0 until (encuesta2.size)){
                puntaje2+= (encuesta2[i] as Long).toInt()
            }
            if(puntaje2<puntaje1){
                reporte+=user["nombre"].toString() + " || " + user["correo"].toString() + " || " + obtenerPuntaje(user["encuesta1"] as List<*>) + " || " +obtenerPuntaje(user["encuesta2"] as List<*>) +"\n"
            }

        }

        return reporte
    }

    private fun obtenerPuntaje(lista:List<*>):Int{
        var puntaje=0

        for (i in 0 until (lista.size)){
            puntaje+= (lista[i] as Long).toInt()
        }

        return puntaje
    }


    private fun obtenerFrecuenciaRespuestas():String{

        var reporte = "Frecuencia en respuestas de las encuestas:\nRespuesta:  Encuesta1 || Encuesta2\n\n"
        val cantidadesE1 = Array(10){IntArray(5)}
        val cantidadesE2 = Array(10){IntArray(5)}

        for (user in usuarios) {
            val encuesta1 = user["encuesta1"] as List<*>
            val encuesta2 = user["encuesta2"] as List<*>
            for(i in encuesta1.indices) {

                    if (i == 3 || i == 4 || i == 6 || i == 7) {
                        when ((encuesta1[i] as Long).toInt()) {
                            4 -> cantidadesE1[i][0] = cantidadesE1[i][0]+1
                            3 -> cantidadesE1[i][1] = cantidadesE1[i][1]+1
                            2 -> cantidadesE1[i][2] = cantidadesE1[i][2]+1
                            1 -> cantidadesE1[i][3] = cantidadesE1[i][3]+1
                            0 -> cantidadesE1[i][4] = cantidadesE1[i][4]+1
                        }
                        when ((encuesta2[i] as Long).toInt()) {
                            4 -> cantidadesE2[i][0] = cantidadesE2[i][0]+1
                            3 -> cantidadesE2[i][1] = cantidadesE2[i][1]+1
                            2 -> cantidadesE2[i][2] = cantidadesE2[i][2]+1
                            1 -> cantidadesE2[i][3] = cantidadesE2[i][3]+1
                            0 -> cantidadesE2[i][4] = cantidadesE2[i][4]+1
                        }
                    } else {
                        when ((encuesta1[i] as Long).toInt()) {
                            0 -> cantidadesE1[i][0] = cantidadesE1[i][0]+1
                            1 -> cantidadesE1[i][1] = cantidadesE1[i][1]+1
                            2 -> cantidadesE1[i][2] = cantidadesE1[i][2]+1
                            3 -> cantidadesE1[i][3] = cantidadesE1[i][3]+1
                            4 -> cantidadesE1[i][4] = cantidadesE1[i][4]+1
                        }
                        when ((encuesta2[i] as Long).toInt()) {
                            0 -> cantidadesE2[i][0] = cantidadesE2[i][0]+1
                            1 -> cantidadesE2[i][1] = cantidadesE2[i][1]+1
                            2 -> cantidadesE2[i][2] = cantidadesE2[i][2]+1
                            3 -> cantidadesE2[i][3] = cantidadesE2[i][3]+1
                            4 -> cantidadesE2[i][4] = cantidadesE2[i][4]+1
                        }
                    }
            }
        }

        for(j in cantidadesE1.indices) {
            reporte += "Pregunta ${(j+1)}:\n\n"
            reporte+=  "Nunca:                      ${cantidadesE1[j][0]}  ||  ${cantidadesE2[j][0]}\n"
            reporte += "Casi Nunca:             ${cantidadesE1[j][1]}  ||  ${cantidadesE2[j][1]}\n"
            reporte += "De vez en Cuando: ${cantidadesE1[j][2]}  ||  ${cantidadesE2[j][2]}\n"
            reporte += "Casi Siempre:          ${cantidadesE1[j][3]}  ||  ${cantidadesE2[j][3]}\n"
            reporte += "Siempre:                   ${cantidadesE1[j][4]}  ||  ${cantidadesE2[j][4]}\n\n"

        }

        return reporte
    }

    fun reporte(): String {

        return mostrarCalificaciones() + "\n\n" + mostrarAyuda() + "\n\n" + mostrarMejoria()+ "\n\n"+ obtenerFrecuenciaRespuestas()
    }
}