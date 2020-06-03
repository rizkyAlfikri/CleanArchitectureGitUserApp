package com.alfikri.rizky.cleanarchitecturegituserapp.presenter

import com.alfikri.rizky.cleanarchitecturegituserapp.di.PerActivity
import com.alfikri.rizky.cleanarchitecturegituserapp.entity.GitUserFollowerEntity
import com.alfikri.rizky.cleanarchitecturegituserapp.exception.ErrorMessageFactory
import com.alfikri.rizky.cleanarchitecturegituserapp.mapper.GitUserEntityDataMapper
import com.alfikri.rizky.cleanarchitecturegituserapp.view.GitUserFollowerView
import com.alfikri.rizky.domain.exception.DefaultErrorBundle
import com.alfikri.rizky.domain.exception.ErrorBundle
import com.alfikri.rizky.domain.interactor.DefaultObserver
import com.alfikri.rizky.domain.interactor.GitUserFollowerUseCase
import com.alfikri.rizky.domain.model.GitUserFollowersModel
import javax.inject.Inject

@PerActivity
class GitUserFollowerPresenter @Inject constructor(
    private val gitUserFollowerUseCase: GitUserFollowerUseCase,
    private val gitUserEntityDataMapper: GitUserEntityDataMapper
) : BasePresenter {

    private var viewGitUserFollower: GitUserFollowerView? = null

    fun setView(view: GitUserFollowerView) {
        viewGitUserFollower = view
    }

    fun initialize(username: String) {
        loadFollowerGitUser(username)
    }

    fun onUserClickGitUser(username: String) {
        viewGitUserFollower?.viewGitUserDetail(username)
    }

    override fun onResume() {

    }

    override fun onPause() {

    }

    override fun onDestroy() {
        gitUserFollowerUseCase.dispose()
        viewGitUserFollower = null
    }

    private fun loadFollowerGitUser(username: String) {
        this@GitUserFollowerPresenter.hideViewRetry()
        this@GitUserFollowerPresenter.showViewLoading()
        this@GitUserFollowerPresenter.getGitUserFollower(username)
    }

    private fun showViewLoading() {
        viewGitUserFollower?.showLoading()
    }

    private fun hideViewLoading() {
        viewGitUserFollower?.hideLoading()
    }

    private fun showViewRetry() {
        viewGitUserFollower?.showRetry()
    }

    private fun hideViewRetry() {
        viewGitUserFollower?.hideRetry()
    }

    private fun showErrorMessage(errorBundle: ErrorBundle) {
        val errorMessage = ErrorMessageFactory.create(
            viewGitUserFollower?.getContext(),
            errorBundle.getException()
        )

        viewGitUserFollower?.showError(errorMessage)
    }

    private fun getGitUserFollower(username: String) {
        gitUserFollowerUseCase.execute(
            GitUserFollowerObserver(),
            GitUserFollowerUseCase.Companion.GitUserFollowerParams.forUser(username)
        )
    }

    private fun showGitUserFollowerToView(gitUserFollowersModel: GitUserFollowersModel) {
        viewGitUserFollower?.renderGitUserFollower(
            GitUserFollowerEntity(gitUserEntityDataMapper.transform(gitUserFollowersModel.list))
        )
    }

    private inner class GitUserFollowerObserver : DefaultObserver<GitUserFollowersModel>() {
        override fun onComplete() {
            this@GitUserFollowerPresenter.hideViewLoading()
        }

        override fun onNext(t: GitUserFollowersModel) {
            this@GitUserFollowerPresenter.showGitUserFollowerToView(t)
        }

        override fun onError(e: Throwable) {
            this@GitUserFollowerPresenter.showViewRetry()
            this@GitUserFollowerPresenter.hideViewLoading()
            this@GitUserFollowerPresenter.showErrorMessage(DefaultErrorBundle(e as Exception))
        }
    }
}