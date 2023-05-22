package com.otus.potions_screen.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.otus.core_api.remote.HerokuApi
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Inject

class PotionsViewModelFactory @Inject constructor(
    private val herokuApi: HerokuApi,
    private val dispatcherIO: CoroutineDispatcher
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return PotionsViewModel(herokuApi, dispatcherIO) as T
    }
}