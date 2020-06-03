package com.alfikri.rizky.cleanarchitecturegituserapp.presenter

import android.util.Log
import com.alfikri.rizky.cleanarchitecturegituserapp.di.PerActivity
import com.alfikri.rizky.cleanarchitecturegituserapp.exception.ErrorMessageFactory
import com.alfikri.rizky.cleanarchitecturegituserapp.mapper.GitUserEntityDataMapper
import com.alfikri.rizky.cleanarchitecturegituserapp.view.GitUserDetailView
import com.alfikri.rizky.domain.exception.DefaultErrorBundle
import com.alfikri.rizky.domain.exception.ErrorBundle
import com.alfikri.rizky.domain.interactor.DefaultObserver
import com.alfikri.rizky.domain.interactor.GitUserDetailUseCase
import com.alfikri.rizky.domain.model.GitUserDetailModel
import javax.inject.Inject

@PerActivity
class GitUserDetailPresenter @Inject constructor(
    private val gitUserDetailUseCase: GitUserDetailUseCase,
    private val gitUserEntityDataMapper: GitUserEntityDataMapper
) : BasePresenter {

    init {
        Log.d("Presenter", "GitUserDetailPresenter use GitUserDetailUseCase instance  $gitUserDetailUseCase")
    }
    private var viewGitUserDetail: GitUserDetailView? = null

    fun setView(view: GitUserDetailView) {
        viewGitUserDetail = view
    }

    fun initialize(username: String) {
        this@GitUserDetailPresenter.loadDetailGitUser(username)
    }

    override fun onResume() {
    }

    override fun onPause() {
    }

    override fun onDestroy() {
        gitUserDetailUseCase.dispose()
        viewGitUserDetail = null
    }

    private fun loadDetailGitUser(username: String) {
        this@GitUserDetailPresenter.hideViewRetry()
        this@GitUserDetailPresenter.showViewLoading()
        this@GitUserDetailPresenter.getGitUserDetail(username)
    }

    private fun showViewLoading() {
        viewGitUserDetail?.showLoading()
    }

    private fun hideViewLoading() {
        viewGitUserDetail?.hideLoading()
    }

    private fun showViewRetry() {
        viewGitUserDetail?.showRetry()
    }

    private fun hideViewRetry() {
        viewGitUserDetail?.hideRetry()
    }

    private fun showErrorMessage(errorBundle: ErrorBundle) {
        val errorMessage = ErrorMessageFactory.create(
            viewGitUserDetail?.getContext(),
            errorBundle.getException()
        )
        viewGitUserDetail?.showError(errorMessage)
    }

    private fun getGitUserDetail(username: String) {
        gitUserDetailUseCase.execute(
            GitUserDetailObserver(),
            GitUserDetailUseCase.Companion.GitUserDetailParams.forUser(username)
        )
    }

    private fun showGitUserDetailInView(gitUserDetailModel: GitUserDetailModel) {
        viewGitUserDetail?.renderGitUserDetail(
            gitUserEntityDataMapper.transform(gitUserDetailModel)
        )
    }

    private inner class GitUserDetailObserver : DefaultObserver<GitUserDetailModel>() {
        override fun onComplete() {
            this@GitUserDetailPresenter.hideViewLoading()
        }

        override fun onNext(t: GitUserDetailModel) {
            this@GitUserDetailPresenter.showGitUserDetailInView(t)
        }

        override fun onError(e: Throwable) {
            this@GitUserDetailPresenter.showViewRetry()
            this@GitUserDetailPresenter.hideViewLoading()
            this@GitUserDetailPresenter.showErrorMessage(
                DefaultErrorBundle(e as Exception)
            )
            Log.e("Error", e.message.toString())
        }
    }
}