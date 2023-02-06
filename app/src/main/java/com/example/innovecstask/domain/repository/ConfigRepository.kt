package com.example.innovecstask.domain.repository

import com.example.innovecstask.data.dto.ConfigResponse
import kotlinx.coroutines.flow.Flow
import retrofit2.Call

interface ConfigRepository {
    fun getConfigs(): Call<List<ConfigResponse>>
}