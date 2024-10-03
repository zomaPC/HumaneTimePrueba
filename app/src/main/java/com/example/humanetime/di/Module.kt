package com.example.humanetime.di

import com.example.humanetime.network.Client
import com.example.humanetime.ui.viewmodel.EmployeeViewModel
import com.example.humanetime.ui.viewmodel.LoginViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val diModule = module {
    single {
        Client.humaneTimeAPI
    }
    viewModel {
        LoginViewModel(humaneTimeAPI = get())
    }
    viewModel {
        EmployeeViewModel(humaneTimeAPI = get())
    }

}
