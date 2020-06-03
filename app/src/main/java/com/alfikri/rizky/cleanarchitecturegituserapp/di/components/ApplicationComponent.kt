package com.alfikri.rizky.cleanarchitecturegituserapp.di.components

import android.content.Context
import com.alfikri.rizky.cleanarchitecturegituserapp.di.modules.AbstractionModule
import com.alfikri.rizky.cleanarchitecturegituserapp.di.modules.ApplicationModule
import com.alfikri.rizky.cleanarchitecturegituserapp.ui.BaseActivity
import com.alfikri.rizky.data.cache.GitUserCache
import com.alfikri.rizky.data.repository.datasource.GitUserDataSource
import com.alfikri.rizky.data.rest.ApiService
import com.alfikri.rizky.domain.executor.PostExecutionThread
import com.alfikri.rizky.domain.executor.ThreadExecutor
import com.alfikri.rizky.domain.repository.GitUserRepository
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [AbstractionModule::class, ApplicationModule::class])
interface ApplicationComponent {

    fun inject(target: BaseActivity)

    fun getContext(): Context

//    fun getApiClient(): ApiService

    fun getGitUserRepository(): GitUserRepository

    fun postExecutor(): PostExecutionThread

    fun threadExecutor(): ThreadExecutor

//    fun userCache(): GitUserCache
}