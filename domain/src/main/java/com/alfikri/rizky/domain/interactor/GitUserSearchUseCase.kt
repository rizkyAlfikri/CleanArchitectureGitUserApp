package com.alfikri.rizky.domain.interactor

import com.alfikri.rizky.domain.executor.PostExecutionThread
import com.alfikri.rizky.domain.executor.ThreadExecutor
import com.alfikri.rizky.domain.model.GitUserSearchModel
import com.alfikri.rizky.domain.repository.GitUserRepository
import io.reactivex.Observable
import javax.inject.Inject


class GitUserSearchUseCase @Inject constructor(
    private val gitUserRepository: GitUserRepository,
    threadExecutor: ThreadExecutor,
    postExecutionThread: PostExecutionThread
) : UseCase<GitUserSearchModel, GitUserSearchUseCase.Companion.GitUserSearchParams>(
    threadExecutor,
    postExecutionThread
) {


    companion object {
        class GitUserSearchParams private constructor(val username: String){
            companion object{
                fun forUser(username: String): GitUserSearchParams {
                    return GitUserSearchParams(username)
                }
            }
        }
    }

    override fun buildUseCaseObservable(params: GitUserSearchParams): Observable<GitUserSearchModel> {
        return gitUserRepository.searchGitUser(params.username)
    }
}