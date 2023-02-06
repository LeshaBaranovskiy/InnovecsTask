package com.example.innovecstask.data.api

import com.example.innovecstask.data.dto.ConfigResponse
import retrofit2.Call

interface ApiHelper {
    fun getConfigs(): Call<List<ConfigResponse>>
}