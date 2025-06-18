package com.phongbaoto.stormbookv2.viewmodel.storyViewModel

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.phongbaoto.stormbookv2.data.model.Chapter
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
    data class AllStory(val data: List<Story>) : StoryUiState
    data class ListChapter(val data: List<Chapter>) : StoryUiState
    data class ListByCategoryID(val data: List<Story>) : StoryUiState
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

    private val _allStory = MutableStateFlow<StoryUiState>(StoryUiState.Loading)
    val allStory: MutableStateFlow<StoryUiState> = _allStory

    private val _storyById = MutableStateFlow<Story?>(null)
    val storyById: MutableStateFlow<Story?> = _storyById

    private val _errorMessage = MutableStateFlow<String?>(null)
    val errorMessage: MutableStateFlow<String?> = _errorMessage

    private val _listChapter = MutableStateFlow<StoryUiState>(StoryUiState.Loading)
    val listChapter: MutableStateFlow<StoryUiState> = _listChapter

    private val _listByCategoryID = MutableStateFlow<StoryUiState>(StoryUiState.Loading)
    val listByCategoryID: MutableStateFlow<StoryUiState> = _listByCategoryID

    init {
        getSuggestedStories()
        getHotWeekStories()
    }

    private fun getSuggestedStories() = viewModelScope.launch {
        repository.getSuggestedStories().fold(
            onSuccess = { _suggestedStories.value = StoryUiState.SuggestedList(it) },
            onFailure = {
                _suggestedStories.value = StoryUiState.Error(it.message ?: "Lỗi không xác định")
            }
        )
    }

    private fun getHotWeekStories() = viewModelScope.launch {
        repository.getHotWeekStories().fold(
            onSuccess = { _hotWeekStories.value = StoryUiState.HotWeekList(it) },
            onFailure = {
                _hotWeekStories.value = StoryUiState.Error(it.message ?: "Lỗi không xác định")
            }
        )
    }

    fun getAllStory(
        page: Int,
        size: Int
    ) = viewModelScope.launch {
        repository.getAllStory(
            page = page,
            size = size
        ).fold(
            onSuccess = {
                _allStory.value = StoryUiState.AllStory(it)
            },
            onFailure = {
                _allStory.value = StoryUiState.Error(it.message ?: "Lỗi không xác định")
            }
        )
    }

    fun getStoryByCategoryId(
        categoryId: Long,
        page: Int,
        size: Int
    ) {
        viewModelScope.launch {
            repository.getStoryByCategoryId(
                categoryId = categoryId,
                page = page,
                size = size
            ).fold(
                onSuccess = {
                    _listByCategoryID.value = StoryUiState.ListByCategoryID(it)
                },
                onFailure = {
                    _listByCategoryID.value = StoryUiState.Error(it.message ?: "Lỗi không xác định")
                }
            )
        }
    }

    fun getStoryById(storyId: Long) = viewModelScope.launch {
        repository.getStoryById(storyId)
            .fold(
                onSuccess = {
                    _storyById.value = it
                },
                onFailure = {
                    _errorMessage.value = it.message ?: "Lỗi không xác định"
                }
            )
    }

    fun getChapterByStoryId(storyId: Long) = viewModelScope.launch {
        repository.getChapterByStoryId(storyId)
            .fold(
                onSuccess = {
                    _listChapter.value = StoryUiState.ListChapter(it)
                },
                onFailure = {
                    _listChapter.value = StoryUiState.Error(it.message ?: "Lỗi không xác định")
                }
            )
    }


}