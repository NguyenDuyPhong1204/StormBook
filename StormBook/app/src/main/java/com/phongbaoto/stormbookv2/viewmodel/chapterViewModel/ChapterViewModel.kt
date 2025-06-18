package com.phongbaoto.stormbookv2.viewmodel.chapterViewModel

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.phongbaoto.stormbookv2.data.model.Chapter
import com.phongbaoto.stormbookv2.data.model.Story
import com.phongbaoto.stormbookv2.data.repository.chapterRepository.ChapterRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import jakarta.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

sealed interface ChapterUIState {
    object Loading : ChapterUIState
    data class ReadList(val data: List<Chapter>) : ChapterUIState
    data class ChapterInfo(val data: Chapter) : ChapterUIState
    data class Error(val message: String) : ChapterUIState
}

@HiltViewModel
class ChapterViewModel @Inject constructor(
    private val repository: ChapterRepository,
    @ApplicationContext private val context: Context
) : ViewModel() {
    private val _uiState = MutableStateFlow<ChapterUIState>(ChapterUIState.Loading)
    val uiState = _uiState.asStateFlow()

    private val _chapter = MutableStateFlow<ChapterUIState>(ChapterUIState.Loading)
    val chapter = _chapter.asStateFlow()

    private val _listReadChapter = MutableStateFlow<ChapterUIState>(ChapterUIState.Loading)
    val listReadChapter: MutableStateFlow<ChapterUIState> = _listReadChapter

    private val _errorMessage = MutableStateFlow<String?>(null)
    val errorMessage = _errorMessage.asStateFlow()

    private val _isLoading = MutableStateFlow(false)
    val isLoading = _isLoading.asStateFlow()

    fun chapter(chapterId: Long) {
        viewModelScope.launch {
            _isLoading.value = true
            val result = repository.getChapterById(chapterId)
            result.fold(
                onSuccess = {
                    _chapter.value = ChapterUIState.ChapterInfo(it)
                },
                onFailure = {
                    _isLoading.value = false
                    _errorMessage.value = it.message
                }
            )
        }
    }

    fun getListReadChapter(userId: Long, storyId: Long) {
        viewModelScope.launch {
            _isLoading.value = true
            val result = repository.getListReadChapter(userId, storyId)
            result.fold(
                onSuccess = {
                    _listReadChapter.value = ChapterUIState.ReadList(it)
                },
                onFailure = {
                    _isLoading.value = false
                    _errorMessage.value = it.message
                }
            )
        }
    }

}