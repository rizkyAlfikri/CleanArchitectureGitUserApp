package com.alfikri.rizky.cleanarchitecturegituserapp.view

import android.content.Context

interface LoadDataView {

    fun showLoading()

    fun hideLoading()

    fun showRetry()

    fun hideRetry()

    fun showError(errorMessage: String)

    fun getContext(): Context
}