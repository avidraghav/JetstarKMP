package com.raghav.jetstar.ui.home

import com.raghav.jetstar.data.repository.HomeScreenRepositoryImpl
import com.raghav.jetstar.router.SavedStateHandle
import com.raghav.jetstar.router.ViewModel
import com.raghav.jetstar.util.Resource
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import org.koin.core.component.inject

class HomeScreenVM(private val savedState: SavedStateHandle) : ViewModel() {
    private val repository: HomeScreenRepositoryImpl by inject()

    private val _state: MutableStateFlow<HomeScreenState> =
        MutableStateFlow(savedState.get() ?: HomeScreenState())
    val state: StateFlow<HomeScreenState> = _state.asStateFlow()

    init {
        getTopRatedMovies()
        saveStateUpdates()
    }

    fun getTopRatedMovies() {
        launch {
            _state.update { it.copy(isLoading = true, isErrorWithMessage = Pair(null, false)) }
            val response = repository.getTopRatedMovies()
            _state.update { it.copy(isLoading = false) }
            when (response) {
                is Resource.Error -> {
                    _state.update { it.copy(isErrorWithMessage = Pair(response.exception, true)) }
                }
                is Resource.Success -> {
                    _state.update {
                        it.copy(
                            media = response.data.results?.filterNotNull() ?: emptyList()
                        )
                    }
                }
            }
        }
    }

    private fun saveStateUpdates() {
        launch {
            state.collectLatest {
                savedState.set(it)
            }
        }
    }
}
