package com.alfikri.rizky.data.repository.datasource

import android.content.Context
import com.alfikri.rizky.data.cache.GitUserCache
import com.alfikri.rizky.data.response.mapper.GitUserResJsonMapper
import com.alfikri.rizky.data.rest.ApiClient
import com.alfikri.rizky.data.rest.ApiService
import com.alfikri.rizky.data.rest.ApiServiceImpl
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GitUserDataStoreFactory @Inject constructor(
    private val context: Context,
    private val gitUserCache: GitUserCache
) {

    fun create(username: String): GitUserDataSource {
        return   createCloudDataSource()
    }

    fun createCloudDataSource(): GitUserDataSource {
        val gitResponseDataMapper = GitUserResJsonMapper()
        val apiClient = ApiServiceImpl(context.applicationContext, gitResponseDataMapper)
        return CloudGitUserDataSource(apiClient, gitUserCache)
    }
}