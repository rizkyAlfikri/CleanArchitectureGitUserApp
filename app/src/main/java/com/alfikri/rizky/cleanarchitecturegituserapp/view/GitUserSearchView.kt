package com.alfikri.rizky.cleanarchitecturegituserapp.view

import com.alfikri.rizky.cleanarchitecturegituserapp.entity.GitUserEntity
import com.alfikri.rizky.cleanarchitecturegituserapp.entity.GitUserSearchEntity

interface GitUserSearchView : LoadDataView {
    fun renderGitUserSearch(listGit: GitUserSearchEntity)

    fun viewGitUserDetail(username: String)
}