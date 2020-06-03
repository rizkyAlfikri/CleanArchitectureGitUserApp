package com.alfikri.rizky.cleanarchitecturegituserapp.view

import com.alfikri.rizky.cleanarchitecturegituserapp.entity.GitUserFollowerEntity

interface GitUserFollowingView : LoadDataView {
    fun renderGitUserFollowing(gitUserFollowerEntity: GitUserFollowerEntity)

    fun viewGitUserDetail(username: String)
}