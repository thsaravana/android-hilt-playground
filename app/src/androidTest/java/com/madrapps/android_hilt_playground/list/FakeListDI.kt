package com.madrapps.android_hilt_playground.list

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.lifecycle.AbstractSavedStateViewModelFactory
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.savedstate.SavedStateRegistryOwner
import dagger.Module
import dagger.Provides
import dagger.hilt.android.components.FragmentComponent
import dagger.hilt.testing.TestInstallIn

@Module
@TestInstallIn(
    components = [FragmentComponent::class],
    replaces = [ListDI::class]
)
object FakeListDI {

    @ListFragmentQualifier
    @Provides
    fun provideFactory(fragment: Fragment): AbstractSavedStateViewModelFactory {
        return ViewModelFactory(fragment, fragment.arguments)
    }

    class ViewModelFactory(
        owner: SavedStateRegistryOwner,
        args: Bundle? = null
    ) :
        AbstractSavedStateViewModelFactory(owner, args) {
        override fun <T : ViewModel?> create(
            key: String,
            modelClass: Class<T>,
            handle: SavedStateHandle
        ): T {
            return FakeViewModel(handle) as T
        }
    }

    class FakeViewModel(
        private val savedStateHandle: SavedStateHandle,
    ) : ListViewModel() {

        override fun load() {
            val value: String? = savedStateHandle["load"]
            if (value == null) {
                savedStateHandle["load"] = "Fake implementation"
            } else {
                savedStateHandle["load"] = "Fake Retained data"
            }
            title.value = savedStateHandle["load"]
        }

        override val title: MutableLiveData<String> = MutableLiveData()
    }
}