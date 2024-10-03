package com.example.humanetime.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.humanetime.model.LoginResponse
import com.example.humanetime.network.HumaneTimeAPI
import com.example.humanetime.session.Session
import kotlinx.coroutines.launch

class LoginViewModel(
    private val humaneTimeAPI: HumaneTimeAPI
) : ViewModel() {
    val errorMap = hashMapOf(
        "ET034" to "No cuenta con una fotografía registrada, favor de realizar la toma correspondiente",
        "ET035" to "El campo token es incorrecto",
        "ET036" to "Actualmente no cuenta con un fotografía autorizada, favor de notificar a su supervisor",
        "ET412" to "La opción del Reporte es incorrecta",
        "ET413" to "El usuario no tiene un correo electronico asociado",
        "ET217" to "Password Invalido"

        )
    private val _isLoading = MutableLiveData(false)
    val isLoading: LiveData<Boolean> = _isLoading

    private val _email = MutableLiveData("")
    val email: LiveData<String> = _email

    private val _password = MutableLiveData("")
    val password: LiveData<String> = _password


    private val _loginState = MutableLiveData<LoginResponse?>()
    val loginResponse: LiveData<LoginResponse?> = _loginState

    //  estado de error
    private val _errorMessage = MutableLiveData("")
    val errorMessage: LiveData<String> = _errorMessage

    private val _loginSuccess = MutableLiveData(false)
    val loginSuccess: LiveData<Boolean> = _loginSuccess

    fun login() {
        _isLoading.value = true
        viewModelScope.launch {

            val response: LoginResponse =
                humaneTimeAPI.login(_email.value ?: "", _password.value ?: "")

            _loginState.value = response

            val errorCode = response.codigo
            val errorMessage = errorMap.get(errorCode)


            if (errorMessage != null || response.error) {

                _isLoading.value = false
                _errorMessage.value = errorMessage?: "Error al Iniciar sesion"

            } else {
                _isLoading.value = false
                _loginSuccess.value = true
                Session.fullName = response.acceso?.usuarioeTime?.nombreCompleto?:""
                Session.avatarURL = response.acceso?.usuarioeTime?.foto?:""
                Session.token = response.token?:""
            }
        }
    }

    fun closeDialog() {
        _errorMessage.value = ""
    }

    fun setEmail(value: String) {
        _email.value = value
    }

    fun setPassword(value: String) {
        _password.value = value
    }
}