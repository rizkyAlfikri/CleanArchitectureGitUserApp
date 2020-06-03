package com.alfikri.rizky.cleanarchitecturegituserapp.ui.detail

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.alfikri.rizky.cleanarchitecturegituserapp.R
import com.alfikri.rizky.cleanarchitecturegituserapp.databinding.ActivityDetailGitUseBinding
import com.alfikri.rizky.cleanarchitecturegituserapp.di.components.DaggerUserComponent
import com.alfikri.rizky.cleanarchitecturegituserapp.di.components.UserComponent
import com.alfikri.rizky.cleanarchitecturegituserapp.entity.GitUserDetailEntity
import com.alfikri.rizky.cleanarchitecturegituserapp.presenter.GitUserDetailPresenter
import com.alfikri.rizky.cleanarchitecturegituserapp.ui.BaseActivity
import com.alfikri.rizky.cleanarchitecturegituserapp.ui.adapter.TabPagerAdapter
import com.alfikri.rizky.cleanarchitecturegituserapp.utils.invisible
import com.alfikri.rizky.cleanarchitecturegituserapp.utils.visible
import com.alfikri.rizky.cleanarchitecturegituserapp.view.GitUserDetailView
import com.bumptech.glide.Glide
import javax.inject.Inject

class DetailGitUserActivity : BaseActivity(), GitUserDetailView {

    @Inject
    lateinit var gitUserDetailPresenter: GitUserDetailPresenter

    private lateinit var binding: ActivityDetailGitUseBinding
    private lateinit var userComponent: UserComponent
    private var username = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailGitUseBinding.inflate(layoutInflater)
        setContentView(binding.root)

        username = intent.getStringExtra("username") ?: ""

        val tabPagerAdapter = TabPagerAdapter(this@DetailGitUserActivity, supportFragmentManager)
        binding.viewPager.adapter = tabPagerAdapter
        binding.tabLayout.setupWithViewPager(binding.viewPager)
        initializeInjector()

        gitUserDetailPresenter.setView(this)
        gitUserDetailPresenter.initialize(username)
    }

    private fun initializeInjector() {
        userComponent = DaggerUserComponent.builder()
            .applicationComponent(getApplicationComponent())
            .activityModule(getActivityModule())
            .build()
        userComponent.inject(this@DetailGitUserActivity)
    }

    override fun renderGitUserDetail(gitUserDetailEntity: GitUserDetailEntity) {
        gitUserDetailEntity.also {
            binding.tvName.text = it.name
            binding.tvLocation.text = it.location
            binding.tvCompany.text = it.company
            Glide.with(this@DetailGitUserActivity).load(it.avatarUrl).into(binding.imgGitUser)
        }
    }

    override fun showLoading() {
        binding.progressBar.visible()
    }

    override fun hideLoading() {
        binding.progressBar.invisible()
    }

    override fun showRetry() {

    }

    override fun hideRetry() {

    }

    override fun showError(errorMessage: String) {
        Toast.makeText(this@DetailGitUserActivity, errorMessage, Toast.LENGTH_SHORT).show()
    }

    override fun getContext(): Context {
        return this@DetailGitUserActivity
    }

    override fun onDestroy() {
        super.onDestroy()
        gitUserDetailPresenter.onDestroy()
    }
}
