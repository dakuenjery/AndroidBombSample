package com.example.AndroidBombSample

import android.app.Application
import com.example.AndroidBombSample.viewModel.GameViewModel
import com.example.AndroidBombSample.viewModel.GamesViewModel
import com.example.AndroidBombSample.ui.MainViewModel
import com.example.AndroidBombSample.viewModel.CompaniesViewModel
import org.koin.android.architecture.ext.viewModel
import org.koin.android.ext.android.startKoin
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module.applicationContext

val module = applicationContext {
    viewModel { MainViewModel() }
    viewModel { GamesViewModel(androidApplication()) }
    viewModel { CompaniesViewModel() }
    viewModel { GameViewModel() }
}

class App : Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin(this, listOf(module))
    }
}