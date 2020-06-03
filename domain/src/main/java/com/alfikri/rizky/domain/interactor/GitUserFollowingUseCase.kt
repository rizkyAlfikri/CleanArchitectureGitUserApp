package com.alfikri.rizky.domain.interactor

import com.alfikri.rizky.domain.executor.PostExecutionThread
import com.alfikri.rizky.domain.executor.ThreadExecutor
import com.alfikri.rizky.domain.model.GitUserFollowingModel
import com.alfikri.rizky.domain.repository.GitUserRepository
import io.reactivex.Observable
import javax.inject.Inject

class GitUserFollowingUseCase @Inject constructor(
    private val gitUserRepository: GitUserRepository,
    threadExecutor: ThreadExecutor,
    postExecutionThread: PostExecutionThread
): UseCase<GitUserFollowingModel, GitUserFollowingUseCase.Companion.GitUserFollowingParams>(
    threadExecutor,
    postExecutionThread
) {

    companion object{
        class GitUserFollowingParams private constructor(val username: String){
            companion object{
                fun furUser(username: String): GitUserFollowingParams {
                    return GitUserFollowingParams(username)
                }
            }
        }
    }

    override fun buildUseCaseObservable(params: GitUserFollowingParams): Observable<GitUserFollowingModel> {
        return gitUserRepository.getGitUserFollowing(params.username)
    }
}