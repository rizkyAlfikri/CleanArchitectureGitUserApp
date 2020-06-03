package com.alfikri.rizky.cleanarchitecturegituserapp.di.components

import com.alfikri.rizky.cleanarchitecturegituserapp.di.PerActivity
import com.alfikri.rizky.cleanarchitecturegituserapp.di.modules.ActivityModule
import com.alfikri.rizky.cleanarchitecturegituserapp.di.modules.UserModule
import com.alfikri.rizky.cleanarchitecturegituserapp.ui.detail.DetailGitUserActivity
import com.alfikri.rizky.cleanarchitecturegituserapp.ui.follower.FollowerGitUserFragment
import com.alfikri.rizky.cleanarchitecturegituserapp.ui.following.FollowingGitUserFragment
import com.alfikri.rizky.cleanarchitecturegituserapp.ui.search.SearchGitUserFragment
import dagger.Component

@PerActivity
@Component(dependencies = [ApplicationComponent::class], modules = [ActivityModule::class, UserModule::class])
interface UserComponent: ActivityComponent  {

    fun inject(target: SearchGitUserFragment)

    fun inject(target: FollowingGitUserFragment)

    fun inject(target: FollowerGitUserFragment)

    fun inject(target: DetailGitUserActivity)
}