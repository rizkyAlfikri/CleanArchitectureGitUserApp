package com.alfikri.rizky.data.response.mapper

import com.alfikri.rizky.data.response.GitUserResponse
import com.google.gson.Gson
import com.google.gson.JsonSyntaxException
import com.google.gson.reflect.TypeToken

class Test {
    private val gson = Gson()

    @Throws(JsonSyntaxException::class)
    fun transformUserEntity(userJsonResponse: String?): GitUserResponse {
        val userEntityType =
            object : TypeToken<GitUserResponse?>() {}.type
        return gson.fromJson(userJsonResponse, userEntityType)
    }
}