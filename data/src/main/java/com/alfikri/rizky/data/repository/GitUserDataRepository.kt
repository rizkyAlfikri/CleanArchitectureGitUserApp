package com.alfikri.rizky.data.repository

import com.alfikri.rizky.data.repository.datasource.GitUserDataStoreFactory
import com.alfikri.rizky.data.response.mapper.GitUSerDataMapper
import com.alfikri.rizky.domain.model.GitUserDetailModel
import com.alfikri.rizky.domain.model.GitUserFollowersModel
import com.alfikri.rizky.domain.model.GitUserFollowingModel
import com.alfikri.rizky.domain.model.GitUserSearchModel
import com.alfikri.rizky.domain.repository.GitUserRepository
import io.reactivex.Observable
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GitUserDataRepository @Inject constructor(
    private val gitUserDataStoreFactory: GitUserDataStoreFactory,
    private val gitDataMapper: GitUSerDataMapper
) : GitUserRepository {

    override fun searchGitUser(username: String): Observable<GitUserSearchModel> {
        return gitUserDataStoreFactory.createCloudDataSource()
            .searchGitUser(username).map {
                GitUserSearchModel(
                    it.incompleteResults,
                    gitDataMapper.transform(it.gitUserResponses),
                    it.totalCount
                )
            }
    }

    override fun getGitUserDetail(username: String): Observable<GitUserDetailModel> {
        return gitUserDataStoreFactory.create(username).getGitUserDetail(username)
            .map { gitDataMapper.transform(it) }
    }

    override fun getGitUserFollowing(username: String): Observable<GitUserFollowingModel> {
        return gitUserDataStoreFactory.createCloudDataSource().getGitUserFollowing(username)
            .map { GitUserFollowingModel(gitDataMapper.transform(it)) }
    }

    override fun getGitUserFollower(username: String): Observable<GitUserFollowersModel> {
        return gitUserDataStoreFactory.createCloudDataSource().getGitUserFollower(username)
            .map { GitUserFollowersModel(gitDataMapper.transform(it)) }
    }
}