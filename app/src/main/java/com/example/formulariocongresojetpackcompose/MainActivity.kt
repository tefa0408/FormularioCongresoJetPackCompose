package com.example.formulariocongresojetpackcompose

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.formulariocongresojetpackcompose.ui.theme.FormularioCongresoJetPackComposeTheme
import java.time.LocalDate

class MainActivity : ComponentActivity() {
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            FormularioCongresoJetPackComposeTheme {
                // lista para guardar objetos
                val listaAsistentes = remember { mutableStateListOf<Asistente>() }
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    //toma el máximo de la pantalla
                    Box(modifier = Modifier.fillMaxSize()){
                        ScreenCRUD(listaAsistentes)
                    }
                }
            }
        }
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun ScreenCRUD(listaAsistentes: MutableList<Asistente>){
    var id by remember { mutableStateOf(1) }
    var nombres by remember { mutableStateOf("") }
    var apellidos by remember { mutableStateOf("") }
    var email by remember{ mutableStateOf("") }
    var telefono by remember{ mutableStateOf("") }
    var fecha by remember { mutableStateOf("") }
    var tipoSangre by remember { mutableStateOf("") }
    var isEditando by remember { mutableStateOf(false) }
    var textButton by remember { mutableStateOf("Agregar Usuario") }

    Column(modifier = Modifier
        .fillMaxWidth()
        .fillMaxHeight()
        .padding(12.dp)) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
        ){
            Formulario(
                id = id,
                funId ={id=it.toInt()},
                nombres = nombres,
                funNombres = {nombres = it},
                apellidos = apellidos,
                funApellidos = {apellidos = it},
                email = email,
                funEmail = {email= it},
                telefono = telefono,
                funTelefono = {telefono= it},
                fecha = fecha,
                funFecha = {fecha= it},
                tipoSangre = tipoSangre,
                funTipoSangre = {tipoSangre= it},
                isEditando = isEditando,
                funIsEditando ={isEditando=false},
                textButton = textButton,
                funTextButton = { textButton= it},
                listaAsistente = listaAsistentes
            ) {
                nombres = ""
                apellidos = ""
                email = ""
                telefono=""
                id= posicionUltimoAsistente(listaAsistentes,isEditando)
            }
            Column (
                modifier = Modifier.fillMaxWidth()
            ){
                LazyColumn(
                    contentPadding = PaddingValues(horizontal = 16.dp,vertical= 8.dp)
                ){
                    items(listaAsistentes){ asistente ->
                        ListaAsistentes(
                            funId = {id=it.toInt()},
                            funNombres = { nombres = it},
                            funApellidos = {apellidos = it},
                            funEmail = { email = it},
                            funTelefono = { telefono = it},
                            funFecha = {fecha= it},
                            funTipoSangre = {tipoSangre= it},
                            funTextButton ={textButton=it},
                            funIsEditando ={isEditando=it},
                            funBorrarAsistente ={borrarAsistente(id,it, listaAsistentes)},
                            asistente = asistente
                        )

                    }
                }

            }
        }

    }

}


fun posicionUltimoAsistente(listaAsistentes: MutableList<Asistente>,isEditando: Boolean): Int {
    //val datoBuscado = Asistente(id,nombres,apellidos,email)
    //val posicion = listaAsistentes.indexOf(datoBuscado)
    var posicion = 0
    if(isEditando){
        posicion= listaAsistentes.size
    }else{
        posicion=listaAsistentes.size+1
    }

    return posicion
}
fun agregarAsistente(id: Int,nombres:String,apellidos:String, email:String,telefono:String,fecha:String,tipoSangre:String, listaAsistentes: MutableList<Asistente>){
    listaAsistentes.add(Asistente(id,nombres, apellidos,email,telefono, fecha, tipoSangre ))
}

fun editarAsistente(id: Int,nombres:String,apellidos:String,email: String,fecha:String,telefono:String,tipoSangre:String,listaAsistentes: MutableList<Asistente>){
    listaAsistentes.forEach { asistente ->
        if (asistente.id ==id){
            asistente.nombres=nombres
            asistente.apellidos=apellidos
            asistente.email = email
            asistente.telefono=telefono
            asistente.fecha=fecha
            asistente.tipoSangre=tipoSangre
        }
    }
}

fun borrarAsistente(id: Int,nombre: String, listaAsistentes: MutableList<Asistente>) {
    listaAsistentes.forEach { asistente ->
        if (asistente.nombres == nombre) {
            listaAsistentes.remove(asistente)
        }
    }
}