package com.alfikri.rizky.cleanarchitecturegituserapp.di.modules

import android.app.Activity
import com.alfikri.rizky.cleanarchitecturegituserapp.di.PerActivity
import dagger.Module
import dagger.Provides


@Module
class ActivityModule(private val activity: Activity) {

    @Provides
    @PerActivity
    fun getActivity(): Activity {
        return activity
    }
}