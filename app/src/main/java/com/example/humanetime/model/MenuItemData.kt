package com.example.humanetime.model

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ExitToApp
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Settings
import androidx.compose.ui.graphics.vector.ImageVector

data class MenuItemData(
    val title: String,
    val icon: ImageVector,
    val dummyData: String
)

val menuItems = listOf(
    MenuItemData("Datos de compañía", Icons.Default.Home, "Información sobre la compañía"),
    MenuItemData("Supervisores", Icons.Default.Person, "Lista de supervisores"),
    MenuItemData("Zonas", Icons.Default.LocationOn, "Detalles sobre las zonas"),
    MenuItemData("Estaciones", Icons.Default.DateRange, "Información sobre las estaciones"),
    MenuItemData("Empleados", Icons.Default.AccountBox, "Lista de empleados"),
    MenuItemData("Reportes", Icons.Default.Edit, "Reportes generados"),
    MenuItemData("Historial de auditoría", Icons.Default.AccountBox, "Registro de auditoría"),
    MenuItemData("Cambiar contraseña", Icons.Default.Lock, "Modificar contraseña del usuario"),
    MenuItemData("Configuración", Icons.Default.Settings, "Opciones de configuración"),
    MenuItemData("Cerrar sesión", Icons.AutoMirrored.Filled.ExitToApp, "Cerrar la sesión actual")
)
