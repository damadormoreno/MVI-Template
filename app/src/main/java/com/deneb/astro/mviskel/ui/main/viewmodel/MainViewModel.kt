package com.deneb.astro.mviskel.ui.main.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.deneb.astro.mviskel.data.repository.MainRepository
import com.deneb.astro.mviskel.ui.main.intent.MainIntent
import com.deneb.astro.mviskel.ui.main.viewstate.MainState
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.consumeAsFlow
import kotlinx.coroutines.launch

@ExperimentalCoroutinesApi
class MainViewModel(
    private val repository: MainRepository.MainRepositoryImpl
) : ViewModel() {

    val userIntent = Channel<MainIntent>(Channel.UNLIMITED)
    private val _state = MutableStateFlow<MainState>(MainState.Idle)
    val state: StateFlow<MainState>
        get() = _state

    init {
        handleIntent()
    }

    private fun handleIntent() {
        viewModelScope.launch {
            userIntent.consumeAsFlow().collect {
                when (it) {
                    is MainIntent.FetchHeros -> fetchHeros()
                }
            }
        }
    }

    private fun fetchHeros() {
        viewModelScope.launch {
            _state.value = MainState.Loading
            _state.value = try {
                MainState.Heroes(repository.getHeroes())
            } catch (e: Exception) {
                MainState.Error(e.localizedMessage)
            }
        }
    }
}