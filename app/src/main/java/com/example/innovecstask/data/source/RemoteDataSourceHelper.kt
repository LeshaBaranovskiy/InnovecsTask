package com.example.innovecstask.data.source

interface RemoteDataSourceHelper {
    fun <Api> buildApi(api: Class<Api>, url: String): Api
}