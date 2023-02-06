package com.example.innovecstask.domain.usecase

import com.example.innovecstask.data.dto.ConfigResponse
import com.example.innovecstask.domain.repository.ConfigRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.lang.Exception

class ConfigsUseCase(
    private val configRepository: ConfigRepository
) {
    fun getConfigs(): Flow<List<ConfigResponse>> {
        return flow {
            val response = configRepository.getConfigs().execute()
            if (response.isSuccessful) {
                emit(response.body()!!)
            } else {
                throw Exception(response.errorBody()?.string())
            }
            configRepository.getConfigs()
        }
    }
}