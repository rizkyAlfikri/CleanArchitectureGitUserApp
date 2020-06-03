package com.alfikri.rizky.cleanarchitecturegituserapp.di

interface HasComponent<C> {
    fun getComponent(): C
}