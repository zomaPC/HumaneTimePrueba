package com.example.humanetime.model

data class EmployResponse(
    val empleado: List<Empleado>,
    )

data class Empleado(
    val apellidoMat: String?="",
    val apellidoPat: String?="",
    val idEmpleado: Int,
    val nombre: String
)