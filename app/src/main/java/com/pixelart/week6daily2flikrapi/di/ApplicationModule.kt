package com.pixelart.week6daily2flikrapi.di

import android.app.Activity
import android.databinding.DataBindingUtil
import com.pixelart.week6daily2flikrapi.R
import com.pixelart.week6daily2flikrapi.base.BaseActivity
import com.pixelart.week6daily2flikrapi.databinding.ActivityDetailBinding
import com.pixelart.week6daily2flikrapi.databinding.ActivityMainBinding
import com.pixelart.week6daily2flikrapi.remote.APIService
import com.pixelart.week6daily2flikrapi.ui.detailscreen.ContractDetail
import com.pixelart.week6daily2flikrapi.ui.detailscreen.DetailActivity
import com.pixelart.week6daily2flikrapi.ui.detailscreen.PresenterDetails
import com.pixelart.week6daily2flikrapi.ui.mainscreen.ContractMain
import com.pixelart.week6daily2flikrapi.ui.mainscreen.MainActivity
import com.pixelart.week6daily2flikrapi.ui.mainscreen.PresenterMain
import dagger.Module
import dagger.Provides

@Module(includes = [NetworkModule::class])
class ApplicationModule (private val baseActivity: BaseActivity<*>){

    @Provides
    fun providesMainContractPresenter(apiService: APIService): ContractMain.Presenter = PresenterMain(baseActivity as MainActivity, apiService)

    @Provides
    fun funProvidesMainScreenBinding():ActivityMainBinding = DataBindingUtil.setContentView(baseActivity as Activity, R.layout.activity_main)

    @Provides
    fun providesDetailContractPresenter(): ContractDetail.Presenter = PresenterDetails(baseActivity as DetailActivity)

    @Provides
    fun providesDetailScreenBinding():ActivityDetailBinding = DataBindingUtil.setContentView(baseActivity as Activity, R.layout.activity_detail)
}