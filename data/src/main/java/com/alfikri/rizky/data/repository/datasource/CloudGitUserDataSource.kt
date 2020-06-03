package com.alfikri.rizky.data.repository.datasource

import com.alfikri.rizky.data.cache.GitUserCache
import com.alfikri.rizky.data.response.GitUserDetailResponse
import com.alfikri.rizky.data.response.GitUserFollowerResponse
import com.alfikri.rizky.data.response.GitUserFollowingResponse
import com.alfikri.rizky.data.response.GitUserSearchResponse
import com.alfikri.rizky.data.rest.ApiService
import io.reactivex.Observable
import javax.inject.Inject

class CloudGitUserDataSource @Inject constructor(
    private val apiService: ApiService,
    private val gitUserCache: GitUserCache
) : GitUserDataSource {

    override fun searchGitUser(username: String): Observable<GitUserSearchResponse> {
        return apiService.getSearchGitUser(username)
    }

    override fun getGitUserDetail(username: String): Observable<GitUserDetailResponse> {
        return apiService.getGitUserDetail(username).doOnNext(gitUserCache::put)
    }

    override fun getGitUserFollowing(username: String): Observable<GitUserFollowingResponse> {
        return apiService.getGitUserFollowing(username)
    }

    override fun getGitUserFollower(username: String): Observable<GitUserFollowerResponse> {
        return apiService.getGitUserFollowers(username)
    }
}