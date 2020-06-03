package com.alfikri.rizky.cleanarchitecturegituserapp.di.modules

import android.content.Context
import com.alfikri.rizky.cleanarchitecturegituserapp.application.AndroidApplication
import com.alfikri.rizky.data.rest.ApiClient
import com.alfikri.rizky.data.rest.ApiService
import dagger.Module
import dagger.Provides
import java.io.File
import javax.inject.Singleton


@Module
class ApplicationModule(private val application: AndroidApplication) {

    @Singleton
    @Provides
    fun provideContext(): Context {
        return application
    }

//    @Singleton
//    @Provides
//    fun provideApiClient(): ApiService {
//        return ApiClient().getClient()
//    }

//    @Singleton
//    @Provides
//    fun provideGitUserDataStoreFactory(gitUserCache: GitUserCache): GitUserDataStoreFactory {
//        return GitUserDataStoreFactory(gitUserCache)
//    }

}