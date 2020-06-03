package com.alfikri.rizky.domain.interactor

import com.alfikri.rizky.domain.executor.PostExecutionThread
import com.alfikri.rizky.domain.executor.ThreadExecutor
import com.alfikri.rizky.domain.model.GitUserFollowersModel
import com.alfikri.rizky.domain.repository.GitUserRepository
import io.reactivex.Observable
import javax.inject.Inject

class GitUserFollowerUseCase @Inject constructor(
    private val gitUserRepository: GitUserRepository,
    threadExecutor: ThreadExecutor,
    postExecutionThread: PostExecutionThread
) : UseCase<GitUserFollowersModel, GitUserFollowerUseCase.Companion.GitUserFollowerParams>(
    threadExecutor,
    postExecutionThread
) {

    companion object {
        class GitUserFollowerParams private constructor(val username: String) {
            companion object {
                fun forUser(username: String): GitUserFollowerParams {
                    return GitUserFollowerParams(username)
                }
            }
        }
    }

    override fun buildUseCaseObservable(params: GitUserFollowerParams): Observable<GitUserFollowersModel> {
        return gitUserRepository.getGitUserFollower(params.username)
    }
}