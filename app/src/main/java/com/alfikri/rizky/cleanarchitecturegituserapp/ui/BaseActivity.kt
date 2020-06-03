package com.alfikri.rizky.cleanarchitecturegituserapp.ui

import android.os.Bundle
import android.os.PersistableBundle
import androidx.appcompat.app.AppCompatActivity
import com.alfikri.rizky.cleanarchitecturegituserapp.application.AndroidApplication
import com.alfikri.rizky.cleanarchitecturegituserapp.di.components.ApplicationComponent
import com.alfikri.rizky.cleanarchitecturegituserapp.di.modules.ActivityModule

open class BaseActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)
        this@BaseActivity.getApplicationComponent().inject(this@BaseActivity)
    }

    protected fun getApplicationComponent(): ApplicationComponent {
        return (application as AndroidApplication).getAppComponent()
    }

    protected fun getActivityModule(): ActivityModule {
        return ActivityModule(this@BaseActivity)
    }
}