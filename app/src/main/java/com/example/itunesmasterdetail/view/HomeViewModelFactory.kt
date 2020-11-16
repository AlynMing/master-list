package com.example.itunesmasterdetail.view

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.itunesmasterdetail.repository.SearchResultRepository

class HomeViewModelFactory(private val movieListRepo: SearchResultRepository):ViewModelProvider.NewInstanceFactory() {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return HomeViewModel(movieListRepo) as T
    }
}