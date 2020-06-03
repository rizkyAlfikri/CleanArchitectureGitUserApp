package com.alfikri.rizky.domain.interactor

import com.alfikri.rizky.domain.executor.PostExecutionThread
import com.alfikri.rizky.domain.executor.ThreadExecutor
import com.alfikri.rizky.domain.model.GitUserDetailModel
import com.alfikri.rizky.domain.repository.GitUserRepository
import io.reactivex.Observable
import javax.inject.Inject
import javax.inject.Singleton

class GitUserDetailUseCase @Inject constructor(
    private val gitUserRepository: GitUserRepository,
    threadExecutor: ThreadExecutor,
    postExecutionThread: PostExecutionThread
) : UseCase<GitUserDetailModel, GitUserDetailUseCase.Companion.GitUserDetailParams>(
    threadExecutor,
    postExecutionThread
) {

    companion object {
        class GitUserDetailParams private constructor(val username: String) {
            companion object{
                fun forUser(username: String): GitUserDetailParams {
                    return GitUserDetailParams(username)
                }
            }
        }
    }

    override fun buildUseCaseObservable(params: GitUserDetailParams): Observable<GitUserDetailModel> {
        return gitUserRepository.getGitUserDetail(params.username)
    }
}