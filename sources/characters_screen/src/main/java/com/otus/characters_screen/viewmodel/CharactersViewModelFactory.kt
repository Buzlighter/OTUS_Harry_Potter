package com.otus.characters_screen.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.otus.core_api.remote.HpApi
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Inject

class CharactersViewModelFactory @Inject constructor(
    private val hpApi: HpApi,
    private val dispatcherIO: CoroutineDispatcher
    ): ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return CharactersViewModel(hpApi, dispatcherIO) as T
    }
}