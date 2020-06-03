package com.alfikri.rizky.cleanarchitecturegituserapp.application

import android.app.Application
import com.alfikri.rizky.cleanarchitecturegituserapp.di.components.ApplicationComponent
import com.alfikri.rizky.cleanarchitecturegituserapp.di.components.DaggerApplicationComponent
import com.alfikri.rizky.cleanarchitecturegituserapp.di.modules.ApplicationModule

class AndroidApplication: Application() {

    private lateinit var applicationComponent: ApplicationComponent

    override fun onCreate() {
        super.onCreate()
        initDagger()
    }

    private fun initDagger() {
        applicationComponent = DaggerApplicationComponent.builder()
            .applicationModule(ApplicationModule(this@AndroidApplication))
            .build()
    }

    fun getAppComponent(): ApplicationComponent {
        return applicationComponent
    }
}