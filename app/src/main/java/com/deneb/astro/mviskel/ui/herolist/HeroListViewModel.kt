package com.deneb.astro.mviskel.ui.herolist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.deneb.astro.mviskel.data.repository.MainRepository
import com.deneb.astro.mviskel.ui.herolist.HeroListIntent
import com.deneb.astro.mviskel.ui.herolist.HeroListState
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.consumeAsFlow
import kotlinx.coroutines.launch

@ExperimentalCoroutinesApi
class HeroListViewModel(
    private val repository: MainRepository.MainRepositoryImpl
) : ViewModel() {

    val userIntent = Channel<HeroListIntent>(Channel.UNLIMITED)
    private val _state = MutableStateFlow<HeroListState>(
        HeroListState.Idle)
    val state: StateFlow<HeroListState>
        get() = _state

    init {
        handleIntent()
    }

    private fun handleIntent() {
        viewModelScope.launch {
            userIntent.consumeAsFlow().collect {
                when (it) {
                    is HeroListIntent.FetchHeros -> fetchHeros()
                }
            }
        }
    }

    private fun fetchHeros() {
        viewModelScope.launch {
            _state.value = HeroListState.Loading
            _state.value = try {
                HeroListState.Heroes(repository.getHeroes())
            } catch (e: Exception) {
                HeroListState.Error(e.localizedMessage)
            }
        }
    }
}