package com.phongbaoto.stormbookv2.viewmodel.categoryViewModel

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.phongbaoto.stormbookv2.data.model.Category
import com.phongbaoto.stormbookv2.data.repository.categoryRepository.CategoryRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import jakarta.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

sealed interface CategoryUiState {
    object Loading : CategoryUiState
    data class Success(val data: List<Category>) : CategoryUiState
    data class Error(val message: String) : CategoryUiState
}

@HiltViewModel
class CategoryViewModel @Inject constructor(
    private val repository: CategoryRepository,
    @ApplicationContext private val context: Context
): ViewModel(){
    private val _uiState = MutableStateFlow<CategoryUiState>(CategoryUiState.Loading)
    val uiState: StateFlow<CategoryUiState> = _uiState

    init { getListCategory() }

    private fun getListCategory() = viewModelScope.launch {
        repository.getListCategory().fold(
            onSuccess = { _uiState.value = CategoryUiState.Success(it) },
            onFailure = { _uiState.value = CategoryUiState.Error(it.message ?: "Lỗi không xác định") }
        )
    }

}