package com.alfikri.rizky.domain.model


data class GitUserSearchModel(
    val incompleteResults: Boolean,
    val gitUserModels: List<GitUserModel>,
    val totalCount: Int
)