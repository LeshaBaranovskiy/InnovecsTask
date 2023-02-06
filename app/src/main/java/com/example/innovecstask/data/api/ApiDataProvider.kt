package com.example.innovecstask.data.api

import com.example.innovecstask.data.dto.ConfigResponse
import kotlinx.coroutines.flow.Flow
import retrofit2.Call
import javax.inject.Inject

class ApiDataProvider @Inject constructor(
    private val apiService: ApiService
): ApiHelper {
    override fun getConfigs(): Call<List<ConfigResponse>> =
        apiService.getConfigs()
}