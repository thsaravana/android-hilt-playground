package com.madrapps.android_hilt_playground.list

import androidx.fragment.app.Fragment
import androidx.lifecycle.AbstractSavedStateViewModelFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.FragmentComponent
import javax.inject.Qualifier

@Module
@InstallIn(FragmentComponent::class)
object ListDI {

    @ListFragmentQualifier
    @Provides
    fun provideFactory(fragment: Fragment): AbstractSavedStateViewModelFactory {
        return ListViewModelFactory(fragment, fragment.arguments)
    }
}

@Qualifier
annotation class ListFragmentQualifier
