package com.otus.tree_screen.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.otus.core_api.dto.Character
import com.otus.core_api.remote.HpApi
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.launch

class TreeViewModel(
    private val hpApi: HpApi,
    private val dispatcherIO: CoroutineDispatcher
) : ViewModel() {
    private var _treeLiveData: MutableLiveData<List<Character>> = MutableLiveData()
    val treeLiveData: LiveData<List<Character>> = _treeLiveData

    fun getAllCharacters() {
        viewModelScope.launch(dispatcherIO) {
            hpApi
                .getAllCharacters()
                .also {
                    _treeLiveData.postValue(it)
                }
        }
    }

    fun getNamesList(namesList: List<Character>): List<String> {
        return namesList
            .map { it.name.substringAfterLast(" ") }
            .distinct()
    }

    fun getFamilyByName(charList: List<Character>, chosenName: String): List<Character> {
        return charList.filter {
            it.name.contains(chosenName)
        }
    }
}