package com.alfikri.rizky.cleanarchitecturegituserapp.view

import com.alfikri.rizky.cleanarchitecturegituserapp.entity.GitUserDetailEntity

interface GitUserDetailView: LoadDataView {
    fun renderGitUserDetail(gitUserDetailEntity: GitUserDetailEntity)
}