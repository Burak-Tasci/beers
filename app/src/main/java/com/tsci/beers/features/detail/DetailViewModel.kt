package com.tsci.beers.features.detail

import androidx.lifecycle.viewModelScope
import com.tsci.beers.core.BaseViewModel
import com.tsci.beers.data.ServerErrorModel
import com.tsci.beers.domain.use_case.GetBeerDetailUseCase
import com.tsci.beers.ui.model.BeerDetailUiModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class DetailViewModel @Inject constructor(
    private val getBeerDetailUseCase: GetBeerDetailUseCase
) : BaseViewModel() {

    private var _uiState = UiState()
    val uiState
        get() = _uiState

    private val _events = MutableSharedFlow<UiEvent>()
    val events = _events.asSharedFlow()


    fun getBeerDetail(beerId: Int) {
        viewModelScope.launch {
            getBeerDetailUseCase.execute(beerId).collect { result ->
                result.onSuccess { detailModel ->
                    _uiState = _uiState.copy(detailModel = detailModel)
                    _events.emit(UiEvent.DetailModel(detailModel))
                }.onError {
                    _events.emit(UiEvent.Error(it))
                }.onLoading {
                    _events.emit(UiEvent.Loading(it))
                }
            }
        }
    }

    data class UiState(
        val detailModel: BeerDetailUiModel? = null
    )

    sealed class UiEvent {
        data class Error(val errorModel: ServerErrorModel) : UiEvent()
        data class Loading(val isLoading: Boolean) : UiEvent()
        data class DetailModel(val detailModel: BeerDetailUiModel) : UiEvent()
    }

}