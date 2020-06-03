package com.alfikri.rizky.cleanarchitecturegituserapp.entity


data class GitUserSearchEntity(
    val incompleteResults: Boolean,
    val gitUserModels: List<GitUserEntity>,
    val totalCount: Int
)