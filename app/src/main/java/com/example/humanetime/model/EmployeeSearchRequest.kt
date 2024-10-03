package com.example.humanetime.model

data class EmployeeSearchRequest(
    val filtroEmpleado: String,
    val idCia: Int,
    val idUsuario: Int,
    val numRegistros: Int,
    val pagina: Int
)