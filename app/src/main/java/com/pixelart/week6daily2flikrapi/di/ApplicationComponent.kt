package com.pixelart.week6daily2flikrapi.di

import com.pixelart.week6daily2flikrapi.ui.detailscreen.DetailActivity
import com.pixelart.week6daily2flikrapi.ui.mainscreen.MainActivity
import dagger.Component

@Component(modules = [ApplicationModule::class])
interface ApplicationComponent {

    fun injectMainScreen(mainScreen: MainActivity)
    fun injectDetailScreen(detailScreen: DetailActivity)
}