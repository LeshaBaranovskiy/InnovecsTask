package com.example.innovecstask.data.source

import com.google.gson.Gson
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RemoteDataSource(
    private val gson: Gson,
): RemoteDataSourceHelper {
    override fun <Api> buildApi(api: Class<Api>, url: String): Api {
        return Retrofit.Builder()
            .baseUrl(url)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
            .create(api)
    }
}