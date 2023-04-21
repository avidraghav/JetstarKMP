package com.raghav.jetstar.ui.home

import com.raghav.jetstar.data.repository.MovieRepositoryImpl
import com.raghav.jetstar.router.SavedStateHandle
import com.raghav.jetstar.router.ViewModel
import com.raghav.jetstar.ui.HomeState
import com.raghav.jetstar.util.Resource
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import org.koin.core.component.inject

class HomeScreenVM(private val savedState: SavedStateHandle) : ViewModel() {
    val repository: MovieRepositoryImpl by inject()

    private val _state: MutableStateFlow<HomeState> =
        MutableStateFlow(savedState.get() ?: HomeState.initialState)
    val state: StateFlow<HomeState> = _state.asStateFlow()

    init {
        getTopRatedMovies()
        saveStateUpdates()
    }

    fun getTopRatedMovies() {
        launch() {
            _state.update { it.copy(isLoading = true, isErrorWithMessage = Pair(null, false)) }
            val result = repository.getTopRatedMovies()
            _state.update { it.copy(isLoading = false) }
            when (result) {
                is Resource.Error -> {
                    _state.update { it.copy(isErrorWithMessage = Pair(result.exception, true)) }
                }
                is Resource.Success -> {
                    _state.update { it.copy(movies = result.data) }
                }
            }

//            when (val response = repository.getTopRatedMovies()) {
//                is Resource.Error -> {
//                    _state.emit(HomeState.Error(exception = response.exception))
//                }
//                is Resource.Success -> {
//                    _state.emit(HomeState.Success(data = response.data))
//                }
//            }
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
