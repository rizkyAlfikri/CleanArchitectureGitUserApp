package com.alfikri.rizky.data.response.mapper

import com.alfikri.rizky.data.response.GitUserDetailResponse
import com.alfikri.rizky.data.response.GitUserResponse
import com.alfikri.rizky.domain.model.GitUserDetailModel
import com.alfikri.rizky.domain.model.GitUserModel
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GitUSerDataMapper @Inject constructor() {

    fun transform(gitUserResponse: GitUserResponse): GitUserModel {
        return gitUserResponse.let {
            GitUserModel(
                it.avatarUrl,
                it.eventsUrl,
                it.followersUrl,
                it.followingUrl,
                it.gistsUrl,
                it.gravatarId,
                it.htmlUrl,
                it.id,
                it.login,
                it.nodeId,
                it.organizationsUrl,
                it.receivedEventsUrl,
                it.reposUrl,
                it.score,
                it.siteAdmin,
                it.starredUrl,
                it.subscriptionsUrl,
                it.type,
                it.url
            )
        }
    }

    fun transform(gitUserResponses: List<GitUserResponse>): List<GitUserModel> {
        return gitUserResponses.map { transform(it) }
    }

    fun transform(gitUserDetailResponse: GitUserDetailResponse): GitUserDetailModel {
       return gitUserDetailResponse.let {
            GitUserDetailModel(
                it.avatarUrl,
                it.bio,
                it.blog,
                it.company,
                it.createdAt,
                it.email,
                it.eventsUrl,
                it.followers,
                it.followersUrl,
                it.following,
                it.followingUrl,
                it.gistsUrl,
                it.gravatarId,
                it.hireable,
                it.htmlUrl,
                it.id,
                it.location,
                it.login,
                it.name,
                it.nodeId,
                it.organizationsUrl,
                it.publicGists,
                it.publicRepos,
                it.receivedEventsUrl,
                it.reposUrl,
                it.siteAdmin,
                it.starredUrl,
                it.subscriptionsUrl,
                it.twitterUsername,
                it.type,
                it.updatedAt,
                it.url
            )
        }
    }
}