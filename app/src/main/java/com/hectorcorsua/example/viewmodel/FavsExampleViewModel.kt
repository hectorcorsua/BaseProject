package com.hectorcorsua.example.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hectorcorsua.example.model.Example
import com.hectorcorsua.example.model.FavoritesDbRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavsExampleViewModel @Inject constructor(
    private val favsRepository: FavoritesDbRepository
) : ViewModel() {
    val favsLiveData = MutableLiveData<List<Example>>()
    val isLoading = MutableLiveData<Boolean>()

    fun onViewCreated() {
        viewModelScope.launch {
            isLoading.postValue(true)
            val favs = favsRepository.get()
            favsLiveData.postValue(favs)
            isLoading.postValue(false)
        }
    }
}