package com.alfikri.rizky.data.repository.datasource

import com.alfikri.rizky.data.cache.GitUserCache
import com.alfikri.rizky.data.response.GitUserDetailResponse
import com.alfikri.rizky.data.response.GitUserFollowerResponse
import com.alfikri.rizky.data.response.GitUserFollowingResponse
import com.alfikri.rizky.data.response.GitUserSearchResponse
import io.reactivex.Observable
import java.lang.UnsupportedOperationException
import javax.inject.Inject

class DiskGitUserDataSource @Inject constructor(
    private val gitUserCache: GitUserCache
) : GitUserDataSource {

    @Suppress("UNREACHABLE_CODE", "ThrowableNotThrown")
    override fun searchGitUser(username: String): Observable<GitUserSearchResponse> {
        TODO("implement simple cache for storing / retrieving collection of git user")
        throw UnsupportedOperationException("Operation is not available!!!")
    }

    override fun getGitUserDetail(username: String): Observable<GitUserDetailResponse> {
        return gitUserCache.get(username)
    }

    @Suppress("UNREACHABLE_CODE", "ThrowableNotThrown")
    override fun getGitUserFollowing(username: String): Observable<GitUserFollowingResponse> {
        TODO("implement simple cache for storing / retrieving collection of git user")
        throw UnsupportedOperationException("Operation is not available!!!")
    }

    @Suppress("UNREACHABLE_CODE", "ThrowableNotThrown")
    override fun getGitUserFollower(username: String): Observable<GitUserFollowerResponse> {
        TODO("implement simple cache for storing / retrieving collection of git user")
        throw UnsupportedOperationException("Operation is not available!!!")
    }


}