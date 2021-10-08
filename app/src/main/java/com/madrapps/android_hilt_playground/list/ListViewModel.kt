package com.madrapps.android_hilt_playground.list

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ListViewModelImpl : ListViewModel() {

    override val title: MutableLiveData<String> = MutableLiveData()

    override fun load() {
        title.value = "Actual Implementation"
    }
}

abstract class ListViewModel : ViewModel() {

    abstract fun load()
    abstract val title: LiveData<String>
}
