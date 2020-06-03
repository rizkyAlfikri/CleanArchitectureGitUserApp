package com.alfikri.rizky.cleanarchitecturegituserapp.di.modules

import com.alfikri.rizky.cleanarchitecturegituserapp.di.PerActivity
import com.alfikri.rizky.cleanarchitecturegituserapp.presenter.*
import com.alfikri.rizky.cleanarchitecturegituserapp.ui.adapter.GitUserListAdapter
import dagger.Binds
import dagger.Module
import dagger.Provides

@Module
abstract class UserModule {

    @Module
    companion object {
        @PerActivity
        @JvmStatic
        @Provides
        fun provideGitUserAdapter(): GitUserListAdapter {
            return GitUserListAdapter()
        }
    }

    @PerActivity
    @Binds
    abstract fun provideSearchPresenter(gitUserSearchPresenter: GitUserSearchPresenter): BasePresenter

    @PerActivity
    @Binds
    abstract fun provideDetailPresenter(gitUserDetailPresenter: GitUserDetailPresenter): BasePresenter

    @PerActivity
    @Binds
    abstract fun provideFollowerPresenter(gitUserFollowerPresenter: GitUserFollowerPresenter): BasePresenter

    @PerActivity
    @Binds
    abstract fun provideFollowingPresenter(gitUserFollowingPresenter: GitUserFollowingPresenter): BasePresenter
}