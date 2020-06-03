package com.alfikri.rizky.data.cache

import com.alfikri.rizky.data.response.GitUserDetailResponse
import io.reactivex.Observable

interface GitUserCache {

    fun get(username: String): Observable<GitUserDetailResponse>

    fun put(gitUserDetailResponse: GitUserDetailResponse?)

    fun isCached(username: String): Boolean

    fun isExpire(): Boolean

    fun evictAll()
}