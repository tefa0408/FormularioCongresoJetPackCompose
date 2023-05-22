package com.example.formulariocongresojetpackcompose

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Person
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun ListaAsistentes(
    funId:(String)-> Unit,
    funNombres : (String) -> Unit,
    funApellidos: (String)-> Unit,
    funEmail : (String) -> Unit,
    funTelefono: (String) -> Unit,
    funTipoSangre:(String)->Unit,
    funFecha: (String) -> Unit,
    funTextButton :(String) -> Unit,
    funIsEditando:(Boolean) -> Unit,
    funBorrarAsistente:(String) -> Unit,
    asistente: Asistente
){

    Column {
        Row(
            Modifier
                .fillMaxWidth()
                .padding(8.dp)
                .background(Color.LightGray)
        ) {
            Text(asistente.id.toString(), Modifier.weight(1f))
            Text(asistente.nombres + " " + asistente.apellidos, Modifier.weight(3f))

            Spacer(modifier = Modifier.width(5.dp))

            IconButton(
                onClick = {
                    // Acción al hacer clic en el botón "Editar"
                    funId(asistente.id.toString())
                    funNombres(asistente.nombres)
                    funApellidos(asistente.apellidos)
                    funEmail(asistente.email)
                    funFecha(asistente.fecha)
                    funTipoSangre(asistente.tipoSangre)
                    funTextButton("Editar Asistente")
                    funIsEditando(true)
                }
            ) {
                Icon(Icons.Default.Edit, contentDescription = "Editar")
            }

            IconButton(
                onClick = {
                    // Acción al hacer clic en el botón "Eliminar"
                    funBorrarAsistente(asistente.nombres)
                }
            ) {
                Icon(Icons.Default.Delete, contentDescription = "Eliminar")
            }

            IconButton(onClick = { /*TODO*/ }) {
                Icon(Icons.Default.Person, contentDescription = "Ver")

            }
        }
    }
}