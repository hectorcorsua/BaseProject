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
class DetailViewModel @Inject constructor(
    private val favoritesRepository: FavoritesDbRepository
): ViewModel() {
    val isFavorite = MutableLiveData<Boolean>()

    fun onViewCreated(example: Example?) {
        example?.let {
            viewModelScope.launch {
                val exist = favoritesRepository.exist(it)
                isFavorite.postValue(exist)
            }
        }
    }

    fun onClickFavorite(example: Example) {
        viewModelScope.launch {
            if (favoritesRepository.exist(example)) {
                favoritesRepository.remove(example)
                isFavorite.postValue(false)
            } else {
                favoritesRepository.save(example)
                isFavorite.postValue(true)
            }
        }
    }
}