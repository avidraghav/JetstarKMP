package com.raghav.jetstar.ui.home

import com.raghav.jetstar.data.repository.HomeScreenRepositoryImpl
import com.raghav.jetstar.domain.entity.trending.Movie
import com.raghav.jetstar.router.SavedStateHandle
import com.raghav.jetstar.router.ViewModel
import com.raghav.jetstar.util.Resource
import kotlinx.coroutines.SupervisorJob
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
        fetchHomeScreenData()
        saveStateUpdates()
    }

    fun fetchHomeScreenData() {
        var popularMovies = emptyList<Movie>()
        var trendingMovies = emptyList<Movie>()
        var topRatedMovies = emptyList<Movie>()
        var nowPlayingMovies = emptyList<Movie>()
        _state.update {
            it.copy(
                isLoading = true
            )
        }
        launch(SupervisorJob()) {
            _state.update { it.copy(isLoading = false) }
            launch {
                when (val response = repository.getPopularMovies()) {
                    is Resource.Error -> {
                        _state.update {
                            it.copy(
                                errorLoadingPopular = response.exception
                            )
                        }
                    }
                    is Resource.Success -> {
                        popularMovies = response.data
                    }
                }
            }.invokeOnCompletion {
                _state.update {
                    it.copy(
                        popularMovies = popularMovies
                    )
                }
            }
            launch {
                when (val response = repository.getTrendingMovies()) {
                    is Resource.Error -> {
                        _state.update {
                            it.copy(
                                errorLoadingTrending = response.exception
                            )
                        }
                    }
                    is Resource.Success -> {
                        trendingMovies = response.data
                    }
                }
            }.invokeOnCompletion {
                _state.update {
                    it.copy(
                        trendingMovies = trendingMovies
                    )
                }
            }
            launch {
                when (val response = repository.getTopRatedMovies()) {
                    is Resource.Error -> {
                        _state.update {
                            it.copy(
                                errorLoadingTopRated = response.exception
                            )
                        }
                    }
                    is Resource.Success -> {
                        topRatedMovies = response.data
                    }
                }
            }.invokeOnCompletion {
                _state.update {
                    it.copy(
                        topRatedMovies = topRatedMovies
                    )
                }
            }
            launch {
                when (val response = repository.getNowPlayingMovies()) {
                    is Resource.Error -> {
                        _state.update {
                            it.copy(
                                errorLoadingNowPlaying = response.exception
                            )
                        }
                    }
                    is Resource.Success -> {
                        nowPlayingMovies = response.data
                    }
                }
            }.invokeOnCompletion {
                _state.update {
                    it.copy(
                        nowPlayingMovies = nowPlayingMovies
                    )
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
