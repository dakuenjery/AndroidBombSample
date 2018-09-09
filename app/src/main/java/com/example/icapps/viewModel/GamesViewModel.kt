package com.example.AndroidBombSample.viewModel

import android.app.Application
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.content.Intent.FLAG_ACTIVITY_NEW_TASK
import com.example.AndroidBombSample.BR
import com.example.AndroidBombSample.Game
import com.example.AndroidBombSample.ItemBase
import com.example.AndroidBombSample.R
import com.example.AndroidBombSample.ui.GameActivityStarter
import me.tatarka.bindingcollectionadapter2.ItemBinding

interface OnItemSelectedListener {
    fun onItemSelected(game: ItemBase)
}

class GamesViewModel(val application: Application) : ViewModel(), OnItemSelectedListener {
    val items = MutableLiveData<List<ItemBase>>()
    val itemBinding = ItemBinding.of<ItemBase>(BR.item, R.layout.recycler_base_item)
            .bindExtra(BR.listener, this)!!

    fun load(count: Int) {
        items.value = (1..count).map {
            ItemBase(it, "Game $it", "Description $it")
        }
    }

    override fun onItemSelected(game: ItemBase) {
        GameActivityStarter.startWithFlags(application, game.id, FLAG_ACTIVITY_NEW_TASK)
    }
}