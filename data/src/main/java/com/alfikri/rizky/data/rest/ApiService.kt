package com.alfikri.rizky.data.rest

import com.alfikri.rizky.data.response.GitUserDetailResponse
import com.alfikri.rizky.data.response.GitUserFollowerResponse
import com.alfikri.rizky.data.response.GitUserFollowingResponse
import com.alfikri.rizky.data.response.GitUserSearchResponse
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

//    @GET("/search/users")
    fun getSearchGitUser( username: String): Observable<GitUserSearchResponse>

//    @GET("/users/{username}")
    fun getGitUserDetail(username: String): Observable<GitUserDetailResponse>

//    @GET("/users/{username}/followers")
    fun getGitUserFollowers( username: String): Observable<GitUserFollowerResponse>

//    @GET("/users/{username}/following")
    fun getGitUserFollowing(username: String): Observable<GitUserFollowingResponse>
}