package com.alfikri.rizky.cleanarchitecturegituserapp.di.modules

import com.alfikri.rizky.cleanarchitecturegituserapp.UIThread
import com.alfikri.rizky.data.cache.GitUserCache
import com.alfikri.rizky.data.cache.GitUserCacheImpl
import com.alfikri.rizky.data.executor.JobExecutor
import com.alfikri.rizky.data.repository.GitUserDataRepository
import com.alfikri.rizky.data.repository.datasource.CloudGitUserDataSource
import com.alfikri.rizky.data.repository.datasource.DiskGitUserDataSource
import com.alfikri.rizky.data.repository.datasource.GitUserDataSource
import com.alfikri.rizky.domain.executor.PostExecutionThread
import com.alfikri.rizky.domain.executor.ThreadExecutor
import com.alfikri.rizky.domain.repository.GitUserRepository
import dagger.Binds
import dagger.Module
import javax.inject.Singleton

@Module
abstract class AbstractionModule {

    @Singleton
    @Binds
    abstract fun provideUserCache(userCacheImpl: GitUserCacheImpl): GitUserCache

    @Singleton
    @Binds
    abstract fun provideCloudGitUser(cloudGitUserDataSource: CloudGitUserDataSource): GitUserDataSource

    @Singleton
    @Binds
    abstract fun provideDiskGitUser(diskGitUserDataSource: DiskGitUserDataSource): GitUserDataSource

    @Singleton
    @Binds
    abstract fun provideGitUserData(gitUserDataRepository: GitUserDataRepository): GitUserRepository

    @Singleton
    @Binds
    abstract fun providePostExecutor(uiThread: UIThread): PostExecutionThread

    @Singleton
    @Binds
    abstract fun provideThreadExecutor(jobExecutor: JobExecutor): ThreadExecutor

}