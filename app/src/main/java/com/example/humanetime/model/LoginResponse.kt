package com.example.humanetime.model

data class LoginResponse(
    val acceso: Acceso? = Acceso(
        usuarioeTime = UsuarioeTime(
            foto = "",
            nombreCompleto = ""
        )
    ),
    val codigo: String,
    val error: Boolean,
    val token: String ?= ""
)

data class Acceso(
    val usuarioeTime: UsuarioeTime
)

data class UsuarioeTime(
    val foto: String,
    val nombreCompleto: String,

    )

