package com.example.humanetime.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.humanetime.model.Empleado
import com.example.humanetime.model.EmployeeSearchRequest
import com.example.humanetime.network.HumaneTimeAPI
import com.example.humanetime.session.Session
import kotlinx.coroutines.launch

class EmployeeViewModel (
    private val humaneTimeAPI: HumaneTimeAPI
):ViewModel(){
    private val _employees = MutableLiveData<List<Empleado>>()
    val employees: LiveData<List<Empleado>> = _employees

    private val _errorMessage = MutableLiveData<String>()
    val errorMessage: LiveData<String> = _errorMessage

    fun searchEmployees() {
        viewModelScope.launch {
            try {
                val requestBody = EmployeeSearchRequest(
                    filtroEmpleado = "",
                    idCia = 10046,
                    idUsuario = 357,
                    numRegistros = 500,
                    pagina = 1
                )

                val response = humaneTimeAPI.searchEmployee("Bearer ${Session.token}", requestBody)
                _employees.value = response.empleado
            } catch (e: Exception) {
                _errorMessage.value = "Error: ${e.localizedMessage}"
            }
        }
    }
}