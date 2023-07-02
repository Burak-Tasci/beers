package com.tsci.beers

import androidx.lifecycle.viewModelScope
import com.tsci.beers.core.BaseViewModel
import com.tsci.beers.data.ServerErrorModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
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

    private val _isSplashScreenVisible =  MutableStateFlow(true)
    val isSplashScreenVisible = _isSplashScreenVisible.asStateFlow()

    init {
        splashScreenWork()
    }

    fun onError(errorModel: ServerErrorModel) {
        _errorModel.update {
            errorModel
        }
    }

    fun onLoading(isLoading: Boolean) {
        _isLoading.update { isLoading }
    }

    private fun splashScreenWork(){
        viewModelScope.launch {
            delay(4000L)
            _isSplashScreenVisible.value = false
        }
    }
}