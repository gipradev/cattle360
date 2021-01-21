package com.android.cattle360.ui.base

import androidx.lifecycle.ViewModelProvider

@Suppress("UNCHECKED_CAST")
class ViewModelFactory(
    private val repository: BaseRepository ?= null
) : ViewModelProvider.NewInstanceFactory() {

//    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
//        return when {
//            modelClass.isAssignableFrom(CategoryViewModel::class.java) -> CategoryViewModel(
//                repository as CategoryRepository
//            ) as T
//            else -> throw IllegalArgumentException("ViewModelClass Not Found")
//        }
//    }
}