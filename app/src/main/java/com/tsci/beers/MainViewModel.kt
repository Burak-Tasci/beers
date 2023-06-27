package com.tsci.beers

import com.tsci.beers.core.BaseViewModel
import com.tsci.beers.data.ServerErrorModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

/**
 * Created by tasci on 24.06.2023.
 */
@HiltViewModel
class MainViewModel @Inject constructor(): BaseViewModel() {

    private val _errorModel = MutableStateFlow(ServerErrorModel(""))
    val errorModel = _errorModel

    private val _isLoading = MutableStateFlow(false)
    val isLoading = _isLoading

    fun onError(errorModel: ServerErrorModel) {
        _errorModel.update {
            errorModel
        }
    }

    fun onLoading(isLoading: Boolean) {
        _isLoading.update { isLoading }
    }
}