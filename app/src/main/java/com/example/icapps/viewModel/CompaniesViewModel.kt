package com.example.AndroidBombSample.viewModel

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.example.AndroidBombSample.*
import me.tatarka.bindingcollectionadapter2.ItemBinding

class CompaniesViewModel() : ViewModel() {
    val items = MutableLiveData<List<ItemBase>>()
    val itemBinding = ItemBinding.of<ItemBase>(BR.item, R.layout.recycler_base_item)

    fun load(count: Int) {
        items.value = (1..count).map {
            ItemBase(it, "Company $it", "Description $it")
        }
    }
}