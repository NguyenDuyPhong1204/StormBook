package com.phongbaoto.stormbookv2.viewmodel.storyViewModel

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.phongbaoto.stormbookv2.data.model.Story
import com.phongbaoto.stormbookv2.data.repository.storyRepository.StoryRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import jakarta.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

sealed interface StoryUiState {
    object Loading : StoryUiState
    data class SuggestedList(val data: List<Story>) : StoryUiState
    data class HotWeekList(val data: List<Story>) : StoryUiState
    data class Error(val message: String) : StoryUiState
}

@HiltViewModel
class StoryViewModel @Inject constructor(
    private val repository: StoryRepository,
    @ApplicationContext private val context: Context
) : ViewModel() {
    private val _uiState = MutableStateFlow<StoryUiState>(StoryUiState.Loading)
    val uiState: MutableStateFlow<StoryUiState> = _uiState

    private val _suggestedStories = MutableStateFlow<StoryUiState>(StoryUiState.Loading)
    val suggestedStories: MutableStateFlow<StoryUiState> = _suggestedStories

    private val _hotWeekStories = MutableStateFlow<StoryUiState>(StoryUiState.Loading)
    val hotWeekStories: MutableStateFlow<StoryUiState> = _hotWeekStories


    init {
        getSuggestedStories()
        getHotWeekStories()
    }

    private fun getSuggestedStories() = viewModelScope.launch {
        repository.getSuggestedStories().fold(
            onSuccess = { _suggestedStories.value = StoryUiState.SuggestedList(it) },
            onFailure = { _suggestedStories.value = StoryUiState.Error(it.message ?: "Lỗi không xác định") }
        )
    }

    private fun getHotWeekStories() = viewModelScope.launch {
        repository.getHotWeekStories().fold(
            onSuccess = { _hotWeekStories.value = StoryUiState.HotWeekList(it) },
            onFailure = { _hotWeekStories.value = StoryUiState.Error(it.message ?: "Lỗi không xác định") }
        )
    }


}