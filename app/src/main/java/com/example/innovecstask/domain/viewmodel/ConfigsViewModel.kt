package com.example.innovecstask.domain.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.innovecstask.data.dto.ConfigResponse
import com.example.innovecstask.domain.usecase.ConfigsUseCase
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

class ConfigsViewModel @Inject constructor(
    private val configsUseCase: ConfigsUseCase
): ViewModel() {
    val configs = MutableLiveData<List<ConfigResponse>>()
    val coroutineExceptionHandler = CoroutineExceptionHandler{_, throwable ->
        Log.d("tttt", throwable.printStackTrace().toString())
    }

    fun loadConfigs() {
        viewModelScope.launch(Dispatchers.IO + coroutineExceptionHandler) {
            configsUseCase.getConfigs().collect { listConfigs ->
                withContext(Dispatchers.Main) {
                    configs.value = listConfigs
                }
            }
        }
    }
}