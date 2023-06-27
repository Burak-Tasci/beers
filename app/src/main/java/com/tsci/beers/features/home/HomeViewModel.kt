package com.tsci.beers.features.home

import androidx.lifecycle.viewModelScope
import com.tsci.beers.core.BaseViewModel
import com.tsci.beers.data.Resource
import com.tsci.beers.ui.model.BeerUiModel
import com.tsci.beers.domain.use_case.GetAllBeersUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getAllBeersUseCase: GetAllBeersUseCase
) : BaseViewModel() {

    private var _uiState = UiState()
    val uiState
        get() = _uiState

    private val _events = MutableSharedFlow<UiEvent>()
    val events = _events.asSharedFlow()

    init {
        getBeers()
    }

    private fun getBeers() {
        viewModelScope.launch {
            getAllBeersUseCase.execute().collect { result ->
                _uiState = _uiState.copy(beers = result)
                _events.emit(UiEvent.BEERS)
            }
        }
    }


    data class UiState(
        val beers: Resource<List<BeerUiModel>> = Resource.Empty
    )

    enum class UiEvent {
        BEERS
    }
}