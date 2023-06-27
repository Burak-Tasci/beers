package com.tsci.beers.features.home

import androidx.lifecycle.viewModelScope
import com.tsci.beers.core.BaseViewModel
import com.tsci.beers.data.ServerErrorModel
import com.tsci.beers.domain.use_case.GetAllBeersUseCase
import com.tsci.beers.ui.model.BeerUiModel
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
                result.onSuccess { beers ->
                    _uiState = _uiState.copy(beers = beers)
                    _events.emit(UiEvent.Beers(beers))
                }.onError {
                    _events.emit(UiEvent.Error(it))
                }.onLoading {
                    _events.emit(UiEvent.Loading(it))
                }
            }
        }
    }


    data class UiState(
        val beers: List<BeerUiModel> = emptyList()
    )

    sealed class UiEvent() {
        data class Error(val errorModel: ServerErrorModel) : UiEvent()
        data class Loading(val isLoading: Boolean) : UiEvent()
        data class Beers(val beers: List<BeerUiModel>) : UiEvent()
    }
}