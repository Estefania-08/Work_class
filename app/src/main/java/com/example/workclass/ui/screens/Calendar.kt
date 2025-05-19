package com.example.workclass.ui.screens

import android.Manifest
import android.annotation.SuppressLint
import android.app.DatePickerDialog
import android.content.ContentProviderOperation
import android.content.ContentValues
import android.content.Context
import android.content.Intent
import android.provider.CalendarContract
import android.provider.ContactsContract
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import java.util.Calendar
import java.util.TimeZone


@Composable
fun Calendar(navController: NavController){

    AppScreen(navController)

}

@SuppressLint("QueryPermissionsNeeded")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppScreen(navController: NavController) {

    val context = LocalContext.current

    fun openContacts(context: Context){
        val intent = Intent(Intent.ACTION_PICK, ContactsContract.Contacts.CONTENT_URI)
        if (intent.resolveActivity(context.packageManager) != null){
            context.startActivity(intent)
        } else {
            Toast.makeText(context, "No app to handle this action", Toast.LENGTH_SHORT).show()
        }
    }
    fun getContacts(context: Context):List<String>{
        val lista = mutableListOf<String>()
        val cursor = context.contentResolver.query(
            ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
            null, null, null, null
        )
        cursor?.use{
            val nombreIndex = it.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME)
            val numeroIndex = it.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER)
            while (it.moveToNext()){
                val nombre = it.getString(nombreIndex)
                val numero = it.getString(numeroIndex)
                lista.add("$nombre: $numero")
            }
        }
        return lista
    }
    fun addContact(context: Context, nombre:String, telefono:String){
        val ops = ArrayList<ContentProviderOperation>().apply {
            add(
                ContentProviderOperation.newInsert(ContactsContract.RawContacts.CONTENT_URI)
                    .withValue(ContactsContract.RawContacts.ACCOUNT_TYPE, null)
                    .withValue(ContactsContract.RawContacts.ACCOUNT_NAME, null)
                    .build()
            )
            add(
                ContentProviderOperation.newInsert(ContactsContract.Data.CONTENT_URI)
                    .withValueBackReference(ContactsContract.Data.RAW_CONTACT_ID, 0)
                    .withValue(ContactsContract.Data.MIMETYPE, ContactsContract.CommonDataKinds.StructuredName.CONTENT_ITEM_TYPE)
                    .withValue(ContactsContract.CommonDataKinds.StructuredName.DISPLAY_NAME, nombre)
                    .build()
            )
            add(
                ContentProviderOperation.newInsert(ContactsContract.Data.CONTENT_URI)
                    .withValueBackReference(ContactsContract.Data.RAW_CONTACT_ID, 0)
                    .withValue(ContactsContract.Data.MIMETYPE, ContactsContract.CommonDataKinds.Phone.CONTENT_ITEM_TYPE)
                    .withValue(ContactsContract.CommonDataKinds.Phone.NUMBER, telefono)
                    .withValue(ContactsContract.CommonDataKinds.Phone.TYPE, ContactsContract.CommonDataKinds.Phone.TYPE_MOBILE)
                    .build()
            )
        }
        try {
            context.contentResolver.applyBatch(ContactsContract.AUTHORITY, ops)
        }catch (exception: Exception){
            exception.printStackTrace()
        }
    }
    fun addEvent(context: Context, titulo:String, descripcion: String, inicio:Long, fin:Long){
        val values = ContentValues().apply {
            put(CalendarContract.Events.DTSTART, inicio)
            put(CalendarContract.Events.DTEND, fin)
            put(CalendarContract.Events.TITLE, titulo)
            put(CalendarContract.Events.DESCRIPTION, descripcion)
            put(CalendarContract.Events.CALENDAR_ID, 1)
            put(CalendarContract.Events.EVENT_TIMEZONE, TimeZone.getDefault().id)
        }
        context.contentResolver.insert(CalendarContract.Events.CONTENT_URI, values)
    }

    // Permisos
    var permisoContactosConcedido by remember { mutableStateOf(false) }
    var permisoCalendarioConcedido by remember { mutableStateOf(false) }

    val launcherPermisoContactosMostrar = rememberLauncherForActivityResult(
        ActivityResultContracts.RequestPermission()
    ) { concedido ->
        permisoContactosConcedido = concedido
    }

    val launcherPermisoContactosAbrir = rememberLauncherForActivityResult(
        ActivityResultContracts.RequestMultiplePermissions()
    ) { permisos ->
        permisoContactosConcedido =
            permisos[Manifest.permission.READ_CONTACTS] == true &&
                    permisos[Manifest.permission.WRITE_CONTACTS] == true

        if (permisoContactosConcedido) {
            openContacts(context)
        } else {
            Toast.makeText(context, "Permisos de contactos denegados", Toast.LENGTH_SHORT).show()
        }
    }

    val launcherPermisoCalendario = rememberLauncherForActivityResult(
        ActivityResultContracts.RequestMultiplePermissions()
    ) { permisos ->
        permisoCalendarioConcedido =
            permisos[Manifest.permission.READ_CALENDAR] == true &&
                    permisos[Manifest.permission.WRITE_CALENDAR] == true
    }

    // Estados de UI
    var mostrarContactos by remember { mutableStateOf(false) }
    var contactos by remember { mutableStateOf(emptyList<String>()) }

    var nuevoNombre by remember { mutableStateOf("") }
    var nuevoTelefono by remember { mutableStateOf("") }

    var titulo by remember { mutableStateOf("") }
    var descripcion by remember { mutableStateOf("") }
    var fechaSeleccionada by remember { mutableStateOf("") }
    var fechaMillis by remember { mutableStateOf<Long?>(null) }


    // Cargar contactos si es necesario
    LaunchedEffect(permisoContactosConcedido, mostrarContactos) {
        if (permisoContactosConcedido && mostrarContactos) {
            contactos = getContacts(context)
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(title = { Text("Agenda & Contactos") })
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .padding(16.dp)
                .verticalScroll(rememberScrollState())
                .fillMaxSize()
        ) {

            // Mostrar contactos
            Button(onClick = {
                if (!permisoContactosConcedido) {
                    launcherPermisoContactosMostrar.launch(Manifest.permission.READ_CONTACTS)
                } else {
                    mostrarContactos = !mostrarContactos
                    if (mostrarContactos) {
                        contactos = getContacts(context)
                    }
                }
            }) {
                Text(if (mostrarContactos) "Ocultar contactos" else "Mostrar contactos")
            }

            Spacer(modifier = Modifier.height(8.dp))

            // Abrir contactos del sistema
            Button(onClick = {
                launcherPermisoContactosAbrir.launch(
                    arrayOf(
                        Manifest.permission.READ_CONTACTS,
                        Manifest.permission.WRITE_CONTACTS
                    )
                )
            }) {
                Icon(Icons.Default.Person, contentDescription = "Abrir contactos")
                Spacer(modifier = Modifier.width(4.dp))
                Text("Abrir contactos")
            }

            Spacer(modifier = Modifier.height(12.dp))

            if (mostrarContactos && permisoContactosConcedido) {
                Card(modifier = Modifier.fillMaxWidth()) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        Text("📇 Contactos", style = MaterialTheme.typography.titleMedium)
                        Spacer(modifier = Modifier.height(8.dp))
                        LazyColumn(modifier = Modifier.heightIn(max = 300.dp)) {
                            items(contactos) { contacto ->
                                Text("• $contacto", modifier = Modifier.padding(4.dp))
                            }
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(24.dp))

            // Guardar contacto
            Text("➕ Agregar contacto", style = MaterialTheme.typography.titleLarge)
            OutlinedTextField(
                value = nuevoNombre,
                onValueChange = { nuevoNombre = it },
                label = { Text("Nombre") },
                modifier = Modifier.fillMaxWidth()
            )
            OutlinedTextField(
                value = nuevoTelefono,
                onValueChange = { nuevoTelefono = it },
                label = { Text("Teléfono") },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone),
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(8.dp))

            Button(onClick = {
                if (!permisoContactosConcedido) {
                    launcherPermisoContactosAbrir.launch(
                        arrayOf(
                            Manifest.permission.READ_CONTACTS,
                            Manifest.permission.WRITE_CONTACTS
                        )
                    )
                    return@Button
                }

                if (nuevoNombre.isNotBlank() && nuevoTelefono.isNotBlank()) {
                    addContact(context, nuevoNombre, nuevoTelefono)
                    Toast.makeText(context, "Contacto agregado", Toast.LENGTH_SHORT).show()
                    nuevoNombre = ""
                    nuevoTelefono = ""
                    contactos = getContacts(context)
                } else {
                    Toast.makeText(context, "Completa los campos", Toast.LENGTH_SHORT).show()
                }
            }) {
                Text("Guardar contacto")
            }

            Spacer(modifier = Modifier.height(24.dp))

            // Crear evento
            Text("📅 Crear evento", style = MaterialTheme.typography.titleLarge)
            OutlinedTextField(
                value = titulo,
                onValueChange = { titulo = it },
                label = { Text("Título") },
                modifier = Modifier.fillMaxWidth()
            )
            OutlinedTextField(
                value = descripcion,
                onValueChange = { descripcion = it },
                label = { Text("Descripción") },
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(8.dp))

            Button(onClick = {
                val calendario = Calendar.getInstance()
                DatePickerDialog(
                    context,
                    { _, year, month, dayOfMonth ->
                        val cal = Calendar.getInstance().apply {
                            set(year, month, dayOfMonth, 9, 0)
                        }
                        fechaSeleccionada = "$dayOfMonth/${month + 1}/$year"
                        fechaMillis = cal.timeInMillis
                    },
                    calendario.get(Calendar.YEAR),
                    calendario.get(Calendar.MONTH),
                    calendario.get(Calendar.DAY_OF_MONTH)
                ).show()
            }) {
                Text(if (fechaSeleccionada.isNotEmpty()) "📅 $fechaSeleccionada" else "📅 Seleccionar fecha")
            }

            Spacer(modifier = Modifier.height(8.dp))

            Button(onClick = {
                if (!permisoCalendarioConcedido) {
                    launcherPermisoCalendario.launch(
                        arrayOf(
                            Manifest.permission.READ_CALENDAR,
                            Manifest.permission.WRITE_CALENDAR
                        )
                    )
                    return@Button
                }

                if (titulo.isNotBlank() && fechaMillis != null) {
                    val fin = fechaMillis!! + 60 * 60 * 1000
                    addEvent(context, titulo, descripcion, fechaMillis!!, fin)
                    Toast.makeText(context, "Evento creado", Toast.LENGTH_SHORT).show()

                    titulo = ""
                    descripcion = ""
                    fechaSeleccionada = ""
                    fechaMillis = null
                } else {
                    Toast.makeText(context, "Completa todos los campos", Toast.LENGTH_SHORT).show()
                }
            }) {
                Text("Guardar evento")
            }

            Spacer(modifier = Modifier.height(8.dp))

            // Abrir app de calendario del sistema



            Spacer(modifier = Modifier.height(80.dp))
        }

    }

}