package com.alfikri.rizky.domain.repository

import com.alfikri.rizky.domain.model.GitUserDetailModel
import com.alfikri.rizky.domain.model.GitUserFollowersModel
import com.alfikri.rizky.domain.model.GitUserFollowingModel
import com.alfikri.rizky.domain.model.GitUserSearchModel
import io.reactivex.Observable

interface GitUserRepository {

    fun searchGitUser(username: String): Observable<GitUserSearchModel>

    fun getGitUserDetail(username: String): Observable<GitUserDetailModel>

    fun getGitUserFollowing(username: String): Observable<GitUserFollowingModel>

    fun getGitUserFollower(username: String): Observable<GitUserFollowersModel>
}