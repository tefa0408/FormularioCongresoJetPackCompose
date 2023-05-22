package com.example.formulariocongresojetpackcompose

import android.os.Build
import android.util.Log
import android.widget.DatePicker
import android.widget.RadioGroup
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import java.time.LocalDate
import java.time.format.DateTimeFormatter

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun Formulario(
    id: Int,
    funId:(String)-> Unit,
    nombres: String,
    funNombres: (String) -> Unit,
    apellidos: String,
    funApellidos: (String) -> Unit,
    email: String,
    funEmail: (String) -> Unit,
    telefono: String,
    funTelefono: (String) -> Unit,
    fecha: String,
    funFecha: (String) -> Unit,
    tipoSangre: String,
    funTipoSangre: (String) -> Unit,
    isEditando: Boolean,
    funIsEditando: () -> Unit,
    textButton: String,
    funTextButton: (String) -> Unit,
    listaAsistente: MutableList<Asistente>,
    funResetCampos: () -> Unit,
){
    val today = LocalDate.now().minusDays(1)
    val opcionesTipoSangre = listOf(
        "A+",
        "A-",
        "B+",
        "B-",
        "AB+",
        "AB-",
        "O+",
        "O-"
    )
    var selectedOption by remember { mutableStateOf("") }

    Row (modifier = Modifier.fillMaxWidth()){

        OutlinedTextField(
            modifier = Modifier.weight(1f),
            value = nombres,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
            singleLine = true,
            maxLines = 1,
            onValueChange = { funNombres(it) },
            label = { Text(text = "Nombres") },
            //enabled = !isEditando
        )
        Spacer(modifier = Modifier.padding(horizontal = 3.dp))
        OutlinedTextField(
            modifier = Modifier.weight(1f),
            value = apellidos,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
            singleLine = true,
            maxLines = 1,
            onValueChange = { funApellidos(it) },
            label = { Text(text = "Apellidos") },
            //enabled = !isEditando
        )
    }
    Spacer(modifier = Modifier.padding(vertical = 5.dp))
    Row (modifier = Modifier.fillMaxWidth()) {
        OutlinedTextField(
            modifier = Modifier.weight(2f),
            value = email,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
            singleLine = true,
            maxLines = 1,
            onValueChange = { funEmail(it) },
            label = { Text(text = "Email") }
        )
        Spacer(modifier = Modifier.padding(horizontal = 3.dp))
        OutlinedTextField(
            modifier = Modifier.weight(1f),
            value = telefono,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            singleLine = true,
            maxLines = 1,
            onValueChange = { funTelefono(it) },
            label = { Text(text = "Telefono") }
        )
    }

    Spacer(modifier = Modifier.padding(vertical = 5.dp))
    Row (modifier = Modifier.fillMaxWidth()) {
        //"${today.dayOfMonth-1}/${today.month.value}/${today.year}"
        OutlinedTextField(
            modifier = Modifier.weight(2f),
            value = today.format(DateTimeFormatter.ofPattern("dd/MM/yyyy")),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
            singleLine = true,
            maxLines = 1,
            onValueChange = { funFecha(it) },
            label = { Text(text = "Fecha Inscripción") },
            enabled = false
        )

        /*RadioGroup {
            Column {
                opcionesTipoSangre.forEach { opcion ->
                    Row(
                        Modifier
                            .fillMaxWidth()
                            .padding(vertical = 4.dp)
                    ) {
                        RadioButton(
                            selected = tipoSangre == opcion,
                            onClick = { funTipoSangre(opcion) }
                        )
                        Text(text = opcion)
                    }
                }
            }
        }*/
    }


Spacer(modifier = Modifier.padding(vertical = 8.dp))
    Button(modifier = Modifier.fillMaxWidth(),
        colors = ButtonDefaults.buttonColors(backgroundColor = Color.DarkGray),
        onClick = {
            if (isEditando) {
                editarAsistente(id,nombres,apellidos, email,telefono,fecha, tipoSangre,listaAsistente)
                funTextButton("Agregar Usuario")
                funIsEditando()

            } else {
                agregarAsistente(id,nombres,apellidos, email,telefono,fecha, tipoSangre,listaAsistente)
            }
            funResetCampos()
        }
    ) {
        Text(
            color = Color.White,
            text = textButton
        )
    }

    // Encabezados de la tabla
    Row(Modifier.padding(4.dp)) {
        Text("N°", Modifier.weight(1f))
        Text("Nombres y Apellidos", Modifier.weight(3f))
        Spacer(modifier = Modifier.width(5.dp))
    }
}