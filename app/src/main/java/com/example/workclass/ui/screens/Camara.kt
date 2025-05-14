package com.example.workclass.ui.screens

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import android.Manifest
import android.content.ContentValues
import android.content.Context
import android.net.Uri
import android.os.Build
import android.provider.MediaStore
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.camera.core.CameraSelector
import androidx.camera.core.ImageCapture
import androidx.camera.core.ImageCaptureException
import androidx.camera.core.Preview
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.content.ContextCompat
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import androidx.camera.view.PreviewView
import androidx.compose.ui.draw.clip
import java.text.SimpleDateFormat
import java.util.Locale

@Composable
fun Camara(navController: NavHostController){
    val context = LocalContext.current
    val lifecycleOwner = LocalLifecycleOwner.current
    var hasCameraPermission by remember { mutableStateOf(false) }
    var imageUri by remember { mutableStateOf<Uri?>(null) }
    val permissionLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestPermission()
    ) { granted ->
        hasCameraPermission = granted
        if (!granted) {
            Toast.makeText(context, "Permiso de cámara denegado",
                Toast.LENGTH_SHORT).show()
        }
    }
    val galleryLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent()
    ) { uri: Uri? ->
        imageUri = uri
    }
    val imageCapture = remember { ImageCapture.Builder().build() }
    LaunchedEffect(Unit) {
        permissionLauncher.launch(Manifest.permission.CAMERA)
    }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Subir reporte fotográfico", style =
            MaterialTheme.typography.titleLarge)
        // Mostrar cámara si tiene permiso
        if (hasCameraPermission) {
            AndroidView(
                factory = { ctx ->
                    val previewView = PreviewView(ctx)
                    val cameraProviderFuture =
                        ProcessCameraProvider.getInstance(ctx)
                    cameraProviderFuture.addListener({
                        val cameraProvider =
                            cameraProviderFuture.get()
                        val preview =
                            Preview.Builder().build().also {

                                it.setSurfaceProvider(previewView.surfaceProvider)
                            }
                        val cameraSelector =
                            CameraSelector.DEFAULT_BACK_CAMERA
                        try {
                            cameraProvider.unbindAll()
                            cameraProvider.bindToLifecycle(
                                lifecycleOwner,
                                cameraSelector,
                                preview,
                                imageCapture
                            )
                        } catch (e: Exception) {
                            e.printStackTrace()
                            Toast.makeText(ctx, "Error al iniciar la cámara", Toast.LENGTH_SHORT).show()
                        }
                    }, ContextCompat.getMainExecutor(ctx))
                    previewView
                },
                modifier = Modifier
                    .size(300.dp)
                    .clip(RoundedCornerShape(12.dp))
            )
        }
        Button(onClick = {
            takePhoto(context, imageCapture) {
                imageUri = it // Guardamos la URI capturada
            }
        }) {
            Text("Tomar Foto")
        }
        Button(onClick = {
            galleryLauncher.launch("image/*")
        }) {
            Text("Seleccionar de galería")
        }
        imageUri?.let {
            Image(
                painter = rememberAsyncImagePainter(it),
                contentDescription = "Imagen seleccionada",
                modifier = Modifier
                    .size(250.dp)
                    .clip(RoundedCornerShape(12.dp))
            )
        }
        Button(
            onClick = {
                Toast.makeText(context, "Imagen lista para subir",
                    Toast.LENGTH_SHORT).show()
                // Aquí podrías llamar a tu lógica de subida
            },
            enabled = imageUri != null
        ) {
            Text("Subir reporte")
        }
    }
}

fun takePhoto(context: Context, imageCapture: ImageCapture, onImageSaved: (Uri) -> Unit){
    val name = SimpleDateFormat("yyyyMMdd_HHmmss", Locale.getDefault())
        .format(System.currentTimeMillis())
    val contentValues = ContentValues().apply {
        put(MediaStore.MediaColumns.DISPLAY_NAME, name)
        put(MediaStore.MediaColumns.MIME_TYPE, "image/jpeg")
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q){
            put(MediaStore.Images.Media.RELATIVE_PATH, "DCIM/CameraX")
        }
    }

    val outputUri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI
    val outputOptions = ImageCapture.OutputFileOptions
        .Builder(context.contentResolver, outputUri, contentValues)
        .build()
    imageCapture.takePicture(
        outputOptions,
        ContextCompat.getMainExecutor(context),
        object : ImageCapture.OnImageSavedCallback {
            override fun onImageSaved(outputFileResults: ImageCapture.OutputFileResults){
                outputFileResults.savedUri?.let {
                    onImageSaved(it)
                    Toast.makeText(context, "Foto guardada en galeria", Toast.LENGTH_SHORT).show()
                }
            }
            override fun onError(exception: ImageCaptureException){
                exception.printStackTrace()
                Toast.makeText(context, "Error al guardar la foto",
                    Toast.LENGTH_SHORT).show()
            }
        }
    )
}