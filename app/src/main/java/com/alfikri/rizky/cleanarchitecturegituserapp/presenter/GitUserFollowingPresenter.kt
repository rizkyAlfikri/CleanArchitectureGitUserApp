package com.alfikri.rizky.cleanarchitecturegituserapp.presenter

import com.alfikri.rizky.cleanarchitecturegituserapp.di.PerActivity
import com.alfikri.rizky.cleanarchitecturegituserapp.entity.GitUserFollowerEntity
import com.alfikri.rizky.cleanarchitecturegituserapp.exception.ErrorMessageFactory
import com.alfikri.rizky.cleanarchitecturegituserapp.mapper.GitUserEntityDataMapper
import com.alfikri.rizky.cleanarchitecturegituserapp.view.GitUserFollowingView
import com.alfikri.rizky.domain.exception.DefaultErrorBundle
import com.alfikri.rizky.domain.exception.ErrorBundle
import com.alfikri.rizky.domain.interactor.DefaultObserver
import com.alfikri.rizky.domain.interactor.GitUserFollowingUseCase
import com.alfikri.rizky.domain.model.GitUserFollowingModel
import javax.inject.Inject

@PerActivity
class GitUserFollowingPresenter @Inject constructor(
    private val gitUserFollowingUseCase: GitUserFollowingUseCase,
    private val gitUserEntityDataMapper: GitUserEntityDataMapper
) : BasePresenter {

    private var viewGitUserFollowing: GitUserFollowingView? = null

    fun setView(view: GitUserFollowingView) {
        viewGitUserFollowing = view
    }

    fun initialize(username: String) {
        this@GitUserFollowingPresenter.hideViewRetry()
        this@GitUserFollowingPresenter.showViewLoading()
        this@GitUserFollowingPresenter.getFollowingGitUser(username)
    }

    fun onUserClickGitUser(username: String) {
        viewGitUserFollowing?.viewGitUserDetail(username)
    }

    override fun onResume() {

    }

    override fun onPause() {

    }

    override fun onDestroy() {
        gitUserFollowingUseCase.dispose()
        viewGitUserFollowing = null
    }

    private fun showViewLoading() {
        viewGitUserFollowing?.showLoading()
    }

    private fun hideViewLoading() {
        viewGitUserFollowing?.hideLoading()
    }

    private fun showViewRetry() {
        viewGitUserFollowing?.showRetry()
    }

    private fun hideViewRetry() {
        viewGitUserFollowing?.hideRetry()
    }

    private fun showErrorMessage(errorBundle: ErrorBundle) {
        val errorMessage = ErrorMessageFactory.create(
            viewGitUserFollowing?.getContext(),
            errorBundle.getException()
        )
        viewGitUserFollowing?.showError(errorMessage)
    }

    private fun getFollowingGitUser(username: String) {
        gitUserFollowingUseCase.execute(
            GitUserFollowingObserver(),
            GitUserFollowingUseCase.Companion.GitUserFollowingParams.furUser(username)
        )
    }

    private fun showGitUserFollowingToView(gitUserFollowingModel: GitUserFollowingModel) {
        viewGitUserFollowing?.renderGitUserFollowing(
            GitUserFollowerEntity(
                gitUserEntityDataMapper.transform(gitUserFollowingModel.list)
            )
        )
    }

    private inner class GitUserFollowingObserver : DefaultObserver<GitUserFollowingModel>() {
        override fun onComplete() {
            this@GitUserFollowingPresenter.hideViewLoading()
        }

        override fun onNext(t: GitUserFollowingModel) {
            this@GitUserFollowingPresenter.showGitUserFollowingToView(t)
        }

        override fun onError(e: Throwable) {
            this@GitUserFollowingPresenter.showViewRetry()
            this@GitUserFollowingPresenter.hideViewLoading()
            this@GitUserFollowingPresenter.showErrorMessage(DefaultErrorBundle(e as Exception))
        }
    }
}