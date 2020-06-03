package com.alfikri.rizky.cleanarchitecturegituserapp.view

import com.alfikri.rizky.cleanarchitecturegituserapp.entity.GitUserFollowerEntity

interface GitUserFollowerView: LoadDataView {
    fun renderGitUserFollower(gitUserFollowerEntity: GitUserFollowerEntity)

    fun viewGitUserDetail(username: String)
}