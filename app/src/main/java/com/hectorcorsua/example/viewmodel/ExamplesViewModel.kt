package com.hectorcorsua.example.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hectorcorsua.example.model.Example
import com.hectorcorsua.example.model.ExampleRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ExamplesViewModel @Inject constructor(
    private val examplesRepository: ExampleRepository
) : ViewModel() {
    val examplesLiveData = MutableLiveData<List<Example>>()
    val isLoading = MutableLiveData<Boolean>()

    fun getNews() {
        viewModelScope.launch {
            isLoading.postValue(true)
            val news = examplesRepository.get()
            examplesLiveData.postValue(news)
            isLoading.postValue(false)
        }
    }
}