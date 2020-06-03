package com.alfikri.rizky.cleanarchitecturegituserapp.presenter

import android.util.Log
import com.alfikri.rizky.cleanarchitecturegituserapp.di.PerActivity
import com.alfikri.rizky.cleanarchitecturegituserapp.entity.GitUserSearchEntity
import com.alfikri.rizky.cleanarchitecturegituserapp.exception.ErrorMessageFactory
import com.alfikri.rizky.cleanarchitecturegituserapp.mapper.GitUserEntityDataMapper
import com.alfikri.rizky.cleanarchitecturegituserapp.view.GitUserSearchView
import com.alfikri.rizky.domain.exception.DefaultErrorBundle
import com.alfikri.rizky.domain.exception.ErrorBundle
import com.alfikri.rizky.domain.interactor.DefaultObserver
import com.alfikri.rizky.domain.interactor.GitUserSearchUseCase
import com.alfikri.rizky.domain.model.GitUserSearchModel
import javax.inject.Inject

@PerActivity
class GitUserSearchPresenter @Inject constructor(
    private val gitUserSearchUseCase: GitUserSearchUseCase,
    private val gitUserEntityDataMapper: GitUserEntityDataMapper
) : BasePresenter {

    init {
        Log.d("Dagger", "GitUserSearchPresenter use GitUserSearchUseCase instance $gitUserSearchUseCase")
    }
    private var viewGitUserSearch: GitUserSearchView? = null

    fun setView(view: GitUserSearchView) {
        viewGitUserSearch = view
    }

    fun initialize(username: String) {
        this.loadSearchGitUser(username)
    }

    fun onUserClickGitUser(username: String) {
        viewGitUserSearch?.viewGitUserDetail(username)
    }

    override fun onResume() {

    }

    override fun onPause() {

    }

    override fun onDestroy() {
        gitUserSearchUseCase.dispose()
        viewGitUserSearch = null
        Log.d("Dispose", "Dispose")
    }

    private fun loadSearchGitUser(username: String) {
        this.hideViewRetry()
        this.showViewLoading()
        this.getGitUserSearchList(username)
    }

    private fun showViewLoading() {
        viewGitUserSearch?.showLoading()
    }

    private fun hideViewLoading() {
        viewGitUserSearch?.hideLoading()
    }

    private fun showViewRetry() {
        viewGitUserSearch?.showRetry()
    }

    private fun hideViewRetry() {
        viewGitUserSearch?.hideRetry()
    }

    private fun showErrorMessage(errorBundle: ErrorBundle) {
        val errorMessage = ErrorMessageFactory.create(
            viewGitUserSearch?.getContext(), errorBundle.getException()
        )
        viewGitUserSearch?.showError(errorMessage)
    }

    private fun getGitUserSearchList(username: String) {
        gitUserSearchUseCase.execute(
            GitUserSearchObserver(),
            GitUserSearchUseCase.Companion.GitUserSearchParams.forUser(username)
        )
    }

    private fun showGitUserSearchInView(gitUserSearchModel: GitUserSearchModel) {
        viewGitUserSearch?.renderGitUserSearch(
            GitUserSearchEntity(
                gitUserSearchModel.incompleteResults,
                gitUserEntityDataMapper
                    .transform(gitUserSearchModel.gitUserModels),
                gitUserSearchModel.totalCount
            )
        )
    }

    private inner class GitUserSearchObserver : DefaultObserver<GitUserSearchModel>() {

        override fun onComplete() {
            this@GitUserSearchPresenter.hideViewLoading()
        }

        override fun onNext(t: GitUserSearchModel) {
            this@GitUserSearchPresenter.showGitUserSearchInView(t)
        }

        override fun onError(e: Throwable) {
            this@GitUserSearchPresenter.hideViewLoading()
            this@GitUserSearchPresenter.showErrorMessage(DefaultErrorBundle(e as Exception))
            this@GitUserSearchPresenter.showViewRetry()
        }
    }
}