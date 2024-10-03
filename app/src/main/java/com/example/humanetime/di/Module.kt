package com.example.humanetime.di

import com.example.humanetime.network.Client
import org.koin.dsl.module
import org.koin.androidx.viewmodel.dsl.viewModel

val diModule = module {
    single {
        Client.humaneTimeAPI
    }

}
