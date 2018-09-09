package com.example.AndroidBombSample.viewModel

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.graphics.drawable.Drawable

class GameViewModel : ViewModel() {
    val image = MutableLiveData<Drawable>()
    val name = MutableLiveData<String>()
    val desc = MutableLiveData<String>()

    fun loadGameData(gameId: Int) {

    }
}