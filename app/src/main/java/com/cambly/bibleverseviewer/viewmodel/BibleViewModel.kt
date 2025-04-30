package com.cambly.bibleverseviewer.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cambly.bibleverseviewer.data.model.Verse
import com.cambly.bibleverseviewer.data.repository.BibleRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BibleViewModel @Inject constructor(
    private val repository: BibleRepository
) : ViewModel() {

    private val _verses = MutableStateFlow<List<Verse>>(emptyList())
    val verses: StateFlow<List<Verse>> = _verses

    init {
        viewModelScope.launch {
            repository.fetchVerses().collect {
                _verses.value = it
            }
        }
    }
}