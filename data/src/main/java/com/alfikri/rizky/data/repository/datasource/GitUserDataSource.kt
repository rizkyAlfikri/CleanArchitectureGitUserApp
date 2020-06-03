package com.alfikri.rizky.data.repository.datasource

import com.alfikri.rizky.data.response.GitUserDetailResponse
import com.alfikri.rizky.data.response.GitUserFollowerResponse
import com.alfikri.rizky.data.response.GitUserFollowingResponse
import com.alfikri.rizky.data.response.GitUserSearchResponse
import io.reactivex.Observable

interface GitUserDataSource {
    fun searchGitUser(username: String): Observable<GitUserSearchResponse>

    fun getGitUserDetail(username: String): Observable<GitUserDetailResponse>

    fun getGitUserFollowing(username: String): Observable<GitUserFollowingResponse>

    fun getGitUserFollower(username: String): Observable<GitUserFollowerResponse>
}