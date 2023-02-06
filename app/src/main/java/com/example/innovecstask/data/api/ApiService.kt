package com.example.innovecstask.data.api

import com.example.innovecstask.data.dto.ConfigResponse
import retrofit2.Call
import retrofit2.http.GET

interface ApiService {
    @GET("butto_to_action_config.json")
    fun getConfigs(): Call<List<ConfigResponse>>
}