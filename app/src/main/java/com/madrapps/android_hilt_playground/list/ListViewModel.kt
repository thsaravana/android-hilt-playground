package com.madrapps.android_hilt_playground.list

import android.os.Bundle
import androidx.lifecycle.AbstractSavedStateViewModelFactory
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.savedstate.SavedStateRegistryOwner

class ListViewModelImpl(
    private val savedStateHandle: SavedStateHandle
) : ListViewModel() {

    override val title: MutableLiveData<String> = MutableLiveData()

    override fun load() {
        val value: String? = savedStateHandle["load"]
        if (value == null) {
            savedStateHandle["load"] = "Actual Implementation"
        } else {
            savedStateHandle["load"] = "Actual Retained Data"
        }
        title.value = savedStateHandle["load"]
    }
}

class ListViewModelFactory(
    owner: SavedStateRegistryOwner,
    args: Bundle? = null
) : AbstractSavedStateViewModelFactory(owner, args) {
    override fun <T : ViewModel?> create(
        key: String,
        modelClass: Class<T>,
        handle: SavedStateHandle
    ): T {
        return ListViewModelImpl(handle) as T
    }
}

abstract class ListViewModel : ViewModel() {

    abstract fun load()
    abstract val title: LiveData<String>
}
