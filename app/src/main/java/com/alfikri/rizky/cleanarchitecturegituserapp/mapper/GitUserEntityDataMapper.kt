package com.alfikri.rizky.cleanarchitecturegituserapp.mapper

import com.alfikri.rizky.cleanarchitecturegituserapp.di.PerActivity
import com.alfikri.rizky.cleanarchitecturegituserapp.entity.GitUserDetailEntity
import com.alfikri.rizky.cleanarchitecturegituserapp.entity.GitUserEntity
import com.alfikri.rizky.domain.model.GitUserDetailModel
import com.alfikri.rizky.domain.model.GitUserModel
import javax.inject.Inject

@PerActivity
class GitUserEntityDataMapper @Inject constructor(){

    fun transform(gitUserModel: GitUserModel): GitUserEntity {
        return gitUserModel.let {
            GitUserEntity(
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

    fun transform(gitUserModels: List<GitUserModel>): List<GitUserEntity> {
        return gitUserModels.map { transform(it) }
    }

    fun transform(gitUserDetailModel: GitUserDetailModel): GitUserDetailEntity {
        return gitUserDetailModel.let {
            GitUserDetailEntity(
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