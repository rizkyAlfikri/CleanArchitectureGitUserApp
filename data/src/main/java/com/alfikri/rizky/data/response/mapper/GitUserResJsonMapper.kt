package com.alfikri.rizky.data.response.mapper

import com.alfikri.rizky.data.response.*
import com.google.gson.Gson
import com.google.gson.JsonSyntaxException
import com.google.gson.reflect.TypeToken
import javax.inject.Inject

class GitUserResJsonMapper @Inject constructor(){

    private val gson: Gson = Gson()

    @Throws(JsonSyntaxException::class)
    fun transformGitUserSearch(gitUserResponseJson: String): GitUserSearchResponse {
        val userEntityType =
            object : TypeToken<GitUserSearchResponse?>() {}.type
        return gson.fromJson(gitUserResponseJson, userEntityType)
    }

    @Throws(JsonSyntaxException::class)
    fun transformGitUserDetail(gitUserResponseJson: String): GitUserDetailResponse {
        val userEntityType =
            object : TypeToken<GitUserDetailResponse?>() {}.type
        return gson.fromJson(gitUserResponseJson, userEntityType)
    }

    @Throws(JsonSyntaxException::class)
    fun transformGitUserFollower(gitUserResponseJson: String): GitUserFollowerResponse {
        val userEntityType =
            object : TypeToken<GitUserFollowerResponse?>() {}.type
        return gson.fromJson(gitUserResponseJson, userEntityType)
    }

    @Throws(JsonSyntaxException::class)
    fun transformGitUserFollowing(gitUserResponseJson: String): GitUserFollowingResponse {
        val userEntityType =
            object : TypeToken<GitUserFollowingResponse?>() {}.type
        return gson.fromJson(gitUserResponseJson, userEntityType)
    }
}