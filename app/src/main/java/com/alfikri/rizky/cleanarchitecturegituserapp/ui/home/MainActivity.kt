package com.alfikri.rizky.cleanarchitecturegituserapp.ui.home

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.alfikri.rizky.cleanarchitecturegituserapp.R
import com.alfikri.rizky.cleanarchitecturegituserapp.di.HasComponent
import com.alfikri.rizky.cleanarchitecturegituserapp.di.components.DaggerUserComponent
import com.alfikri.rizky.cleanarchitecturegituserapp.di.components.UserComponent
import com.alfikri.rizky.cleanarchitecturegituserapp.ui.BaseActivity

class MainActivity : BaseActivity(), HasComponent<UserComponent> {

    private lateinit var userComponent: UserComponent

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initializeInjector()
    }

    private fun initializeInjector() {
        userComponent = DaggerUserComponent.builder()
            .applicationComponent(getApplicationComponent())
            .activityModule(getActivityModule())
            .build()
    }

    override fun getComponent(): UserComponent {
        return userComponent
    }


}
