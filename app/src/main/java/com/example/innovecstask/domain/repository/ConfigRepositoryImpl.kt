package com.example.innovecstask.domain.repository

import com.example.innovecstask.data.api.ApiDataProvider
import com.example.innovecstask.data.dto.ConfigResponse
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ConfigRepositoryImpl @Inject constructor(
    private val dataProvider: ApiDataProvider
): ConfigRepository {
    override fun getConfigs() =
        dataProvider.getConfigs()
}